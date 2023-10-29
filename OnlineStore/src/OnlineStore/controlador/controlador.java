package OnlineStore.controlador;

import OnlineStore.modelo.*;
import OnlineStore.vista.GestionOS;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class controlador {

    public Datos ModeloInicial = new Datos();
    Scanner teclado = new Scanner(System.in);

    public char demanarOpcioMenu() {
        String resp;
        System.out.print("Elige una opción (0 - Salir): ");
        resp = teclado.nextLine();
        if (resp.isEmpty()) {
            resp = " ";
        }
        return resp.charAt(0);
    }

    /**
     * Método para introducir pedidos nuevos
     */
    public void nuevopedido(){
        int numpedido;
        cliente Cliente;
        int IndiceCliente;
        articulo Articulo;
        int IndiceArticulo;
        boolean flag = false;
        String fechapedido;
        String horapedido;
        int unidades;

        LocalDateTime fechaYHoraActual = LocalDateTime.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");

        do{
            try{
                System.out.println("---------------------------------");
                System.out.println("| CREAR NUEVO PEDIDO");
                System.out.println("---------------------------------\n");

                numpedido = ModeloInicial.maximopedidos()+1;
                System.out.println("Número pedido : " + numpedido);

                fechapedido = fechaYHoraActual.format(formatoFecha);
                System.out.println("Fecha del Pedido : " + fechapedido);
                horapedido = fechaYHoraActual.format(formatoHora);
                System.out.println("Hora del Pedido : " + horapedido);
                System.out.println("----------------------------------------");
                // Listar clientes para seleccionar
                do{
                    ModeloInicial.listaclienteindice();
                    System.out.print("Introduce el Índice del cliente : ");
                    IndiceCliente = Integer.parseInt(teclado.nextLine());
                } while (ModeloInicial.getClienteporIndice(IndiceCliente) == null);

                // Listar artículos para seleccionar
                do{
                    ModeloInicial.listaarticuloindice();
                    System.out.print("Introduce el Índice del artículo : ");
                    IndiceArticulo = Integer.parseInt(teclado.nextLine());
                } while (ModeloInicial.getArticuloporIndice(IndiceArticulo)==null);

                System.out.print("Unidades : ");
                unidades = Integer.parseInt(teclado.nextLine());

                // Creamos el Pedido
                ModeloInicial.addPedido(new pedido(numpedido,
                        ModeloInicial.getClienteporIndice(IndiceCliente),
                        ModeloInicial.getArticuloporIndice(IndiceArticulo),
                        LocalDate.parse(fechapedido, formatoFecha),
                        LocalTime.parse(horapedido, formatoHora),
                        unidades));
                flag = true;
            } catch (Exception e){
                System.out.println("ERROR : " + e.getMessage());
            }
        } while (!flag);

    }

    /**
     * Método para eliminar un Modelo.pedido
     */
    public void eliminarpedido(){
        String Opcion;
        ModeloInicial.listapedidoindice();
        System.out.println("Selecciona el índice para eliminarlo. (E)) para salir.");
        Opcion = teclado.nextLine();
        if (Opcion.equals("E")){
            System.out.println("Abortado por el usuario.");
        }else{
            ModeloInicial.dropPedido(Integer.parseInt(Opcion));
        }
    }

    /**
     * Método para ver los pedidos pendientes
     */
    public void listarpedidospendientes(){
        System.out.println("--------------------------------------");
        System.out.println("| PEDIDOS PENDIENTES");
        System.out.println("--------------------------------------");
        ModeloInicial.ListadodePedidos(true);
    }

    /**
     * Método para ver los pedidos enviados
     */
    public void listarpedidosenviados(){
        System.out.println("--------------------------------------");
        System.out.println("| PEDIDOS ENVIADOS");
        System.out.println("--------------------------------------");
        ModeloInicial.ListadodePedidos(false);
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

        try {
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
        } catch (Exception e){
            System.out.println("ERROR : " + e.getMessage());
        }


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


    /**
     * Añadir un nuevo Modelo.cliente a la BBDD
     */
    public void nuevocliente(){
        String mail;
        String nif;
        String nombre;

        try {
            System.out.println("----------------------------");
            System.out.println("| NUEVO CLIENTE             ");
            System.out.println("----------------------------\n");
            boolean flag;
            do{
                System.out.print("Mail         : ");
                mail = teclado.nextLine();
                if (Datos.mailok(mail)){
                    flag= !ModeloInicial.checkmail(mail);
                }else{
                    System.out.println("Mail incorrecto. Vuelva a introducirlo. ");
                    flag=false;
                }
            } while (!flag);

            System.out.print("Nombre       : ");
            nombre = teclado.nextLine();
            System.out.print("Dirección    : ");
            String domicilio = teclado.nextLine();

            do{
                System.out.print("NIF          : ");
                nif = teclado.nextLine();
                if (ModeloInicial.validarNIF(nif)){
                    flag= true;
                } else{
                    System.out.println("NIF incorrecto. Vuelva a introducirlo. ");
                    flag = false;
                }
            } while (!flag);

            String TipoCliente;
            do {
                System.out.print("Tipo Cliente (P) PREMIUM / (E) ESTANDARD: ");
                TipoCliente = teclado.nextLine();
            } while (!TipoCliente.equals("P") && !TipoCliente.equals("E"));

            if (TipoCliente.equals("P")){
                ModeloInicial.addClientePremium(new cliente_premium(mail, nif, nombre, domicilio));
            }else{
                ModeloInicial.addClienteEstandard(new cliente_estandard(mail, nif, nombre, domicilio));
            }
        } catch (Exception e) {
            // Manejo de excepciones si ocurre un error al agregar el elemento
            System.out.println("ERROR: " + e.getMessage());
        }


    }

    /**
     * Dar de baja a un cliente de la lista
     */
    public void borrarcliente(){
        String Opcion;
        ModeloInicial.listaclienteindice();
        System.out.println("Selecciona el índice para eliminarlo. S para salir.");
        Opcion = teclado.nextLine();
        if (Opcion.equals("S")){
            System.out.println("Abortado por el usuario.");
        }else{
            try {
                ModeloInicial.dropcliente(Integer.parseInt(Opcion));
            } catch (Exception e) {
                // Manejo de excepciones si ocurre un error al agregar el elemento
                System.out.println("ERROR: " + e.getMessage());
            }

        }
    }

    /**
     * Ver una ficha del cliente
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
