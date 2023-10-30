package modelo;

import java.util.ArrayList;

/**
 * @author SyntaxError
 * @version 2.0.1
 */
public class Lista<T> {


	protected ArrayList<T> lista;


	/**
	 * Constructo de la clase generica Lista
	 */
	public Lista() {
		lista = new ArrayList<>();
	}

	/**
	 * Getter de la clase generica de tipo Lista<T>
	 * @return
	 */
	public ArrayList<T> getLista() {
		return lista;
	}

	/**
	 * Metodo para agregar un elemento a la lista
	 * @param elemento
	 */

	public void agregar(T elemento) {
		lista.add(elemento);
	}

	/**
	 * Metodo para eliminar un elemento de la lista
	 * @param elemento
	 */

	public void eliminar(T elemento) {
		lista.remove(elemento);
	}

	/**
	 * Metodo para Obtener el tamaño de la lista
	 * @return
	 */

	public int tamanio() {
		return lista.size();
	}

	/**
	 * Metodo para Obtener un elemento por el indice
	 */

	public void mostrarTodo(){

		for (T lista: getLista() ){
			System.out.println(lista);

		}

	}


}
