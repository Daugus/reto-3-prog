package clases;

import java.util.Objects;

public class MaterialUsado extends Material {
	private static final long serialVersionUID = -4881100923692845852L;

	// ===== propiedades =====
	private int cantidad;

	// ===== constructores =====
	/**
	 * materialUsado por defecto
	 */
	public MaterialUsado() {
		super();

		cantidad = 1;
	}

	/**
	 * constructor copia
	 */
	public MaterialUsado(MaterialUsado other) {
		super(other);

		this.cantidad = other.cantidad;
	}

	// --- personalizados ---
	/**
	 * material usado personalizado
	 * 
	 * @param m Material material que va a ser usado
	 * @param c int cantidad
	 */
	public MaterialUsado(Material m, int c) {
		super(m);

		cantidad = c;
	}

	/**
	 * material personalizado hereda propiedades del super mas la propiedad cantidad
	 * 
	 * @param n String nombre de material
	 * @param p double precio material
	 * @param c int cantidad material
	 */
	public MaterialUsado(String n, double p, int c) {
		super(n, p);

		cantidad = c;
	}

	// ===== métodos =====
	// --- salida ---
	/**
	 * Devuelve una representación de cadena del objeto.
	 * 
	 * @return devuelve un valor hash
	 */
	@Override
	public String toString() {
		return super.toString() + ", cantidad: " + cantidad;
	}

	// --- comparación ---
	/**
	 * devuelve el hash code del objeto basado en sus atributos
	 * 
	 * @return devuelve un valor hash
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cantidad);
		return result;
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaterialUsado other = (MaterialUsado) obj;
		return cantidad == other.cantidad;
	}

	// --- getters y setters ---
	/**
	 * acceso a cantidad
	 * 
	 * @return cantidad tipo String
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * modifica el valor de cantidad pasando int como parametro
	 * 
	 * @param c tipo int
	 */
	public void setCantidad(int c) {
		cantidad = c;
	}
}
