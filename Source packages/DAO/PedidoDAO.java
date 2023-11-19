package DAO;

import modelo.Pedido;

import java.util.ArrayList;

/**
 * Interfaz de PedidoDAO que extiende de DAO
 */
public interface PedidoDAO extends DAO <Pedido,Integer> {
    ArrayList<Pedido> obtenerPorCriterio(Integer columna, String criterio);
}