package funciones;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * esta clase difiene la manera de formateo de un precio
 * 
 * @author Grupo 2
 * @version 2.0.1
 */
public class General {
	/**
	 * formatea y concatina un double con el simbolo euro
	 * 
	 * @param decimal double a formatear
	 * @return devuelve decimal formateado concatinado con el simbolo euro
	 */
	public static String formatear(double decimal) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		return formatter.format(decimal) + "€";
	}
}