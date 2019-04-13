import java.util.ArrayList;
import java.util.List;

public class ConfigDB {
    private String name;
    private List<ConfigFileDB> ConfigFiles = new ArrayList<>();

    public ConfigDB(String name, List<ConfigFileDB> configFiles) {
        this.name = name;
        ConfigFiles = configFiles;
    }

    public String getName() {
        return name;
    }

    public List<ConfigFileDB> getConfigFiles() {
        return ConfigFiles;
    }
}
