package OnlineStore.modelo;

/**
 * Clase Modelo.cliente estandard
 * Clase hija de Modelo.cliente.
 */

public class cliente_estandard extends cliente {
    public static final float cuota = 0.0f;
    public static final float descuento = 0.0f;
    public static final String TipoCliente = "ESTANDARD";

    /**
     * Constructor de la clase padre Cliente
     *
     * @param NIF       NIF del Modelo.cliente
     * @param Nombre    Nombre del Modelo.cliente
     * @param Domicilio Domicilio del Modelo.cliente
     */
    public cliente_estandard(String Mail, String NIF, String Nombre, String Domicilio) {
        super(Mail, NIF, Nombre, Domicilio);
    }

    /**
     * Otros métodos
     */

    @Override
    public float descuentoEnv() {
        return this.descuento;
    }

    @Override
    public float calcAnual() {
        return this.cuota;
    }

    @Override
    public String tipoCliente() {
        return this.TipoCliente;
    }

    /**
     * Método que devuelve datos del artículo.
     * @return Detalle del artículo.
     */
    @Override
    public String toString() {
        return super.toString()
                + "\t Tipo : " + String.format("%-12s", this.TipoCliente)
                + "\t Cuota : " + this.cuota + "€"
                + "\t Descuento : " + (this.descuento*100) + "%"
                + "\n";
    }
}
