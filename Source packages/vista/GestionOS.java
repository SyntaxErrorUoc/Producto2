package vista;

import controlador.Controlador;
import modelo.Cliente;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SyntaxError
 * @version 2.0.1
 */
public class GestionOS {

	private Controlador controlador;

	Scanner teclado = new Scanner(System.in);

	/**
	 * Constructor de la clase que inicializa el atributo controlador
	 */
	public GestionOS() {
		controlador = new Controlador();
	}

	/**
	 * Metodo inicial que muestra el menu inicial
	 */
	public void inicio() throws SQLException, ClassNotFoundException {

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
						System.out.println("Opción no válida. Introduce un valor valido.");
						break;

				}
			}catch(InputMismatchException e){
				System.out.println("Dato introducido no valido, vuelve a introducirlo");
				teclado.nextLine();
			}
		}
		while (!salir);

	}

	/**
	 * Metodo para mostrar el menu articulos
	 */
	public void menuArticulo() {
		boolean salir = false;
		int opcion;

		do {
			try {
				mostrarMenu("Introducir articulo", "Mostrar articulos","Mostrar un articulo","Eliminar articulo","Modificar Articulo");
				opcion = pedirOpcion(5);

				switch (opcion) {
					case 1:
						addDatosArticulo();
						break;
					case 2:
						System.out.println(controlador.mostrarArticulos());
						break;
					case 3:
						mostrarUnArticulo();

						break;
					case 4:
						deleteArticulo();
						break;

					case 5:
						modificarArticulo();
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
	/**
	 * Metodo para mostrar el menu clientes
	 */
	public void menuClientes() {

		boolean salir = false;
		int opcion;

		do {
			try {
				mostrarMenu("Insertar cliente", "Mostrar Clientes", "Mostrar clientes premium", "Mostrar clientes standard","Buscar cliente","Eliminar Cliente","Modificar cliente");
				opcion = pedirOpcion(7);


				switch (opcion) {
					case 1:
						addDatosCliente();
						break;
					case 2:
						System.out.println(controlador.mostrarCliente());
						break;
					case 3:
						mostrarPorTipoCliente("1");
						break;
					case 4:
						mostrarPorTipoCliente("0");
						break;
					case 5:
						buscarCliente();
						break;
					case 6:
						deleteCliente();
						break;
					case 7:
						modificarCliente();
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
	/**
	 * Metodo para mostrar el menu Pedidos
	 */
	public void menuPedidos() {
		boolean salir = false;
		int opcion;
		String filtr;
		String mail;

		do {
			try {
				mostrarMenu("Insertar pedido", "Eliminar pedido", "Mostrar pedidos pendientes", "Mostrar pedidos enviados", "Mostrar un Pedido", "Mostrar todos los pedidos");
				opcion = pedirOpcion(5);

				switch (opcion) {
					case 1:
						addDatosPedido();
						break;
					case 2:
						deletePedido();
						break;
					case 3:
						mostrarPedidosEnPrep();
						break;
					case 4:
						mostrarPedidosEnviados();
						break;
					case 5:
						buscarPedido();
						break;
					case 6:
						mostrarTodosLosPedidos();
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

	/**
	 * Metodo para mostrar el menu las opciones de todos los menus
	 */
	public void mostrarMenu(String... Opciones){
		System.out.println("*****************************");
		for (int i = 0;i< Opciones.length;i++){
			System.out.printf("%d. %s%n", i +1 ,Opciones[i]);
		}
		System.out.println("*****************************");
		System.out.println( "0. Para salir ");
	}
	/**
	 * Metodo para mostrar pedir la opcion de todos los menus
	 */
	public int pedirOpcion(int OpcionMax){
		int resultado;
		System.out.printf("Selecciona una opción(1 a %d o 0)\n", OpcionMax);
		resultado = teclado.nextInt();
		teclado.nextLine();
		return resultado;
	}

	/**
	 * Metodo para añadir clientes
	 */


	/**
	 * Metodo para validar Email
	 * @param emilio de tipo string
	 * @return devuelve un boolean
	 */
	public boolean validaEmilio(String emilio){
		String regex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher((emilio));
		return matcher.matches();
	}

	//Metodos para Articulo
	public void addDatosArticulo(){
		try {
			String cp;
			String desc;
			double precio;
			Duration tiempoPrep;

			System.out.println("introduce el cp");
			cp = teclado.nextLine();
			System.out.println("Introduce la descripcion");
			desc = teclado.nextLine();
			System.out.println("introduce el precio del articulo(0,0)");
			precio = teclado.nextDouble();
			teclado.nextLine();
			System.out.println("introduce el tiempo de preparacion en horas(HH:MM):");
			String tiempo = teclado.nextLine();
			String[] partes = tiempo.split(":");
			if (partes.length == 2){
				try{
					int horas = Integer.parseInt(partes[0]);
					int minutos = Integer.parseInt(partes[1]);
					tiempoPrep = Duration.ofHours(horas).plusMinutes(minutos);

					// -------------------------------------------------------------
					controlador.addArticulo(cp, desc, precio,tiempoPrep);
					// -------------------------------------------------------------
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
	public void deleteArticulo() {
		String cp;
		System.out.println("introduce el codigo del articulo a eliminar:");
		cp = teclado.nextLine();
		controlador.eliminarArticulo(cp);
	}
	public void modificarArticulo(){

		String cp;
		String desc;
		double precio;
		String tiempo;
		Duration tiempoP;

		System.out.println("Introduce el CP del articulo a modificar:");
		cp = teclado.nextLine();
		if (controlador.articuloExiste(cp)) {
			System.out.println("Introduce la nueva descripcion");
			desc = teclado.nextLine();
			System.out.println("Introduce el nuevo precio o el anterior");
			precio = teclado.nextDouble();
			teclado.nextLine();
			System.out.println("Introduce el nuevo tiempo de preparacion en horas()");
			tiempo = teclado.nextLine();
			String[] partes = tiempo.split(":");
			if (partes.length == 2) {
				try {
					int horas = Integer.parseInt(partes[0]);
					int minutos = Integer.parseInt(partes[1]);
					tiempoP = Duration.ofHours(horas).plusMinutes(minutos);

					// -------------------------------------------------------------
					controlador.modArticulo(cp, desc, precio, tiempoP);
					// -------------------------------------------------------------
				} catch (NumberFormatException e) {
					System.out.println("La hora no es valida");
				}

			} else {
				System.out.println("El articulo no existe");
			}
		}

	}
	public void mostrarUnArticulo(){

		String cp;
		System.out.println("Introduce el codigo de articulo a buscar");
		cp = teclado.nextLine();
		if(!controlador.articuloExiste(cp)){
			System.out.println("El articulo introducido no esta en la lista.");
		}else{
			System.out.println(controlador.mostrarArticuloIndividual(cp));;
		}
	}

	//Metodos Para Cliente
	public void addDatosCliente(){

		try {

			String mail;
			String name;
			String VIP;
			String dir;
			double descuento = controlador.obtenerDescuento();

			System.out.println("Introduce el mail");
			mail = teclado.nextLine();

			if(controlador.emailExiste(mail)){
				System.out.println("El cliente ya existe");
				return;
			}
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
	public void deleteCliente(){

		Boolean existe;

		System.out.println("Introduce el correo a eliminar");
		String mail = teclado.nextLine();
		existe = controlador.eliminarCliente(mail);
		if(!existe){
			System.out.println("EL cliente no existe");
		}else{
			System.out.println("Cliente eliminado");
		}

	}
	public void modificarCliente(){

		String mail;
		String nombre;
		String direccion;
		String vip;
		double	desc;
		System.out.println("Introduce el mail a cmabiar");
		mail = teclado.nextLine();
		System.out.println("Introduce nuevo nombre");
		nombre = teclado.nextLine();
		System.out.println("Introduce la nueva direccion");
		direccion = teclado.nextLine();
		System.out.println("Es cliente VIP (S/N)");
		vip = teclado.nextLine();
		if (vip.equals("S")){
			System.out.println("Introduce el descuento");
			desc = teclado.nextDouble();
			teclado.nextLine();
			controlador.modificarCliente(mail,nombre,direccion,desc);
		}else if(vip.equals("N")){
			controlador.modificarCliente(mail,nombre,direccion);
		}else{
			System.out.println("EL valor introducido no es valido");
		}

	}
	public void mostrarPorTipoCliente(String value){
		ArrayList<Cliente> array= controlador.mostrarClientesPorTipo("vip",value);

		for (Cliente c: array ){

			System.out.println(c);
		}

	}
	public void buscarCliente(){

		String mail;
		System.out.println("Introduce el correo electrónico");
		mail = teclado.nextLine();

		if (controlador.emailExiste(mail)){
			System.out.println(controlador.buscarCliente(mail));
		}else{
			System.out.println("El cliente no existe");
		}

	}

	//Metodos para Pedido

	public void addDatosPedido(){
		try {
			String cl;
			String art;
			int cantidad;
			double costeE;
			LocalDateTime fechaHora;
			boolean envio = false;
			String envioS;
			int np;
			String fecha;
			String hora;
			String fechahora;

			System.out.println("Introduce nombre cliente:");
			cl = teclado.nextLine();
			//TODO
			/*
			if (!controlador.clienteExiste(cl)){
				System.out.println("El nombre de cliente es invalido o no existe");
				return;
			}*/
			System.out.println("Introduce el codigo de articulo:");
			art = teclado.nextLine();
			if (!controlador.articuloExiste(art)){
				System.out.println("El codigo de articulo es invalido o ya existe");
				return;
			}
			System.out.println("Introduce la cantidad:");
			cantidad = teclado.nextInt();
			teclado.nextLine();
			System.out.println("introduce el numero de pedido:");
			np = teclado.nextInt();
			teclado.nextLine();
			if (controlador.pedidoExiste(np)){
				System.out.println("El numero de pedido ya existe");
				return;
			}

			try{System.out.println("introduce la fecha (y-m-d):");
				fecha = teclado.nextLine();
				System.out.println("introduce la hora (h:m:s)");
				hora = teclado.nextLine();
				fechahora = (fecha+"T"+hora);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
				fechaHora = LocalDateTime.parse(fechahora, formatter);}

			catch(Exception e){
				System.out.println(" dato introducido no valido, recuerda el formato correcto!!!" );

				return ;
			}
			System.out.println("esta enviado?(S/N):");
			envioS = teclado.nextLine();
			if (envioS.equalsIgnoreCase("s")){
				envio = true;
			}else if(envioS.equalsIgnoreCase("n")){

				envio = false;
			}else{
				System.out.println("Error al insertar envio");
				return;
			}

			System.out.println("Introduce el porcentaje de coste de envio:");
			costeE = teclado.nextDouble();
			teclado.nextLine();
			controlador.addPedido(cl, art, np, fechaHora, cantidad, envio, costeE);
		}catch(InputMismatchException e){
			System.out.println("Dato introducido no valido, vuelve a introducirlo:");

		}
	}
	public void deletePedido(){
		//TODO
		int cp;
		Boolean eliminado;
		System.out.println("Introduce el número de pedido a eliminar");
		cp = teclado.nextInt();
		teclado.nextLine();
		eliminado = controlador.deletePedido(cp);
		if (eliminado){
			System.out.println("El pedido se ha eliminado.");
		}else{
			System.out.println("El pedido no se ha eliminado, fecha de preparación vencida.");
		}
	}
	public void modificarPedido(){
		//TODO

	}
	public void mostrarPedidosEnviados(){
		//TODO
		String filtr;
		String mail;
		System.out.println("LISTAR PEDIDOS EN PREPARACIÓN");
		System.out.println("-----------------------------------------------------");
		System.out.println("Desea filtrar por cliente(S/N)");
		filtr = teclado.nextLine();
		if (filtr.equals("N")) {
			System.out.println(controlador.obtenerpedidos(1, null));
		}else if (filtr.equals("S")){
			System.out.println("Introduce el correo electrónico del cliente:");
			mail = teclado.nextLine();
			System.out.println(controlador.obtenerpedidos(1, mail));
		}else{
			System.out.println("Introduce un valor correcto");
		}

	}
	public void mostrarPedidosEnPrep(){
		//TODO
		String filtr;
		String mail;
		System.out.println("LISTAR PEDIDOS PENDIENTES");
		System.out.println("-----------------------------------------------------");
		System.out.println("Desea filtrar por cliente(S/N)");
		filtr = teclado.nextLine();
		if (filtr.equals("N")) {
			System.out.println(controlador.obtenerpedidos(0, null));
		}else if (filtr.equals("S")){
			System.out.println("Introduce el correo electronico del cliente:");
			mail = teclado.nextLine();
			System.out.println(controlador.obtenerpedidos(0, mail));
		}else{
			System.out.println("Introduce un valor correcto");
		}
	}
	public void mostrarTodosLosPedidos(){
		//TODO
		System.out.println(controlador.MostrarTodosLosPedidos());
	}
	public void buscarPedido(){
		String filtr;
		System.out.println("Introduzca el número de pedido : ");
		filtr = teclado.nextLine();

		System.out.println(controlador.mostrarPedido(Integer.parseInt(filtr)));

	}

}