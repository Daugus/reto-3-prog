package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Reparacion implements Comparable<Reparacion>, Serializable
{
	private static final long serialVersionUID = -4533693024823055118L;
	
	// ===== propiedades =====
	private String codReparacion;
	private String descripcion;
	private int horas;
	private double manoObra;
	private Fecha fecha;
	private Cuenta mecanico;
	private ArrayList<MaterialUsado> materialesUsados;
	
	// ===== constructores =====
	// --- por defecto ---
	public Reparacion()
	{
		codReparacion = "";
		descripcion = "";
		horas = 1;
		manoObra = 25;
		fecha = new Fecha();
		mecanico = new Cuenta(true);
		materialesUsados = new ArrayList<MaterialUsado>();
	}
	
	// --- copia ---
	public Reparacion(Reparacion other)
	{
		this.codReparacion = other.codReparacion;
		this.descripcion = other.descripcion;
		this.horas = other.horas;
		this.manoObra = other.manoObra;
		fecha = new Fecha(other.fecha);
		this.mecanico = new Cuenta(other.mecanico);
		this.materialesUsados = new ArrayList<MaterialUsado>(other.materialesUsados);
	}
	
	// --- personalizado ---
	public Reparacion(String cod, String desc, int h, double mo, Fecha fe, Cuenta mec, ArrayList<MaterialUsado> al)
	{
		codReparacion = cod;
		descripcion = desc;
		horas = h;
		manoObra = mo;
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
				", descripción: " + descripcion +
				", horas: " + horas +
				", precio mano de obra: " + manoObra +
				", mecánico: " + mecanico.getDNI() +
				", materiales: " + materialesUsados;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(codReparacion, descripcion, horas, manoObra, fecha, materialesUsados, mecanico);
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
		return Objects.equals(codReparacion, other.codReparacion) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(fecha, other.fecha) && Objects.equals(materialesUsados, other.materialesUsados)
				&& Objects.equals(mecanico, other.mecanico) && Objects.equals(horas, other.horas)
				&& Objects.equals(manoObra, other.manoObra);
	}
	
	@Override
	public int compareTo(Reparacion other)
	{
		return codReparacion.compareTo(other.codReparacion);
	}

	// --- getters y setters ---
	public String getCodReparacion() {
		return codReparacion;
	}

	public void setCodReparacion(String codReparacion) {
		this.codReparacion = codReparacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public double getManoObra() {
		return manoObra;
	}

	public void setManoObra(int manoObra) {
		this.manoObra = manoObra;
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