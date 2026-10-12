module edu.rico.tutorial.tutorialclase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires atlantafx.base;

    // Permitimos la inyección reflexiva de @FXML en los controladores
    opens edu.rico.tutorial.tutorialclase.controllers to javafx.fxml;

    // Abrimos los modelos para bindings reactivos en componentes futuros
    //opens edu.rico.tutorial.tutorialclase.model to javafx.base;

    // Exportamos el paquete raíz y el paquete de navegación
    exports edu.rico.tutorial.tutorialclase;
    exports edu.rico.tutorial.tutorialclase.navigation;
}