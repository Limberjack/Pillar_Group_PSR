package database.repositories;

import database.DBConfigable;
import database.DBProgramable;
import database.modelsDB.ConfigInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigRepositoryJdbcImpl implements ConfigRepository{

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
    public boolean save(DBProgramable dbProgramable, DBConfigable dbConfigable) {
        // The SQL Query
        String sqlQuery = "";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            // Do the SQL query
            stmt.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        return true;
    }

    @Override
    public List<DBConfigable> find(DBProgramable dbProgramable) {

        // The SQL Query
        String sqlQuery = "SELECT * FROM shop_user";
        try (Statement stmt = connection.createStatement();
             // Do the SQL query and get the result
             ResultSet rs = stmt.executeQuery(sqlQuery)) {
            List<ConfigInfo> configInfos = new ArrayList<>();
            while (rs.next()) {
                configInfos.add(configRowMapper.mapRow(rs));
            }

            return null;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
