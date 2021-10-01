package com.codegym.dao;

import com.codegym.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, country) VALUES " +
            " (?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";
    private static final String SELECT_ALL_USER_OFFSET = "select * from users limit 3 offset ?;";

    private Connection connection = DBConnection.getConnection();

    public UserDAO() {
    }

    @Override
    public void insertUser(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_USERS_SQL);
        statement.setString(1, user.getName());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getCountry());
        statement.executeUpdate();
    }

    @Override
    public User selectUser(int id) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID);
            statement.setInt(1, id);
            ResultSet rc = statement.executeQuery();
            while (rc.next()) {
                String name = rc.getString("name");
                String email = rc.getString("email");
                String country = rc.getString("country");
                user = new User(id, name, email, country);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);
        statement.setInt(1, id);
        if (statement.executeUpdate() > 0) return true;
        return false;
    }

    @Override
    public boolean updateUser(User T) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);
        statement.setString(1, T.getName());
        statement.setString(2, T.getEmail());
        statement.setString(3, T.getCountry());
        statement.setInt(4, T.getId());
        statement.executeUpdate();
        if (statement.executeUpdate() > 0) return true;
        return false;
    }

    @Override
    public User getUserByIdStore(int id) {
        User user = null;
        String query = "CALL get_user_by_id(?)";

        try {
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();
            rs.next();
            String name = rs.getString("name");
            String email = rs.getString("email");
            String country = rs.getString("country");

            user = new User(id,name, email, country);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void insertUserStore(User user) throws SQLException {
        String query = "{CALL insert_users(?,?,?)}";

        try {
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setString(1, user.getName());

            callableStatement.setString(2, user.getEmail());

            callableStatement.setString(3, user.getCountry());

            System.out.println(callableStatement);

            callableStatement.executeUpdate();

        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    @Override
    public List<User> selectUserByOffset(int offset) {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USER_OFFSET);
            statement.setInt(1,offset);
            ResultSet rs =  statement.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id,name,email,country));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public int countRecord() {
        int soluong = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("select count(id) as soluong from users;");
            ResultSet rs = statement.executeQuery();
            rs.next();
            soluong = rs.getInt("soluong");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return soluong;
    }
}
