package database.repositories;

import database.IConfigDB;
import database.IProgramDB;
import database.modelsDB.ProgramDB;

import java.util.List;

/**
 * Interface's extension for configs
 */
public interface ConfigRepository extends CrudRepository<IProgramDB, IConfigDB> {
    List<ProgramDB> findAllPrograms();
}