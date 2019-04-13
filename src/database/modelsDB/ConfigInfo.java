package database.modelsDB;

public class ConfigInfo {
    private String configName;
    private String fileName;
    private String pathProgram;
    private String pathBackup;

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
}