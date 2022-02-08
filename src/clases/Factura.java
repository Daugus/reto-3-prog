package clases;

import java.util.ArrayList;
import java.util.Objects;

/**
 * esta clase difiene facturas que se usaran en el programa
 * @author Grupo 2
 * @version 2.0.1
 */
public class Factura extends Pendiente {
	private static final long serialVersionUID = 6717239572208402072L;

	// ===== propiedades =====
	private double costeReparaciones = 0;
	private double costeMateriales = 0;
	private double subtotal;
	private double iva;
	private double total;

	/**
	 * constructor por defeccto hereda 
	 */
	public Factura() {
		super();

		costeReparaciones = 0;
		costeMateriales = 0;
		subtotal = 0;
		iva = 0;
		total = 0;
	}

	/**
	 * constructor copia
	 * @param other copia del constructor por defecto
	 */
	public Factura(Factura other) {
		super(other);

		this.costeReparaciones = other.costeReparaciones;
		this.costeMateriales = other.costeMateriales;
		this.subtotal = other.subtotal;
		this.iva = other.iva;
		this.total = other.total;
	}

	// --- personalizados ---
	public Factura(String com, Cliente c, Vehiculo v, Cuenta atc, ArrayList<Reparacion> r) {
		super(com, c, v, atc, r);

		calcularTotal();
	}
	/**
	 * genera un codigo y calcula el total de la factura pendiente
	 * @param pendiente 
	 */
	public Factura(Pendiente pendiente) {
		super(pendiente);

		generarCodigo();
		calcularTotal();
	}

	// ===== métodos =====
	// --- personalizado ---
	private void calcularTotal() {
		for (Reparacion r : getReparaciones()) {
			for (MaterialUsado mu : r.getMaterialesUsados()) {
				costeMateriales += mu.getCantidad() * mu.getPrecio();
			}

			costeReparaciones += r.getHoras() * r.getManoObra();
		}

		subtotal = costeReparaciones + costeMateriales;
		iva = subtotal * 0.21;
		total = subtotal + iva;
	}

	// --- salida ---
	@Override
	public String toString() {
		return super.toString() + ", total reparaciones: " + costeReparaciones + ", total materiales: "
				+ costeMateriales + ", subtotal: " + subtotal + ", coste iva: " + iva + ", total: " + total;
	}

	// --- comparación ---
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(costeMateriales, costeReparaciones, iva, subtotal, total);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Factura other = (Factura) obj;
		return Double.doubleToLongBits(costeMateriales) == Double.doubleToLongBits(other.costeMateriales)
				&& Double.doubleToLongBits(costeReparaciones) == Double.doubleToLongBits(other.costeReparaciones)
				&& Double.doubleToLongBits(iva) == Double.doubleToLongBits(other.iva)
				&& Double.doubleToLongBits(subtotal) == Double.doubleToLongBits(other.subtotal)
				&& Double.doubleToLongBits(total) == Double.doubleToLongBits(other.total);
	}

	/**
	 * accede a la propiedad costaReparaciones
	 * @return devuelve costeReparaciones
	 */
	public double getCosteReparaciones() {
		return costeReparaciones;
	}
	/**
	 * accede a la propiedad costeMateriales
	 * @return devuelve costeMateriales
	 */
	public double getCosteMateriales() {
		return costeMateriales;
	}
	/**
	 * accede a la propiedad subtotal
	 * @return devuelve subtotal
	 */
	public double getSubtotal() {
		return subtotal;
	}
	/**
	 * accede a la propiedad iva
	 * @return devuelve iva
	 */
	public double getIva() {
		return iva;
	}
	/**
	 * accede a la propiedad total
	 * @return devuelve total
	 */
	public double getTotal() {
		return total;
	}

}