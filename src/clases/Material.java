package clases;

import java.io.Serializable;
import java.util.Objects;

/**
 * esta clase difiene material que se usaran en el programa
 * @author Grupo 2
 * @version 2.0.1
 */
public class Material implements Comparable<Material>, Serializable
{
	private static final long serialVersionUID = -4533693024823055118L;
	
	// ===== propiedades =====
	private String nombre;
	private double precio;
	
	// ===== constructores =====
	/**
	 * constructor por defecto
	 */
	public Material()
	{
		nombre = "";
		precio = 1.0;
	}
	
	/**
	 * material 
	 * @param m copia de constructor por defecto
	 */
	public Material(Material m)
	{
		this.nombre = m.nombre;
		this.precio = m.precio;
	}
	
	/**
	 * material personalizado
	 * @param n nombre
	 * @param p precio
	 */
	public Material(String n, double p)
	{
		nombre = n;
		precio = p;
	}

	// ===== métodos =====
	// --- salida ---
	public String toString()
	{
		return "Nombre: " + nombre +
				", precio: " + precio;
	}

	// --- comparación ---
	@Override
	public int hashCode()
	{
		return Objects.hash(nombre, precio);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Material other = (Material) obj;
		return Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio);
	}
	
	@Override
	public int compareTo(Material other)
	{
		return nombre.compareTo(other.nombre);
	}
	
	// --- getters y setters ---
	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String n)
	{
		nombre = n;
	}

	public double getPrecio()
	{
		return precio;
	}

	public void setPrecio(double p)
	{
		precio = p;
	}
}