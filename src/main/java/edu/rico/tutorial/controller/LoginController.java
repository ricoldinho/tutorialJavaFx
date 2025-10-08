package edu.rico.tutorial.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController implements Initializable {

    // 1. Inyección de componentes: JavaFX asigna aquí los elementos del FXML.
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    // 2. Este método se llama cuando se hace clic en el botón de login.
    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // 3. Lógica de autenticación simple.
        if (username.equals("usuario") && password.equals("1234")) {
            // Si el login es correcto, cambiamos de escena.
            // Obtenemos una referencia al Stage (la ventana principal)
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Cargamos el FXML de la nueva vista
            Parent root = FXMLLoader.load(getClass().getResource("jugadores-view.fxml"));

            // Creamos una nueva escena con la vista de bienvenida
            Scene scene = new Scene(root);

            // Reemplazamos la escena actual por la nueva en el mismo Stage
            stage.setScene(scene);
            stage.show();

        } else {
            // 4. Si el login es incorrecto, mostramos una alerta.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Acceso");
            alert.setHeaderText("Credenciales incorrectas");
            alert.setContentText("Por favor, verifica tu usuario y contraseña.");
            alert.showAndWait();
        }
    }
    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        // Este código se ejecuta ANTES de que la ventana sea visible.
        System.out.println("La vista de login está lista. Poniendo el foco en el campo de usuario.");

        // Ponemos el foco en el campo de usuario para que se pueda escribir directamente.
        // Platform.runLater() asegura que esta acción se ejecute en el hilo de la UI de JavaFX
        // de forma segura, una vez que el layout esté completamente calculado.
        Platform.runLater(() -> usernameField.requestFocus());
    }
}