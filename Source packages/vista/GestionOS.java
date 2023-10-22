package vista;

import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import controlador.Controlador;
import java.time.LocalDate;
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
			System.out.println("1. Gestion articulos");
			System.out.println("2. Gestion clientes");
			System.out.println("3. Gestion pedidos");
			System.out.println("S para salir");
			opcion = teclado.nextInt();
			teclado.nextLine();
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
		}
		while (!salir);

	}

	public void menuArticulo() {
		boolean salir = false;
		int opcion;

		do {
			System.out.println("1. Add articulos");
			System.out.println("2. Show articulos");
			System.out.println("0 para salir");

			opcion = teclado.nextInt();
			teclado.nextLine();
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
		}
		while (!salir);

	}

	;

	public void menuClientes() {
		boolean salir = false;
		int opcion;

		do {
			System.out.println("1. Add cliente");
			System.out.println("2. Show clientes");
			System.out.println("3. Show cliente premium");
			System.out.println("4. Show cliente estandar");
			System.out.println("0 para salir");

			opcion = teclado.nextInt();
			teclado.nextLine();


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
		}
		while (!salir);

	}

	public void menuPedidos() {
		boolean salir = false;
		int opcion;

		do {
			System.out.println("1. Add pedido");
			System.out.println("2. Eliminar pedido");
			System.out.println("3. Mostrar pedidos pendientes de envio");
			System.out.println("4. Mostrar pedidos enviados ");
			System.out.println("0 para salir");

			opcion = teclado.nextInt();
			teclado.nextLine();


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
		}
		while (!salir);


	}

	public void addDatosArticulo(){

		String cp;
		String desc;
		double precio;

		System.out.println( "introduce el cp");
		cp = teclado.nextLine();
		System.out.println(" Introduce la descripcion");
		desc = teclado.nextLine();
		System.out.println("introduce el precio del articulo");
		precio = teclado.nextDouble();
		teclado.nextLine();
		controlador.addArticulo(cp,desc,precio);
	};

	public void addDatosCliente(){
		String mail;
		String name;
		String VIP;
		String dir;
		double descuento = 0.45;
		System.out.println( "Introduce el mail");
		mail = teclado.nextLine();
		System.out.println(" Introduce la direccion");
		dir = teclado.nextLine();
		System.out.println("introduce el nombre");
		name = teclado.nextLine();
		System.out.println("Es cliente VIP?(S/N)");
		VIP = teclado.nextLine();
		if (VIP.equals("S") ){
			controlador.addCliente(mail,name,dir,descuento);
		}else{

			controlador.addCliente(mail,name,dir);
		}

	}

	public void addPedido(){
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
		fechaHora = LocalDate.parse(teclado.nextLine(),formatter);
		System.out.println("esta enviado?");
		envio = teclado.nextBoolean();
		teclado.nextLine();
		System.out.println("introduce precio envio");
		costeE = teclado.nextDouble();
		teclado.nextLine();
		controlador.addPedido(cl,art,np,fechaHora,cantidad,envio,costeE);
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
		controlador.addCliente("jose@gmail","paco","camino3");

		controlador.addArticulo("cp","ordenador",120);
		controlador.addArticulo("mc","movil",120);
		controlador.addArticulo("ja","tablet",120);

		controlador.addPedido("pepe","cp",1,LocalDate.parse("1920-12-12"),5,true,12.0);
		controlador.addPedido("paco","mc",2,LocalDate.parse("1920-12-12"),2,false,12.0);
		controlador.addPedido("jose","ja",3,LocalDate.parse("1920-12-12"),6,false,12.0);
	}
}



