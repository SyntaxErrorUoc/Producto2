package OnlineStore.modelo;

public class Datos {
    private ListaArticulos listaArticulos;
    private ListaClientes listaClientes;
    private ListaPedidos listaPedidos;


    public Datos (){
        listaArticulos = new ListaArticulos();
        listaClientes = new ListaClientes();
        listaPedidos = new ListaPedidos ();
    }

    public void addArticulo(articulo Articulo){

        this.listaArticulos.add(Articulo);
    }

    public void dropArticulo(int Indice){
        articulo T;
        T = listaArticulos.getAt(Indice);
        listaArticulos.borrar(T);
    }

    public void viewArticulos(){
        listaArticulos.mostrartodo();
    }


    public void dropcliente(int Indice){
        listaClientes.eliminarElemento(Indice);
    }
    public void addClientePremium(cliente_premium Cliente){
        this.listaClientes.add(Cliente);
    }

    public void addClienteEstandard(cliente_estandard Cliente){

        this.listaClientes.add(Cliente);
    }

    public void viewClientes(){
        System.out.println("-------------------------------------");
        System.out.println("| LISTADO DE CLIENTES COMPLETO       ");
        System.out.println("-------------------------------------\n");
        listaClientes.mostrartodo();
    }

    public void viewClientesPorTipo(String Tipo){
        System.out.println("-------------------------------------");
        System.out.println("| LISTADO DE CLIENTES TIPO : " + Tipo);
        System.out.println("-------------------------------------\n");
        for ( cliente C: listaClientes.getArrayList()){
            if(C.tipoCliente().equals(Tipo)){
                System.out.println(C.toString());
            }
        }
    }

    public void listaclienteindice(){
        System.out.println("| Índice\t Nombre Cliente \t Tipo Cliente");
        for (int i = 0; i < listaClientes.getSize(); i++){
            System.out.print("|" + i + "\t\t");
            System.out.print( " " + String.format("%-20s", listaClientes.getAt(i).getNombre()) + "\t");
            System.out.println( " " + String.format("%-20s",listaClientes.getAt(i).tipoCliente()) );
        }
    }

    public void listaarticuloindice(){
        System.out.println("Índice\t Artículo  \t Descripción");
        for (int i = 0; i < listaArticulos.getSize(); i++){
            System.out.print("|" + i + "\t\t");
            System.out.print(" " + String.format("%-10s",listaArticulos.getAt(i).getCodigo()) + "\t");
            System.out.println(" " + String.format("%-20s",listaArticulos.getAt(i).getDescripcion()) );
        }
    }

    public void addPedido(){

    }

    public void dropPedido(int Indice){
        listaArticulos.eliminarElemento(Indice);
    }


}
