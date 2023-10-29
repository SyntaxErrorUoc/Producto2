package controlador;
import modelo.*;
import java.time.*;

public class Controlador {
	private Datos datos;


    // Constructor
    public Controlador() {

        this.datos = new Datos();

    }
    public void addArticulo(String cp, String desc, double precio, Duration timempoPrepacion){

        Articulo a;
        a = new Articulo(cp,desc,precio, timempoPrepacion);
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
    public void addPedido(String nombreCliente, String codigoArticulo, int numeroPedido, LocalDateTime fechaHoraPedido,int cantidad,
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
    public Boolean deletePedido(int np){
        Pedido ped;

        Boolean eliminado =false;

        ped = datos.getListaPedidos().getLista().get(np-1);
        if (!comprobarPreparacion(ped.getFechaHoraPedido(),ped.getArticulo().getTiempoPreparacion())){
            datos.eliminarPedido(ped);
            eliminado = true;
        }
        return eliminado;

    }
    public void mostrarPedido(boolean enviado){

        if (enviado){
            for(Pedido pedido:datos.getListaPedidos().getLista()){

                if (pedido.pedidoEnviado()){
                        System.out.println(pedido);

                }
            }

        }else{

                for(Pedido pedido:datos.getListaPedidos().getLista()) {
                    if (!pedido.pedidoEnviado()) {
                        System.out.println(pedido);
                    }

            }
        }
    }
    public void mostrarPedido(boolean enviado, String  mail){
        int indice;
        if (enviado){
            for(Pedido pedido:datos.getListaPedidos().getLista()){
                if (pedido.pedidoEnviado()){
                    if (pedido.getCliente().getCorreoElectronico().equals(mail)){
                        System.out.println(pedido);
                    }
                }
            }
        }else{

            for(Pedido pedido:datos.getListaPedidos().getLista()) {
                if (!pedido.pedidoEnviado()) {
                    if (pedido.getCliente().getCorreoElectronico().equals(mail)){
                        System.out.println(pedido);
                    }
                }
            }
        }
    }

    public double obtenerDescuento(){
       return 0.45;
    };
    public boolean comprobarPreparacion(LocalDateTime fechaHoraActual, Duration tiempoPrep){
        LocalDateTime now = LocalDateTime.now();
        boolean valida = false;
        fechaHoraActual = fechaHoraActual.plus(tiempoPrep);
        System.out.println(fechaHoraActual);
        if (now.isBefore(fechaHoraActual)){
            valida = false;
        }else{
            valida = true;
        }
        return valida;
    }
}
