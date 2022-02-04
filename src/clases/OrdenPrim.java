package clases;

import java.io.Serializable;
import java.util.Objects;

public class OrdenPrim implements Comparable<OrdenPrim>, Serializable
{
	private static final long serialVersionUID = 6717239572208402072L;
	
	// ===== propiedades =====
	private String codOrden;
	private String comentarios;
	private Fecha fechaEntrada;
	private Cliente propietario;
	private Vehiculo vehiculo;
	
	// ===== constructores =====
	// por defecto
	public OrdenPrim()
	{
		codOrden = "";
		comentarios = "";
		fechaEntrada = new Fecha();
		
		propietario = new Cliente();
		vehiculo = new Vehiculo();
	}
	
	// copia
	public OrdenPrim(OrdenPrim other)
	{
		this.codOrden = other.codOrden;
		this.comentarios = other.comentarios;
		this.fechaEntrada = new Fecha(other.fechaEntrada);
		
		this.propietario = new Cliente(other.propietario);
		this.vehiculo = new Vehiculo(other.vehiculo);
	}
	
	// personalizado
	public OrdenPrim(String cod, String com, Fecha entrada, Cliente c, Vehiculo v)
	{
		codOrden = cod;
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
		return "codOrdenPrim = " + codOrden +
				", comentarios = " + comentarios +
				", fechaEntrada = " + fechaEntrada +
				", vehiculo = " + vehiculo +
				", propietario = " + propietario;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(codOrden, comentarios, fechaEntrada, propietario, vehiculo);
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
		return Objects.equals(codOrden, other.codOrden) && Objects.equals(comentarios, other.comentarios)
				&& Objects.equals(fechaEntrada, other.fechaEntrada) && Objects.equals(propietario, other.propietario)
				&& Objects.equals(vehiculo, other.vehiculo);
	}

	@Override
	public int compareTo(OrdenPrim other)
	{
		return codOrden.compareTo(other.codOrden);
	}

	// --- getters y setters ---
	public String getCodOrden() {
		return codOrden;
	}

	public void setCodOrden(String codOrdenPrim) {
		this.codOrden = codOrdenPrim;
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