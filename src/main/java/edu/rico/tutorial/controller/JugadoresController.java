package edu.rico.tutorial.controller;

import edu.rico.tutorial.model.Jugador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class JugadoresController implements Initializable {

    // Componentes de la Tabla
    @FXML private TableView<Jugador> tablaJugadores;
    @FXML private TableColumn<Jugador, String> colNombre;
    @FXML private TableColumn<Jugador, String> colApellido;
    @FXML private TableColumn<Jugador, String> colApodo;
    @FXML private TableColumn<Jugador, LocalDate> colFechaNacimiento;
    @FXML private TableColumn<Jugador, String> colTipoJuego;

    // Componentes del Formulario
    @FXML private TextField tfNombre;
    @FXML private TextField tfApellido;
    @FXML private TextField tfApodo;
    @FXML private DatePicker dpFechaNacimiento;
    @FXML private ComboBox<String> cbTipoJuego;

    // Botones
    @FXML private Button btnAnadir;
    @FXML private Button btnModificar;
    @FXML private Button btnEliminar;

    // Lista observable para los datos de la tabla
    private ObservableList<Jugador> listaJugadores;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 1. Inicializar la lista de jugadores
        listaJugadores = FXCollections.observableArrayList();

        // 2. Configurar las columnas para que sepan qué atributo de 'Jugador' mostrar
        // El String "nombre" DEBE coincidir con el método getNombre() del modelo.
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colApodo.setCellValueFactory(new PropertyValueFactory<>("apodo"));
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        colTipoJuego.setCellValueFactory(new PropertyValueFactory<>("tipoJuego"));

        // 3. Rellenar el ComboBox de estilos de juego
        cbTipoJuego.setItems(FXCollections.observableArrayList("Farolero", "Agresivo", "Conservador", "Roca"));

        // 4. Cargar datos de ejemplo
        cargarDatosDeEjemplo();

        // 5. Asignar los datos a la tabla
        tablaJugadores.setItems(listaJugadores);
    }

    private void cargarDatosDeEjemplo() {
        listaJugadores.add(new Jugador("Daniel", "Negreanu", "Kid Poker",
                LocalDate.of(1974, 7, 26), "Agresivo"));
        listaJugadores.add(new Jugador("Phil", "Ivey", "No Home Jerome",
                LocalDate.of(1977, 2, 1), "Roca"));
        listaJugadores.add(new Jugador("Vanessa", "Selbst", "Vanessa",
                LocalDate.of(1984, 7, 9), "Agresivo"));
        listaJugadores.add(new Jugador("Phil", "Hellmuth", "The Poker Brat",
                LocalDate.of(1964, 7, 16), "Farolero"));
    }

    // Lógica para los botones (Añadir, Modificar, Eliminar)
    @FXML
    private void handleAnadir() {
        // Aquí iría la lógica para añadir un nuevo jugador
        // 1. Recoger datos del formulario
        // 2. Crear un nuevo objeto Jugador
        // 3. Añadirlo a 'listaJugadores'
        System.out.println("Botón Añadir pulsado");
    }

    @FXML
    private void handleModificar() {
        System.out.println("Botón Modificar pulsado");
    }

    @FXML
    private void handleEliminar() {
        System.out.println("Botón Eliminar pulsado");
    }

    @FXML
    private void handleLimpiar() {
        System.out.println("Botón Limpiar pulsado");
    }
}