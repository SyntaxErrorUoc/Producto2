package DAO;
import DAO.MySQL.DAOExceptions;

import java.util.List;

public interface DAO<T,k> {

    void insertar(T a) throws DAOExceptions;
    void modificar (T a) throws  DAOExceptions;
    void eliminar(k a) throws  DAOExceptions;

    List<T> obtenerTodos() throws  DAOExceptions;

    T obtener(k id) throws DAOExceptions;
}