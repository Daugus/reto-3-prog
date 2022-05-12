package clases;

import java.io.Serializable;
import java.util.Objects;

/**
 * clase de Orden
 */
public class Orden implements Comparable<Orden>, Serializable {
	private static final long serialVersionUID = 6717239572208402072L;

	// ===== propiedades =====
	private String codigo;
	private String comentarios;

	private String matricula;
	private String empleado;

	private int horas;

	private Fecha fechaInicio;
	private Fecha fechaFin;

	// ===== constructores =====
	/**
	 * constructor por defecto
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
	 * 
	 * @param other Objeto que se va a copiar
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

	// --- personalizados ---
	/**
	 * constructor personalizado para mostrar factura
	 * 
	 * @param codigo    código de la orden
	 * @param matricula matrícula del vehículo de la orden
	 */
	public Orden(String codigo, String matricula) {
		this.codigo = codigo;

		this.matricula = matricula;
	}

	/**
	 * constructor personalizado para finalizar la orden
	 * 
	 * @param codigo   código de la orden
	 * @param horas    horas de trabajo que han llevado las reparaciones de la orden
	 * @param fechaFin fecha de fin de la orden
	 */
	public Orden(String codigo, int horas, Fecha fechaFin) {
		this.codigo = codigo;

		this.fechaFin = new Fecha(fechaFin);

		this.horas = horas;
	}

	/**
	 * constructor personalizado para finalizar la orden
	 * 
	 * @param codigo      código de la orden
	 * @param comentario  comentario de la orden
	 * @param matricula   matrícula del vehículo de la orden
	 * @param dniEmpleado DNI del empleado que se encarga de la orden
	 * @param fechaInicio fecha de inicio de la orden
	 */
	public Orden(String codigo, String comentario, String matricula, String dniEmpleado, Fecha fechaInicio) {
		this.codigo = codigo;
		this.comentarios = comentario;

		this.fechaInicio = new Fecha(fechaInicio);
		this.fechaFin = null;

		this.matricula = matricula;
		this.empleado = dniEmpleado;
	}

	/**
	 * constructor personalizado completo
	 * 
	 * @param codigo      código de la orden
	 * @param comentario  comentario de la orden
	 * @param matricula   matrícula del vehículo de la orden
	 * @param dniEmpleado DNI del empleado que se encarga de la orden
	 * @param horas       horas de trabajo que han llevado las reparaciones de la
	 *                    orden
	 * @param fechaInicio fecha de inicio de la orden
	 * @param fechaFin    fecha de fin de la orden
	 */
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
	@Override
	public String toString() {
		return "Código: " + codigo + ", comentarios: " + comentarios + ", Fecha Entrada: " + fechaInicio
				+ ", Fecha Salida: " + fechaFin + ", matrícula: " + matricula + ", empleado: " + empleado + ", horas: "
				+ horas;
	}

	// --- comparación ---
	@Override
	public int hashCode() {
		return Objects.hash(codigo, comentarios, fechaInicio, fechaFin, horas, matricula, empleado);
	}

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

	@Override
	public int compareTo(Orden other) {
		return codigo.compareTo(other.codigo);
	}

	// --- getters ---
	public String getCodigo() {
		return codigo;
	}

	public String getComentarios() {
		return comentarios;
	}

	public Fecha getFechaInicio() {
		return fechaInicio;
	}

	public Fecha getFechaFin() {
		return fechaFin;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getEmpleado() {
		return empleado;
	}

	public int getTiempoHoras() {
		return horas;
	}
}