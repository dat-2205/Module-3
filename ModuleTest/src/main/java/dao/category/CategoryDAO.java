package dao.category;

import config.DBConnection;
import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO{
    private Connection connection = DBConnection.getConnection();
    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        try {
            String select_all_query = "select * from category";
            PreparedStatement statement = connection.prepareStatement(select_all_query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name =  rs.getString("name");
                String image = rs.getString("image");
                Category category = new Category(id,name,image);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category select(int id) {
        Category category = null;
        try {
            String select_by_id = "select * from category where id = ?";
            PreparedStatement statement = connection.prepareStatement(select_by_id);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String name =  rs.getString("name");
                String image = rs.getString("image");
                category = new Category(id,name,image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public boolean delete(int id) {
        boolean isDelete = false;
        try {
            String delete_query = "delete from category where id = ?";
            PreparedStatement statement = connection.prepareStatement(delete_query);
            statement.setInt(1,id);
            isDelete = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDelete;
    }

    @Override
    public boolean update(Category category) {
        boolean isUpdate = false;
        try {
            String update_query = "update category set name = ?, image = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(update_query);
            statement.setString(1,category.getName());
            statement.setString(2,category.getImage());
            statement.setInt( 3, category.getId());
            isUpdate =  statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdate;
    }

    @Override
    public void add(Category category) {
        try {
            String insert_into = "insert into category (name,image) values (?,?)";
            PreparedStatement statement = connection.prepareStatement(insert_into);
            statement.setString(1, category.getName());
            statement.setString(2, category.getImage());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Category> getByName(String name) {
        List<Category> categories = new ArrayList<>();
        try {
            String select_query = "select * from category where name like ?";
            PreparedStatement statement = connection.prepareStatement(select_query);
            statement.setString(1,name);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name2 = rs.getString("name");
                String image = rs.getString("image");
                Category category = new Category(id,name2,image);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
