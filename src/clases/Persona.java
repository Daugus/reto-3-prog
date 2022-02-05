package clases;

import java.io.Serializable;
import java.util.Objects;

public abstract class Persona implements Comparable<Persona>, Serializable
{
	private static final long serialVersionUID = 6717239572208402072L;

	// ===== propiedades =====
	private String dni;

	private String nombre;
	private String apellidos;

	private int tel;
	private String email;

	private Fecha fechaNacimiento;
	
	private Direccion direccion;

	
	// ===== constructores =====
	// --- por defecto ---
	public Persona()
	{
		dni = "";

		nombre = "";
		apellidos = "";
		
		tel = 600000000;
		email = "";
		 
		fechaNacimiento = new Fecha(); 
		 
		direccion = new Direccion();
	}
	
	// --- copia ---
	public Persona(Persona other)
	{
		this.dni = other.dni;

		this.nombre = other.nombre;
		this.apellidos = other.apellidos;
		
		this.tel = other.tel;
		this.email = other.email;
		
		this.fechaNacimiento = new Fecha(other.fechaNacimiento);
		
		this.direccion = new Direccion(other.direccion);
	}
	
	// --- personalizado ---
	public Persona(String d, String n, String a, int t, String e, Fecha fn, Direccion dir)
	{
		dni = d;

		nombre = n;
		apellidos = a;
		
		tel = t;
		email = e;
		
		fechaNacimiento = new Fecha(fn);
		
		direccion = new Direccion(dir);
	}

	// ===== métodos =====
	// --- salida ---
	@Override
	public String toString()
	{
		return "DNI: " + dni +
				", nombre: " + nombre + 
				", apellidos: " + apellidos +
				", teléfono: " + tel +
				", email: " + email +
				", fecha de nacimiento: " + fechaNacimiento +
				", dirección: " + direccion;
	}

	// --- comparación ---
	@Override
	public int hashCode()
	{
		return Objects.hash(apellidos, dni, email, fechaNacimiento, nombre, tel);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(apellidos, other.apellidos)
				&& Objects.equals(dni, other.dni) && Objects.equals(email, other.email)
				&& Objects.equals(fechaNacimiento, other.fechaNacimiento) && Objects.equals(nombre, other.nombre)
				&& tel == other.tel && Objects.equals(direccion, other.direccion);
	}

	@Override
	public int compareTo(Persona other)
	{
		return dni.compareTo(other.dni);
	}

	// --- getters y setters ---
	public String getDNI()
	{
		return dni;
	}

	public void setDNI(String d)
	{
		dni = d;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String n)
	{
		nombre = n;
	}

	public String getApellidos()
	{
		return apellidos;
	}

	public void setApellidos(String a)
	{
		apellidos = a;
	}

	public int getTelefono()
	{
		return tel;
	}

	public void setTelefono(int t)
	{
		tel = t;
	}

	public Fecha getFechaNacimiento()
	{
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Fecha fn)
	{
		fechaNacimiento = new Fecha(fn);
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String e)
	{
		email = e;
	}

	public Direccion getDireccion()
	{
		return direccion;
	}

	public void setDireccion(Direccion dir)
	{
		direccion = new Direccion(dir);
	}
}