package modelo;


public class ClientePremium extends Cliente{

	private double descuento;
	//Constructor
	public ClientePremium(String correoElectronico, String nombre, String direccion, double descuento) {
		super(correoElectronico, nombre, direccion);
		this.descuento = descuento;
	}

	@Override
	public String tipoCliente() {
		return "Premium";
	}

	@Override
	public double calcAnual() {
		// Implementación del cálculo de la cuota anual para un cliente premium.
		double cuota = 30.0;
		return cuota;
	}

	@Override
	public double descuentoEnv() {
		// Implementación del descuento de gastos de envío para un cliente premium.
		double descuentoE = 0.2;
		return descuentoE;
	}

	@Override
	public String toString() {
		return super.toString() + "CLIENTE PREMIUM" + "y su descuento es " + this.descuento;
	}
}