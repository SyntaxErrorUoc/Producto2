package Factory;

import ConexionMySQL.DAOExceptions;
import DAO.PedidoDAO;
import modelo.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PedidoDAOFactoryMySQL implements PedidoDAO {

    final String INSERT ="INSERT INTO pedido (numeroPedido, fechaHoraPedido, costeEnvio" +
            ", cantidad, enviado, Articulo_CP, ClienteStandard_mail) " +
            " VALUES (?,?,?,?,?,?,?);";
    final String UPDATE ="UPDATE pedido SET " +
            " numeroPedido=?" +
            ", fechaHoraPedido=?" +
            ", costeEnvio=? " +
            ", cantidad=?" +
            ", enviado=?" +
            ", Articulo_CP=?" +
            ", ClienteStandard_mail=?" +
            " WHERE numeroPedido=?;";
    final String DELETE  ="DELETE FROM `pedido` WHERE `numeroPedido`=?;";
    final String GETONE=  "SELECT * FROM pedido " +
            "INNER JOIN Articulo ON pedido.CP = Articulo.Articulo_CP " +
            "INNER JOIN Cliente ON Pedido.ClienteStandard_mail = cliente.mail" +
            " WHERE numeroPedido=?;";
    final String GETALL = "SELECT * FROM `pedido` " +
            "INNER JOIN Articulo ON pedido.CP = Articulo.Articulo_CP " +
            "INNER JOIN Cliente ON Pedido.ClienteStandard_mail = cliente.mail";
    private Connection conn;

    public PedidoDAOFactoryMySQL(Connection Conn){
        this.conn = Conn;
    }

    @Override
    public void insertar(Pedido p) {
        PreparedStatement stat  = null;
        try{

            stat = this.conn.prepareStatement(INSERT);
            stat.setInt(1,p.getNumeroPedido());
            //stat.setTimestamp(2, new java.sql.Timestamp(p.getFechaHoraPedido());
        }catch(SQLException e){
            new DAOExceptions("Error al insertar");
        }
    }

    @Override
    public void modificar(Pedido a) {

    }

    @Override
    public void eliminar(Integer a) {

    }

    @Override
    public ArrayList<Pedido> obtenerTodos() {
        return null;
    }

    @Override
    public Pedido obtenerUno(Integer id) {

        PreparedStatement stat = null;
        String hora;
        Pedido p = null;

        try {
            stat = conn.prepareStatement(GETONE);
            stat.setInt(1,id);
            ResultSet rs = stat.executeQuery();

            while(rs.next() ){
                p = new Pedido();
                p.setNumeroPedido(rs.getInt("numeroPedido"));
                p.setCantidad(rs.getInt("cantidad"));
                p.getArticulo(rs.getString("Articulo_CP"));
                p.getCliente(rs.getString("ClienteStandard_mail"));
            }
        } catch (SQLException e) {
            new DAOExceptions("Error e SQL", e);
        }finally{
            if (stat != null){
                try {
                    stat.close();
                }catch(SQLException e){
                    new DAOExceptions("Error en SQL", e);
                }
            }
        }
        return p;
    }


    @Override
    public ArrayList<Pedido> obtenerPorCriterio(Integer columna, Integer criterio) {
        return null;
    }

    /**
     * @param columna
     * @param criterio
     * @return
     */

}