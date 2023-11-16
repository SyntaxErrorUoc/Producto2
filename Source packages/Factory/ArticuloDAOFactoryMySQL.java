package Factory;

import ConexionMySQL.DAOExceptions;
import DAO.ArticuloDAO;
import modelo.Articulo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class ArticuloDAOFactoryMySQL implements ArticuloDAO {
    final String INSERT  ="INSERT INTO articulo (CP, Descripcion, Precio, TiempoPreparacion) VALUES (?,?,?,?)";
    final String UPDATE = "UPDATE articulo SET Descripcion = ?, Precio = ?, TiempoPreparacion = ? WHERE CP=?";
    final String DELETE  ="DELETE FROM articulo WHERE CP=?;";
    final String GETONE = "SELECT * FROM articulo WHERE CP=?";
    final String GETALL  = "SELECT * FROM articulo;";

    private Connection conn;

    public ArticuloDAOFactoryMySQL(Connection conn){
        this.conn = conn;
    }


    @Override
    public void insertar(Articulo a) {
        PreparedStatement stat = null;

        try {
            stat = this.conn.prepareStatement(INSERT);
            stat.setString(1,a.getCodigo());
            stat.setString(2,a.getDescripcion());
            stat.setDouble(3,a.getPrecio());
            LocalTime time  = LocalTime.MIDNIGHT.plus(a.getTiempoPreparacion());
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

    @Override
    public void modificar(Articulo a) {
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

    @Override
    public void eliminar(String cp) {
        PreparedStatement stat = null;
        try{

            stat = this.conn.prepareStatement(DELETE);
            stat.setString(1,cp);
            if (stat.executeUpdate() == 0 ){

                throw new DAOExceptions("No se encontró el código " + cp + " para eliminar.") ;
            }

        }catch (SQLException e) {
            new DAOExceptions("Error e SQL", e);
        }
    }

    @Override
    public ArrayList<Articulo> obtenerTodos() {

        ArrayList<Articulo> listaA = new ArrayList<>();
        PreparedStatement stat = null;
        try{

            stat = this.conn.prepareStatement(GETALL);
            ResultSet rs = stat.executeQuery();

            while(rs.next()){

                String id = rs.getString("CP");
                String desc = rs.getString("Descripcion");
                Double precio = rs.getDouble("Precio");
                String tiempoprep = rs.getString("TiempoPreparacion");
                LocalTime tiempo = LocalTime.parse(tiempoprep);
                int horas = tiempo.getHour();
                int minutos = tiempo.getMinute();
                Duration tiempoP = Duration.ofHours(horas).plusMinutes(minutos);

                Articulo a = new Articulo(id,desc,precio,tiempoP);
                listaA.add(a);
            }



        }catch(SQLException e){
            new DAOExceptions("Fallo al realizar la conexion ", e);
        }

        return listaA;
    }

    @Override
    public Articulo obtenerUno(String id) {
        PreparedStatement stat = null;
        String hora;
        Articulo a = new Articulo();

        try {
            stat = conn.prepareStatement(GETONE);
            stat.setString(1,id);
            ResultSet rs = stat.executeQuery();

            while(rs.next() ){
                a.setCodigo( rs.getString("cp"));
                a.setDescripcion(rs.getString("Descripcion"));
                a.setPrecio(rs.getDouble("Precio"));
                hora = rs.getString("TiempoPreparacion");
                LocalTime tiempo = LocalTime.parse(hora);
                int horas = tiempo.getHour();
                int minutos = tiempo.getMinute();
                Duration tiempoP = Duration.ofHours(horas).plusMinutes(minutos);
                a.setTiempoPreparacion(tiempoP);
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

    @Override
    public ArrayList<Articulo> obtenerPorCriterio(String columna, String criterio) {
        return null;
    }
}