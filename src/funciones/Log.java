package funciones;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import navegacion.Inicio;

public class Log 
{
	// ===== Rutas =====
	public static String raiz = "C:\\RKA\\Logs\\";
	public static String sesion = raiz + "sesion.log";
	public static String primarias = raiz + "primarias.log";
	public static String error = raiz + "error.log";
	
	// ====== Generar logs =======
	public static void grabar(String rutaArchivo, String mensaje, boolean error)
	{
		Logger logger = Logger.getLogger("log");
		FileHandler fh;

		try 
		{
			fh = new FileHandler(rutaArchivo, true);
			logger.addHandler(fh);

			System.setProperty("java.util.logging.SimpleFormatter.format",
					"[%1$tF %1$tT] [%4$-7s] %5$s %n");

			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			if (error)
			{
				logger.warning(mensaje);
			}
			else
			{
				logger.info(mensaje);
			}

			fh.close();
		}
		catch (SecurityException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// ===== individuales =====
	public static void login()
	{
		String mensaje = "el usuario: " + Inicio.cuentaActual.getDNI() + " ha iniciado sesión";
		grabar(sesion, mensaje, false);
	}
		
	public static void logout() 
	{
		String mensaje = "el usuario: " + Inicio.cuentaActual.getDNI() + " ha cerrado sesión";
		grabar(sesion, mensaje, false);
	}
		
	public static void orden(String ord)
	{
		grabar(primarias, ord, false);
	}
		
	public static void error(String err)
	{
		grabar(error, err, true);
	}
}