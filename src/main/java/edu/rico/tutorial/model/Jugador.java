package edu.rico.tutorial.model;

import java.time.LocalDate;

public class Jugador {
    private String nombre;
    private String apellido;
    private String apodo;
    private LocalDate fechaNacimiento; //Usamos LocalDate para el DatePicker
    private String tipoJuego;

    // Constructor para crear jugadores fácilmente
    public Jugador(String nombre, String apellido, String apodo, LocalDate fechaNacimiento, String tipoJuego) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.apodo = apodo;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoJuego = tipoJuego;
    }

    // GETTERS Y SETTERS (esenciales para que la TableView pueda leer los datos)
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getApodo() { return apodo; }
    public void setApodo(String apodo) { this.apodo = apodo; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public String getTipoJuego() { return tipoJuego; }
    public void setTipoJuego(String tipoJuego) { this.tipoJuego = tipoJuego; }
}
