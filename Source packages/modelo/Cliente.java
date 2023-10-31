package modelo;
/**
 * @author SyntaxError
 * @version 2.0.1
 */
public abstract class Cliente {

	private String correoElectronico;
	private String nombre;
	private String direccion;

	/**
	 * Constructor de la clase con 3 prámetros
	 * @param correoElectronico recibe un tipo String
	 * @param nombre recibe un tipo String
	 * @param direccion recibe un tipo String
	 */
		public Cliente(String correoElectronico, String nombre, String direccion) {
		this.correoElectronico = correoElectronico;
		this.nombre = nombre;
		this.direccion = direccion;
	}

	/**
	 * Constructor de la clase vacío
	 */
	public Cliente() {
	}

	/**
	 * Getter de Correo Electronico
	 * @return devuelve un tipo String
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	/**
	 * Getter de Nombre
	 * @return devuelve un tipo String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Getter de Direccion
	 * @return devuelve un tipo String
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Setter de Correo electrónico
	 * @param correoElectronico recibe un tipo Strind
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	/**
	 * Setter de nombre
	 * @param nombre recibe un tipo String
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Setter de direccion
	 * @param direccion recibe un tipo String
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Metodo abstracto de tipoCliente
	 * @return devuelve un String
	 */
	public abstract String tipoCliente();

	/**
	 * Metodo abstracto de Calculo Anual
	 * @return devuelve un double
	 */
	public abstract double calcAnual();

	/**
	 * Metodo abstracto de descuento envio
	 * @return devuelve un tipo double
	 */
	public abstract double descuentoEnv();
	
	
	@Override
	public String toString() {
		return "\n\n-----------\n"+"Cliente :" + nombre +"\n-----------"+ "\nCorreo electronico : "+correoElectronico+"\nDireccion fiscal: " +  direccion + "\n";
	}

	
}
