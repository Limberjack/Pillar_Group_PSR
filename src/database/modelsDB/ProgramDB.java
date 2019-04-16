package database.modelsDB;

import database.IProgramDB;

public class ProgramDB implements IProgramDB {
    private String programName;
    private String programVersion;

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
}
