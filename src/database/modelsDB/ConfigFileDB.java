package database.modelsDB;

import database.IConfigFileDB;

/**
 * File of the config to save
 */
public class ConfigFileDB implements IConfigFileDB {
    private String name;
    private String pathProgram;
    private String pathBackup;

    /**
     * @param name        name of the file with file extension
     * @param pathProgram path where this file is located for program
     * @param pathBackup  path where this file is copied to the backup
     */
    public ConfigFileDB(String name, String pathProgram, String pathBackup) {
        this.name = name;
        this.pathProgram = pathProgram;
        this.pathBackup = pathBackup;
    }

    @Override
    public String getFileName() {
        return name;
    }

    @Override
    public String getFilePathProgram() {
        return pathProgram;
    }

    @Override
    public String getFilePathBackup() {
        return pathBackup;
    }

    @Override
    public String toString() {
        return "ConfigFileDB{" +
                "name='" + name + '\'' +
                ", pathProgram='" + pathProgram + '\'' +
                ", pathBackup='" + pathBackup + '\'' +
                '}';
    }
}