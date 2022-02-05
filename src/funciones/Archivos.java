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

import clases.*;
import navegacion.Inicio;

public class Archivos {
	// ===== rutas =====
	public static String raiz = "C:\\RKA\\";

	public static String logs = raiz + "Logs\\";

	public static String materiales = raiz + "Material\\";
	public static String vehiculos = raiz + "Vehiculo\\";
	public static String clientes = raiz + "Cliente\\";
	public static String cuentas = raiz + "Cuenta\\";
	public static String facturas = raiz + "Factura\\";
	public static String primarias = raiz + "OrdenPrim\\";
	public static String pendientes = raiz + "OrdenPend\\";
	
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
	public static void guardar(Object o, File f)
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
		} catch (IOException e)
		{
			System.out.println("error guardado");
		}
	}

	// --- individuales ---
	public static void guardarMaterial(Material m)
	{
		File f = new File(materiales + m.getNombre() + ".dat");
		guardar(m, f);
	}

	public static void guardarVehiculo(Vehiculo v)
	{
		File f = new File(vehiculos + v.getMatricula() + ".dat");
		Cliente c = cargarCliente(v.getPropietario());
		// TODO comprobar si existe
		c.getVehiculos().add(v.getMatricula());
		
		guardar(v, f);
		guardarCliente(c);
	}

	public static void guardarCliente(Cliente c)
	{
		File f = new File(clientes + c.getDNI() + ".dat");
		guardar(c, f);
	}

	public static void guardarCuenta(Cuenta c)
	{
		File f = new File(cuentas + c.getDNI() + ".dat");
		guardar(c, f);
	}

	public static void guardarOrdenPrim(OrdenPrim op)
	{
		File f = new File(primarias + op.getCodigo() + ".dat");
		guardar(op, f);
	}

	public static void guardarOrdenPend(OrdenPend op)
	{
		File f = new File(pendientes + op.getCodigo() + ".dat");
		guardar(op, f);
	}

	public static void guardarFactura(Factura fa)
	{
		File f = new File(facturas + fa.getCodigo() + ".dat");
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
		} catch (IOException e)
		{
			//System.out.println("error carga");
			Log.error("no se han cargado los datos");
		} catch (ClassNotFoundException e)
		{
			//System.out.println("error carga - clase");
			Log.error("error carga - clase");
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

	public static OrdenPrim cargarOrdenPrim(String cod)
	{
		File f = new File(primarias + cod + ".dat");
		return (OrdenPrim) cargar(f);
	}
	
	public static OrdenPend cargarOrdenPend(String cod)
	{
		File f = new File(pendientes + cod + ".dat");
		return (OrdenPend) cargar(f);
	}
	
	public static Factura cargarFactura(String cod)
	{
		File f = new File(facturas + cod + ".dat");
		return (Factura) cargar(f);
	}
	
	// ===== borrar archivos =====
	public static void borrarOrdenPrim(String cod)
	{
		File f = new File(primarias + cod + ".dat");
		if (f.delete())
		{
			Log.orden("Se ha borrado la orden " + cod);
		}
		else
		{
			Log.error("Error al borrar la orden primaria " + cod);
		}
	}
	
	public static void BorrarOrdenPend(String cod)
	{
		File f = new File(pendientes + cod + ".dat");
		if (f.delete())
		{
			Log.orden("Se ha borrado la orden " + cod);
		}
		else
		{
			Log.error("Error al borrar la orden pendiente " + cod);
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

	public static ArrayList<String> listarOrdenPrim()
	{
		File[] listaOriginal = new File(primarias).listFiles();
		return listar(listaOriginal);
	}
	
	public static ArrayList<String> listarOrdenPend()
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
	// --- individuales ---
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

	public static ArrayList<OrdenPrim> cargarTodosOrdenPrim()
	{
		ArrayList<String> nombres = listarOrdenPrim();
		ArrayList<OrdenPrim> primarias = new ArrayList<OrdenPrim>();

		for (int i = 0; i < nombres.size(); i++)
		{
			primarias.add(cargarOrdenPrim(nombres.get(i)));
		}

		return primarias;
	}

	public static ArrayList<OrdenPend> cargarTodosOrdenPend()
	{
		ArrayList<String> nombres = listarOrdenPend();
		ArrayList<OrdenPend> pendientes = new ArrayList<OrdenPend>();

		for (int i = 0; i < nombres.size(); i++)
		{
			pendientes.add(cargarOrdenPend(nombres.get(i)));
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