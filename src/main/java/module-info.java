module edu.rico.tutorial {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires org.mariadb.jdbc;

    opens edu.rico.tutorial.controller to javafx.fxml;
    exports edu.rico.tutorial.controller;

    opens edu.rico.tutorial to javafx.fxml;
    exports edu.rico.tutorial;

    opens edu.rico.tutorial.model to javafx.fxml;
    exports edu.rico.tutorial.model;

    opens edu.rico.tutorial.db to javafx.fxml;
    exports edu.rico.tutorial.db;
}