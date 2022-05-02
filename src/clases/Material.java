package clases;

import java.io.Serializable;
import java.util.Objects;

public class Material implements Comparable<Material>, Serializable {
	private static final long serialVersionUID = -4533693024823055118L;

	// ===== propiedades =====
	private String id;
	private String marca;
	private String nombre;
	private double pvp;
	private double precioCompra;
	private boolean activo;

	// ===== constructores =====
	/**
	 * constructor por defecto
	 */
	public Material() {
		id = "";
		marca = "";
		nombre = "";
		pvp = 1.0;
		precioCompra = 1.0;
		activo = true;
	}

	/**
	 * constructor copia
	 */
	public Material(Material m) {
		this.id = m.id;
		this.marca = m.marca;
		this.nombre = m.nombre;
		this.pvp = m.pvp;
		this.precioCompra = m.precioCompra;
		this.activo = m.activo;
	}

	// --- personalizados ---
	/**
	 * material personalizado
	 * 
	 * @param nombre nombre
	 * @param pvp    precio
	 */
	public Material(String id, String marca, String nombre, double pvp, double precioCompra, boolean activo) {
		this.id = id;
		this.marca = marca;
		this.nombre = nombre;
		this.pvp = pvp;
		this.precioCompra = precioCompra;
		this.activo = activo;
	}

	// ===== métodos =====
	// --- salida ---
	/**
	 * Devuelve una representación de cadena del objeto.
	 * 
	 * @return los atributos del objeto
	 */
	public String toString() {
		return "ID:" + id + ", marca: " + marca + ", nombre: " + nombre + ", precio: " + pvp + ", precio compra: "
				+ precioCompra + ", activo: " + activo;
	}

	// --- comparación ---
	/**
	 * devuelve el hash code del objeto basado en sus atributos
	 * 
	 * @return devuelve un valor hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		Material other = (Material) obj;
		return Objects.equals(id, other.id);
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
	public int compareTo(Material other) {
		return id.compareTo(other.id);
	}

	// --- getters y setters ---
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPvp() {
		return pvp;
	}

	public void setPvp(double pvp) {
		this.pvp = pvp;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}