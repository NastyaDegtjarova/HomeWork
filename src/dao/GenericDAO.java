package dao;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nastya on 19.11.2017.
 */
public interface GenericDAO<T, ID> {

    T getById(ID id) throws SQLException;

    List<T> getAll() throws SQLException;

    void save(T t);

    void update(T t);

    void delete(T t);
}