package clases;

import java.util.Objects;

public class Cuenta extends Persona
{
	private static final long serialVersionUID = -1206167340211110010L;
	
	// ===== propiedades =====
	private boolean mecanico;
	private String password;
	private Ajustes ajustes;
	
	// ===== constructores =====
	// --- por defecto ---
	public Cuenta()
	{
		super();

		mecanico = true;
		password = "";
		ajustes = new Ajustes();
	}
	
	public Cuenta(boolean cod)
	{
		super();
		
		mecanico = cod;
	}
	
	// --- copia ---
	public Cuenta(Cuenta other)
	{
		super(other);

		mecanico = other.mecanico;
		password = other.password;
		ajustes = new Ajustes(other.ajustes);
	}
	
	// --- personalizados ---
	// Persona por defecto con código, contraseña y ajustes personalizados
	public Cuenta(boolean cod, String p, Ajustes a)
	{
		super();

		mecanico = cod;
		password = p;
		ajustes = new Ajustes(a);
	}

	// Persona nueva con código, contraseña y ajustes personalizados
	public Cuenta(String d, String n, String a, int t, String e, Fecha fn, Direccion dir, boolean cod, String pa, Ajustes aj)
	{
		super(d, n, a, t, e, fn, dir);

		mecanico = cod;
		password = pa;
		ajustes = new Ajustes(aj);
	}
	
	// ===== métodos =====
	// --- salida ---
	@Override
	public String toString()
	{
		String tipo;
		if (mecanico)
		{
			tipo = "Mecánico";
		}
		else
		{
			tipo = "Atención al cliente";
		}

		return super.toString() +
				", tipo: " + tipo;
	}

	// --- comparación ---
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(ajustes, mecanico, password);
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
		Cuenta other = (Cuenta) obj;
		return Objects.equals(ajustes, other.ajustes) && mecanico == other.mecanico
				&& Objects.equals(password, other.password);
	}

	// --- getters y setters ---
	public String tipo()
	{
		String tipo;
		if (mecanico)
		{
			tipo = "Mecánico";
		}
		else
		{
			tipo = "Atención al cliente";
		}

		return tipo;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public boolean esMecanico()
	{
		return mecanico;
	}

	public void setMecanico(boolean mecanico)
	{
		this.mecanico = mecanico;
	}
	
	public Ajustes getAjustes()
	{
		return ajustes;
	}
	
	public void setAjustes(Ajustes ajustes)
	{
		this.ajustes = new Ajustes(ajustes);
	}
}
