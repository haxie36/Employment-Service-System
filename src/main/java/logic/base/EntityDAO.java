package logic.base;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class EntityDAO<T extends HasId> {

    protected abstract T map(ResultSet rs) throws SQLException;

    public abstract boolean add(T item);
    public abstract T getById(int id);
    public abstract boolean update(T item);
    public abstract boolean delete(int id);
    public abstract T[] getAll();
}