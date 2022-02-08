package clases;

import java.util.ArrayList;
import java.util.Objects;
/**
 * esta clase difiene fechaAlta al vehiculo que se usaran en el programa
 * @author Grupo 2
 * @version 2.0.1
 */
public class Cliente extends Persona
{
	private static final long serialVersionUID = -4712701685349672058L;

	// ===== propiedades =====
	private Fecha fechaAlta;
	private ArrayList<String> vehiculos = new ArrayList<String>();
	
	// ===== constructores =====
	/**
	 * constructor por defecto 
	 */
	public Cliente()
	{
		super();

		fechaAlta = new Fecha();
	}	
	
	/**
	 * constructor copia
	 * @param other copia del constructor por defecto
	 */
	public Cliente(Cliente other)
	{
		super(other);

		fechaAlta = new Fecha(other.fechaAlta);
	}	
	
	// --- personalizados ---
	/**
	 * constructor cliente por defecto
	 * @param fa fecha personalizada
	 */
	public Cliente(Fecha fa)
	{
		super();

		this.fechaAlta = new Fecha(fa);
	}
	
	/**
	 * constructor personalizado
	 * @param d
	 * @param n
	 * @param a
	 * @param t
	 * @param e
	 * @param fn 
	 * @param dir direccion
	 * @param fa
	 * @param v
	 */
	public Cliente(String d, String n, String a, int t, String e, Fecha fn, Direccion dir, Fecha fa, ArrayList<String> v)
	{
		super(d, n, a, t, e, fn, dir);

		fechaAlta = new Fecha(fa);
		vehiculos = v;
	}

	// ===== métodos =====
	// --- salida ---
	@Override
	public String toString()
	{
		return super.toString() +
				", fecha de alta: " + fechaAlta +
				", vehículos: " + vehiculos;
	}

	// --- comparacion ---
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(fechaAlta);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(fechaAlta, other.fechaAlta);
	}
	
	// --- getters y setters ---
	public Fecha getFechaAlta()
	{
		return fechaAlta;
	}

	public void setFechaAlta(Fecha fechaAlta)
	{
		this.fechaAlta = fechaAlta;
	}
	
	public ArrayList<String> getVehiculos()
	{
		return vehiculos;
	}
}