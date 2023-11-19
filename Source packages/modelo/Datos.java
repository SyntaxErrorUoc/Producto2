package modelo;


import ConexionMySQL.DatabaseConnectionException;
import Factory.FactoryDAO;

import java.util.ArrayList;

/**
 * Clase que implementa la conexión a la BBDD y las tablas relacionadas.
 */
public class Datos {

	private FactoryDAO factory;

	//Connection conn;

	/**
	 * Constructor que permite conectarse a la BBDD.
	 * @throws DatabaseConnectionException
	 */
	public Datos() throws DatabaseConnectionException {

		this.factory = new FactoryDAO();

	}

	// **********************************************************************************
	// * Métodos para gestionar Artículos
	// **********************************************************************************

	/**
	 * Método para agregar un artículo a la tabla "articulo".
	 * @param a Tipo Articulo que contiene los datos a insertar.
	 */
	public void agregarArticulo(Articulo a)  {

		this.factory.articulo.insertar(a);
	}

	/**
	 * Método para actualizar la información de un artículo existente en la tabla "artículo"
	 * @param a Tipo Articulo que contiene la información a actualizar.
	 */
	public void modificarArticulo(Articulo a ){
		this.factory.articulo.modificar(a);

	}

	/**
	 * Método para eliminar un artículo existente en la tabla "articulo".
	 * @param cp Tipo String que contiene el código de producto.
	 */
	public void eliminarArticulo(String cp) {
		this.factory.articulo.eliminar(cp);

	}

	/**
	 * Método para obtener la información de un artículo existente en la tabla "articulo".
	 * @param id Tipo String que contiene el código de producto.
	 * @return Tipo Articulo con la información del artículo.
	 */
	public Articulo obtenerArticulo(String id) {
		Articulo a = this.factory.articulo.obtenerUno(id);
		return  a;

	}

	/**
	 * Método para obtener un listado de todos los artículos existentes en la BBDD.
	 * @return ArrayList con todos los articulos que aparecen en la consulta.
	 */
	public ArrayList<Articulo> obtenerArticulos() {
		ArrayList<Articulo> articulos;
		articulos = this.factory.articulo.obtenerTodos();
		return articulos;
	}

	// ****************************************************************************************
	// * Métodos para gestionar Clientes
	// ****************************************************************************************

	/**
	 * Método para insertar un nuevo cliente a la tabla "cliente"
	 * @param c Tipo Cliente con la información del cliente tanto si es Premium como Estándar
	 */
	public void agregarCliente(Cliente c)  {
		this.factory.cliente.insertar(c);

	}

	/**
	 * Método para eliminar un cliente existente en la tabla "cliente".
	 * @param mail Tipo String. Contiene el mail del cliente a eliminar.
	 */
	public void eliminarCliente(String mail) {

		this.factory.cliente.eliminar(mail);

	}

	/**
	 * Método para actualizar la información existente en la tabla "cliente".
	 * @param cliente Tipo Cliente. Contiene la información del cliente tanto si es premium como estándar.
	 */
	public void modificarCliente(Cliente cliente) {
		this.factory.cliente.modificar(cliente);

	}

	/**
	 * Método que devuelve todos los clientes existente en la tabla "cliente" independientemente de si son
	 * premium o estándar.
	 * @return Tipo ArrayList con el resultado de la consulta.
	 */
	public ArrayList<Cliente> mostrarTodosLosClientes() {

		ArrayList<Cliente> listaClientes;
		listaClientes = this.factory.cliente.obtenerTodos();
		return listaClientes;

	}

	/**
	 * Método que permite mostrar un listado de todos los clientes filtrado por el tipo de cliente.
	 * @param c Tipo String que indica la columna a filtrar.
	 * @param d Tipo String que indica el valor por el que filtrar la tabla.
	 * @return Tipo ArrayList con el resultado de la consulta.
	 */
	public ArrayList<Cliente> mostrarPorTipo(String c,String d){

		ArrayList<Cliente> listado;
		listado = this.factory.cliente.obtenerPorCriterio(c,d);
		return listado;
	}

	/**
	 * Método para obtener la información de un cliente según su mail.
	 * @param mail Tipo String. Mail del cliente que es el identificador único de la tabla "cliente".
	 * @return Tipo Cliente con la información del cliente seleccionado.
	 */
	public Cliente obtenerCliente (String mail) {

		return this.factory.cliente.obtenerUno(mail);
	}

	// ***********************************************************************************************
	// Métodos para gestionar Pedidos
	// ***********************************************************************************************

	/**
	 * Método para agregar un pedido a la tabla "pedido"
	 * @param pedido Tipo Pedido. Contiene la información del pedido a insertar.
	 */
	public void agregarPedido(Pedido pedido) {
		this.factory.pedido.insertar(pedido);

	}

	/**
	 * Método para eliminar un pedido existente de la tabla "pedido".
	 * @param id Tipo int que contiene el número de pedido.
	 */
	public void eliminarPedido(int id) {
		this.factory.pedido.eliminar(id);
	}

	/**
	 * Método que devuelve un listado con todos los pedidos existentes en la tabla "pedido"
	 * @return ArrayList con el resultado de la consulta.
	 */
	public ArrayList<Pedido> obtenerPedidos() {
		ArrayList<Pedido> lista = new ArrayList<>();
		lista = this.factory.pedido.obtenerTodos();
		return lista;
	}

	/**
	 * Método que devuelve un listado con todos los pedidos existentes en la tabla "pedido"
	 * @return ArrayList con el resultado de la consulta.
	 */
	public ArrayList<Pedido> obtenerPedidosfiltro() {
		ArrayList<Pedido> lista = new ArrayList<>();
		lista = this.factory.pedido.obtenerTodos();
		return lista;
	}

	/**
	 * Método para obtener la información de un pedido de la tabla "pedido".
	 * @param cp Tipo int con el número de pedido a buscar.
	 * @return Tipo Pedido con la información del pedido.
	 */
	public Pedido obtenerUnPedido(int cp){
		return this.factory.pedido.obtenerUno(cp);
	}

}

