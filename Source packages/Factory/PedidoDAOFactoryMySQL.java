package Factory;

import ConexionMySQL.DAOExceptions;
import DAO.PedidoDAO;
import modelo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PedidoDAOFactoryMySQL implements PedidoDAO {

    final String INSERT ="INSERT INTO pedido (numeroPedido, fechaHoraPedido, costeEnvio" +
            ", cantidad, enviado, Articulo_CP, ClienteStandard_mail) " +
            " VALUES (?,?,?,?,?,?,?);";
    final String UPDATE ="UPDATE pedido SET " +
            " fechaHoraPedido=?" +
            ", costeEnvio=? " +
            ", cantidad=?" +
            ", enviado=?" +
            ", Articulo_CP=?" +
            ", ClienteStandard_mail=?" +
            " WHERE numeroPedido=?;";
    final String DELETE  ="DELETE FROM `pedido` WHERE `numeroPedido`=?;";
    final String GETONE=  "SELECT * FROM pedido " +
            "INNER JOIN articulo ON pedido.Articulo_CP = articulo.CP " +
            "INNER JOIN cliente ON Pedido.ClienteStandard_mail = cliente.mail" +
            " WHERE numeroPedido=?;";
    final String GETALL = "SELECT * FROM `pedido` " +
            "INNER JOIN articulo ON pedido.Articulo_CP = articulo.CP " +
            "INNER JOIN cliente ON pedido.ClienteStandard_mail = cliente.mail";

    final String GETALLFILTERESTATUSPEDIDO = "SELECT * FROM `pedido` " +
            "INNER JOIN articulo ON pedido.Articulo_CP = articulo.CP " +
            "INNER JOIN cliente ON pedido.ClienteStandard_mail = cliente.mail WHERE pedido.enviado=?;";

    final String GETALLFILTERESTATUSPEDIDOCLIENTE = "SELECT * FROM `pedido` " +
            "INNER JOIN articulo ON pedido.Articulo_CP = articulo.CP " +
            "INNER JOIN cliente ON pedido.ClienteStandard_mail = cliente.mail WHERE pedido.enviado=? AND ClienteStandard_mail=?;";

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
            stat.setTimestamp(2,Timestamp.valueOf(p.getFechaHoraPedido()));
            stat.setDouble(3,p.getCosteEnvio());
            stat.setInt(4,p.getCantidad());
            stat.setBoolean(5,p.isEnviado());
            stat.setString(6,p.getArticulo().getCodigo());
            stat.setString(7, p.getCliente().getCorreoElectronico());
            if (stat.executeUpdate() == 0){
                new DAOExceptions("Posible error al guardar");
            }
        }catch(SQLException e){
            new DAOExceptions("Error al insertar");
        }
    }

    @Override
    public void modificar(Pedido p) {
        PreparedStatement stat = null;

        try {
            stat = this.conn.prepareStatement(UPDATE);
            java.sql.Time sqlTime = java.sql.Time.valueOf(String.valueOf(p.getFechaHoraPedido()));
            stat.setTime(1,sqlTime);
            stat.setDouble(2,p.getCosteEnvio());
            stat.setInt(3,p.getCantidad());
            stat.setBoolean(4,p.isEnviado());
            stat.setString(5,p.getArticulo().getCodigo());
            stat.setString(6,p.getCliente().getCorreoElectronico());
            stat.setDouble(7,p.getNumeroPedido());
            if (stat.executeUpdate() == 0){
                new DAOExceptions("Posible error al guardar");
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

    @Override
    public void eliminar(Integer a) {
        PreparedStatement stat = null;

        try {
            stat = this.conn.prepareStatement(DELETE);
            stat.setInt(1,a);
            if (stat.executeUpdate() == 0){
                new DAOExceptions("Posible error al guardar");
            }
        }catch (SQLException e) {
            new DAOExceptions("Error al eliminar", e);
        } {

        }
    }

    @Override
    public ArrayList<Pedido> obtenerTodos() {
        PreparedStatement stat = null;
        ArrayList<Pedido> ped = new ArrayList<>();
        String hora;
        Pedido p = null;
        Articulo art =null;
        ClienteEstandar clienteest = null;
        ClientePremium clienteprem = null;

        // Formato del patrón de la cadena
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            stat = conn.prepareStatement(GETALL);
            ResultSet rs = stat.executeQuery();
            while(rs.next() ){
                p = new Pedido();
                p.setFechaHoraPedido(LocalDateTime.parse(rs.getString("fechaHoraPedido"),formatter));
                p.setNumeroPedido(rs.getInt("numeroPedido"));
                p.setCantidad(rs.getInt("cantidad"));
                hora = rs.getString("TiempoPreparacion");
                LocalTime tiempo = LocalTime.parse(hora);
                int horas = tiempo.getHour();
                int minutos = tiempo.getMinute();
                Duration tiempoP = Duration.ofHours(horas).plusMinutes(minutos);
                art = new Articulo(rs.getString("CP"),
                        rs.getString("Descripcion"),
                        rs.getDouble("Precio"),
                        tiempoP);
                p.setArticulo(art);
                if (rs.getInt("vip") == 0){
                    clienteest = new ClienteEstandar(rs.getString("mail"),
                            rs.getString("nombre"),
                            rs.getString("direccion"));
                    p.setCliente(clienteest);
                }else{
                    clienteprem = new ClientePremium(rs.getString("mail"),
                            rs.getString("nombre"),
                            rs.getString("direccion"), rs.getDouble("descuento"));
                    p.setCliente(clienteprem);
                }
                ped.add(p);
            }
        } catch (SQLException e) {
            new DAOExceptions("Error e SQL", e);
        }
        return ped;
    }

    @Override
    public Pedido obtenerUno(Integer id) {

        PreparedStatement stat = null;
        String hora;
        Pedido p = null;
        Articulo art =null;
        ClienteEstandar clienteest = null;
        ClientePremium clienteprem = null;

        // Formato del patrón de la cadena
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            stat = conn.prepareStatement(GETONE);
            stat.setInt(1,id);
            ResultSet rs = stat.executeQuery();

            while(rs.next() ){
                p = new Pedido();
                p.setNumeroPedido(rs.getInt("numeroPedido"));
                p.setFechaHoraPedido(LocalDateTime.parse(rs.getString("fechaHoraPedido"),formatter));
                p.setCosteEnvio(rs.getDouble("costeEnvio"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setEnviado((rs.getBoolean("enviado")));

                // Artículo
                hora = rs.getString("TiempoPreparacion");
                LocalTime tiempo = LocalTime.parse(hora);
                int horas = tiempo.getHour();
                int minutos = tiempo.getMinute();
                Duration tiempoP = Duration.ofHours(horas).plusMinutes(minutos);

                art = new Articulo(rs.getString("CP"),
                        rs.getString("Descripcion"),
                        rs.getDouble("Precio"),
                        tiempoP);

                p.setArticulo(art);

                // Cliente

                if (rs.getInt("vip") == 0){
                    clienteest = new ClienteEstandar(rs.getString("mail"),
                            rs.getString("nombre"),
                            rs.getString("direccion"));
                    p.setCliente(clienteest);
                }else{
                    clienteprem = new ClientePremium(rs.getString("mail"),
                            rs.getString("nombre"),
                            rs.getString("direccion"), rs.getDouble("descuento"));
                    p.setCliente(clienteprem);
                }

            }
        } catch (SQLException e) {
            new DAOExceptions("Error e SQL", e);
        }
        return p;
    }

    @Override
    public ArrayList<Pedido> obtenerPorCriterio(Integer columna, Integer criterio) {
        return null;
    }

    @Override
    public ArrayList<Pedido> obtenerPorCriterio(Integer columna, String criterio) {
        String hora;
        Pedido p = null;
        Articulo art =null;
        ClienteEstandar clienteest = null;
        ClientePremium clienteprem = null;
        ArrayList<Pedido> ped = new ArrayList<>();

        // Formato del patrón de la cadena
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        PreparedStatement stat = null;

        try{
            // Columna = enviado donde 1 es sí y 0 es no
            // criterio = mail del cliente
            if (criterio.equals(null)){
                stat = conn.prepareStatement(GETALLFILTERESTATUSPEDIDO);
                stat.setInt(1,columna);
            } else {
                stat = conn.prepareStatement(GETALLFILTERESTATUSPEDIDOCLIENTE);
                stat.setInt(1,columna);
                stat.setString(2, criterio);
            }

            ResultSet rs = stat.executeQuery();
            while(rs.next() ) {
                p = new Pedido();
                p.setNumeroPedido(rs.getInt("numeroPedido"));
                p.setFechaHoraPedido(LocalDateTime.parse(rs.getString("fechaHoraPedido"),formatter));
                p.setCosteEnvio(rs.getDouble("costeEnvio"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setEnviado(rs.getBoolean("enviado"));

                // Artículo

                hora = rs.getString("TiempoPreparacion");
                LocalTime tiempo = LocalTime.parse(hora);
                int horas = tiempo.getHour();
                int minutos = tiempo.getMinute();
                Duration tiempoP = Duration.ofHours(horas).plusMinutes(minutos);

                art = new Articulo(rs.getString("CP"),
                        rs.getString("Descripcion"),
                        rs.getDouble("Precio"),
                        tiempoP);

                p.setArticulo(art);

                // Cliente

                if (rs.getInt("vip") == 0){
                    clienteest = new ClienteEstandar(rs.getString("mail"),
                            rs.getString("nombre"),
                            rs.getString("direccion"));
                    p.setCliente(clienteest);
                }else{
                    clienteprem = new ClientePremium(rs.getString("mail"),
                            rs.getString("nombre"),
                            rs.getString("direccion"), rs.getDouble("descuento"));
                    p.setCliente(clienteprem);
                }
                ped.add(p);
            }

        } catch (SQLException e) {
            new DAOExceptions("Error e SQL", e);
        }

        return ped;
    }


}