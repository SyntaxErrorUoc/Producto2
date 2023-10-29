package modelo;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Articulo {
	private String codigo;
    private String descripcion;
    private double precio;
	private Duration tiempoPreparacion;



	/**
	 * Constructor de la clase con 4 prámetros
	 * @param codigo tipo String
	 * @param descripcion tipo String
	 * @param precio tipo double
	 * @param tiempoPreparacion tipo Duration
	 */
	// Constructor
	public Articulo(String codigo, String descripcion, double precio, Duration tiempoPreparacion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.tiempoPreparacion = tiempoPreparacion;
	}

	/**
	 * Constructor de la clase vacío
	 */
	//constructor sobrecargado
	public Articulo() {
	}

	/**
	 * Getter de Tiempo de preparación
	 * @return Devuelve un tipo Duration
	 */
	public Duration getTiempoPreparacion() {
		return tiempoPreparacion;
	}

	/**
	 * Setter de Tiempo de preparación
	 * @param tiempoPreparacion de tipo Duration
	 */
	public void setTiempoPreparacion(Duration tiempoPreparacion) {
		this.tiempoPreparacion = tiempoPreparacion;
	}


	/**
	 * Getter de Código
	 * @return devuelve un tipo String
	 */

	public String getCodigo() {
		return codigo;
	}

	/**
	 * Getter de Descripcion
	 * @return  devuelve un tipo String
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Getter de Precio
	 * @return devuelve un double
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Setter de código
	 * @param codigo recibe un tipo String
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Setter de descripcion
	 * @param descripcion recibe un tipo String
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Setter de precio
	 * @param precio recibe un tipo double
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "\nArticulo [codigo=" + codigo + ", descripcion=" + descripcion + ", precio=" + precio + "]\n";
	}


    

}
