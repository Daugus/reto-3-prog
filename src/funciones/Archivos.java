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
import java.util.Comparator;

import javax.swing.JOptionPane;

import clases.Ajustes;
import clases.Cliente;
import clases.Cuenta;
import clases.Direccion;
import clases.Factura;
import clases.Fecha;
import clases.Material;
import clases.Pendiente;
import clases.Primaria;
import clases.Vehiculo;
import navegacion.Inicio;

public class Archivos {
	// ===== rutas =====
//	private static String raiz = "C:\\RKA\\";
	private static String raiz = "RKA\\";
//	private static String raiz = "\\\\SERVERG2\\Compartidas\\RKA\\";
//	private static String raiz = "Z:\\";

	private static String logs = raiz + "Logs\\";

	private static String materiales = raiz + "Material\\";
	private static String vehiculos = raiz + "Vehiculo\\";
	private static String clientes = raiz + "Cliente\\";
	private static String cuentas = raiz + "Cuenta\\";
	private static String facturas = raiz + "Factura\\";
	private static String primarias = raiz + "Primaria\\";
	private static String pendientes = raiz + "Pendiente\\";

	/**
	 * crear carpetas en caso de que no existan
	 */
	public static void crearCarpetas() {
		ArrayList<String> directorios = new ArrayList<String>();
		directorios
				.addAll(Arrays.asList(logs, materiales, vehiculos, clientes, cuentas, facturas, primarias, pendientes));

		File f;
		for (int i = 0; i < directorios.size(); i++) {
			f = new File(directorios.get(i));
			if (!f.exists()) {
				f.mkdirs();
			}
		}

		crearArchivosPrueba();
	}

	private static void crearArchivosPrueba() {
		if (new File(cuentas).listFiles().length == 0) {
			guardarCuenta(new Cuenta("mec", "mec", "mec", 123456789, "mec@rka.com", new Fecha(), new Direccion(), true,
					"mec", new Ajustes()));

			guardarCuenta(new Cuenta("atc", "atc", "atc", 123456789, "atc@rka.com", new Fecha(), new Direccion(), false,
					"atc", new Ajustes()));

			guardarMaterial(new Material("Tornillo", 1.2));
			guardarMaterial(new Material("Tuerca", 1.5));

			ArrayList<String> v = new ArrayList<String>();
			v.add("7878MDH");

			guardarCliente(new Cliente("X6578073G", "Leandro", "Moya", 734460685, "leandro_72@gmail.com",
					new Fecha(14, 2, 1972), new Direccion(45787, "Carretera Catalunya", 45, 2, "Derecha"), new Fecha(),
					v));
			v.clear();
			v.add("2988BJB");
			guardarCliente(new Cliente("93477368D", "Aroa", "Cantero Barreiro", 721494366, "aroa_57@gmail.com",
					new Fecha(30, 6, 1957), new Direccion(44772, "Passeig Horno", 11, 3, "d"), new Fecha(), v));

			guardarVehiculo(new Vehiculo("7878MDH", "3D7MU436138104574", "X6578073G", "Toyota", "Corolla", "Naranja",
					2000, 3612, new Fecha(2022), "Gasolina"));
			guardarVehiculo(new Vehiculo("2988BJB", "1FTMX1R69B6928185", "93477368D", "Tesla", "Model S", "Amarillo", 0,
					66117, new Fecha(2020), "Eléctrico"));

			JOptionPane.showOptionDialog(null, (String) "Se han creado objetos por defecto para pruebas", "INFO",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "OK" },
					"OK");

		}
	}

	private static void guardar(Object o, File f) {
		FileOutputStream fos;
		ObjectOutputStream oos;

		try {
			fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);

			oos.writeObject(o);

			oos.close();
			fos.close();
		} catch (IOException e) {
			Log.error("error al guardar el objeto");
		}
	}

	public static void guardarMaterial(Material m) {
		File f = new File(materiales + m.getNombre() + ".dat");
		Log.material(m.getNombre());
		guardar(m, f);
	}

	public static void guardarVehiculo(Vehiculo v) {
		File f = new File(vehiculos + v.getMatricula() + ".dat");
		Log.vehiculo(v.getMatricula());
		guardar(v, f);
	}

	public static void guardarCliente(Cliente c) {
		c.getVehiculos().sort(Comparator.naturalOrder());
		File f = new File(clientes + c.getDNI() + ".dat");
		Log.cliente(c.getDNI());
		guardar(c, f);
	}

	public static void guardarCuenta(Cuenta c) {
		File f = new File(cuentas + c.getDNI() + ".dat");
		Log.cuenta(c.getDNI());
		guardar(c, f);
	}

	public static void guardarPrimaria(Primaria p) {
		File f = new File(primarias + p.getCodigo() + ".dat");
		Log.primaria(p.getCodigo());
		guardar(p, f);
	}

	public static void guardarPendiente(Pendiente p) {
		File f = new File(pendientes + p.getCodigo() + ".dat");
		Log.pendiente(p.getCodigo());
		guardar(p, f);
	}

	public static void guardarFactura(Factura fa) {
		File f = new File(facturas + fa.getCodigo() + ".dat");
		Log.factura(fa.getCodigo());
		guardar(fa, f);
	}

	private static Object cargar(File f) {
		Object o = null;

		FileInputStream fis;
		ObjectInputStream ois;

		try {
			fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);

			o = ois.readObject();

			ois.close();
			fis.close();
		} catch (IOException e) {
			Log.error("no se han cargado los datos");
		} catch (ClassNotFoundException e) {
			Log.error("no se ha encontrado la clase espcificada");
		}

		return o;
	}

	public static Material cargarMaterial(String nombre) {
		File f = new File(materiales + nombre + ".dat");
		return (Material) cargar(f);
	}

	public static Vehiculo cargarVehiculo(String matricula) {
		File f = new File(vehiculos + matricula + ".dat");
		return (Vehiculo) cargar(f);
	}

	public static Cliente cargarCliente(String DNI) {
		File f = new File(clientes + DNI + ".dat");
		return (Cliente) cargar(f);
	}

	public static Cuenta cargarCuenta(String DNI) {
		File f = new File(cuentas + DNI + ".dat");
		return (Cuenta) cargar(f);
	}

	public static Primaria cargarPrimaria(String cod) {
		File f = new File(primarias + cod + ".dat");
		return (Primaria) cargar(f);
	}

	public static Pendiente cargarPendiente(String cod) {
		File f = new File(pendientes + cod + ".dat");
		return (Pendiente) cargar(f);
	}

	public static Factura cargarFactura(String cod) {
		File f = new File(facturas + cod + ".dat");
		return (Factura) cargar(f);
	}

	// ===== borrar archivos =====
	public static void borrarPrimaria(String cod) {
		File f = new File(primarias + cod + ".dat");
		if (f.delete()) {
			Log.borrarPrimaria(cod);
		} else {
			Log.error("error al borrar la orden primaria " + cod);
		}
	}

	public static void borrarPendiente(String cod) {
		File f = new File(pendientes + cod + ".dat");
		if (f.delete()) {
			Log.borrarPendiente(cod);
		} else {
			Log.error("error al borrar la orden pendiente " + cod);
		}
	}

	// ===== listar archivos =====
	private static ArrayList<String> listar(File[] listaOriginal) {
		ArrayList<String> lista = new ArrayList<String>();
		for (int i = 0; i < listaOriginal.length; i++) {
			if (listaOriginal[i].isFile()) {
				String nombre = listaOriginal[i].getName().replaceAll(".dat", "");
				lista.add(nombre);
			}
		}

		return lista;
	}

	// --- individuales --
	public static ArrayList<String> listarMateriales() {
		File[] listaOriginal = new File(materiales).listFiles();
		return listar(listaOriginal);
	}

	public static ArrayList<String> listarVehiculos() {
		File[] listaOriginal = new File(vehiculos).listFiles();
		return listar(listaOriginal);
	}

	public static ArrayList<String> listarClientes() {
		File[] listaOriginal = new File(clientes).listFiles();
		return listar(listaOriginal);
	}

	public static ArrayList<String> listarCuentas() {
		File[] listaOriginal = new File(cuentas).listFiles();
		return listar(listaOriginal);
	}

	public static ArrayList<String> listarPrimarias() {
		File[] listaOriginal = new File(primarias).listFiles();
		return listar(listaOriginal);
	}

	public static ArrayList<String> listarPendientes() {
		File[] listaOriginal = new File(pendientes).listFiles();
		return listar(listaOriginal);
	}

	public static ArrayList<String> listarFacturas() {
		File[] listaOriginal = new File(facturas).listFiles();
		return listar(listaOriginal);
	}

	// ===== cargar todos =====
	public static ArrayList<Material> cargarTodosMateriales() {
		ArrayList<String> nombres = listarMateriales();
		ArrayList<Material> materiales = new ArrayList<Material>();

		for (int i = 0; i < nombres.size(); i++) {
			materiales.add(cargarMaterial(nombres.get(i)));
		}

		return materiales;
	}

	public static ArrayList<Vehiculo> cargarTodosVehiculos() {
		ArrayList<String> nombres = listarVehiculos();
		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

		for (int i = 0; i < nombres.size(); i++) {
			vehiculos.add(cargarVehiculo(nombres.get(i)));
		}

		return vehiculos;
	}

	public static ArrayList<Cuenta> cargarTodosCuentas() {
		ArrayList<String> nombres = listarCuentas();
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

		for (int i = 0; i < nombres.size(); i++) {
			cuentas.add(cargarCuenta(nombres.get(i)));
		}

		return cuentas;
	}

	public static ArrayList<Cliente> cargarTodosClientes() {
		ArrayList<String> nombres = listarClientes();
		ArrayList<Cliente> materiales = new ArrayList<Cliente>();

		for (int i = 0; i < nombres.size(); i++) {
			materiales.add(cargarCliente(nombres.get(i)));
		}

		return materiales;
	}

	public static ArrayList<Primaria> cargarTodosPrimarias() {
		ArrayList<String> nombres = listarPrimarias();
		ArrayList<Primaria> primarias = new ArrayList<Primaria>();

		for (int i = 0; i < nombres.size(); i++) {
			primarias.add(cargarPrimaria(nombres.get(i)));
		}

		return primarias;
	}

	public static ArrayList<Pendiente> cargarTodosPendientes() {
		ArrayList<String> nombres = listarPendientes();
		ArrayList<Pendiente> pendientes = new ArrayList<Pendiente>();

		for (int i = 0; i < nombres.size(); i++) {
			pendientes.add(cargarPendiente(nombres.get(i)));
		}

		return pendientes;
	}

	public static ArrayList<Factura> cargarTodosFacturas() {
		ArrayList<String> nombres = listarFacturas();
		ArrayList<Factura> facturas = new ArrayList<Factura>();

		for (int i = 0; i < nombres.size(); i++) {
			facturas.add(cargarFactura(nombres.get(i)));
		}

		return facturas;
	}

	// ===== ajustes =====
	public static void guardarAjustes(Ajustes a) {
		Inicio.cuentaActual.setAjustes(a);
		guardarCuenta(Inicio.cuentaActual);
		Log.ajustes(Inicio.cuentaActual.getDNI());
	}

	public static void cargarAjustes() {
		Inicio.cuentaActual = new Cuenta(cargarCuenta(Inicio.cuentaActual.getDNI()));

		Inicio.fuente = Inicio.cuentaActual.getAjustes().getFuente();
		Inicio.fuenteObjetos = Inicio.cuentaActual.getAjustes().getFuenteObjetos();

		Inicio.colorFondo = Inicio.cuentaActual.getAjustes().getColorFondo();
		Inicio.colorFondoObjetos = Inicio.cuentaActual.getAjustes().getColorFondoObjetos();

		Inicio.colorFuente = Inicio.cuentaActual.getAjustes().getColorFuente();
		Inicio.colorFuenteObjetos = Inicio.cuentaActual.getAjustes().getColorFuenteObjetos();
	}

	public static void reiniciarAjustes() {
		Inicio.fuente = new Font("Segoe UI", Font.PLAIN, 13);
		Inicio.fuenteObjetos = new Font("Segoe UI", Font.BOLD, 13);

		Inicio.colorFondo = Color.DARK_GRAY;
		Inicio.colorFondoObjetos = Color.LIGHT_GRAY;
		Inicio.colorFuente = Color.WHITE;
		Inicio.colorFuenteObjetos = Color.BLACK;
	}
}