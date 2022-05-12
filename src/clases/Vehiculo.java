package clases;

import java.io.Serializable;
import java.util.Objects;

/**
 * clase de Vehículo
 */
public class Vehiculo implements Comparable<Vehiculo>, Serializable {
	private static final long serialVersionUID = 2469183365830659245L;

	// ===== propiedades =====
	private String matricula;
	private String bastidor;
	private String propietario;

	private String marca;
	private String modelo;

	private Fecha fechaFabricacion;

	private String tipo;

	private boolean activo;

	// ===== constructores =====
	/**
	 * constructor por defecto
	 */
	public Vehiculo() {
		matricula = "";
		bastidor = "";
		propietario = "";

		marca = "";
		modelo = "";

		activo = true;

		fechaFabricacion = new Fecha();

		tipo = "";
	}

	// --- personalizados ---
	/**
	 * constructor personalizado para listar vehículos
	 * 
	 * @param matricula   matrícula del vehículo
	 * @param propietario propietario del vehículo
	 */
	public Vehiculo(String matricula, String propietario) {
		this.matricula = matricula;
		bastidor = "";
		this.propietario = propietario;

		marca = "";
		modelo = "";

		activo = true;

		fechaFabricacion = new Fecha();

		tipo = "";
	}

	/**
	 * constructor personalizado completo
	 * 
	 * @param matricula        matrícula del vehículo
	 * @param bastidor         nº de bastido del vehículo
	 * @param propietario      propietario del vehículo
	 * @param marca            marca del vehículo
	 * @param modelo           modelo del vehículo
	 * @param fechaFabricacion fecha de fabricación del vehículo
	 * @param tipo             tipo de vehículo (Diésel, Gasolina, Eléctrico)
	 * @param activo           estado del vehículo
	 */
	public Vehiculo(String matricula, String bastidor, String propietario, String marca, String modelo,
			Fecha fechaFabricacion, String tipo, boolean activo) {
		this.matricula = matricula;
		this.bastidor = bastidor;
		this.propietario = propietario;

		this.marca = marca;
		this.modelo = modelo;

		this.fechaFabricacion = fechaFabricacion;

		this.tipo = tipo;

		this.activo = activo;
	}

	// ===== métodos =====
	// --- salida ---
	@Override
	public String toString() {
		return "Matrícula:  " + matricula + ", Propietario: " + propietario + ", Modelo: " + marca + " " + modelo;
	}

	// --- comparación ---
	@Override
	public int hashCode() {
		return Objects.hash(propietario, activo, fechaFabricacion, marca, matricula, modelo, bastidor, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehiculo other = (Vehiculo) obj;
		return Objects.equals(activo, other.activo) && Objects.equals(fechaFabricacion, other.fechaFabricacion)
				&& Objects.equals(marca, other.marca) && Objects.equals(matricula, other.matricula)
				&& Objects.equals(modelo, other.modelo) && bastidor == other.bastidor
				&& Objects.equals(tipo, other.tipo) && Objects.equals(propietario, other.propietario);
	}

	@Override
	public int compareTo(Vehiculo other) {
		return matricula.compareTo(other.matricula);
	}

	// --- getters ---
	public String getMatricula() {
		return matricula;
	}

	public String getBastidor() {
		return bastidor;
	}

	public String getModelo() {
		return modelo;
	}

	public String getMarca() {
		return marca;
	}

	public Fecha getFechaFabricacion() {
		return fechaFabricacion;
	}

	public boolean isActivo() {
		return activo;
	}

	public String getTipo() {
		return tipo;
	}

	public String getPropietario() {
		return propietario;
	}
}