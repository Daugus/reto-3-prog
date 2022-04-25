package clases;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class Reparacion implements Comparable<Reparacion>, Serializable {
	private static final long serialVersionUID = -4533693024823055118L;

	// ===== propiedades =====
	private String codigo;
	private String descripcion;
	private int horas;
	private double manoObra;
	private Fecha fecha;
	private Cuenta mecanico;
	private ArrayList<MaterialUsado> materialesUsados;

	// ===== constructores =====
	/**
	 * reparacion por defecto
	 */
	public Reparacion() {
		codigo = "";
		descripcion = "";
		horas = 1;
		manoObra = 25;
		fecha = new Fecha();
		mecanico = new Cuenta(true);
		materialesUsados = new ArrayList<MaterialUsado>();
	}

	/**
	 * constructor copia
	 */
	public Reparacion(Reparacion other) {
		this.codigo = other.codigo;
		this.descripcion = other.descripcion;
		this.horas = other.horas;
		this.manoObra = other.manoObra;
		fecha = new Fecha(other.fecha);
		this.mecanico = new Cuenta(other.mecanico);
		this.materialesUsados = new ArrayList<MaterialUsado>(other.materialesUsados);
	}

	/**
	 * reparacion personalizado requiere los seguientes parametros
	 * 
	 * @param desc String descripcion
	 * @param h    int horas
	 * @param mo   double mano de obra
	 * @param fe   objeto fecha
	 * @param mec  objeto mecanico
	 * @param al   arraylist para agrupar datos
	 */
	public Reparacion(String desc, int h, double mo, Fecha fe, Cuenta mec, ArrayList<MaterialUsado> al) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String cod = formatter.format(calendar.getTime());

		codigo = cod;
		descripcion = desc;
		horas = h;
		manoObra = mo;
		fecha = new Fecha(fe);
		mecanico = new Cuenta(mec);
		materialesUsados = new ArrayList<MaterialUsado>(al);
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
		return "Código: " + codigo + ", descripción: " + descripcion + ", horas: " + horas + ", precio mano de obra: "
				+ manoObra + ", mecánico: " + mecanico.getDNI() + ", materiales: " + materialesUsados;
	}

	// --- comparación ---
	/**
	 * devuelve el hash code del objeto basado en sus atributos
	 * 
	 * @return devuelve un valor hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(codigo, descripcion, horas, manoObra, fecha, materialesUsados, mecanico);
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
		Reparacion other = (Reparacion) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(fecha, other.fecha) && Objects.equals(materialesUsados, other.materialesUsados)
				&& Objects.equals(mecanico, other.mecanico) && Objects.equals(horas, other.horas)
				&& Objects.equals(manoObra, other.manoObra);
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
	public int compareTo(Reparacion other) {
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
	 * @param codReparacion tipo String
	 */
	public void setCodigo(String codReparacion) {
		this.codigo = codReparacion;
	}

	/**
	 * acceso a descripcion
	 * 
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * modifica el valor de descripcion pasando String como parametro
	 * 
	 * @param descripcion tipo String
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * acceso a horas
	 * 
	 * @return horas
	 */
	public int getHoras() {
		return horas;
	}

	/**
	 * modifica el valor de horas pasando int como parametro
	 * 
	 * @param horas tipo int
	 */
	public void setHoras(int horas) {
		this.horas = horas;
	}

	/**
	 * acceso a manoObra
	 * 
	 * @return manoObra
	 */
	public double getManoObra() {
		return manoObra;
	}

	/**
	 * modifica el valor de manoObra pasando int como parametro
	 * 
	 * @param manoObra tipo int
	 */
	public void setManoObra(int manoObra) {
		this.manoObra = manoObra;
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
	 * @param fecha tipo Fecha
	 */
	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	/**
	 * acceso a mecanico
	 * 
	 * @return mecanico
	 */
	public Cuenta getMecanico() {
		return mecanico;
	}

	/**
	 * modifica el valor de mecanico pasando cuenta mecanico como parametro
	 * 
	 * @param mecanico tipo Cuenta
	 */
	public void setMecanico(Cuenta mecanico) {
		this.mecanico = mecanico;
	}

	/**
	 * acceso a materialesUsados
	 * 
	 * @return materialesUsados
	 */
	public ArrayList<MaterialUsado> getMaterialesUsados() {
		return materialesUsados;
	}

	/**
	 * modifica el valor de materialesUsados pasando ArrayList de MaterialUsado como
	 * parametro
	 * 
	 * @param materialesUsados tipo ArrayList de MaterialUsado
	 */
	public void setMaterialesUsados(ArrayList<MaterialUsado> materialesUsados) {
		this.materialesUsados = materialesUsados;
	}
}