package ConexionMySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {

    // Librería de MySQL
    private static String driver = "com.mysql.cj.jdbc.Driver";
    //private static String driver = "com.mysql.cj.jdbc.Driver";

    // Nombre de la base de datos
    private static String database = "OnlineStore";

    // Host
    private static String hostname = "localhost";

    // Puerto
    private static String port = "3306";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?allowPublicKeyRetrieval=true&useSSL=false";

    // Nombre de usuario
    private static String username = "root";

    // Clave de usuario
    private static String password = "ubuntu";

    public static Connection conectarMySQL() throws SQLException {
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
