package clases;

import java.io.Serializable;
import java.util.Objects;

public class Reparacion implements Comparable<Reparacion>, Serializable {
	private static final long serialVersionUID = -4533693024823055118L;

	// ===== propiedades =====
	private String codigo;
	private String descripcion;
	private boolean activo;

	// ===== constructores =====
	/**
	 * reparacion por defecto
	 */
	public Reparacion() {
		codigo = "";
		descripcion = "";
		activo = true;
	}

	/**
	 * constructor copia
	 */
	public Reparacion(Reparacion other) {
		this.codigo = other.codigo;
		this.descripcion = other.descripcion;
	}

	public Reparacion(String codigo, String descripcion, boolean activo) {
		this.codigo = codigo;
		this.descripcion = descripcion;
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
		return "Código: " + codigo + ", descripción: " + descripcion + ", activo: " + activo;
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

	public void setCodigo(String codReparacion) {
		this.codigo = codReparacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public boolean getActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}