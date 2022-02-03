package clases;

import java.io.Serializable;
import java.util.Objects;

public class OrdenPrim implements Comparable<OrdenPrim>, Serializable
{
	private static final long serialVersionUID = 6717239572208402072L;
	
	// ===== propiedades =====
	private String codOrdenPrim;
	private String comentarios;
	private Fecha fechaEntrada;
	private Cliente propietario;
	private Vehiculo vehiculo;
	
	// ===== constructores =====
	// por defecto
	public OrdenPrim()
	{
		codOrdenPrim = "";
		comentarios = "";
		fechaEntrada = new Fecha();
		
		propietario = new Cliente();
		vehiculo = new Vehiculo();
	}
	
	// copia
	public OrdenPrim(OrdenPrim other)
	{
		this.codOrdenPrim = other.codOrdenPrim;
		this.comentarios = other.comentarios;
		this.fechaEntrada = new Fecha(other.fechaEntrada);
		
		this.propietario = new Cliente(other.propietario);
		this.vehiculo = new Vehiculo(other.vehiculo);
	}
	
	// personalizado
	public OrdenPrim(String cod, String com, Fecha entrada, Cliente c, Vehiculo v)
	{
		codOrdenPrim = cod;
		comentarios = com;
		fechaEntrada = new Fecha(entrada);

		propietario = new Cliente(c);
		vehiculo = new Vehiculo(v);
	}

	// ===== métodos =====
	// --- salida ---
	@Override
	public String toString()
	{
		return "codOrdenPrim = " + codOrdenPrim +
				", comentarios = " + comentarios +
				", fechaEntrada = " + fechaEntrada +
				", vehiculo = " + vehiculo +
				", propietario = " + propietario;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(codOrdenPrim, comentarios, fechaEntrada, propietario, vehiculo);
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
		OrdenPrim other = (OrdenPrim) obj;
		return Objects.equals(codOrdenPrim, other.codOrdenPrim) && Objects.equals(comentarios, other.comentarios)
				&& Objects.equals(fechaEntrada, other.fechaEntrada) && Objects.equals(propietario, other.propietario)
				&& Objects.equals(vehiculo, other.vehiculo);
	}

	@Override
	public int compareTo(OrdenPrim other)
	{
		return codOrdenPrim.compareTo(other.codOrdenPrim);
	}

	// --- getters y setters ---
	public String getCodOrdenPrim() {
		return codOrdenPrim;
	}

	public void setCodOrdenPrim(String codOrdenPrim) {
		this.codOrdenPrim = codOrdenPrim;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Fecha getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Fecha fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Cliente getPropietario() {
		return propietario;
	}

	public void setPropietario(Cliente propietario) {
		this.propietario = propietario;
	}
}