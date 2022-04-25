package clases;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.Vector;

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
	 * @return devolver posición del siguente componente
	 */
	public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
		int index = (orden.indexOf(aComponent) + 1) % orden.size();
		return orden.get(index);
	}

	public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
		int index = orden.indexOf(aComponent) - 1;

		if (index < 0) {
			index = orden.size() - 1;
		}
		return orden.get(index);
	}

	public Component getDefaultComponent(Container focusCycleRoot) {
		return orden.get(0);
	}

	public Component getFirstComponent(Container focusCycleRoot) {
		return orden.get(0);
	}

	public Component getLastComponent(Container focusCycleRoot) {
		return orden.lastElement();
	}
}