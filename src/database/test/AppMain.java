package database.test;

import database.repositories.ConfigRepository;
import database.repositories.ConfigRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class AppMain {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/confix";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConfigRepository configRepository =
                new ConfigRepositoryJdbcImpl(conn);

        // findAll
        System.out.println("Programs:");
        System.out.println(configRepository.findAllPrograms());
        System.out.println();


    }
}
