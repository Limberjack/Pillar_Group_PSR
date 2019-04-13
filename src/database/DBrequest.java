package database;

import database.modelsDB.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Class which contains static methods for other users
 */
public class DBrequest {

    private static Connection connection;

    private static String username = "postgres";
    private static String password = "postgres";

    /**
     * Initialize data base when the program starts
     */
    public static void startDataBase(){
        String url = "jdbc:postgresql://localhost:5432/config_db";
        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);
        connection = null;
        try {
            connection = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds new config to the data base
     * @param program
     * @param config
     * @return true if the adding was successful or false if wasn't
     */
    public static boolean add(ProgramDB program, ConfigDB config){

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

    /**
     * This method returns suitable configs for the requested program
     * @param program description of program which config is required
     * @return List of configs
     */
    public static List<ConfigDB> query(ProgramDB program){
        return null;
    }
}