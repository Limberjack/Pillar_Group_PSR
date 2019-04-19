package database.modelsDB;

import database.IProgramDB;

/**
 * General information about Program
 */
public class ProgramDB implements IProgramDB {
    private String programName;
    private String programVersion;

    /**
     * @param programName    name of the program
     * @param programVersion version of the program
     */
    public ProgramDB(String programName, String programVersion) {
        this.programName = programName;
        this.programVersion = programVersion;
    }

    public String getProgramName() {
        return programName;
    }

    public String getProgramVersion() {
        return programVersion;
    }

    @Override
    public String toString() {
        return "ProgramDB{" +
                "programName='" + programName + '\'' +
                ", programVersion='" + programVersion + '\'' +
                '}';
    }
}