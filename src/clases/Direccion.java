package clases;

import java.io.Serializable;
import java.util.Objects;

/**
 * esta clase difiene direcccion que se usaran en el programa
 * @author Grupo 2
 * @version 2.0.1
 */
public class Direccion implements Comparable<Direccion>, Serializable
{
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
	public Direccion()
	{
		codPostal = 01001;
		calle = "";
		portal = 1;
		piso = 1;
		puerta = "A";
	}
	
	/**
	 * constructor copia
	 * @param other copia del constructor por defecto
	 */
	public Direccion(Direccion other)
	{
		this.codPostal = other.codPostal;
		this.calle = other.calle;
		this.portal = other.portal;
		this.piso = other.piso;
		this.puerta = other.puerta;
	}
	
	// --- personalizado ---
	/**
	 * si la direccion no comple sierto datos establicidos asigna unos por defecto
	 * @param cp codigo postal
	 * @param c calle
	 * @param po portal
	 * @param pi piso
	 * @param pu puerta
	 */
	public Direccion(int cp, String c, int po, int pi, String pu)
	{
		// - codPostal -
		if (cp < 01001)
		{
			codPostal = 01001;
		}
		else if (cp > 52080)
		{
			codPostal = 52080;
		}
		else
		{
			codPostal = cp;
		}

		// - calle -
		calle = c;

		// - portal -
		if (po <= 0)
		{
			portal = 1;
		}
		else
		{
			portal = po;
		}

		// - piso -
		if (pi <= 0)
		{
			piso = 1;
		}
		else
		{
			piso = pi;
		}

		// - puerta -
		if (pu.equals(""))
		{
			pu = "A";
		}
		else
		{
			puerta = pu;
		}
	}

	// ===== métodos =====
	// --- salida ---
	@Override
	public String toString()
	{
		return calle + ", " + portal + ", " + piso + " " + puerta + ", " + codPostal;
	}

	// --- comparación ---
	@Override
	public int hashCode()
	{
		return Objects.hash(calle, codPostal, piso, portal, puerta);
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
		Direccion other = (Direccion) obj;
		return Objects.equals(calle, other.calle) && codPostal == other.codPostal && piso == other.piso
				&& portal == other.portal && Objects.equals(puerta, other.puerta);
	}
	
	@Override
	public int compareTo(Direccion other)
	{
		int comparacionCodPostal = Integer.valueOf(this.codPostal).compareTo(Integer.valueOf(other.codPostal));
		if (comparacionCodPostal == 0)
		{
			int comparacionCalle = this.calle.compareTo(other.calle);
			if (comparacionCalle == 0)
			{
				int comparacionPortal = Integer.valueOf(this.portal).compareTo(Integer.valueOf(other.portal));
				if (comparacionPortal == 0)
				{
					int comparacionPiso = Integer.valueOf(this.piso).compareTo(Integer.valueOf(other.piso));
					if (comparacionPiso == 0)
					{
						int comparacionPuerta = this.puerta.compareTo(other.puerta);
						if (comparacionPuerta != 0)
						{
							return comparacionPuerta;
						}
					}
					else
					{
						return comparacionPiso;
					}
				}
				else
				{
					return comparacionPortal;
				}
			}
			else
			{
				return comparacionCalle;
			}
		}
		else
		{
			return comparacionCodPostal;
		}

		return 0;
	}

	// --- getters y setters ---
	public int getCodPostal()
	{
		return codPostal;
	}

	public void setCodPostal(int cp)
	{
		if (cp < 01001)
		{
			codPostal = 01001;
		}
		else if (cp > 52080)
		{
			codPostal = 52080;
		}
		else
		{
			codPostal = cp;
		}
	}

	public String getCalle()
	{
		return calle;
	}

	public void setCalle(String calle)
	{
		this.calle = calle;
	}

	public int getPortal()
	{
		return portal;
	}

	public void setPortal(int po)
	{
		if (po <= 0)
		{
			portal = 1;
		}
		else
		{
			portal = po;
		}
	}

	public int getPiso()
	{
		return piso;
	}

	public void setPiso(int pi)
	{
		if (pi <= 0)
		{
			piso = 1;
		}
		else
		{
			portal = pi;
		}
	}

	public String getPuerta()
	{
		return puerta;
	}

	public void setPuerta(String pu)
	{
		if (pu.equals(""))
		{
			pu = "A";
		}
		else
		{
			puerta = pu;
		}
	}
}