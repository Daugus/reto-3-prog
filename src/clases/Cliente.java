package clases;

import java.util.ArrayList;
import java.util.Objects;

public class Cliente extends Persona {
	private static final long serialVersionUID = -4712701685349672058L;

	// ===== propiedades =====
	private Fecha fechaAlta;
	private ArrayList<String> vehiculos = new ArrayList<String>();

	// ===== constructores =====
	/**
	 * constructor por defecto
	 */
	public Cliente() {
		super();

		fechaAlta = new Fecha();
	}

	/**
	 * constructor copia
	 */
	public Cliente(Cliente other) {
		super(other);

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
	 * @param fn  Objeto fecha
	 * @param dir direccion
	 * @param fa  Objeto fechaAlta
	 * @param v   ArrayList<String> array de las matrículas de los vehículos
	 */
	public Cliente(String d, String n, String a, int t, String e, Fecha fn, Direccion dir, Fecha fa,
			ArrayList<String> v) {
		super(d, n, a, t, e, fn, dir);

		fechaAlta = new Fecha(fa);
		vehiculos = v;
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
		return super.toString() + ", fecha de alta: " + fechaAlta + ", vehículos: " + vehiculos;
	}

	// --- comparacion ---
	/**
	 * devuelve el hash code del objeto basado en sus atributos
	 * 
	 * @return el hash
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(fechaAlta);
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
		Cliente other = (Cliente) obj;
		return Objects.equals(fechaAlta, other.fechaAlta);
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

	/**
	 * acceso a vehiculos
	 * 
	 * @return vehiculos piu
	 */
	public ArrayList<String> getVehiculos() {
		return vehiculos;
	}
}