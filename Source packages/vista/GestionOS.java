package vista;

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
						controlador.mostrarPedido(false);
						break;
					case 4:
						controlador.mostrarPedido(true);
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
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
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
			LocalDate fechaHora;
			boolean envio;
			int np;

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
			System.out.println("introduce la fecha y hora");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			fechaHora = LocalDate.parse(teclado.nextLine(), formatter);
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

			System.out.println("introduce el cp");
			cp = teclado.nextLine();
			System.out.println(" Introduce la descripcion");
			desc = teclado.nextLine();
			System.out.println("introduce el precio del articulo(0,0)");
			precio = teclado.nextDouble();
			teclado.nextLine();
			controlador.addArticulo(cp, desc, precio);
		}catch(InputMismatchException e){
			System.out.println(" dato introducido no valido, vuelve a introducirlo");
			teclado.nextLine();
			addDatosArticulo();
		}
	}
	public void deletePedido(){

		int cp;
		System.out.println("introduce el numero de pedido a eliminar");
		cp = teclado.nextInt();
		teclado.nextLine();

		controlador.deletePedido(cp);
	}
	public void introducirBase(){
		controlador.addCliente("jose@gmail","jose","camino1");
		controlador.addCliente("pepe@gmail","pepe","camino2");
		controlador.addCliente("paco@gmail","paco","camino3");

		controlador.addArticulo("cp","ordenador",120);
		controlador.addArticulo("mc","movil",120);
		controlador.addArticulo("ja","tablet",120);

		controlador.addPedido("pepe","cp",1,LocalDate.parse("1920-12-12"),5,true,12.0);
		controlador.addPedido("paco","mc",2,LocalDate.parse("1920-12-12"),2,false,12.0);
		controlador.addPedido("jose","ja",3,LocalDate.parse("1920-12-12"),6,false,12.0);
	}

}



