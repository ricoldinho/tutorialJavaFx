package edu.rico.tutorial.tutorialclase;

import atlantafx.base.theme.PrimerDark;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Cargamos la estructura contenedora principal con menú y BorderPane
            URL fxmlLocation = getClass().getResource("/edu/rico/tutorial/tutorialclase/views/main-layout.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);

            // Garantizamos la compatibilidad ClassLoader con JPMS
            loader.setClassLoader(getClass().getClassLoader());

            Parent root = loader.load();
            Scene scene = new Scene(root, 1024, 768);

            // Aplicamos el tema visual AtlantaFX
            Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());

            primaryStage.setTitle("Sistema de Gestión Enterprise - Módulo 3");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            System.err.println("Error crítico al inicializar el contenedor principal:");
            e.printStackTrace();
        }
    }
}

