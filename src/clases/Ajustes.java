package clases;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * clase de Ajustes de usuario para los empleados
 */
@Entity
public class Ajustes implements Serializable {
	private static final long serialVersionUID = -4533693024823055118L;

	// ===== propiedades =====
	@Id
	private String dniEmpleado;

	private boolean temaOscuro;
	private String fuente;

	// ===== constructores =====
	/**
	 * constructor por defecto
	 */
	public Ajustes() {
		dniEmpleado = "";

		fuente = "Segoe UI";

		temaOscuro = true;
	}

	/**
	 * constructor copia
	 * 
	 * @param other Objeto que se va a copiar
	 */
	public Ajustes(Ajustes other) {
		this.dniEmpleado = other.dniEmpleado;
		this.fuente = other.fuente;

		this.temaOscuro = other.temaOscuro;
	}

	// --- personalizado ---
	/**
	 * constructor personalizado
	 * 
	 * @param dniEmpleado DNI del empleado
	 * @param temaOscuro  {@code true} si es oscuro, {@code false} si es claro
	 * @param fuente      nombre de la familia tipográfica de la fuente
	 */
	public Ajustes(String dniEmpleado, boolean temaOscuro, String fuente) {
		this.dniEmpleado = dniEmpleado;

		this.fuente = fuente;

		if (temaOscuro) {
			this.temaOscuro = true;
		} else {
			this.temaOscuro = false;
		}
	}

	// ===== métodos =====
	// --- salida ---
	public String toString() {
		String nombreTema;
		if (temaOscuro) {
			nombreTema = "Oscuro";
		} else {
			nombreTema = "Claro";
		}

		return "DNI Empleado: " + dniEmpleado + ", tema: " + nombreTema + ", fuente " + fuente;
	}

	// --- comparación ---
	@Override
	public int hashCode() {
		return Objects.hash(dniEmpleado, fuente, temaOscuro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ajustes other = (Ajustes) obj;
		return Objects.equals(dniEmpleado, other.dniEmpleado) && Objects.equals(fuente, other.fuente)
				&& temaOscuro == other.temaOscuro;
	}

	// --- getters y setters ---
	public String getDniEmpleado() {
		return dniEmpleado;
	}

	public void setTemaOscuro(boolean tema) {
		this.temaOscuro = tema;
	}

	public boolean isTemaOscuro() {
		return temaOscuro;
	}

	public void setFamiliaFuente(String fuente) {
		this.fuente = fuente;
	}

	public String getFamiliaFuente() {
		return fuente;
	}

	/**
	 * @return Font para las etiquetas basada en la propiedad fuente
	 */
	public Font getFuente() {
		return new Font(fuente, Font.PLAIN, 13);
	}

	/**
	 * @return Font para los botones, campos de texto y otros elementos basada en la
	 *         propiedad fuente
	 */
	public Font getFuenteObjetos() {
		return new Font(fuente, Font.BOLD, 13);
	}

	/**
	 * @return Color de fondo para la ventana basado en el tema
	 */
	public Color getColorFondo() {
		if (temaOscuro)
			return Color.DARK_GRAY;

		return Color.LIGHT_GRAY;
	}

	/**
	 * @return Color de fondo para los botones, campos de texto y otros elementos
	 *         basado en el tema
	 */
	public Color getColorFondoObjetos() {
		if (temaOscuro)
			return Color.LIGHT_GRAY;

		return Color.WHITE;
	}

	/**
	 * @return Color de la fuente para las etiquetas basado en el tema
	 */
	public Color getColorFuente() {
		if (temaOscuro)
			return Color.WHITE;

		return Color.BLACK;
	}

	/**
	 * @return Color de la fuente para los botones, campos de texto y otros
	 *         elementos basado en el tema
	 */
	public Color getColorFuenteObjetos() {
		return Color.BLACK;
	}
}