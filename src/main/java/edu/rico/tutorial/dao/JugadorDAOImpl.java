package edu.rico.tutorial.dao;

import edu.rico.tutorial.db.DatabaseConnection;
import edu.rico.tutorial.model.Jugador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JugadorDAOImpl implements JugadorDAO {

    @Override
    public List<Jugador> getAllJugadores() {
        List<Jugador> jugadores = new ArrayList<>();
        String sql = "SELECT * FROM jugadores";

        // Usamos try-with-resources para asegurar que la conexión se cierre
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Jugador jugador = new Jugador(
                        rs.getInt("id"), // Asumimos que la tabla tiene una columna 'id'
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("apodo"),
                        rs.getDate("fecha_nacimiento").toLocalDate(),
                        rs.getString("tipoJuego")
                );
                jugadores.add(jugador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jugadores;
    }

    @Override
    public void addJugador(Jugador jugador) {
        String sql = "INSERT INTO jugadores(nombre, apellido, apodo, fecha_nacimiento, tipoJuego) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, jugador.getNombre());
            pstmt.setString(2, jugador.getApellido());
            pstmt.setString(3, jugador.getApodo());
            pstmt.setDate(4, Date.valueOf(jugador.getFechaNacimiento()));
            pstmt.setString(5, jugador.getTipoJuego());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateJugador(Jugador jugador) {
        String sql = "UPDATE jugadores SET nombre = ?, apellido = ?, apodo = ?, fecha_nacimiento = ?, tipoJuego = ? " +
                "WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, jugador.getNombre());
            pstmt.setString(2, jugador.getApellido());
            pstmt.setString(3, jugador.getApodo());
            pstmt.setDate(4, Date.valueOf(jugador.getFechaNacimiento()));
            pstmt.setString(5, jugador.getTipoJuego());
            pstmt.setInt(6, jugador.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteJugador(int id) {
        String sql = "DELETE FROM jugadores WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Este método no lo usaremos en el controlador actual, pero es parte del contrato
    @Override
    public Jugador getJugadorById(int id) {
        // Implementación sería similar a getAllJugadores pero con "WHERE id = ?"
        return null;
    }
}