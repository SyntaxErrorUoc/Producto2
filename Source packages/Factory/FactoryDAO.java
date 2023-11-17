package Factory;
import ConexionMySQL.DAOExceptions;
import ConexionMySQL.ConexionMySQL;
import ConexionMySQL.DatabaseConnectionException;
import DAO.*;

import java.sql.Connection;
import java.sql.SQLException;


public class FactoryDAO {

    Connection conn = null;

    public ArticuloDAO articulo;
    public PedidoDAO pedido;
    public ClienteDAO cliente;

    public FactoryDAO() throws DatabaseConnectionException {

        if (MySQL() != null) {
            Connection conn = MySQL();
            this.articulo = new ArticuloDAOFactoryMySQL(conn);
            this.pedido = new PedidoDAOFactoryMySQL(conn);
            this.cliente = new ClienteDAOFactoryMySQL(conn);

        } else {
            Connection conn = null;

            throw new DatabaseConnectionException(("No es una base de datos MySQL"));

        }


    }

    public Connection MySQL()  {
        Connection conn = null;
        try{
            conn = ConexionMySQL.conectarMySQL();

        } catch(SQLException e){
            new DAOExceptions("Error al conectar", e);
        }
        return conn;
    }
    public Connection NoMysql(){
        return null;
    }

}