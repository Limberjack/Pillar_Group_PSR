package database.repositories;

import database.DBConfigFileable;
import database.DBConfigable;
import database.DBProgramable;
import database.modelsDB.ConfigDB;
import database.modelsDB.ConfigFileDB;
import database.modelsDB.ConfigInfo;

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
                    rs.getString("programName"),
                    rs.getString("fileName"),
                    rs.getString("pathProgram"),
                    rs.getString("pathBackup")
            );

    /**
     * Adds new config to the data base
     *
     * @param dbProgramable interface for program's description
     * @param dbConfigable  interface for config's description
     */
    @Override
    public void save(DBProgramable dbProgramable, DBConfigable dbConfigable) {
        // The SQL Query
        String sqlQuery = "";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            // Do the SQL query

            //stmt.execute(); //fixme

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Finds all configs for current program
     *
     * @param dbProgramable interface for program's description
     * @return List of configs.
     */
    @Override
    public List<DBConfigable> find(DBProgramable dbProgramable) {

        // The SQL Query
        String sqlQuery = "";

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
    private List<DBConfigable> convertConfigToInterface(Stack<ConfigInfo> configInfos) {

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

            List<DBConfigFileable> configFilesI = new ArrayList<>();
            while (!configFiles.isEmpty()) {
                configFilesI.add(configFiles.pop());
            }

            configsStack.push(new ConfigDB(configName, configFilesI));
        }

        List<DBConfigable> configsListInterfaces = new ArrayList<>();

        // Rewrites configsStack to the interfaces
        while (!configsStack.isEmpty()) {
            configsListInterfaces.add(configsStack.pop());
        }

        return configsListInterfaces;
    }
}