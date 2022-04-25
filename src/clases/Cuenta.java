package clases;

import java.util.Objects;

public class Cuenta extends Persona {
	private static final long serialVersionUID = -1206167340211110010L;

	// ===== propiedades =====
	private boolean mecanico;
	private String password;
	private Ajustes ajustes;

	// ===== constructores =====
	/**
	 * constructor por defecto
	 */
	public Cuenta() {
		super();

		mecanico = true;
		password = "";
		ajustes = new Ajustes();
	}

	/**
	 * constructor personalizado
	 * 
	 * @param cod codigo del mecanico
	 */
	public Cuenta(boolean cod) {
		super();

		if (cod) {
			setDNI("mec");
			password = "mec";
		} else {
			setDNI("atc");
			password = "atc";
		}

		mecanico = cod;
	}

	/**
	 * constructor copia
	 */
	public Cuenta(Cuenta other) {
		super(other);

		mecanico = other.mecanico;
		password = other.password;
		ajustes = new Ajustes(other.ajustes);
	}

	// --- personalizados ---
	/**
	 * persona por defecto
	 * 
	 * @param cod de persona
	 * @param p   contaseña
	 * @param a   ajustes personalizados
	 */
	public Cuenta(boolean cod, String p, Ajustes a) {
		super();

		mecanico = cod;
		password = p;
		ajustes = new Ajustes(a);
	}

	// Persona nueva con código, contraseña y ajustes personalizados
	/**
	 * persona nueva
	 * 
	 * @param d   Strnig dni
	 * @param n   String nombre
	 * @param a   Strnig apellido
	 * @param t   int telefono
	 * @param e   String email
	 * @param fn  Object fecha
	 * @param dir Object direccion
	 * @param cod Boolean mecanico
	 * @param pa  String password
	 * @param aj  Objeto Ajustes
	 */
	public Cuenta(String d, String n, String a, int t, String e, Fecha fn, Direccion dir, boolean cod, String pa,
			Ajustes aj) {
		super(d, n, a, t, e, fn, dir);

		mecanico = cod;
		password = pa;
		ajustes = new Ajustes(aj);
	}

	// ===== métodos =====
	/**
	 * Devuelve una representación de cadena del objeto.
	 * 
	 * @return tipo de usuario
	 */
	@Override
	public String toString() {
		String tipo;
		if (mecanico) {
			tipo = "Mecánico";
		} else {
			tipo = "Atención al cliente";
		}

		return super.toString() + ", tipo: " + tipo;
	}

	// --- comparación ---
	/**
	 * devuelve el hash code del objeto basado en sus atributos
	 * 
	 * @return el hash
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(ajustes, mecanico, password);
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
		Cuenta other = (Cuenta) obj;
		return Objects.equals(ajustes, other.ajustes) && mecanico == other.mecanico
				&& Objects.equals(password, other.password);
	}

	// --- getters y setters ---
	/**
	 * acceso a tipo
	 * 
	 * @return tema devuelve si es mecanico o atencion al cliente
	 */
	public String tipo() {
		String tipo;
		if (mecanico) {
			tipo = "Mecánico";
		} else {
			tipo = "Atención al cliente";
		}

		return tipo;
	}

	/**
	 * acceso a password
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * modifica el valor de password pasando password como parametro
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * acceso a mecanico
	 * 
	 * @return mecanico
	 */
	public boolean esMecanico() {
		return mecanico;
	}

	/**
	 * modifica el valor de mecanico pasando mecanico como parametro
	 * 
	 * @param mecanico tipo boolean
	 */
	public void setMecanico(boolean mecanico) {
		this.mecanico = mecanico;
	}

	/**
	 * acceso a ajustes
	 * 
	 * @return ajustes
	 */
	public Ajustes getAjustes() {
		return ajustes;
	}

	/**
	 * modifica el valor de ajustes pasando ajustes como parametro
	 * 
	 * @param ajustes
	 */
	public void setAjustes(Ajustes ajustes) {
		this.ajustes = new Ajustes(ajustes);
	}
}
