package funciones;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class General
{
	public static String formatear(double decimal)
	{
		NumberFormat formatter = new DecimalFormat("#0.00");
		return formatter.format(decimal) + "€";
	}
}