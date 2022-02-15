package clases;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.Vector;

/**
 * esta clase hace referencia a la tabulacion de componentes de las tabls
 * 
 * @author Grupo 2
 * @version 2.0.1
 *
 */

public class OrdenTabulacion extends FocusTraversalPolicy {
	// ===== propiedades =====
	private Vector<Component> orden;

	/**
	 * constructor personalizado
	 * 
	 * @param order lista de orden a alterar
	 */
	public OrdenTabulacion(Vector<Component> order) {
		this.orden = new Vector<Component>(order.size());
		this.orden.addAll(order);
	}

	// ===== métodos =====
	/**
	 * si el componente actual es el último, devolver el primero (posicionFinal + 1
	 * ) MOD tamaño = 0
	 * 
	 * @return devolver posición del siguente componente
	 */
	public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
		int index = (orden.indexOf(aComponent) + 1) % orden.size();
		return orden.get(index);
	}

	/**
	 * devolver posición del componente anterior, si el componente actual es el
	 * primero, devolver el último
	 * 
	 * @return orden index de la posicion
	 */
	public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
		int index = orden.indexOf(aComponent) - 1;

		if (index < 0) {
			index = orden.size() - 1;
		}
		return orden.get(index);
	}

	/**
	 * Este Componente será el primero en recibir el foco cuando se desplace hacia
	 * un nuevo ciclo transversal de foco enraizado en un Contenedor.
	 * 
	 * @return devuelve el componente predeterminado al foco.
	 */
	public Component getDefaultComponent(Container focusCycleRoot) {
		return orden.get(0);
	}

	/**
	 * Este metodo is usado para determinar el seguiente componente
	 * 
	 * @return devuelve el primer componente en el intervalo de ciclo
	 */
	public Component getFirstComponent(Container focusCycleRoot) {
		return orden.get(0);
	}

	/**
	 * This method is usedto determine the next Component to focus when traversal
	 * wraps in theforward direction. este metodo es usado para determinar el
	 * siguiente componente de la posicion ultima
	 * 
	 * @return devuelve el ultimo componente en el intervalo de ciclo
	 */
	public Component getLastComponent(Container focusCycleRoot) {
		return orden.lastElement();
	}
}