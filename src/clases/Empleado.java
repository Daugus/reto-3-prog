package clases;

import java.util.Objects;

public class Empleado extends Persona {
	private static final long serialVersionUID = -1206167340211110010L;

	// ===== propiedades =====
	private Ajustes ajustes;

	private String dniJefe;

	private double salario;
	private double comision;
	
	private Fecha fechaNacimiento;
	private Fecha fechaAlta;
	
	private String tipo;
	private String password;
	
	private boolean activo;

	// ===== constructores =====
	/**
	 * constructor por defecto
	 */
	public Empleado() {
		super();

		ajustes = new Ajustes();

		dniJefe = "";
		password = "";
		salario = 1000.0;
		comision = 0.0;
		fechaNacimiento = new Fecha();
		tipo = "Mecanico";
		fechaAlta = new Fecha();
		activo = true;
	}

	/**
	 * constructor copia
	 */
	public Empleado(Empleado other) {
		super(other);

		ajustes = new Ajustes(other.ajustes);
		dniJefe = "";
		password = other.password;
		salario = other.salario;
		comision = other.comision;
		fechaNacimiento = new Fecha(other.fechaNacimiento);
		tipo = other.tipo;
		fechaAlta = new Fecha(other.fechaAlta);
		activo = other.activo;
	}

	// --- personalizados ---
	public Empleado(String dni, String nombre, String apellidos, String telefono, String email, String dir,
			Ajustes ajustes, String jefe, String password, double sal, double com, Fecha fn, String tipo, Fecha fa,
			boolean act) {

		super(dni, nombre, apellidos, telefono, email, dir);

		this.ajustes = new Ajustes(ajustes);
		this.dniJefe = jefe;
		this.password = password;
		this.salario = sal;
		this.comision = com;
		this.fechaNacimiento = new Fecha(fn);
		this.tipo = tipo;
		this.fechaAlta = new Fecha(fa);
		this.activo = act;
	}

	// ===== métodos =====
	/**
	 * Devuelve una representación de cadena del objeto.
	 * 
	 * @return tipo de usuario
	 */
	@Override
	public String toString() {
		return super.toString() + ", dniJefe: " + dniJefe + ", salario: " + salario + ", comision: " + comision
				+ ", fecha de nacimiento: " + fechaNacimiento + ", tipo: " + tipo + ", fecha de alta: " + fechaAlta
				+ ", activo: " + activo;
	}

	// --- comparación ---
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(activo, ajustes, comision, dniJefe, fechaAlta, fechaNacimiento, password, salario, tipo);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return activo == other.activo && Objects.equals(ajustes, other.ajustes)
				&& Double.doubleToLongBits(comision) == Double.doubleToLongBits(other.comision)
				&& Objects.equals(dniJefe, other.dniJefe) && Objects.equals(fechaAlta, other.fechaAlta)
				&& Objects.equals(fechaNacimiento, other.fechaNacimiento) && Objects.equals(password, other.password)
				&& Double.doubleToLongBits(salario) == Double.doubleToLongBits(other.salario)
				&& Objects.equals(tipo, other.tipo);
	}

	// --- getters y setters ---
	public Ajustes getAjustes() {
		return ajustes;
	}

	public void setAjustes(Ajustes ajustes) {
		this.ajustes = ajustes;
	}

	public String getDniJefe() {
		return dniJefe;
	}

	public double getSalario() {
		return salario;
	}

	public double getComision() {
		return comision;
	}

	public Fecha getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getTipo() {
		return tipo;
	}

	public Fecha getFechaAlta() {
		return fechaAlta;
	}

	public String getPassword() {
		return password;
	}

	public boolean isActivo() {
		return activo;
	}
}
