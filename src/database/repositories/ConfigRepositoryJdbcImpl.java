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
 * The data base realisation
 */
public class ConfigRepositoryJdbcImpl implements ConfigRepository {

    private Connection connection;

    /**
     * @param connection url and properties for data base connection
     */
    public ConfigRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * The RowMapper's realisation
     */
    private static RowMapper<ConfigInfo> configRowMapper = rs ->
            new ConfigInfo(
                    rs.getString("config_name"),
                    rs.getString("file_name"),
                    rs.getString("config_file_name"),
                    rs.getString("config_file_path_backup")
            );

    private static RowMapper<ProgramDB> programRowMapper = rs ->
            new ProgramDB(
                    rs.getString("name"),
                    rs.getString("version")
            );

    /**
     * Adds new config to the data base
     *
     * @param IProgramDb interface for program's description
     * @param IConfigDb  interface for config's description
     */
    @Override
    public void save(IProgramDB IProgramDb, IConfigDB IConfigDb) {

        // The SQL Queries

        //fixme if this program exists

        // Inserting program's information to the data base
        String sqlQueryProgram = "INSERT INTO program (name, version) " +
                "VALUES (" + IProgramDb.getProgramName() + ", " + IProgramDb.getProgramVersion() + ");";

        // Inserting config's information to the data base
        String sqlQueryConfig = "INSERT INTO config (name) " +
                "VALUES (" + IConfigDb.getConfigName() + ");";

        PreparedStatement stmtProgram = null;
        PreparedStatement stmtConfig = null;
        try {
            stmtProgram = connection.prepareStatement(sqlQueryProgram);
            stmtConfig = connection.prepareStatement(sqlQueryConfig);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int id_pr = 0;
        int id_conf = 0;
        try {
            id_pr = stmtProgram.executeUpdate();
            id_conf = stmtConfig.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        for (IConfigFileDB file : IConfigDb.getConfigFiles()) {

            // Inserting information about config's files
            String sqlQueryFile = "INSERT INTO File (name, path_program, path_backup) " +
                    "VALUES (" + file.getFileName() + ", " + file.getFilePathProgram() + ", " + file.getFilePathBackup() + ");";

            PreparedStatement stmtFile = null;
            try {
                stmtFile = connection.prepareStatement(sqlQueryFile);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                stmtFile.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }


            // fixme why in the loop?
            // Inserting information about linking between program and config
            String sqlQuerryPC_Store = "INSERT INTO pc_store (id_program, id_config) " +
                    "VALUES(" + id_pr + ", " + id_conf + " ); ";

            PreparedStatement stmtPC_Store = null;
            try {
                stmtPC_Store = connection.prepareStatement(sqlQuerryPC_Store);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                stmtPC_Store.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
            // stack of configInfos models
            Stack<ConfigInfo> configInfos = new Stack<>();

            // reads the result of sql query
            while (rs.next()) {
                configInfos.push(configRowMapper.mapRow(rs));
            }

            //return convertConfigToInterface(configInfos); //fixme
            return null; //fixme //remove

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Converts stack of configInfos to the list of 'DBConfigable' interfaces
     *
     * @param configInfos
     * @return
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

    @Override
    public List<ProgramDB> findAllPrograms() {
        // The SQL Query
        String sqlQuery = "SELECT * FROM program";
        try (Statement stmt = connection.createStatement();
             // Do the SQL query and get the result
             ResultSet rs = stmt.executeQuery(sqlQuery)) {

            Stack<ProgramDB> programs = new Stack<>();
            while (rs.next()) {
                programs.add(programRowMapper.mapRow(rs));
            }

            return programs;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}