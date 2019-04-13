package database;

import java.util.List;

public interface DBConfigable {
    String getConfigName();
    List<DBConfigFileable> getConfigFiles();
}
