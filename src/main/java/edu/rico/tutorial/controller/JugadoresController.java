package edu.rico.tutorial.controller;

import edu.rico.tutorial.dao.JugadorDAO;
import edu.rico.tutorial.dao.JugadorDAOImpl;
import edu.rico.tutorial.model.Jugador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class JugadoresController implements Initializable {

    @FXML private TableView<Jugador> tablaJugadores;
    @FXML private TableColumn<Jugador, Integer> colId;
    @FXML private TableColumn<Jugador, String> colNombre;
    @FXML private TableColumn<Jugador, String> colApellido;
    @FXML private TableColumn<Jugador, String> colApodo;
    @FXML private TableColumn<Jugador, LocalDate> colFechaNacimiento;
    @FXML private TableColumn<Jugador, String> colTipoJuego;

    // ¡NUEVO! Conectar el Label del FXML al controlador
    @FXML private Label lblJugadorId;
    @FXML private TextField tfNombre;
    @FXML private TextField tfApellido;
    @FXML private TextField tfApodo;
    @FXML private DatePicker dpFechaNacimiento;
    @FXML private ComboBox<String> cbTipoJuego;

    @FXML private Button btnAnadir;
    @FXML private Button btnModificar;
    @FXML private Button btnEliminar;

    // ¡NUEVO! Referencia a nuestro DAO
    private JugadorDAO jugadorDAO;

    private ObservableList<Jugador> listaJugadores;

    // AÑADIDO: Mantiene una referencia al jugador seleccionado
    private Jugador jugadorSeleccionado;

    public JugadoresController() {
        // Inicializamos la implementación del DAO
        this.jugadorDAO = new JugadorDAOImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaJugadores = FXCollections.observableArrayList();

        // ¡NUEVO! Configurar también la columna del ID
        this.colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        this.colApodo.setCellValueFactory(new PropertyValueFactory<>("apodo"));
        this.colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        this.colTipoJuego.setCellValueFactory(new PropertyValueFactory<>("tipoJuego"));

        // Rellenar ComboBox (se mantiene igual)
        cbTipoJuego.setItems(FXCollections.observableArrayList("Farolero", "Agresivo", "Conservador", "Roca"));

        tablaJugadores.setItems(listaJugadores);

        // ¡MODIFICADO! Cargamos los datos desde la BD
        cargarJugadoresDeLaBaseDeDatos();


        // GESTIÓN DE LA SELECCIÓN EN LA TABLA ---
        // Añadimos un listener para saber qué fila se está seleccionando
        tablaJugadores.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        jugadorSeleccionado = newValue;
                        rellenarFormulario(jugadorSeleccionado);
                    }
                }
        );
    }

    private void cargarJugadoresDeLaBaseDeDatos() {
        listaJugadores.clear(); // Limpiamos la lista por si había datos
        List<Jugador> jugadoresDeLaBD = jugadorDAO.getAllJugadores();
        listaJugadores.addAll(jugadoresDeLaBD);
    }


    @FXML
    private void handleAnadir() {
        // Validamos que los campos no estén vacíos
        if (camposSonValidos()) {
            Jugador nuevo = new Jugador(tfNombre.getText(), tfApellido.getText(), tfApodo.getText(),
                    dpFechaNacimiento.getValue(), cbTipoJuego.getValue());
            jugadorDAO.addJugador(nuevo); // <-- LLAMADA AL DAO
            cargarJugadoresDeLaBaseDeDatos(); // <-- Refrescamos la tabla
            handleLimpiar();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Jugador Añadido", "El nuevo jugador ha sido añadido a la lista.");
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Datos Incompletos", "Por favor, rellena todos los campos del formulario.");
        }
    }

    @FXML
    private void handleModificar() {
        if (jugadorSeleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Ningún Jugador Seleccionado", "Por favor, selecciona " +
                    "un jugador de la tabla para modificar.");
            return;
        }
        if (camposSonValidos()) {
            // Actualizamos los datos del objeto Jugador ya existente
            jugadorSeleccionado.setNombre(tfNombre.getText());
            jugadorSeleccionado.setApellido(tfApellido.getText());
            jugadorSeleccionado.setApodo(tfApodo.getText());
            jugadorSeleccionado.setFechaNacimiento(dpFechaNacimiento.getValue());
            jugadorSeleccionado.setTipoJuego(cbTipoJuego.getValue());

            jugadorDAO.updateJugador(jugadorSeleccionado); // <-- LLAMADA AL DAO
            cargarJugadoresDeLaBaseDeDatos(); // <-- Refrescamos
            handleLimpiar();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Jugador Modificado", "Los datos del jugador han sido actualizados.");
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Datos Incompletos", "Por favor, rellena todos los campos del formulario.");
        }
    }

    @FXML
    private void handleEliminar() {
        if (jugadorSeleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Ningún Jugador Seleccionado", "Por favor, selecciona " +
                    "un jugador de la tabla para eliminar.");
            return;
        }

        // Pedimos confirmación antes de borrar
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar Eliminación");
        confirmacion.setHeaderText("¿Estás seguro de que quieres eliminar a " + jugadorSeleccionado.getNombre() + "?");
        confirmacion.setContentText("Esta acción no se puede deshacer.");

        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            jugadorDAO.deleteJugador(jugadorSeleccionado.getId()); // <-- LLAMADA AL DAO
            cargarJugadoresDeLaBaseDeDatos(); // <-- Refrescamos
            handleLimpiar();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Jugador Eliminado", "El jugador ha sido eliminado de la lista.");
        }
    }

    @FXML
    private void handleLimpiar() {
        // ¡CAMBIO! Reseteamos el Label del ID a su estado inicial.
        lblJugadorId.setText("-");
        tfNombre.clear();
        tfApellido.clear();
        tfApodo.clear();
        dpFechaNacimiento.setValue(null);
        cbTipoJuego.setValue(null);
        jugadorSeleccionado = null;
        tablaJugadores.getSelectionModel().clearSelection(); // Deselecciona la fila en la tabla
    }

    /**
     * Rellena el formulario con los datos de un jugador.
     * @param jugador El jugador cuyos datos se mostrarán.
     */
    private void rellenarFormulario(Jugador jugador) {
        // ¡CAMBIO! Añadimos la lógica para actualizar el Label del ID.
        // Lo convertimos a String para poder mostrarlo.
        lblJugadorId.setText(String.valueOf(jugador.getId()));
        tfNombre.setText(jugador.getNombre());
        tfApellido.setText(jugador.getApellido());
        tfApodo.setText(jugador.getApodo());
        dpFechaNacimiento.setValue(jugador.getFechaNacimiento());
        cbTipoJuego.setValue(jugador.getTipoJuego());
    }

    /**
     * Comprueba si los campos del formulario tienen datos.
     * @return true si todos los campos son válidos, false en caso contrario.
     */
    private boolean camposSonValidos() {
        return !tfNombre.getText().isEmpty() &&
                !tfApellido.getText().isEmpty() &&
                !tfApodo.getText().isEmpty() &&
                dpFechaNacimiento.getValue() != null &&
                cbTipoJuego.getValue() != null;
    }

    /**
     * Muestra una ventana de alerta genérica.
     * @param tipo El tipo de alerta (INFORMATION, WARNING, ERROR).
     * @param titulo El título de la ventana.
     * @param mensaje El mensaje a mostrar.
     */
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}