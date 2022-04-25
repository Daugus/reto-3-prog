package clases;

import java.io.Serializable;
import java.util.Objects;

public class Direccion implements Comparable<Direccion>, Serializable {
	private static final long serialVersionUID = 5402725963046351341L;

	// ===== propiedades =====
	private int codPostal;
	private String calle;
	private int portal;
	private int piso;
	private String puerta;

	// ===== constructores =====
	/**
	 * constructor por defecto
	 */
	public Direccion() {
		codPostal = 01001;
		calle = "";
		portal = 1;
		piso = 1;
		puerta = "A";
	}

	/**
	 * constructor copia
	 */
	public Direccion(Direccion other) {
		this.codPostal = other.codPostal;
		this.calle = other.calle;
		this.portal = other.portal;
		this.piso = other.piso;
		this.puerta = other.puerta;
	}

	// --- personalizado ---
	/**
	 * si la direccion no cumple ciertos datos establecidos asigna unos por defecto
	 * 
	 * @param cp codigo postal
	 * @param c  calle
	 * @param po portal
	 * @param pi piso
	 * @param pu puerta
	 */
	public Direccion(int cp, String c, int po, int pi, String pu) {
		// - codPostal -
		if (cp < 01001) {
			codPostal = 01001;
		} else if (cp > 52080) {
			codPostal = 52080;
		} else {
			codPostal = cp;
		}

		// - calle -
		calle = c;

		// - portal -
		if (po <= 0) {
			portal = 1;
		} else {
			portal = po;
		}

		// - piso -
		if (pi <= 0) {
			piso = 1;
		} else {
			piso = pi;
		}

		// - puerta -
		if (pu.equals("")) {
			pu = "A";
		} else {
			puerta = pu;
		}
	}

	// ===== métodos =====
	// --- salida ---
	/**
	 * Devuelve una representación de cadena del objeto.
	 * 
	 * @return los atributos del objeto
	 */
	@Override
	public String toString() {
		return calle + ", " + portal + ", " + piso + " " + puerta + ", " + codPostal;
	}

	// --- comparación ---
	/**
	 * devuelve el hash code del objeto basado en sus atributos
	 * 
	 * @return devuelve un valor hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(calle, codPostal, piso, portal, puerta);
	}

	/**
	 * indica si algún otro objeto es igual a este
	 * 
	 * @param obj el objeto con el que se va a comparar
	 * @return {@code true} si el objeto es igual que el parámetro obj
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Direccion other = (Direccion) obj;
		return Objects.equals(calle, other.calle) && codPostal == other.codPostal && piso == other.piso
				&& portal == other.portal && Objects.equals(puerta, other.puerta);
	}

	/**
	 * Compara este objeto con el objeto especificado para el orden. Devuelve un
	 * entero negativo, cero o un entero positivo, ya que este objeto es menor,
	 * igual o mayor que el objeto especificado.
	 * 
	 * @return entero negativo, cero o un entero positivo, ya que este objeto es
	 *         menor, igual o mayor que el objeto especificado.
	 */
	@Override
	public int compareTo(Direccion other) {
		int comparacionCodPostal = Integer.valueOf(this.codPostal).compareTo(Integer.valueOf(other.codPostal));
		if (comparacionCodPostal == 0) {
			int comparacionCalle = this.calle.compareTo(other.calle);
			if (comparacionCalle == 0) {
				int comparacionPortal = Integer.valueOf(this.portal).compareTo(Integer.valueOf(other.portal));
				if (comparacionPortal == 0) {
					int comparacionPiso = Integer.valueOf(this.piso).compareTo(Integer.valueOf(other.piso));
					if (comparacionPiso == 0) {
						int comparacionPuerta = this.puerta.compareTo(other.puerta);
						if (comparacionPuerta != 0) {
							return comparacionPuerta;
						}
					} else {
						return comparacionPiso;
					}
				} else {
					return comparacionPortal;
				}
			} else {
				return comparacionCalle;
			}
		} else {
			return comparacionCodPostal;
		}

		return 0;
	}

	// --- getters y setters ---
	/**
	 * acceso a tema
	 * 
	 * @return tema
	 */
	public int getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(int cp) {
		if (cp < 01001) {
			codPostal = 01001;
		} else if (cp > 52080) {
			codPostal = 52080;
		} else {
			codPostal = cp;
		}
	}

	/**
	 * acceso a calle
	 * 
	 * @return calle
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * modifica el valor de calle pasando calle como parametro
	 * 
	 * @param tema tipo String
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}

	/**
	 * acceso a portal
	 * 
	 * @return portal
	 */
	public int getPortal() {
		return portal;
	}

	/**
	 * modifica el valor de po pasando po como parametro
	 * 
	 * @param po tipo int
	 */
	public void setPortal(int po) {
		if (po <= 0) {
			portal = 1;
		} else {
			portal = po;
		}
	}

	/**
	 * acceso a piso
	 * 
	 * @return piso
	 */
	public int getPiso() {
		return piso;
	}

	/**
	 * modifica el valor de portal pasando portal como parametro
	 * 
	 * @param pi tipo int
	 */
	public void setPiso(int pi) {
		if (pi <= 0) {
			piso = 1;
		} else {
			portal = pi;
		}
	}

	/**
	 * acceso a puerta
	 * 
	 * @return puerta
	 */
	public String getPuerta() {
		return puerta;
	}

	/**
	 * modifica el valor de puerta pasando puerta como parametro
	 * 
	 * @param pu tipo String
	 */
	public void setPuerta(String pu) {
		if (pu.equals("")) {
			pu = "A";
		} else {
			puerta = pu;
		}
	}
}