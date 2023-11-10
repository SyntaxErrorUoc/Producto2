package controlador;

import DAO.MySQL.DAOExceptions;
import modelo.*;

import java.sql.SQLException;
import java.time.*;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SyntaxError
 * @version 2.0.1
 */
public class Controlador {
	private Datos datos;


    /**
     * Constructor de la clase vacio
     */
    public Controlador()  {

        this.datos = new Datos();

    }

    /**
     *
     * @param cp
     * @param desc
     * @param precio
     * @param tiempoPrepacion
     */
    public void addArticulo(String cp, String desc, double precio, Duration tiempoPrepacion) throws SQLException, ClassNotFoundException {

        Articulo a;
        a = new Articulo(cp, desc, precio, tiempoPrepacion);
        datos.agregarArticulo(a);

    }

    /**
     * Metodo para mostrar Articulo
     */
    public List<Articulo> mostrarArticulos(){
      return datos.obtenerArticulos();
    }

    public void eliminarArticulo(String cp) {
        datos.eliminarArticulo(cp);

    }
    public boolean articuloExiste(String cp) {

        boolean existe = false;
        if (datos.obtenerArticulo(cp).getCodigo().equals(cp)){
            existe = true;
            return existe;
        }
        return existe;
    }
    public Articulo mostrarArticulo(String cp){

        Articulo a = null;
        if (articuloExiste(cp)){
            a = datos.obtenerArticulo(cp);
        }
        return a;
    }

    /**
     * Metodo para añadir cliente sobrecargados
     * @param mail de tipo String
     * @param name de tipo String
     * @param dir de tipo String
     */
    public void addCliente(String mail, String name, String dir)  {

        ClienteEstandar stand = new ClienteEstandar(mail,name,dir);
        datos.agregarCliente(stand);


    }

    /**
     * Metodo para añadir cliente sobrecargados
     * @param mail de tipo String
     * @param name de tipo String
     * @param dir de tipo String
     * @param desc de tipo double
     */
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
        if (datos.obtenerCliente(email).getCorreoElectronico().equals(email)){
            existe = true;
        }
        return existe;
    }

    public void eliminarCliente(String correo){
        if (emailExiste(correo)){
            datos.eliminarCliente(correo);
        }

    }


    public void modificarCliente(String mail, String nombre, String direccion){

        Cliente c = new ClienteEstandar(mail,nombre,direccion);
        if (emailExiste(mail)){
            datos.modificarCliente(c);
        }

    }

    /**
     * Metdo para mostrar cliente
     */
    public List<Cliente> mostrarCliente(){

        return datos.mostrarTodosLosClientes();

    }

    /**
     *
     * @param tipo
     */
    public void mostratClientesPorTipo(String tipo){

    }


    /**
     * Metdo para mostrar cliente Premium
     */
    /* TODO
    /*
    public void mostrarClientePremium(){
        try{
            List<Cliente> clientePremium = datos.mostrarClientesPremium();
            for (Cliente cliente : clientesPremium){
                System.out.println(cliente);
            }
        } catch (DAOExceptions e){
            e.printStackTrace();
        }

    }
*/
    /**
     * Metdo para mostrar Estandar
     */
    /*
    public void mostrarClienteStandard(){
        for( Cliente cliente: datos.getListaClientes().getLista()){
            if (cliente.tipoCliente().equals("Estandar")){
                System.out.println(cliente);
            }
        }
    }
*/

    //Metodos de pedidos
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

    /**
     * Metodo para comprobar si un numero existe
     * @param numeroPedido recibe un int
     * @return devuelve un Boolean
     */
    public boolean pedidoExiste(int numeroPedido){

        Boolean existe = false ;

        if(datos.obtenerUnPedido(numeroPedido).getNumeroPedido()== numeroPedido){
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

    /**
     * Metodo para mostrarPedido
     * @param enviado recibe un boolean
     */
    public Lista<Pedido> mostrarPedido(boolean enviado){

        Lista<Pedido> listaTemp = null;
        List<Pedido> lista = datos.obtenerPedidos();

        for (Pedido p: lista){
            if (p.isEnviado()){
                listaTemp.agregar(p);
            }else{
                listaTemp.agregar(p);
            }
        }
        return listaTemp;
    }

    /**
     * Metodo para mostrar pedido sobrecargados
     * @param enviado recibe un boolean
     * @param mail recibe un string
     */

    /**
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
    **/
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
        fechaHoraActual = fechaHoraActual.plus((TemporalAmount) tiempoPrep);
        System.out.println(fechaHoraActual);
        if (now.isBefore(fechaHoraActual)){
            valida = false;
        }else{
            valida = true;
        }
        return valida;
    }


    /**
     * Metodo para comprobar si un email existe
     * @param email recibe un String
     * @return devuelve un Boolean
     */


    /**
     * Metodo para comprobar si un articulo existe
     * @param cp recibe un String
     * @return devuelve un Boolean
     */


}
