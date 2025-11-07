package edu.rico.tutorial.dao;

import edu.rico.tutorial.db.DatabaseConnection;
import edu.rico.tutorial.model.Jugador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoritoDAOImpl implements FavoritoDAO {

    @Override
    public List<Jugador> getJugadoresFavoritos(int usuarioId) {
        List<Jugador> jugadores = new ArrayList<>();
        String sql = "SELECT j.* FROM jugadores j " +
                     "JOIN favoritos f ON j.id = f.jugador_id " +
                     "WHERE f.usuario_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, usuarioId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Jugador jugador = new Jugador();
                jugador.setId(rs.getInt("id"));
                jugador.setNombre(rs.getString("nombre"));
                jugador.setApellido(rs.getString("apellido"));
                jugador.setApodo(rs.getString("apodo"));
                jugador.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                jugador.setTipoJuego(rs.getString("tipoJuego"));
                jugadores.add(jugador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jugadores;
    }

    @Override
    public void addJugadorFavorito(int usuarioId, int jugadorId) {
        String sql = "INSERT INTO favoritos (usuario_id, jugador_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, usuarioId);
            pstmt.setInt(2, jugadorId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeJugadorFavorito(int usuarioId, int jugadorId) {
        String sql = "DELETE FROM favoritos WHERE usuario_id = ? AND jugador_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, usuarioId);
            pstmt.setInt(2, jugadorId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
