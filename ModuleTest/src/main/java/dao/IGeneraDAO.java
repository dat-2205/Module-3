package dao;

import java.util.List;

public interface IGeneraDAO<T> {
    List<T> getAll();

    T select(int id);

    boolean delete(int id);

    boolean update(T t);

    void add(T t);
}
