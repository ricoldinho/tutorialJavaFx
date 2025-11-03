package edu.rico.tutorial.controller;

import edu.rico.tutorial.dao.JugadorDAO;
import edu.rico.tutorial.dao.JugadorDAOImpl;
import edu.rico.tutorial.model.Jugador;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UsuariosController implements Initializable {

    // --- Componentes FXML ---
    @FXML private TextField tfBuscador;
    @FXML private TableView<Jugador> tablaBusquedaJugadores;
    @FXML private TableColumn<Jugador, String> colNombreBusqueda;
    @FXML private TableColumn<Jugador, String> colApellidoBusqueda;
    @FXML private TableColumn<Jugador, String> colApodoBusqueda;
    @FXML private Button btnAnadirFavorito;

    @FXML private TableView<Jugador> tablaJugadoresFavoritos;
    @FXML private TableColumn<Jugador, String> colNombreFavorito;
    @FXML private TableColumn<Jugador, String> colApodoFavorito;
    @FXML private Button btnEliminarFavorito;

    // --- DAO y Listas Observables ---
    private JugadorDAO jugadorDAO;
    private ObservableList<Jugador> listaBusquedaJugadores;
    private ObservableList<Jugador> listaJugadoresFavoritos;

    // --- Debounce ---
    private Timeline debounceTimeline;
    private static final int DEBOUNCE_DELAY_MS = 300;

    public UsuariosController() {
        this.jugadorDAO = new JugadorDAOImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 1. Inicializar listas
        listaBusquedaJugadores = FXCollections.observableArrayList();
        listaJugadoresFavoritos = FXCollections.observableArrayList();

        // 2. Configurar columnas de la tabla de búsqueda
        colNombreBusqueda.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoBusqueda.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colApodoBusqueda.setCellValueFactory(new PropertyValueFactory<>("apodo"));
        tablaBusquedaJugadores.setItems(listaBusquedaJugadores);

        // 3. Configurar columnas de la tabla de favoritos
        colNombreFavorito.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApodoFavorito.setCellValueFactory(new PropertyValueFactory<>("apodo"));
        tablaJugadoresFavoritos.setItems(listaJugadoresFavoritos);

        // 4. Configurar el Timeline para el "debouncing"
        debounceTimeline = new Timeline(new KeyFrame(Duration.millis(DEBOUNCE_DELAY_MS), event -> {
            buscarJugadores(tfBuscador.getText());
        }));
        debounceTimeline.setCycleCount(1); // Ejecutar solo una vez

        // 5. Configurar el listener para la búsqueda con debounce
        tfBuscador.textProperty().addListener((observable, oldValue, newValue) -> {
            debounceTimeline.stop(); // Detiene el temporizador anterior
            debounceTimeline.playFromStart(); // Inicia uno nuevo
        });
    }

    private void buscarJugadores(String terminoBusqueda) {
        listaBusquedaJugadores.clear();
        if (terminoBusqueda != null && !terminoBusqueda.trim().isEmpty()) {
            List<Jugador> jugadoresEncontrados = jugadorDAO.buscarJugadoresPorNombreOApodo(terminoBusqueda);
            listaBusquedaJugadores.addAll(jugadoresEncontrados);
        }
    }

    @FXML
    private void handleAnadirFavorito() {
        Jugador jugadorSeleccionado = tablaBusquedaJugadores.getSelectionModel().getSelectedItem();
        if (jugadorSeleccionado != null) {
            if (!listaJugadoresFavoritos.contains(jugadorSeleccionado)) {
                listaJugadoresFavoritos.add(jugadorSeleccionado);
            } else {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Jugador ya es favorito", "Este jugador ya está en tu lista de favoritos.");
            }
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Ningún Jugador Seleccionado", "Por favor, selecciona un jugador de la lista de búsqueda.");
        }
    }

    @FXML
    private void handleEliminarFavorito() {
        Jugador jugadorSeleccionado = tablaJugadoresFavoritos.getSelectionModel().getSelectedItem();
        if (jugadorSeleccionado != null) {
            listaJugadoresFavoritos.remove(jugadorSeleccionado);
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Ningún Favorito Seleccionado", "Por favor, selecciona un jugador de tu lista de favoritos.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}