package clases;

import java.util.Objects;

public class MaterialUsado extends Material
{
	private static final long serialVersionUID = -4881100923692845852L;

	// ===== propiedades =====
	private int cantidad;
	
	// ===== constructores =====
	/**
	 * materialUsado por defecto
	 */
	public MaterialUsado()
	{
		super();
		
		cantidad = 1;
	}

	/**
	 * 
	 * @param other copia
	 */
	public MaterialUsado(MaterialUsado other)
	{
		super(other);
		
		this.cantidad = other.cantidad;
	}

	// --- personalizados ---
	public MaterialUsado(Material m, int c)
	{
		super(m);
		
		cantidad = c;
	}
	
	public MaterialUsado(String n, double p, int c)
	{
		super(n, p);
		
		cantidad = c;
	}
	
	// ===== métodos =====
	// --- salida ---
	@Override
	public String toString()
	{
		return super.toString() +
				", cantidad: " + cantidad;
	}

	// --- comparación ---
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cantidad);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaterialUsado other = (MaterialUsado) obj;
		return cantidad == other.cantidad;
	}

	// --- getters y setters ---
	public int getCantidad()
	{
		return cantidad;
	}
	
	public void setCantidad(int c)
	{
		cantidad = c;
	}
}
