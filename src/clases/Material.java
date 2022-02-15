package clases;

import java.io.Serializable;
import java.util.Objects;

/**
 * esta clase difiene material que se usaran en el programa
 * 
 * @author Grupo 2
 * @version 2.0.1
 */
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
	 * material
	 * 
	 * @param m copia de constructor por defecto
	 */
	public Material(Material m) {
		this.nombre = m.nombre;
		this.precio = m.precio;
	}

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
	/**
	 * Devuelve una representación de cadena del objeto.
	 * 
	 * @return nombre y precio
	 */
	public String toString() {
		return "Nombre: " + nombre + ", precio: " + precio;
	}

	// --- comparación ---
	/**
	 * para el objeto Este método es compatible en beneficio de las tablas hash
	 * como las proporcionadas por java.util.HashMap.
	 * 
	 * @return devuelve un valor hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(nombre, precio);
	}

	/**
	 * 
	 * Indica si algún otro objeto es "igual a" este.
	 * 
	 * @return true si este objeto es el mismo que el objargument; falso en caso
	 *         contrario.
	 * @param obj objeto referente con el que desea comparar
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