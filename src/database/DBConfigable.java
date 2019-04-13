package database;

import java.util.List;

/**
 * Use this interface for the general config
 */
public interface DBConfigable {
    String getConfigName();
    List<DBConfigFileable> getConfigFiles();
}