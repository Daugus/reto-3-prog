package clases;

import java.io.Serializable;
import java.util.Objects;

import funciones.General;

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
	 * reparacion por defecto
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


	public Reparacion(String codigo, String descripcion, double precio, String idMaterial, int cantidadMaterial,
			boolean activo) {
		this.codigo = codigo;
		this.descripcion = descripcion;

		this.precio = precio;

		this.idMaterial = idMaterial;
		this.cantidadMaterial = cantidadMaterial;

		this.activo = activo;
	}

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
	/**
	 * Devuelve una representación de cadena del objeto.
	 * 
	 * @return los atributos del objeto
	 */
	@Override
	public String toString() {
		return "Código: " + codigo + ", descripción: " + descripcion + ", precio: " + General.formatearPrecio(precio)
				+ ", activo: " + activo;
	}

	// --- comparación ---
	/**
	 * devuelve el hash code del objeto basado en sus atributos
	 * 
	 * @return devuelve un valor hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	/**
	 * indica si algún otro objeto es igual a este
	 * 
	 * @param obj el objeto con el que se va a comparar
	 * @return {@code true} si el objeto es igual que el parámetro obj
	 */
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

	/**
	 * Compara este objeto con el objeto especificado para el orden. Devuelve un
	 * entero negativo, cero o un entero positivo, ya que este objeto es menor,
	 * igual o mayor que el objeto especificado.
	 * 
	 * @return entero negativo, cero o un entero positivo, ya que este objeto es
	 *         menor, igual o mayor que el objeto especificado.
	 */
	@Override
	public int compareTo(Reparacion other) {
		return codigo.compareTo(other.codigo);
	}

	// --- getters y setters ---
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