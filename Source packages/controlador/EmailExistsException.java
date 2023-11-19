package controlador;

public class EmailExistsException extends Exception {

    /**
     * Método para controlar la excepción en caso de existir el mail.
     * @param message
     */
    public EmailExistsException(String message) {
        super(message);
    }
}
