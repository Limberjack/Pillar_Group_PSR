package database.test;

import database.repositories.ConfigRepository;
import database.repositories.ConfigRepositoryJdbcImpl;

import java.sql.SQLException;


public class ConnectionMain {

    static ConfigRepository configRepository;

    public static void main(String[] args) throws SQLException {
        System.out.println( ConfigRepositoryJdbcImpl.getInstance().findAllPrograms());
        System.out.println( ConfigRepositoryJdbcImpl.getInstance().findAllPrograms());
    }
}