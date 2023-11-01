package DAO;

import modelo.Articulo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static DAO.conexionMySQL.obtener;

public class articuloImplementacionDAO implements articuloDAO {

    private static final String INSERTAR ="INSERT INTO articulo ('CP', 'Descripcion', 'Precio', 'TiempoPreparacion') VALUES (?,?,?,?);";
    private static final String ELIMINAR ="DELETE FROM articulo WHERE 'CP'=?;";
    private static final String CARGAR= "SELECT * FROM articulo WHERE 'CP'='?';";
    private static final String LISTAR = "SELECT * FROM articulo;";

    @Override
    public void insertararticulo(Articulo articulo) throws SQLException {
        try {
            PreparedStatement stmt = obtener().prepareStatement(INSERTAR);
            stmt.setString (1, articulo.getCodigo());
            stmt.setString (2, articulo.getDescripcion());
            stmt.setDouble (3, articulo.getPrecio());
            stmt.setTime (4, articulo.getTiempoPreparacion());
            int i = stmt.executeUpdate(INSERTAR);
            if (i > 0) {
                System.out.println("ROW INSERTED");
            } else {
                System.out.println("ROW NOT INSERTED");
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void eliminararticulo(String cp) throws SQLException {
        try {
            PreparedStatement stmt = obtener().prepareStatement(ELIMINAR);
            stmt.setString (1, cp);
            int i = stmt.executeUpdate(ELIMINAR);
            if (i > 0) {
                System.out.println("ROW ELIMINATED");
            } else {
                System.out.println("ROW NOT ELIMINATED");
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void actualizararticulo(Articulo articulo) throws SQLException {

    }

    @Override
    public void listararticulo(Articulo articulo) throws SQLException {

    }
}
