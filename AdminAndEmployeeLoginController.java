package bd.edu.seu.nursery_management_system.controller;

import bd.edu.seu.nursery_management_system.NMS;
import bd.edu.seu.nursery_management_system.model.Admin;
import bd.edu.seu.nursery_management_system.model.Employee;
import bd.edu.seu.nursery_management_system.service.AdminService;
import bd.edu.seu.nursery_management_system.service.EmployeeService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminAndEmployeeLoginController implements Initializable {
    private final AdminService adminService = new AdminService();
    private final EmployeeService employeeService = new EmployeeService();

    @FXML
    private AnchorPane admShade;

    @FXML
    private AnchorPane empShade;

    @FXML
    private TextField AdmUsername;

    @FXML
    private PasswordField AdmPassword;

    @FXML
    private TextField EmpUsername;

    @FXML
    private PasswordField EmpPassword;

    @FXML
    private Label title;

    @FXML
    void showAdmin() {
        admShade.setVisible(false);
        empShade.setVisible(true);
        title.setText("Admin Sign in");
    }

    @FXML
    void showEmployee() {
        empShade.setVisible(false);
        admShade.setVisible(true);
        title.setText("Employee Sign in");
    }

    @FXML
    public void goCustomerLogin() {
        try {
            NMS.changeScene("customer-login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void admLogin() throws IOException {
        String username = this.AdmUsername.getText();
        String password = this.AdmPassword.getText();

        Admin admin = null;
        try {
            admin = adminService.getAdmin(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (admin != null) {
            NMS.loggedAdmin = admin;
            NMS.changeScene("admin-profile");
        }
    }

    @FXML
    public void empLogin() throws SQLException, IOException {
        String username = this.EmpUsername.getText();
        String password = this.EmpPassword.getText();

        Employee employee = null;
        try {
            employee = employeeService.getEmployee(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (employee != null) {
            NMS.loggedEmployee = employee;
            NMS.changeScene("employee-profile");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        empShade.setVisible(false);
    }
}
