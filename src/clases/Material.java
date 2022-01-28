package clases;

import java.io.Serializable;
import java.util.Objects;

public class Material implements Comparable<Material>, Serializable
{
	private static final long serialVersionUID = -4533693024823055118L;
	
	// ===== propiedades =====
	private String nombre;
	private double precio;
	
	// ===== constructores =====
	// --- por defecto ---
	public Material()
	{
		nombre = "";
		precio = 1.0;
	}
	
	// --- copia ---
	public Material(Material m)
	{
		this.nombre = m.nombre;
		this.precio = m.precio;
	}
	
	// --- personalizado ---
	public Material(String n, double p)
	{
		nombre = n;
		precio = p;
	}

	// ===== métodos =====
	// --- salida ---
	public String toString()
	{
		return (nombre + " " + precio);
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