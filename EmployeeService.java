package bd.edu.seu.nursery_management_system.service;

import bd.edu.seu.nursery_management_system.model.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    public void addEmployee(Employee employee) throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "INSERT INTO employee VALUES ('" + employee.getName() + "', '" + employee.getUsername() + "', '" + employee.getPassword() + "', '" + employee.getPhone() + "', '" + employee.getEmail() + "')";
        statement.execute(query);
    }

    public Employee getEmployee(String username, String password) throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM employee WHERE username = '" + username + "' AND password = '" + password + "'";
        ResultSet resultSet = statement.executeQuery(query);
        Employee employee = null;
        if (resultSet.next()) {
            employee = new Employee(resultSet.getString("name"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("phone"), resultSet.getString("email"));
        }
        return employee;
    }

    public List<Employee> getEmployeeList() throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM employee";
        ResultSet resultSet = statement.executeQuery(query);
        List<Employee> employeeList = new ArrayList<>();
        while (resultSet.next()) {
            employeeList.add(new Employee(resultSet.getString("name"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("phone"), resultSet.getString("email")));
        }
        return employeeList;
    }

    public int countEmployee() throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT COUNT(*) FROM employee";
        ResultSet resultSet = statement.executeQuery(query);
        int count = 0;
        while (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        return count;
    }
}
