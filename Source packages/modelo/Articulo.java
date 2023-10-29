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


	public Duration getTiempoPreparacion() {
		return tiempoPreparacion;
	}

	public void setTiempoPreparacion(Duration tiempoPreparacion) {
		this.tiempoPreparacion = tiempoPreparacion;
	}

	// Constructor
	public Articulo(String codigo, String descripcion, double precio, Duration tiempoPreparacion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.tiempoPreparacion = tiempoPreparacion;
	}
	//constructor sobrecargado
	public Articulo() {
	}


	// Getters
	public String getCodigo() {
		return codigo;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public double getPrecio() {
		return precio;
	}

	//Setters
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "\nArticulo [codigo=" + codigo + ", descripcion=" + descripcion + ", precio=" + precio + "]\n";
	}


    

}
