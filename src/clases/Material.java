package clases;

import java.io.Serializable;
import java.util.Objects;

public class Material implements Comparable<Material>, Serializable {
	private static final long serialVersionUID = -4533693024823055118L;

	// ===== propiedades =====
	private String nombre;
	private double precio;

	// ===== constructores =====
	/**
	 * constructor por defecto
	 */
	public Material() {
		nombre = "";
		precio = 1.0;
	}

	/**
	 * constructor copia
	 */
	public Material(Material m) {
		this.nombre = m.nombre;
		this.precio = m.precio;
	}

	// --- personalizados ---
	/**
	 * material personalizado
	 * 
	 * @param n nombre
	 * @param p precio
	 */
	public Material(String n, double p) {
		nombre = n;
		precio = p;
	}

	// ===== métodos =====
	// --- salida ---
	/**
	 * Devuelve una representación de cadena del objeto.
	 * 
	 * @return los atributos del objeto
	 */
	public String toString() {
		return "Nombre: " + nombre + ", precio: " + precio;
	}

	// --- comparación ---
	/**
	 * devuelve el hash code del objeto basado en sus atributos
	 * 
	 * @return devuelve un valor hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(nombre, precio);
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
		return Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio);
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
		return nombre.compareTo(other.nombre);
	}

	// --- getters y setters ---
	/**
	 * acceso a nombre
	 * 
	 * @return nombre tipo String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * modifica el valor de nombre pasando string como parametro
	 * 
	 * @param n tipo String
	 */
	public void setNombre(String n) {
		nombre = n;
	}

	/**
	 * acceso a precio
	 * 
	 * @return precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * modifica el valor de precio pasando double precio como parametro
	 * 
	 * @param p tipo double
	 */
	public void setPrecio(double p) {
		precio = p;
	}
}