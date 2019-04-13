package database.modelsDB;

import java.util.List;

public class ConfigInfo {
    private String programName;
    private String fileName;
    private String pathProgram;
    private String pathBackup;

    public ConfigInfo(String programName, String fileName, String pathProgram, String pathBackup) {
        this.programName = programName;
        this.fileName = fileName;
        this.pathProgram = pathProgram;
        this.pathBackup = pathBackup;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPathProgram() {
        return pathProgram;
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
