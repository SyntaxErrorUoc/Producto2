package controlador;

import ConexionMySQL.DatabaseConnectionException;
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

    /**
     * Método Controlador que es parte del modelo MVC utilizado en este Producto.
     * @throws DatabaseConnectionException
     */
    public Controlador() throws DatabaseConnectionException {

        this.datos = new Datos();

    }

    // *********************************************************************************
    // * Métodos para gestionar Artículo
    // *********************************************************************************

    /**
     * Método para añadir un artículo a la BBDD.
     * @param cp Tipo String. Contiene el código del artículo.
     * @param desc Tipo String. Contiene la descripción del artículo.
     * @param precio Tipo Double. Contiene el precio de venta del artículo.
     * @param tiempoPrepacion Tipo Duration que contiene el tiempo que se tarda en preparar este artículo para su envío.
     */
    public void addArticulo(String cp, String desc, double precio, Duration tiempoPrepacion)  {

        Articulo a;
        a = new Articulo(cp, desc, precio, tiempoPrepacion);

            if (a.getCodigo() != null) {
                datos.agregarArticulo(a);
            }

    }

    /**
     * Modificar un artículo existente en la BBDD.
     * @param cp Tipo String que contiene el código del artículo.
     * @param desc Tipo String que contiene la descripción del artículo.
     * @param precio Tipo Double que contiene el valor de venta del artículo.
     * @param tiempoPrepacion Tipo Duration que contiene el tiempo que se tarda en preparar este artículo para su envío.
     */
    public void modArticulo(String cp, String desc, double precio, Duration tiempoPrepacion){

        Articulo a ;
        Articulo nuevoA = new Articulo(cp,desc,precio,tiempoPrepacion);
        a = datos.obtenerArticulo(cp);
        if (articuloExiste(cp)){
            datos.modificarArticulo(nuevoA);
        }

    }

    /**
     * Método para mostrar la lista de los artículos existentes.
     * @return Arraylist con el resultado de la consulta.
     */
    public ArrayList<Articulo> mostrarArticulos(){
        return datos.obtenerArticulos();
    }

    /**
     * Método para eliminar un artículo de la BBDD según su Código de Producto.
     * @param cp Tipo String que contiene el código del producto.
     */
    public void eliminarArticulo(String cp) {
        datos.eliminarArticulo(cp);

    }

    /**
     * Método para mostrar un artículo según suy código de producto.
     * @param cp tipo String que contiene el código del producto.
     * @return retorna un Articulo.
     */
    public Articulo mostrarArticuloIndividual(String cp){

        if (datos.obtenerArticulo(cp)!= null) {
            return datos.obtenerArticulo(cp) ;
        }
        return null;
    }

    /**
     * Método para comprobar si un artículo existe en la BBDD.
     * @param cp tipo String que contiene el código del producto.
     * @return Devuelve un booleano para indicar si lo ha encontrado o no.
     */
    public boolean articuloExiste(String cp) {

        boolean existe = false;
        if (datos.obtenerArticulo(cp) != null){

            existe = true;
            return existe;
        }
        return existe;
    }

    // *********************************************************************************
    // * Métodos para gestionar Cliente
    // *********************************************************************************

    /**
     * Método que permite añadir un cliente estándar a la BBDD.
     * @param mail Tipo String. Contiene el mail del cliente que es el indicador principal.
     * @param name Tipo String. Contiene el nombre del cliente.
     * @param dir Tipo String. Contiene la dirección del cliente.
     */
    public void addCliente(String mail, String name, String dir)  {

        ClienteEstandar stand = new ClienteEstandar(mail,name,dir);
        datos.agregarCliente(stand);


    }

    /**
     * Método que permite añadir un cliente premium a la BBDD.
     * @param mail Tipo String. Contiene el mail del cliente que es el indicador principal.
     * @param name Tipo String. Contiene el nombre del cliente.
     * @param dir Tipo String. Contiene la dirección del cliente.
     * @param desc Tipo Double. Contiene el descuento a aplicar por ser cliente premium.
     * @throws EmailExistsException
     */
    public void addCliente(String mail, String name, String dir,double desc) throws EmailExistsException {

        ClientePremium prem = new ClientePremium(mail,name, dir, desc);
        datos.agregarCliente(prem);


    }

    /**
     * Método para verificar si el mail existe en la BBDD
     * @param email Tipo String. Contiene el mail de cliente.
     * @return Retorna un booleano que indica si existe o no en la BBDD.
     */
    public boolean emailExiste(String email) {
        boolean existe = false;
        if (datos.obtenerCliente(email) != null){
            existe = true;
        }
        return existe;
    }

    /**
     * Método para eliminar un cliente existente en la BBDD.
     * @param correo Tipo String. Contiene el mail del cliente.
     * @return Retorna un booleano que indica si la eliminación se ha realizado con éxito o no.
     */
    public Boolean eliminarCliente(String correo){
        Boolean existe = true;
        if (emailExiste(correo)){
            datos.eliminarCliente(correo);
        }else{
            existe =false;
        }
        return existe;
    }

    /**
     * Método que permite modificar la información del cliente estándar existente en la BBDD.
     * @param mail Tipo String. Contiene el mail del cliente.
     * @param nombre Tipo String. Contiene el nombre del cliente.
     * @param direccion Tipo String. Contiene la dirección del cliente.
     */
    public void modificarCliente(String mail, String nombre, String direccion){

        Cliente c = new ClienteEstandar(mail,nombre,direccion);
        if (emailExiste(mail)){
            datos.modificarCliente(c);
        }

    }

    /**
     * Método que permite modificar la información del cliente premium existente en la BBDD.
     * @param mail Tipo String. Contiene el mail del cliente.
     * @param nombre Tipo String. Contiene el nombre del cliente.
     * @param direccion Tipo String. Contiene la dirección del cliente.
     * @param descuento Tipo Double. Contiene el descuento a aplicar por ser cliente premium.
     */
    public void modificarCliente(String mail, String nombre, String direccion, double descuento){

        Cliente c = new ClientePremium( mail,nombre,direccion,descuento);
        if (emailExiste(mail)){
            datos.modificarCliente(c);
        }

    }

    /**
     * Método quie devuelve una lista de todos los clientes existentes en la BBDD sean estándar o premium.
     * @return Arraylist con todos los clientes.
     */
    public ArrayList<Cliente> mostrarCliente(){

        return datos.mostrarTodosLosClientes();

    }

    /**
     * Método que devuelve una lista con los clietnes existentes en la BBDD filtrato por Tipo de cliente: Estándar o Premium.
     * @param columna Tipo String. Indica el nombre de la columna de la BBDD donde está contenido el valor.
     * @param tipo Tipo String. Indica el valor seleccionado para esa columna.
     * @return ArrayList con los datos que contiene la consulta.
     */
    public ArrayList<Cliente> mostrarClientesPorTipo(String columna,String tipo){

        ArrayList<Cliente> lista;
        lista = datos.mostrarPorTipo(columna,tipo);

        return lista;


    }

    /**
     * Método para obtener la información del cliente buscándolo en la BBDD según su mail.
     * @param mail Tipo String. Contiene el mail de cliente.
     * @return retorna un la información del cliente.
     */
    public Cliente buscarCliente(String mail){
        Cliente c;
        c = datos.obtenerCliente(mail);
        return c;
    }

    // *********************************************************************************
    // * Métodos para gestionar Pedido
    // *********************************************************************************

    /**
     * Método para mostrar si un pedido existe en la BBDD
     * @param numeroPedido tipo int. Contiene el número del pedido.
     * @return Retorna un booleano indicando si existe o no el pedido en la BBDD.
     */
    public boolean pedidoExiste(int numeroPedido){

        Boolean existe = false ;

        if(datos.obtenerUnPedido(numeroPedido) !=null){
            existe = true;
        }
        return existe;
    }

    /**
     * Método que añade un nuevo pedido a la BBDD
     * @param mail Tipo String. Contiene el mail del cliente que es el campo índice para localizarlo en la tabla cliente.
     * @param codigoArticulo Tipo String. Contiene el código del Producto que es el campo índice para localizarlo en la tabla artículo.
     * @param numeroPedido Tipo int. Contiene el número del pedido.
     * @param fechaHoraPedido Tipo LocalDateTime. Contiene la fecha y hora en la que se ha realizado el pedido.
     * @param cantidad Tipo int. Contiene la cantidad del artículo solicitada por el cliente.
     * @param enviado tipo boolean. Contiene el valor que indica si el pedido ha sido enviado o no.
     * @param costeEnvio Tipo double. Contiene el valor que indica el coste de envío del pedido.
     */
    public void addPedido(String mail, String codigoArticulo, int numeroPedido, LocalDateTime fechaHoraPedido,int cantidad,
                          boolean enviado,double costeEnvio){

        Cliente c = datos.obtenerCliente(mail) ;
        Articulo a = datos.obtenerArticulo(codigoArticulo);
        Pedido p = new Pedido(numeroPedido,fechaHoraPedido,c,a,cantidad,enviado,costeEnvio);

        datos.agregarPedido(p);
    }

    /**
     * Método para eliminar un pedido existente en la BBDD.
     * @param np Tipo int. Contiene el valor del número de pedido.
     * @return devuelve un tipo boolean indicando si se ha eliminado o no de la BBDD.
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
     * Método utilizado para obtener los pedidos de un cliente filtrando por si están enviados o no.
     * @param enviado Tipo boolean. Indicamos si está enviado (1) o no enviado (0)
     * @param mail Tipo String. Indica el mail del cliente.
     * @return ArrayList de pedidos que coinciden con los criterios establecidos.
     */
    public ArrayList<Pedido> obtenerPedidos(boolean enviado, String mail) {
        ArrayList<Pedido> lista = new ArrayList<>();

        for (Pedido ped : datos.obtenerPedidos()) {

            /*if (enviado == ped.pedidoEnviado() ) {
                if (ped.getCliente().getCorreoElectronico().equals(mail)){
                    lista.add(ped);
                }else if (mail!=null){
                    lista.add(ped);
                }
            }*/
            if (ped.pedidoEnviado() == enviado) {
                if (mail == null || (ped.getCliente() != null && ped.getCliente().getCorreoElectronico().equals(mail))) {
                    lista.add(ped);
                }
            }
        }

        return lista;
    }

    /**
     * Método para mostrar un pedido buscando por el número de pedido.
     * @param pedido Tipo int. Contiene el número de pedido a buscar.
     * @return Devuelve el pedido seleccionado.
     */
    public Pedido mostrarPedido(int pedido){
        if(datos.obtenerUnPedido(pedido)!=null){
            return datos.obtenerUnPedido(pedido);
        }
        return null;
    }

    /**
     * Muestra todos los pedidos que existen en la BBDD sin restricciones.
     * @return ArrayList con todos pedidos.
     */
    public ArrayList<Pedido> MostrarTodosLosPedidos(){
        return datos.obtenerPedidos();
    }

    /**
     * Método para obtener un pedido de la tabla de pedidos.
     * @param cp Tipo int. Contiene el número de pedido a buscar.
     * @return Devuelve el pedido seleccionado.
     */
    public Pedido obtenerPedido( int  cp){

        Pedido pedido = null;

        for(Pedido ped:datos.obtenerPedidos()){

            if(ped.getNumeroPedido() == cp){
                return ped;
            }
        }

        return pedido;
    }

    /**
     * Método que devuelve el descuento a aplicar a clientes premium.
     * @return Devuelve un tipo Double con el importe del descuento.
     */
    public double obtenerDescuento(){
        return 0.45;
    }

    /**
     * Método que permite comprobar si el pedido está en preparación o enviado.
     * @param fechaHoraActual Tipo LocalDateTime. Contiene la fecha y hora actual
     * @param tiempoPrep Tipo Duration. Contiene el tiempo de preparación del producto.
     * @return
     */
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