package OnlineStore.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class pedido {

    private int numpedido;
    private cliente Cliente;
    private articulo Articulo;
    private LocalDate fechapedido;
    private LocalTime horapedido;
    private int unidades;

    public pedido(int NumeroPedido, cliente Cliente, articulo Articulo, LocalDate FechaPedido, LocalTime HoraPedido, int Unidades){
        this.numpedido= NumeroPedido;
        this.Cliente = Cliente;
        this.Articulo = Articulo;
        this.fechapedido= FechaPedido;
        this.horapedido = HoraPedido;
        this.unidades = Unidades;
    }

    /**
     * Getters y Setters para la clase pedido
     */

    public int getNumpedido() {
        return numpedido;
    }

    public void setNumpedido(int numpedido) {
        this.numpedido = numpedido;
    }

    public cliente getCliente() {
        return Cliente;
    }

    public void setCliente(cliente cliente) {
        Cliente = cliente;
    }

    public articulo getArticulo() {
        return Articulo;
    }

    public void setArticulo(articulo articulo) {
        Articulo = articulo;
    }

    public LocalDate getFechapedido() {
        return fechapedido;
    }

    public void setFechapedido(LocalDate fechapedido) {
        this.fechapedido = fechapedido;
    }

    public LocalTime getHorapedido() {
        return horapedido;
    }

    public void setHorapedido(LocalTime horapedido) {
        this.horapedido = horapedido;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public void eliminarpedido(int numpedido){

    }

    /**
     * Otros métodos
     */

    public double pedidoEnviado(){

        return 5.5;
    };

    public float precioEnvio(){

        return 6.5F;
    };


    public void MuestraTicket(int Pedido){
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("| Nº Pedido    : " + this.getNumpedido());
        System.out.println("| Cliente      : " + this.getCliente() );
        System.out.println("| Fecha Pedido : " + this.getFechapedido() + " Hora : " + this.getHorapedido() );
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println(this.getArticulo().toString() + " CANTIDAD : " + this.getUnidades());
        double subtotalpedido;
        double totalpedido;
        subtotalpedido = this.getArticulo().getPrecioventa() + this.getUnidades();
        totalpedido = subtotalpedido + this.getArticulo().getGastosenvio();
        System.out.println("Importe pedido    : " + String.format("%-8s", subtotalpedido));
        System.out.println("Importe descuento : " + String.format("%-8s", this.getArticulo().getGastosenvio()));
        System.out.println("TOTAL             : " + String.format("%-8s", totalpedido));
        System.out.println("------------------------------------------------------------------------------------");
    }

}
