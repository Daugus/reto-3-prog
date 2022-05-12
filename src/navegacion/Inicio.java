package navegacion;

import java.awt.Color;
import java.awt.Font;

import clases.Empleado;
import funciones.Datos;

/**
 * clase para iniciar el programa
 */
public class Inicio {
	/*
	 * objeto con los datos del empleado que ha iniciado sesión
	 */
	public static Empleado empleadoActual;

	/**
	 * Font para las etiquetas basada en la propiedad fuente
	 */
	public static Font fuente = new Font("Segoe UI", Font.PLAIN, 13);

	/**
	 * Font para los botones, campos de texto y otros elementos basada en la
	 * propiedad fuente
	 */
	public static Font fuenteObjetos = new Font("Segoe UI", Font.BOLD, 13);

	/**
	 * Color de fondo para la ventana basado en el tema
	 */
	public static Color colorFondo = Color.DARK_GRAY;

	/**
	 * Color de fondo para los botones, campos de texto y otros elementos basado en
	 * el tema
	 */
	public static Color colorFondoObjetos = Color.LIGHT_GRAY;

	/**
	 * Color de la fuente para las etiquetas basado en el tema
	 */
	public static Color colorFuente = Color.WHITE;

	/**
	 * Color de la fuente para los botones, campos de texto y otros elementos basado
	 * en el tema
	 */
	public static Color colorFuenteObjetos = Color.BLACK;

	/*
	 * función main para ejecutar el programa
	 */
	public static void main(String[] args) {
		Datos.configurarObjectDB();

		new Login().setVisible(true);
	}
}