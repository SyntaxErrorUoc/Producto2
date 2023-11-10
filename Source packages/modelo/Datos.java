package modelo;

import DAO.MySQL.*;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SyntaxError
 * @version 2.0.1
 */

public class Datos {

	private articuloMySQLDAO articuloMySQLDAO;
	private clienteMySQLDAO clienteMySQLDAO;
	private pedidoMySQLDAO pedidoMySQLDAO;

	Connection conn;
	/**
	 * Constructor de la clase sin parámetros
	 */
	public Datos() {

		try{
			this.conn =  new ConexionMySQL().conectarMySQL();
		}catch(SQLException e){
			new DAOExceptions("Ha fallado la conexion a la base de datos",e);
		}
		this.clienteMySQLDAO = new clienteMySQLDAO(conn);
		this.articuloMySQLDAO = new articuloMySQLDAO(conn);
		this.pedidoMySQLDAO = new pedidoMySQLDAO(conn);


	}

	// Métodos para gestionar Artículos

	/**
	 * Metodo para agregar articulo
	 * @param articulo recibe un tipo Articulo
	 */
	public void agregarArticulo(Articulo articulo)  {


		this.articuloMySQLDAO.insertar(articulo);
	}

	/**
	 * Metodo para eliminar articulo
	 * @param cp recibe un tipo articulo
	 */
	public void eliminarArticulo(String cp) {


		this.articuloMySQLDAO.eliminar(cp);
	}

	/**
	 * Metodo para mostrar articulos
	 * Devuelve un arrayList de tipo Articulo
	 */
	public Articulo obtenerArticulo(String id) {
		return  this.articuloMySQLDAO.obtenerUno(id);
	}

	/**
	 * Metodo para mostrar la lista de articulos
	 * @return devuelve una lista de tipo Articulo
	 */
	public List<Articulo> obtenerArticulos() {
		List<Articulo> articulos;
		articulos = this.articuloMySQLDAO.obtenerTodos();
		return articulos;
	}


	// Métodos para gestionar Clientes

	/**
	 * Metodo para agregar cliente
	 * @param cliente recibe un tipo Cliente
	 */
	public void agregarCliente(Cliente cliente)  {

			this.clienteMySQLDAO.insertar(cliente);
	}

	/**
	 * Metodo para eliminar Cliente
	 * @param mail recibe un tipo String
	 */
	public void eliminarCliente(String mail) {

		this.clienteMySQLDAO.eliminar(mail);

	}

	public void modificarCliente(Cliente cliente) {

		this.clienteMySQLDAO.modificar(cliente);

	}

	public List<Cliente> mostrarTodosLosClientes() {
		List<Cliente> listaClientes  = new ArrayList();
		listaClientes = this.clienteMySQLDAO.obtenerTodos();
		return listaClientes;

	}

	/**
	 * Metodo para mostrar Todos los Clientes
	 */
	public Cliente obtenerCliente (String mail) {
		 return this.clienteMySQLDAO.obtenerUno(mail);
	}


	// Métodos para gestionar Pedidos


	/**
	 * Metodo para agregar pedido
	 * @param pedido
	 */
	public void agregarPedido(Pedido pedido) {
	        this.pedidoMySQLDAO.insertar(pedido);
	    }


	public void eliminarPedido(int id) {
		this.pedidoMySQLDAO.eliminar(id);
	}

	/**
	 * Metodo para obtener os pedidos
	 */
	public ArrayList<Pedido> obtenerPedidos() {
		ArrayList<Pedido> lista = new ArrayList<>();
		lista = this.pedidoMySQLDAO.obtenerTodos();
		return lista;
	}
	public Pedido obtenerUnPedido(int cp){

		Pedido p = this.pedidoMySQLDAO.obtenerUno(cp);
		return p;

	}



	// Metodos para obtener los indices


}


