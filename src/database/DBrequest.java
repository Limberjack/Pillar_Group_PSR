package database;

import database.modelsDB.*;

import java.util.List;

/**
 * Class which contains static methods for other users
 */
public class DBrequest {
    /**
     * Adds new config to the data base
     * @param program
     * @param config
     * @return true if the adding was successful or false if wasn't
     */
    public static boolean add(ProgramDB program, ConfigDB config){

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