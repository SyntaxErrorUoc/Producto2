package modelo;

/**
 * @author SyntaxError
 * @version 2.0.1
 */
public class ClientePremium extends Cliente{
	private double descuento;
	/**
	 * Constructor de la clase heredada de cliente
	 * @param correoElectronico recibe un String
	 * @param nombre recibe un String
	 * @param direccion recibe un String
	 */
	public ClientePremium(String correoElectronico, String nombre, String direccion, double descuento) {
		super(correoElectronico, nombre, direccion);
		this.descuento = descuento;
	}

	public ClientePremium() {

	}

	/**
	 * Implementación de la clase abstracta tipoCliente
	 * @return devuelve un tipo String
	 */
	@Override
	public String tipoCliente() {
		return "Premium";
	}

	/**
	 * Implementación de la clase abstracta calcAnual
	 * @return devuelve un tipo double
	 */
	@Override
	public double calcAnual() {
		// Implementación del cálculo de la cuota anual para un cliente premium.
		double cuota = 30.0;
		return cuota;
	}

	/**
	 * Implementación de la clase abstracta descuentoEnv
	 * @return devuelve un tipo double
	 */
	@Override
	public double descuentoEnv() {
		// Implementación del descuento de gastos de envío para un cliente premium.
		double descuentoE = 0.2;
		return descuentoE;
	}

	@Override
	public String toString() {
		return super.toString() + "CLIENTE PREMIUM " + "y su descuento es " + this.descuento+"\n";
	}
}
