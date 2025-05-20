module bd.edu.seu.nursery_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens bd.edu.seu.nursery_management_system to javafx.fxml;
    exports bd.edu.seu.nursery_management_system;
    exports bd.edu.seu.nursery_management_system.controller;
    opens bd.edu.seu.nursery_management_system.controller to javafx.fxml;
}