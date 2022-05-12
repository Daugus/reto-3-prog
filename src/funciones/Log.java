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
	private static String raiz = "C:\\RKA\\Logs\\";

	private static String materiales = raiz + "materiales.log";
	private static String vehiculos = raiz + "vehiculos.log";
	private static String clientes = raiz + "clientes.log";
	private static String cuentas = raiz + "cuentas.log";
	private static String ordenes = raiz + "ordenes.log";
	private static String sesion = raiz + "sesion.log";
	private static String error = raiz + "error.log";

	// ====== generar logs =======
	private static void grabar(String rutaArchivo, String mensaje, boolean error) {
		// --- crear carpeta de logs ---
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

			if (error) {
				logger.warning(mensaje);
			} else {
				logger.info(mensaje);
			}

			fh.close();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// --- individuales ---
	// - guardado -
	public static void material(String cod) {
		grabar(materiales, "se ha guardado el material " + cod, false);
	}

	public static void vehiculo(String cod) {
		grabar(vehiculos, "se ha guardado el vehículo " + cod, false);
	}

	public static void cliente(String cod) {
		grabar(clientes, "se ha guardado el cliente " + cod, false);
	}

	public static void cuenta(String cod) {
		grabar(cuentas, "se ha guardado la cuenta " + cod, false);
	}

	public static void ajustes(String cod) {
		grabar(cuentas, "se han cambiado los ajustes de " + cod, false);
	}

	public static void factura(String cod) {
		grabar(ordenes, "se ha guardado la factura " + cod, false);
	}

	// - inicio y salida -
	public static void login() {
		String mensaje = "el usuario: " + Inicio.empleadoActual.getDNI() + " ha iniciado sesión";
		grabar(sesion, mensaje, false);
	}

	public static void logout() {
		String mensaje = "el usuario: " + Inicio.empleadoActual.getDNI() + " ha cerrado sesión";
		grabar(sesion, mensaje, false);
	}

	// - errores -
	public static void error(String err) {
		grabar(error, err, true);
	}
}