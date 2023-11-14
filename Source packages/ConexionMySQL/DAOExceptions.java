package ConexionMySQL;

import java.sql.SQLException;

public class DAOExceptions extends SQLException {

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