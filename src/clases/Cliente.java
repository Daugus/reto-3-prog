package clases;

import java.util.Objects;

public class Cliente extends Persona {
	private static final long serialVersionUID = -4712701685349672058L;

	// ===== propiedades =====
	private Fecha fechaAlta;
	private boolean activo;

	// ===== constructores =====
	/**
	 * constructor por defecto
	 */
	public Cliente() {
		super();

		activo = true;
		fechaAlta = new Fecha();
	}

	/**
	 * constructor copia
	 */
	public Cliente(Cliente other) {
		super(other);

		activo = other.activo;
		fechaAlta = new Fecha(other.fechaAlta);
	}

	// --- personalizados ---
	/**
	 * cliente personalizado constructor personalizado
	 * 
	 * @param d   String Dni
	 * @param n   Strnig nombre
	 * @param a   String apellido
	 * @param t   int telefono
	 * @param e   String email
	 * @param dir direccion
	 * @param fa  Objeto fechaAlta
	 * @param v   ArrayList<String> array de las matrículas de los vehículos
	 */
	public Cliente(String d, String n, String a, String t, String e, String dir, Fecha fa, boolean act) {
		super(d, n, a, t, e, dir);

		activo = act;
		fechaAlta = new Fecha(fa);
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
		return super.toString() + ", activo: " + activo + ", fecha de alta: " + fechaAlta;
	}
	
	// --- comparacion ---
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(activo, fechaAlta);
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
		Cliente other = (Cliente) obj;
		return activo == other.activo && Objects.equals(fechaAlta, other.fechaAlta);
	}

	// --- getters y setters ---
	/**
	 * acceso a fechaAlta
	 * 
	 * @return fechaAlta
	 */
	public Fecha getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * modifica el valor de fechaAlta pasando tema como parametro
	 * 
	 * @param fechaAlta
	 */
	public void setFechaAlta(Fecha fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean getActivo() {
		return activo;
	}
}