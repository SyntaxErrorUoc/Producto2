package OnlineStore.vista;

import java.util.Scanner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import OnlineStore.modelo.*;
import OnlineStore.controlador.*;

/**
 * Online Store
 */
public class GestionOS {
    Scanner teclado = new Scanner(System.in);

    public Datos ModeloInicial = new Datos();
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
    char demanarOpcioMenu() {
        String resp;
        System.out.print("Elige una opción (1,2,3,4 o 0): ");
        resp = teclado.nextLine();
        if (resp.isEmpty()) {
            resp = " ";
        }
        return resp.charAt(0);
    }

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
            opcio = demanarOpcioMenu();
            switch (opcio) {
                case '1' -> clientes();
                case '2' -> articulos();
                case '3' -> pedidos();
                case '0' -> salir = true;
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
            System.out.println("| 1. Añadir Pedido");
            System.out.println("| 2. Eliminar Pedido");
            System.out.println("| 4. Mostrar Pedidos pendientes de envío");
            System.out.println("+············································");
            System.out.println("| 0. Salir de la aplicación");
            System.out.println("=============================================");
            opcio = demanarOpcioMenu();
            switch (opcio) {
                case '1' -> nuevopedido();
                case '2' -> eliminarpedido();
                case '3' -> listarpedidospendientes();
                case '4' -> listarpedidosenviados();
                case '0' -> salir = true;
            }
        } while (!salir);
    }

    /**
     * Método para introducir pedidos nuevos
     */
    public void nuevopedido(){
        int numpedido;
        cliente Cliente;
        articulo Articulo;

        int unidades;

        LocalDateTime fechaYHoraActual = LocalDateTime.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        //LocalDate fechapedido = LocalDate.parse(fechaYHoraActual, formatoFecha);
        //LocalTime horapedido = LocalTime.parse(fechaYHoraActual, formatoHora);

        System.out.println("---------------------------------");
        System.out.println("| CREAR NUEVO PEDIDO");
        System.out.println("---------------------------------\n");





    }

    /**
     * Método para eliminar un Modelo.pedido
     */
    public void eliminarpedido(){
        String Opcion;
        ModeloInicial.listaclienteindice();
        System.out.println("Selecciona el índice para eliminarlo. (E)) para salir.");
        Opcion = teclado.nextLine();
        if (Opcion.equals("E")){
            System.out.println("Abortado por el usuario.");
        }else{
            ModeloInicial.dropcliente(Integer.parseInt(Opcion));
        }
    }

    /**
     * Método para ver un Modelo.pedido
     */
    public void verpedido(){

    }

    /**
     * Método para ver los pedidos pendientes
     */
    public void listarpedidospendientes(){

    }

    /**
     * Método para ver los pedidos enviados
     */
    public void listarpedidosenviados(){

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
            opcio = demanarOpcioMenu();
            switch (opcio) {
                case '1' -> nuevoarticulo();
                case '2' -> borrararticulo();
                case '3' -> listararticulos();
                case '0' -> salir = true;
            }
        } while (!salir);
    }


    /**
     * Crear un nuevo artículo en la BBDD de Online Store
     */
    public void nuevoarticulo(){
        String codigo;
        String descripcion;
        double precioventa;
        double gastosenvio;
        LocalTime tiempopreparacion;
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String Tiempo;

        System.out.println("----------------------------");
        System.out.println("| NUEVO ARTICULO            ");
        System.out.println("----------------------------\n");

        System.out.print("Código artículo : ");
        codigo = teclado.nextLine();
        System.out.print("Descripción     : ");
        descripcion = teclado.nextLine();
        System.out.print("Previo venta €  : ");
        precioventa = Double.parseDouble(teclado.nextLine());
        System.out.print("Gastos envío €  : ");
        gastosenvio = Double.parseDouble(teclado.nextLine());
        System.out.print("Tiempo preparación HH:MM:ss : ");
        Tiempo = teclado.nextLine();
        tiempopreparacion = LocalTime.parse(Tiempo, formatoHora);

        ModeloInicial.addArticulo(new articulo (codigo, descripcion, precioventa, gastosenvio, tiempopreparacion));

    }

    /**
     * Borrar un artículo existente en la BBDD de Online Store
     */
    public void borrararticulo(){
        String Opcion;
        ModeloInicial.listaarticuloindice();
        System.out.println("Selecciona el índice para eliminarlo. (E) para salir.");
        Opcion = teclado.nextLine();
        if (Opcion.equals("E")){
            System.out.println("Abortado por el usuario.");
        }else{
            ModeloInicial.dropArticulo(Integer.parseInt(Opcion));
        }
    }

    /**
     * Listar artículos existentes en la BBDD de Online Store
     */
    public void listararticulos(){
        ModeloInicial.viewArticulos();
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
            opcio = demanarOpcioMenu();
            switch (opcio) {
                case '1' -> nuevocliente();
                case '2' -> borrarcliente();
                case '3' -> vercliente();
                case '4' -> listarclientepestandard();
                case '5' -> listarclientepremium();
                case '0' -> salir = true;
            }
        } while (!salir);
    }


    /**
     * Añadir un nuevo Modelo.cliente a la BBDD
     */
    public void nuevocliente(){
        String mail;
        String nif;
        String nombre;
        String domicilio;
        String TipoCliente;

        System.out.println("----------------------------");
        System.out.println("| NUEVO CLIENTE             ");
        System.out.println("----------------------------\n");

        System.out.print("Mail         : ");
        mail = teclado.nextLine();
        System.out.print("Nombre       : ");
        nombre = teclado.nextLine();
        System.out.print("Dirección    : ");
        domicilio = teclado.nextLine();
        System.out.print("NIF          : ");
        nif = teclado.nextLine();
        System.out.print("Tipo Cliente (P) PREMIUM / (E) ESTANDARD: ");
        TipoCliente = teclado.nextLine();
        if (TipoCliente.equals("P")){
            ModeloInicial.addClientePremium(new cliente_premium(mail, nif, nombre, domicilio));
        }else{
            ModeloInicial.addClienteEstandard(new cliente_estandard(mail, nif, nombre, domicilio));
        }

    }

    /**
     * Dar de baja a un Modelo.cliente de la BBDD
     */
    public void borrarcliente(){
        String Opcion;
        ModeloInicial.listaclienteindice();
        System.out.println("Selecciona el índice para eliminarlo. 0 para salir.");
        Opcion = teclado.nextLine();
        if (Opcion.equals("0")){
            System.out.println("Abortado por el usuario.");
        }else{
            ModeloInicial.dropcliente(Integer.parseInt(Opcion));
        }
    }

    /**
     * Ver una ficha del Modelo.cliente
     */
    public void vercliente(){
        System.out.println("---------------------------");
        System.out.println("| LISTADO DE CLIENTES     |");
        System.out.println("---------------------------\n");
        ModeloInicial.viewClientes();
    }

    /**
     * Listar clientes estandard de Online Store
     */
    public void listarclientepestandard(){
        System.out.println("------------------------------------");
        System.out.println("| LISTADO DE CLIENTES ESTANDARD    |");
        System.out.println("------------------------------------\n");
        ModeloInicial.viewClientesPorTipo("ESTANDARD");
    }

    /**
     * Listar clientes premium de Online Store
     */
    public void listarclientepremium(){
        System.out.println("------------------------------------");
        System.out.println("| LISTADO DE CLIENTES PREMIUM      |");
        System.out.println("------------------------------------\n");
        ModeloInicial.viewClientesPorTipo("PREMIUM");
    }
}
