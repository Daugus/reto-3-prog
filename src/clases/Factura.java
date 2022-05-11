package clases;

import java.io.Serializable;
import java.util.Objects;

public class Factura implements Comparable<Factura>, Serializable {
	private static final long serialVersionUID = 6717239572208402072L;

	// ===== propiedades =====
	private String codigo;
	private String codigoOrden;

	private String metodoPago;
	private boolean pagada;
	private int descuento;
	
	private Fecha fecha;

	// ===== constructores =====
	/**
	 * constructor por defeccto hereda
	 */
	public Factura() {
		super();

		codigo = "";
		codigoOrden = "";
		metodoPago = "Tarjeta";
		pagada = true;
		descuento = 0;
		fecha = new Fecha();
	}

	/**
	 * constructor copia
	 */
	public Factura(Factura other) {
		this.codigo = other.codigo;
		this.codigoOrden = other.codigoOrden;
		this.metodoPago = other.metodoPago;
		this.pagada = other.pagada;
		this.descuento = other.descuento;
		this.fecha = new Fecha(other.fecha);
	}

	// --- personalizados ---
	public Factura(String codigo, String codigoOrden, boolean pagada, Fecha fecha) {
		this.codigo = codigo;
		this.codigoOrden = codigoOrden;
		this.pagada = pagada;
		this.fecha = new Fecha(fecha);
	}
	
	public Factura(String codigo, String codigoOrden, String metodoPago, boolean pagada, int descuento, Fecha fecha) {
		this.codigo = codigo;
		this.codigoOrden = codigoOrden;
		this.metodoPago = metodoPago;
		this.pagada = pagada;
		this.descuento = descuento;
		this.fecha = new Fecha(fecha);
	}

	// ===== métodos =====
	// --- salida ---
	@Override
	public String toString() {
		return "Código: " + codigo + ", ódigo orden: " + codigoOrden + ", método de pago: " + metodoPago + ", pagada: "
				+ pagada + ", descuento: " + descuento + ", fecha: " + fecha;
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
		Factura other = (Factura) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public int compareTo(Factura other) {
		return codigo.compareTo(other.codigo);
	}

	// --- getters y setters ---
	public String getCodigo() {
		return codigo;
	}

	public String getCodigoOrden() {
		return codigoOrden;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public boolean isPagada() {
		return pagada;
	}

	public int getDescuento() {
		return descuento;
	}

	public Fecha getFecha() {
		return fecha;
	}
}