package bd.edu.seu.nursery_management_system.controller;

import bd.edu.seu.nursery_management_system.NMS;
import bd.edu.seu.nursery_management_system.model.Customer;
import bd.edu.seu.nursery_management_system.service.CustomerService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CustomerLoginController {
    private final CustomerService customerService = new CustomerService();

    @FXML
    public TextField username;

    @FXML
    public PasswordField password;

    @FXML
    public void goEmpLogin() {
        try {
            NMS.changeScene("employee-login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void login() throws IOException {
        String username = this.username.getText();
        String password = this.password.getText();

        Customer customer = null;
        try {
            customer = customerService.getCustomer(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (customer != null) {
            NMS.loggedCustomer = customer;
            NMS.changeScene("customer-profile");
        }
    }

    @FXML
    public void customerRegistration() throws IOException {
        NMS.changeScene("customer-registration");
    }
}
