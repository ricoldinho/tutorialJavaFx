package edu.rico.tutorial.dao;

public interface UsuarioDAO {
    boolean validarCredenciales(String username, String password);
}