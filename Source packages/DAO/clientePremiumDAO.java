package DAO;

import modelo.ClientePremium;

import java.sql.SQLException;
public interface clientePremiumDAO {

    void insertarclientePremium (ClientePremium clientepremium) throws SQLException;
    // Eliminamos por el campo índice que es mail
    void eliminarclientePremium (String mail) throws SQLException;

    void actualizarclientePremium (ClientePremium clientepremium) throws SQLException;

    void listarclientePremium (ClientePremium clientepremium) throws SQLException;

}
