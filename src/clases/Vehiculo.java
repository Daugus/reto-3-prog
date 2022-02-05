package clases;

import java.io.Serializable;
import java.util.Objects;

public class Vehiculo implements Comparable<Vehiculo>, Serializable
{
	private static final long serialVersionUID = 2469183365830659245L;
	
	// ===== propiedades =====
	private String matricula;
	private String bastidor;
	private String propietario;

	private String marca;
	private String modelo;
	private String color;

	private int cilindrada;

	private int kmRecorridos;
	private Fecha fechaITV;

	private String tipo;
	
	// ===== constructores =====
	// --- por defecto ---
	public Vehiculo()
	{
		matricula = "";
		bastidor = "";
		propietario = "";

		marca = "";
		modelo = "";
		color = "";

		cilindrada = 0;

		kmRecorridos = 0;
		fechaITV = new Fecha();

		tipo = "";
	}
	
	// --- copia ---
	public Vehiculo(Vehiculo other)
	{
		this.matricula = other.matricula;
		this.bastidor = other.bastidor;
		this.propietario = other.propietario;

		this.marca = other.marca;
		this.modelo = other.modelo;
		this.color = other.color;

		this.cilindrada = other.cilindrada;

		this.kmRecorridos = other.kmRecorridos;
		this.fechaITV = other.fechaITV;

		this.tipo = other.tipo;
	}
	
	// --- personalizados ---
	// vehículo en blanco, usado para agregar los vehículos de un cliente nuevo
	public Vehiculo(String m, String p)
	{
		matricula = m;
		bastidor = "";
		propietario = p;

		marca = "";
		modelo = "";
		color = "";

		cilindrada = 0;

		kmRecorridos = 0;
		fechaITV = new Fecha();

		tipo = "";
	}
	
	public Vehiculo(String m, String b, String dni, String mar, String mod, String c, int cc, int km, Fecha i, String t)
	{
		matricula = m;
		bastidor = b;
		propietario = dni;

		marca = mar;
		modelo = mod;
		color = c;

		cilindrada = cc;

		kmRecorridos = km;
		fechaITV = i;

		tipo = t;
	}
	
	// ===== métodos =====
	// --- salida ---
	@Override
	public String toString()
	{
		return "Matrícula:  " + matricula +
				", Propietario: " + propietario +
				", Modelo: " + marca + " " + modelo;
	}

	// --- comparación ---
	@Override
	public int hashCode()
	{
		return Objects.hash(cilindrada, propietario, color, fechaITV, kmRecorridos, marca, matricula, modelo, bastidor, tipo);
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
		Vehiculo other = (Vehiculo) obj;
		return Objects.equals(cilindrada, other.cilindrada) && Objects.equals(color, other.color)
				&& Objects.equals(fechaITV, other.fechaITV) && kmRecorridos == other.kmRecorridos
				&& Objects.equals(marca, other.marca) && Objects.equals(matricula, other.matricula)
				&& Objects.equals(modelo, other.modelo) && bastidor == other.bastidor
				&& Objects.equals(tipo, other.tipo) && Objects.equals(propietario, other.propietario);
	}
	

	@Override
	public int compareTo(Vehiculo other)
	{
		return matricula.compareTo(other.matricula);
	}

	// --- getters y setters ---
	public String getMatricula()
	{
		return matricula;
	}

	public void setMatricula(String m)
	{
		matricula = m;
	}

	public String getBastidor()
	{
		return bastidor;
	}

	public void setBastidor(String b)
	{
		bastidor = b;
	}

	public String getModelo()
	{
		return modelo;
	}

	public void setModelo(String m)
	{
		modelo = m;
	}

	public String getMarca()
	{
		return marca;
	}

	public void setMarca(String m)
	{
		marca = m;
	}

	public int getKmRecorridos()
	{
		return kmRecorridos;
	}

	public void setKmRecorridos(int km)
	{
		kmRecorridos = km;
	}

	public int getCilindrada()
	{
		return cilindrada;
	}

	public void setCilindrada(int cc)
	{
		cilindrada = cc;
	}

	public Fecha getFechaITV()
	{
		return fechaITV;
	}

	public void setFechaITV(Fecha itv)
	{
		fechaITV = itv;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String c)
	{
		color = c;
	}

	public String getTipo()
	{
		return tipo;
	}

	public void setTipo(String t)
	{
		tipo = t;
	}
	
	public String getPropietario()
	{
		return propietario;
	}

	public void setPropietario(String p)
	{
		propietario = p;
	}
}