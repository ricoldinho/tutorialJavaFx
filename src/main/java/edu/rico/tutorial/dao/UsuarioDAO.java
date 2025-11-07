package edu.rico.tutorial.dao;

import edu.rico.tutorial.model.Usuario;

public interface UsuarioDAO {
    boolean validarCredenciales(String username, String password);
    Usuario getUsuarioPorUsername(String username);
}
