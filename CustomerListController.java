package bd.edu.seu.nursery_management_system.controller;

import bd.edu.seu.nursery_management_system.NMS;
import bd.edu.seu.nursery_management_system.model.Cart;
import bd.edu.seu.nursery_management_system.model.Customer;
import bd.edu.seu.nursery_management_system.service.CartService;
import bd.edu.seu.nursery_management_system.service.CustomerService;
import bd.edu.seu.nursery_management_system.service.ProductService;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerListController implements Initializable {
    private final CustomerService customerService = new CustomerService();
    private final CartService cartService = new CartService();
    private final ProductService productService = new ProductService();

    @FXML
    private TableView<Customer> customerList;

    @FXML
    private TableColumn<Customer, String> customerCol;

    @FXML
    private TableColumn<Customer, String> phoneCol;

    @FXML
    private TableColumn<Customer, Number> quantityCol;

    @FXML
    private TableColumn<Customer, Number> tnxCol;

    @FXML
    void goDashboard() throws IOException {
        NMS.changeScene("admin-profile");
    }
    @FXML
    void goEmployeeList() throws IOException {
        NMS.changeScene("employee-list");
    }
    @FXML
    void goProductEntry() throws IOException {
        NMS.changeScene("product-entry");
    }

    @FXML
    void logout() throws IOException {
        NMS.logout();
    }

    public int getTotalQuantity(String username) throws SQLException {
        List<Cart> carts = cartService.findByCustomer(username);
        return carts.stream().mapToInt(c -> c.getQuantity()).sum();
    }

    public double getTotalCost(String username) throws SQLException {
        List<Cart> carts = cartService.getAllCartByCustomer(username);
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
        List<Customer> customers = new ArrayList<>();

        try {
            customers = customerService.getCustomerList();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObservableList<Customer> customerList = FXCollections.observableArrayList();

        customerCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        phoneCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPhone()));
        quantityCol.setCellValueFactory(cell -> {
            try {
                return new SimpleIntegerProperty(getTotalQuantity(cell.getValue().getUsername()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        tnxCol.setCellValueFactory(cell -> {
            try {
                return new SimpleDoubleProperty(getTotalCost(cell.getValue().getUsername()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });

        customerList.addAll(customers);
        this.customerList.setItems(customerList);
    }
}
