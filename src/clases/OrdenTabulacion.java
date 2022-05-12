package clases;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.Vector;

/**
 * clase para personalizar el orden de tabulación entre los campos de una
 * ventana
 */
public class OrdenTabulacion extends FocusTraversalPolicy {
	// ===== propiedades =====
	private Vector<Component> orden;

	// ===== constructor =====
	public OrdenTabulacion(Vector<Component> order) {
		this.orden = new Vector<Component>(order.size());
		this.orden.addAll(order);
	}

	// ===== métodos =====
	/**
	 * si el componente actual es el último, devolver el primero (posicionFinal + 1
	 * ) MOD tamaño = 0
	 * 
	 * @return Componente posterior
	 */
	public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
		int index = (orden.indexOf(aComponent) + 1) % orden.size();
		return orden.get(index);
	}

	/**
	 * @return Componente anterior
	 */
	public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
		int index = orden.indexOf(aComponent) - 1;

		if (index < 0)
			index = orden.size() - 1;

		return orden.get(index);
	}

	/**
	 * @return Componente por defecto
	 */
	public Component getDefaultComponent(Container focusCycleRoot) {
		return orden.get(0);
	}

	/**
	 * @return primer Componente
	 */
	public Component getFirstComponent(Container focusCycleRoot) {
		return orden.get(0);
	}

	/**
	 * @return último Componente
	 */
	public Component getLastComponent(Container focusCycleRoot) {
		return orden.lastElement();
	}
}