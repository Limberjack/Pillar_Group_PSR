package database.test;



import database.repositories.ConfigRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class AppMain {



    public static void main(String[] args) throws SQLException {

        // findAll
        System.out.println("Programs:");
        System.out.println(ConfigRepositoryJdbcImpl.getInstance().findAllPrograms());
        System.out.println();






    }
}
