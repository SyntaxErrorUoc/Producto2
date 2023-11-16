package DAO;

import modelo.Pedido;

import java.util.ArrayList;

public interface PedidoDAO extends DAO <Pedido,Integer> {


    ArrayList<Pedido> obtenerPorCriterio(Integer columna, String criterio);
}