package database.modelsDB;

import database.DBConfigFileable;
import database.DBConfigable;

import java.util.List;

/**
 * General config
 */
public class ConfigDB implements DBConfigable {

    private String name;
    private List<DBConfigFileable> ConfigFiles;

    /**
     * @param name        config's name
     * @param configFiles list of all files which are contained to this config
     */
    public ConfigDB(String name, List<DBConfigFileable> configFiles) {
        this.name = name;
        ConfigFiles = configFiles;
    }

    @Override
    public String getConfigName() {
        return name;
    }

    @Override
    public List<DBConfigFileable> getConfigFiles() {
        return ConfigFiles;
    }
}