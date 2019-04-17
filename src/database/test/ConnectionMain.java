package database.test;

import database.repositories.DataBase;

import java.sql.SQLException;

/**
 * Test creation of data base
 */
public class ConnectionMain {

    public static void main(String[] args) throws SQLException {
        System.out.println( DataBase.getInstance().findAllPrograms());
        System.out.println( DataBase.getInstance().findAllPrograms());
    }
}