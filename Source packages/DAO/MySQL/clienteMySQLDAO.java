package DAO.MySQL;

import DAO.clienteDAO;
import modelo.Articulo;
import modelo.Cliente;
import modelo.ClienteEstandar;
import modelo.ClientePremium;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class clienteMySQLDAO implements clienteDAO {

    final String INSERT ="INSERT INTO cliente (mail" +
            ", nombre" +
            ", apellidos" +
            ", direccion" +
            ", vip" +
            ", descuento" +
            ", cuotaAnual) " +
            "VALUES ('?','?','?','?',?,?,?);";
    final String UPDATE ="UPDATE cliente SET mail=?" +
            ", nombre=?" +
            ", apellidos=?" +
            ", direccion=?" +
            ", vip=?" +
            ", descuento=?" +
            ", cuotaAnual=? WHERE mail=?;";
    final String DELETE ="DELETE FROM cliente WHERE 'mail'=?;";
    final String GETONE= "SELECT * FROM cliente WHERE 'mail'='?';";
    final String GETALL = "SELECT * FROM cliente;";

    private Connection conn;

    public clienteMySQLDAO(Connection Conn){
        this.conn = Conn;
    }


    /**
     * @param C
     */
    @Override
    public void insertar(Cliente C) throws DAOExceptions {

        PreparedStatement stat = null;

        try {
            stat = conn.prepareStatement(INSERT);
            stat.setString(1,C.getCorreoElectronico());
            stat.setString(1,C.getNombre());
            stat.setString(2,C.getDireccion());
            if (C.tipoCliente().equals("PREMIUM")){
                stat.setInt(2,1);
                stat.setDouble(2,0.20);
                stat.setDouble(2, 30);
            }else{
                stat.setInt(2,0);
                stat.setDouble(2,0);
                stat.setDouble(2,0);
            }

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

    @Override
    public void modificar(Cliente a) throws DAOExceptions {
        PreparedStatement stat = null;

        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1,a.getCorreoElectronico());
            stat.setString(2,a.getNombre());
            // TODO
            // No hay campo de apellidos
            stat.setString(2,a.getDireccion());
            if (a.tipoCliente().equals("PREMIUM")){
                stat.setInt(2,1);
            }else{
                stat.setInt(2,10);
            }
            stat.setDouble(2,a.descuentoEnv());
            stat.setDouble(2,a.calcAnual());

            if (stat.executeUpdate(UPDATE) == 0){
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

    @Override
    public void eliminar(Cliente a) throws DAOExceptions {
        PreparedStatement stat = null;
        try{
            stat = conn.prepareStatement(DELETE);
            stat.setString(1,a.getCorreoElectronico());
            if (stat.executeUpdate(DELETE) == 0){

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

    @Override
    public List<Cliente> obtenerTodos() throws DAOExceptions {
        return null;
    }

    @Override
    public Cliente obtener(String id) throws DAOExceptions {

        PreparedStatement stat = null;
        String hora;
        ClientePremium a = null;
        ClienteEstandar b = null;

        try {
            stat = conn.prepareStatement(GETONE);
            stat.setString(1,id);
            ResultSet rs = stat.executeQuery();

            while(rs.next() ) {
                if (rs.getInt("vip") == 0) {
                    b = new ClienteEstandar();
                    b.setCorreoElectronico(rs.getString("mail"));
                    b.setNombre(rs.getString("nombre"));
                    // -- [ Apellidos ] ---------------------------------------
                    b.setDireccion(rs.getString("direccion"));
                    // TODO
                    // Al final hay que hacer los Getters y Setters para pasar el valor o se asume
                    // ¿Hacer una tabla sólo para clientes VIP y que recoja los datos de esta?
                    // b.tipoCliente("ESTANDAR");
                    // b.calcAnual(30);
                    // b.descuentoEnv(0.20);
                } else {
                    a = new ClientePremium();
                    a.setCorreoElectronico(rs.getString("mail"));
                    a.setNombre(rs.getString("nombre"));
                    // -- [ Apellidos ] ---------------------------------------
                    a.setDireccion(rs.getString("direccion"));
                    // En cliente ESTANDAR no existe esto.
                    // a.tipoCliente("PREMIUM");
                    // a.calcAnual(30);
                    // a.descuentoEnv(0.20);
                }
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
        return a;
    }
}
