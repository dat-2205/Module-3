package com.codegym.service;

import com.codegym.dao.IUserDAO;
import com.codegym.dao.UserDAO;
import com.codegym.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService implements IUserService{
    private IUserDAO userDAO = new UserDAO();

    @Override
    public List<User> getAll() {
        return userDAO.selectAllUsers();
    }

    @Override
    public boolean save(User user) {
        try {
            userDAO.insertUser(user);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean update(int id, User user) {
        boolean isUpdate = false;
        try {
            isUpdate = userDAO.updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdate;
    }

    @Override
    public boolean delete(int id) {
        boolean isDelete = false;
        try {
            isDelete = userDAO.deleteUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDelete;
    }

    @Override
    public User findById(int id) {
        return userDAO.selectUser(id);
    }
}
