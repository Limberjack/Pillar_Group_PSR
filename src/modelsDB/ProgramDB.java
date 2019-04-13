package modelsDB;

public class ProgramDB {
    private String name;
    private String version;

    public ProgramDB(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }
    public String getVersion() {
        return version;
    }
}