package bd.edu.seu.nursery_management_system.controller;

import bd.edu.seu.nursery_management_system.NMS;
import bd.edu.seu.nursery_management_system.model.Cart;
import bd.edu.seu.nursery_management_system.service.CartService;
import bd.edu.seu.nursery_management_system.service.CustomerService;
import bd.edu.seu.nursery_management_system.service.EmployeeService;
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

public class AdminProfileController implements Initializable {
    private final CustomerService customerService = new CustomerService();
    private final CartService cartService = new CartService();
    private final EmployeeService employeeService =  new EmployeeService();
    private final ProductService productService = new ProductService();

    @FXML
    private TableColumn<Cart, Number> id;

    @FXML
    private TableColumn<Cart, Number> price;

    @FXML
    private TableColumn<Cart, String> product;

    @FXML
    private TableColumn<Cart, Number> quantity;

    @FXML
    private TableColumn<Cart, String> status;

    @FXML
    private TableView<Cart> soldList;

    @FXML
    private Label totalCustomer;

    @FXML
    private Label totalEmployee;

    @FXML
    private Label totalIncome;

    @FXML
    private Label username;


    @FXML
    public void goProductEntry() throws IOException {
        NMS.changeScene("product-entry");
    }

    @FXML
    public void goCustomerList() throws IOException {
        NMS.changeScene("customer-list");
    }

    @FXML
    public void logout() throws IOException {
        NMS.logout();
    }

    @FXML
    public void goEmployeeList() throws IOException {
        NMS.changeScene("employee-list");
    }

    public void cartList(){
        List<Cart> carts = new ArrayList<>();

        try {
            carts = cartService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ObservableList<Cart> cartList = FXCollections.observableArrayList();

        id.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getId()));
        product.setCellValueFactory(cell -> {
            try {
                return new SimpleStringProperty(productService.getProduct(cell.getValue().getProduct()).getName());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        quantity.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getQuantity()));
        price.setCellValueFactory(cell -> {
            try {
                return new SimpleDoubleProperty(cell.getValue().getQuantity() * productService.getProduct(cell.getValue().getProduct()).getRate());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        status.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getIsApproved() == 1 ? "Sold" : "Pending"));

        cartList.addAll(carts);
        soldList.setItems(cartList);
    }

    public double getTotalIncome() throws SQLException {
        List<Cart> carts = new ArrayList<>();

        try {
            carts = cartService.findAll().stream().filter(c -> c.getIsApproved() == 1).toList();
        }catch (Exception e){
            e.printStackTrace();
        }

        double totalSold = 0;
        double totalPrice = 0;
        for (Cart cart : carts) {
            totalSold += cart.getQuantity() * productService.getProduct(cart.getProduct()).getRate();
            totalPrice += cart.getQuantity() * productService.getProduct(cart.getProduct()).getPrice();
        }
        return totalSold - totalPrice;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            totalCustomer.setText(String.valueOf(customerService.countCustomer()));
            totalEmployee.setText(String.valueOf(employeeService.countEmployee()));
            totalIncome.setText(String.valueOf(String.valueOf(getTotalIncome())));
            username.setText(String.valueOf(NMS.loggedAdmin.getName()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cartList();
    }
}
