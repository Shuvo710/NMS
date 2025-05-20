package bd.edu.seu.nursery_management_system.controller;

import bd.edu.seu.nursery_management_system.NMS;
import bd.edu.seu.nursery_management_system.model.Cart;
import bd.edu.seu.nursery_management_system.model.Employee;
import bd.edu.seu.nursery_management_system.service.CartService;
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

public class EmployeeListController implements Initializable {
    private final EmployeeService employeeService = new EmployeeService();
    private final CartService cartService = new CartService();
    private final ProductService productService = new ProductService();

    @FXML
    private TableColumn<Employee, String> employeeCol;

    @FXML
    private TableView<Employee> employeeList;

    @FXML
    private TableColumn<Employee, String> phoneCol;

    @FXML
    private TableColumn<Employee, Number> quantityCol;

    @FXML
    private TableColumn<Employee, Number> sellCol;

    @FXML
    void goProductEntry() throws IOException {
        NMS.changeScene("product-entry");
    }

    @FXML
    void goDashboard() throws IOException {
        NMS.changeScene("admin-profile");
    }

    @FXML
    void goCustomerList() throws IOException {
        NMS.changeScene("customer-list");
    }

    @FXML
    void logout() throws IOException {
        NMS.logout();
    }

    public int getTotalQuantity(String employee) throws SQLException {
        List<Cart> carts = cartService.findByEmployee(employee);
        return carts.stream().mapToInt(Cart::getQuantity).sum();
    }

    public double getTotalSell(String employee) throws SQLException {
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
        List<Employee> employees = new ArrayList<>();

        try {
            employees = employeeService.getEmployeeList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ObservableList<Employee> employeeList = FXCollections.observableArrayList();

        employeeCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        phoneCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPhone()));
        quantityCol.setCellValueFactory(cell -> {
            try {
                return new SimpleIntegerProperty(getTotalQuantity(cell.getValue().getUsername()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        sellCol.setCellValueFactory(cell -> {
            try {
                return new SimpleDoubleProperty(getTotalSell(cell.getValue().getUsername()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });

        employeeList.addAll(employees);
        this.employeeList.setItems(employeeList);
    }
}
