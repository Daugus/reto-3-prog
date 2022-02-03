package pruebas;

import java.awt.Font;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import clases.*;
import funciones.*;

@SuppressWarnings("unused")
public class Pruebas
{
	public static void main(String[] args)
	{
		// ===== crear carpetas =====
		Archivos.crearCarpetas();

		// ===== objetos de prueba =====
//		Archivos.guardarCuenta(new Cuenta("Y0723663M", "Augusto Augusto", "de la Cámara de la Cámara de la Cámara", 664860653,
//				"augustodelacamara@mail.com", new Fecha(26, 12, 2002),
//				new Direccion(48007, "Travesía Ciudad Jardín", 42, 12, "Izquierda"), true, "augusto123", new Ajustes()));
////
//		Archivos.guardarCuenta(new Cuenta("mec", "mec", "mec", 664860653,
//				"mec@mec.com", new Fecha(26, 12, 2002),
//				new Direccion(48007, "mec", 10, 1, "a"), true, "mec", new Ajustes()));
//
//		Archivos.guardarCuenta(new Cuenta("atc", "atc", "atc", 664860653,
//				"atc@atc.com", new Fecha(26, 12, 2002),
//				new Direccion(48007, "atc", 10, 1, "a"), false, "atc", new Ajustes()));
////
//		ArrayList<String> vehiculos = new ArrayList<String>();
//		vehiculos.add("4580BMK");
//		Cliente juan = new Cliente("04772641A", "Juan", "Calvo Peinado", 767425866,
//				"calvopeinado@gmail.com", new Fecha(23, 10, 1950),
//				new Direccion (29567, "Buena Vista", 88, 9, "b"), new Fecha(), vehiculos);
//		Archivos.guardarCliente(juan);
////		
//		vehiculos.removeAll(vehiculos);
//		vehiculos.add("7878MDH");
//		Archivos.guardarCliente(new Cliente("X6578073G", "Leandro", "Moya", 734460685,
//				"leandro_72@gmail.com", new Fecha(14, 2, 1972),
//				new Direccion (45787, "Carretera Catalunya", 45, 2, "Derecha"), new Fecha(), vehiculos));
//		
////
//		Archivos.guardarVehiculo(new Vehiculo("7878MDH", "3D7MU436138104574", "X6578073G",
//				"Toyota", "Corolla", "Naranja", 2000, 3612, new Fecha(2022), "Gasolina"));
//
//		Vehiculo vJuan = new Vehiculo("4580BMK", "SALTM1246Y1427642", "04772641A",
//				"BMW", "Serie 1", "Rojo", 2000, 84409, new Fecha(2021), "Diésel");
//		Archivos.guardarVehiculo(vJuan);
//
//		Archivos.guardarOrdenPrim(new OrdenPrim("1", "comentario de prueba", new Fecha(), juan, vJuan));
		
		// ===== ArrayLists de prueba =====
//		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
//		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

		// ===== agregar objetos cargados a las ArrayLists (necesario especificar tipo) =====
//		cuentas.add((Cuenta) Archivos.cargar("Y0723663M", "Cuenta"));
//		vehiculos.add((Vehiculo) Archivos.cargar("1234abc", "Vehiculo"));
		
		// ===== sacar ArrayLists por pantalla =====
//		System.out.println(cuentas);
//		System.out.println(vehiculos);
		
		// ===== listar clientes existentes =====
//		System.out.println(Archivos.listarClientes());
		
		// ===== pruebas =====
//		System.out.println(Archivos.cargarCliente("04772641A"));
//		System.out.println(Archivos.cargarMaterial("a"));
//		funciones.Archivos.guardarMaterial(new Material("test", 2.0));
		
//		System.out.println(new Font("Tahoma", Font.BOLD, 15).getFamily());
		
//		Archivos.guardarMaterial(new Material("atest", 30.0));
//		Archivos.guardarMaterial(new Material("btest", 30.0));

		// ===== fecha =====
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]");
		System.out.println(sdf.format(calendar.getTime()));
	}
}