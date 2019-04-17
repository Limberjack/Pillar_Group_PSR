package database.modelsDB;

/**
 * Config of joiner general config's description and config file's of this config description
 */
public class ConfigInfo {
    private String configName;
    private String fileName;
    private String pathProgram;
    private String pathBackup;

    /**
     * @param configName  name of the general config
     * @param fileName    name of the file of from this config
     * @param pathProgram file's path where it is located for the user's program
     * @param pathBackup  file's path where it is backup
     */
    public ConfigInfo(String configName, String fileName, String pathProgram, String pathBackup) {
        this.configName = configName;
        this.fileName = fileName;
        this.pathProgram = pathProgram;
        this.pathBackup = pathBackup;
    }

    public String getConfigName() {
        return configName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPathProgram() {
        return pathProgram;
    }

    public String getPathBackup() {
        return pathBackup;
    }

    @Override
    public String toString() {
        return "ConfigInfo{" +
                "configName='" + configName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", pathProgram='" + pathProgram + '\'' +
                ", pathBackup='" + pathBackup + '\'' +
                '}';
    }
}