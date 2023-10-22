package controlador;

import modelo.*;
import vista.GestionOS;

import java.util.ArrayList;

public class Controlador {
	private Datos datos;


    // Constructor
    public Controlador() {

        this.datos = new Datos();

    }
    public Articulo crearInstanciaArticulo(String cp, String desc, double precio){

        Articulo art;
        art = new Articulo(cp,desc,precio);
        return  art;
    };

    public void addArticulo(Articulo a){
        datos.agregarArticulo(a);

    };

    public void mostrarArticulos(){
        datos.obtenerArticulo();
    }



    public void addCliente(String mail, String name, String dir){

        ClienteEstandar stand;
        stand = new ClienteEstandar(mail,name,dir);
        datos.agregarCliente(stand);

    }
    public void addCliente(String mail, String name, String dir,double desc){

        ClientePremium prem;
        prem = new ClientePremium(mail,name, dir, desc);
        datos.agregarCliente(prem);

    }

    public void mostrarCliente(){
        datos.obtenerCliente();
    }
    public void mostrarClientePremium(){
        for( Cliente cliente: datos.getListaClientes().getLista()){
            if (cliente.tipoCliente().equals("Premium")){
                System.out.println(cliente);
            }
        }

    };
    public void mostrarClienteStandard(){
        for( Cliente cliente: datos.getListaClientes().getLista()){
            if (cliente.tipoCliente().equals("Estandar")){
                System.out.println(cliente);
            }
        }
    };

    public void addPedido(String nombreCliente, String codigoArticulo, int numeroPedido, String fechaHoraPedido,int cantidad,
                          boolean enviado,double costeEnvio){
       Articulo art;
       Cliente cl;
       Pedido pd;
       int indexArt;
       int  indexCl;
       indexArt = datos.devolverIndiceArticulo(codigoArticulo);
       art = datos.getListaArticulos().getLista().get(indexArt);
       indexCl = datos.devolverIndiceCliente(nombreCliente);
       cl = datos.getListaClientes().getLista().get(indexCl);
       pd = new Pedido(numeroPedido,fechaHoraPedido,cl,art,cantidad,enviado,costeEnvio);
       datos.agregarPedido(pd);
       System.out.println(pd);

    }
    public void deletePedido(int np){
        Pedido ped;
        int indicePedido;
        indicePedido = datos.devolverIndicePedido(np);

        ped = datos.getListaPedidos().getLista().get(np-1);
        datos.eliminarPedido(ped);
        System.out.println(datos.getListaPedidos().getLista());
    }
}
