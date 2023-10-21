package syntaxerror.modelo;

public abstract class Cliente {

	private String correoElectronico;
	private String nombre;
	private String direccion;
	
	// Constructor
	public Cliente(String correoElectronico, String nombre, String direccion) {
		this.correoElectronico = correoElectronico;
		this.nombre = nombre;
		this.direccion = direccion;
	}
	
	// Getters
	public String getCorreoElectronico() {
		return correoElectronico;
	}


	public String getNombre() {
		return nombre;
	}

	
	public String getDireccion() {
		return direccion;
	}

	// Setters
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	// Metodos abstractos
	public abstract String tipoCliente();
	public abstract double calcAnual();
	public abstract double descuentoEnv();
	
	
	@Override
	public String toString() {
		return "Cliente [correoElectronico=" + correoElectronico + ", nombre=" + nombre + ", direccion=" + direccion
				+ "]";
	}
	
	
	
	
}
