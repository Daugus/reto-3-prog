package funciones;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import navegacion.Inicio;

/**
 * métodos para crear y escribir archivos de log
 */
public class Log {
	// ===== rutas =====
	private static String raiz = "Logs\\";

	private static String datos = raiz + "datos.log";
	private static String sesion = raiz + "sesion.log";
	private static String error = raiz + "error.log";

	// ====== generar logs =======
	// --- general ---
	private static void grabar(String rutaArchivo, String mensaje) {
		// - crear carpeta de logs -
		File f = new File(raiz);
		if (!f.exists())
			f.mkdirs();

		Logger logger = Logger.getLogger("log");
		FileHandler fh;

		try {
			fh = new FileHandler(rutaArchivo, true);
			logger.addHandler(fh);

			System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");

			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			logger.info(mensaje);

			fh.close();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// --- individuales ---
	// - guardado -
	/**
	 * genera el mensaje para el log de guardado de ajustes
	 * 
	 * @param cod la clave primaria del objeto guardado
	 */
	public static void guardadoAjustes(String cod) {
		grabar(datos, "se han cambiado los ajustes de " + cod);
	}

	/**
	 * genera el mensaje para el log de guardado del cliente
	 * 
	 * @param cod la clave primaria del objeto guardado
	 */
	public static void guardadoCliente(String cod) {
		grabar(datos, "se ha guardado el cliente " + cod);
	}

	/**
	 * genera el mensaje para el log de guardado del empleado
	 * 
	 * @param cod la clave primaria del objeto guardado
	 */
	public static void guardadoEmpleado(String cod) {
		grabar(datos, "se ha guardado el empleado " + cod);
	}

	/**
	 * genera el mensaje para el log de guardado de la factura
	 * 
	 * @param cod la clave primaria del objeto guardado
	 */
	public static void guardadoFactura(String cod) {
		grabar(datos, "se ha guardado la factura " + cod);
	}

	/**
	 * genera el mensaje para el log de guardado del material
	 * 
	 * @param cod la clave primaria del objeto guardado
	 */
	public static void guardadoMaterial(String cod) {
		grabar(datos, "se ha guardado el material " + cod);
	}

	/**
	 * genera el mensaje para el log de guardado de la orden
	 * 
	 * @param cod la clave primaria del objeto guardado
	 */
	public static void guardadoOrden(String cod) {
		grabar(datos, "se ha guardado la orden " + cod);
	}

	/**
	 * genera el mensaje para el log de guardado de la reparación
	 * 
	 * @param cod la clave primaria del objeto guardado
	 */
	public static void guardadoReparacion(String cod) {
		grabar(datos, "se ha guardado la reparación " + cod);
	}

	/**
	 * genera el mensaje para el log de guardado del vehículo
	 * 
	 * @param cod la clave primaria del objeto guardado
	 */
	public static void guardadoVehiculo(String cod) {
		grabar(datos, "se ha guardado el vehículo " + cod);
	}

	// - borrado -
	/**
	 * genera el mensaje para el log de borrado de la factura
	 * 
	 * @param cod la clave primaria del objeto guardado
	 */
	public static void borradoFactura(String cod) {
		grabar(datos, "se ha borrado la factura " + cod);
	}

	// - inicio y cerrado de sesión -
	/**
	 * genera el mensaje para el log de inicio de sesión
	 */
	public static void login() {
		String mensaje = "el usuario: " + Inicio.empleadoActual.getDNI() + " ha iniciado sesión";
		grabar(sesion, mensaje);
	}

	/**
	 * genera el mensaje para el log de cerrado de sesión
	 */
	public static void logout() {
		String mensaje = "el usuario: " + Inicio.empleadoActual.getDNI() + " ha cerrado sesión";
		grabar(sesion, mensaje);
	}

	// - errores -
	/**
	 * graba el mensaje de error
	 * 
	 * @param mensaje mensaje de error
	 */
	public static void error(String mensaje) {
		// - crear carpeta de logs -
		File f = new File(raiz);
		if (!f.exists())
			f.mkdirs();

		Logger logger = Logger.getLogger("log");
		FileHandler fh;

		try {
			fh = new FileHandler(error, true);
			logger.addHandler(fh);

			System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");

			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			logger.warning(mensaje);

			fh.close();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}