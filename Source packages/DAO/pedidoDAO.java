package DAO;

import modelo.Pedido;
import java.sql.SQLException;
public interface pedidoDAO {

    void insertarpedido (Pedido pedido) throws SQLException;
    // Eliminamos por el campo índice que es cp
    void eliminarpedido (String numpedido) throws SQLException;

    void actualizarpedido (Pedido pedido) throws SQLException;

    void listarpedido (Pedido pedido) throws SQLException;

}
