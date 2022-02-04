package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class OrdenPend extends OrdenPrim implements Serializable
{
	private static final long serialVersionUID = 6717239572208402072L;
	
	// ===== propiedades =====
	private Fecha fecha;
	private ArrayList<Reparacion> reparaciones;
	
	// ===== constructores =====
	// por defecto
	public OrdenPend()
	{
		super();
		
		setCodOrden("");
		fecha = new Fecha();
		reparaciones = new ArrayList<Reparacion>();
	}
	
	// copia
	public OrdenPend(OrdenPend other)
	{
		super(other);
		
		setCodOrden(other.getCodOrden());
		fecha = new Fecha(other.fecha);
		reparaciones = new ArrayList<Reparacion>(other.reparaciones);
	}
	
	// personalizado
	public OrdenPend(String cod, String com, Fecha f, Cliente c, Vehiculo v, ArrayList<Reparacion> r)
	{
		super(cod, com, f, c, v);
		
		fecha = new Fecha(f);
		reparaciones = new ArrayList<Reparacion>(r);
	}
	
	public OrdenPend(OrdenPrim primaria, Fecha f, ArrayList<Reparacion> r)
	{
		super(primaria);
		
		fecha = new Fecha(f);
		reparaciones = new ArrayList<Reparacion>(r);
	}

	// ===== métodos =====
	// --- salida ---
	@Override
	public String toString() {
		return super.toString() +
				", reparaciones: " + reparaciones;
	}

	// --- comparación ---
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(fecha, reparaciones);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdenPend other = (OrdenPend) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(reparaciones, other.reparaciones);
	}

	// --- getters y setters ---
	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public ArrayList<Reparacion> getReparaciones() {
		return reparaciones;
	}

	public void setReparaciones(ArrayList<Reparacion> reparaciones) {
		this.reparaciones = reparaciones;
	}
}