package navegacion;

import java.awt.Color;
import java.awt.Font;

import clases.Cuenta;
import funciones.Archivos;

/**
 * esta clase contine el <b>main</b> para 
 * @author Grupo 2
 * @version 2.0.1
 */
public class Inicio
{
	/**
	 * Objeto cuenta
	 */
	public static Cuenta cuentaActual;
	/**
	 * estabelece valor a la fuente
	 */
	public static Font fuente = new Font("Segoe UI", Font.PLAIN, 13);
	/**
	 * estabelece valor a fuente de objeto
	 */
	public static Font fuenteObjetos = new Font("Segoe UI", Font.BOLD, 13);
	/**
	 * estabelece valor a color de fondo
	 */
	public static Color colorFondo = Color.DARK_GRAY;
	/**
	 * estabelece valor a color de fondo al objeto
	 */
	public static Color colorFondoObjetos = Color.LIGHT_GRAY;
	/**
	 * estabelece valore a color de fuente
	 */
	public static Color colorFuente = Color.WHITE;
	/**
	 * estabelece valore a color
	 */
	public static Color colorFuenteObjetos = Color.BLACK;
	
	/**
	 * 
	 * @param args main que se encarga de arrancar la aplicacion
	 * @see Login
	 * 
	 */
	public static void main(String[] args)
	{
		Archivos.crearCarpetas();

		new Login().setVisible(true);
	}
}