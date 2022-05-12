package clases;

/**
 * clase del Total de la factura
 */
public class Total {
	// ===== propiedades =====
	private final int IVA = 21;

	private double costeReparaciones;
	private double costeMateriales;

	private double descuento;
	private double subtotal;

	private double costeIVA;

	private double total;

	// ===== constructor =====
	/**
	 * constructor personalizado que calcula todos los valores para mostrar factura
	 * 
	 * @param costeReparaciones el coste de las reparaciones realizadas
	 * @param costeMateriales   el coste de los materiales usados
	 * @param descuento         porcentaje de descuento que se va a aplicar
	 */
	public Total(double costeReparaciones, double costeMateriales, int porcentajeDescuento) {
		this.costeReparaciones = costeReparaciones;
		this.costeMateriales = costeMateriales;

		double subtotalSinDescuento = costeReparaciones + costeMateriales;
		descuento = (subtotalSinDescuento * porcentajeDescuento) / 100;
		subtotal = subtotalSinDescuento - descuento;
		costeIVA = subtotal * IVA / 100;
		total = subtotal + costeIVA;
	}

	// ===== getters =====
	public double getCosteReparaciones() {
		return costeReparaciones;
	}

	public double getCosteMateriales() {
		return costeMateriales;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public double getDescuento() {
		return descuento;
	}

	public double getCosteIVA() {
		return costeIVA;
	}

	public double getTotal() {
		return total;
	}

	public int getIVA() {
		return IVA;
	}
}