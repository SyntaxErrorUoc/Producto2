package modelo;
import java.time.LocalDate;
public class Pedido {
	 	private int numeroPedido;
	    private LocalDate fechaHoraPedido;
	    private Cliente cliente;
	    private Articulo articulo;
		private double costeEnvio;
	    private int cantidad;
	    private boolean enviado;
	    
	    
	    // Constructor
		public Pedido(int numeroPedido, LocalDate fechaHoraPedido, Cliente cliente, Articulo articulo, int cantidad,
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

		// Getters
		public int getNumeroPedido() {
			return numeroPedido;
		}


		public LocalDate getFechaHoraPedido() {
			return fechaHoraPedido;
		}


		public Cliente getCliente() {
			return cliente;
		}


		public Articulo getArticulo() {
			return articulo;
		}


		public int getCantidad() {
			return cantidad;
		}


		public boolean isEnviado() {
			return enviado;
		}

		// Setters
		public void setNumeroPedido(int numeroPedido) {
			this.numeroPedido = numeroPedido;
		}


		public void setFechaHoraPedido(LocalDate fechaHoraPedido) {
			this.fechaHoraPedido = fechaHoraPedido;
		}


		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}


		public void setArticulo(Articulo articulo) {
			this.articulo = articulo;
		}


		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}


		public void setEnviado(boolean enviado) {
			this.enviado = enviado;
		}
	    
		
	    public boolean pedidoEnviado() {
	    	return enviado;
	    }

	    public double precioEnvio(double costeEnvio ) {


	    	
	    	if (cliente instanceof ClientePremium) {
	    		costeEnvio -= (costeEnvio * cliente.descuentoEnv()/100);
	    	}
	    	return costeEnvio;
	    }
		public double precioTotal (){

			double total= 0.0;
			total = precioEnvio(cantidad * articulo.getPrecio());
			return total;
		}


		@Override
		public String toString() {
			return "Pedido [numeroPedido=" + numeroPedido + ", fechaHoraPedido=" + fechaHoraPedido + ", cliente="
					+ cliente + ", articulo=" + articulo + ", cantidad=" + cantidad + ", enviado=" + enviado + ",costeEnvio:"+
					precioEnvio(costeEnvio)+",precioTotal: "+ precioTotal() +"]\n";
		}
	    
	    
}
