package edu.rico.tutorial.dao;

import edu.rico.tutorial.db.DatabaseConnection;
import edu.rico.tutorial.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public boolean validarCredenciales(String username, String password) {
        String sql = "SELECT * FROM usuarios WHERE nickname = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Si hay un resultado, las credenciales son válidas
            }
        } catch (SQLException e) {
            System.err.println("Error al validar las credenciales del usuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Usuario getUsuarioPorUsername(String username) {
        String sql = "SELECT * FROM usuarios WHERE nickname = ?";
        Usuario usuario = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setUsuario_id(rs.getInt("usuario_id"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellido(rs.getString("apellido"));
                    usuario.setNickname(rs.getString("nickname"));
                    usuario.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                    usuario.setEmail(rs.getString("email"));
                    usuario.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el usuario: " + e.getMessage());
            e.printStackTrace();
        }
        return usuario;
    }
}
