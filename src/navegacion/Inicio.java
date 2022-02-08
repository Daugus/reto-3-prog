package navegacion;

import java.awt.Color;
import java.awt.Font;

import clases.Cuenta;
import funciones.Archivos;

/**
 * esta clase difiene el main del programa
 * @author Grupo 2
 * @version 2.0.1
 */
public class Inicio
{
	public static Cuenta cuentaActual;
	public static Font fuente = new Font("Segoe UI", Font.PLAIN, 13);
	public static Font fuenteObjetos = new Font("Segoe UI", Font.BOLD, 13);
	
	public static Color colorFondo = Color.DARK_GRAY;
	public static Color colorFondoObjetos = Color.LIGHT_GRAY;
	public static Color colorFuente = Color.WHITE;
	public static Color colorFuenteObjetos = Color.BLACK;
	/**
	 * 
	 * @param args main de arranque del programa
	 */
	public static void main(String[] args)
	{
		Archivos.crearCarpetas();

		new Login().setVisible(true);
	}
}