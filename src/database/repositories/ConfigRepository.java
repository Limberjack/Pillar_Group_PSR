package database.repositories;

import database.DBConfigable;
import database.DBProgramable;

public interface ConfigRepository extends CrudRepository<DBProgramable, DBConfigable> {
}