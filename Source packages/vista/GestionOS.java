package vista;

import ConexionMySQL.DatabaseConnectionException;
import controlador.Controlador;
import modelo.Cliente;
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
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

	public GestionOS() throws DatabaseConnectionException {
		controlador = new Controlador();
	}

	/**
	 * Menú principal
	 */
	public void inicio() {

		boolean salir = false;
		int opcion;


		do {
			try {

				mostrarMenu("Gestión de artículos","Gestión de clientes","Gestión de pedidos");
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
						System.out.println("ERROR: Opción no válida. Introduce un valor valido.");
						break;

				}
			}catch(InputMismatchException e){
				System.out.println("ERROR: Dato introducido no valido, vuelve a introducirlo");
				teclado.nextLine();
			}
		}
		while (!salir);

	}

	/**
	 *  Menú principal para Articulo
	 */
	public void menuArticulo() {
		boolean salir = false;
		int opcion;

		do {
			try {
				mostrarMenu("Introducir artículo", "Mostrar artículos","Mostrar un artículo","Eliminar artículo","Modificar artículo");
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

						System.out.println("ERROR: Opción no válida. Introduce un valor valido.");
						break;
				}
			}catch(InputMismatchException e){
				System.out.println("ERROR: Vuelve a introducir el valor correcto");
				teclado.nextLine();
			}
		}
		while (!salir);

	}

	/**
	 * Menú para Cliente
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
				System.out.println("ERROR: Valor introducido incorrecto. Vuelve a introducir el dato.");
				teclado.nextLine();
			}
		}
		while (!salir);

	}

	/**
	 * Menú para Pedido
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
				System.out.println("ERROR: El valor introducido no es correcto. Introduce un nuevo valor. ");
				teclado.nextLine();
			}
		}
		while (!salir);
	}

	/**
	 * Método para mostrar las opciones a seleccionar en cualquiera de los menús.
	 * @param Opciones Tipo String. Muestra en una cadena las diferentes opciones a seleccionar.
	 */
	public void mostrarMenu(String... Opciones){
		System.out.println("*****************************");
		for (int i = 0;i< Opciones.length;i++){
			System.out.printf("* %d. %s%n", i +1 ,Opciones[i]);
		}
		System.out.println("*****************************");
		System.out.println( "* 0. Para salir ");
		System.out.println("*****************************");
	}

	// ************************************************************************************
	// * Métodos para Artículo
	// ************************************************************************************

	/**
	 * Método para añadir un artículo a la BBDD.
	 */
	public void addDatosArticulo(){
		try {
			String cp;
			String desc;
			double precio;
			Duration tiempoPrep;

			System.out.println("Introduce el Código del Producto (CP): ");
			cp = teclado.nextLine();
			if(controlador.articuloExiste(cp)==true) {
				System.out.println("ERROR: El artículo ya existe.");
				return;
			}
			System.out.println("Introduce la descripción: ");
			desc = teclado.nextLine();
			System.out.println("Introduce el precio del artículo (0,0): ");
			precio = teclado.nextDouble();
			teclado.nextLine();
			System.out.println("Introduce el tiempo de preparación en horas (HH:MM): ");
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
					System.out.println("Articulo insertado con éxito.");
				}catch(NumberFormatException e){
					System.out.println("ERROR: La hora no es valida");
				}

			}
		}catch(InputMismatchException e){
			System.out.println("ERROR: Dato introducido no valido, vuelve a introducirlo.");
			teclado.nextLine();
			addDatosArticulo();
		}
		System.out.println("Datos añadidos con éxito.");
	}

	/**
	 * Método para borrar un artículo de la BBDD.
	 */
	public void deleteArticulo() {
		String cp;
		System.out.println("Introduce el código del articulo a eliminar: ");
		cp = teclado.nextLine();
		controlador.eliminarArticulo(cp);
		System.out.println("Artículo eliminado con éxito.");
	}

	/**
	 * Método para modificar un artículo de la BBDD.
	 */
	public void modificarArticulo(){

		String cp;
		String desc;
		double precio;
		String tiempo;
		Duration tiempoP;

		System.out.println("Introduce el código del producto (CP) a modificar: ");
		cp = teclado.nextLine();
		if (controlador.articuloExiste(cp)) {
			System.out.println("Introduce la nueva descripción: ");
			desc = teclado.nextLine();
			System.out.println("Introduce el nuevo precio o el anterior: ");
			precio = teclado.nextDouble();
			teclado.nextLine();
			System.out.println("Introduce el nuevo tiempo de preparación en horas (00:00): ");
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
					System.out.println("Datos modificados con éxito");
				} catch (NumberFormatException e) {
					System.out.println("ERROR: La hora no es válida.");
				}


			} else {
				System.out.println("El artículo no existe.");
			}
		}

	}

	/**
	 * Método para mostrar un artículo de la BBDD.
	 */
	public void mostrarUnArticulo(){
		String cp;
		System.out.println("Introduce el código de articulo a buscar: ");
		cp = teclado.nextLine();
		if(!controlador.articuloExiste(cp)){
			System.out.println("ERROR: El articulo introducido no esta en la lista. ");
		}else{
			System.out.println(controlador.mostrarArticuloIndividual(cp));;
		}
	}

	// ************************************************************************************
	// * Métodos para Cliente
	// ************************************************************************************

	/**
	 * Método para añadir un cliente a la BBDD.
	 */
	public void addDatosCliente(){

		try {

			String mail;
			String name;
			String VIP;
			String dir;
			double descuento = controlador.obtenerDescuento();

			System.out.println("Introduce el mail: ");
			mail = teclado.nextLine();

			if(controlador.emailExiste(mail)){
				System.out.println("ERROR: El cliente ya existe. ");
				return;
			}
			while(!validaEmilio(mail)){
				System.out.println("ERROR: Mail no valido , intentalo de nuevo.");
				System.out.println("Introduce el correo electrónico: ");
				mail = teclado.nextLine();
			}

			System.out.println("Introduce la dirección: ");
			dir = teclado.nextLine();
			System.out.println("Introduce el nombre: ");
			name = teclado.nextLine();
			System.out.println("Es cliente VIP? (S/N): ");
			VIP = teclado.nextLine();
			if (VIP.equalsIgnoreCase("s")) {
				controlador.addCliente(mail, name, dir, descuento);
			} else {

				controlador.addCliente(mail, name, dir);
			}

		}catch(InputMismatchException e ){
			System.out.println("ERROR: Entrada no valida. Vuelve a introducir los datos. ");
			teclado.nextLine();
		}catch(Exception e){
			System.out.println("Ha ocurrido un error: "+ e.getMessage());
		}
		System.out.println("Datos añadidos con éxito");
	}

	/**
	 * Método para borrar un cliente de la BBDD.
	 */
	public void deleteCliente(){

		Boolean existe;

		System.out.println("Introduce el correo a eliminar: ");
		String mail = teclado.nextLine();
		existe = controlador.eliminarCliente(mail);
		if(!existe){
			System.out.println("ERROR: El cliente no existe");
		}else{
			System.out.println("Cliente eliminado");
		}

	}

	/**
	 * Método para modificar un cliente de la BBDD.
	 */
	public void modificarCliente(){

		String mail;
		String nombre;
		String direccion;
		String vip;
		double	desc;
		System.out.println("Introduce el mail a cambiar: ");
		mail = teclado.nextLine();
		System.out.println("Introduce nuevo nombre: ");
		nombre = teclado.nextLine();
		System.out.println("Introduce la nueva dirección: ");
		direccion = teclado.nextLine();
		System.out.println("Es cliente VIP (S/N): ");
		vip = teclado.nextLine();
		if (vip.equalsIgnoreCase("s")){
			System.out.println("Introduce el descuento: ");
			desc = teclado.nextDouble();
			teclado.nextLine();
			controlador.modificarCliente(mail,nombre,direccion,desc);
		}else if(vip.equalsIgnoreCase("n")){
			controlador.modificarCliente(mail,nombre,direccion);
		}else{
			System.out.println("ERROR: EL valor introducido no es valido.");
		}
		System.out.println("Datos actualizados con éxito.");

	}

	/**
	 * Método para mostrar los clientes según su tipo.
	 * @param value tipo String. Valor a utilizar para el filtro.
	 */
	public void mostrarPorTipoCliente(String value){
		ArrayList<Cliente> array= controlador.mostrarClientesPorTipo("vip",value);

		for (Cliente c: array ){

			System.out.println(c);
		}

	}

	/**
	 * Método para buscar un cliente en la BBDD.
	 */
	public void buscarCliente(){

		String mail;
		System.out.println("Introduce el correo electrónico: ");
		mail = teclado.nextLine();

		if (controlador.emailExiste(mail)){
			System.out.println(controlador.buscarCliente(mail));
		}else{
			System.out.println("ERROR: El cliente no existe. ");
		}

	}

	// ************************************************************************************
	// * Métodos para Pedido
	// ************************************************************************************

	/**
	 * Método para añadir un nuevo pedido.
	 */
	public void addDatosPedido() {
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

			System.out.println("Introduce el mail del cliente: ");
			cl = teclado.nextLine();

			if (controlador.emailExiste(cl)) {

				System.out.println("Introduce el código de artículo: ");
				art = teclado.nextLine();
				if (!controlador.articuloExiste(art)) {
					System.out.println("ERROR: El código de articulo es inválido o ya existe. ");
					return;
				}
				System.out.println("Introduce la cantidad: ");
				cantidad = teclado.nextInt();
				teclado.nextLine();
				System.out.println("Introduce el número de pedido: ");
				np = teclado.nextInt();
				teclado.nextLine();
				if (controlador.pedidoExiste(np)) {
					System.out.println("ERROR: El número de pedido ya existe.");
					return;
				}

				try {
					System.out.println("Introduce la fecha (y-m-d): ");
					fecha = teclado.nextLine();
					System.out.println("Introduce la hora (h:m:s): ");
					hora = teclado.nextLine();
					fechahora = (fecha + "T" + hora);
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
					fechaHora = LocalDateTime.parse(fechahora, formatter);
				} catch (Exception e) {
					System.out.println("ERROR: Dato introducido no valido, recuerda el formato correcto!!!");

					return;
				}
				System.out.println("¿Está enviado? (S/N): ");
				envioS = teclado.nextLine();
				if (envioS.equalsIgnoreCase("s")) {
					envio = true;
				} else if (envioS.equalsIgnoreCase("n")) {

					envio = false;
				} else {
					System.out.println("ERROR: Opción no válida para envío.");
					return;
				}

				System.out.println("Introduce el porcentaje de coste de envío: ");
				costeE = teclado.nextDouble();
				teclado.nextLine();
				controlador.addPedido(cl, art, np, fechaHora, cantidad, envio, costeE);
				System.out.println("Datos insertados con éxito.");
			}
		}catch(InputMismatchException e){
			System.out.println("ERROR: Información introducida no válida.");
		}
	}

	/**
	 * Método para borrar un pedido existente en la BBDD.
	 */
	public void deletePedido(){

		int cp;
		Boolean eliminado =false;
		System.out.println("Introduce el número de pedido a eliminar: ");
		cp = teclado.nextInt();
		teclado.nextLine();
		LocalDateTime date = controlador.obtenerPedido(cp).getFechaHoraPedido();
		Duration timePrep = controlador.obtenerPedido(cp).getArticulo().getTiempoPreparacion();
		if (controlador.comprobarPreparacion(date,timePrep)) {
			eliminado = controlador.deletePedido(cp);

		}
		if (eliminado) {
			System.out.println("El pedido se ha eliminado.");
		} else {
			System.out.println("El pedido no se ha eliminado, fecha de preparación vencida.");
		}
	}

	/**
	 * Método para mostrar los pedidos enviados. Permite filtrar por tipo de cliente.
	 */
	public void mostrarPedidosEnviados(){

		String filtr;
		String mail;
		System.out.println("LISTAR PEDIDOS EN PREPARACIÓN");
		System.out.println("-----------------------------------------------------");
		System.out.println("Desea filtrar por cliente (S/N): ");
		filtr = teclado.nextLine();
		if (filtr.equalsIgnoreCase("n")) {
			System.out.println(controlador.obtenerPedidos(true, null));
		}else if (filtr.equalsIgnoreCase("s")){
			System.out.println("Introduce el correo electrónico del cliente: ");
			mail = teclado.nextLine();
			System.out.println(controlador.obtenerPedidos(true, mail));
		}else{
			System.out.println("ERROR: Introduce un valor correcto. ");
		}

	}

	/**
	 * Método para mostrar los pedidos en preparación. Permite filtrar por tipo de cliente.
	 */
	public void mostrarPedidosEnPrep(){

		String filtr;
		String mail;
		System.out.println("LISTAR PEDIDOS PENDIENTES");
		System.out.println("-----------------------------------------------------");
		System.out.println("Desea filtrar por cliente (S/N): ");
		filtr = teclado.nextLine();
		if (filtr.equalsIgnoreCase("n")) {
			System.out.println(controlador.obtenerPedidos(false, null));
		}else if (filtr.equalsIgnoreCase("s")){
			System.out.println("Introduce el correo electrónico del cliente: ");
			mail = teclado.nextLine();
			System.out.println(controlador.obtenerPedidos(false, mail));
		}else{
			System.out.println("ERROR: Introduce un valor correcto");
		}
	}

	/**
	 * Método para mostrar todos los pedidos.
	 */
	public void mostrarTodosLosPedidos(){

		System.out.println(controlador.MostrarTodosLosPedidos());
	}

	/**
	 * Método para mostrar un pedido por su número.
	 */
	public void buscarPedido(){
		String filtr;
		System.out.println("Introduzca el número de pedido: ");
		filtr = teclado.nextLine();

		System.out.println(controlador.mostrarPedido(Integer.parseInt(filtr)));

	}

	/**
	 * Método para validar el mail del cliente.
	 * @param emilio Tipo String que contiene el mail del cliente.
	 * @return retorna un booleano indicando si es correcto o no.
	 */
	public boolean validaEmilio(String emilio){
		String regex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher((emilio));
		return matcher.matches();
	}

	/**
	 * Método para pedir una opción
	 * @param OpcionMax tipo Int. Contendrá el valor de la opción seleccionada.
	 * @return valor introducido por el usuario.
	 */
	public int pedirOpcion(int OpcionMax){
		int resultado;
		System.out.printf("Selecciona una opción(1 a %d o 0)\n", OpcionMax);
		resultado = teclado.nextInt();
		teclado.nextLine();
		return resultado;
	}



}