package OnlineStore.modelo;

public class ListaPedidos extends Lista<pedido> {

    private Lista<pedido> lpedidos;

    private void addPedido(pedido Pedido){
        lpedidos.add(Pedido);
    }

}
