package controlador;
import modelo.*;
import java.time.*;
/**
 * @author SyntaxError
 * @version 2.0.1
 */
public class Controlador {
	private Datos datos;


    /**
     * Constructor de la clase vacio
     */
    public Controlador() {

        this.datos = new Datos();

    }

    /**
     *
     * @param cp
     * @param desc
     * @param precio
     * @param timempoPrepacion
     */
    public void addArticulo(String cp, String desc, double precio, Duration timempoPrepacion){

        Articulo a;
        a = new Articulo(cp,desc,precio, timempoPrepacion);
        datos.agregarArticulo(a);

    }

    /**
     * Metodo para mostrar Articulo
     */
    public void mostrarArticulos(){
        datos.obtenerArticulo();
    }

    /**
     * Metodo para añadir cliente sobrecargados
     * @param mail de tipo String
     * @param name de tipo String
     * @param dir de tipo String
     */
    public void addCliente(String mail, String name, String dir){

        if(emailExiste(mail)){
            System.out.println("Error: El correo electrónico " + mail + " ya existe.");
            return;
        }

        ClienteEstandar stand;
        stand = new ClienteEstandar(mail,name,dir);
        datos.agregarCliente(stand);

    }

    /**
     * Metodo para añadir cliente sobrecargados
     * @param mail de tipo String
     * @param name de tipo String
     * @param dir de tipo String
     * @param desc de tipo double
     */
    public void addCliente(String mail, String name, String dir,double desc){

        if(emailExiste(mail)){
            System.out.println("Error: El correo electrónico " + mail + " ya existe.");
            return;
        }

        ClientePremium prem;
        prem = new ClientePremium(mail,name, dir, desc);
        datos.agregarCliente(prem);

    }

    /**
     * Metdo para mostrar cliente
     */
    public void mostrarCliente(){
        datos.obtenerCliente();
    }

    /**
     * Metdo para mostrar cliente Premium
     */
    public void mostrarClientePremium(){
        for( Cliente cliente: datos.getListaClientes().getLista()){
            if (cliente.tipoCliente().equals("Premium")){
                System.out.println(cliente);
            }
        }

    }

    /**
     * Metdo para mostrar Estandar
     */
    public void mostrarClienteStandard(){
        for( Cliente cliente: datos.getListaClientes().getLista()){
            if (cliente.tipoCliente().equals("Estandar")){
                System.out.println(cliente);
            }
        }
    }

    /**
     * Metdo para añadir pedido
     * @param nombreCliente de tipo String
     * @param codigoArticulo de tipo String
     * @param numeroPedido de tipo int
     * @param fechaHoraPedido de tipo LocalDateTime
     * @param cantidad de tipo int
     * @param enviado de tipo boolean
     * @param costeEnvio de tipo double
     */
    public void addPedido(String nombreCliente, String codigoArticulo, int numeroPedido, LocalDateTime fechaHoraPedido,int cantidad,

                       boolean enviado,double costeEnvio){

        if(pedidoExiste(numeroPedido)){
            System.out.println("Error: El número de pedido " + numeroPedido + " ya existe.");
            return;
        }

        try {

            Articulo art;
            Cliente cl;
            Pedido pd;
            int indexArt;
            int indexCl;
            indexArt = datos.devolverIndiceArticulo(codigoArticulo);
            art = datos.getListaArticulos().getLista().get(indexArt);
            indexCl = datos.devolverIndiceCliente(nombreCliente);
            cl = datos.getListaClientes().getLista().get(indexCl);
            pd = new Pedido(numeroPedido, fechaHoraPedido, cl, art, cantidad, enviado, costeEnvio);
            datos.agregarPedido(pd);
            System.out.println(pd);
        } catch (IndexOutOfBoundsException e){
            System.out.println("Error: Índice fuera de rango.");
        } catch (NullPointerException e){
            System.out.println("Error: Obleto nulo inesperado.");
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metodo para borrar Pedido
     * @param np de tipo int
     * @return devuelve un tipo boolean
     */
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

    /**
     * Metodo para mostrarPedido
     * @param enviado recibe un boolean
     */
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

    /**
     * Metodo para mostrar pedido sobrecargados
     * @param enviado recibe un boolean
     * @param mail recibe un string
     */
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

    /**
     * Metodo para obtener el descuento
     * @return devuelve un double
     */
    public double obtenerDescuento(){
       return 0.45;
    }

    /**
     * Metodo para comprobarPreparacion
     * @param fechaHoraActual de tipo LocalDateTime
     * @param tiempoPrep de tipo Dutration
     * @return devuelve un boolean
     */
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

    private boolean pedidoExiste(int numeroPedido){
        for (Pedido pedido: datos.getListaPedidos().getLista()){
            if(pedido.getNumeroPedido() == numeroPedido){
                return true;
            }
        }
        return false;
    }
    private boolean emailExiste(String email) {
        for (Cliente cliente : datos.getListaClientes().getLista()) {
            if (cliente.getCorreoElectronico().equals(email)) {
                return true;
            }
        }
        return false;
    }

}
