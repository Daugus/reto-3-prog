package clases;

import java.util.ArrayList;
import java.util.Objects;

public class Cliente extends Persona
{
	private static final long serialVersionUID = -4712701685349672058L;

	// ===== propiedades =====
	private Fecha fechaAlta;
	private ArrayList<String> vehiculos = new ArrayList<String>();
	
	// ===== constructores =====
	// --- por defecto ---
	public Cliente()
	{
		super();

		fechaAlta = new Fecha();
	}	
	
	// --- copia ---
	public Cliente(Cliente other)
	{
		super(other);

		fechaAlta = new Fecha(other.fechaAlta);
	}	
	
	// --- personalizados ---
	// Persona por defecto con fecha personalizada
	public Cliente(Fecha fa)
	{
		super();

		this.fechaAlta = new Fecha(fa);
	}
	
	// Persona nueva con fecha personalizada
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
				", vehíulos: " + vehiculos;
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