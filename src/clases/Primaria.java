package clases;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Primaria implements Comparable<Primaria>, Serializable {
	private static final long serialVersionUID = 6717239572208402072L;

	// ===== propiedades =====
	private String codigo;
	private String comentarios;
	private Fecha fecha;
	private Cliente propietario;
	private Vehiculo vehiculo;
	private Cuenta empleado;

	// ===== constructores =====
	/**
	 * orden primaria por defecto
	 */
	public Primaria() {
		generarCodigo();
		comentarios = "";
		fecha = new Fecha();

		propietario = new Cliente();
		vehiculo = new Vehiculo();

		empleado = new Cuenta(false);
	}

	/**
	 * constructor copia
	 */
	public Primaria(Primaria other) {
		this.codigo = other.codigo;
		this.comentarios = other.comentarios;
		this.fecha = new Fecha(other.fecha);

		this.propietario = new Cliente(other.propietario);
		this.vehiculo = new Vehiculo(other.vehiculo);

		this.empleado = new Cuenta(other.empleado);
	}

	/**
	 * orden primaria personalizado
	 * 
	 * @param com String comentario
	 * @param c   objeto cliente
	 * @param v   objeto vehiculo
	 * @param atc objeto cuenta atencion cliente
	 */
	public Primaria(String com, Cliente c, Vehiculo v, Cuenta atc) {
		generarCodigo();
		comentarios = com;
		fecha = new Fecha();

		propietario = new Cliente(c);
		vehiculo = new Vehiculo(v);

		empleado = new Cuenta(atc);
	}

	// ===== métodos =====
	// --- personalizado ---
	protected void generarCodigo() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		codigo = formatter.format(calendar.getTime());
	}

	// --- salida ---
	/**
	 * Devuelve una representación de cadena del objeto.
	 * 
	 * @return los atributos del objeto
	 */
	@Override
	public String toString() {
		return "Código: " + codigo + ", comentarios: " + comentarios + ", fechaEntrada: " + fecha + ", vehículo: "
				+ vehiculo + ", propietario: " + propietario + ", empleado: " + empleado.getDNI();
	}

	// --- comparación ---
	/**
	 * devuelve el hash code del objeto basado en sus atributos
	 * 
	 * @return devuelve un valor hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(codigo, comentarios, fecha, propietario, vehiculo, empleado);
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
		Primaria other = (Primaria) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(comentarios, other.comentarios)
				&& Objects.equals(fecha, other.fecha) && Objects.equals(propietario, other.propietario)
				&& Objects.equals(vehiculo, other.vehiculo) && Objects.equals(empleado, other.empleado);
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
	public int compareTo(Primaria other) {
		return codigo.compareTo(other.codigo);
	}

	// --- getters y setters ---
	/**
	 * acceso a codigo
	 * 
	 * @return codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * modifica el valor de codigo pasando String como parametro
	 * 
	 * @param codOrdenPrim tipo boolean
	 */
	public void setCodigo(String codOrdenPrim) {
		this.codigo = codOrdenPrim;
	}

	/**
	 * acceso a comentarios
	 * 
	 * @return comentarios tipo String
	 */
	public String getComentarios() {
		return comentarios;
	}

	/**
	 * modifica el valor de comentarios pasando String como parametro
	 * 
	 * @param comentarios tipo String
	 */
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	/**
	 * acceso a fecha
	 * 
	 * @return fecha
	 */
	public Fecha getFecha() {
		return fecha;
	}

	/**
	 * modifica el valor de fecha pasando Fecha como parametro
	 * 
	 * @param fechaEntrada tipo Fecha
	 */
	public void setFecha(Fecha fechaEntrada) {
		this.fecha = fechaEntrada;
	}

	/**
	 * acceso a vehiculo
	 * 
	 * @return vehiculo tipo Vehiculo
	 */
	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	/**
	 * modifica el valor de vehiculo pasando Vehiculo como parametro
	 * 
	 * @param vehiculo tipo Vehiculo
	 */
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	/**
	 * acceso a propietario
	 * 
	 * @return propietario tipo Cliente
	 */
	public Cliente getPropietario() {
		return propietario;
	}

	/**
	 * modifica el valor de propietario pasando Cliente como parametro
	 * 
	 * @param propietario tipo Cliente
	 */
	public void setPropietario(Cliente propietario) {
		this.propietario = propietario;
	}

	/**
	 * acceso a empleado
	 * 
	 * @return empleado tipo Cuenta
	 */
	public Cuenta getEmpleado() {
		return empleado;
	}

	/**
	 * modifica el valor de empleado pasando Cuenta como parametro
	 * 
	 * @param empleado tipo Cuenta
	 */
	public void setEmpleado(Cuenta empleado) {
		this.empleado = new Cuenta(empleado);
	}
}