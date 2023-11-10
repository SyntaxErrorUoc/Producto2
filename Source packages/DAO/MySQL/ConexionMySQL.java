package DAO.MySQL;

import java.sql.*;

public class ConexionMySQL {

    // Librería de MySQL

    private String driver = "com.mysql.cj.jdbc.Driver";


    // Nombre de la base de datos
    private String database = "OnlineStore";

    // Host
    private String hostname = "localhost";

    // Puerto
    private String port = "3306";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    private String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";

    // Nombre de usuario
    private String username = "root";

    // Clave de usuario
    private String password = "ubuntu";

    public Connection conectarMySQL() throws SQLException {
        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

}
