package clases;

import java.io.Serializable;
import java.util.Objects;

public class OrdenPend implements Comparable<OrdenPend>, Serializable
{
	private static final long serialVersionUID = 6717239572208402072L;
	
	// ===== propiedades =====
	private String codOrdenPend;
	private String comentarios;
	private Fecha fechaEntrada;
	private Cliente propietario;
	private Vehiculo vehiculo;
	private Material material;
	private int horas;
	
	// ===== constructores =====
	// por defecto
	public OrdenPend()
	{
		codOrdenPend = "";
		comentarios = "";
		horas =0;
		fechaEntrada = new Fecha();
		propietario = new Cliente();
		vehiculo = new Vehiculo();
		material = new Material();
	}
	
	// copia
	public OrdenPend(OrdenPend other)
	{
		this.codOrdenPend = other.codOrdenPend;
		this.comentarios = other.comentarios;
		this.fechaEntrada = new Fecha(other.fechaEntrada);
		this.horas = other.horas;
		this.propietario = new Cliente(other.propietario);
		this.vehiculo = new Vehiculo(other.vehiculo);
		this.material = new Material(other.material);
	}
	
	// personalizado
	public OrdenPend(String cod, String com, int h, Fecha entrada, Cliente c, Vehiculo v, Material m)
	{
		codOrdenPend = cod;
		comentarios = com;
		horas= h;
		fechaEntrada = new Fecha(entrada);
		propietario = new Cliente(c);
		vehiculo = new Vehiculo(v);
		material = new Material(m);
	}

	// ===== métodos =====
	// --- salida ---
	@Override
	public String toString()
	{
		return "codOrdenPend = " + codOrdenPend +
				", comentarios = " + comentarios +
				", fechaEntrada = " + fechaEntrada +
				", vehiculo = " + vehiculo +
				", propietario = " + propietario +
				", material = "+ material+
				", horas = " + horas;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(codOrdenPend, comentarios, fechaEntrada, propietario, vehiculo, material, horas);
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
		OrdenPend other = (OrdenPend) obj;
		return Objects.equals(codOrdenPend, other.codOrdenPend)
				&& Objects.equals(comentarios, other.comentarios)
				&& Objects.equals(fechaEntrada, other.fechaEntrada)
				&& Objects.equals(horas, other.horas)
				&& Objects.equals(propietario, other.propietario)
				&& Objects.equals(vehiculo, other.vehiculo)
				&& Objects.equals(material, other.material);
	}

	@Override
	public int compareTo(OrdenPend other)
	{
		int comparacionFecha = fechaEntrada.compareTo(other.fechaEntrada);
		if (comparacionFecha == 0)
		{
			int comparacionPropietario = propietario.compareTo(other.propietario);
			if (comparacionPropietario == 0)
			{
				int comparacionVehiculo = vehiculo.compareTo(other.vehiculo);
				if (comparacionVehiculo != 0)
				{
					return comparacionVehiculo;
				}
			}
			else
			{
				return comparacionPropietario;
			}
		}
		else
		{
			return comparacionFecha;
		}

		return 0;
	}


	// --- getters y setters ---
	public String getCodOrdenPend() {
		return codOrdenPend;
	}

	public void setCodOrdenPrim(String codOrdenPend) {
		this.codOrdenPend = codOrdenPend;
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
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}
}