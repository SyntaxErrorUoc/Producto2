package DAO.MySQL;

import DAO.pedidoDAO;
import modelo.Articulo;
import modelo.Cliente;
import modelo.ClienteEstandar;
import modelo.Pedido;

import java.sql.*;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class pedidoMySQLDAO implements pedidoDAO {

    final String INSERT ="INSERT INTO `pedido` (`numeroPedido`, `fechaHoraPedido`, `costeEnvio`" +
            ", `cantidad`, `enviado`, `Articulo_CP`, `ClienteStandard_mail`) " +
            " VALUES (?,?,?,?,?,?,?);";
    final String UPDATE ="UPDATE pedido SET " +
            " `numeroPedido`=?" +
            ", `fechaHoraPedido`=?" +
            ", `costeEnvio`=? " +
            ", `cantidad`=?" +
            ", `enviado`=?" +
            ", `Articulo_CP`=?" +
            ", `ClienteStandard_mail`=?" +
            " WHERE numeroPedido=?;";
    final String DELETE  ="DELETE FROM `pedido` WHERE `numeroPedido`=?;";
    final String GETONE=  "SELECT * FROM `pedido` " +
            "INNER JOIN Articulo ON pedido.CP = Articulo.Articulo_CP " +
            "INNER JOIN Cliente ON Pedido.ClienteStandard_mail = cliente.mail" +
            " WHERE `numeroPedido`=?;";
    final String GETALL = "SELECT * FROM `pedido` " +
            "INNER JOIN Articulo ON pedido.CP = Articulo.Articulo_CP " +
            "INNER JOIN Cliente ON Pedido.ClienteStandard_mail = cliente.mail";
    private Connection conn;

    public pedidoMySQLDAO(Connection Conn){
        this.conn = Conn;
    }

    /**
     * @param p
     */
    @Override
    public void insertar(Pedido p)  {

        PreparedStatement stat = null;

        try {
            stat = this.conn.prepareStatement(INSERT);
            stat.setInt(1,p.getNumeroPedido());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
            String DateTimeString = p.getFechaHoraPedido().format(formatter);
            stat.setString(2,DateTimeString);
            stat.setDouble(3,p.getArticulo().getPrecio());
            stat.setDouble(4,p.getCantidad());
            stat.setBoolean(5,p.isEnviado());
            stat.setString(6,p.getArticulo().getCodigo());
            stat.setString(7,p.getCliente().getCorreoElectronico());

            if (stat.executeUpdate(INSERT) == 0){

                throw new DAOExceptions(" posible error al guardar");
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
    }


    /**
     * @param p
     */
    @Override
    public void modificar(Pedido p) {
        PreparedStatement stat = null;

        try {
            stat = this.conn.prepareStatement(UPDATE);
            stat.setInt(1,p.getNumeroPedido());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
            String DateTimeString = p.getFechaHoraPedido().format(formatter);
            stat.setString(2,DateTimeString);
            stat.setDouble(3,p.getArticulo().getPrecio());
            stat.setDouble(3,p.getCantidad());
            stat.setBoolean(3,p.isEnviado());
            stat.setString(3,p.getArticulo().getCodigo());
            stat.setString(3,p.getCliente().getCorreoElectronico());

            if (stat.executeUpdate(UPDATE) == 0){
                throw new DAOExceptions(" posible error al guardar");
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
    }

    /**
     *
     * @param idPedido
     */
    @Override
    public void eliminar(Integer idPedido) {
        PreparedStatement stat = null;
        try{
            stat = this.conn.prepareStatement(DELETE);
            stat.setInt(1,idPedido);
            if (stat.executeUpdate(DELETE) == 0){

               new DAOExceptions(" posible error al eliminar");
            }

        }catch (SQLException e) {
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

    }

    /**
     * @return
     */
    @Override
    public ArrayList<Pedido> obtenerTodos() {

        Cliente c = null;
        Articulo a = null;
        Pedido p = null;
        ArrayList<Pedido> listaPedidos= new ArrayList<>();

        try{
            PreparedStatement stat = this.conn.prepareStatement(GETALL);
            ResultSet rs = stat.executeQuery();
            while(rs.next()){

                String cp = rs.getString("CP");
                String desc = rs.getString("Descripcion");
                Double precio = rs.getDouble("Precio");
                Long tiempoPrep = rs.getLong("TiempoPreparacion");

                String mail = rs.getString("mail");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");

                int numPed = rs.getInt("numeroPedido");
                Long fechahora = rs.getLong("fechaHoraPedido");
                int cant = rs.getInt("cantidad");
                double costeE = rs.getDouble("costeEnvio");
                int enviado = rs.getInt("enviado");
                boolean envio = false;

                if (enviado == 0){
                    envio = false;
                }else{
                    envio = true;
                }

                Duration duration = Duration.ofMillis(tiempoPrep);
                Instant instant = Instant.ofEpochMilli(fechahora);
                LocalDateTime prep = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                c = new ClienteEstandar(mail,nombre,direccion);
                a = new Articulo(cp, desc, precio,duration);

                p = new Pedido(numPed, prep,c,a,cant,envio,costeE);

                listaPedidos.add(p);



            }
        }catch(SQLException e ){
            new DAOExceptions("Error en la busqueda",e);
        }


        return listaPedidos;

    }

    /**
     * @param NP
     * @return
     */
    @Override
    public Pedido obtenerUno(Integer NP) {

        Cliente c = null;
        Articulo a = null;
        Pedido p = null;


        try{
            PreparedStatement stat = this.conn.prepareStatement(GETONE);
            ResultSet rs = stat.executeQuery();
            stat.setInt(1,NP);

            String cp = rs.getString("CP");
            String desc = rs.getString("Descripcion");
            Double precio = rs.getDouble("Precio");
            Long tiempoPrep = rs.getLong("TiempoPreparacion");

            String mail = rs.getString("mail");
            String nombre = rs.getString("nombre");
            String direccion = rs.getString("direccion");

            int numPed = rs.getInt("numeroPedido");
            Long fechahora = rs.getLong("fechaHoraPedido");
            int cant = rs.getInt("cantidad");
            double costeE = rs.getDouble("costeEnvio");
            int enviado = rs.getInt("enviado");
            boolean envio = false;

            if (enviado == 0){
                envio = false;
            }else{
                envio = true;
            }

            Duration duration = Duration.ofMillis(tiempoPrep);
            Instant instant = Instant.ofEpochMilli(fechahora);
            LocalDateTime prep = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            c = new ClienteEstandar(mail,nombre,direccion);
            a = new Articulo(cp, desc, precio,duration);

            p = new Pedido(numPed, prep,c,a,cant,envio,costeE);






        }catch(SQLException e ){
            new DAOExceptions("Error en la busqueda",e);
        }


        return p;
    }

    /**
     * @param criterio
     * @return
     * @throws DAOExceptions
     */
    @Override
    public List<Pedido> obtenerPorCriterio(String criterio) {
        return null;
    }

    // FIN --------------------------------------------------------------------
}
