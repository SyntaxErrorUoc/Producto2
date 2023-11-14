package DAO;

import java.util.ArrayList;
import java.util.List;

public interface DAO<T,k> {

    void insertar(T a) ;
    void modificar (T a);
    void eliminar(k a) ;
    ArrayList<T> obtenerTodos() ;
    T obtenerUno(k id);
    ArrayList<T> obtenerPorCriterio(k columna,k criterio);
}