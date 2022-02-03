package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Reparacion implements Comparable<Reparacion>, Serializable
{
	private static final long serialVersionUID = -4533693024823055118L;
	
	// ===== propiedades =====
	private String codReparacion;
	private Fecha fecha;
	private Cuenta mecanico;
	private ArrayList<MaterialUsado> materialesUsados;
	
	// ===== constructores =====
	// --- por defecto ---
	public Reparacion()
	{
		codReparacion = "";
		fecha = new Fecha();
		mecanico = new Cuenta(true);
		materialesUsados = new ArrayList<MaterialUsado>();
	}
	
	// --- copia ---
	public Reparacion(Reparacion other)
	{
		this.codReparacion = other.codReparacion;
		fecha = new Fecha(other.fecha);
		this.mecanico = new Cuenta(other.mecanico);
		this.materialesUsados = new ArrayList<MaterialUsado>(other.materialesUsados);
	}
	
	// --- personalizado ---
	public Reparacion(String cod, Fecha fe, Cuenta mec, ArrayList<MaterialUsado> al)
	{
		codReparacion = cod;
		fecha = new Fecha(fe);
		mecanico = new Cuenta(mec);
		materialesUsados = new ArrayList<MaterialUsado>(al);
	}

	// ===== métodos =====
	// --- salida ---
	@Override
	public String toString()
	{
		return "Código: " + codReparacion +
				", mecánico: " + mecanico.getDNI() +
				", materiales: " + materialesUsados;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(codReparacion, fecha, materialesUsados, mecanico);
	}

	// --- comparación ---
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reparacion other = (Reparacion) obj;
		return Objects.equals(codReparacion, other.codReparacion) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(materialesUsados, other.materialesUsados) && Objects.equals(mecanico, other.mecanico);
	}
	
	// --- getters y setters ---
	@Override
	public int compareTo(Reparacion other)
	{
		return codReparacion.compareTo(other.codReparacion);
	}

	public String getCodReparacion() {
		return codReparacion;
	}

	public void setCodReparacion(String codReparacion) {
		this.codReparacion = codReparacion;
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public Cuenta getMecanico() {
		return mecanico;
	}

	public void setMecanico(Cuenta mecanico) {
		this.mecanico = mecanico;
	}

	public ArrayList<MaterialUsado> getMaterialesUsados() {
		return materialesUsados;
	}

	public void setMaterialesUsados(ArrayList<MaterialUsado> materialesUsados) {
		this.materialesUsados = materialesUsados;
	}
}