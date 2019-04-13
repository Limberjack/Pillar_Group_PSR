package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Transforms the result from the data base to the class
 *
 * @param <T> class in which to transform
 */
public interface RowMapper<T> {
    T mapRow(ResultSet row) throws SQLException;
}