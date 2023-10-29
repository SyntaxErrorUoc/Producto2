package OnlineStore.vista;

import java.awt.event.MouseEvent;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import OnlineStore.controlador.*;

/**
 * Online Store
 */
public class GestionOS {


    public controlador InstanciaControlador = new controlador();

    /**
     * Inicio del programa
     * @param args En caso de pasar argumentos al programa.
     */
    public static void main(String[] args){
        GestionOS prg = new GestionOS();
        prg.inicio();
    }

    /**
     * Método para seleccionar una opción
     * @return Valor de la tecla pulsada.
     */


    public void inicio(){
        boolean salir = false;
        char opcio;
        do {
            System.out.println("+-------------------------------------------+");
            System.out.println("| Online Store - Menú principal             |");
            System.out.println("+-------------------------------------------+\n");
            System.out.println("1. Clientes");
            System.out.println("2. Artículos");
            System.out.println("3. Pedidos");
            System.out.println("············································");
            System.out.println("0. Salir de la aplicación");
            System.out.println("============================================");
            opcio = InstanciaControlador.demanarOpcioMenu();
            switch (opcio) {
                case '1' -> clientes();
                case '2' -> articulos();
                case '3' -> pedidos();
                case '0' -> salir = true;
                default -> salir =false;
            }
        } while (!salir);
    }


    public void pedidos(){
        boolean salir = false;
        char opcio;
        do {
            System.out.println("+-------------------------------------------+");
            System.out.println("| Online Store - Menú Pedidos               |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1. Añadir Pedido                          |");
            System.out.println("| 2. Eliminar Pedido                        |");
            System.out.println("| 3. Mostrar Pedidos pendientes de envío    |");
            System.out.println("| 4. Mostrar Pedidos enviados               |");
            System.out.println("+···········································+");
            System.out.println("| 0. Salir de la aplicación                 |");
            System.out.println("=============================================");
            opcio = InstanciaControlador.demanarOpcioMenu();
            switch (opcio) {
                case '1' -> InstanciaControlador.nuevopedido();
                case '2' -> InstanciaControlador.eliminarpedido();
                case '3' -> InstanciaControlador.listarpedidospendientes();
                case '4' -> InstanciaControlador.listarpedidosenviados();
                case '0' -> salir = true;
                default -> salir =false;
            }
        } while (!salir);
    }

    /**
     * Menú de artículos
     */
    public void articulos(){
        boolean salir = false;
        char opcio;
        do {
            System.out.println("+-------------------------------------------+");
            System.out.println("| Online Store - Menú Artículos             |");
            System.out.println("+-------------------------------------------+\n");
            System.out.println("1. Crear un artículo");
            System.out.println("2. Eliminar un artículo");
            System.out.println("3. Listar artículos");
            System.out.println("············································");
            System.out.println("0. Salir de la aplicación");
            System.out.println("============================================");
            opcio = InstanciaControlador.demanarOpcioMenu();
            switch (opcio) {
                case '1' -> InstanciaControlador.nuevoarticulo();
                case '2' -> InstanciaControlador.borrararticulo();
                case '3' -> InstanciaControlador.listararticulos();
                case '0' -> salir = true;
                default -> salir = false;
            }
        } while (!salir);
    }



    public void clientes(){
        boolean salir = false;
        char opcio;
        do {
            System.out.println("+-------------------------------------------+");
            System.out.println("| Online Store - Menú Clientes              |");
            System.out.println("+-------------------------------------------+\n");
            System.out.println("1. Nuevo cliente");
            System.out.println("2. Borrar cliente");
            System.out.println("3. Ver clientes");
            System.out.println("4. Listado clientes Standard");
            System.out.println("5. Listado clientes Premium");
            System.out.println("············································");
            System.out.println("0. Volver al menú principal");
            System.out.println("============================================");
            opcio = InstanciaControlador.demanarOpcioMenu();
            switch (opcio) {
                case '1' -> InstanciaControlador.nuevocliente();
                case '2' -> InstanciaControlador.borrarcliente();
                case '3' -> InstanciaControlador.vercliente();
                case '4' -> InstanciaControlador.listarclientepestandard();
                case '5' -> InstanciaControlador.listarclientepremium();
                case '0' -> salir = true;
                default -> salir = false;
            }
        } while (!salir);
    }


}
