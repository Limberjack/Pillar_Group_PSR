package database.modelsDB;

/**
 * File to save
 */
public class ConfigFileDB {
    private String name;
    private String pathProgram;
    private String pathBackup;

    /**
     *
     * @param name name of the file with file extension
     * @param pathProgram path where this file is located for program
     * @param pathBackup path where this file is copied to the backup
     */
    public ConfigFileDB(String name, String pathProgram, String pathBackup) {
        this.name = name;
        this.pathProgram = pathProgram;
        this.pathBackup = pathBackup;
    }


    public String getName() {
        return name;
    }

    public String getPathProgram() {
        return pathProgram;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPathProgram(String pathProgram) {
        this.pathProgram = pathProgram;
    }

    public String getPathBackup() {
        return pathBackup;
    }

    public void setPathBackup(String pathBackup) {
        this.pathBackup = pathBackup;
    }
}
