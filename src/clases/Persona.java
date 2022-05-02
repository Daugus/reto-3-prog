package clases;

import java.io.Serializable;
import java.util.Objects;

public abstract class Persona implements Comparable<Persona>, Serializable {
	private static final long serialVersionUID = 6717239572208402072L;

	// ===== propiedades =====
	private String dni;

	private String nombre;
	private String apellidos;

	private int tel;
	private String email;

	private Direccion direccion;

	// ===== constructores =====
	/**
	 * persona por defecto
	 */
	public Persona() {
		dni = "";

		nombre = "";
		apellidos = "";

		tel = 600000000;
		email = "";

		direccion = new Direccion();
	}

	/**
	 * constructor copia
	 */
	public Persona(Persona other) {
		this.dni = other.dni;

		this.nombre = other.nombre;
		this.apellidos = other.apellidos;

		this.tel = other.tel;
		this.email = other.email;

		this.direccion = new Direccion(other.direccion);
	}

	/**
	 * persona personalizado
	 * 
	 * @param d   String dni
	 * @param n   String nombre
	 * @param a   String apellidos
	 * @param t   int telefono
	 * @param e   String email
	 * @param fn  objeto fecha
	 * @param dir objeto direccion
	 */
	public Persona(String d, String n, String a, int t, String e, Direccion dir) {
		dni = d;

		nombre = n;
		apellidos = a;

		tel = t;
		email = e;

		direccion = new Direccion(dir);
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
		return "DNI: " + dni + ", nombre: " + nombre + ", apellidos: " + apellidos + ", teléfono: " + tel + ", email: "
				+ email + ", dirección: " + direccion;
	}

	// --- comparación ---
	/**
	 * devuelve el hash code del objeto basado en sus atributos
	 * 
	 * @return devuelve un valor hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(apellidos, dni, email, nombre, tel);
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(dni, other.dni)
				&& Objects.equals(email, other.email) && Objects.equals(nombre, other.nombre) && tel == other.tel
				&& Objects.equals(direccion, other.direccion);
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
	public int compareTo(Persona other) {
		return dni.compareTo(other.dni);
	}

	// --- getters y setters ---
	/**
	 * acceso a dni
	 * 
	 * @return dni
	 */
	public String getDNI() {
		return dni;
	}

	/**
	 * modifica el valor de dni pasando String como parametro
	 * 
	 * @param d tipo String
	 * @return
	 */
	public void setDNI(String d) {
		dni = d;
	}

	/**
	 * acceso a nombre
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * modifica el valor de nombre pasando String como parametro
	 * 
	 * @param n tipo String
	 */
	public void setNombre(String n) {
		nombre = n;
	}

	/**
	 * acceso a apellidos
	 * 
	 * @return apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * modifica el valor de apellidos pasando String como parametro
	 * 
	 * @param a tipo String
	 */
	public void setApellidos(String a) {
		apellidos = a;
	}

	/**
	 * acceso a telelfono
	 * 
	 * @return tel tipo int
	 */
	public int getTelefono() {
		return tel;
	}

	/**
	 * modifica el valor de tel pasando int como parametro
	 * 
	 * @param t tipo int
	 */
	public void setTelefono(int t) {
		tel = t;
	}

	/**
	 * acceso a email
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * modifica el valor de email pasando String como parametro
	 * 
	 * @param e tipo String
	 */
	public void setEmail(String e) {
		email = e;
	}

	/**
	 * acceso a direccion
	 * 
	 * @return direccion tipo Direccion
	 */
	public Direccion getDireccion() {
		return direccion;
	}

	/**
	 * modifica el valor de direccion pasando Direccion como parametro
	 * 
	 * @param dir tipo Direccion
	 */
	public void setDireccion(Direccion dir) {
		direccion = new Direccion(dir);
	}
}