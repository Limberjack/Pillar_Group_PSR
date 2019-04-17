package database.test;

import database.repositories.DataBase;

import java.sql.SQLException;

/**
 * Test methods of date base
 */
public class AppMain {

    public static void main(String[] args) throws SQLException {

        // findAll
        System.out.println("Programs:");
        System.out.println(DataBase.getInstance().findAllPrograms());
        System.out.println();

    }
}