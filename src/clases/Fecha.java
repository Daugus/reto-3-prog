package clases;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

/**
 * clase de Fecha
 */
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
		month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		year = Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * constructor copia
	 * 
	 * @param other Objeto que se va a copiar
	 */
	public Fecha(Fecha other) {
		this.day = other.day;
		this.month = other.month;
		this.year = other.year;
	}

	// --- personalizados ---
	/**
	 * constructor personalizado usando enteros
	 * 
	 * @param d día
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

	/**
	 * constructor personalizado usando enteros
	 * 
	 * @param fechaSQL fecha en formato usado por SQL
	 */
	public Fecha(String fechaSQL) {
		String[] campos = fechaSQL.split("-");
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
	@Override
	public String toString() {
		return String.format("%02d/%02d/%04d", day, month, year);
	}

	/**
	 * formatear fecha en formato usado por SQL
	 * 
	 * @return fecha formateada en aaaa-mm-dd
	 */
	public String toSQLDate() {
		return String.format("%04d-%02d-%02d", year, month, day);
	}

	// --- comparación ---
	@Override
	public int hashCode() {
		return Objects.hash(day, month, year);
	}

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

	// --- getters ---
	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}
}