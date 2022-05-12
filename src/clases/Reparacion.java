package clases;

import java.io.Serializable;
import java.util.Objects;

import funciones.General;

/**
 * clase de Reparacion
 */
public class Reparacion implements Comparable<Reparacion>, Serializable {
	private static final long serialVersionUID = -4533693024823055118L;

	// ===== propiedades =====
	private String codigo;
	private String descripcion;

	private double precio;
	private int horas;

	private String idMaterial;
	private int cantidadMaterial;

	private boolean activo;

	// ===== constructores =====
	/**
	 * constructor por defecto
	 */
	public Reparacion() {
		codigo = "";
		descripcion = "";

		precio = 1.0;
		horas = 1;

		idMaterial = "";
		cantidadMaterial = 1;

		activo = true;
	}

	/**
	 * constructor personalizado para listar reparaciones
	 * 
	 * @param codigo           código de la reparación
	 * @param descripcion      descripción de la reparación
	 * @param precio           precio de la reparación
	 * @param idMaterial       ID del material usado en la reparación
	 * @param cantidadMaterial cantidad de material que se ha usado en la reparación
	 * @param activo           estado de la reparación
	 */
	public Reparacion(String codigo, String descripcion, double precio, String idMaterial, int cantidadMaterial,
			boolean activo) {
		this.codigo = codigo;
		this.descripcion = descripcion;

		this.precio = precio;

		this.idMaterial = idMaterial;
		this.cantidadMaterial = cantidadMaterial;

		this.activo = activo;
	}

	/**
	 * constructor personalizado completo
	 * 
	 * @param codigo           código de la reparación
	 * @param descripcion      descripción de la reparación
	 * @param precio           precio de la reparación
	 * @param horas            cantidad de horas que ha llevado la reparación
	 * @param idMaterial       ID del material usado en la reparación
	 * @param cantidadMaterial cantidad de material que se ha usado en la reparación
	 * @param activo           estado de la reparación
	 */
	public Reparacion(String codigo, String descripcion, double precio, int horas, String idMaterial,
			int cantidadMaterial, boolean activo) {
		this.codigo = codigo;
		this.descripcion = descripcion;

		this.precio = precio;
		this.horas = horas;

		this.idMaterial = idMaterial;
		this.cantidadMaterial = cantidadMaterial;

		this.activo = activo;
	}

	// ===== métodos =====
	// --- salida ---
	@Override
	public String toString() {
		return "Código: " + codigo + ", descripción: " + descripcion + ", precio: " + General.formatearPrecio(precio)
				+ ", activo: " + activo;
	}

	// --- comparación ---
	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reparacion other = (Reparacion) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public int compareTo(Reparacion other) {
		return codigo.compareTo(other.codigo);
	}

	// --- getters ---
	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public boolean getActivo() {
		return activo;
	}

	public double getPrecio() {
		return precio;
	}

	public String getIdMaterial() {
		return idMaterial;
	}

	public int getCantidadMaterial() {
		return cantidadMaterial;
	}

	public int getHoras() {
		return horas;
	}
}