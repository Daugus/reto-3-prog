package funciones;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import navegacion.Inicio;

/**
 * esta clase difiene logs que se generaran durante el proceso de la ejecucion
 * del programa
 * 
 * @author Grupo 2
 * @version 2.0.1
 */
public class Log {
	// ===== rutas =====
	private static String raiz = "RKA\\Logs\\";
	//private static String raiz = "C:\\RKA\\Logs\\";
	private static String error = raiz + "error.log";
	private static String materiales = raiz + "materiales.log";
	private static String vehiculos = raiz + "vehiculos.log";
	private static String clientes = raiz + "clientes.log";
	private static String cuentas = raiz + "cuentas.log";
	private static String ordenes = raiz + "ordenes.log";
	private static String sesion = raiz + "sesion.log";

	// ====== generar logs =======
	/**
	 * genera logs pasandle tres parametros
	 * 
	 * @param rutaArchivo donde se guardara el mensaje
	 * @param mensaje     a grabar
	 * @param error       boolean si es error o info //por defecto error
	 * @see Logger
	 * @exception si la ruta no existe
	 */
	private static void grabar(String rutaArchivo, String mensaje, boolean error) {
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

	/**
	 * guarda el menaje pasado como parametro en la carpeta de logs/ordenes como
	 * info
	 * 
	 * @param mensaje que se guarda en el log
	 * @see Logger
	 */
	private static void orden(String mensaje) {
		grabar(ordenes, mensaje, false);
	}

	// --- individuales ---
	// - guardado -
	/**
	 * guarda el menaje y codigo de material pasado como parametro en la carpeta de
	 * logs/materiales como info
	 * 
	 * @param cod de matrial a guardar
	 * @see Logger
	 */
	public static void material(String cod) {
		grabar(materiales, "se ha guardado el material " + cod, false);
	}

	/**
	 * guarda el menaje y matricula del vehiculo pasado como parametro en la carpeta
	 * de logs/vehiculo como info
	 * 
	 * @param cod matricula del vehiculo que se guarda en el log
	 * @see Logger
	 */
	public static void vehiculo(String cod) {
		grabar(vehiculos, "se ha guardado el vehículo " + cod, false);
	}

	/**
	 * guarda el menaje y dni pasado como parametro en la carpeta de logs/clientes
	 * como info
	 * 
	 * @param cod dni del cliente que se guarda en el log
	 * @see Logger
	 */
	public static void cliente(String cod) {
		grabar(clientes, "se ha guardado el cliente " + cod, false);
	}

	/**
	 * guarda el menaje y dni pasado como parametro en la carpeta de logs/cuentas
	 * como info
	 * 
	 * @param cod dni de la cuenta que se guarda en el log
	 * @see Logger
	 */
	public static void cuenta(String cod) {
		grabar(cuentas, "se ha guardado la cuenta " + cod, false);
	}

	/**
	 * guarda el menaje y dni pasado como parametro en la carpeta de logs/cuentas
	 * como info
	 * 
	 * @param cod dni de la cuenta que se guarda en el log
	 * @see Logger
	 */
	public static void ajustes(String cod) {
		grabar(cuentas, "se han cambiado los ajustes de " + cod, false);
	}

	/**
	 * guarda el menaje y codigo de orden primaria pasado como parametro en la
	 * carpeta de logs/ordenes como info
	 * 
	 * @param cod numero de la orden primaria que se guarda en el log
	 * @see Logger
	 */
	public static void primaria(String cod) {
		grabar(ordenes, "se ha guardado la orden primaria " + cod, false);
	}

	/**
	 * guarda el menaje y codigo de orden pendiente pasado como parametro en la
	 * carpeta de logs/ordenes como info
	 * 
	 * @param cod numero de la orden pendiente que se guarda en el log
	 * @see Logger
	 */
	public static void pendiente(String cod) {
		grabar(ordenes, "se ha guardado la orden pendiente " + cod, false);
	}

	/**
	 * guarda el menaje y codigo de la factura pasado como parametro en la carpeta
	 * de logs/ordenes como info
	 * 
	 * @param cod numero de la factura que se guarda en el log
	 * @see Logger
	 */
	public static void factura(String cod) {
		grabar(ordenes, "se ha guardado la factura " + cod, false);
	}

	// - inicio y salida -
	/**
	 * guarda el menaje y usuario de la cuenta actual que inicia sesion en la
	 * carpeta de logs/sesion como info
	 * 
	 * @see Logger
	 */
	public static void login() {
		String mensaje = "el usuario: " + Inicio.cuentaActual.getDNI() + " ha iniciado sesión";
		grabar(sesion, mensaje, false);
	}

	/**
	 * guarda el menaje y usuario de la cuenta actual que cierra sesion en la
	 * carpeta de logs/sesion como info
	 * 
	 * @see Logger
	 */
	public static void logout() {
		String mensaje = "el usuario: " + Inicio.cuentaActual.getDNI() + " ha cerrado sesión";
		grabar(sesion, mensaje, false);
	}

	// - errores -
	/**
	 * guarda el mensaje del error en la ruta de errores con tipo error
	 * 
	 * @param err String mensaje a grabar
	 */
	public static void error(String err) {
		grabar(error, err, true);
	}

	// - borrado -
	/**
	 * si se borra una orden guarda mensaje y numero de orden primaria pasado como
	 * parametro en la ruta de ordenes
	 * 
	 * @param cod numero de orden primaria a guardar
	 * @see Logger
	 */
	public static void borrarPrimaria(String cod) {
		orden("se ha borrado la orden primaria " + cod);
	}

	/**
	 * si se borra una orden guarda mensaje y numero de orden pendiente pasado como
	 * parametro en la ruta de ordenes
	 * 
	 * @param cod numero de orden pendiente a guardar
	 * @see Logger
	 */
	public static void borrarPendiente(String cod) {
		orden("se ha borrado la orden pendiente " + cod);
	}
}