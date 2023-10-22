package OnlineStore.modelo;

import java.util.ArrayList;

public class clientelistado {

    private ArrayList<cliente> ARR_Cliente;

    public clientelistado(){
        ArrayList<cliente> ARR_Cliente = new ArrayList<>();
    }

    void mostrarpedido(String NIF){
        for (int i =0; i<ARR_Cliente.size(); i++){
            if (NIF == ARR_Cliente.get(i).getNif()) {
                System.out.print("Índice (" + i + ") : " + ARR_Cliente.get(i));
            }
        }
    }

    void listarpedidos(){
        for (int i =0; i<ARR_Cliente.size(); i++){
            System.out.print("Índice (" + i + ") : " + ARR_Cliente.get(i));
        }
    }

    void eliminarcliente(int Indice){
        ARR_Cliente.remove(Indice);
    }

    void insertarcliente(cliente Cliente){
        ARR_Cliente.add(Cliente);
    }


}
