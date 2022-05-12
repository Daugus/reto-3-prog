package clases;

import java.io.Serializable;
import java.util.Objects;

/**
 * clase abstracta de Persona
 */
public abstract class Persona implements Comparable<Persona>, Serializable {
	private static final long serialVersionUID = 6717239572208402072L;

	// ===== propiedades =====
	private String dni;

	private String nombre;
	private String apellidos;

	private String telefono;
	private String email;

	private String direccion;

	// ===== constructores =====
	/**
	 * constructor por defecto
	 */
	public Persona() {
		dni = "";

		nombre = "";
		apellidos = "";

		telefono = "600000000";
		email = "";

		direccion = "";
	}

	/**
	 * constructor copia
	 * 
	 * @param other Objeto que se va a copiar
	 */
	public Persona(Persona other) {
		this.dni = other.dni;

		this.nombre = other.nombre;
		this.apellidos = other.apellidos;

		this.telefono = other.telefono;
		this.email = other.email;

		this.direccion = other.direccion;
	}

	/**
	 * persona personalizado
	 *
	 * @param dni       DNI del cliente
	 * @param nombre    nombre del cliente
	 * @param apellidos apellidos del cliente
	 * @param telefono  telefono del cliente
	 * @param email     email del cliente
	 * @param direccion dir del cliente
	 */
	public Persona(String dni, String nombre, String apellidos, String telefono, String email, String direccion) {
		this.dni = dni;

		this.nombre = nombre;
		this.apellidos = apellidos;

		this.telefono = telefono;
		this.email = email;

		this.direccion = direccion;
	}

	// ===== métodos =====
	// --- salida ---
	@Override
	public String toString() {
		return "DNI: " + dni + ", nombre: " + nombre + ", apellidos: " + apellidos + ", teléfono: " + telefono
				+ ", email: " + email + ", dirección: " + direccion;
	}

	// --- comparación ---
	@Override
	public int hashCode() {
		return Objects.hash(dni, nombre, apellidos, email, nombre, telefono, direccion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(dni, other.dni)
				&& Objects.equals(email, other.email) && Objects.equals(nombre, other.nombre)
				&& telefono == other.telefono && Objects.equals(direccion, other.direccion);
	}

	@Override
	public int compareTo(Persona other) {
		return dni.compareTo(other.dni);
	}

	// --- getters ---
	public String getDNI() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getEmail() {
		return email;
	}

	public String getDireccion() {
		return direccion;
	}
}