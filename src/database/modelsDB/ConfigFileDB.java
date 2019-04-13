package database.modelsDB;

import database.DBConfigFileable;

/**
 * File to save
 */
public class ConfigFileDB implements DBConfigFileable {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPathProgram(String pathProgram) {
        this.pathProgram = pathProgram;
    }

    public void setPathBackup(String pathBackup) {
        this.pathBackup = pathBackup;
    }

    @Override
    public String getFileName() {
        return pathProgram;
    }

    @Override
    public String getFilePathProgram() {
        return null;
    }

    @Override
    public String getFilePathBackup() {
        return pathBackup;
    }
}