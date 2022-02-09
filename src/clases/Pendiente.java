package clases;

import java.util.ArrayList;
import java.util.Objects;

/**
 * esta clase difiene ordenes pendientes que se usaran en el programa
 * @author Grupo 2
 * @version 2.0.1
 */
	
public class Pendiente extends Primaria
{
	private static final long serialVersionUID = 6717239572208402072L;
	
	// ===== propiedades =====
	private ArrayList<Reparacion> reparaciones;
	
	// ===== constructores =====
	/**
	 * orden pendiente por defecto
	 */
	public Pendiente()
	{
		super();
		
		setEmpleado(new Cuenta(true));
		reparaciones = new ArrayList<Reparacion>();
	}
	
	/**
	 * orden pendiente por defecto
	 * @param other copia
	 */
	public Pendiente(Pendiente other)
	{
		super(other);
		
		reparaciones = new ArrayList<Reparacion>(other.reparaciones);
	}
	
	/**
	 * orden pendiente personalizado
	 * @param com String comentario de la orden
	 * @param c objeto cliente 
	 * @param v objeto vehiculo
	 * @param mec objeto mecanico
	 * @param r arraylist para agrupar datos
	 */
	public Pendiente(String com, Cliente c, Vehiculo v, Cuenta mec, ArrayList<Reparacion> r)
	{
		super(com, c, v, mec);
		
		reparaciones = new ArrayList<Reparacion>(r);
	}
	/**
	 * orden pendiente personalizado
	 * @param primaria objeto orden primaria
	 * @param mec objeto mecanico
	 * @param r arraylist para agrupar datos
	 */
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