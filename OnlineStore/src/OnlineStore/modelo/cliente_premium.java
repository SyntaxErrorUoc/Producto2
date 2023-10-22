package OnlineStore.modelo;

public class cliente_premium extends cliente {

    /**
     * Al ser valores fijos las declaro como Constantes.
     */
    public static final float cuota = 30.00f;
    public static final float descuento = 0.20f;
    public static final String TipoCliente = "PREMIUM";

    public cliente_premium(String Mail, String NIF, String Nombre, String Domicilio){
        super(Mail, NIF, Nombre, Domicilio);
    }

    /**
     * Getters y Setters de la clase
     */

    /**
     * Método que devuelve el importe de la cuota del Modelo.cliente.
     * @return Cuota del Modelo.cliente
     */
    public float getCuota() {
        return cuota;
    }

    /**
     * Método que edevuelve el Descuento a aplicar al Modelo.cliente
     * @return Descuento del Modelo.cliente.
     */
    public float getDescuento() {
        return descuento;
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
        return  this.TipoCliente;
    }

    /**
     * Método que devuelve la cuota y el descuento a aplicar al Modelo.cliente.
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
