module edu.rico.tutorial {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics; // <-- AÑADIDO ESENCIAL en macIOS

    // Abre los paquetes de los controladores a FXML
    opens edu.rico.tutorial.controller to javafx.fxml;
    exports edu.rico.tutorial.controller;

    // Abre el paquete principal (donde está MainApplication)
    opens edu.rico.tutorial to javafx.fxml;
    exports edu.rico.tutorial;

    // Abre el paquete del modelo a FXML para que la TableView funcione
    opens edu.rico.tutorial.model to javafx.fxml; // <-- AÑADIDO ESENCIAL
    exports edu.rico.tutorial.model;
}