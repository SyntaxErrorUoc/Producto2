package Factory;
import ConexionMySQL.DAOExceptions;
import ConexionMySQL.ConexionMySQL;
import ConexionMySQL.DatabaseConnectionException;
import DAO.*;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Clase que permite seleccionar el tipo de conexión según la BBDD a la que queramos conectar.
 */
public class FactoryDAO {

    Connection conn = null;

    public ArticuloDAO articulo;
    public PedidoDAO pedido;
    public ClienteDAO cliente;

    /**
     * Constructor de la clase que discrimina el tipo de conexión a realizar según la BBDD a la que queramos conectar.
     * @throws DatabaseConnectionException
     */
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

    /**
     * Método que establece la conexión a una BBDD de MySQL.
     * @return Tipo Connection que contiene la conexión activa a la BBDD
     */
    public Connection MySQL()  {
        Connection conn = null;
        try{
            conn = ConexionMySQL.conectarMySQL();

        } catch(SQLException e){
            new DAOExceptions("Error al conectar", e);
        }
        return conn;
    }

    /**
     * Método que retorna null enb caso de no poder conectar a ninguna BBDD.
     * @return null. Indica que no ha sido posible conectar a la BBDD.
     */

    public Connection NoMysql(){
        return null;
    }

}