package bd.edu.seu.nursery_management_system.service;

import bd.edu.seu.nursery_management_system.model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public void addProduct(Product product) throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "INSERT INTO product VALUES ('" + product.getId() + "', '" + product.getName() + "', '" + product.getDescription() + "', '" + product.getRate() + "', '" + product.getPrice() + "')";
        statement.execute(query);
    }

    public Product getProduct(int id) throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM product WHERE id = '" + id + "'";
        ResultSet resultSet = statement.executeQuery(query);
        Product product = null;
        if (resultSet.next()) {
            product = new Product(Integer.parseInt(resultSet.getString("id")), resultSet.getString("name"), resultSet.getString("description"), Double.parseDouble(resultSet.getString("rate")), Double.parseDouble(resultSet.getString("price")));
        }
        return product;
    }

    public List<Product> getProductList() throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM product";
        ResultSet resultSet = statement.executeQuery(query);
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            products.add(new Product(Integer.parseInt(resultSet.getString("id")), resultSet.getString("name"), resultSet.getString("description"), Double.parseDouble(resultSet.getString("rate")), Double.parseDouble(resultSet.getString("price"))));
        }
        return products;
    }
}
