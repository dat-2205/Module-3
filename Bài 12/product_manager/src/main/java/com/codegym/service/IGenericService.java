package com.codegym.service;

import com.codegym.model.User;

import java.util.List;

public interface IGenericService<T> {
    List<T> getAll();

    boolean save(T t);

    boolean update(int id, T t);

    boolean delete(int id);

    T findById(int id);

}
