package ordenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import clases.Cliente;
import clases.Factura;
import clases.Orden;
import clases.Reparacion;
import clases.Total;
import clases.Vehiculo;
import funciones.Datos;
import funciones.General;
import funciones.Salir;
import funciones.Tablas;
import navegacion.Inicio;

public class MostrarFactura extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private static JButton btnVolver;
	private static JButton btnEditar;

	private JTable tblCliente;
	private JTable tblVehiculo;

	private JScrollPane scrollReparaciones;
	private static JTable tblReparaciones;

	private JTable tblTotal;

	private static ArrayList<Reparacion> alReparaciones = new ArrayList<Reparacion>();

	private Factura factura;

	private static boolean bloqueado;

	public MostrarFactura() {
		setResizable(false);

		setTitle("Mostrar factura | " + Inicio.empleadoActual.getNombre());

		setBounds(100, 100, 790, 605);
		getContentPane().setPreferredSize(new Dimension(790, 605));
		pack();

		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		btnEditar = new JButton("Editar pago");
		btnEditar.setBounds(403, 555, 180, 40);
		panelPrincipal.add(btnEditar);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(207, 555, 180, 40);
		panelPrincipal.add(btnVolver);

		// ===== barras de desplazamiento =====
		// --- reparaciones ---
		scrollReparaciones = new JScrollPane();
		scrollReparaciones.setBounds(10, 185, 770, 200);
		panelPrincipal.add(scrollReparaciones);

		// ===== modelos =====
		// --- crear ---
		DefaultTableModel dtmCliente = new DefaultTableModel();
		dtmCliente.addColumn("");
		dtmCliente.addColumn("");

		DefaultTableModel dtmVehiculo = new DefaultTableModel();
		dtmVehiculo.addColumn("");
		dtmVehiculo.addColumn("");

		DefaultTableModel dtmReparaciones = new DefaultTableModel();
		dtmReparaciones.addColumn("Descripción");
		dtmReparaciones.addColumn("Coste");
		dtmReparaciones.addColumn("Material");
		dtmReparaciones.addColumn("Cantidad");

		DefaultTableModel dtmTotal = new DefaultTableModel();
		dtmTotal.addColumn("");
		dtmTotal.addColumn("");

		// --- asignar ---
		tblCliente = new JTable(dtmCliente) {
			private static final long serialVersionUID = -3909141556237115067L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblCliente.setBounds(10, 10, 420, 160);
		tblCliente.setRowHeight(20);
		tblCliente.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblCliente.getTableHeader().setReorderingAllowed(false);
		tblCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelPrincipal.add(tblCliente);

		tblVehiculo = new JTable(dtmVehiculo) {
			private static final long serialVersionUID = -3909141556237115067L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblVehiculo.setBounds(445, 10, 335, 160);
		tblVehiculo.setRowHeight(20);
		tblVehiculo.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblVehiculo.getTableHeader().setReorderingAllowed(false);
		tblVehiculo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelPrincipal.add(tblVehiculo);

		tblReparaciones = new JTable(dtmReparaciones) {
			private static final long serialVersionUID = -3909141556237115067L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblReparaciones.setRowHeight(20);
		tblReparaciones.setFillsViewportHeight(true);
		tblReparaciones.getTableHeader().setReorderingAllowed(false);
		tblReparaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollReparaciones.setViewportView(tblReparaciones);

		tblTotal = new JTable(dtmTotal) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblTotal.setBounds(530, 410, 250, 120);
		tblTotal.setRowHeight(20);
		tblTotal.getTableHeader().setReorderingAllowed(false);
		tblTotal.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblTotal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		panelPrincipal.add(tblTotal);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		btnVolver.addActionListener(this);
		btnEditar.addActionListener(this);

		// ===== ajustes de usuario =====
		// --- fuente y color ---
		panelPrincipal.setBackground(Inicio.colorFondo);

		tblReparaciones.getTableHeader().setFont(Inicio.fuenteObjetos);
		tblReparaciones.getTableHeader().setBackground(Inicio.colorFondoObjetos);

		tblReparaciones.setFont(Inicio.fuente);
		tblReparaciones.setBackground(Inicio.colorFondoObjetos);
		tblReparaciones.setForeground(Inicio.colorFuenteObjetos);

		btnVolver.setFont(Inicio.fuenteObjetos);
		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnVolver.setForeground(Inicio.colorFuenteObjetos);

		btnEditar.setFont(Inicio.fuenteObjetos);
		btnEditar.setBackground(Inicio.colorFondoObjetos);
		btnEditar.setForeground(Inicio.colorFuenteObjetos);

		scrollReparaciones.setBackground(Inicio.colorFondoObjetos);

		// --- tablas verticales ---
		tblCliente.setFont(Inicio.fuente);
		tblVehiculo.setFont(Inicio.fuente);
		tblTotal.setFont(Inicio.fuente);
		tblTotal.setBackground(Inicio.colorFondoObjetos);

		tblCliente.setBackground(Inicio.colorFondoObjetos);
		tblVehiculo.setBackground(Inicio.colorFondoObjetos);

		tblCliente.setForeground(Inicio.colorFuenteObjetos);
		tblVehiculo.setForeground(Inicio.colorFuenteObjetos);
		tblTotal.setForeground(Inicio.colorFuenteObjetos);
	}

	public void cargarDatos(Factura f, boolean edicion) {
		factura = new Factura(f);
		Orden orden = Datos.cargarOrden(factura.getCodigoOrden());

		if (!edicion) {
			btnEditar.setVisible(false);
			btnVolver.setBounds(305, 555, 180, 40);
		}

		// ===== datos reparaciones =====
		// --- cargar ---
		ArrayList<Reparacion> alReparaciones = Datos.cargarReparaciones(orden.getCodigo());

		// --- escribir ---
		DefaultTableModel dtmReparaciones = (DefaultTableModel) tblReparaciones.getModel();

		for (Reparacion r : alReparaciones) {
			dtmReparaciones.addRow(new Object[] { r.getDescripcion(), String.valueOf(r.getPrecio()),
					Datos.getNombreMaterial(r.getIdMaterial()), r.getCantidadMaterial() });
		}

		Tablas.ajustarColumnas(tblReparaciones);

		// ===== datos vehículo =====
		// --- cargar ---
		Vehiculo v = Datos.cargarVehiculo(orden.getMatricula());

		// --- escribir ---
		DefaultTableModel dtmVehiculo = (DefaultTableModel) tblVehiculo.getModel();
		dtmVehiculo.addRow(new Object[] { "Matrícula", v.getMatricula() });
		dtmVehiculo.addRow(new Object[] { "Bastidor", v.getBastidor() });

		dtmVehiculo.addRow(new Object[] { "Modelo", v.getMarca() + " " + v.getModelo() });

		dtmVehiculo.addRow(new Object[] { "Fecha fabricación", v.getFechaFabricacion() });

		dtmVehiculo.addRow(new Object[] { "Tipo", v.getTipo() });

		// ===== datos cliente =====
		// --- cargar ---
		Cliente c = Datos.cargarCliente(v.getPropietario());

		// --- escribir ---
		DefaultTableModel dtmCliente = (DefaultTableModel) tblCliente.getModel();
		dtmCliente.addRow(new Object[] { "DNI", c.getDNI() });

		dtmCliente.addRow(new Object[] { "Nombre", c.getNombre() });
		dtmCliente.addRow(new Object[] { "Apellidos", c.getApellidos() });

		dtmCliente.addRow(new Object[] { "Tel.", c.getTelefono() });
		dtmCliente.addRow(new Object[] { "Email", c.getEmail() });

		dtmCliente.addRow(new Object[] { "Fecha de alta", c.getFechaAlta() });
		dtmCliente.addRow(new Object[] { "Dirección", c.getDireccion() });

		// ===== total =====
		// --- calcular ---
		Total total = Datos.calcularTotal(factura);

		// --- escribir ---
		DefaultTableModel dtmTotal = (DefaultTableModel) tblTotal.getModel();
		dtmTotal.addRow(new Object[] { "Total reparaciones", General.formatearPrecio(total.getCosteReparaciones()) });
		dtmTotal.addRow(new Object[] { "Total materiales", General.formatearPrecio(total.getCosteMateriales()) });
		dtmTotal.addRow(new Object[] { "Descuento (" + factura.getDescuento() + "%)",
				General.formatearPrecio(total.getDescuento()) });
		dtmTotal.addRow(new Object[] { "Subtotal", General.formatearPrecio(total.getSubtotal()) });
		dtmTotal.addRow(new Object[] { "IVA (" + total.getIVA() + "%)", General.formatearPrecio(total.getCosteIVA()) });
		dtmTotal.addRow(new Object[] { "Total", General.formatearPrecio(total.getTotal()) });

		// ===== estilizar tablas =====
		Tablas.vertical(tblCliente);
		Tablas.vertical(tblVehiculo);
		Tablas.vertical(tblTotal);
	}

	public static void actualizarTablas() {
		alReparaciones.sort(Comparator.naturalOrder());

		DefaultTableModel dtmReparaciones = (DefaultTableModel) tblReparaciones.getModel();
		dtmReparaciones.setRowCount(0);

		for (Reparacion r : alReparaciones) {
			dtmReparaciones
					.addRow(new Object[] { r.getCodigo(), r.getDescripcion(), General.formatearPrecio(r.getPrecio()),
							Datos.getNombreMaterial(r.getIdMaterial()), r.getCantidadMaterial() });
		}

		Tablas.ajustarColumnas(tblReparaciones);
	}

	public static void botones(boolean estado) {
		btnVolver.setEnabled(estado);
		btnEditar.setEnabled(estado);

		bloqueado = !estado;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o == btnEditar) {
			EditarFactura ef = new EditarFactura();
			boolean nueva = false;
			ef.cargarDatos(factura, nueva);
			ef.setVisible(true);

			this.dispose();
		} else if (o == btnVolver) {
			alReparaciones.clear();

			ListaFacturas lf = new ListaFacturas();
			lf.setVisible(true);

			this.dispose();
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (bloqueado) {
			Salir.errorBloqueado();
		} else {
			Salir.general(this);
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// comportamiento por defecto
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// comportamiento por defecto
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// comportamiento por defecto
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// comportamiento por defecto
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// comportamiento por defecto
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// comportamiento por defecto
	}
}