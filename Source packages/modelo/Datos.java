package modelo;


import ConexionMySQL.DatabaseConnectionException;
import Factory.FactoryDAO;

import java.util.ArrayList;


public class Datos {

	private FactoryDAO factory;

	//Connection conn;

	public Datos() throws DatabaseConnectionException {

		this.factory = new FactoryDAO();

	}

	// Métodos para gestionar Artículos

	public void agregarArticulo(Articulo a)  {

		this.factory.articulo.insertar(a);
	}

	public void modificarArticulo(Articulo a ){
		this.factory.articulo.modificar(a);

	}

	public void eliminarArticulo(String cp) {
		this.factory.articulo.eliminar(cp);

	}

	public Articulo obtenerArticulo(String id) {
		Articulo a = this.factory.articulo.obtenerUno(id);
		return  a;

	}

	public ArrayList<Articulo> obtenerArticulos() {
		ArrayList<Articulo> articulos;
		articulos = this.factory.articulo.obtenerTodos();
		return articulos;
	}


	// Métodos para gestionar Clientes

	public void agregarCliente(Cliente c)  {
		this.factory.cliente.insertar(c);

	}

	public void eliminarCliente(String mail) {

		this.factory.cliente.eliminar(mail);

	}

	public void modificarCliente(Cliente cliente) {
		this.factory.cliente.modificar(cliente);

	}

	public ArrayList<Cliente> mostrarTodosLosClientes() {

		ArrayList<Cliente> listaClientes;
		listaClientes = this.factory.cliente.obtenerTodos();
		return listaClientes;

	}

	public ArrayList<Cliente> mostrarPorTipo(String c,String d){

		ArrayList<Cliente> listado;
		listado = this.factory.cliente.obtenerPorCriterio(c,d);
		return listado;
	}

	public Cliente obtenerCliente (String mail) {

		return this.factory.cliente.obtenerUno(mail);
	}


	// Métodos para gestionar Pedidos

	public void agregarPedido(Pedido pedido) {
		this.factory.pedido.insertar(pedido);

	}

	public void eliminarPedido(int id) {
		this.factory.pedido.eliminar(id);
	}

	public ArrayList<Pedido> obtenerPedidos() {
		ArrayList<Pedido> lista = new ArrayList<>();
		lista = this.factory.pedido.obtenerTodos();
		return lista;
	}

	public ArrayList<Pedido> obtenerPedidosfiltro() {
		ArrayList<Pedido> lista = new ArrayList<>();
		lista = this.factory.pedido.obtenerTodos();
		return lista;
	}

	public Pedido obtenerUnPedido(int cp){
		return this.factory.pedido.obtenerUno(cp);
	}

}

