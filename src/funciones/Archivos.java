package funciones;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import clases.Ajustes;
import clases.Cliente;
import clases.Cuenta;
import clases.Factura;
import clases.Material;
import clases.Pendiente;
import clases.Primaria;
import clases.Vehiculo;
import navegacion.Inicio;

public class Archivos {
	// ===== rutas =====
	private static String raiz = "C:\\RKA\\";

	private static String logs = raiz + "Logs\\";

	private static String materiales = raiz + "Material\\";
	private static String vehiculos = raiz + "Vehiculo\\";
	private static String clientes = raiz + "Cliente\\";
	private static String cuentas = raiz + "Cuenta\\";
	private static String facturas = raiz + "Factura\\";
	private static String primarias = raiz + "Primaria\\";
	private static String pendientes = raiz + "Pendiente\\";
	
	// ===== crear carpetas en caso de que no existan ======
	public static void crearCarpetas()
	{
		ArrayList<String> directorios = new ArrayList<String>();
		directorios.addAll(Arrays.asList(logs, materiales, vehiculos, clientes, cuentas, facturas, primarias, pendientes));

		File f;
		for (int i = 0; i < directorios.size(); i++)
		{
			f = new File(directorios.get(i));
			if (!f.exists())
			{
				f.mkdirs();
			}
		}
	}

	// ===== guardar =====
	private static void guardar(Object o, File f)
	{
		FileOutputStream fos;
		ObjectOutputStream oos;

		try 
		{
			fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);

			oos.writeObject(o);

			oos.close();
			fos.close();
		}
		catch (IOException e)
		{
			Log.error("error al guardar el objeto");
		}
	}

	// --- individuales ---
	public static void guardarMaterial(Material m)
	{
		File f = new File(materiales + m.getNombre() + ".dat");
		Log.material(m.getNombre());
		guardar(m, f);
	}

	public static void guardarVehiculo(Vehiculo v)
	{
		File f = new File(vehiculos + v.getMatricula() + ".dat");
		Log.vehiculo(v.getMatricula());
		guardar(v, f);
	}

	public static void guardarCliente(Cliente c)
	{
		File f = new File(clientes + c.getDNI() + ".dat");
		Log.cliente(c.getDNI());
		guardar(c, f);
	}

	public static void guardarCuenta(Cuenta c)
	{
		File f = new File(cuentas + c.getDNI() + ".dat");
		Log.cuenta(c.getDNI());
		guardar(c, f);
	}

	public static void guardarPrimaria(Primaria p)
	{
		File f = new File(primarias + p.getCodigo() + ".dat");
		Log.primaria(p.getCodigo());
		guardar(p, f);
	}

	public static void guardarPendiente(Pendiente p)
	{
		File f = new File(pendientes + p.getCodigo() + ".dat");
		Log.pendiente(p.getCodigo());
		guardar(p, f);
	}

	public static void guardarFactura(Factura fa)
	{
		File f = new File(facturas + fa.getCodigo() + ".dat");
		Log.factura(fa.getCodigo());
		guardar(fa, f);
	}

	// ===== cargar =====
	private static Object cargar(File f)
	{
		Object o = null;

		FileInputStream fis;
		ObjectInputStream ois;

		try {
			fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);

			o = ois.readObject();

			ois.close();
			fis.close();
		}
		catch (IOException e)
		{
			Log.error("no se han cargado los datos");
		}
		catch (ClassNotFoundException e)
		{
			Log.error("no se ha encontrado la clase espcificada");
		}

		return o;
	}

	// --- individuales ---
	public static Material cargarMaterial(String nombre)
	{
		File f = new File(materiales + nombre + ".dat");
		return (Material) cargar(f);
	}

	public static Vehiculo cargarVehiculo(String matricula)
	{
		File f = new File(vehiculos + matricula + ".dat");
		return (Vehiculo) cargar(f);
	}

	public static Cliente cargarCliente(String DNI)
	{
		File f = new File(clientes + DNI + ".dat");
		return (Cliente) cargar(f);
	}

	public static Cuenta cargarCuenta(String DNI)
	{
		File f = new File(cuentas + DNI + ".dat");
		return (Cuenta) cargar(f);
	}

	public static Primaria cargarPrimaria(String cod)
	{
		File f = new File(primarias + cod + ".dat");
		return (Primaria) cargar(f);
	}
	
	public static Pendiente cargarPendiente(String cod)
	{
		File f = new File(pendientes + cod + ".dat");
		return (Pendiente) cargar(f);
	}
	
	public static Factura cargarFactura(String cod)
	{
		File f = new File(facturas + cod + ".dat");
		return (Factura) cargar(f);
	}
	
	// ===== borrar archivos =====
	public static void borrarPrimaria(String cod)
	{
		File f = new File(primarias + cod + ".dat");
		if (f.delete())
		{
			Log.borrarPrimaria(cod);
		}
		else
		{
			Log.error("error al borrar la orden primaria " + cod);
		}
	}
	
	public static void borrarPendiente(String cod)
	{
		File f = new File(pendientes + cod + ".dat");
		if (f.delete())
		{
			Log.borrarPendiente(cod);
		}
		else
		{
			Log.error("error al borrar la orden pendiente " + cod);
		}
	}

	// ===== listar archivos =====
	private static ArrayList<String> listar(File[] listaOriginal)
	{
		ArrayList<String> lista = new ArrayList<String>();
		for (int i = 0; i < listaOriginal.length; i++)
		{
			if (listaOriginal[i].isFile())
			{
				String nombre = listaOriginal[i].getName().replaceAll(".dat", "");
				lista.add(nombre);
			}
		}

		return lista;
	}

	// --- individuales ---
	public static ArrayList<String> listarMateriales()
	{
		File[] listaOriginal = new File(materiales).listFiles();
		return listar(listaOriginal);
	}

	public static ArrayList<String> listarVehiculos()
	{
		File[] listaOriginal = new File(vehiculos).listFiles();
		return listar(listaOriginal);
	}

	public static ArrayList<String> listarClientes()
	{
		File[] listaOriginal = new File(clientes).listFiles();
		return listar(listaOriginal);
	}

	public static ArrayList<String> listarCuentas()
	{
		File[] listaOriginal = new File(cuentas).listFiles();
		return listar(listaOriginal);
	}

	public static ArrayList<String> listarPrimarias()
	{
		File[] listaOriginal = new File(primarias).listFiles();
		return listar(listaOriginal);
	}
	
	public static ArrayList<String> listarPendientes()
	{
		File[] listaOriginal = new File(pendientes).listFiles();
		return listar(listaOriginal);
	}
	
	public static ArrayList<String> listarFacturas()
	{
		File[] listaOriginal = new File(facturas).listFiles();
		return listar(listaOriginal);
	}

	// ===== cargar todos =====
	public static ArrayList<Material> cargarTodosMateriales()
	{
		ArrayList<String> nombres = listarMateriales();
		ArrayList<Material> materiales = new ArrayList<Material>();

		for (int i = 0; i < nombres.size(); i++)
		{
			materiales.add(cargarMaterial(nombres.get(i)));
		}

		return materiales;
	}

	public static ArrayList<Vehiculo> cargarTodosVehiculos()
	{
		ArrayList<String> nombres = listarVehiculos();
		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

		for (int i = 0; i < nombres.size(); i++)
		{
			vehiculos.add(cargarVehiculo(nombres.get(i)));
		}

		return vehiculos;
	}

	public static ArrayList<Cuenta> cargarTodosCuentas()
	{
		ArrayList<String> nombres = listarCuentas();
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

		for (int i = 0; i < nombres.size(); i++)
		{
			cuentas.add(cargarCuenta(nombres.get(i)));
		}

		return cuentas;
	}

	public static ArrayList<Cliente> cargarTodosClientes()
	{
		ArrayList<String> nombres = listarClientes();
		ArrayList<Cliente> materiales = new ArrayList<Cliente>();

		for (int i = 0; i < nombres.size(); i++)
		{
			materiales.add(cargarCliente(nombres.get(i)));
		}

		return materiales;
	}

	public static ArrayList<Primaria> cargarTodosPrimarias()
	{
		ArrayList<String> nombres = listarPrimarias();
		ArrayList<Primaria> primarias = new ArrayList<Primaria>();

		for (int i = 0; i < nombres.size(); i++)
		{
			primarias.add(cargarPrimaria(nombres.get(i)));
		}

		return primarias;
	}

	public static ArrayList<Pendiente> cargarTodosPendientes()
	{
		ArrayList<String> nombres = listarPendientes();
		ArrayList<Pendiente> pendientes = new ArrayList<Pendiente>();

		for (int i = 0; i < nombres.size(); i++)
		{
			pendientes.add(cargarPendiente(nombres.get(i)));
		}

		return pendientes;
	}
	public static ArrayList<Factura> cargarTodosFacturas()
	{
		ArrayList<String> nombres = listarFacturas();
		ArrayList<Factura> facturas = new ArrayList<Factura>();

		for (int i = 0; i < nombres.size(); i++)
		{
			facturas.add(cargarFactura(nombres.get(i)));
		}

		return facturas;
	}

	// ===== ajustes =====
	public static void guardarAjustes(Ajustes a)
	{
		Inicio.cuentaActual.setAjustes(a);
		guardarCuenta(Inicio.cuentaActual);
		Log.ajustes(Inicio.cuentaActual.getDNI());
	}

	public static void cargarAjustes()
	{
		Inicio.cuentaActual = new Cuenta(cargarCuenta(Inicio.cuentaActual.getDNI()));

		Inicio.fuente = Inicio.cuentaActual.getAjustes().getFuente();
		Inicio.fuenteObjetos = Inicio.cuentaActual.getAjustes().getFuenteObjetos();

		Inicio.colorFondo = Inicio.cuentaActual.getAjustes().getColorFondo();
		Inicio.colorFondoObjetos = Inicio.cuentaActual.getAjustes().getColorFondoObjetos();

		Inicio.colorFuente = Inicio.cuentaActual.getAjustes().getColorFuente();
		Inicio.colorFuenteObjetos = Inicio.cuentaActual.getAjustes().getColorFuenteObjetos();
	}
	
	public static void reiniciarAjustes()
	{
		Inicio.fuente = new Font("Segoe UI", Font.PLAIN, 13);
		Inicio.fuenteObjetos = new Font("Segoe UI", Font.BOLD, 13);

		Inicio.colorFondo = Color.DARK_GRAY;
		Inicio.colorFondoObjetos = Color.LIGHT_GRAY;
		Inicio.colorFuente = Color.WHITE;
		Inicio.colorFuenteObjetos = Color.BLACK;
	}
}