package clases;

import java.io.Serializable;
import java.util.Objects;

public class Vehiculo implements Comparable<Vehiculo>, Serializable {
	private static final long serialVersionUID = 2469183365830659245L;

	// ===== propiedades =====
	private String matricula;
	private String bastidor;
	private String propietario;

	private String marca;
	private String modelo;

	private Fecha fechaFabricacion;

	private String tipo;

	private boolean activo;

	// ===== constructores =====
	/**
	 * vehiculo por defecto
	 */
	public Vehiculo() {
		matricula = "";
		bastidor = "";
		propietario = "";

		marca = "";
		modelo = "";

		activo = true;

		fechaFabricacion = new Fecha();

		tipo = "";
	}

	/**
	 * constructor copia
	 */
	public Vehiculo(Vehiculo other) {
		this.matricula = other.matricula;
		this.bastidor = other.bastidor;
		this.propietario = other.propietario;

		this.marca = other.marca;
		this.modelo = other.modelo;

		this.activo = other.activo;

		this.fechaFabricacion = other.fechaFabricacion;

		this.tipo = other.tipo;
	}

	// --- personalizados ---
	/**
	 * vehículo en blanco, usado para agregar los vehículos de un cliente nuevo
	 * 
	 * @param m String matricula
	 * @param p String propietario
	 */
	public Vehiculo(String m, String p) {
		matricula = m;
		bastidor = "";
		propietario = p;

		marca = "";
		modelo = "";

		activo = true;

		fechaFabricacion = new Fecha();

		tipo = "";
	}

	public Vehiculo(String matricula,
			String bastidor, String propietario,
			String marca, String modelo,
			Fecha fechaFabricacion, String tipo,
			boolean activo) {
		this.matricula = matricula;
		this.bastidor = bastidor;
		this.propietario = propietario;

		this.marca = marca;
		this.modelo = modelo;

		this.fechaFabricacion = fechaFabricacion;

		this.tipo = tipo;

		this.activo = activo;
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
		return "Matrícula:  " + matricula + ", Propietario: " + propietario + ", Modelo: " + marca + " " + modelo;
	}

	// --- comparación ---
	/**
	 * devuelve el hash code del objeto basado en sus atributos
	 * 
	 * @return devuelve un valor hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(propietario, activo, fechaFabricacion, marca, matricula, modelo, bastidor, tipo);
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
		Vehiculo other = (Vehiculo) obj;
		return Objects.equals(activo, other.activo) && Objects.equals(fechaFabricacion, other.fechaFabricacion)
				&& Objects.equals(marca, other.marca) && Objects.equals(matricula, other.matricula)
				&& Objects.equals(modelo, other.modelo) && bastidor == other.bastidor
				&& Objects.equals(tipo, other.tipo) && Objects.equals(propietario, other.propietario);
	}

	/**
	 * Compara este objeto con el objeto especificado para el orden. Devuelve un
	 * entero negativo, cero o un entero positivo, ya que este objeto es menor,
	 * igual o mayor que el objeto especificado.
	 * 
	 * @return entero negativo, cero o un entero positivo, ya que este objeto es
	 *         menor, igual o mayor que el objeto especificado.
	 * @return other tipo Vehiculo
	 */
	@Override
	public int compareTo(Vehiculo other) {
		return matricula.compareTo(other.matricula);
	}

	// --- getters y setters ---
	/**
	 * acceso a matricula
	 * 
	 * @return matricula tipo String
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * modifica el valor de tema matricula String como parametro
	 * 
	 * @param m tipo String
	 */
	public void setMatricula(String m) {
		matricula = m;
	}

	/**
	 * acceso a bastidor
	 * 
	 * @return bastidor tipo String
	 */
	public String getBastidor() {
		return bastidor;
	}

	/**
	 * modifica el valor de bastidor pasando String como parametro
	 * 
	 * @param b tipo String
	 */
	public void setBastidor(String b) {
		bastidor = b;
	}

	/**
	 * acceso a modelo
	 * 
	 * @return modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * modifica el valor de modelo pasando String como parametro
	 * 
	 * @param m tipo String
	 */
	public void setModelo(String m) {
		modelo = m;
	}

	/**
	 * acceso a marca
	 * 
	 * @return marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * modifica el valor de marca pasando String como parametro
	 * 
	 * @param m tipo String
	 */
	public void setMarca(String m) {
		marca = m;
	}

	/**
	 * acceso a fechaFabricacion
	 * 
	 * @return fechaFabricacion
	 */
	public Fecha getFechaFabricacion() {
		return fechaFabricacion;
	}

	/**
	 * modifica el valor de fechaFabricacion pasando de la calse Fecha como
	 * parametro
	 * 
	 * @param itv tipo Fecha
	 */
	public void setFechaFabricacion(Fecha fechFab) {
		fechaFabricacion = fechFab;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean a) {
		activo = a;
	}

	/**
	 * acceso a tipo
	 * 
	 * @return tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * modifica el valor de tipo pasando String como parametro
	 * 
	 * @param t tipo String
	 */
	public void setTipo(String t) {
		tipo = t;
	}

	/**
	 * acceso a propietario
	 * 
	 * @return propietario
	 */
	public String getPropietario() {
		return propietario;
	}

	/**
	 * modifica el valor de propietario pasando String como parametro
	 * 
	 * @param p tipo String
	 */
	public void setPropietario(String p) {
		propietario = p;
	}
}