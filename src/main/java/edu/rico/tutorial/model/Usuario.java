package edu.rico.tutorial.model;

import java.time.LocalDate;
import java.util.List;

public class Usuario {

    private int usuario_id;
    private String nombre;
    private String apellido;
    private String nickname;
    private LocalDate fecha_nacimiento;
    private String email;
    private String password;

    private List<Jugador> jugadoresFavoritos;

    public Usuario() {
    }

    public Usuario(int usuario_id, String nombre, String apellido, String nickname, LocalDate fecha_nacimiento,
                   String email, String password) {
        this.usuario_id = usuario_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname = nickname;
        this.fecha_nacimiento = fecha_nacimiento;
        this.email = email;
        this.password = password;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Jugador> getJugadoresFavoritos() {
        return jugadoresFavoritos;
    }

    public void setJugadoresFavoritos(List<Jugador> jugadoresFavoritos) {
        this.jugadoresFavoritos = jugadoresFavoritos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuario_id=" + usuario_id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nickname='" + nickname + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}