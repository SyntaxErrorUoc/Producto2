package modelo;

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
	public void agregarArticulo(Articulo articulo) {
		listaArticulos.agregar(articulo);
	}

	/**
	 * Metodo para eliminar articulo
	 * @param articulo recibe un tipo articulo
	 */
	public void eliminarArticulo(Articulo articulo) {
		listaArticulos.eliminar(articulo);
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
	 *
	 * @param cliente
	 */
	public void agregarCliente(Cliente cliente) {
		listaClientes.agregar(cliente);
	}

	/**
	 *
	 * @param listaClientes
	 */
	public void setListaClientes(ListaClientes listaClientes) {
		this.listaClientes = listaClientes;
	}

	/**
	 *
	 * @param cliente
	 */
	public void eliminarCliente(Cliente cliente) {
		listaClientes.eliminar(cliente);
	}

	/**
	 *
	 * @return
	 */
	public ListaClientes getListaClientes() {
		return listaClientes;
	}

	/**
	 *
	 */
	public void obtenerCliente () {
		 listaClientes.mostrarTodo();
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
	 *
	 * @return
	 */
	public ListaPedidos getListaPedidos() {
		return listaPedidos;
	}

	/**
	 *
	 * @param listaPedidos
	 */

	public void setListaPedidos(ListaPedidos listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	/**
	 *
	 * @param pedido
	 */
	public void agregarPedido(Pedido pedido) {
	        listaPedidos.agregar(pedido);
	    }

	/**
	 *
	 * @param pedido
	 */
	public void eliminarPedido(Pedido pedido) {
		listaPedidos.eliminar(pedido);
	}

	/**
	 *
	 */
	public void obtenerPedido() {
		listaPedidos.mostrarTodo();
	}

	/**
	 *
	 */
	public void tamPedidos() {
		listaPedidos.tamanio();
	}



	// Metodos para obtener los indices

	/**
	 *
	 * @param valor
	 * @return
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
	 *
	 * @param valor
	 * @return
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
	 *
	 * @param valor
	 * @return
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


