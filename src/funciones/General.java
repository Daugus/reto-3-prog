package funciones;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class General {
	public static String formatearPrecio(double decimal) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		return formatter.format(decimal) + "€";
	}
	
	public static String pagadaAString(boolean pagada) {
		String resultado = "pendiente";
		if (pagada)
			resultado = "pagada";

		return resultado;
	}

	public static boolean pagadaABoolean(String pagada) {
		boolean resultado = false;
		if (pagada.equals("pagada"))
			resultado = true;

		return resultado;
	}

	public static String estadoAString(boolean estado) {
		String resultado = "inactivo";
		if (estado)
			resultado = "activo";

		return resultado;
	}

	public static boolean estadoABoolean(String estado) {
		boolean resultado = false;
		if (estado.equals("activo"))
			resultado = true;

		return resultado;
	}
}