package controlador;

import modelo.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SyntaxError
 * @version 2.0.1
 */
public class Controlador {
    private Datos datos;

    public Controlador()  {

        this.datos = new Datos();

    }

    public void addArticulo(String cp, String desc, double precio, Duration tiempoPrepacion)  {

        Articulo a;
        a = new Articulo(cp, desc, precio, tiempoPrepacion);
        if (a.getCodigo() != null) {
            datos.agregarArticulo(a);
        }

    }

    public void modArticulo(String cp, String desc, double precio, Duration tiempoPrepacion){

        Articulo a ;
        Articulo nuevoA = new Articulo(cp,desc,precio,tiempoPrepacion);
        a = datos.obtenerArticulo(cp);
        if (articuloExiste(cp)){
            datos.modificarArticulo(nuevoA);
        }

    }

    public ArrayList<Articulo> mostrarArticulos(){
        return datos.obtenerArticulos();
    }

    public void eliminarArticulo(String cp) {
        datos.eliminarArticulo(cp);

    }

    public Articulo mostrarArticuloIndividual(String cp){

        if (datos.obtenerArticulo(cp)!= null) {
            return datos.obtenerArticulo(cp) ;
        }
        return null;
    }
    public boolean articuloExiste(String cp) {

        boolean existe = false;
        if (datos.obtenerArticulo(cp)!= null){

            existe = true;
            return existe;
        }
        return existe;
    }

    // metodos para gestioanr cliente


    public void addCliente(String mail, String name, String dir)  {

        ClienteEstandar stand = new ClienteEstandar(mail,name,dir);
        datos.agregarCliente(stand);


    }


    public void addCliente(String mail, String name, String dir,double desc)  {

        if(emailExiste(mail)){
            System.out.println("Error: El correo electrónico " + mail + " ya existe.");
            return;
        }

        ClientePremium prem = new ClientePremium(mail,name, dir, desc);
        datos.agregarCliente(prem);


    }

    public boolean emailExiste(String email) {
        boolean existe = false;
        if (datos.obtenerCliente(email) != null){
            existe = true;
        }
        return existe;
    }

    public Boolean eliminarCliente(String correo){
        Boolean existe = true;
        if (emailExiste(correo)){
            datos.eliminarCliente(correo);
        }else{
            existe =false;
        }
        return existe;
    }

    public void modificarCliente(String mail, String nombre, String direccion){

        Cliente c = new ClienteEstandar(mail,nombre,direccion);
        if (emailExiste(mail)){
            datos.modificarCliente(c);
        }

    }
    public void modificarCliente(String mail, String nombre, String direccion, double descuento){

        Cliente c = new ClientePremium( mail,nombre,direccion,descuento);
        if (emailExiste(mail)){
            datos.modificarCliente(c);
        }

    }

    public ArrayList<Cliente> mostrarCliente(){

        return datos.mostrarTodosLosClientes();

    }

    public ArrayList<Cliente> mostrarClientesPorTipo(String columna,String tipo){

        ArrayList<Cliente> lista;
        lista = datos.mostrarPorTipo(columna,tipo);

        return lista;


    }
    public Cliente buscarCliente(String mail){
        Cliente c;
        c = datos.obtenerCliente(mail);
        return c;
    }


    //Metodos de pedidos



    public boolean pedidoExiste(int numeroPedido){

        Boolean existe = false ;

        if(datos.obtenerUnPedido(numeroPedido) !=null){
            existe = true;
        }
        return existe;
    }

    public void addPedido(String mail, String codigoArticulo, int numeroPedido, LocalDateTime fechaHoraPedido,int cantidad,
                          boolean enviado,double costeEnvio){

        Cliente c = datos.obtenerCliente(mail) ;
        Articulo a = datos.obtenerArticulo(codigoArticulo);
        Pedido p = new Pedido(numeroPedido,fechaHoraPedido,c,a,cantidad,enviado,costeEnvio);

        datos.agregarPedido(p);


    }

    /**
     * Metodo para borrar Pedido
     * @param np de tipo int
     * @return devuelve un tipo boolean
     */
    public Boolean deletePedido(int np){

        boolean eliminado = false;
        if (pedidoExiste(np)){

            datos.eliminarPedido(np);
            eliminado = true;
        }
        return eliminado;
    }

    public ArrayList<Pedido> obtenerPedidos(boolean enviado, String mail) {
        ArrayList<Pedido> lista = new ArrayList<>();

        for (Pedido ped : datos.obtenerPedidos()) {

            if (enviado == ped.pedidoEnviado() ) {
                if (ped.getCliente().getCorreoElectronico().equals(mail)){
                    lista.add(ped);
                }else{
                    lista.add(ped);
                }
            }
        }

        return lista;
    }

    /**
     * Metodo para mostrarPedido
     * @param pedido recibe un boolean
     */
    public Pedido mostrarPedido(int pedido){

        if(datos.obtenerUnPedido(pedido)!=null){
            return datos.obtenerUnPedido(pedido);
        }
        return null;
    }

    public ArrayList<Pedido> MostrarTodosLosPedidos(){
        return datos.obtenerPedidos();
    }



    public Pedido obtenerPedido( int  cp){

        Pedido pedido = null;

        for(Pedido ped:datos.obtenerPedidos()){

            if(ped.getNumeroPedido() == cp){
                return ped;
            }
        }

        return pedido;
    }

    public double obtenerDescuento(){
        return 0.45;
    }

    public boolean comprobarPreparacion(LocalDateTime fechaHoraActual, Duration tiempoPrep){
        LocalDateTime now = LocalDateTime.now();
        boolean valida ;
        fechaHoraActual = fechaHoraActual.plus(tiempoPrep);
        if (now.isBefore(fechaHoraActual)){
            valida = false;
        }else{
            valida = true;
        }
        return valida;
    }

}