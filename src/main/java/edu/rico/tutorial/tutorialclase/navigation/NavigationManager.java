package edu.rico.tutorial.tutorialclase.navigation;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.net.URL;

public class NavigationManager {

    private static NavigationManager instance;
    private BorderPane mainContainer;

    // Propiedad reactiva que expone la vista activa actual en tiempo real
    private final ReadOnlyObjectWrapper<ViewType> activeView = new ReadOnlyObjectWrapper<>();

    private NavigationManager() {
        // Constructor privado para implementar el patrón Singleton
    }

    public static synchronized NavigationManager getInstance() {
        if (instance == null) {
            instance = new NavigationManager();
        }
        return instance;
    }

    public void initializeContainer(BorderPane container) {
        this.mainContainer = container;
    }

    /**
     * Realiza el cambio de vista dinámico en la región central del BorderPane.
     */
    public void navigateTo(ViewType viewType) {
        if (mainContainer == null) {
            throw new IllegalStateException("El contenedor principal BorderPane no ha sido inicializado.");
        }

        try {
            URL fxmlLocation = getClass().getResource(viewType.getFxmlPath());
            if (fxmlLocation == null) {
                throw new IllegalArgumentException("No se encontró el recurso FXML: " + viewType.getFxmlPath());
            }

            FXMLLoader loader = new FXMLLoader(fxmlLocation);

            // DECISIÓN ARQUITECTÓNICA JPMS: Alineamos el ClassLoader con nuestro módulo empresarial
            loader.setClassLoader(getClass().getClassLoader());

            Parent view = loader.load(); // Deserialización polimórfica del nodo raíz

            // Reemplazamos dinámicamente el contenido central
            mainContainer.setCenter(view);

            // Emitimos el cambio reactivo a todos los subscriptores
            activeView.set(viewType);

        } catch (IOException e) {
            System.err.println("Error crítico al cargar la vista FXML: " + viewType.getFxmlPath());
            e.printStackTrace();
        }
    }

    // Exponemos exclusivamente la propiedad de solo lectura para evitar modificaciones externas no autorizadas
    public ReadOnlyObjectProperty<ViewType> activeViewProperty() {
        return activeView.getReadOnlyProperty();
    }

    public ViewType getActiveView() {
        return activeView.get();
    }
}