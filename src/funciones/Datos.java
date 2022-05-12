package funciones;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import clases.Ajustes;
import clases.Cliente;
import clases.Empleado;
import clases.Factura;
import clases.Fecha;
import clases.Material;
import clases.Orden;
import clases.Reparacion;
import clases.Total;
import clases.Vehiculo;
import navegacion.Inicio;

/**
 * métodos para interactuar con las bases de datos
 */
public class Datos {
	// ===== rutas =====
	private static String logs = "C:\\RKA\\Logs\\";

	// ===== configuración BBDD =====
	// --- MariaDB ---
	private static String rutaSQL = "jdbc:mysql://192.168.220.220/reto3";
	private static String usr = "g1";
	private static String pass = "Inf662";

	// --- ObjectDB ---
	private static String rutaObjectDB = "objectdb://192.168.220.220:6136/db/ajustes.odb";
	private static Map<String, String> configObjectDB = new HashMap<String, String>();

	// ===== configuración al iniciar =====
	/**
	 * crea la configuración para conectarse a la base de datos ObjectDB y crea la
	 * carpeta para guardar los logs en caso de que no existan
	 */
	public static void configuracion() {
		// --- configurar ObjectDB ---
		configObjectDB.put("javax.persistence.jdbc.user", usr);
		configObjectDB.put("javax.persistence.jdbc.password", pass);

		// --- crear carpeta de logs ---
		File f = new File(logs);
		if (!f.exists())
			f.mkdirs();
	}

	// ===== guardar =====
	/**
	 * guarda o actualiza un cliente en la base de datos
	 * 
	 * @param c       objeto de cliente que contiene los datos
	 * @param edicion si es {@code true} se ejecuta una sentencia update, si es
	 *                {@code false} se ejecuta una sentencia insert
	 */
	public static void guardarCliente(Cliente c, boolean edicion) {
		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);
			Statement st = conexion.createStatement();

			String estado = General.estadoAString(c.isActivo()).toLowerCase();

			String sentencia;

			if (edicion) {
				sentencia = String.format(
						"update reto3.cliente set nombre = '%s', apellidos = '%s', telefono = '%s',"
								+ " email = '%s', direccion = '%s', estado = '%s' where dniCliente like '%s';",
						c.getNombre(), c.getApellidos(), c.getTelefono(), c.getEmail(), c.getDireccion(), estado,
						c.getDNI());
			} else {
				sentencia = String.format(
						"insert into reto3.cliente (dniCliente, nombre, apellidos, telefono, email, direccion, fecAlta, estado)"
								+ " values('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
						c.getDNI(), c.getNombre(), c.getApellidos(), c.getTelefono(), c.getEmail(), c.getDireccion(),
						c.getFechaAlta().toSQLDate(), estado);
			}

			st.executeUpdate(sentencia);

			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}
	}

	/**
	 * guarda o actualiza un empleado en la base de datos
	 * 
	 * @param e       objeto de empleado que contiene los datos
	 * @param edicion si es {@code true} se ejecuta una sentencia update, si es
	 *                {@code false} se ejecuta una sentencia insert
	 */
	public static void guardarEmpleado(Empleado e, boolean edicion) {
		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);
			Statement st = conexion.createStatement();

			String tipo = e.getTipo().replaceAll("Mecánico", "Mecanico");
			String estado = General.estadoAString(e.isActivo()).toLowerCase();

			String sentencia;

			if (edicion) {
				sentencia = String.format(Locale.US,
						"update reto3.empleado set nombre = '%s', apellidos = '%s', telefono = '%s',"
								+ " email = '%s', direccion = '%s', dniJefe = NULLIF('%s', ''), password = '%s',"
								+ " salBase = %.2f, comision = %.2f, tipoEmpleado = '%s',"
								+ " estado = '%s' where dniEmple like '%s';",
						e.getNombre(), e.getApellidos(), e.getTelefono(), e.getEmail(), e.getDireccion(),
						e.getDniJefe(), e.getPassword(), e.getSalario(), e.getComision(), tipo, estado, e.getDNI());
			} else {
				sentencia = String.format(Locale.US, "insert into reto3.empleado"
						+ " (dniEmple, nombre, apellidos, telefono, email, direccion, dniJefe, password,"
						+ " salBase, comision, fecNac, tipoEmpleado, fecAltaContrato, estado)"
						+ " values('%s', '%s', '%s', '%s', '%s', '%s', NULLIF('%s', ''), '%s', %.2f, %.2f, '%s', '%s', '%s', '%s');",
						e.getDNI(), e.getNombre(), e.getApellidos(), e.getTelefono(), e.getEmail(), e.getDireccion(),
						e.getDniJefe(), e.getPassword(), e.getSalario(), e.getComision(),
						e.getFechaNacimiento().toSQLDate(), tipo, e.getFechaAlta().toSQLDate(), estado);
			}

			st.executeUpdate(sentencia);

			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}
	}

	/**
	 * guarda o actualiza una factura en la base de datos
	 * 
	 * @param f       objeto de factura que contiene los datos
	 * @param edicion si es {@code true} se ejecuta una sentencia update, si es
	 *                {@code false} se ejecuta una sentencia insert
	 */
	public static void guardarFactura(Factura f, boolean edicion) {
		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);
			Statement st = conexion.createStatement();

			String pagada = General.pagadaAString(f.isPagada());
			String sentencia;

			if (edicion) {
				sentencia = String.format(
						"update reto3.factura set pagada = '%s', metodoPago = '%s', descuento = %d where idFactura = '%s';",
						pagada, f.getMetodoPago(), f.getDescuento(), f.getCodigo());
			} else {
				if (f.isPagada()) {
					sentencia = String.format(
							"insert into reto3.factura (idFactura, idOrden, pagada, fecha, metodoPago, descuento)"
									+ " values('%s', '%s', '%s', '%s', '%s', %d);",
							f.getCodigo(), f.getCodigoOrden(), pagada, f.getFecha().toSQLDate(), f.getMetodoPago(),
							f.getDescuento());
				} else {
					sentencia = String.format(
							"insert into reto3.factura (idFactura, idOrden, pagada, fecha) values('%s', '%s', '%s', '%s');",
							f.getCodigo(), f.getCodigoOrden(), pagada, f.getFecha().toSQLDate());
				}
			}

			st.executeUpdate(sentencia);

			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}
	}

	/**
	 * guarda o actualiza un material en la base de datos
	 * 
	 * @param m       objeto de material que contiene los datos
	 * @param edicion si es {@code true} se ejecuta una sentencia update, si es
	 *                {@code false} se ejecuta una sentencia insert
	 */
	public static void guardarMaterial(Material m, boolean edicion) {
		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);
			Statement st = conexion.createStatement();

			String estado = General.estadoAString(m.isActivo()).toLowerCase();

			String sentencia;

			if (edicion) {
				sentencia = String.format(Locale.US, "update reto3.pieza"
						+ " set marca = '%s', nombre = '%s', stock = %d, pvp = %.2f, precioCompra = %.2f, estado = '%s'"
						+ " where idPieza like '%s';", m.getMarca(), m.getNombre(), m.getStock(), m.getPVP(),
						m.getPrecioCompra(), estado, m.getID());
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

	/**
	 * guarda o actualiza una orden en la base de datos
	 * 
	 * @param o       objeto de orden que contiene los datos
	 * @param edicion si es {@code true} se ejecuta una sentencia update, si es
	 *                {@code false} se ejecuta una sentencia insert
	 */
	public static void guardarOrden(Orden o, boolean edicion) {
		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);
			Statement st = conexion.createStatement();

			String sentencia;

			if (edicion) {
				sentencia = String.format(
						"update reto3.ordenTrabajo set fecFin = '%s', tiempoHoras = %d where idOrden like '%s';",
						o.getFechaFin().toSQLDate(), o.getTiempoHoras(), o.getCodigo());
			} else {
				sentencia = String.format(
						"insert into reto3.ordenTrabajo (idOrden, matricula, dniEmple, fecInicio, descTrabajo) values('%s', '%s', '%s', '%s', '%s');",
						o.getCodigo(), o.getMatricula(), o.getEmpleado(), o.getFechaInicio().toSQLDate(),
						o.getComentarios());
			}

			st.executeUpdate(sentencia);

			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}
	}

	/**
	 * guarda varias reparaciones en la base de datos
	 * 
	 * @param alReparaciones ArrayList con todas las reparaciones
	 * @param idOrden        ID de la orden a la que pertenecen las reparaciones
	 */
	public static void guardarReparaciones(ArrayList<Reparacion> alReparaciones, String idOrden) {
		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);
			Statement st = conexion.createStatement();

			for (Reparacion r : alReparaciones) {
				String sentencia = String.format("insert into reto3.reparacion (idReparacion, descripcion, estado)"
						+ " values('%s', '%s', '%s');", r.getCodigo(), r.getDescripcion(), "activo");

				st.executeUpdate(sentencia);

				sentencia = String.format(Locale.US,
						"insert into reto3.requiere (idOrden, idReparacion, idPieza, precioHistorico, cantidad)"
								+ " values('%s', '%s', '%s', %.2f, %d);",
						idOrden, r.getCodigo(), r.getIdMaterial(), r.getPrecio(), r.getCantidadMaterial());
				st.executeUpdate(sentencia);
			}

			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}
	}

	/**
	 * guarda o actualiza un vehículo en la base de datos
	 * 
	 * @param v       objeto de vehículo que contiene los datos
	 * @param edicion si es {@code true} se ejecuta una sentencia update, si es
	 *                {@code false} se ejecuta una sentencia insert
	 */
	public static void guardarVehiculo(Vehiculo v, boolean edicion) {
		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);
			Statement st = conexion.createStatement();

			String estado = General.estadoAString(v.isActivo()).toLowerCase();

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

	// ===== borrar =====
	/**
	 * borra una factura de la base de datos
	 * 
	 * @param f la factura que se va a borrar
	 */
	public static void borrarFactura(Factura f) {
		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);
			Statement st = conexion.createStatement();

			st.executeUpdate(String.format("delete from reto3.factura where idFactura = '%s'", f.getCodigo()));

			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}
	}

	// ===== listar =====
	/**
	 * lista los DNIs de los clientes almacenados en la base de datos
	 * 
	 * @return ArrayList con los DNIs
	 */
	public static ArrayList<String> listarClientes() {
		ArrayList<String> alDNIs = new ArrayList<String>();

		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);

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

	/**
	 * lista los DNIs de los empleados almacenados en la base de datos
	 * 
	 * @return ArrayList con los DNIs
	 */
	public static ArrayList<String> listarEmpleados(String dni) {
		ArrayList<String> alDNIs = new ArrayList<String>();

		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);

			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(
					String.format("select dniEmple from reto3.empleado where dniEmple not like '%s'", dni));

			while (rs.next()) {
				alDNIs.add(rs.getString("dniEmple"));
			}

			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}

		return alDNIs;
	}

	/**
	 * lista las matrículas de los vehículos almacenados en la base de datos
	 * 
	 * @return ArrayList con las matrículas
	 */
	public static ArrayList<Vehiculo> listarVehiculos() {
		ArrayList<Vehiculo> alMatriculas = new ArrayList<Vehiculo>();

		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);

			Statement st = conexion.createStatement();
			ResultSet rs = st
					.executeQuery("select matricula, dniCliente from reto3.vehiculo where estado like 'activo';");

			while (rs.next()) {
				alMatriculas.add(new Vehiculo(rs.getString("matricula"), rs.getString("dniCliente")));
			}

			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}

		return alMatriculas;
	}

	// ===== cargar individual =====
	/**
	 * carga un cliente de la base de datos
	 * 
	 * @param dni DNI del cliente que se va a cargar
	 * @return un objeto de Cliente
	 */
	public static Cliente cargarCliente(String dni) {
		Cliente c = null;
		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);

			Statement st = conexion.createStatement();
			ResultSet rs = st
					.executeQuery(String.format("select * from reto3.cliente where dniCliente like '%s';", dni));

			if (rs.next()) {
				boolean activo = General.estadoABoolean(rs.getString("estado"));

				c = new Cliente(rs.getString("dniCliente"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("telefono"), rs.getString("email"), rs.getString("direccion"),
						new Fecha(rs.getString("fecAlta")), activo);
			}

			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}

		return c;
	}

	/**
	 * carga una factura almacenada en la base de datos
	 * 
	 * @param codigo código de la factura que se va a cargar
	 * @return un objeto Factura
	 */
	public static Factura cargarFactura(String codigo) {
		Factura f = null;
		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);

			Statement st = conexion.createStatement();
			String sentencia = null;

			if (codigo.charAt(0) == 'F') {
				sentencia = String.format("select * from reto3.factura where idFactura like '%s';", codigo);
			} else {
				sentencia = String.format("select * from reto3.factura where idOrden like '%s';", codigo);
			}

			ResultSet rs = st.executeQuery(sentencia);

			if (rs.next()) {
				boolean pagada = General.pagadaABoolean(rs.getString("pagada"));

				f = new Factura(rs.getString("idFactura"), rs.getString("idOrden"), rs.getString("metodoPago"), pagada,
						rs.getInt("descuento"), new Fecha(rs.getString("fecha")));
			}

			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}

		return f;
	}

	/**
	 * carga una orden almacenada en la base de datos
	 * 
	 * @param codigo         código de la orden que se va a cargar
	 * @param mostrarFactura {@code true} si solamente se necesita mostrar en
	 *                       Mostrar Factura
	 * @return un objeto Orden
	 */
	public static Orden cargarOrden(String codigo, boolean mostrarFactura) {
		Orden o = null;
		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);

			Statement st = conexion.createStatement();
			ResultSet rs = st
					.executeQuery(String.format("select * from reto3.ordenTrabajo where idOrden like '%s';", codigo));

			if (rs.next()) {
				if (mostrarFactura) {
					o = new Orden(rs.getString("idOrden"), rs.getString("matricula"));
				} else {
					o = new Orden(new Orden(rs.getString("idOrden"), rs.getString("descTrabajo"),
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

		return o;
	}

	/**
	 * carga un vehículo almacenado en la base de datos
	 * 
	 * @param matricula matrícula del vehículo que se va a cargar
	 * @return un objeto Vehículo
	 */
	public static Vehiculo cargarVehiculo(String matricula) {
		Vehiculo v = null;

		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);

			Statement st = conexion.createStatement();
			ResultSet rs = st
					.executeQuery(String.format("select * from reto3.vehiculo where matricula like '%s';", matricula));

			if (rs.next()) {
				boolean activo = General.estadoABoolean(rs.getString("estado"));

				v = new Vehiculo(rs.getString("matricula"), rs.getString("nBastidor"), rs.getString("dniCliente"),
						rs.getString("marca"), rs.getString("modelo"), new Fecha(rs.getString("fecFabric")),
						rs.getString("tipoCombustible"), activo);
			}

			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}

		return v;
	}

	// ===== cargar varios =====
	/**
	 * carga los clientes almacenados en la base de datos
	 * 
	 * @return ArrayList con los objetos Cliente
	 */
	public static ArrayList<Cliente> cargarClientes() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);

			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("select * from reto3.cliente");

			while (rs.next()) {
				boolean activo = General.estadoABoolean(rs.getString("estado"));

				clientes.add(new Cliente(rs.getString("dniCliente"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("telefono"), rs.getString("email"), rs.getString("direccion"),
						new Fecha(rs.getString("fecAlta")), activo));
			}

			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}

		return clientes;
	}

	/**
	 * carga los empleados almacenados en la base de datos
	 * 
	 * @return ArrayList con los objetos Empleado
	 */
	public static ArrayList<Empleado> cargarEmpleados() {
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();

		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);

			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("select * from reto3.empleado");

			while (rs.next()) {
				Ajustes ajustes = cargarAjustes(rs.getString("dniEmple"), true);
				String tipo = rs.getString("tipoEmpleado").replaceAll("Mecanico", "Mecánico");
				boolean activo = General.estadoABoolean(rs.getString("estado"));

				empleados.add(new Empleado(rs.getString("dniEmple"), rs.getString("nombre"), rs.getString("apellidos"),
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

		return empleados;
	}

	/**
	 * carga las facturas almacenados en la base de datos
	 * 
	 * @return ArrayList con los objetos Factura
	 */
	public static ArrayList<Factura> cargarFacturas() {
		ArrayList<Factura> facturas = new ArrayList<Factura>();

		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);

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

	/**
	 * carga los materiales almacenados en la base de datos
	 * 
	 * @return ArrayList con los objetos Material
	 */
	public static ArrayList<Material> cargarMateriales(boolean todos) {
		ArrayList<Material> materiales = new ArrayList<Material>();

		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);

			Statement st = conexion.createStatement();
			String sentencia = null;

			if (todos) {
				sentencia = "select * from reto3.pieza";
			} else {
				sentencia = "select * from reto3.pieza where estado = 'activo';";
			}

			ResultSet rs = st.executeQuery(sentencia);

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

	/**
	 * carga las órdenes almacenados en la base de datos
	 * 
	 * @return ArrayList con los objetos Orden
	 */
	public static ArrayList<Orden> cargarOrdenes() {
		ArrayList<Orden> materiales = new ArrayList<Orden>();

		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);

			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("select * from reto3.ordenTrabajo");

			while (rs.next()) {
				if (rs.getString("fecFin") == null || rs.getString("tiempoHoras") == null) {
					materiales.add(new Orden(rs.getString("idOrden"), rs.getString("descTrabajo"),
							rs.getString("matricula"), rs.getString("dniEmple"), new Fecha(rs.getString("fecInicio"))));
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

	/**
	 * carga las reparaciones almacenados en la base de datos
	 * 
	 * @return ArrayList con los objetos Reparacion
	 */
	public static ArrayList<Reparacion> cargarReparaciones(String idOrden) {
		ArrayList<Reparacion> reparaciones = new ArrayList<Reparacion>();

		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);

			Statement stRequiere = conexion.createStatement();
			ResultSet rsRequiere = stRequiere
					.executeQuery(String.format("select * from reto3.requiere where idOrden = '%s';", idOrden));

			while (rsRequiere.next()) {
				Statement stReparacion = conexion.createStatement();
				ResultSet rsReparacion = stReparacion
						.executeQuery(String.format("select * from reto3.reparacion where idReparacion = '%s';",
								rsRequiere.getString("idReparacion")));

				rsReparacion.next();
				String descripcion = rsReparacion.getString("descripcion");
				boolean estado = General.estadoABoolean(rsReparacion.getString("estado"));

				reparaciones.add(new Reparacion(rsRequiere.getString("idReparacion"), descripcion,
						rsRequiere.getDouble("precioHistorico"), rsRequiere.getString("idPieza"),
						rsRequiere.getInt("cantidad"), estado));
				rsReparacion.close();
				stReparacion.close();
			}

			rsRequiere.close();
			stRequiere.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}

		return reparaciones;
	}

	/**
	 * carga los vehículos almacenados en la base de datos
	 * 
	 * @return ArrayList con los objetos Vehiculo
	 */
	public static ArrayList<Vehiculo> cargarVehiculos() {
		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);

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

	// ===== generar códigos =====
	/**
	 * genera un código para la factura basado en el código de la última factura
	 * almacenada
	 * 
	 * @return el código
	 */
	public static String generarCodigoFactura() {
		String codigo = null;
		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);

			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("select idFactura from reto3.factura order by idFactura desc limit 1;");

			if (rs.next()) {
				codigo = rs.getString("idFactura");
			} else {
				codigo = "F00000";
			}

			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}

		int numCodigo = Integer.valueOf(codigo.substring(1));
		numCodigo++;
		codigo = String.format("F%05d", numCodigo);

		return codigo;
	}

	/**
	 * genera un código para la orden basado en el código de la última orden
	 * almacenada
	 * 
	 * @return el código
	 */
	public static String generarCodigoOrden() {
		String codigo = null;
		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);

			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("select idOrden from reto3.ordenTrabajo order by idOrden desc limit 1;");

			if (rs.next()) {
				codigo = rs.getString("idOrden");
			} else {
				codigo = "OT0000";
			}

			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}

		int numCodigo = Integer.valueOf(codigo.substring(2));
		numCodigo++;
		codigo = String.format("OT%04d", numCodigo);

		return codigo;
	}

	/**
	 * genera un código para la reparación basado en el código pasado como parámetro
	 * o en el código de la última reparación almacenada en caso de que esté vacío
	 * 
	 * @param anterior el código de la reparación anterior
	 * @return el código
	 */
	public static String generarCodigoReparacion(String anterior) {
		String codigo = null;

		if (anterior.equals("")) {
			try {
				Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);

				Statement st = conexion.createStatement();
				ResultSet rs = st
						.executeQuery("select idReparacion from reto3.reparacion order by idReparacion desc limit 1;");

				if (rs.next()) {
					anterior = rs.getString("idReparacion");
				} else {
					anterior = "R00000";
				}

				rs.close();
				st.close();
				conexion.close();
			} catch (SQLException sqle) {
				System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
			}
		}

		int numCodigo = Integer.valueOf(anterior.substring(1));
		numCodigo++;
		codigo = String.format("R%05d", numCodigo);
		return codigo;
	}

	// ===== ajustes =====
	/**
	 * guarda o actualiza los ajustes en la base de datos
	 * 
	 * @param a objeto de ajustes que contiene los datos
	 */
	public static void guardarAjustes(Ajustes a) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(rutaObjectDB, configObjectDB);
		EntityManager em = emf.createEntityManager();

		Ajustes aBusqueda = em.find(Ajustes.class, a.getDniEmpleado());

		em.getTransaction().begin();

		if (aBusqueda == null) {
			em.persist(a);
		} else {
			aBusqueda.setFamiliaFuente(a.getFamiliaFuente());
			aBusqueda.setTemaOscuro(a.isTemaOscuro());
		}

		em.getTransaction().commit();

		Log.ajustes(Inicio.empleadoActual.getDNI());
	}

	/**
	 * carga los ajustes de usuario de la base de datos
	 * 
	 * @param dni    DNI del empleado
	 * @param listar si es {@code true} solamente se cargan los datos, si es
	 *               {@code false} además se aplicarán a la sesión actual
	 * @return un objeto Ajustes
	 */
	public static Ajustes cargarAjustes(String dni, boolean listar) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(rutaObjectDB, configObjectDB);
		EntityManager em = emf.createEntityManager();

		// leer por dni
		Ajustes a = em.find(Ajustes.class, dni);

		// si no se encuentra
		if (a == null)
			a = new Ajustes();

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

	/**
	 * resetea los ajustes a los por defecto al cerrar sesión
	 */
	public static void reiniciarAjustes() {
		Inicio.fuente = new Font("Segoe UI", Font.PLAIN, 13);
		Inicio.fuenteObjetos = new Font("Segoe UI", Font.BOLD, 13);

		Inicio.colorFondo = Color.DARK_GRAY;
		Inicio.colorFondoObjetos = Color.LIGHT_GRAY;
		Inicio.colorFuente = Color.WHITE;
		Inicio.colorFuenteObjetos = Color.BLACK;
	}

	// ===== otros =====
	/**
	 * carga los datos del empleado con con el que se va a iniciar sesión
	 * 
	 * @param dni DNI del empleado
	 * @return un objeto Empleado
	 */
	public static Empleado iniciarSesion(String dni) {
		Empleado e = null;
		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);

			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(String.format("select * from reto3.empleado where dniEmple like '%s'", dni));

			if (rs.next()) {
				boolean activo = General.estadoABoolean(rs.getString("estado"));

				e = new Empleado(rs.getString("dniEmple"), rs.getString("nombre"), rs.getString("apellidos"),
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

		return e;
	}

	/**
	 * carga el nombre de un material
	 * 
	 * @param idMaterial la ID del material que se va a buscar
	 * @return el nombre del material
	 */
	public static String getNombreMaterial(String idMaterial) {
		String nombre = null;

		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(
					String.format("select marca, nombre from reto3.pieza where idPieza = '%s';", idMaterial));

			if (rs.next()) {
				nombre = String.format("%s %s", rs.getString("nombre"), rs.getString("marca"));
			}

			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}

		return nombre;
	}

	/**
	 * calcula el total de una factura
	 * 
	 * @param factura factura de la que se va a cargar el total
	 * @return un objeto Total
	 */
	public static Total calcularTotal(Factura factura) {
		Total t = null;

		try {
			Connection conexion = DriverManager.getConnection(rutaSQL, usr, pass);
			Statement stRequiere = conexion.createStatement();
			ResultSet rsRequiere = stRequiere.executeQuery(
					String.format("select * from reto3.requiere where idOrden = '%s';", factura.getCodigoOrden()));

			double costeReparaciones = 0;
			double costeMateriales = 0;

			while (rsRequiere.next()) {
				costeReparaciones += rsRequiere.getDouble("precioHistorico");

				Statement stPieza = conexion.createStatement();
				ResultSet rsPieza = stPieza.executeQuery(String
						.format("select pvp from reto3.pieza where idPieza = '%s';", rsRequiere.getString("idPieza")));

				while (rsPieza.next()) {
					costeMateriales += rsPieza.getDouble("pvp") * rsRequiere.getInt("cantidad");
				}
			}

			t = new Total(costeReparaciones, costeMateriales, factura.getDescuento());

			rsRequiere.close();
			stRequiere.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("Error SQL " + sqle.getErrorCode() + ":\n" + sqle.getMessage());
		}

		return t;
	}
}