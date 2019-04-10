public class Program {
    private long id;
    private String name;
    private String version;

    public Program(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
