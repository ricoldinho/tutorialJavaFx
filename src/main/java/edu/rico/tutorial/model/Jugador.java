package edu.rico.tutorial.model;

import java.time.LocalDate;

public class Jugador {
    private int id; // ¡NUEVO CAMPO!
    private String nombre;
    private String apellido;
    private String apodo;
    private LocalDate fechaNacimiento;
    private String tipoJuego;

    // Constructor para crear objetos desde el formulario (sin id)
    public Jugador(String nombre, String apellido, String apodo, LocalDate fechaNacimiento, String tipoJuego) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.apodo = apodo;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoJuego = tipoJuego;
    }

    // Constructor para leer objetos desde la BD (con id)
    public Jugador(int id, String nombre, String apellido, String apodo, LocalDate fechaNacimiento, String tipoJuego) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.apodo = apodo;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoJuego = tipoJuego;
    }

    public Jugador() {

    }

    // GETTER Y SETTER PARA EL ID
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // ... el resto de getters y setters se mantienen igual ...
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