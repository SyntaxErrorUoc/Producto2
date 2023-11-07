package modelo;

import DAO.MySQL.ConexionMySQL;
import DAO.MySQL.DAOExceptions;
import DAO.MySQL.articuloMySQLDAO;
import DAO.MySQL.clienteMySQLDAO;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SyntaxError
 * @version 2.0.1
 */

public class Datos {
	private ListaArticulos listaArticulos;
	private ListaClientes listaClientes;
	private ListaPedidos listaPedidos;


	/**
	 * Constructor de la clase sin parámetros
	 */
	public Datos() {
		listaArticulos = new ListaArticulos();
		listaClientes = new ListaClientes();
		listaPedidos = new ListaPedidos();
	}


	// Métodos para gestionar Artículos

	/**
	 * Metodo para agregar articulo
	 * @param articulo recibe un tipo Articulo
	 */
	public void agregarArticulo(Articulo articulo) throws SQLException {
		Connection conn = new ConexionMySQL().conectarMySQL();
		articuloMySQLDAO b = new articuloMySQLDAO(conn);
		b.insertar(articulo);
	}

	/**
	 * Metodo para eliminar articulo
	 * @param cp recibe un tipo articulo
	 */
	public void eliminarArticulo(String cp) throws SQLException {

		Connection conn = new ConexionMySQL().conectarMySQL();
		articuloMySQLDAO b = new articuloMySQLDAO(conn);
		b.eliminar(cp);
	}

	/**
	 * Metodo para mostrar articulos
	 * Devuelve un arrayList de tipo Articulo
	 */
	public void obtenerArticulo() {
		 listaArticulos.mostrarTodo();
	}

	/**
	 * Metodo para obtener el tamaño de la lista Articulos
	 * @return devuelve un int
	 */

	public int tamArticulos() {
		return listaArticulos.tamanio();
	}

	/**
	 * Metodo para mostrar la lista de articulos
	 * @return devuelve una lista de tipo Articulo
	 */
	public ListaArticulos getListaArticulos() {return listaArticulos;}

	/**
	 * Setter para ListaArticulos
	 * @param listaArticulos recibe un tipo ListaArticulo
	 */
	public void setListaArticulos(ListaArticulos listaArticulos) {
		this.listaArticulos = listaArticulos;
	}


	// Métodos para gestionar Clientes

	/**
	 * Metodo para agregar cliente
	 * @param cliente recibe un tipo Cliente
	 */
	public void agregarCliente(Cliente cliente) throws DAOExceptions {

		try(Connection conn = new ConexionMySQL().conectarMySQL()){
			clienteMySQLDAO b = new clienteMySQLDAO(conn);
			b.insertar(cliente);
		} catch (SQLException e){
			throw new DAOExceptions("SQL exception", e);
		}
	}

	/**
	 * Metodo para eliminar Cliente
	 * @param mail recibe un tipo String
	 */
	public void eliminarCliente(String mail) throws SQLException {
		try(Connection conn = new ConexionMySQL().conectarMySQL()){
			clienteMySQLDAO b = new clienteMySQLDAO(conn);
			b.eliminar(mail);
		}catch (SQLException e){
			throw new DAOExceptions("Error al eliminar el cliente", e);
		}


	}

	public void modificarCliente(String correo, String nombre, String direccion) throws DAOExceptions {
		try(Connection conn = new ConexionMySQL().conectarMySQL()){
			clienteMySQLDAO m = new clienteMySQLDAO(conn);
			Cliente cliente = m.obtener(correo);
			if(cliente == null){
				throw new DAOExceptions("Cliente no encontrado");
			}
			cliente.setNombre(nombre);
			cliente.setDireccion(direccion);
			m.modificar(cliente);

		}catch (SQLException e){
			throw new DAOExceptions("Error al eliminar el cliente", e);
		}
	}

	public List<Cliente> mostrarTodosLosClientes() throws DAOExceptions {
		List<Cliente> clientes = new ArrayList<>();
		try(Connection conn = new ConexionMySQL().conectarMySQL()){
			clienteMySQLDAO mot = new clienteMySQLDAO(conn);
			clientes = mot.obtenerTodos();
		} catch (SQLException e){
			throw new DAOExceptions("Error al obtener los clientes", e);
		}
		return clientes;
	}





	/**
	 * Metodo para obtener la lista Clientes
	 * @param listaClientes
	 */
	public void setListaClientes(ListaClientes listaClientes) {
		this.listaClientes = listaClientes;
	}

	/**
	 * Metodo para mostrar ListaCliente
	 * @return ListaCliente
	 */
	public ListaClientes getListaClientes() {
		return listaClientes;
	}

	/**
	 * Metodo para mostrar Todos los Clientes
	 */
	public void obtenerCliente () {
		 listaClientes.mostrarTodo();
	}

	public List<Cliente> obtenerClientePorTipo(String tipo)throws DAOExceptions{
		List<Cliente> clientes = new ArrayList<>();
		try(Connection conn = new ConexionMySQL().conectarMySQL()){
			clienteMySQLDAO cpt = new clienteMySQLDAO(conn);
			return cpt.mostrarPorTipoCliente(tipo);
		} catch (SQLException e) {
			throw new DAOExceptions("Error al obtener los clientes", e);
		}

	}
	/**
	 *
	 * @return
	 */
	public int tamClientes() {
		return listaClientes.tamanio();
	}




	// Métodos para gestionar Pedidos

	/**
	 * Getter ListaPedido
	 * @return devuelve un listaPedido
	 */
	public ListaPedidos getListaPedidos() {
		return listaPedidos;
	}

	/**
	 * Setter de listaPedidos
	 * @param listaPedidos recibe listadoPedido
	 */
	public void setListaPedidos(ListaPedidos listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	/**
	 * Metodo para agregar pedido
	 * @param pedido
	 */
	public void agregarPedido(Pedido pedido) {
	        listaPedidos.agregar(pedido);
	    }

	/**
	 * Metodo para eliminar pedido
	 * @param pedido
	 */
	public void eliminarPedido(Pedido pedido) {
		listaPedidos.eliminar(pedido);
	}

	/**
	 * Metodo para obtener os pedidos
	 */
	public void obtenerPedido() {
		listaPedidos.mostrarTodo();
	}

	/**
	 * Metodo para obtener el tamaño de lista pedido
	 */
	public void tamPedidos() {
		listaPedidos.tamanio();
	}



	// Metodos para obtener los indices

	/**
	 * Metodo para obtener el indice de un cliente
	 * @param valor recibe un String
	 * @return devuelve un int
	 */
	public int devolverIndiceCliente(String valor){
		boolean existe = false;
		int counter = 0;
		while ((listaClientes.getLista().size() > counter) && (!existe)) {
			if (listaClientes.getLista().get(counter).getNombre().equals(valor)) {
				existe = true;
			} else {
				counter++;
			}
		}
		if (counter == listaClientes.getLista().size()){
			return 0;
		}else{
			return counter;
			}
		}

	/**
	 * Metodo para obtener el indice de un cliente
	 * @param valor recibe un String
	 * @return devuelve un int
	 */
	public int devolverIndiceArticulo(String valor){

		boolean existe = false;
		int counter = 0;

		while ((listaArticulos.getLista().size() > counter) && (!existe)) {
			if (listaArticulos.getLista().get(counter).getCodigo().equals(valor)) {
				existe = true;
			} else {
				counter++;
			}
		}
		if (counter == listaArticulos.getLista().size()){
			return 0;
		}else{
			return counter;
			}
		}

	/**
	 * Metodo para obtener el indice de un pedido
	 * @param valor recibe un int
	 * @return devuelve un int
	 */
	public int devolverIndicePedido(int valor){

		boolean existe = false;
		int counter = 0;

		while ((listaPedidos.getLista().size() > counter) && (!existe)) {
			if (listaPedidos.getLista().get(counter).getNumeroPedido() == valor) {
				existe = true;
			} else {
				counter++;
			}
		}
		if (counter == listaPedidos.getLista().size()){
			return 0;
		}else{
			return counter;
		}
	}

}


