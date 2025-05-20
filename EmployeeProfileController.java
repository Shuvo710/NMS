package bd.edu.seu.nursery_management_system.controller;

import bd.edu.seu.nursery_management_system.NMS;
import bd.edu.seu.nursery_management_system.model.Cart;
import bd.edu.seu.nursery_management_system.service.CartService;
import bd.edu.seu.nursery_management_system.service.CustomerService;
import bd.edu.seu.nursery_management_system.service.ProductService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeProfileController implements Initializable {
    private final CustomerService customerService = new CustomerService();
    private final CartService cartService = new CartService();
    private final ProductService productService = new ProductService();

    @FXML
    private Label pendingProduct;

    @FXML
    private Label soldProduct;

    @FXML
    private Label totalCustomer;

    @FXML
    private Label username;

    @FXML
    public void goPendingList() throws IOException {
        NMS.changeScene("employee-pending");
    }

    @FXML
    public void goEmployeeProfile() throws IOException {
        NMS.changeScene("employee-profile");
    }

    @FXML
    public void logout() throws IOException {
        NMS.logout();
    }
    @FXML
    public void goCustomerList() throws IOException {
        NMS.changeScene("customer-list-employee");
    }

    public double totalSold(String employee) throws SQLException {
        List<Cart> carts = cartService.findByEmployee(employee);
        return carts.stream().mapToDouble(c -> {
            try {
                return c.getQuantity() * productService.getProduct(c.getProduct()).getRate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }).sum();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            totalCustomer.setText(String.valueOf(customerService.countCustomer()));
            pendingProduct.setText(String.valueOf(cartService.pending()));
            soldProduct.setText(String.valueOf(totalSold(NMS.loggedEmployee.getUsername())));
            username.setText(String.valueOf(NMS.loggedEmployee.getName()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
