package edu.rico.tutorial.dao;

import edu.rico.tutorial.model.Jugador;
import java.util.List;

public interface JugadorDAO {
    void addJugador(Jugador jugador);
    void updateJugador(Jugador jugador);
    void deleteJugador(int id);
    List<Jugador> getAllJugadores();
    Jugador getJugadorById(int id); // Útil para futuras implementaciones
}
