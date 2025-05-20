package bd.edu.seu.nursery_management_system;

import bd.edu.seu.nursery_management_system.model.Admin;
import bd.edu.seu.nursery_management_system.model.Customer;
import bd.edu.seu.nursery_management_system.model.Employee;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NMS extends Application {
    public static Stage primaryStage;
    public static Customer loggedCustomer;
    public static Employee loggedEmployee;
    public static Admin loggedAdmin;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(NMS.class.getResource("scene/customer-login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Nursery Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void changeScene(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NMS.class.getResource("scene/" + fxml + ".fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primaryStage.setTitle("Nursery Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void logout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NMS.class.getResource("scene/customer-login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primaryStage.setTitle("Nursery Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}