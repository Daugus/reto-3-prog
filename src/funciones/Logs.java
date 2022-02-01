package funciones;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import clases.Material;
import navegacion.Inicio;

public class Logs 
{
	// ===== Rutas =====
	public static String raiz = "C:\\RKA\\";
	public static String log = raiz +"Logs.log\\";
	
	// ====== Generar logs =======
		public static void grabar(String rutaArchivo, String mensaje)
		{

			Logger logger = Logger.getLogger("losLog");
			FileHandler fh;

			try 
			{

				fh = new FileHandler(rutaArchivo, true);
				logger.addHandler(fh);

				SimpleFormatter formatter = new SimpleFormatter();

				fh.setFormatter(formatter);

				logger.info(mensaje);

				fh.close();

			} catch (SecurityException e) 
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			}

		}
		// ===== individuales =====
		public static void loginLog()
		{
			String f=(log );
			String mensaje="el usuario: "+ Inicio.cuentaActual.getDNI()+" ha iniciado sesion\n";
			grabar(f,mensaje );
		}
		
		public static void logoutLog() 
		{
			String f=(log );
			String mensaje="el usuario: "+ Inicio.cuentaActual.getDNI()+" ha cerrado sesion\n";
			grabar(f,mensaje );
		}
		
		public static void ordenLog(String veh) {
			String f=(log);
			grabar(f,veh);
		}

}
