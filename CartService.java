package bd.edu.seu.nursery_management_system.service;

import bd.edu.seu.nursery_management_system.model.Cart;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CartService {
    public void addToCart(Cart cart) throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "INSERT INTO cart VALUES ('" + cart.getId() + "', '" + cart.getCustomer() + "', '" + cart.getProduct() + "', '" + cart.getQuantity() + "', '" + cart.getIsConfirmed() + "', '" + cart.getIsApproved() + "', '" + cart.getEmployee() + "')";
        statement.execute(query);
    }

    public List<Cart> getAllCartByCustomer(String customer) throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM cart WHERE customer = '" + customer + "'";
        ResultSet resultSet = statement.executeQuery(query);
        List<Cart> cartList = new ArrayList<>();
        while (resultSet.next()) {
            cartList.add(new Cart(Integer.parseInt(resultSet.getString("id")), resultSet.getString("customer"), Integer.parseInt(resultSet.getString("product")), Integer.parseInt(resultSet.getString("quantity")), Integer.parseInt(resultSet.getString("isConfirmed")), Integer.parseInt(resultSet.getString("isApproved")), resultSet.getString("employee")));
        }
        return cartList;
    }

    public boolean isInCart(String customer, int product) throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM cart WHERE customer = '" + customer + "' AND product = '" + product + "'";
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet.next();
    }

    public void confirmOrder(String customer) throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "UPDATE cart SET isConfirmed = 1 WHERE customer = '" + customer + "'";
        statement.execute(query);
    }

    public int pending() throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT COUNT(*) FROM cart WHERE isApproved = 0 AND isConfirmed = 1";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        return resultSet.getInt(1);
    }

    public List<Cart> findAll() throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM cart";
        ResultSet resultSet = statement.executeQuery(query);
        List<Cart> cartList = new ArrayList<>();
        while (resultSet.next()) {
            cartList.add(new Cart(Integer.parseInt(resultSet.getString("id")), resultSet.getString("customer"), Integer.parseInt(resultSet.getString("product")), Integer.parseInt(resultSet.getString("quantity")), Integer.parseInt(resultSet.getString("isConfirmed")), Integer.parseInt(resultSet.getString("isApproved")), resultSet.getString("employee")));
        }
        return cartList;
    }

    public void approve(String employee, int id) throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "UPDATE cart SET isApproved = 1, employee = '" + employee + "' WHERE id = " + id;
        statement.execute(query);
    }

    public List<Cart> findByCustomer(String customer) throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM cart WHERE customer = '" + customer + "'";
        ResultSet resultSet = statement.executeQuery(query);
        List<Cart> cartList = new ArrayList<>();
        while (resultSet.next()) {
            cartList.add(new Cart(Integer.parseInt(resultSet.getString("id")), resultSet.getString("customer"), Integer.parseInt(resultSet.getString("product")), Integer.parseInt(resultSet.getString("quantity")), Integer.parseInt(resultSet.getString("isConfirmed")), Integer.parseInt(resultSet.getString("isApproved")), resultSet.getString("employee")));
        }
        return cartList;
    }

    public List<Cart> findByEmployee(String employee) throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM cart WHERE employee = '" + employee + "'";
        ResultSet resultSet = statement.executeQuery(query);
        List<Cart> cartList = new ArrayList<>();
        while (resultSet.next()) {
            cartList.add(new Cart(Integer.parseInt(resultSet.getString("id")), resultSet.getString("customer"), Integer.parseInt(resultSet.getString("product")), Integer.parseInt(resultSet.getString("quantity")), Integer.parseInt(resultSet.getString("isConfirmed")), Integer.parseInt(resultSet.getString("isApproved")), resultSet.getString("employee")));
        }
        return cartList;
    }
}
