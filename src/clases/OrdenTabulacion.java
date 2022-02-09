package clases;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.Vector;

/**
 * esta clase hace referencia a la tabulacion de componentes de las tabls
 * @author Grupo 2
 * @version 2.0.1
 *
 */
	
public class OrdenTabulacion extends FocusTraversalPolicy
{
	// ===== propiedades =====
	private Vector<Component> orden;

	// ===== constructor personalizado =====
	public OrdenTabulacion(Vector<Component> order)
	{
		this.orden = new Vector<Component>(order.size());
		this.orden.addAll(order);
	}
	
	// ===== métodos =====
	public Component getComponentAfter(Container focusCycleRoot, Component aComponent)
	{
		// devolver posición del siguente componente,
		// si el componente actual es el último, devolver el primero
		// (posicionFinal + 1 ) MOD tamaño = 0
		
		int index = (orden.indexOf(aComponent) + 1) % orden.size();
		return orden.get(index);
	}

	public Component getComponentBefore(Container focusCycleRoot, Component aComponent)
	{
		// devolver posición del componente anterior,
		// si el componente actual es el primero, devolver el último
		int index = orden.indexOf(aComponent) - 1;

		if (index < 0)
		{
			index = orden.size() - 1;
		}

		return orden.get(index);
	}

	public Component getDefaultComponent(Container focusCycleRoot)
	{
		return orden.get(0);
	}
	
	public Component getFirstComponent(Container focusCycleRoot)
	{
		return orden.get(0);
	}

	public Component getLastComponent(Container focusCycleRoot)
	{
		return orden.lastElement();
	}
}