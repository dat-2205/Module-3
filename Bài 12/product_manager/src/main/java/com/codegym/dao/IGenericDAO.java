package com.codegym.dao;

import java.sql.SQLException;
import java.util.List;

public interface IGenericDAO<T> {
    public void insertUser(T t) throws SQLException;

    public T selectUser(int id);

    public List<T> selectAllUsers();

    public boolean deleteUser(int id) throws SQLException;

    public boolean updateUser(T T) throws SQLException;
}
