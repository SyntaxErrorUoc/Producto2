package modelo;

public class ClienteEstandar extends Cliente {
	// Constructor
	public ClienteEstandar(String correoElectronico, String nombre, String direccion) {
		super(correoElectronico, nombre, direccion);
	}


	@Override
	public String tipoCliente() {
		
		return "Estandar";
	}

	@Override
	public double calcAnual() {
		// Implementación del cálculo de la cuota anual para un cliente premium.
		double cuota = 0.0;
		return cuota;
	}

	@Override
	public double descuentoEnv() {
		// Implementación del descuento de gastos de envío para un cliente premium.
		double descuentoE = 0.0;
		return descuentoE;
	}
	@Override
	public String toString() {
		return super.toString() + "CLIENTE STANDARD";
	}
	

}
