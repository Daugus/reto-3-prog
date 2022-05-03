package clases;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

public class Fecha implements Comparable<Fecha>, Serializable {
	private static final long serialVersionUID = 5402725963046351341L;

	// ===== propiedades =====
	private int day;
	private int month;
	private int year;

	// ===== constructores =====
	/**
	 * constructor por defecto
	 */
	public Fecha() {
		day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		month = Calendar.getInstance().get(Calendar.MONTH);
		year = Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * constructor copia
	 */
	public Fecha(Fecha other) {
		this.day = other.day;
		this.month = other.month;
		this.year = other.year;
	}

	public Fecha(int a) {
		day = 1;
		month = 1;
		year = a;
	}

	/**
	 * fecha que se le pasan parametros, si le pasan datos imcorrecctos difiene unos
	 * por defecto
	 * 
	 * @param d dia
	 * @param m mes
	 * @param a año
	 */
	public Fecha(int d, int m, int a) {
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
	
	public Fecha(String fecha) {
		String[] campos = fecha.split("-");
		year = Integer.parseInt(campos[0]);
		month = Integer.parseInt(campos[1]);
		day = Integer.parseInt(campos[2]);
	}

	// ===== métodos =====
	// --- personalizado ---
	private void bisiesto(int d, int m, int a) {
		if (d > 28) {
			switch (m) {
			// febrero
			case 2:
				if (a % 4 == 0) {
					if (a % 100 == 0) {
						if (a % 400 == 0) {
							day = 29;
						} else {
							day = 28;
						}
					} else {
						day = 29;
					}
				} else {
					day = 28;
				}
				break;
			// abril, junio, septiembre o noviembre
			case 4:
			case 6:
			case 9:
			case 11:
				if (d > 30) {
					day = 30;
				} else {
					day = d;
				}
				break;
			// enero, marzo, mayo, julio, agosto, octubre, diciembre
			default:
				day = d;
				break;
			}
		} else {
			day = d;
		}
	}

	// --- salida ---
	/**
	 * Devuelve una representación de cadena del objeto.
	 * 
	 * @return los atributos del objeto
	 */
	@Override
	public String toString() {
		String d = String.format("%02d", day);
		String m = String.format("%02d", month);
		String y = String.format("%04d", year);

		return d + "/" + m + "/" + y;
	}

	// --- comparación ---
	/**
	 * devuelve el hash code del objeto basado en sus atributos
	 * 
	 * @return el hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(day, month, year);
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
		Fecha other = (Fecha) obj;
		return year == other.year && day == other.day && month == other.month;
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
	public int compareTo(Fecha other) {
		if (this.year > other.year) {
			return 1;
		} else if (this.year < other.year) {
			return -1;
		} else {
			// si los años son iguales
			if (this.month > other.month) {
				return 1;
			} else if (this.month < other.month) {
				return -1;
			} else {
				// si los meses son iguales
				if (this.day > other.day) {
					return 1;
				} else if (this.day < other.day) {
					return -1;
				}
			}

			return 0;
		}
	}

	// --- getters y setters ---
	/**
	 * acceso a dia
	 * 
	 * @return day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * modifica el valor de dia pasando int dia como parametro
	 * 
	 * @param d tipo int
	 */
	public void setDay(int d) {
		if (d < 1) {
			day = 1;
		} else {
			bisiesto(d, month, year);
		}
	}

	/**
	 * acceso a mes
	 * 
	 * @return month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * modifica el valor de mes pasando int mes como parametro
	 * 
	 * @param m tipo int
	 */
	public void setMonth(int m) {
		if (m < 1) {
			month = 1;
		} else if (m > 12) {
			month = 12;
		} else {
			month = m;
		}

		bisiesto(day, m, year);
	}

	/**
	 * acceso al año
	 * 
	 * @return year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * modifica el valor de año pasando año como parametro
	 * 
	 * @param a tipo int
	 */
	public void setYear(int a) {
		year = a;
	}
}
