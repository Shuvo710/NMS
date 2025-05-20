package bd.edu.seu.nursery_management_system.controller;

import bd.edu.seu.nursery_management_system.NMS;
import bd.edu.seu.nursery_management_system.model.Cart;
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

public class EmployeePendingController implements Initializable {
    private final CustomerService customerService = new CustomerService();
    private final CartService cartService = new CartService();
    private final ProductService productService = new ProductService();

    @FXML
    private TableColumn<Cart, String> customer;

    @FXML
    private TableView<Cart> pendingList;

    @FXML
    private TableColumn<Cart, Number> price;

    @FXML
    private TableColumn<Cart, Number> id;

    @FXML
    private TableColumn<Cart, String> product;

    @FXML
    private TableColumn<Cart, Number> quantity;

    @FXML
    void goDashboard() throws IOException{
        NMS.changeScene("employee-profile");
    }

    /*@FXML
    void goCustomerList() throws IOException{
        NMS.changeScene("customer-list");
    }*/

    @FXML
    void approve() throws SQLException {
        Cart cart = pendingList.getSelectionModel().getSelectedItem();

        if (cart != null) {
            cartService.approve(NMS.loggedEmployee.getUsername(), cart.getId());
            pendingList();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Sold Successfully");
            alert.showAndWait();
        }
    }

    public void pendingList(){
        List<Cart> carts = new ArrayList<>();

        try {
            carts = cartService.findAll().stream().filter(cart -> cart.getIsApproved() == 0).toList();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObservableList<Cart> cartObservableList = FXCollections.observableArrayList();

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

        customer.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCustomer()));

        cartObservableList.addAll(carts);
        pendingList.setItems(cartObservableList);

    }

    @FXML
    public void logout() throws IOException {
        NMS.logout();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pendingList();
    }
}
