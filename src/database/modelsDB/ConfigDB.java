package database.modelsDB;

import java.util.List;

/**
 * General config
 */
public class ConfigDB {

    private String name;
    private List<ConfigFileDB> ConfigFiles;

    /**
     *
     * @param name config's name
     * @param configFiles list of all files which are contained to this config
     */
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

    public void setName(String name) {
        this.name = name;
    }

    public void setConfigFiles(List<ConfigFileDB> configFiles) {
        ConfigFiles = configFiles;
    }
}