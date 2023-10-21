package syntaxerror.modelo;

public class Articulo {
	private String codigo;
    private String descripcion;
    private double precio;
    
    
    // Constructor
	public Articulo(String codigo, String descripcion, double precio) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.precio = precio;
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
		return "Articulo [codigo=" + codigo + ", descripcion=" + descripcion + ", precio=" + precio + "]";
	}


    

}
