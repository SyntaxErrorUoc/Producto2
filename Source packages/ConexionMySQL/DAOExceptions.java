package ConexionMySQL;

import java.sql.SQLException;

public class DAOExceptions extends SQLException {

    /**
     * Método para gestionar las excepciones que se producen durante la conexión a la BBDD.
     * @param reason
     */
    public DAOExceptions(String reason) {
        super(reason);
    }

    public DAOExceptions(Throwable cause) {
        super(cause);
    }

    public DAOExceptions(String reason, Throwable cause) {
        super(reason, cause);
    }
}