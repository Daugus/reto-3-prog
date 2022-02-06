package clases;

import java.util.ArrayList;
import java.util.Objects;

public class Pendiente extends Primaria
{
	private static final long serialVersionUID = 6717239572208402072L;
	
	// ===== propiedades =====
	private ArrayList<Reparacion> reparaciones;
	
	// ===== constructores =====
	// --- por defecto ---
	public Pendiente()
	{
		super();
		
		setEmpleado(new Cuenta(true));
		reparaciones = new ArrayList<Reparacion>();
	}
	
	// --- copia ---
	public Pendiente(Pendiente other)
	{
		super(other);
		
		reparaciones = new ArrayList<Reparacion>(other.reparaciones);
	}
	
	// --- personalizados ---
	public Pendiente(String com, Cliente c, Vehiculo v, Cuenta mec, ArrayList<Reparacion> r)
	{
		super(com, c, v, mec);
		
		reparaciones = new ArrayList<Reparacion>(r);
	}
	
	public Pendiente(Primaria primaria, Cuenta mec, ArrayList<Reparacion> r)
	{
		super(primaria);
		
		generarCodigo();
		setFecha(new Fecha());
		setEmpleado(new Cuenta(mec));
		reparaciones = new ArrayList<Reparacion>(r);
	}

	// ===== métodos =====
	// --- salida ---
	@Override
	public String toString()
	{
		return super.toString() +
				", reparaciones: " + reparaciones;
	}

	// --- comparación ---
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(reparaciones);
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
		Pendiente other = (Pendiente) obj;
		return Objects.equals(reparaciones, other.reparaciones);
	}

	// --- getters y setters ---
	public ArrayList<Reparacion> getReparaciones()
	{
		return reparaciones;
	}

	public void setReparaciones(ArrayList<Reparacion> reparaciones)
	{
		this.reparaciones = reparaciones;
	}
}