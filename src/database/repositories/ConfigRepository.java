package database.repositories;

import database.DBConfigable;
import database.DBProgramable;

/**
 * Interface's extension for configs
 */
public interface ConfigRepository extends CrudRepository<DBProgramable, DBConfigable> {
}