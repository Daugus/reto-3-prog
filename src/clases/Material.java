package clases;

import java.io.Serializable;
import java.util.Objects;

/**
 * clase de Material
 */
public class Material implements Comparable<Material>, Serializable {
	private static final long serialVersionUID = -4533693024823055118L;

	// ===== propiedades =====
	private String id;

	private String marca;
	private String nombre;

	private int stock;
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
		stock = 1;
		pvp = 1.0;
		precioCompra = 1.0;
		activo = true;
	}

	// --- personalizados ---
	/**
	 * constructor personalizado para listar dentro de la tabla de reparaciones
	 * 
	 * @param id ID del material
	 */
	public Material(String id) {
		this.id = id;
	}

	/**
	 * constructor personalizado completo
	 * 
	 * @param id           ID del material
	 * @param marca        marca del material
	 * @param nombre       nombre del material
	 * @param stock        stock del material
	 * @param pvp          precio de venta del material
	 * @param precioCompra precio de compra del material
	 * @param activo       estado del material
	 */
	public Material(String id, String marca, String nombre, int stock, double pvp, double precioCompra,
			boolean activo) {
		this.id = id;
		this.marca = marca;
		this.nombre = nombre;
		this.stock = stock;
		this.pvp = pvp;
		this.precioCompra = precioCompra;
		this.activo = activo;
	}

	// ===== métodos =====
	// --- salida ---
	public String toString() {
		return "ID:" + id + ", marca: " + marca + ", nombre: " + nombre + ", stock: " + stock + ", precio: " + pvp
				+ ", precio compra: " + precioCompra + ", activo: " + activo;
	}

	// --- comparación ---
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

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

	@Override
	public int compareTo(Material other) {
		return id.compareTo(other.id);
	}

	// --- getters ---
	public String getID() {
		return id;
	}

	public String getMarca() {
		return marca;
	}

	public String getNombre() {
		return nombre;
	}

	public int getStock() {
		return stock;
	}

	public double getPVP() {
		return pvp;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public boolean isActivo() {
		return activo;
	}
}