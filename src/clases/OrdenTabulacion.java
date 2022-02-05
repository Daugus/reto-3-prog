package clases;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.Vector;

public class OrdenTabulacion extends FocusTraversalPolicy
{
	// ===== propiedades =====
	Vector<Component> order;

	// ===== constructor personalizado =====
	public OrdenTabulacion(Vector<Component> order)
	{
		this.order = new Vector<Component>(order.size());
		this.order.addAll(order);
	}

	// ===== métodos =====
	public Component getComponentAfter(Container focusCycleRoot, Component aComponent)
	{
		// devolver posición del siguente componente,
		// si el componente actual es el último, devolver el primero
		// (posicionFinal + 1 ) MOD tamaño = 0
		int index = (order.indexOf(aComponent) + 1) % order.size();
		return order.get(index);
	}

	public Component getComponentBefore(Container focusCycleRoot, Component aComponent)
	{
		// devolver posición del componente anterior,
		// si el componente actual es el primero, devolver el último
		int index = order.indexOf(aComponent) - 1;

		if (index < 0)
		{
			index = order.size() - 1;
		}

		return order.get(index);
	}

	public Component getDefaultComponent(Container focusCycleRoot)
	{
		return order.get(0);
	}
	
	public Component getFirstComponent(Container focusCycleRoot)
	{
		return order.get(0);
	}

	public Component getLastComponent(Container focusCycleRoot)
	{
		return order.lastElement();
	}
}