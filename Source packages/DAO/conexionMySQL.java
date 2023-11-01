package DAO;

import java.sql.*;

public class conexionMySQL {
    private static Connection cnx = null;
    private static Statement sentencia;
    private static ResultSet resultado;
    private static String privateURL = "jdbc:mysql://localhost:3306/onlinestore";
    private static String privateDriver = "com.mysql.cj.jdbc.Driver";
    private static String privateUser = "root";
    private static String privatePass = "NT486Wak";


    public static Connection obtener() throws SQLException, ClassNotFoundException {
        if (cnx == null) {
            try {
                Class.forName( privateDriver );
                cnx = DriverManager.getConnection(privateURL, privateUser, privatePass );
            } catch (SQLException ex) {
                throw new SQLException(ex);
            } catch (ClassNotFoundException ex) {
                throw new ClassCastException(ex.getMessage());
            }
        }
        return cnx;
    }

    public static void cerrar() throws SQLException {
        try{
            if (cnx != null) {
                if (sentencia != null){
                    sentencia.close();
                }
                cnx.close();
            }
        } catch (SQLException e){
            throw new SQLException(e);
        }
    }

}
