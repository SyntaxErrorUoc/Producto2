package DAO.MySQL;

import java.sql.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

import modelo.Articulo;
import DAO.articuloDAO;

public class articuloMySQLDAO implements articuloDAO {

    final String INSERT  ="INSERT INTO articulo ('CP', 'Descripcion', 'Precio', 'TiempoPreparacion') VALUES ('?','?',?,'?');";
    final String UPDATE = "UPDATE articulo SET Descripcion = ?, Precio = ?, TiempoPreparacion = ? WHERE cp = ?";
    final String DELETE  ="DELETE FROM articulo WHERE 'CP'=?;";
    final String GETONE = "SELECT * FROM articulo WHERE 'CP'='?';";
    final String GETALL  = "SELECT * FROM articulo;";

    private Connection conn;

    public articuloMySQLDAO(Connection Conn){
        this.conn = Conn;
    }

    /**
     * @param a
     */
    @Override
    public void insertar(Articulo a) throws DAOExceptions {

        PreparedStatement stat = null;

        try {
            stat = conn.prepareStatement(INSERT);
            stat.setString(1,a.getCodigo());
            stat.setString(2,a.getDescripcion());
            stat.setDouble(3,a.getPrecio());
            LocalTime time  = LocalTime.MIDNIGHT.plus(a.getTiempoPreparacion());
            //TODO
            java.sql.Time sqlTime = java.sql.Time.valueOf(time);
            stat.setTime(4,sqlTime);
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
     * @param a
     */
    @Override
    public void modificar(Articulo a) throws DAOExceptions {
        PreparedStatement stat = null;

        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1,a.getDescripcion());
            stat.setDouble(2,a.getPrecio());
            LocalTime time  = LocalTime.MIDNIGHT.plus(a.getTiempoPreparacion());
            //TODO
            java.sql.Time sqlTime = java.sql.Time.valueOf(time);
            stat.setTime(3,sqlTime);
            stat.setString(4,a.getCodigo());

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
     * @param a
     */
    @Override
    public void eliminar(Articulo a) throws DAOExceptions{
        PreparedStatement stat = null;
        try{
            stat = conn.prepareStatement(DELETE);
            stat.setString(1,a.getCodigo());
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
    public List<Articulo> obtenerTodos() {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Articulo obtener(String id) throws DAOExceptions {


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
