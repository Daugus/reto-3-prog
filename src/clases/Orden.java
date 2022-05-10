package clases;

import java.io.Serializable;
import java.util.Objects;

public class Orden implements Comparable<Orden>, Serializable {
	private static final long serialVersionUID = 6717239572208402072L;

	// ===== propiedades =====
	private String codigo;
	private String comentarios;

	private Fecha fechaInicio;
	private Fecha fechaFin;

	private int horas;

	private String matricula;
	private String empleado;

	// ===== constructores =====
	/**
	 * orden primaria por defecto
	 */
	public Orden() {
		codigo = "";
		comentarios = "";

		fechaInicio = new Fecha();
		fechaFin = new Fecha();

		horas = 1;

		matricula = "";
		empleado = "";
	}

	/**
	 * constructor copia
	 */
	public Orden(Orden other) {
		this.codigo = other.codigo;
		this.comentarios = other.comentarios;

		this.fechaInicio = new Fecha(other.fechaInicio);
		if (other.fechaFin != null)
			this.fechaFin = new Fecha(other.fechaFin);

		if (Integer.valueOf(other.horas) != null)
			this.horas = other.horas;

		this.matricula = other.matricula;
		this.empleado = other.empleado;
	}

	/**
	 * orden primaria personalizado
	 */
	public Orden(String codigo, String matricula) {
		this.codigo = codigo;
		
		this.matricula = matricula;
	}
	
	public Orden(String codigo, int horas, Fecha fechaFin) {
		this.codigo = codigo;
		
		this.fechaFin = new Fecha(fechaFin);
		
		this.horas = horas;
	}
	
	public Orden(String codigo, String comentario, String matricula, String dniEmpleado, Fecha fechaInicio) {
		this.codigo = codigo;
		this.comentarios = comentario;

		this.fechaInicio = new Fecha(fechaInicio);
		this.fechaFin = null;

		this.matricula = matricula;
		this.empleado = dniEmpleado;
	}

	public Orden(String codigo, String comentario, String matricula, String dniEmpleado, int horas, Fecha fechaInicio,
			Fecha fechaFin) {
		this.codigo = codigo;
		this.comentarios = comentario;

		this.fechaInicio = new Fecha(fechaInicio);
		this.fechaFin = new Fecha(fechaFin);

		this.horas = horas;

		this.matricula = matricula;
		this.empleado = dniEmpleado;
	}

	// --- salida ---
	/**
	 * Devuelve una representación de cadena del objeto.
	 * 
	 * @return los atributos del objeto
	 */
	@Override
	public String toString() {
		return "Código: " + codigo + ", comentarios: " + comentarios + ", Fecha Entrada: " + fechaInicio
				+ ", Fecha Salida: " + fechaFin + ", matrícula: " + matricula + ", empleado: " + empleado + ", horas: "
				+ horas;
	}

	// --- comparación ---
	/**
	 * devuelve el hash code del objeto basado en sus atributos
	 * 
	 * @return devuelve un valor hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(codigo, comentarios, fechaInicio, fechaFin, horas, matricula, empleado);
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
		Orden other = (Orden) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(comentarios, other.comentarios)
				&& Objects.equals(fechaInicio, other.fechaInicio) && Objects.equals(fechaFin, other.fechaFin)
				&& Objects.equals(horas, other.horas) && Objects.equals(matricula, other.matricula)
				&& Objects.equals(empleado, other.empleado);
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
	public int compareTo(Orden other) {
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
	public Fecha getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * modifica el valor de fecha pasando Fecha como parametro
	 * 
	 * @param fechaEntrada tipo Fecha
	 */
	public void setFechaInicio(Fecha fechaEntrada) {
		this.fechaInicio = fechaEntrada;
	}

	/**
	 * acceso a fecha
	 * 
	 * @return fecha
	 */
	public Fecha getFechaFin() {
		return fechaFin;
	}

	/**
	 * modifica el valor de fecha pasando Fecha como parametro
	 * 
	 * @param fechaEntrada tipo Fecha
	 */
	public void setFechaFin(Fecha fechaSalida) {
		this.fechaFin = fechaSalida;
	}

	/**
	 * acceso a vehiculo
	 * 
	 * @return vehiculo tipo String
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * modifica el valor de vehiculo pasando String como parametro
	 * 
	 * @param vehiculo tipo String
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * acceso a empleado
	 * 
	 * @return empleado tipo String
	 */
	public String getEmpleado() {
		return empleado;
	}

	/**
	 * modifica el valor de empleado pasando String como parametro
	 * 
	 * @param empleado tipo String
	 */
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	/**
	 * acceso a las horas empleadas
	 * 
	 * @return horas tipo int
	 */
	public int getTiempoHoras() {
		return horas;
	}

	/**
	 * modifica el valor de las horas pasando int como parametro
	 * 
	 * @param tiempoHoras tipo int
	 */
	public void setTiempoHoras(int tiempoHoras) {
		this.horas = tiempoHoras;
	}
}