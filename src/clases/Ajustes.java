package clases;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.util.Objects;

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
	// --- por defecto ---
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
	
	// --- copia ---
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
	public Ajustes(boolean fo, String fu)
	{
		if (fu.equals("Segoe UI"))
		{
			fuente = new Font("Segoe UI", Font.PLAIN, 15);
			fuenteObjetos = new Font("Segoe UI", Font.BOLD, 15);
		}
		else
		{
			fuente = new Font("Tahoma", Font.PLAIN, 15);
			fuenteObjetos = new Font("Tahoma", Font.BOLD, 15);
		}

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

		return (nombreTema + " " + fuente.getFamily());
	}

	// --- comparación ---
	@Override
	public int hashCode() {
		return Objects.hash(colorFondo, colorFondoObjetos, colorFuente, colorFuenteObjetos, fuente, fuenteObjetos,
				tema);
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
		return Objects.equals(colorFondo, other.colorFondo)
				&& Objects.equals(colorFondoObjetos, other.colorFondoObjetos)
				&& Objects.equals(colorFuente, other.colorFuente)
				&& Objects.equals(colorFuenteObjetos, other.colorFuenteObjetos) && Objects.equals(fuente, other.fuente)
				&& Objects.equals(fuenteObjetos, other.fuenteObjetos) && tema == other.tema;
	}

	// --- getters y setters ---
	public boolean temaOscuro() {
		return tema;
	}

	public void setTemaOscuro(boolean tema) {
		this.tema = tema;
	}

	public Font getFuente() {
		return fuente;
	}

	public void setFuente(Font fuente) {
		this.fuente = fuente;
	}

	public Font getFuenteObjetos() {
		return fuenteObjetos;
	}

	public void setFuenteObjetos(Font fuenteObjetos) {
		this.fuenteObjetos = fuenteObjetos;
	}

	public Color getColorFondo() {
		return colorFondo;
	}

	public void setColorFondo(Color colorFondo) {
		this.colorFondo = colorFondo;
	}

	public Color getColorFondoObjetos() {
		return colorFondoObjetos;
	}

	public void setColorFondoObjetos(Color colorFondoObjetos) {
		this.colorFondoObjetos = colorFondoObjetos;
	}

	public Color getColorFuente() {
		return colorFuente;
	}

	public void setColorFuente(Color colorFuente) {
		this.colorFuente = colorFuente;
	}

	public Color getColorFuenteObjetos() {
		return colorFuenteObjetos;
	}

	public void setColorFuenteObjetos(Color colorFuenteObjetos) {
		this.colorFuenteObjetos = colorFuenteObjetos;
	}
}