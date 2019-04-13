package database.repositories;

import java.util.List;

public interface CrudRepository<P, C> {
    void save(P p, C c);
    List<C> find(P p);
}