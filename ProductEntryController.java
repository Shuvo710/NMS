package bd.edu.seu.nursery_management_system.controller;

import bd.edu.seu.nursery_management_system.NMS;
import bd.edu.seu.nursery_management_system.model.Product;
import bd.edu.seu.nursery_management_system.service.ProductService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;

public class ProductEntryController {
    private final ProductService productService = new ProductService();

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField price;

    @FXML
    private TextField rate;

    @FXML
    private TextField type;

    @FXML
    void goDashboard() throws IOException {
        NMS.changeScene("admin-profile");
    }
    @FXML
    void goEmployeeList() throws IOException {
        NMS.changeScene("employee-list");
    }
    @FXML
    void goCustomerList() throws IOException {
        NMS.changeScene("customer-list");
    }
    @FXML
    void goProductEntry() throws IOException {
        NMS.changeScene("product-entry");
    }

    @FXML
    public void logout() throws IOException {
        NMS.logout();
    }

    @FXML
    void save() throws SQLException {
        int id = this.id.getText() == null ? 0 : Integer.parseInt(this.id.getText());
        String name = this.name.getText();
        String type = this.type.getText();
        double price = this.price.getText() == null ? 0.0 : Double.parseDouble(this.price.getText());
        double rate = this.rate.getText() == null ? 0.0 : Double.parseDouble(this.rate.getText());

        if (id == 0 || name == null || type == null || price == 0.0 || rate == 0.0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
            return;
        }

        productService.addProduct(new Product(id, name, type, price, rate));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Product added successfully");
        alert.showAndWait();
    }
}
