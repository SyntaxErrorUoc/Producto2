package ConexionMySQL;

public class DatabaseConnectionException extends Exception{

    /**
     * Método para gestionar las excepciones que se producen en la conexión a la BBDD.
     * @param message
     */
    public DatabaseConnectionException(String message) {
        super(message);
    }
}
