package vista;


import ConexionMySQL.DatabaseConnectionException;

import java.sql.SQLException;

public class OnlineStore {


	public static void main(String[] args) throws SQLException, ClassNotFoundException, DatabaseConnectionException {
		GestionOS gestion = new GestionOS();
		gestion.inicio();
		}
}
