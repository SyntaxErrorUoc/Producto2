package DAO;

import modelo.ClienteEstandar;

import java.sql.SQLException;
public interface clienteEstandarDAO {

    void insertarclienteEstandar (ClienteEstandar clienteestandar) throws SQLException;
    // Eliminamos por el campo índice que es mail
    void eliminarclienteEstandar (String mail) throws SQLException;

    void actualizarclienteEstandar (ClienteEstandar clienteestandard) throws SQLException;

    void listarclienteEstandar (ClienteEstandar clienteestandar) throws SQLException;
}
