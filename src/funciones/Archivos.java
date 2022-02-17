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

/**
 * esta clase difiene la manera de crear carpetas y archivos de manera ordenada
 * 
 * @author Grupo 2
 * @version 2.0.1
 */
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
	 * crear carpetas en caso de que no existan lleva un arr
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


	private static void crearArchivosPrueba()
	{
		if (new File(cuentas).listFiles().length == 0)
		{
			guardarCuenta(new Cuenta("mec", "mec", "mec", 123456789,
					"mec@rka.com", new Fecha(), new Direccion(),
					true, "mec", new Ajustes()));

			guardarCuenta(new Cuenta("atc", "atc", "atc", 123456789,
					"atc@rka.com", new Fecha(), new Direccion(),
					false, "atc", new Ajustes()));

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


			guardarVehiculo(new Vehiculo("7878MDH", "3D7MU436138104574", "X6578073G",
					"Toyota", "Corolla", "Naranja", 2000, 3612, new Fecha(2022), "Gasolina"));
			guardarVehiculo(new Vehiculo("2988BJB", "1FTMX1R69B6928185", "93477368D",
					"Tesla", "Model S", "Amarillo", 0, 66117, new Fecha(2020), "Eléctrico"));

			JOptionPane.showOptionDialog(null, (String) "Se han creado objetos por defecto para pruebas", "INFO",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
					new Object[] {"OK"}, "OK");

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

	/**
	 * metodo que guarda un material pasado como parametro
	 * 
	 * @param m material a guardar
	 * @see guardar(o,f)
	 */
	public static void guardarMaterial(Material m) {
		File f = new File(materiales + m.getNombre() + ".dat");
		Log.material(m.getNombre());
		guardar(m, f);
	}

	/**
	 * metodo que guarda un vehiculo pasado como parametro
	 * 
	 * @param v vehiculo a guardar
	 * @see guardar(o,f)
	 */
	public static void guardarVehiculo(Vehiculo v) {
		File f = new File(vehiculos + v.getMatricula() + ".dat");
		Log.vehiculo(v.getMatricula());
		guardar(v, f);
	}

	/**
	 * metodo que guarda un cliente pasado como parametro
	 * 
	 * @param c cliente a guardar
	 * @see guardar(o,f)
	 */
	public static void guardarCliente(Cliente c) {
		c.getVehiculos().sort(Comparator.naturalOrder());
		File f = new File(clientes + c.getDNI() + ".dat");
		Log.cliente(c.getDNI());
		guardar(c, f);
	}

	/**
	 * metodo que guarda un cuenta pasado como parametro
	 * 
	 * @param c cuenta a guardar
	 * @see guardar(o,f)
	 */
	public static void guardarCuenta(Cuenta c) {
		File f = new File(cuentas + c.getDNI() + ".dat");
		Log.cuenta(c.getDNI());
		guardar(c, f);
	}

	/**
	 * metodo que guarda una orden primaria pasado como parametro
	 * 
	 * @param p orden primaria a guardar
	 * @see guardar(o,f)
	 */
	public static void guardarPrimaria(Primaria p) {
		File f = new File(primarias + p.getCodigo() + ".dat");
		Log.primaria(p.getCodigo());
		guardar(p, f);
	}

	/**
	 * metodo que guarda una orden pendiente pasado como parametro
	 * 
	 * @param p orden pendiente a guardar
	 * @see guardar(o,f)
	 */
	public static void guardarPendiente(Pendiente p) {
		File f = new File(pendientes + p.getCodigo() + ".dat");
		Log.pendiente(p.getCodigo());
		guardar(p, f);
	}

	/**
	 * metodo que guarda factura pasado como parametro
	 * 
	 * @param fa factura a guardar
	 * @see guardar(o,f)
	 */
	public static void guardarFactura(Factura fa) {
		File f = new File(facturas + fa.getCodigo() + ".dat");
		Log.factura(fa.getCodigo());
		guardar(fa, f);
	}

	/**
	 * carga datos pasando como parametro un archivo
	 * 
	 * @param f archivo a leer
	 * @return devuelve un objeto
	 * @exception Log.error() error si no han cargado los datos
	 * @exception Log.error   error si no se encuentra la clase espcificada
	 */
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

	/**
	 * carga los datos que se encuentan en archivo pasadole el nombre de un material
	 * 
	 * @param nombre del material a cargar
	 * @return devuelve los datos que se encuentran en el archivo
	 * @see cargar
	 */
	public static Material cargarMaterial(String nombre) {
		File f = new File(materiales + nombre + ".dat");
		return (Material) cargar(f);
	}

	/**
	 * carga los datos que se encuentan en archivo pasandole la matricula como
	 * parametro
	 * 
	 * @param matricula a cargar
	 * @return devuelve los datos que se encuentran en el archivo
	 * @see cargar(f)
	 */
	public static Vehiculo cargarVehiculo(String matricula) {
		File f = new File(vehiculos + matricula + ".dat");
		return (Vehiculo) cargar(f);
	}

	/**
	 * carga los datos que se encuentan en archivo pasandole el dni como parametro
	 * 
	 * @param DNI a cargar
	 * @return devuelve los datos que se encuentran en el archivo
	 * @see cargar(f)
	 */
	public static Cliente cargarCliente(String DNI) {
		File f = new File(clientes + DNI + ".dat");
		return (Cliente) cargar(f);
	}

	/**
	 * carga los datos que se encuentan en archivo pasandole la matricula como
	 * parametro
	 * 
	 * @param DNI a cargar
	 * @return devuelve los datos que se encuentran en el archivo
	 * @see cargar(f)
	 */
	public static Cuenta cargarCuenta(String DNI) {
		File f = new File(cuentas + DNI + ".dat");
		return (Cuenta) cargar(f);
	}

	/**
	 * carga los datos que se encuentan en archivo pasandole numero de orden
	 * primaria como parametro
	 * 
	 * @param cod numero de orden a cargar
	 * @return devuelve los datos que se encuentran en el archivo
	 * @see cargar(f)
	 */
	public static Primaria cargarPrimaria(String cod) {
		File f = new File(primarias + cod + ".dat");
		return (Primaria) cargar(f);
	}

	/**
	 * carga los datos que se encuentan en archivo pasandole numero de orden
	 * pendiente como parametro
	 * 
	 * @param cod de orden a cargar
	 * @return devuelve los datos que se encuentran en el archivo
	 * @see cargar(f)
	 */
	public static Pendiente cargarPendiente(String cod) {
		File f = new File(pendientes + cod + ".dat");
		return (Pendiente) cargar(f);
	}

	/**
	 * carga los datos que se encuentan en archivo pasandole numero de factura como
	 * parametro
	 * 
	 * @param cod de la factura a cargar
	 * @return devuelve los datos que se encuentran en el archivo
	 * @see cargar(f)
	 */
	public static Factura cargarFactura(String cod) {
		File f = new File(facturas + cod + ".dat");
		return (Factura) cargar(f);
	}

	// ===== borrar archivos =====
	/**
	 * borra una orden pasandole cod de la orden como parametro
	 * 
	 * @param cod de la orden primaaria
	 * @exception Log.error si no se encuera la orden primaria
	 */
	public static void borrarPrimaria(String cod) {
		File f = new File(primarias + cod + ".dat");
		if (f.delete()) {
			Log.borrarPrimaria(cod);
		} else {
			Log.error("error al borrar la orden primaria " + cod);
		}
	}

	/**
	 * borra una orden pasandole cod de la orden pendiente como parametro
	 * 
	 * @param cod de la orden pendiente
	 * @exception Log.error si no se encuera la orden pendiente
	 */
	public static void borrarPendiente(String cod) {
		File f = new File(pendientes + cod + ".dat");
		if (f.delete()) {
			Log.borrarPendiente(cod);
		} else {
			Log.error("error al borrar la orden pendiente " + cod);
		}
	}

	// ===== listar archivos =====
	/**
	 * recorre aray si se encuentra archivo .dat se remplaza por ""
	 * 
	 * @param listaOriginal contine archivos con extension .dat
	 * @return devuelve la lista con solo nombres sin extension
	 */
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
	/**
	 * recorre aray materiales si se encuentra archivo .dat se remplaza por ""
	 * 
	 * @return devuelve lista de materiales sin extension .dat
	 * @see Listar(listaOrginal)
	 */
	public static ArrayList<String> listarMateriales() {
		File[] listaOriginal = new File(materiales).listFiles();
		return listar(listaOriginal);
	}

	/**
	 * recorre aray vehiculos si se encuentra archivo .dat se remplaza por ""
	 * 
	 * @return devuelve lista de vehiculos sin extension .dat
	 * @see Listar(listaOrginal)
	 */
	public static ArrayList<String> listarVehiculos() {
		File[] listaOriginal = new File(vehiculos).listFiles();
		return listar(listaOriginal);
	}

	/**
	 * recorre aray clientes si se encuentra archivo .dat se remplaza por ""
	 * 
	 * @return devuelve lista de clientes sin extension .dat
	 * @see Listar(listaOrginal)
	 */
	public static ArrayList<String> listarClientes() {
		File[] listaOriginal = new File(clientes).listFiles();
		return listar(listaOriginal);
	}

	/**
	 * recorre aray cuentas si se encuentra archivo .dat se remplaza por ""
	 * 
	 * @return devuelve lista de cuentas sin extension .dat
	 * @see Listar(listaOrginal)
	 */
	public static ArrayList<String> listarCuentas() {
		File[] listaOriginal = new File(cuentas).listFiles();
		return listar(listaOriginal);
	}

	/**
	 * recorre aray ordenes primarias si se encuentra archivo .dat se remplaza por
	 * ""
	 * 
	 * @return devuelve lista de ordenes primarias sin extension .dat
	 * @see Listar(listaOrginal)
	 */
	public static ArrayList<String> listarPrimarias() {
		File[] listaOriginal = new File(primarias).listFiles();
		return listar(listaOriginal);
	}

	/**
	 * recorre aray ordenes pendientes si se encuentra archivo .dat se remplaza por
	 * ""
	 * 
	 * @return devuelve lista de ordenes pendientes sin extension .dat
	 * @see Listar(listaOrginal)
	 */
	public static ArrayList<String> listarPendientes() {
		File[] listaOriginal = new File(pendientes).listFiles();
		return listar(listaOriginal);
	}

	/**
	 * recorre aray facturas si se encuentra archivo .dat se remplaza por ""
	 * 
	 * @return devuelve lista de facturas sin extension .dat
	 * @see Listar(listaOrginal)
	 */
	public static ArrayList<String> listarFacturas() {
		File[] listaOriginal = new File(facturas).listFiles();
		return listar(listaOriginal);
	}

	/**
	 * carga los materiales que se encuentran en el array de material
	 * 
	 * @return devuelve lista de materiales sin extersion .dat
	 * @see cargarMaterial
	 */
	// ===== cargar todos =====
	public static ArrayList<Material> cargarTodosMateriales() {
		ArrayList<String> nombres = listarMateriales();
		ArrayList<Material> materiales = new ArrayList<Material>();

		for (int i = 0; i < nombres.size(); i++) {
			materiales.add(cargarMaterial(nombres.get(i)));
		}

		return materiales;
	}

	/**
	 * carga los vehiculos que se encuentran el array de vehiculos
	 * 
	 * @return devuelve lista de vehiculos sin extersion .dat
	 * @see cargarVehiculos
	 */
	public static ArrayList<Vehiculo> cargarTodosVehiculos() {
		ArrayList<String> nombres = listarVehiculos();
		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

		for (int i = 0; i < nombres.size(); i++) {
			vehiculos.add(cargarVehiculo(nombres.get(i)));
		}

		return vehiculos;
	}

	/**
	 * carga las cuentas que se encuentran el array de cuentas
	 * 
	 * @return devuelve lista de cuentas sin extersion .dat
	 * @see cargarCuentas
	 */
	public static ArrayList<Cuenta> cargarTodosCuentas() {
		ArrayList<String> nombres = listarCuentas();
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

		for (int i = 0; i < nombres.size(); i++) {
			cuentas.add(cargarCuenta(nombres.get(i)));
		}

		return cuentas;
	}

	/**
	 * carga los clientes que se encuentran el array de Cliente
	 * 
	 * @return devuelve lista de clientes sin extersion .dat
	 * @see cargarCliente
	 */
	public static ArrayList<Cliente> cargarTodosClientes() {
		ArrayList<String> nombres = listarClientes();
		ArrayList<Cliente> materiales = new ArrayList<Cliente>();

		for (int i = 0; i < nombres.size(); i++) {
			materiales.add(cargarCliente(nombres.get(i)));
		}

		return materiales;
	}

	/**
	 * carga las ordenes primarias que se encuentran el array de primarias
	 * 
	 * @return devuelve lista de ordenes primarias sin extersion .dat
	 * @see cargarPrimaria
	 */
	public static ArrayList<Primaria> cargarTodosPrimarias() {
		ArrayList<String> nombres = listarPrimarias();
		ArrayList<Primaria> primarias = new ArrayList<Primaria>();

		for (int i = 0; i < nombres.size(); i++) {
			primarias.add(cargarPrimaria(nombres.get(i)));
		}

		return primarias;
	}

	/**
	 * carga las oedenes pendienes que se encuentran el array de pendienes
	 * 
	 * @return devuelve lista de pendientes sin extersion .dat
	 * @see cargarPendientes
	 */
	public static ArrayList<Pendiente> cargarTodosPendientes() {
		ArrayList<String> nombres = listarPendientes();
		ArrayList<Pendiente> pendientes = new ArrayList<Pendiente>();

		for (int i = 0; i < nombres.size(); i++) {
			pendientes.add(cargarPendiente(nombres.get(i)));
		}

		return pendientes;
	}

	/**
	 * carga las facturas que se encuentran el array de facturas
	 * 
	 * @return devuelve lista de facturas sin extersion .dat
	 * @see cargarFacturas
	 */
	public static ArrayList<Factura> cargarTodosFacturas() {
		ArrayList<String> nombres = listarFacturas();
		ArrayList<Factura> facturas = new ArrayList<Factura>();

		for (int i = 0; i < nombres.size(); i++) {
			facturas.add(cargarFactura(nombres.get(i)));
		}

		return facturas;
	}

	// ===== ajustes =====
	/**
	 * guarda ajustes pasandole un objeto
	 * 
	 * @param a objeto ajustes
	 * @see Ajustes
	 */
	public static void guardarAjustes(Ajustes a) {
		Inicio.cuentaActual.setAjustes(a);
		guardarCuenta(Inicio.cuentaActual);
		Log.ajustes(Inicio.cuentaActual.getDNI());
	}

	/**
	 * carga los ajustes establicidos llamando a la cuenta configurada
	 * 
	 * @see Ajustes
	 */
	public static void cargarAjustes() {
		Inicio.cuentaActual = new Cuenta(cargarCuenta(Inicio.cuentaActual.getDNI()));

		Inicio.fuente = Inicio.cuentaActual.getAjustes().getFuente();
		Inicio.fuenteObjetos = Inicio.cuentaActual.getAjustes().getFuenteObjetos();

		Inicio.colorFondo = Inicio.cuentaActual.getAjustes().getColorFondo();
		Inicio.colorFondoObjetos = Inicio.cuentaActual.getAjustes().getColorFondoObjetos();

		Inicio.colorFuente = Inicio.cuentaActual.getAjustes().getColorFuente();
		Inicio.colorFuenteObjetos = Inicio.cuentaActual.getAjustes().getColorFuenteObjetos();
	}

	/**
	 * reinicia los ajustes establiciendo tema, fuente y color por defecto
	 * 
	 * @see Ajustes
	 */
	public static void reiniciarAjustes() {
		Inicio.fuente = new Font("Segoe UI", Font.PLAIN, 13);
		Inicio.fuenteObjetos = new Font("Segoe UI", Font.BOLD, 13);

		Inicio.colorFondo = Color.DARK_GRAY;
		Inicio.colorFondoObjetos = Color.LIGHT_GRAY;
		Inicio.colorFuente = Color.WHITE;
		Inicio.colorFuenteObjetos = Color.BLACK;
	}
}