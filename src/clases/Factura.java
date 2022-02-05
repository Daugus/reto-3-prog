package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Factura extends Pendiente implements Serializable
{
	private static final long serialVersionUID = 6717239572208402072L;
	
	// ===== propiedades =====
	private double costeReparaciones = 0;
	private double costeMateriales = 0;
	private double subtotal;
	private double iva;
	private double total;
	
	// ===== constructores =====
	// --- por defecto ---
	public Factura()
	{
		super();
		
		costeReparaciones = 0;
		costeMateriales = 0;
		subtotal = 0;
		iva = 0;
		total = 0;
	}
	
	// --- copia ---
	public Factura(Factura other)
	{
		super(other);
		
		this.costeReparaciones = other.costeReparaciones;
		this.costeMateriales = other.costeMateriales;
		this.subtotal = other.subtotal;
		this.iva = other.iva;
		this.total = other.total;
	}
	
	// --- personalizados ---
	public Factura(String com, Cliente c, Vehiculo v, Cuenta atc, ArrayList<Reparacion> r)
	{
		super(com, c, v, atc, r);
		
		calcularTotal();
	}
	
	public Factura(Pendiente pendiente)
	{
		super(pendiente);
		
		generarCodigo();
		calcularTotal();
	}

	// ===== métodos =====
	// --- personalizado ---
	private void calcularTotal()
	{
		for (Reparacion r : getReparaciones())
		{
			for (MaterialUsado mu : r.getMaterialesUsados())
			{
				costeMateriales += mu.getCantidad() * mu.getPrecio();
			}
			
			costeReparaciones += r.getHoras() * r.getManoObra();
		}
		
		subtotal = costeReparaciones + costeMateriales;
		iva = subtotal * 0.21;
		total = subtotal + iva;
	}

	// --- salida ---
	@Override
	public String toString()
	{
		return super.toString() +
				", total reparaciones: " + costeReparaciones +
				", total materiales: " + costeMateriales +
				", subtotal: " + subtotal + 
				", coste iva: " + iva +
				", total: " + total;
	}

	// --- comparación ---
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(costeMateriales, costeReparaciones, iva, subtotal, total);
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
		Factura other = (Factura) obj;
		return Double.doubleToLongBits(costeMateriales) == Double.doubleToLongBits(other.costeMateriales)
				&& Double.doubleToLongBits(costeReparaciones) == Double.doubleToLongBits(other.costeReparaciones)
				&& Double.doubleToLongBits(iva) == Double.doubleToLongBits(other.iva)
				&& Double.doubleToLongBits(subtotal) == Double.doubleToLongBits(other.subtotal)
				&& Double.doubleToLongBits(total) == Double.doubleToLongBits(other.total);
	}

	// --- getters y setters ---
	public double getCosteReparaciones()
	{
		return costeReparaciones;
	}

	public void setCosteReparaciones(double costeReparaciones)
	{
		this.costeReparaciones = costeReparaciones;
	}

	public double getCosteMateriales()
	{
		return costeMateriales;
	}

	public void setCosteMateriales(double costeMateriales)
	{
		this.costeMateriales = costeMateriales;
	}

	public double getSubtotal()
	{
		return subtotal;
	}

	public void setSubtotal(double subtotal)
	{
		this.subtotal = subtotal;
	}

	public double getIva()
	{
		return iva;
	}

	public void setIva(double iva)
	{
		this.iva = iva;
	}

	public double getTotal()
	{
		return total;
	}

	public void setTotal(double total)
	{
		this.total = total;
	}
}