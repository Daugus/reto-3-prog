package clases;

import java.util.Objects;

/**
 * clase de Cliente
 */
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

	// --- personalizados ---
	/**
	 * constructor personalizado
	 * 
	 * @param dni       DNI del cliente
	 * @param nombre    nombre del cliente
	 * @param apellidos apellidos del cliente
	 * @param telefono  telefono del cliente
	 * @param email     email del cliente
	 * @param direccion dir del cliente
	 * @param fechaAlta fecha de alta del cliente
	 * @param activo    estado del cliente
	 */
	public Cliente(String dni, String nombre, String apellidos, String telefono, String email, String direccion,
			Fecha fechaAlta, boolean activo) {
		super(dni, nombre, apellidos, telefono, email, direccion);

		this.fechaAlta = new Fecha(fechaAlta);
		this.activo = activo;
	}

	// ===== métodos =====
	// --- salida ---
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

	// --- getters ---
	public Fecha getFechaAlta() {
		return fechaAlta;
	}

	public boolean isActivo() {
		return activo;
	}
}