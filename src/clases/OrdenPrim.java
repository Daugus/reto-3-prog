package clases;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class OrdenPrim implements Comparable<OrdenPrim>, Serializable
{
	private static final long serialVersionUID = 6717239572208402072L;
	
	// ===== propiedades =====
	private String codigo;
	private String comentarios;
	private Fecha fecha;
	private Cliente propietario;
	private Vehiculo vehiculo;
	
	// ===== constructores =====
	// por defecto
	public OrdenPrim()
	{
		codigo = "";
		comentarios = "";
		fecha = new Fecha();
		
		propietario = new Cliente();
		vehiculo = new Vehiculo();
	}
	
	// copia
	public OrdenPrim(OrdenPrim other)
	{
		this.codigo = other.codigo;
		this.comentarios = other.comentarios;
		this.fecha = new Fecha(other.fecha);
		
		this.propietario = new Cliente(other.propietario);
		this.vehiculo = new Vehiculo(other.vehiculo);
	}
	
	// personalizado
	public OrdenPrim(String com, Cliente c, Vehiculo v)
	{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String cod = formatter.format(calendar.getTime());

		codigo = cod;
		comentarios = com;
		fecha = new Fecha();

		propietario = new Cliente(c);
		vehiculo = new Vehiculo(v);
	}

	// ===== métodos =====
	// --- salida ---
	@Override
	public String toString()
	{
		return "codOrdenPrim = " + codigo +
				", comentarios = " + comentarios +
				", fechaEntrada = " + fecha +
				", vehiculo = " + vehiculo +
				", propietario = " + propietario;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(codigo, comentarios, fecha, propietario, vehiculo);
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
		return Objects.equals(codigo, other.codigo) && Objects.equals(comentarios, other.comentarios)
				&& Objects.equals(fecha, other.fecha) && Objects.equals(propietario, other.propietario)
				&& Objects.equals(vehiculo, other.vehiculo);
	}

	@Override
	public int compareTo(OrdenPrim other)
	{
		return codigo.compareTo(other.codigo);
	}

	// --- getters y setters ---
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codOrdenPrim) {
		this.codigo = codOrdenPrim;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fechaEntrada) {
		this.fecha = fechaEntrada;
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