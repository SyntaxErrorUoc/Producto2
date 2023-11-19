package DAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Interfaz para DAO ("Data Access Object") Proporciona la capa de abstracción entre la lógica de la aplicación
 * y la fuente de datos. Su proposito es encapsular los detalles de acceso a la BBDD y proporcionar la interfaz
 * simple y consistente para interactuar con los datos.
 * @param <T>
 * @param <k>
 */
public interface DAO<T,k> {

    void insertar(T a) ;
    void modificar (T a);
    void eliminar(k a) ;
    ArrayList<T> obtenerTodos() ;
    T obtenerUno(k id);
    ArrayList<T> obtenerPorCriterio(k columna,k criterio);
}