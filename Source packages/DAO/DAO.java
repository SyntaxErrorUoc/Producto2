package DAO;
import DAO.MySQL.DAOExceptions;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T,k> {

    void insertar(T a) throws DAOExceptions;
    void modificar (T a) throws  DAOExceptions;
    void eliminar(k a) throws SQLException;

    List<T> obtenerTodos() throws  DAOExceptions;

    T obtener(k id) throws DAOExceptions;
    List<T> obtenerPorCriterio(String criterio) throws DAOExceptions;
}