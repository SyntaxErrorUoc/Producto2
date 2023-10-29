package vista;

import java.time.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

import com.sun.source.tree.TryTree;
import controlador.Controlador;

import javax.sound.midi.Soundbank;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GestionOS {

	private Controlador controlador;

	Scanner teclado = new Scanner(System.in);

	public GestionOS() {
		controlador = new Controlador();
	}
	public void inicio() {

		introducirBase();
		boolean salir = false;
		int opcion;


		do {
			try {

				mostrarMenu("Gestion de articulos","Gestion de clientes","Gestion de pedidos");
				opcion = pedirOpcion(3);

				switch (opcion) {
					case 1:
						menuArticulo();
						break;
					case 2:
						menuClientes();
						break;
					case 3:
						menuPedidos();
						break;
					case 0:
						salir = true;
						break;
					default:
						System.out.println("Opcion no válida. Introduce un valor valido.");
						break;

				}
			}catch(InputMismatchException e){
				System.out.println(" dato introducido no valido, vuelve a introducirlo");
				teclado.nextLine();
			}
		}
		while (!salir);

	}
	public void menuArticulo() {
		boolean salir = false;
		int opcion;

		do {
			try {
				mostrarMenu("Introducir articulo", "Mostrar articulos");
				opcion = pedirOpcion(2);

				switch (opcion) {
					case 1:
						addDatosArticulo();
						break;
					case 2:
						controlador.mostrarArticulos();
						break;

					case 0:
						salir = true;
						break;
					default:
						System.out.println("Opcion no válida. Introduce un valor valido.");
						break;
				}
			}catch(InputMismatchException e){
				System.out.println("Vuelve a introducir el valor correcto");
				teclado.nextLine();
			}
		}
		while (!salir);

	}
	public void menuClientes() {

		boolean salir = false;
		int opcion;

		do {
			try {
				mostrarMenu("Insertar cliente", "Mostrar Clientes", "Mostrar clientes premium", "Mostrar clientes standard");
				opcion = pedirOpcion(4);


				switch (opcion) {
					case 1:
						addDatosCliente();
						break;
					case 2:
						controlador.mostrarCliente();
						break;
					case 3:
						controlador.mostrarClientePremium();
						break;
					case 4:
						controlador.mostrarClienteStandard();
						break;
					case 0:
						salir = true;
						break;

				}
			}catch(InputMismatchException e){
				System.out.println("Valor introducido incorrecto. Vuelve a introducir el dato");
				teclado.nextLine();
			}
		}
		while (!salir);

	}
	public void menuPedidos() {
		boolean salir = false;
		int opcion;
		String filtr;
		String mail;

		do {
			try {
				mostrarMenu("Insertar pedido", "Eliminar pedido", "Mostrar pedidos pendientes", "Mostrar pedidos enviados");
				opcion = pedirOpcion(4);

				switch (opcion) {
					case 1:
						addPedido();
						break;
					case 2:
						deletePedido();
						break;
					case 3:
						System.out.println("Desea filtrar por cliente(S/N)");
						filtr = teclado.nextLine();
						if (filtr.equals("N")) {
							controlador.mostrarPedido(false);
						}else if (filtr.equals("S")){
							System.out.println("Introduce el correo electronico del cliente:");
							mail = teclado.nextLine();
							controlador.mostrarPedido(false,mail);
						}else{
							System.out.println("Introduce un valor correcto");
						}
						break;
					case 4:
						System.out.println("Desea filtrar por cliente(S/N)");
						filtr = teclado.nextLine();
						if (filtr.equals("N")) {
							controlador.mostrarPedido(true);
						}else if (filtr.equals("S")){
							System.out.println("Introduce el correo electronico del cliente:");
							mail = teclado.nextLine();
							controlador.mostrarPedido(true,mail);
						}else{
							System.out.println("Introduce un valor correcto");
						}
						break;
					case 0:
						salir = true;
						break;

				}
			}catch(InputMismatchException e ) {
				System.out.println("El valor introducido no es correcto. Introduce un nuevo valor");
				teclado.nextLine();
			}
		}
		while (!salir);
	}
	public void mostrarMenu(String... Opciones){
		System.out.println("*****************************");
		for (int i = 0;i< Opciones.length;i++){
			System.out.printf("%d. %s%n", i +1 ,Opciones[i]);
		}
		System.out.println( "0. Pâra salir ");
	}
	public int pedirOpcion(int OpcionMax){
		int resultado;
		System.out.printf("Selecciona una opcion(1 a %d o 0)\n", OpcionMax);
		resultado = teclado.nextInt();
		teclado.nextLine();
		return resultado;
	}
	public void addDatosCliente(){

		try {

			String mail;
			String name;
			String VIP;
			String dir;
			double descuento = controlador.obtenerDescuento();

			System.out.println("Introduce el mail");
			mail = teclado.nextLine();
			while(!validaEmilio(mail)){
				System.out.println("Mail no valido , intentalo de nuevo.");
				System.out.println("Introduce el correo electronico:");
				mail = teclado.nextLine();
			}
			System.out.println(" Introduce la direccion");
			dir = teclado.nextLine();
			System.out.println("introduce el nombre");
			name = teclado.nextLine();
			System.out.println("Es cliente VIP?(S/N)");
			VIP = teclado.nextLine();
			if (VIP.equals("S")) {
				controlador.addCliente(mail, name, dir, descuento);
			} else {

				controlador.addCliente(mail, name, dir);
			}
		}catch(InputMismatchException e ){
			System.out.println("Entrada no valida. Vuelve a introducir los datos");
			teclado.nextLine();
		}catch(Exception e){
			System.out.println("Ha ocurrido un error: "+ e.getMessage());
		}

	}
	public boolean validaEmilio(String emilio){
		String regex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher((emilio));
		return matcher.matches();
	}
	public void addPedido(){
		try {
			String cl;
			String art;
			int cantidad;
			double costeE;
			LocalDateTime fechaHora;
			boolean envio;
			int np;
			String fecha;
			String hora;
			String fechahora;

			System.out.println("Introduce nombre cliente");
			cl = teclado.nextLine();
			System.out.println("Introduce el codigo de articulo");
			art = teclado.nextLine();
			System.out.println("Introduce la cantidad");
			cantidad = teclado.nextInt();
			teclado.nextLine();
			System.out.println("introduce el numero de pedido");
			np = teclado.nextInt();
			teclado.nextLine();
			System.out.println("introduce la fecha ");
			fecha = teclado.nextLine();
			System.out.println("introduce la hora ");
			hora = teclado.nextLine();
			fechahora = (fecha+"T"+hora);
			System.out.println(fechahora);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
			fechaHora = LocalDateTime.parse(fechahora, formatter);
			System.out.println("esta enviado?");
			envio = teclado.nextBoolean();
			teclado.nextLine();
			System.out.println("introduce precio envio");
			costeE = teclado.nextDouble();
			teclado.nextLine();
			controlador.addPedido(cl, art, np, fechaHora, cantidad, envio, costeE);
		}catch(InputMismatchException e){
			System.out.println(" dato introducido no valido, vuelve a introducirlo");
			teclado.nextLine();
			addPedido();
		}
	}
	public void addDatosArticulo(){
		try {
			String cp;
			String desc;
			double precio;
			Duration tiempoPrep;

			System.out.println("introduce el cp");
			cp = teclado.nextLine();
			System.out.println(" Introduce la descripcion");
			desc = teclado.nextLine();
			System.out.println("introduce el precio del articulo(0,0)");
			precio = teclado.nextDouble();
			teclado.nextLine();
			System.out.println("introduce el timepo de preparacion en horas(HH:MM):");
			String tiempo = teclado.nextLine();
			String[] partes = tiempo.split(":");
			if (partes.length == 2){
				try{
					int horas = Integer.parseInt(partes[0]);
					int minutos = Integer.parseInt(partes[1]);
					tiempoPrep = Duration.ofHours(horas).plusMinutes(minutos);
					System.out.println(tiempoPrep);
					controlador.addArticulo(cp, desc, precio,tiempoPrep);
				}catch(NumberFormatException e){
					System.out.println("La hora no es valida");
				}
			}
		}catch(InputMismatchException e){
			System.out.println(" dato introducido no valido, vuelve a introducirlo");
			teclado.nextLine();
			addDatosArticulo();
		}
	}
	public void deletePedido(){

		int cp;
		Boolean eliminado;
		System.out.println("introduce el numero de pedido a eliminar");
		cp = teclado.nextInt();
		teclado.nextLine();
		eliminado = controlador.deletePedido(cp);
		if (eliminado){
			System.out.println("El pedido se ha eliminado");
		}else{
			System.out.println("El pedido no se ha eliminado, fecha de preparacion vencida");
		}
	}
	public void introducirBase(){
		controlador.addCliente("jose@gmail","jose","camino1");
		controlador.addCliente("pepe@gmail","pepe","camino2");
		controlador.addCliente("paco@gmail","paco","camino3");
		controlador.addCliente("juan@gmail","juan","camino1");
		controlador.addCliente("mario@gmail","mario","camino2");
		controlador.addCliente("laura@gmail","laura","camino3");

		controlador.addArticulo("cp","ordenador",120,Duration.parse("PT180H30M"));
		controlador.addArticulo("mc","movil",120,Duration.parse("PT360H40M"));
		controlador.addArticulo("ja","tablet",120,Duration.parse("PT720H20M"));

		controlador.addPedido("pepe","cp",1,LocalDateTime.parse("1920-12-12T23:00:00"),5,true,12.0);
		controlador.addPedido("paco","mc",2,LocalDateTime.parse("2023-10-29T23:00:00"),2,false,12.0);
		controlador.addPedido("jose","ja",3,LocalDateTime.parse("1920-12-12T23:00:00"),6,false,12.0);
		controlador.addPedido("juan","cp",1,LocalDateTime.parse("1920-12-12T23:00:00"),5,true,12.0);
		controlador.addPedido("mario","mc",2,LocalDateTime.parse("1920-12-12T23:00:00"),2,true,12.0);
		controlador.addPedido("laura","ja",3,LocalDateTime.parse("1920-12-12T23:00:00"),6,true,12.0);
		controlador.addPedido("laura","pc",3,LocalDateTime.parse("1920-12-12T23:00:00"),12,true,12.0);
	}

}



