package clases;

import java.io.Serializable;
import java.util.Objects;

/**
 * esta clase difiene propiedades y metodos del vehiculo
 * 
 * @author Grupo 2
 * @version 2.0.1
 */
public class Vehiculo implements Comparable<Vehiculo>, Serializable {
	private static final long serialVersionUID = 2469183365830659245L;

	// ===== propiedades =====
	private String matricula;
	private String bastidor;
	private String propietario;

	private String marca;
	private String modelo;
	private String color;

	private int cilindrada;

	private int kmRecorridos;
	private Fecha fechaITV;

	private String tipo;

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
		color = "";

		cilindrada = 0;

		kmRecorridos = 0;
		fechaITV = new Fecha();

		tipo = "";
	}

	/**
	 * vehiculo copia
	 * 
	 * @param other copia
	 */
	public Vehiculo(Vehiculo other) {
		this.matricula = other.matricula;
		this.bastidor = other.bastidor;
		this.propietario = other.propietario;

		this.marca = other.marca;
		this.modelo = other.modelo;
		this.color = other.color;

		this.cilindrada = other.cilindrada;

		this.kmRecorridos = other.kmRecorridos;
		this.fechaITV = other.fechaITV;

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
		color = "";

		cilindrada = 0;

		kmRecorridos = 0;
		fechaITV = new Fecha();

		tipo = "";
	}

	/**
	 * vehículo en blanco
	 * 
	 * @param m   String maticula
	 * @param b   string bastidor
	 * @param dni string dni
	 * @param mar String marca
	 * @param mod Strnig modelo
	 * @param c   String color
	 * @param cc  int cilindrada
	 * @param km  int kmrecorridos
	 * @param i   objeto fecha(fechaItv)
	 * @param t   String tipo
	 */
	public Vehiculo(String m, String b, String dni, String mar, String mod, String c, int cc, int km, Fecha i,
			String t) {
		matricula = m;
		bastidor = b;
		propietario = dni;

		marca = mar;
		modelo = mod;
		color = c;

		cilindrada = cc;

		kmRecorridos = km;
		fechaITV = i;

		tipo = t;
	}

	// ===== métodos =====
	// --- salida ---
	/**
	 * Devuelve una representación de cadena del objeto.
	 * 
	 * @return matricula, propietario, marca y modelo
	 */
	@Override
	public String toString() {
		return "Matrícula:  " + matricula + ", Propietario: " + propietario + ", Modelo: " + marca + " " + modelo;
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
		return Objects.hash(cilindrada, propietario, color, fechaITV, kmRecorridos, marca, matricula, modelo, bastidor,
				tipo);
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
		Vehiculo other = (Vehiculo) obj;
		return Objects.equals(cilindrada, other.cilindrada) && Objects.equals(color, other.color)
				&& Objects.equals(fechaITV, other.fechaITV) && kmRecorridos == other.kmRecorridos
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
	 * acceso a kmRecorridos
	 * 
	 * @return kmRecorridos
	 */
	public int getKmRecorridos() {
		return kmRecorridos;
	}

	/**
	 * modifica el valor de kmRecorridos pasando int como parametro
	 * 
	 * @param km tipo int
	 */
	public void setKmRecorridos(int km) {
		kmRecorridos = km;
	}

	/**
	 * acceso a cilindrada
	 * 
	 * @return cilindrada
	 */
	public int getCilindrada() {
		return cilindrada;
	}

	/**
	 * modifica el valor de cilindrada pasando int como parametro
	 * 
	 * @param cc tipo int
	 */
	public void setCilindrada(int cc) {
		cilindrada = cc;
	}

	/**
	 * acceso a fechaITV
	 * 
	 * @return fechaITV
	 */
	public Fecha getFechaITV() {
		return fechaITV;
	}

	/**
	 * modifica el valor de fechaITV pasando itv de la calse Fecha como parametro
	 * 
	 * @param itv tipo Fecha
	 */
	public void setFechaITV(Fecha itv) {
		fechaITV = itv;
	}

	/**
	 * acceso a color
	 * 
	 * @return color tipo String
	 */
	public String getColor() {
		return color;
	}

	/**
	 * modifica el valor de color pasando String como parametro
	 * 
	 * @param c tipo String
	 */
	public void setColor(String c) {
		color = c;
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