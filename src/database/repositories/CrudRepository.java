package database.repositories;

import java.util.List;

/**
 * Main concept of the repository
 */
public interface CrudRepository<P, C> {
    void save(P p, C c);

    List<C> find(P p);
}