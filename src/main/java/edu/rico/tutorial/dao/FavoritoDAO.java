package edu.rico.tutorial.dao;

import edu.rico.tutorial.model.Jugador;

import java.util.List;

public interface FavoritoDAO {
    List<Jugador> getJugadoresFavoritos(int usuarioId);
    void addJugadorFavorito(int usuarioId, int jugadorId);
    void removeJugadorFavorito(int usuarioId, int jugadorId);
}
