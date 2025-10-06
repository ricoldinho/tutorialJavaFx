package edu.rico.tutorial;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // 1. Creamos el FXMLLoader para cargar nuestro archivo FXML.
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));

        // 2. Creamos la Scene con el contenido del FXML y definimos su tamaño.
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        // 3. Configuramos el Stage (la ventana).
        stage.setTitle("Inicio de Sesión");
        stage.setScene(scene); // Le asignamos la escena.
        stage.show(); // ¡Lo mostramos al público!
    }

    public static void main(String[] args) {
        launch(args);
    }
}
