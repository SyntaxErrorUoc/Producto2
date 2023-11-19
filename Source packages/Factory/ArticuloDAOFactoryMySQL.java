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

/**
 * Clase tipo Factory para crear las instancias concretas de métodos DAO.
 */
public class ArticuloDAOFactoryMySQL implements ArticuloDAO {
    final String INSERT  ="INSERT INTO articulo (CP, Descripcion, Precio, TiempoPreparacion) VALUES (?,?,?,?)";
    final String UPDATE = "UPDATE articulo SET Descripcion = ?, Precio = ?, TiempoPreparacion = ? WHERE CP=?";
    final String DELETE  ="DELETE FROM articulo WHERE CP=?;";
    final String GETONE = "SELECT * FROM articulo WHERE CP=?";
    final String GETALL  = "SELECT * FROM articulo;";

    private Connection conn;

    /**
     * Constructor de la clase. Establece la conexión
     * @param conn Tipo Connection. Recibe el tipo de conexión a la BBDD.
     */
    public ArticuloDAOFactoryMySQL(Connection conn){
        this.conn = conn;
    }

    /**
     * Método para insertar un artículo dentro de la tabla "articulo" de la BBDD
     * @param a Tipo Articulo. Datos del artículo.
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
     * Método que permite modificar un Artículo existente en la tabla "articulo".
     * @param a Tipo Articulo. Datos del artículo para modificar.
     */
    @Override
    public void modificar(Articulo a) {
        PreparedStatement stat = null;

        try {
            stat = this.conn.prepareStatement(UPDATE);
            stat.setString(1,a.getDescripcion());
            stat.setDouble(2,a.getPrecio());
            LocalTime time  = LocalTime.MIDNIGHT.plus(a.getTiempoPreparacion());
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
     * Método para eliminar un artículo de la tabla "articulo" de la BBDD
     * @param cp Tipo String. Contiene el código del producto a eliminar.
     */
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

    /**
     * Método que devuelve un ArrayList con todos los artículos existentes en la tabla "articulo".
     * @return ArrayList de todos los artículos.
     */
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

    /**
     * Método para obtener la información de un artículo de la tabla "articulo".
     * @param id Tipo String. Contiene el código del producto.
     * @return Tipo Articulo. Contiene la información del producto seleccionado.
     */
    @Override
    public Articulo obtenerUno(String id) {
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

    /**
     * Método para obtener una lista con los artículo seleccionados por un criterio en concreto.
     * @param columna Tipo String. Contiene el nombre de la columna a aplicar el criterio.
     * @param criterio Tipo String. Contiene el valor por el que queremos filtrar la tabla "articulo".
     * @return ArrayList con los artículos que coincidan en la búsqueda.
     */
    @Override
    public ArrayList<Articulo> obtenerPorCriterio(String columna, String criterio) {
        return null;
    }
}