package DAO.MySQL;

import DAO.pedidoDAO;
import modelo.Articulo;
import modelo.Datos;
import modelo.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class pedidoMySQLDAO implements pedidoDAO {

    final String INSERT ="INSERT INTO pedido (numeroPedido, fechaHoraPedido, costeEnvio" +
            ", cantidad, enviado, Articulo_CP, ClienteStandard_mail) " +
            " VALUES (?,'?',?,?,?,'?','?');";
    final String UPDATE ="UPDATE pedido SET numeroPedido=?, fechaHoraPedido=?" +
            ", costeEnvio=? " +
            ", cantidad=?" +
            ", enviado='?'" +
            ", Articulo_CP='?'" +
            ", ClienteStandard_mail='?'" +
            " WHERE 'numeroPedido'=?;";
    final String DELETE  ="DELETE FROM pedido WHERE 'numeroPedido'=?;";
    final String GETONE= "SELECT * FROM pedido WHERE 'numeroPedido'=?;";
    final String GETALL = "SELECT * FROM pedido;";
    private Connection conn;

    public pedidoMySQLDAO(Connection Conn){
        this.conn = Conn;
    }

    /**
     * @param p
     */
    @Override
    public void insertar(Pedido p) throws DAOExceptions {

        PreparedStatement stat = null;

        try {
            stat = conn.prepareStatement(INSERT);
            stat.setInt(1,p.getNumeroPedido());
            // TODO
            // Convertir el formato date time para poder pasarlo a MySQL
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
            String DateTimeString = p.getFechaHoraPedido().format(formatter);
            stat.setString(2,DateTimeString);
            // Coste envío no está en la clase cliente
            stat.setDouble(3,p.getArticulo().getPrecio());
            stat.setDouble(3,p.getCantidad());
            // envidado, es un campo true/false
            stat.setBoolean(3,p.isEnviado());
            stat.setString(3,p.getArticulo().getCodigo());
            stat.setString(3,p.getCliente().getCorreoElectronico());

            if (stat.executeUpdate() == 0){

                throw new DAOExceptions(" posible error al guardar");
            }

        } catch (SQLException e) {
            throw new DAOExceptions("Error e SQL", e);
        }finally{
            if (stat != null){
                try {
                    stat.close();
                }catch(SQLException e){
                    throw  new DAOExceptions("Error en SQL", e);
                }
            }
        }
    }


    /**
     * @param p
     */
    @Override
    public void modificar(Pedido p) throws DAOExceptions {
        PreparedStatement stat = null;

        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setInt(1,p.getNumeroPedido());
            // TODO
            // Convertir el formato date time para poder pasarlo a MySQL
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
            String DateTimeString = p.getFechaHoraPedido().format(formatter);
            stat.setString(2,DateTimeString);
            // Coste envío no está en la clase cliente
            stat.setDouble(3,p.getArticulo().getPrecio());
            stat.setDouble(3,p.getCantidad());
            // envidado, es un campo true/false
            stat.setBoolean(3,p.isEnviado());
            stat.setString(3,p.getArticulo().getCodigo());
            stat.setString(3,p.getCliente().getCorreoElectronico());

            if (stat.executeUpdate() == 0){
                throw new DAOExceptions(" posible error al guardar");
            }

        } catch (SQLException e) {
            throw new DAOExceptions("Error e SQL", e);
        }finally{
            if (stat != null){
                try {
                    stat.close();
                }catch(SQLException e){
                    throw  new DAOExceptions("Error en SQL", e);
                }
            }
        }
    }


    /**
     * @param p
     */
    @Override
    public void eliminar(Pedido p) throws DAOExceptions{
        PreparedStatement stat = null;
        try{
            stat = conn.prepareStatement(DELETE);
            stat.setInt(1,p.getNumeroPedido());
            if (stat.executeUpdate() == 0){

                throw new DAOExceptions(" posible error al eliminar");
            }

        }catch (SQLException e) {
            throw new DAOExceptions("Error e SQL", e);
        }finally{
            if (stat != null){
                try {
                    stat.close();
                }catch(SQLException e){
                    throw  new DAOExceptions("Error en SQL", e);
                }
            }
        }

    }

    /**
     * @return
     */
    @Override
    public List<Pedido> obtenerTodos() {
        return null;
    }

    /**
     * @param NP
     * @return
     */
    @Override
    public Pedido obtener(String NP) throws DAOExceptions {
        PreparedStatement stat = null;
        String hora;
        Pedido p = null;

        try {
            stat = conn.prepareStatement(GETONE);
            stat.setString(1,NP);
            ResultSet rs = stat.executeQuery();


            while(rs.next() ){
                p = new Pedido();
                p.setNumeroPedido(rs.getInt("numeroPedido"));
                // TODO
                // Pasar String a LocalDateTime
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(rs.getString("fechaHoraPedido"), formatter);
                p.setFechaHoraPedido(dateTime);
                // ------------------------------------------------
                p.setCantidad(rs.getInt("cantidad"));
                p.setEnviado(rs.getBoolean("enviado"));
                // TODO
                // Hay que detectar el Articulo y cargar la clase
                // Datos.devolverIndiceArticulo(rs.getString("Articulo_CP"));
                //p.setArticulo(rs.getString("Articulo_CP"));
                // TODO
                // Hay que leer el mail y cargar los datos del cliente
                // Datos.devolverIndiceCliente(rs.getString("ClienteStandard_mail")
                //p.setCliente(rs.getString("ClienteStandard_mail"));

            }
        } catch (SQLException e) {
            throw new DAOExceptions("Error e SQL", e);
        }finally{
            if (stat != null){
                try {
                    stat.close();
                }catch(SQLException e){
                    throw  new DAOExceptions("Error en SQL", e);
                }
            }
        }
        return p;
    }

    // FIN --------------------------------------------------------------------
}
