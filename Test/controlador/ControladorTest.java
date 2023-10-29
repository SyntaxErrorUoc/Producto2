package controlador;

import modelo.Articulo;
import modelo.Cliente;
import modelo.Pedido;
import modelo.Datos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ControladorTest {
    private Controlador controlador;
    private Datos datos;

    @BeforeEach
    public void setUp(){
        controlador = new Controlador();
    }

    @Test
    public void tesComprobarPreparacion() {
        Controlador controlador = new Controlador();
        LocalDateTime fechaActual = LocalDateTime.now();
        Duration tiempoPrep = Duration.ofHours(2);

        assertFalse(controlador.comprobarPreparacion(fechaActual, tiempoPrep), "The method should return false as the current time is before the preparation end time.");

    }


    @Test
    public void testDeletePedido() {
        // Tienes que agregar lógica aquí para crear un pedido primero y luego intentar borrarlo.

        Articulo art;
        Cliente cl;
        Pedido pd;
        int indexArt;
        int  indexCl;
        indexArt = 1;
        LocalDateTime fechaActual = LocalDateTime.of(2023, 10, 29, 12, 0);
        art = datos.getListaArticulos().getLista().get(indexArt);
        indexCl = datos.devolverIndiceCliente("Pepe");
        cl = datos.getListaClientes().getLista().get(indexCl);
        pd = new Pedido(1,fechaActual,cl,art,1,true,12.0);
        datos.agregarPedido(pd);




        //controlador.addPedido("Pepe","1234",1,LocalDateTime.parse("1920-12-12T23:00:00"),5,true,12.0);

        assertTrue(controlador.deletePedido(1), "El pedido debería ser eliminado.");
    }

}