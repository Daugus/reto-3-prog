package clases;

import java.io.Serializable;
import java.util.Objects;
import java.awt.Color;

public class Ajustes implements Serializable
{
	private static final long serialVersionUID = -4533693024823055118L;
	
	// ===== propiedades =====
	private Color fondo;
	private String fuente;
	
	// ===== constructores =====
	// --- por defecto ---
	public Ajustes()
	{
		fondo = Color.DARK_GRAY;
		fuente = "Tahoma";
	}
	
	// --- copia ---
	public Ajustes(Ajustes other)
	{
		this.fondo = other.fondo;
		this.fuente = other.fuente;
	}
	
	// --- personalizado ---
	public Ajustes(Color fo, String fu)
	{
		fondo = fo;
		fuente = fu;
	}

	// ===== métodos =====
	// --- salida ---
	public String toString()
	{
		return (fondo + " " + fuente);
	}

	// --- comparación ---
	@Override
	public int hashCode() {
		return Objects.hash(fondo, fuente);
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
		return Objects.equals(fondo, other.fondo) && Objects.equals(fuente, other.fuente);
	}

	// --- getters y setters ---
	public Color getFondo() {
		return fondo;
	}

	public void setFondo(Color fondo) {
		this.fondo = fondo;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}
}