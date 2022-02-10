package clases;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.util.Objects;

/**
 * esta clase difiene fuentes y colores mas temas que se usaran en el programa
 * @author Grupo 2
 * @version 2.0.1
 */
public class Ajustes implements Serializable
{
	private static final long serialVersionUID = -4533693024823055118L;
	
	// ===== propiedades =====
	private boolean tema;
	private Font fuente;
	private Font fuenteObjetos;
	private Color colorFondo;
	private Color colorFondoObjetos;
	private Color colorFuente;
	private Color colorFuenteObjetos;
	
	// ===== constructores =====
	/**
	 * constructor por defecto 
	 */
	public Ajustes()
	{
		fuente = new Font("Segoe UI", Font.PLAIN, 13);
		fuenteObjetos = new Font("Segoe UI", Font.BOLD, 13);

		tema = true;
		colorFondo = Color.DARK_GRAY;
		colorFondoObjetos = Color.LIGHT_GRAY;
		colorFuente = Color.WHITE;
		colorFuenteObjetos = Color.BLACK;
	}
	
	/**
	 * constructor copia
	 * @param other copia del constructor por defecto
	 */
	public Ajustes(Ajustes other)
	{
		this.fuente = other.fuente;
		this.fuenteObjetos = other.fuenteObjetos;

		this.tema = other.tema;
		this.colorFondo = other.colorFondo;
		this.colorFondoObjetos = other.colorFondoObjetos;
		this.colorFuente = other.colorFuente;
		this.colorFuenteObjetos = other.colorFuenteObjetos;
	}
	
	// --- personalizado ---
	/**
	 * constructor personalizado 
	 * @param fo cambia tema si le pasa por parametro true
	 * @param fu 
	 */
	public Ajustes(boolean fo, String fu)
	{
		fuente = new Font(fu, Font.PLAIN, 13);
		fuenteObjetos = new Font(fu, Font.BOLD, 13);

		if (fo)
		{
			tema = true;
			colorFondo = Color.DARK_GRAY;
			colorFondoObjetos = Color.LIGHT_GRAY;
			colorFuente = Color.WHITE;
			colorFuenteObjetos = Color.BLACK;
		}
		else
		{
			tema = false;
			colorFondo = Color.LIGHT_GRAY;
			colorFondoObjetos = Color.WHITE;
			colorFuente = Color.BLACK;
			colorFuenteObjetos = Color.BLACK;
		}
	}

	// ===== métodos =====
	// --- salida ---
	/**
	 * Devuelve una representación de cadena del objeto.
	 * @return Tema y fuente
	 */
	public String toString()
	{
		String nombreTema;
		if (tema)
		{
			nombreTema = "Oscuro";
		}
		else
		{
			nombreTema = "Claro";
		}

		return "Tema: " + nombreTema +
				", fuente " + fuente.getFamily();
	}

	// --- comparación ---
	/**
	 * para el objeto Este método es compatible
	 * en beneficio de las tablas hash 
	 * como las proporcionadas por java.util.HashMap.
	 * @return devuelve un valor hash
	 */
	@Override
	public int hashCode()
	{
		return Objects.hash(colorFondo, colorFondoObjetos, colorFuente, colorFuenteObjetos, fuente, fuenteObjetos,
				tema);
	}
	/**
	 * 
	 * Indica si algún otro objeto es "igual a" este.
	 * @return true si este objeto
	 *  es el mismo que el objargument; falso en caso contrario.
	 * @param obj objeto referente con el que desea comparar
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ajustes other = (Ajustes) obj;
		return Objects.equals(colorFondo, other.colorFondo)
				&& Objects.equals(colorFondoObjetos, other.colorFondoObjetos)
				&& Objects.equals(colorFuente, other.colorFuente)
				&& Objects.equals(colorFuenteObjetos, other.colorFuenteObjetos) && Objects.equals(fuente, other.fuente)
				&& Objects.equals(fuenteObjetos, other.fuenteObjetos) && tema == other.tema;
	}

	// --- getters y setters ---
	/**
	 * acceso a tema
	 * @return tema 
	 */
	public boolean temaOscuro()
	{
		return tema;
	}
	/**
	 * modifica el valor de tema pasando tema como parametro
	 * @param tema tipo boolean
	 */
	public void setTemaOscuro(boolean tema)
	{
		this.tema = tema;
	}
	/**
	 * acceso a fuente
	 * @return devuelve fuente
	 */
	public Font getFuente()
	{
		return fuente;
	}
	/**
	 * modifica el valor de fuente pasando tema como parametro
	 * @param fuente 
	 */
	public void setFuente(Font fuente)
	{
		this.fuente = fuente;
	}
	/**
	 * acceso a fuenteObjetos
	 * @return fuenteObjetos 
	 */
	public Font getFuenteObjetos()
	{
		return fuenteObjetos;
	}
	/**
	 * modifica el valor de fuenteObjetos pasando tema como parametro
	 * @param fuenteObjetos 
	 */
	public void setFuenteObjetos(Font fuenteObjetos)
	{
		this.fuenteObjetos = fuenteObjetos;
	}
	/**
	 * acceso a colorFondo
	 * @return colorFondo 
	 */
	public Color getColorFondo()
	{
		return colorFondo;
	}
	/**
	 * modifica el valor de colorFondo pasando tema como parametro
	 * @param colorFondo 
	 */
	public void setColorFondo(Color colorFondo)
	{
		this.colorFondo = colorFondo;
	}
	/**
	 * acceso a colorFondoObjetos
	 * @return colorFondoObjetos 
	 */
	public Color getColorFondoObjetos()
	{
		return colorFondoObjetos;
	}
	/**
	 * modifica el valor de colorFondoObjetos pasando tema como parametro
	 * @param colorFondoObjetos 
	 */
	public void setColorFondoObjetos(Color colorFondoObjetos)
	{
		this.colorFondoObjetos = colorFondoObjetos;
	}
	/**
	 * acceso a colorFuente
	 * @return colorFuente 
	 */
	public Color getColorFuente()
	{
		return colorFuente;
	}
	/**
	 * modifica el valor de colorFuente pasando tema como parametro
	 * @param colorFuente 
	 */
	public void setColorFuente(Color colorFuente)
	{
		this.colorFuente = colorFuente;
	}
	/**
	 * acceso a colorFuenteObjetos
	 * @return colorFuenteObjetos 
	 */
	public Color getColorFuenteObjetos()
	{
		return colorFuenteObjetos;
	}
	/**
	 * modifica el valor de tema pasando colorFuenteObjetos como parametro
	 * @param colorFuenteObjetos 
	 */
	public void setColorFuenteObjetos(Color colorFuenteObjetos)
	{
		this.colorFuenteObjetos = colorFuenteObjetos;
	}
}