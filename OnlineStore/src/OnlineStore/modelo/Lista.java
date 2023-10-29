package OnlineStore.modelo;

import java.util.ArrayList;

public class Lista<T> {

    private ArrayList < T > lista = new ArrayList < T >();


    void mostrarlista(){
        for (T elemento : this.lista) {
            System.out.println(this.lista);
        }
    }

    public ArrayList<T> getArrayList() {
        ArrayList<T> arrlist = new ArrayList<>(lista);
        return arrlist;
    }

    public void mostrartodo(){
        System.out.println(this.lista);
    }

    public int getSize(){
        // Realizar
        return this.lista.size();
    }

    public T getAt (int posicion){
        return this.lista.get(posicion);
    }

    public void eliminarElemento(int posicion) {
        if (posicion >= 0 && posicion < this.lista.size()) {
            this.lista.remove(posicion);
        } else {
            System.out.println("La posición no es válida.");
            throw new IllegalArgumentException("La posición no es válida.");
        }
    }

    public void clear(){
        try {
            this.lista.clear();
        } catch (Exception e) {
            System.out.println("ERROR : " + e.getMessage());
        }
    }

    public void borrar (T t){
        for (int i=0; i< this.lista.size(); i++){
            if (lista.contains(t)){
                try {
                    lista.remove(t);
                } catch (Exception e) {
                    System.out.println("Se produjo una excepción al eliminar el elemento a la lista: " + e.getMessage());
                }
            }
        }
    }

    public void add(T objeto){

        try {
            this.lista.add(objeto);
        } catch (Exception e) {
            System.out.println("Se produjo una excepción al agregar el elemento a la lista: " + e.getMessage());
        }

    }


}
