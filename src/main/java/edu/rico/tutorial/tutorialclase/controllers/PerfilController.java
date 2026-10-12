package edu.rico.tutorial.tutorialclase.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.converter.NumberStringConverter;

public class PerfilController {

    @FXML private TextField txtNombre;
    @FXML private Slider sldRadio;
    @FXML private TextField txtRadio;
    @FXML private ColorPicker colorPicker;
    @FXML private Circle circlePreview;
    @FXML private Label lblEstado;
    @FXML private Button btnGuardar;

    @FXML
    public void initialize() {
        // Establecemos un color inicial por defecto en el ColorPicker
        colorPicker.setValue(Color.web("#89b4fa"));

        // =========================================================================
        // 1. BINDING BIDIRECCIONAL: Slider <---> TextField del Radio
        // =========================================================================
        // Sincroniza el valor numérico del Slider (DoubleProperty) con el texto del TextField (StringProperty).
        // Usamos NumberStringConverter("###") para trabajar con números enteros sin decimales molestos.
        Bindings.bindBidirectional(
                txtRadio.textProperty(),
                sldRadio.valueProperty(),
                new NumberStringConverter("###")
        );
        // =========================================================================
        // 2. BINDING UNIDIRECCIONAL DIRECTO: Slider ---> Radio del Círculo
        // =========================================================================
        // El radio del Circle escucha reactivamente la DoubleProperty del Slider.
        // Si el slider se mueve (o se teclea en el TextField), el círculo cambia de tamaño al instante.
        circlePreview.radiusProperty().bind(sldRadio.valueProperty());
        // =========================================================================
        // 3. BINDING DE PROPIEDAD DE COLOR: ColorPicker ---> Relleno del Círculo
        // =========================================================================
        // El color del círculo se encuentra estrictamente ligado a la ObjectProperty<Color> del ColorPicker.
        circlePreview.fillProperty().bind(colorPicker.valueProperty());
        // =========================================================================
        // 4. BINDING COMPUESTO DE TEXTO (Fluent API + Bindings.concat)
        // =========================================================================
        // Creamos una cadena reactiva que concatena texto estático con la propiedad formateada del Slider.
        lblEstado.textProperty().bind(
                Bindings.concat("Dimensiones del Avatar: ", sldRadio.valueProperty().asString("%.0f"), " px de radio")
        );
        // =========================================================================
        // 5. VALIDACIÓN REACTIVA DEL FORMULARIO (Deshabilitar Botón)
        // =========================================================================
        // Regla de Negocio: El botón "Guardar" debe estar DESHABILITADO si:
        // - El campo de nombre está vacío O
        // - El radio del avatar es menor a 30 px.
        BooleanBinding nombreVacio = txtNombre.textProperty().isEmpty();
        BooleanBinding radioInsuficiente = sldRadio.valueProperty().lessThan(30);
        // Unimos ambas condiciones lógicas con una operación OR reactiva [10-12]
        BooleanBinding formularioInvalido = nombreVacio.or(radioInsuficiente);
        // Enlazamos la propiedad disableProperty del botón al resultado de la condición
        btnGuardar.disableProperty().bind(formularioInvalido);
    }
}