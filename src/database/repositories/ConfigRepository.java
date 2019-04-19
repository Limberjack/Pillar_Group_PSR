package database.repositories;

import database.IConfigDB;
import database.IProgramDB;

import java.util.List;

/**
 * Interface's extension for configs
 */
public interface ConfigRepository extends CrudRepository<IProgramDB, IConfigDB> {
    List<IProgramDB> findAllPrograms();

    Integer getProgramId(IProgramDB programDB);
}