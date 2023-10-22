package OnlineStore.modelo;

import java.util.ArrayList;

public class pedidolistado {
    private ArrayList<pedido> ARR_Pedido;

    public pedidolistado(){
        ArrayList<articulo> ARR_Pedido = new ArrayList<>();
    }

    void mostrarpedido(int numeropedido){
        for (int i =0; i<ARR_Pedido.size(); i++){
            if (numeropedido == ARR_Pedido.get(i).getNumpedido()) {
                System.out.println("Índice (" + i + ") : " + ARR_Pedido.get(i));
            }
        }
    }

    void listarpedidos(){
        for (int i =0; i<ARR_Pedido.size(); i++){
            System.out.println("Índice (" + i + ") : " + ARR_Pedido.get(i));
        }
    }

    void eliminarpedido(int Indice){
        ARR_Pedido.remove(Indice);
    }

    void insertarpedido(pedido Pedido){
        ARR_Pedido.add(Pedido);
    }

}
