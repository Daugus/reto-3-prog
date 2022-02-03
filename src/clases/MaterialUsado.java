package clases;

public class MaterialUsado extends Material
{
	private static final long serialVersionUID = -4881100923692845852L;

	// ===== propiedades =====
	private int cantidad;
	
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
	
	public int getCantidad()
	{
		return cantidad;
	}
	
	public void setCantidad(int c)
	{
		cantidad = c;
	}
}
