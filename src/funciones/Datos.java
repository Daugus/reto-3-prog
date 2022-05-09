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
import clases.Cliente;
import clases.Empleado;
import clases.Factura;
import clases.Fecha;
import clases.Material;
import clases.Orden;
import clases.Reparacion;
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
	public static void guardarCliente(Cliente c, boolean edicion) {
		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);
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

	public static void guardarEmpleado(Empleado c, boolean edicion) {
		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);
			Statement st = conexion.createStatement();

			String tipo = c.getTipo().replaceAll("Mecánico", "Mecanico");
			String estado = General.estadoAString(c.isActivo()).toLowerCase();

			String sentencia;

			if (edicion) {
				sentencia = String.format(Locale.US,
						"update reto3.empleado set nombre = '%s', apellidos = '%s', telefono = '%s',"
								+ " email = '%s', direccion = '%s', dniJefe = NULLIF('%s', ''), password = '%s',"
								+ " salBase = %.2f, comision = %.2f, tipoEmpleado = '%s',"
								+ " estado = '%s' where dniEmple like '%s';",
						c.getNombre(), c.getApellidos(), c.getTelefono(), c.getEmail(), c.getDireccion(),
						c.getDniJefe(), c.getPassword(), c.getSalario(), c.getComision(), tipo, estado, c.getDNI());
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

	public static void guardarOrden(Orden o, boolean edicion) {
		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);
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

	public static void guardarReparaciones(ArrayList<Reparacion> alReparaciones, String idOrden) {
		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);
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

	public static void guardarVehiculo(Vehiculo v, boolean edicion) {
		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);
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

	public static ArrayList<String> listarEmpleados(String dni) {
		ArrayList<String> alDNIs = new ArrayList<String>();

		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);

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

	public static ArrayList<Vehiculo> listarVehiculos() {
		ArrayList<Vehiculo> alMatriculas = new ArrayList<Vehiculo>();

		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);

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

	// ===== iniciar sesión =====
	public static Empleado iniciarSesion(String dni) {
		Empleado e = null;
		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);

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

	// ===== cargar individual =====
	public static Cliente cargarCliente(String dni) {
		Cliente c = null;
		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);

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

	public static Vehiculo cargarVehiculo(String matricula) {
		Vehiculo v = null;

		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);

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

	// ===== cargar todos =====
	public static ArrayList<Cliente> cargarClientes() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);

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

	public static ArrayList<Empleado> cargarEmpleados() {
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();

		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);

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

	public static ArrayList<Factura> cargarFacturas() {
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

	public static ArrayList<Material> cargarMateriales() {
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

	public static ArrayList<Orden> cargarOrdenes() {
		ArrayList<Orden> materiales = new ArrayList<Orden>();

		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);

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

	public static ArrayList<Vehiculo> cargarVehiculos() {
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

	// ===== generar códigos =====
	public static String generarCodigoOrden() {
		String codigo = null;
		try {
			Connection conexion = DriverManager.getConnection(ruta, usr, pass);

			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("select idOrden from reto3.ordenTrabajo order by idOrden desc limit 1;");

			if (rs.next()) {
				codigo = rs.getString("idOrden");
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

	public static String generarCodigoReparacion(String anterior) {
		String codigo = null;

		if (anterior.equals("")) {
			try {
				Connection conexion = DriverManager.getConnection(ruta, usr, pass);

				Statement st = conexion.createStatement();
				ResultSet rs = st
						.executeQuery("select idReparacion from reto3.reparacion order by idReparacion desc limit 1;");

				if (rs.next()) {
					anterior = rs.getString("idReparacion");
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
	public static void guardarAjustes(Ajustes a) {
		FileOutputStream fos;
		ObjectOutputStream oos;

		try {
			File f = new File(ajustes + Inicio.empleadoActual.getDNI());
			fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);

			oos.writeObject(a);

			oos.close();
			fos.close();
		} catch (IOException e) {
			Log.error("error al guardar el objeto");
		}

		Log.ajustes(Inicio.empleadoActual.getDNI());
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