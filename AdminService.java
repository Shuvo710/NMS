package bd.edu.seu.nursery_management_system.service;

import bd.edu.seu.nursery_management_system.model.Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminService {
    public Admin getAdmin(String username, String password) throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM admin WHERE username = '" + username + "' AND password = '" + password + "'";
        ResultSet resultSet = statement.executeQuery(query);
        Admin admin = null;
        if (resultSet.next()) {
            admin = new Admin(resultSet.getString("name"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("phone"), resultSet.getString("email"));
        }
        return admin;
    }
}
