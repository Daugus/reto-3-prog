package funciones;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * métodos genéricos
 */
public class General {
	/**
	 * formatea precio para mostrar un precio limitando a dos decimales y agregando
	 * el símbolo de euro
	 * 
	 * @param decimal double original
	 * @return String formateada
	 */
	public static String formatearPrecio(double decimal) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		return formatter.format(decimal) + "€";
	}

	/**
	 * convierte el booleano de estado de factura en una String para poder guardar
	 * el estado en la base de datos
	 * 
	 * @param pagada estado compatible con el programa
	 * @return estado compatible con la base de datos
	 */
	public static String pagadaAString(boolean pagada) {
		String resultado = "Pendiente";
		if (pagada)
			resultado = "Pagada";

		return resultado;
	}

	/**
	 * convierte la String de estado de factura de la base de datos en un booleano
	 * para poder crear el objeto Factura
	 * 
	 * @param pagada estado compatible con la base de datos
	 * @return estado compatible con la base de datos
	 */
	public static boolean pagadaABoolean(String pagada) {
		pagada = pagada.toLowerCase();
		boolean resultado = false;
		if (pagada.equals("pagada"))
			resultado = true;

		return resultado;
	}

	/**
	 * convierte el booleano de estado del objeto en una String para poder guardar
	 * el estado en la base de datos
	 * 
	 * @param estado estado compatible con el programa
	 * @return estado compatible con la base de datos
	 */
	public static String estadoAString(boolean estado) {
		String resultado = "Inactivo";
		if (estado)
			resultado = "Activo";

		return resultado;
	}

	/**
	 * convierte la String de estado de la base de datos en un booleano para poder
	 * crear el objeto
	 * 
	 * @param estado estado compatible con la base de datos
	 * @return estado compatible con el programa
	 */
	public static boolean estadoABoolean(String estado) {
		estado = estado.toLowerCase();
		boolean resultado = false;
		if (estado.equals("activo"))
			resultado = true;

		return resultado;
	}
}