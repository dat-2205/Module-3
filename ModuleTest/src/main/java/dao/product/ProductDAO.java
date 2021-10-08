package dao.product;

import config.DBConnection;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO{
    private Connection connection = DBConnection.getConnection();
    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try {
            String select_all_query = "select * from product";
            PreparedStatement statement = connection.prepareStatement(select_all_query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                Product product = getProductInformation(id,rs);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product select(int id) {
        Product product = null;
        try {
            String select_by_id = "select * from product where id = ?";
            PreparedStatement statement = connection.prepareStatement(select_by_id);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                product = getProductInformation(id, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    private Product getProductInformation(int id, ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        double price = rs.getDouble("price");
        int  quantity = rs.getInt("quantity");
        String color = rs.getString("color");
        String description = rs.getString("description");
        int category_id = rs.getInt("category_id");
        Product product = new Product(id,name,price,quantity,color,description,category_id);
        return product;
    }

    @Override
    public boolean delete(int id) {
        boolean isDelete = false;
        try {
            String delete_query = "delete from product where id = ?";
            PreparedStatement statement = connection.prepareStatement(delete_query);
            statement.setInt(1,id);
            isDelete = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDelete;
    }

    @Override
    public boolean update(Product product) {
        boolean isUpdate = false;
        try {
            String update_query = "update product set name = ?, price = ?, quantity = ?, color = ?, description = ?, category_id = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(update_query);
            statement.setString(1,product.getName());
            statement.setDouble(2,product.getPrice());
            statement.setInt( 3,product.getQuantity());
            statement.setString(4,product.getColor());
            statement.setString(5,product.getDescription());
            statement.setInt(6,product.getCategory_id());
            statement.setInt(7,product.getId());
            isUpdate =  statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdate;
    }

    @Override
    public void add(Product product) {
        try {
            String insert_into = "insert into product (name,price,quantity,color,description,category_id) values (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(insert_into);
            statement.setString(1, product.getName());
            statement.setDouble(2, product. getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setString(4, product.getColor());
            statement.setString(5, product.getDescription());
            statement.setInt(6, product.getCategory_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getByName(String name) {
        List<Product> products = new ArrayList<>();
        try {
            String select_query = "select * from product where name like ?";
            PreparedStatement statement = connection.prepareStatement(select_query);
            statement.setString(1,name);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                Product product = getProductInformation(id,rs);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

}
