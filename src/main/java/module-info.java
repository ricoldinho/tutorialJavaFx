module edu.rico.tutorial {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.rico.tutorial to javafx.fxml;
    exports edu.rico.tutorial;
}