package modelo;

public class Datos {
	private ListaArticulos listaArticulos;
	private ListaClientes listaClientes;
	private ListaPedidos listaPedidos;




	// Constructor
	public Datos() {
		listaArticulos = new ListaArticulos();
		listaClientes = new ListaClientes();
		listaPedidos = new ListaPedidos();
	}

	// Métodos para gestionar Artículos
	public void agregarArticulo(Articulo articulo) {
		listaArticulos.agregar(articulo);
	}

	public void eliminarArticulo(Articulo articulo) {
		listaArticulos.eliminar(articulo);
	}

	public void obtenerArticulo() {
		 listaArticulos.mostrarTodo();
	}

	public int tamArticulos() {
		return listaArticulos.tamanio();
	}

	public ListaArticulos getListaArticulos() {return listaArticulos;}

	// Métodos para gestionar Clientes
	public void agregarCliente(Cliente cliente) {
		listaClientes.agregar(cliente);
	}

	public void eliminarCliente(Cliente cliente) {
		listaClientes.eliminar(cliente);
	}
	public ListaClientes getListaClientes() {
		return listaClientes;
	}
	public void obtenerCliente () {
		 listaClientes.mostrarTodo();
	}

	public int tamClientes() {
		return listaClientes.tamanio();
	}

	// Métodos para gestionar Pedidos


	public ListaPedidos getListaPedidos() {
		return listaPedidos;
	}

	public void agregarPedido(Pedido pedido) {
	        listaPedidos.agregar(pedido);
	    }

	public void eliminarPedido(Pedido pedido) {
		listaPedidos.eliminar(pedido);
	}

	public void obtenerPedido() {
		listaPedidos.mostrarTodo();
	}

	public void tamPedidos() {
		listaPedidos.tamanio();
	}

	// Metodos para obtener los indices
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


