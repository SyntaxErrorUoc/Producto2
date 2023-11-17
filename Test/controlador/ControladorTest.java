package controlador;

import ConexionMySQL.DatabaseConnectionException;
import modelo.Articulo;
import modelo.Cliente;
import modelo.Pedido;
import modelo.Datos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ControladorTest {
    private Controlador controlador;
    private Datos datos;

    @BeforeEach
    public void setUp() throws DatabaseConnectionException {
        controlador = new Controlador();
    }

    @Test
    public void tesComprobarPreparacion() throws DatabaseConnectionException {

        Controlador controlador = new Controlador();
        LocalDateTime fechaActual = LocalDateTime.now();
        Duration tiempoPrep = Duration.ofHours(2);

        assertFalse(controlador.comprobarPreparacion(fechaActual, tiempoPrep), "El metodo debe devolver falso como current antes de la preparacion termine.");

    }

    public String convertirDuration(Duration duration){
        // Hay que pasar Duration a String
        String cadena = duration.toString(); // formato ISO
        // formato personalizado
        return String.format("%02d:%02d:%02d",
                duration.toHours(),
                duration.toMinutesPart(),
                duration.toSecondsPart());
    }

    @Test
    public void testDeletePedido() throws SQLException, ClassNotFoundException {
        //este es el junit para el deletepedido
        controlador.addArticulo("cp","ordenador",120,Duration.parse("PT180H30M"));
        controlador.addCliente("pepe@gmail","pepe","camino2");
        controlador.addPedido("pepe","cp",1,LocalDateTime.parse("1920-12-12T23:00:00"),5,true,12.0);

        assertFalse(controlador.deletePedido(1), "El pedido debería ser eliminado.");
    }

}