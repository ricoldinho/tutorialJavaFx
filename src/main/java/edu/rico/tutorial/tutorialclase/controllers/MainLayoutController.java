package edu.rico.tutorial.tutorialclase.controllers;

import edu.rico.tutorial.tutorialclase.navigation.NavigationManager;
import edu.rico.tutorial.tutorialclase.navigation.ViewType;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class MainLayoutController {

    @FXML private BorderPane rootContainer;
    @FXML private Label lblHeaderTitle;
    @FXML private Button btnNavPerfil;
    @FXML private Button btnNavDashboard;
    @FXML private Button btnNavEstadisticas;

    private NavigationManager navManager;

    @FXML
    public void initialize() {
        // 1. Vinculamos el contenedor al coordinador de navegación Singleton
        navManager = NavigationManager.getInstance();
        navManager.initializeContainer(rootContainer);

        // =========================================================================
        // 2. BINDING REACTIVO DE TÍTULO DE ENCABEZADO
        // =========================================================================
        // Cuando la vista activa cambia en el NavigationManager, el texto de la etiqueta
        // se actualiza automáticamente con el título definido en el Enum ViewType.
        lblHeaderTitle.textProperty().bind(
                Bindings.createStringBinding(
                        () -> navManager.activeViewProperty().get() != null ?
                                navManager.activeViewProperty().get().getTitle() : "Cargando...",
                        navManager.activeViewProperty()
                )
        );

        // =========================================================================
        // 3. DESHABILITACIÓN REACTIVA DE BOTONES NAVEGACIONALES
        // =========================================================================
        // Regla UX: Si ya estamos en la vista 'PERFIL', deshabilitamos el botón de 'Perfil'
        // automáticamente para evitar recargas innecesarias y dar feedback visual al usuario.
        btnNavPerfil.disableProperty().bind(
                navManager.activeViewProperty().isEqualTo(ViewType.PERFIL)
        );

        btnNavDashboard.disableProperty().bind(
                navManager.activeViewProperty().isEqualTo(ViewType.DASHBOARD)
        );

        btnNavEstadisticas.disableProperty().bind(
                navManager.activeViewProperty().isEqualTo(ViewType.ESTADISTICAS)
        );

        // =========================================================================
        // 4. ASIGNACIÓN DE ACCIONES DE NAVEGACIÓN
        // =========================================================================
        btnNavPerfil.setOnAction(e -> navManager.navigateTo(ViewType.PERFIL));
        btnNavDashboard.setOnAction(e -> navManager.navigateTo(ViewType.DASHBOARD));
        btnNavEstadisticas.setOnAction(e -> navManager.navigateTo(ViewType.ESTADISTICAS));

        // 5. Carga inicial por defecto
        navManager.navigateTo(ViewType.PERFIL);
    }

    @FXML
    private void onNavPerfil() {
        navManager.navigateTo(ViewType.PERFIL);
    }

    @FXML
    private void onNavDashboard() {
        navManager.navigateTo(ViewType.DASHBOARD);
    }

    @FXML
    private void onNavEstadisticas() {
        navManager.navigateTo(ViewType.ESTADISTICAS);
    }

    @FXML
    private void onExitApp() {
        Platform.exit(); // Cierre limpio del hilo gráfico JavaFX
    }

    @FXML
    private void onShowAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de");
        alert.setHeaderText("Sistema de Gestión Empresarial - JavaFX 2026");
        alert.setContentText("Desarrollado bajo arquitectura modular JPMS, FXML y programación reactiva pura.");
        alert.showAndWait();
    }
}