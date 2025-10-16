package edu.rico.tutorial.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Constantes para los detalles de la conexión
    private static final String URL = "jdbc:mysql://localhost:8889/pokerfx";
    private static final String USER = "root"; // <-- CAMBIA ESTO
    private static final String PASSWORD = "root"; // <-- CAMBIA ESTO

    private static Connection connection = null;

    // Constructor privado para evitar que se creen instancias de esta clase (patrón Singleton)
    private DatabaseConnection() {}

    /**
     * Devuelve una instancia de la conexión a la base de datos.
     * Si la conexión no existe o está cerrada, intenta crear una nueva.
     * @return un objeto Connection.
     * @throws SQLException si ocurre un error al conectar.
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // El Class.forName es opcional en versiones modernas de JDBC, pero es una buena práctica
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                System.err.println("Error: Driver JDBC de MySQL no encontrado.");
                e.printStackTrace();
            }
        }
        return connection;
    }
}