package navegacion;

import java.awt.Color;

import clases.Cuenta;
import funciones.Archivos;

/**
 * 
 * @author Grupo 2
 *
 */
public class Inicio
{
	/**
	 * @param args
	 * contiene el main que se encarga del arranque del programa
	 */
	public static Cuenta cuentaActual;
	public static Color colorFondo = Color.DARK_GRAY;
	public static Color colorFuente = Color.WHITE;
	public static String fuente = "Tahoma";

	public static void main(String[] args)
	{
		Archivos.crearCarpetas();

		Login l = new Login();
		l.setLocationRelativeTo(null);
		l.setVisible(true);
	}
}