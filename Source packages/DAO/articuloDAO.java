package DAO;

import modelo.Articulo;
import java.sql.SQLException;
public interface articuloDAO {

    void insertararticulo (Articulo articulo) throws SQLException;
    // Eliminamos por el campo índice que es cp
    void eliminararticulo (String cp) throws SQLException;

    void actualizararticulo (Articulo articulo) throws SQLException;

    void listararticulo (Articulo articulo) throws SQLException;

}
