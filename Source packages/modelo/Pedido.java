package modelo;
import java.time.LocalDateTime;
/**
 * @author SyntaxError
 * @version 2.0.1
 */
public class Pedido {
	 	private int numeroPedido;
	    private LocalDateTime fechaHoraPedido;
	    private Cliente cliente;
	    private Articulo articulo;
		private double costeEnvio;
	    private int cantidad;
	    private boolean enviado;


	/**
	 * Constructor de la clase con 7 parametros
	 * @param numeroPedido recibe un tipo int
	 * @param fechaHoraPedido recibe un tipo LocalDateTime
	 * @param cliente recibe un tipo Cliente
	 * @param articulo recibe un tipo Articulo
	 * @param cantidad recibe un tipo int
	 * @param enviado recibe un tipo boolean
	 * @param costeEnvio recibe un tipo double
	 */
	public Pedido(int numeroPedido, LocalDateTime fechaHoraPedido, Cliente cliente, Articulo articulo, int cantidad,
					  boolean enviado,double costeEnvio) {
			super();
			this.numeroPedido = numeroPedido;
			this.fechaHoraPedido = fechaHoraPedido;
			this.cliente = cliente;
			this.articulo = articulo;
			this.cantidad = cantidad;
			this.enviado = enviado;
			this.costeEnvio = costeEnvio;
		}

	/**
	 * Constructor de la clase vacio
	 */
	public Pedido() {
	}

	/**
	 * Getter del numeroPedido
	 * @return devuelve un int
	 */
		public int getNumeroPedido() {
			return numeroPedido;
		}

	/**
	 * Getter del fechaHoraPedido
	 * @return devuelve Un LocalDateTime
	 */
		public LocalDateTime getFechaHoraPedido() {
			return fechaHoraPedido;
		}

	/**
	 * Getter del cliente
	 * @return devuelve un tipo Cliente
	 */
		public Cliente getCliente() {
			return cliente;
		}

	/**
	 * Getter del articulo
	 * @return devuelve un tipo Articulo
	 */
		public Articulo getArticulo() {
			return articulo;
		}

	/**
	 * Getter de cantidad
	 * @return devuelve un int
	 */
	public int getCantidad() {
			return cantidad;
		}

	/**
	 * Getter de enviado
	 * @return devuelve un tipo boolean
	 */
	public boolean isEnviado() {
			return enviado;
		}

	/**
	 * Setter de numeroPedido
 	 * @param numeroPedido de tipo int
	 */

	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	/**
	 * Setter de fechaHoraPedido
	 * @param fechaHoraPedido de tipo LocalDateTime
	 */
	public void setFechaHoraPedido(LocalDateTime fechaHoraPedido) {
		this.fechaHoraPedido = fechaHoraPedido;
	}

	/**
	 * Setter de cliente
	 * @param cliente de tipo Cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Setter de articulo
	 * @param articulo de tipo Articulo
	 */
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	/**
	 * Setter de cantidad
	 * @param cantidad de tipo int
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Setter de enviado
	 * @param enviado de tipo boolean
	 */
	public void setEnviado(boolean enviado) {
		this.enviado = enviado;
	}

	/**
	 * Metodo para pedidoEniado
 	 * @return devuelve un boolean
	 */
	public boolean pedidoEnviado() {
		return enviado;
	}

	/**
	 * Metodo de calculo para precioEnvio
	 * @param costeEnvio recibe un double
	 * @return devuelve un tipo doble
	 */
	public double precioEnvio(double costeEnvio ) {

			if (cliente instanceof ClientePremium) {
				costeEnvio -= (costeEnvio * cliente.descuentoEnv()/100);
			}
			return costeEnvio;
		}

	/**
	 * Metodo para el calculo del precioTotal
	 * @return devuele un double
	 */
	public double precioTotal (){

		double total= 0.0;
		total = precioEnvio(cantidad * articulo.getPrecio())+precioEnvio(costeEnvio);
		return total;
	}

	/**
	 * Metodo para mostrar MailCliente
	 * @return devuelve un String
	 */
	public String getMailCliente(){
		return cliente.getCorreoElectronico();
	}


		@Override
		public String toString() {
			return "\n-----------------"+"\nPedido numero : " + numeroPedido +"\n-----------------"+ "\nFecha y hora del pedido :" + fechaHoraPedido + "\nCliente del pedido: "
					+ cliente.getNombre() +"\n Mail del cliente" + cliente.getCorreoElectronico()+ "Articulo=" + articulo + "\nCantidad del articulo :" + cantidad + "\nEstado del envio :" + enviado + "\nCosteEnvio :"+
					precioEnvio(costeEnvio)+"Precio total del pedido : "+ precioTotal() +"\n";
		}
	    
	    
}
