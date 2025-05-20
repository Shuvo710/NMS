package bd.edu.seu.nursery_management_system.controller;

import bd.edu.seu.nursery_management_system.NMS;
import bd.edu.seu.nursery_management_system.model.Cart;
import bd.edu.seu.nursery_management_system.model.Product;
import bd.edu.seu.nursery_management_system.service.CartService;
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

public class CustomerProfileController implements Initializable {
    private final ProductService productService = new ProductService();
    private final CartService cartService = new CartService();

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, Number> productId;

    @FXML
    private TableColumn<Product, String> productName;

    @FXML
    private TableColumn<Product, Number> productPrice;

    @FXML
    private TableColumn<Cart, String> cName;

    @FXML
    private TableColumn<Cart, Number> cQuantity;

    @FXML
    private TableView<Cart> cart;

    @FXML
    private TextField quantity;

    @FXML
    private Label totalPayable;

    @FXML
    private Label change;

    @FXML
    private TextField pay;

    @FXML
    public void addToCart() throws SQLException {
        Product product = productTable.getSelectionModel().getSelectedItem();
        int quantity = Integer.parseInt(this.quantity.getText());

        if (product != null) {
            if (cartService.isInCart(NMS.loggedCustomer.getUsername(), product.getId())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Product already exists in your cart!");
                alert.showAndWait();
                return;
            }
            cartService.addToCart(new Cart((int) (Math.random() * 100000), NMS.loggedCustomer.getUsername(), product.getId(), quantity, 0, 0, ""));
            cartList();
            this.quantity.setText("1");
            totalPayable.setText(String.valueOf(getTotalPayable()));
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No product selected!");
            alert.showAndWait();
        }
    }

    @FXML
    public void logout() throws IOException {
        NMS.logout();
    }

    public void productList() throws SQLException {
        List<Product> products = new ArrayList<>();
        try {
            products = productService.getProductList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ObservableList<Product> productList = FXCollections.observableArrayList();

        productId.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getId()));
        productName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        productPrice.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getPrice()));

        productList.addAll(products);
        productTable.setItems(productList);
    }

    public void cartList() throws SQLException {
        List<Cart> carts = new ArrayList<>();

        try {
            carts = cartService.getAllCartByCustomer(NMS.loggedCustomer.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }

        carts = carts.stream().filter(c -> c.getIsConfirmed() == 0).toList();

        ObservableList<Cart> cartList = FXCollections.observableArrayList();
        cName.setCellValueFactory(cell -> {
            try {
                return new SimpleStringProperty(productService.getProduct(cell.getValue().getProduct()).getName());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        cQuantity.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getQuantity()));
        cartList.addAll(carts);
        cart.setItems(cartList);
    }

    @FXML
    public void change() throws SQLException {
        double totalPayable = getTotalPayable();
        double pay = Double.parseDouble(this.pay.getText());
        this.change.setText(String.valueOf(pay - totalPayable));
    }

    @FXML
    public void confirm() throws SQLException {
        if (Double.parseDouble(this.change.getText()) < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter more amount!");
            alert.showAndWait();
            return;
        }
        cartService.confirmOrder(NMS.loggedCustomer.getUsername());
        cartList();
    }

    public double getTotalPayable() throws SQLException {
        double totalPayable = 0;
        List<Cart> allProInCart = cartService.getAllCartByCustomer(NMS.loggedCustomer.getUsername());
        for (Cart cart : allProInCart) {
            if (cart.getIsConfirmed() == 0) {
                totalPayable += productService.getProduct(cart.getProduct()).getPrice() * cart.getQuantity();
            }
        }
        return totalPayable;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cartList();
            productList();
            totalPayable.setText(String.valueOf(getTotalPayable()));
            change.setText(String.valueOf(getTotalPayable()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
