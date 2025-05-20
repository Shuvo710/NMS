package bd.edu.seu.nursery_management_system.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {
    private final String DB_URL = "jdbc:mysql://localhost/nms";
    private final String USER = "shuvo";
    private final String PASSWORD = "shuvo@710";
    private static Connection connection;
    private static final ConnectionSingleton connectionSingleton = new ConnectionSingleton();

    private ConnectionSingleton() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
