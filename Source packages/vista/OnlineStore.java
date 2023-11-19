package vista;


import ConexionMySQL.DatabaseConnectionException;

import java.sql.SQLException;

public class OnlineStore {

	/**
	 * Método que inicializa la ejecución del programa.
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws DatabaseConnectionException
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException, DatabaseConnectionException {
		GestionOS gestion = new GestionOS();
		gestion.inicio();
		}
}

