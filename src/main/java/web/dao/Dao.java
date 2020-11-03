package web.dao;

import java.util.List;

public interface Dao<T> {
    public List<T> findAll();

    public T findOne(long id);

    public T findOne(String keyNamedField);

    public void delete(T entity);

    public void deleteById(long entityId);

    public void update(T entity);

    public void create(T entity);
}
