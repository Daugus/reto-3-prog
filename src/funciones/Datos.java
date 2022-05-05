package funciones;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import clases.Ajustes;
import clases.Cuenta;
import clases.Factura;
import clases.Fecha;
import clases.Material;
import clases.Orden;
import clases.Vehiculo;
import navegacion.Inicio;

public class Datos {
	// ===== rutas =====
	private static String raiz = "C:\\RKA\\";

	private static String logs = raiz + "Logs\\";
	private static String ajustes = raiz + "Ajustes\\";

	private static String ruta = "jdbc:mysql://192.168.220.220/reto3";
	private static String usr = "g1";
	private static String pass = "Inf662";

	// ===== crear carpetas en caso de que no existan =====
	public static void crearCarpetas() {
		ArrayList<String> directorios = new ArrayList<String>();
		directorios.addAll(Arrays.asList(logs, ajustes));

		File f;
		for (int i = 0; i < directorios.size(); i++) {
			f = new File(directorios.get(i));
			if (!f.exists()) {
				f.mkdirs();
			}
		}
	}

	// ===== guardar =====
	public static void guardarCuenta(Cuenta c, boolean edicion) {
		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);
			Statement st = conexion.createStatement();

			String tipo = c.getTipo().replaceAll("Mecánico", "Mecanico");
			String estado = General.estadoAString(c.isActivo());

			String sentencia;

			if (edicion) {
				sentencia = String.format(Locale.US,
						"update reto3.empleado set nombre = '%s', apellidos = '%s', telefono = '%s',"
								+ " email = '%s', direccion = '%s', dniJefe = NULLIF('%s', ''), password = '%s',"
								+ " salBase = %.2f, comision = %.2f, fecNac = '%s', tipoEmpleado = '%s',"
								+ " fecAltaContrato = '%s', estado = '%s' where dniEmple like '%s';",
						c.getNombre(), c.getApellidos(), c.getTelefono(), c.getEmail(), c.getDireccion(),
						c.getDniJefe(), c.getPassword(), c.getSalario(), c.getComision(),
						c.getFechaNacimiento().toSQLDate(), tipo, c.getFechaAlta().toSQLDate(), estado, c.getDNI());
			} else {
				sentencia = String.format(Locale.US, "insert into reto3.empleado"
						+ " (dniEmple, nombre, apellidos, telefono, email, direccion, dniJefe, password,"
						+ " salBase, comision, fecNac, tipoEmpleado, fecAltaContrato, estado)"
						+ " values('%s', '%s', '%s', '%s', '%s', '%s', NULLIF('%s', ''), '%s', %.2f, %.2f, '%s', '%s', '%s', '%s');",
						c.getDNI(), c.getNombre(), c.getApellidos(), c.getTelefono(), c.getEmail(), c.getDireccion(),
						c.getDniJefe(), c.getPassword(), c.getSalario(), c.getComision(),
						c.getFechaNacimiento().toSQLDate(), tipo, c.getFechaAlta().toSQLDate(), estado);
			}

			st.executeUpdate(sentencia);

			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}
	}

	public static void guardarMaterial(Material m, boolean edicion) {
		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);
			Statement st = conexion.createStatement();

			String estado = General.estadoAString(m.isActivo());

			String sentencia;

			if (edicion) {
				sentencia = String.format(Locale.US,
						"update reto3.pieza set marca = '%s', nombre = '%s', stock = %d, pvp = %.2f, precioCompra = %.2f, estado = '%s' where idPieza like '%s';",
						m.getMarca(), m.getNombre(), m.getStock(), m.getPVP(), m.getPrecioCompra(), estado, m.getID());
			} else {
				sentencia = String.format(Locale.US,
						"insert into reto3.pieza values('%s', '%s', '%s', %d, %.2f, %.2f, '%s');", m.getID(),
						m.getMarca(), m.getNombre(), m.getStock(), m.getPVP(), m.getPrecioCompra(), estado);
			}

			st.executeUpdate(sentencia);

			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}
	}

	public static void guardarVehiculo(Vehiculo v, boolean edicion) {
		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);
			Statement st = conexion.createStatement();

			String estado = General.estadoAString(v.isActivo());

			String sentencia;

			if (edicion) {
				sentencia = String.format(
						"update reto3.vehiculo" + " set dniCliente = '%s', estado = '%s' where matricula like '%s';",
						v.getPropietario(), estado, v.getMatricula());
			} else {
				sentencia = String.format(
						"insert into reto3.vehiculo values('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
						v.getMatricula(), v.getPropietario(), estado, v.getBastidor(), v.getMarca(), v.getModelo(),
						v.getTipo(), v.getFechaFabricacion().toSQLDate());
			}

			st.executeUpdate(sentencia);

			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}
	}

	// ===== cargar =====
	public static Cuenta cargarCuenta(String dni) {
		Cuenta c = null;
		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);

			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(String.format("select * from reto3.empleado where dniEmple like '%s'", dni));

			if (rs.next()) {
				boolean activo = General.estadoABoolean(rs.getString("estado"));

				c = new Cuenta(rs.getString("dniEmple"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("telefono"), rs.getString("email"), rs.getString("direccion"),
						cargarAjustes(rs.getString("dniEmple"), false), rs.getString("dniJefe"),
						rs.getString("password"), rs.getDouble("salBase"), rs.getDouble("comision"),
						new Fecha(rs.getString("fecNac")), rs.getString("tipoEmpleado"),
						new Fecha(rs.getString("fecAltaContrato")), activo);
			}

			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}

		return c;
	}

	// ===== borrar =====
//	public static void borrarPrimaria(String cod) {
//		File f = new File(primarias + cod + ".dat");
//		if (f.delete()) {
//			Log.borrarPrimaria(cod);
//		} else {
//			Log.error("error al borrar la orden primaria " + cod);
//		}
//	}

	// ===== listar =====
	public static ArrayList<String> listarClientes() {
		ArrayList<String> alDNIs = new ArrayList<String>();

		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);

			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("select dniCliente from reto3.cliente where estado = 'activo'");

			while (rs.next()) {
				alDNIs.add(rs.getString("dniCliente"));
			}

			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}

		return alDNIs;
	}

	// ===== cargar todos =====
	public static ArrayList<Cuenta> cargarTodosCuentas() {
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);

			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("select * from reto3.empleado");

			while (rs.next()) {
				Ajustes ajustes = cargarAjustes(rs.getString("dniEmple"), true);
				String tipo = rs.getString("tipoEmpleado").replaceAll("Mecanico", "Mecánico");
				boolean activo = General.estadoABoolean(rs.getString("estado"));

				cuentas.add(new Cuenta(rs.getString("dniEmple"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("telefono"), rs.getString("email"), rs.getString("direccion"), ajustes,
						rs.getString("dniJefe"), rs.getString("password"), rs.getDouble("salBase"),
						rs.getDouble("comision"), new Fecha(rs.getString("fecNac")), tipo,
						new Fecha(rs.getString("fecAltaContrato")), activo));
			}

			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}

		return cuentas;
	}

	public static ArrayList<Factura> cargarTodosFacturas() {
		ArrayList<Factura> facturas = new ArrayList<Factura>();

		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);

			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("select * from reto3.factura");

			while (rs.next()) {
				boolean pagada = General.pagadaABoolean(rs.getString("pagada"));

				facturas.add(new Factura(rs.getString("idFactura"), rs.getString("idOrden"), rs.getString("metodoPago"),
						pagada, rs.getInt("descuento"), new Fecha(rs.getString("fecha"))));
			}

			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}

		return facturas;
	}

	public static ArrayList<Material> cargarTodosMateriales() {
		ArrayList<Material> materiales = new ArrayList<Material>();

		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);

			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("select * from reto3.pieza");

			while (rs.next()) {
				boolean activo = General.estadoABoolean(rs.getString("estado"));

				materiales.add(new Material(rs.getString("idPieza"), rs.getString("marca"), rs.getString("nombre"),
						rs.getInt("stock"), rs.getDouble("pvp"), rs.getDouble("precioCompra"), activo));
			}

			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}

		return materiales;
	}

	public static ArrayList<Orden> cargarTodosOrdenes() {
		ArrayList<Orden> materiales = new ArrayList<Orden>();

		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);

			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("select * from reto3.ordenTrabajo");

			while (rs.next()) {
				if (rs.getString("fecFin") == null) {
					materiales.add(new Orden(rs.getString("idOrden"), rs.getString("descTrabajo"),
							rs.getString("matricula"), rs.getString("dniEmple"), rs.getInt("tiempoHoras"),
							new Fecha(rs.getString("fecInicio"))));
				} else {
					materiales.add(new Orden(rs.getString("idOrden"), rs.getString("descTrabajo"),
							rs.getString("matricula"), rs.getString("dniEmple"), rs.getInt("tiempoHoras"),
							new Fecha(rs.getString("fecInicio")), new Fecha(rs.getString("fecFin"))));
				}
			}

			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}

		return materiales;
	}

	public static ArrayList<Vehiculo> cargarTodosVehiculos() {
		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);

			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("select * from reto3.vehiculo");

			while (rs.next()) {
				boolean activo = General.estadoABoolean(rs.getString("estado"));

				vehiculos.add(new Vehiculo(rs.getString("matricula"), rs.getString("nBastidor"),
						rs.getString("dniCliente"), rs.getString("marca"), rs.getString("modelo"),
						new Fecha(rs.getString("fecFabric")), rs.getString("tipoCombustible"), activo));
			}

			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}

		return vehiculos;
	}

	// ===== ajustes =====
	public static void guardarAjustes(Ajustes a) {
		FileOutputStream fos;
		ObjectOutputStream oos;

		try {
			File f = new File(ajustes + Inicio.cuentaActual.getDNI());
			fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);

			oos.writeObject(a);

			oos.close();
			fos.close();
		} catch (IOException e) {
			Log.error("error al guardar el objeto");
		}

		Log.ajustes(Inicio.cuentaActual.getDNI());
	}

	public static Ajustes cargarAjustes(String dni, boolean listar) {
		Ajustes a = null;

		FileInputStream fis;
		ObjectInputStream ois;

		try {
			File f = new File(ajustes + dni);
			fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);

			a = (Ajustes) ois.readObject();

			ois.close();
			fis.close();
		} catch (IOException e) {
//			Log.error("No se han encontrado ajustes para " + Inicio.cuentaActual.getDNI());
			a = new Ajustes();
		} catch (ClassNotFoundException e) {
			Log.error("no se ha encontrado la clase especificada");
		}

		if (!listar) {
			Inicio.fuente = a.getFuente();
			Inicio.fuenteObjetos = a.getFuenteObjetos();

			Inicio.colorFondo = a.getColorFondo();
			Inicio.colorFondoObjetos = a.getColorFondoObjetos();

			Inicio.colorFuente = a.getColorFuente();
			Inicio.colorFuenteObjetos = a.getColorFuenteObjetos();
		}

		return a;
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