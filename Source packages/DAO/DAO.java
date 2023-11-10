package DAO;
import DAO.MySQL.DAOExceptions;

import java.sql.Connection;
import java.util.List;

public interface DAO<T,k> {

    void insertar(T a) ;
    void modificar (T a);
    void eliminar(k a) ;

    List<T> obtenerTodos() ;

    T obtenerUno(k id);
    List<T> obtenerPorCriterio(String criterio);
}