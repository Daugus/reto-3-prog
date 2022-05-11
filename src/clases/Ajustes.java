package clases;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ajustes implements Serializable {
	private static final long serialVersionUID = -4533693024823055118L;

	// ===== propiedades =====
	@Id
	private String dniEmpleado;

	private boolean temaOscuro;
	private String fuente;
//	private String fuenteObjetos;
//	private Color colorFondo;
//	private Color colorFondoObjetos;
//	private Color colorFuente;
//	private Color colorFuenteObjetos;

	// ===== constructores =====
	/**
	 * constructor por defecto
	 */
	public Ajustes() {
		dniEmpleado = "";

		fuente = "Segoe UI";
//		fuenteObjetos = "Segoe UI";

		temaOscuro = true;
	}

	/**
	 * constructor copia
	 */
	public Ajustes(Ajustes other) {
		this.dniEmpleado = other.dniEmpleado;
		this.fuente = other.fuente;
//		this.fuenteObjetos = other.fuenteObjetos;

		this.temaOscuro = other.temaOscuro;
	}

	// --- personalizado ---
	/**
	 * constructor personalizado
	 * 
	 * @param fo  {@code true} si el tema es oscuro; {@code false} si es claro
	 * @param fu: nombre de la fuente
	 */
	public Ajustes(String dniEmpleado, boolean fo, String fu) {
		this.dniEmpleado = dniEmpleado;

		fuente = fu;
//		fuenteObjetos = fu;

		if (fo) {
			temaOscuro = true;
		} else {
			temaOscuro = false;
		}
	}

	// ===== métodos =====
	// --- salida ---
	/**
	 * devuelve una representación del objeto como String
	 * 
	 * @return los atributos del objeto
	 */
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
	/**
	 * devuelve el hash code del objeto basado en sus atributos
	 * 
	 * @return el hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(dniEmpleado, fuente, temaOscuro);
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
		Ajustes other = (Ajustes) obj;
		return Objects.equals(dniEmpleado, other.dniEmpleado) && Objects.equals(fuente, other.fuente)
				&& temaOscuro == other.temaOscuro;
	}

	// --- getters y setters ---
	public void setDniEmpleado(String dniEmpleado) {
		this.dniEmpleado = dniEmpleado;
	}

	public String getDniEmpleado() {
		return dniEmpleado;
	}

	/**
	 * devuelve el valor de tema
	 * 
	 * @return el valor de tema
	 */
	public boolean isTemaOscuro() {
		return temaOscuro;
	}
	
	public String getFamiliaFuente() {
		return fuente;
	}

	/**
	 * establece el valor de tema
	 * 
	 * @param valor booleano
	 */
	public void setTemaOscuro(boolean tema) {
		this.temaOscuro = tema;
	}

	/**
	 * devuelve el valor de fuente
	 * 
	 * @return el valor de fuente
	 */
	public Font getFuente() {
		return new Font(fuente, Font.PLAIN, 13);
	}

	/**
	 * establece el valor de fuente pasando tema como parametro
	 * 
	 * @param fuente
	 */
	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	/**
	 * acceso a fuenteObjetos
	 * 
	 * @return fuenteObjetos
	 */
	public Font getFuenteObjetos() {
		return new Font(fuente, Font.BOLD, 13);
	}

	/**
	 * acceso a colorFondo
	 * 
	 * @return colorFondo
	 */
	public Color getColorFondo() {
		if (temaOscuro)
			return Color.DARK_GRAY;

		return Color.LIGHT_GRAY;
	}

	/**
	 * acceso a colorFondoObjetos
	 * 
	 * @return colorFondoObjetos
	 */
	public Color getColorFondoObjetos() {
		if (temaOscuro)
			return Color.LIGHT_GRAY;

		return Color.WHITE;
	}

	/**
	 * acceso a colorFuente
	 * 
	 * @return colorFuente
	 */
	public Color getColorFuente() {
		if (temaOscuro)
			return Color.WHITE;

		return Color.BLACK;
	}

	/**
	 * acceso a colorFuenteObjetos
	 * 
	 * @return colorFuenteObjetos
	 */
	public Color getColorFuenteObjetos() {
		return Color.BLACK;
	}
}