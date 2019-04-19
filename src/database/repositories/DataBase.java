package database.repositories;

import database.IConfigFileDB;
import database.IConfigDB;
import database.IProgramDB;
import database.modelsDB.ConfigDB;
import database.modelsDB.ConfigFileDB;
import database.modelsDB.ConfigInfo;
import database.modelsDB.ProgramDB;

import java.sql.*;
import java.util.*;

/**
 * The data base for configs realisation
 */
public class DataBase implements ConfigRepository {

    private Connection connection;
    private static DataBase dataBase;

    /**
     * Use this method to get data base's methods
     *
     * @return static instance of data base
     * @throws SQLException
     */
    public static DataBase getInstance() throws SQLException {
        // Create data base if it's a first lunch
        if (dataBase == null) {

            // Data base's properties
            String url = "jdbc:postgresql://localhost:5432/confix";
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "postgres");
            Connection conn = DriverManager.getConnection(url, props);

            dataBase = new DataBase(conn);
        }

        return dataBase;
    }

    /**
     * @param connection url and properties for data base connection
     */
    private DataBase(Connection connection) {
        this.connection = connection;
    }

    /**
     * The RowMapper's realisation for ConfigInfo
     */
    private static RowMapper<ConfigInfo> configRowMapper = rs ->
            new ConfigInfo(
                    rs.getString("config_name"),
                    rs.getString("file_name"),
                    rs.getString("config_file_name"),
                    rs.getString("config_file_path_backup")
            );

    /**
     * The RowMapper's realisation for ProgramDB
     */
    private static RowMapper<ProgramDB> programRowMapper = rs ->
            new ProgramDB(
                    rs.getString("name"),
                    rs.getString("version")
            );

    /**
     * The RowMapper's realisation for getting program's id
     */
    private static RowMapper<Integer> programIdRowMapper = rs ->
            rs.getInt("id");

    /**
     * Adds new config to the data base
     *
     * @param IProgramDb interface for program's description
     * @param IConfigDb  interface for config's description
     */
    @Override
    public void save(IProgramDB IProgramDb, IConfigDB IConfigDb) {

        // Program's id
        // Initialise id if data base contains this program
        Integer idProgram = getProgramId(IProgramDb);

        // Inserting program's information to the data base if it does not contains it
        if (idProgram == null) {
            String sqlQueryProgram = "INSERT INTO program (name, version) " +
                    "VALUES (" + IProgramDb.getProgramName() + ", " + IProgramDb.getProgramVersion() + ");";

            PreparedStatement stmtProgram;
            try {
                stmtProgram = connection.prepareStatement(sqlQueryProgram);
                idProgram = stmtProgram.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        // Config's id
        Integer idConfig = null;
        // Inserting config's information to the data base
        String sqlQueryConfig = "INSERT INTO config (name) " +
                "VALUES (" + IConfigDb.getConfigName() + ");";

        PreparedStatement stmtConfig;
        try {
            stmtConfig = connection.prepareStatement(sqlQueryConfig);
            idConfig = stmtConfig.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // Inserting information about config's files
        for (IConfigFileDB file : IConfigDb.getConfigFiles()) {
            // Inserting file's information
            String sqlQueryFile = "INSERT INTO File (name, path_program, path_backup) " +
                    "VALUES (" + file.getFileName() + ", " + file.getFilePathProgram() + ", " + file.getFilePathBackup() + ");";

            PreparedStatement stmtFile;
            try {
                stmtFile = connection.prepareStatement(sqlQueryFile);
                stmtFile.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Inserting information about link between program and config
        String sqlQuerryPC_Store = "INSERT INTO pc_store (id_program, id_config) " +
                "VALUES(" + idProgram + ", " + idConfig + " ); ";

        PreparedStatement stmtPC_Store;
        try {
            stmtPC_Store = connection.prepareStatement(sqlQuerryPC_Store);
            stmtPC_Store.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finds all configs for current program
     *
     * @param IProgramDb interface for program's description
     * @return List of configs.
     */
    @Override
    public List<IConfigDB> find(IProgramDB IProgramDb) {

        // The SQL Query
        String sqlQuery = "SELECT prco_store.name AS config_name, " +
                "file.name AS file_name, " +
                "file.path_program AS config_file_name, " +
                "file.path_backup AS config_file_path_backup " +
                "FROM (" +
                "SELECT * " +
                "FROM (" +
                "SELECT pc_store.id_config " +
                "FROM pc_store INNER JOIN program ON pc_store.id_program = program.id " +
                "WHERE program.name = 'word' " +
                "AND program.version = '12') AS prc_store " +
                "INNER JOIN config ON prc_store.id_config = config.id) AS prco_store " +
                "INNER JOIN file ON prco_store.id = file.id;";

        try (Statement stmt = connection.createStatement();
             // Do the SQL query and get the result
             ResultSet rs = stmt.executeQuery(sqlQuery)) {

            // Stack of configInfos models
            Stack<ConfigInfo> configInfos = new Stack<>();

            // Reads the result of sql query
            while (rs.next()) {
                configInfos.push(configRowMapper.mapRow(rs));
            }

            // returns configInfos converted to the interface
            return convertConfigToInterface(configInfos);

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Converts stack of configInfos to the list of 'DBConfigable' interfaces
     *
     * @param configInfos objects to convert to interfaces
     * @return List of 'IConfigDB' interfaces
     */
    private List<IConfigDB> convertConfigToInterface(Stack<ConfigInfo> configInfos) {

        // key - name of config, value - list of files for this config
        Map<String, List<ConfigFileDB>> configsMap = new HashMap<>();

        // Rewrites configInfos's stack to the map
        while (!configInfos.isEmpty()) {
            ConfigInfo configToAdd = configInfos.pop();

            List<ConfigFileDB> configFiles = configsMap.getOrDefault(configToAdd.getConfigName(), new ArrayList<>());
            configFiles.add(new ConfigFileDB(configToAdd.getFileName(), configToAdd.getPathProgram(), configToAdd.getPathBackup()));

            configsMap.put(configToAdd.getConfigName(), configFiles);
        }

        Stack<ConfigDB> configsStack = new Stack<>();

        // Rewrites configsMap to the configsStack
        for (String configName : configsMap.keySet()) {

            Stack<ConfigFileDB> configFiles = new Stack<>();

            for (ConfigFileDB configFile : configsMap.get(configName)) {
                configFiles.push(configFile);
            }

            List<IConfigFileDB> configFilesI = new ArrayList<>();
            while (!configFiles.isEmpty()) {
                configFilesI.add(configFiles.pop());
            }

            configsStack.push(new ConfigDB(configName, configFilesI));
        }

        List<IConfigDB> configsListInterfaces = new ArrayList<>();

        // Rewrites configsStack to the interfaces
        while (!configsStack.isEmpty()) {
            configsListInterfaces.add(configsStack.pop());
        }

        return configsListInterfaces;
    }

    /**
     * Finds programs which are contained in the data base
     *
     * @return list of 'IProgramDB' interfaces
     */
    @Override
    public List<IProgramDB> findAllPrograms() {
        // The SQL Query
        String sqlQuery = "SELECT * FROM program";
        try (Statement stmt = connection.createStatement();
             // Do the SQL query and get the result
             ResultSet rs = stmt.executeQuery(sqlQuery)) {

            // Programs from daat base
            Stack<ProgramDB> programs = new Stack<>();
            while (rs.next()) {
                programs.push(programRowMapper.mapRow(rs));
            }

            // Rewrites stack 'programs' to the list of 'IProgramDB' interfaces
            List<IProgramDB> programsI = new ArrayList<>();
            while (!programs.isEmpty()) {
                programsI.add(programs.pop());
            }
            return programsI;

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Finds program's id from data base
     *
     * @param programDB program which id must be get
     * @return program's id or null if this program does not contains in the data base
     */
    @Override
    public Integer getProgramId(IProgramDB programDB) {
        // The SQL Query
        String sqlQuery = "SELECT id FROM program " +
                "WHERE name =" + programDB.getProgramName() + "," +
                " version =" + programDB.getProgramVersion() + ";";

        try (Statement stmt = connection.createStatement();
             // Do the SQL query and get the result
             ResultSet rs = stmt.executeQuery(sqlQuery)) {

            Integer id = null;
            while (rs.next()) {
                id = programIdRowMapper.mapRow(rs);
            }

            return id;

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}