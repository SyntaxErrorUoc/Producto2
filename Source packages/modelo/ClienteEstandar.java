package modelo;
/**
 * @author SyntaxError
 * @version 2.0.1
 */
public class ClienteEstandar extends Cliente {

	/**
	 * Constructor de la clase heredada de cliente
	 * @param correoElectronico recibe un String
	 * @param nombre recibe un String
	 * @param direccion recibe un String
	 */
	public ClienteEstandar(String correoElectronico, String nombre, String direccion) {
		super(correoElectronico, nombre, direccion);
	}

	public ClienteEstandar() {

	}
	/**
	 * Implementación de la clase abstracta tipoCliente
	 * @return devuelve un tipo String
	 */
	@Override
	public String tipoCliente() {
		
		return "Estandar";
	}

	/**
	 * Implementación de la clase abstracta calcAnual
	 * @return devuelve un tipo double
	 */
	@Override
	public double calcAnual() {
		// Implementación del cálculo de la cuota anual para un cliente premium.
		double cuota = 0.0;
		return cuota;
	}
	/**
	 * Implementación de la clase abstracta descuentoEnv
	 * @return devuelve un tipo double
	 */
	@Override
	public double descuentoEnv() {
		// Implementación del descuento de gastos de envío para un cliente premium.
		double descuentoE = 0.0;
		return descuentoE;
	}

	/**
	 * Método para mostrar en pantalla la información del cliente estándar.
	 * @return Tipo String con el texto del cliente estándar.
	 */
	@Override
	public String toString() {
		return super.toString() + "CLIENTE STANDARD\n";
	}
	

}
