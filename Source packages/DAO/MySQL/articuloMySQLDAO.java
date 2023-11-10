package DAO.MySQL;

import java.sql.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import modelo.Articulo;
import DAO.articuloDAO;

public class articuloMySQLDAO implements articuloDAO {

    final String INSERT  ="INSERT INTO articulo (CP, Descripcion, Precio, TiempoPreparacion) VALUES (?,?,?,?)";
    final String UPDATE = "UPDATE articulo SET Descripcion = ?, Precio = ?, TiempoPreparacion = ? WHERE cp = ?";
    final String DELETE  ="DELETE FROM articulo WHERE cp = ?;";
    final String GETONE = "SELECT * FROM articulo WHERE CP=?";
    final String GETALL  = "SELECT * FROM articulo;";

    private Connection conn;

    public articuloMySQLDAO(Connection Conn){
        this.conn = Conn;
    }

    /**
     * @param a
     */
    @Override
    public void insertar(Articulo a) {

        PreparedStatement stat = null;

        try {
            stat = this.conn.prepareStatement(INSERT);
            stat.setString(1,a.getCodigo());
            stat.setString(2,a.getDescripcion());
            stat.setDouble(3,a.getPrecio());
            LocalTime time  = LocalTime.MIDNIGHT.plus(a.getTiempoPreparacion());
            //TODO
            java.sql.Time sqlTime = java.sql.Time.valueOf(time);
            stat.setTime(4,sqlTime);
            if (stat.executeUpdate() == 0){

                new DAOExceptions(" posible error al guardar");
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
     * @param a
     */
    @Override
    public void modificar(Articulo a)  {
        PreparedStatement stat = null;

        try {
            stat = this.conn.prepareStatement(UPDATE);
            stat.setString(1,a.getDescripcion());
            stat.setDouble(2,a.getPrecio());
            LocalTime time  = LocalTime.MIDNIGHT.plus(a.getTiempoPreparacion());
            //TODO
            java.sql.Time sqlTime = java.sql.Time.valueOf(time);
            stat.setTime(3,sqlTime);
            stat.setString(4,a.getCodigo());

            if (stat.executeUpdate() == 0){

                new DAOExceptions(" posible error al guardar");
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
     * @param
     */
    @Override
    public void eliminar(String cp) {
        PreparedStatement stat = null;
        try{

            stat = this.conn.prepareStatement(DELETE);
            stat.setString(1,cp);
            if (stat.executeUpdate() == 0 ){

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
    public List<Articulo> obtenerTodos() {

        List<Articulo> listaA = new ArrayList<>();
        PreparedStatement stat = null;
        try{
            stat = this.conn.prepareStatement(GETALL);
            ResultSet rs = stat.executeQuery();
            while(rs.next()){
                //TODO
                String id = rs.getString("CP");
                String desc = rs.getString("Descripcion");
                Double precio = rs.getDouble("Precio");
                Long tiempoprep = rs.getLong("TiempoPreparacion");
                Duration tiempoP = Duration.ofHours(tiempoprep);

                Articulo a = new Articulo(id,desc,precio,tiempoP);

                listaA.add(a);
            }



        }catch(SQLException e){
            new DAOExceptions("Fallo al realizar la conexion ", e);
        }

        return listaA;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Articulo obtenerUno(String id ) {


        PreparedStatement stat = null;
        String hora;
        Articulo a = null;

        try {
            stat = conn.prepareStatement(GETONE);
            stat.setString(1,id);
            ResultSet rs = stat.executeQuery();

            while(rs.next() ){
                a = new Articulo();
                a.setCodigo( rs.getString("cp"));
                a.setDescripcion(rs.getString("Descripcion"));
                a.setPrecio(rs.getDouble("Precio"));
                hora = rs.getString("TiempoPreparacion");
                String[] partes = hora.split(":");
                if (partes.length == 2){
                    try{
                        int horas = Integer.parseInt(partes[0]);
                        int minutos = Integer.parseInt(partes[1]);
                        Duration tiempoPrep = Duration.ofHours(horas).plusMinutes(minutos);
                        a.setTiempoPreparacion(tiempoPrep);
                    }catch(NumberFormatException e){
                        System.out.println("La hora no es valida");
                    }
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
        return a;
    }

    /**
     * @param criterio
     * @return
     * @throws DAOExceptions
     */
    @Override
    public List<Articulo> obtenerPorCriterio(String criterio)  {
        return null;
    }
}
