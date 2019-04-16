package database;

import java.util.List;

/**
 * Use this interface for the general config
 */
public interface IConfigDB {
    String getConfigName();
    List<IConfigFileDB> getConfigFiles();
}