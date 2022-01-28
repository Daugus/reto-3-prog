package clases;

import java.io.Serializable;
import java.util.Objects;

public class Fecha implements Comparable<Fecha>, Serializable
{
	private static final long serialVersionUID = 5402725963046351341L;

	// ===== propiedades =====
	private int day;
	private int month;
	private int year;
	
	// ===== constructores =====
	// --- por defecto ---
	public Fecha()
	{
		day = 1;
		month = 1;
		year = 2022;
	}
	
	// --- copia ---
	public Fecha(Fecha other)
	{
		this.day = other.day;
		this.month = other.month;
		this.year = other.year;
	}
	
	// --- personalizado ---
	public Fecha(int a)
	{
		day = 1;
		month = 1;
		year = a;
	}

	public Fecha(int d, int m, int a)
	{
		if (m < 1) {
			this.month = 1;
		} else if (m > 12) {
			this.month = 12;
		} else {
			this.month = m;
		}

		if (d < 1) {
			this.day = 1;
		} else {
			this.bisiesto(d, m, a);
		}

		this.year = a;
	}

	// ===== métodos =====
	// --- salida ---
	@Override
	public String toString()
	{
		return day + "/" + month + "/" + year;
	}

	// --- comparación ---
	@Override
	public int hashCode()
	{
		return Objects.hash(day, month, year);
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
		Fecha other = (Fecha) obj;
		return year == other.year && day == other.day && month == other.month;
	}

	@Override
	public int compareTo(Fecha other)
	{
		if (this.year > other.year)
		{
			return 1;
		}
		else if (this.year < other.year)
		{
			return -1;
		}
		else
		{
			// si los años son iguales
			if (this.month > other.month)
			{
				return 1;
			}
			else if (this.month < other.month)
			{
				return -1;
			}
			else
			{
				// si los meses son iguales
				if (this.day > other.day)
				{
					return 1;
				}
				else if (this.day < other.day)
				{
					return -1;
				}
			}
			
			return 0;
		}
	}
	
	// --- getters y setters ---
	public int getDay()
	{
		return day;
	}

	public void setDay(int d)
	{
		if (d < 1)
		{
			day = 1;
		}
		else
		{
			bisiesto(d, month, year);
		}
	}

	public int getMonth()
	{
		return month;
	}

	public void setMonth(int m)
	{
		if (m < 1)
		{
			month = 1;
		}
		else if (m > 12)
		{
			month = 12;
		}
		else
		{
			month = m;
		}
		
		bisiesto(day, m, year);
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int a)
	{
		year = a;
	}
	
	// --- personalizados ---
	private void bisiesto(int d, int m, int a)
	{
		if (d > 28)
		{
			switch (m)
			{
				// febrero
				case 2:
					if (a % 4 == 0)
					{
						if (a % 100 == 0)
						{
							if (a % 400 == 0)
							{
								day = 29;
							}
							else
							{
								day = 28;
							}
						}
						else
						{
							day = 29;
						}
					}
					else
					{
						day = 28;
					}
					break;
				// abril, junio, septiembre o noviembre
				case 4:
				case 6:
				case 9:
				case 11:
					if (d > 30)
					{
						day = 30;
					}
					else
					{
						day = d;
					}
					break;
				default:
					day = d;
					break;
			}
		}
		else
		{
			day = d;
		}
	 }
}
