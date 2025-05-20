package bd.edu.seu.nursery_management_system.service;

import bd.edu.seu.nursery_management_system.model.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    public void addCustomer(Customer customer) throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "INSERT INTO customer VALUES ('" + customer.getName() + "','" + customer.getUsername() + "','" + customer.getPassword() + "','" + customer.getPhone() + "','" + customer.getEmail() + "')";
        statement.execute(query);
    }

    public Customer getCustomer(String username, String password) throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM customer WHERE username = '" + username + "' AND password = '" + password + "'";
        ResultSet resultSet = statement.executeQuery(query);
        Customer customer = null;
        if (resultSet.next()) {
            customer = new Customer(resultSet.getString("name"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("phone"), resultSet.getString("email"));
        }
        return customer;
    }

    public List<Customer> getCustomerList() throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM customer";
        ResultSet resultSet = statement.executeQuery(query);
        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()) {
            customerList.add(new Customer(resultSet.getString("name"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("phone"), resultSet.getString("email")));
        }
        return customerList;
    }

    public boolean isExistCustomer(String username) throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM customer WHERE username = '" + username + "'";
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet.next();
    }

    public Customer getCustomerByUsername(String username) throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM customer WHERE username = '" + username + "'";
        ResultSet resultSet = statement.executeQuery(query);
        Customer customer = null;
        if (resultSet.next()) {
            customer = new Customer(resultSet.getString("name"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("phone"), resultSet.getString("email"));
        }
        return customer;
    }

    public int countCustomer() throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT COUNT(*) FROM customer";
        ResultSet resultSet = statement.executeQuery(query);
        int count = 0;
        while (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        return count;
    }
}
