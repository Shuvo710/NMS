package bd.edu.seu.nursery_management_system.controller;

import bd.edu.seu.nursery_management_system.NMS;
import bd.edu.seu.nursery_management_system.model.Customer;
import bd.edu.seu.nursery_management_system.service.CustomerService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerRegistrationController {
    private final CustomerService customerService = new CustomerService();

    @FXML
    private PasswordField email;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField phone;

    @FXML
    private PasswordField rePassword;

    @FXML
    private TextField username;

    @FXML
    void signup() throws SQLException {
        String name = this.name.getText();
        String phone = this.phone.getText();
        String email = this.email.getText();
        String username = this.username.getText();
        String password = this.password.getText();
        String rePassword = this.rePassword.getText();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
            return;
        }

        if (!password.equals(rePassword)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Passwords do not match");
            alert.showAndWait();
            return;
        }

        if (!customerService.isExistCustomer(username)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Customer already exists");
            alert.showAndWait();
            return;
        }

        customerService.addCustomer(new Customer(name, username, password, phone, email));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Customer Registration");
        alert.setHeaderText(null);
        alert.setContentText("Customer Registration Successful");
        alert.showAndWait();
    }

    @FXML
    public void goCustomerSignin() throws IOException {
        NMS.changeScene("customer-login");
    }
}
