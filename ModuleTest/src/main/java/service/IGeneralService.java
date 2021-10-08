package service;

import java.util.List;

public interface IGeneralService<T>{
    List<T> getAll();

    T select(int id);

    boolean delete(int id);

    boolean update(T t);

    void add(T t);
}
