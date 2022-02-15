package clases;

import java.io.Serializable;
import java.util.Objects;

/**
 * esta clase difiene Personas que se usaran en el programa
 * 
 * @author Grupo 2
 * @version 2.0.1
 */
public abstract class Persona implements Comparable<Persona>, Serializable {
	private static final long serialVersionUID = 6717239572208402072L;

	// ===== propiedades =====
	private String dni;

	private String nombre;
	private String apellidos;

	private int tel;
	private String email;

	private Fecha fechaNacimiento;

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

		fechaNacimiento = new Fecha();

		direccion = new Direccion();
	}

	/**
	 * persona copia
	 * 
	 * @param other copia de persona
	 */
	public Persona(Persona other) {
		this.dni = other.dni;

		this.nombre = other.nombre;
		this.apellidos = other.apellidos;

		this.tel = other.tel;
		this.email = other.email;

		this.fechaNacimiento = new Fecha(other.fechaNacimiento);

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
	public Persona(String d, String n, String a, int t, String e, Fecha fn, Direccion dir) {
		dni = d;

		nombre = n;
		apellidos = a;

		tel = t;
		email = e;

		fechaNacimiento = new Fecha(fn);

		direccion = new Direccion(dir);
	}

	// ===== métodos =====
	// --- salida ---
	/**
	 * Devuelve una representación de cadena del objeto.
	 * 
	 * @return dni, nombre, apellidos, tel, email,fechaNacimiento y direccion
	 */
	@Override
	public String toString() {
		return "DNI: " + dni + ", nombre: " + nombre + ", apellidos: " + apellidos + ", teléfono: " + tel + ", email: "
				+ email + ", fecha de nacimiento: " + fechaNacimiento + ", dirección: " + direccion;
	}

	// --- comparación ---
	/**
	 * para el objeto Este método es compatible en beneficio de las tablas hash
	 * como las proporcionadas por java.util.HashMap.
	 * 
	 * @return devuelve un valor hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(apellidos, dni, email, fechaNacimiento, nombre, tel);
	}

	/**
	 * 
	 * Indica si algún otro objeto es "igual a" este.
	 * 
	 * @return true si este objeto es el mismo que el objargument; falso en caso
	 *         contrario.
	 * @param obj objeto referente con el que desea comparar
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
				&& Objects.equals(email, other.email) && Objects.equals(fechaNacimiento, other.fechaNacimiento)
				&& Objects.equals(nombre, other.nombre) && tel == other.tel
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
	 * acceso a fechaNacimiento
	 * 
	 * @return fechaNacimiento
	 */
	public Fecha getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * modifica el valor de fechaNacimiento pasando fecha como parametro
	 * 
	 * @param fn tipo fecha
	 */
	public void setFechaNacimiento(Fecha fn) {
		fechaNacimiento = new Fecha(fn);
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