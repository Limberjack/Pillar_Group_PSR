package database.repositories;

import database.DBConfigFileable;
import database.DBConfigable;
import database.DBProgramable;
import database.modelsDB.ConfigDB;
import database.modelsDB.ConfigFileDB;
import database.modelsDB.ConfigInfo;

import java.sql.*;
import java.util.*;

public class ConfigRepositoryJdbcImpl implements ConfigRepository {

    private Connection connection;

    public ConfigRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    private static RowMapper<ConfigInfo> configRowMapper = rs ->
            new ConfigInfo(
                    rs.getString("programName"),
                    rs.getString("fileName"),
                    rs.getString("pathProgram"),
                    rs.getString("pathBackup")
            );

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

    @Override
    public List<DBConfigable> find(DBProgramable dbProgramable) {

        // The SQL Query
        String sqlQuery = "";

        try (Statement stmt = connection.createStatement();
             // Do the SQL query and get the result

             ResultSet rs = stmt.executeQuery(sqlQuery)) {
            Stack<ConfigInfo> configInfos = new Stack<>();

            while (rs.next()) {
                configInfos.push(configRowMapper.mapRow(rs));
            }

            //return convertConfigToInterface(configInfos); //fixme
            return null; //fixme

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<DBConfigable> convertConfigToInterface(Stack<ConfigInfo> configInfos) {
        Map<String, List<ConfigFileDB>> configsMap = new HashMap<>();

        while (!configInfos.isEmpty()) {
            ConfigInfo configToAdd = configInfos.pop();

            List<ConfigFileDB> configFiles = configsMap.getOrDefault(configToAdd.getConfigName(), new ArrayList<>());
            configFiles.add(new ConfigFileDB(configToAdd.getFileName(), configToAdd.getPathProgram(), configToAdd.getPathBackup()));

            configsMap.put(configToAdd.getConfigName(), configFiles);
        }

        Stack<ConfigDB> configsStack = new Stack<>();
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

        List<DBConfigable> listToReturn = new ArrayList<>();

        while (!configsStack.isEmpty()) {
            listToReturn.add(configsStack.pop());
        }
        return listToReturn;
    }
}