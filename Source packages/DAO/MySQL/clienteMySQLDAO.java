package DAO.MySQL;

import DAO.clienteDAO;
import modelo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class clienteMySQLDAO implements clienteDAO {

    final String INSERT ="INSERT INTO cliente (mail" +
            ", nombre" +
            ", apellidos" +
            ", direccion" +
            ", vip" +
            ", descuento" +
            ", cuotaAnual) " +
            "VALUES (?,?,?,?,?,?,?);";
    final String UPDATE ="UPDATE cliente SET " + ", nombre=?" +
            ", apellidos=?" +
            ", direccion=?" +
            ", vip=?" +
            ", descuento=?" +
            ", cuotaAnual=? WHERE mail=?;";
    final String DELETE ="DELETE FROM cliente WHERE mail=?;";
    final String GETONE= "SELECT * FROM cliente WHERE mail=?;";
    final String GETPREM= "SELECT * FROM cliente WHERE VIP=?;";
    final String GETALL = "SELECT * FROM cliente;";

    private Connection conn;

    public clienteMySQLDAO(Connection Conn){
        this.conn = Conn;
    }


    /**
     * @param C
     */
    @Override
    public void insertar(Cliente C)  {

        PreparedStatement stat = null;

        try {
            stat = this.conn.prepareStatement(INSERT);
            stat.setString(1,C.getCorreoElectronico());
            stat.setString(2,C.getNombre());
            stat.setString(3,C.getApellidos());
            stat.setString(4,C.getDireccion());
            stat.setInt(5,C.tipoCliente().equals("PREMIUM") ? 1 : 0);
            stat.setDouble(6,C.tipoCliente().equals("PREMIUM") ? 0.20 : 0);
            stat.setDouble(7,C.tipoCliente().equals("PREMIUM") ? 30 : 0);


            if (stat.executeUpdate() == 0){

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

    @Override
    public void modificar(Cliente a)  {
        PreparedStatement stat = null;

        try {
            stat = this.conn.prepareStatement(UPDATE);

            stat.setString(1,a.getNombre());
            stat.setString(2, a.getApellidos());
            stat.setString(3, a.getDireccion());
            stat.setInt(4, a.tipoCliente().equals("PREMIUM") ? 1 : 0);
            stat.setDouble(5, a.descuentoEnv());
            stat.setDouble(6, a.calcAnual());
            stat.setString(7, a.getCorreoElectronico());

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

    @Override
    public void eliminar(String a) {
        PreparedStatement stat = null;

        try{
            stat = this.conn.prepareStatement(DELETE);
            stat.setString(1, a);

            if (stat.executeUpdate(DELETE) == 0){

                throw new DAOExceptions(" posible error al eliminar");
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

    @Override
    public List<Cliente> obtenerTodos()  {

        List<Cliente> listado = new ArrayList<>();
        ClientePremium a = null;
        ClienteEstandar b = null;
        PreparedStatement stat = null;

        try {
            stat = conn.prepareStatement(GETALL);
            ResultSet rs = stat.executeQuery();

            while(rs.next() ) {
                if (rs.getInt("vip") == 0) {
                    b = new ClienteEstandar(rs.getString("mail"),rs.getString("nombre"),rs.getString("direccion"));
                    listado.add(b);

                } else {
                    a = new ClientePremium(rs.getString("mail"), rs.getString("nombre"), rs.getString("direccion"), rs.getDouble("descuento"));
                    listado.add(a);
                }
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


        return listado;
    }

    @Override
    public Cliente obtenerUno(String id) {
        try{
            PreparedStatement stat = this.conn.prepareStatement(GETONE);
            ResultSet rs = stat.executeQuery();
            ClienteEstandar a =  null;
            ClientePremium b = null;
            stat.setString(1,id);
            while (rs.next()){

                if(rs.getInt("vip") == 0){
                    a = new ClienteEstandar(rs.getString("mail"),rs.getString("nombre"),rs.getString("direccion"));
                    return a;
                }else{
                    b = new ClientePremium(rs.getString("mail"), rs.getString("nombre"), rs.getString("direccion"), rs.getDouble("descuento"));
                    return b;
                }
            }

        }catch(SQLException e){
            new DAOExceptions("Error al buscar datos ", e);
        }
        return null;
    }

    /**
     * @param criterio
     * @return
     * @throws DAOExceptions
     */
    @Override
    public List<Cliente> obtenerPorCriterio(String criterio)  {
        return null;
    }

}
