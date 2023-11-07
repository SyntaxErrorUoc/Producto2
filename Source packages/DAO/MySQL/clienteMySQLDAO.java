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
    final String UPDATE ="UPDATE cliente SET " +
            ", nombre=?" +
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
    public void insertar(Cliente C) throws DAOExceptions {

        PreparedStatement stat = null;

        try {
            stat = conn.prepareStatement(INSERT);
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
    public void eliminar(String a) throws SQLException {
        PreparedStatement stat = null;

        try{
            stat = conn.prepareStatement(DELETE);
            stat.setString(1, a);

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
        ListaClientes Listado = null;
        ClientePremium a = null;
        ClienteEstandar b = null;
        PreparedStatement stat = null;

        try {
            stat = conn.prepareStatement(GETALL);
            ResultSet rs = stat.executeQuery();

            while(rs.next() ) {
                if (rs.getInt("vip") == 0) {
                    b = new ClienteEstandar(rs.getString("mail"), rs.getString("nombre"), rs.getString("direccion"));
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
                    Listado.agregar(b);
                } else {
                    a = new ClientePremium(rs.getString("mail"), rs.getString("nombre"), rs.getString("direccion"), rs.getDouble("descuento"));
                    a.setCorreoElectronico(rs.getString("mail"));
                    a.setNombre(rs.getString("nombre"));
                    // -- [ Apellidos ] ---------------------------------------
                    a.setDireccion(rs.getString("direccion"));
                    // En cliente ESTANDAR no existe esto.
                    // a.tipoCliente("PREMIUM");
                    // a.calcAnual(30);
                    // a.descuentoEnv(0.20);
                    Listado.agregar(a);
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


        return Listado.getLista();
    }

    @Override
    public Cliente obtener(String id) throws DAOExceptions {
        return null;
    }

    @Override
    public List<Cliente> obtenerPorCriterio(String criterio) throws DAOExceptions {
        return null;
    }

    /*
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
*/

    public List<Cliente> mostrarPorTipoCliente(String tc) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        try (PreparedStatement stat = conn.prepareStatement(GETPREM)){
            stat.setInt(1,"PREMIUM".equals(tc) ? 1 : 0);
            try(ResultSet rs = stat.executeQuery()){
                while(rs.next()){
                    if(tc.equals("PREMIUM")) {
                        ClientePremium cP = new ClientePremium(
                                rs.getString("mail"),
                                rs.getString("nombre"),
                                rs.getString("direccion"),
                                rs.getDouble("descuento")
                        );
                        clientes.add(cP);
                    }else{
                        ClienteEstandar cE = new ClienteEstandar(
                                rs.getString("mail"),
                                rs.getString("nombre"),
                                rs.getString("direccion")
                        );
                        clientes.add(cE);
                    }
                }
            }
        }catch(SQLException e){
            throw new DAOExceptions("Error al obtener los clientes, e");
        }
        return clientes;
    }
}
