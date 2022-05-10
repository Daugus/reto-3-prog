package ordenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import clases.Cliente;
import clases.Fecha;
import clases.Orden;
import clases.Reparacion;
import clases.Vehiculo;
import edicion.EditarReparacion;
import funciones.Datos;
import funciones.General;
import funciones.Salir;
import funciones.Tablas;
import navegacion.Inicio;

public class MostrarOrden extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private static JButton btnVolver;
	private static JButton btnFinalizar;
	private static JButton btnAgregar;
	private static JButton btnEditar;
	private static JButton btnEliminar;

	private JLabel lblCodigoTxt;
	private JLabel lblComentarioTxt;

	private JTable tblCliente;
	private JTable tblVehiculo;

	private JScrollPane scrollReparaciones;
	private static JTable tblReparaciones;

	private static ArrayList<Reparacion> alReparaciones = new ArrayList<Reparacion>();

	private Orden orden;

	private String mostrar;
	private static boolean bloqueado;

	public MostrarOrden(String mostrar) {
		setResizable(false);

		this.mostrar = mostrar;

		if (mostrar.equals("mostrar")) {
			setTitle("Mostrar orden de trabajo | " + Inicio.empleadoActual.getNombre());
		} else if (mostrar.equals("generar")) {
			setTitle("Generar factura | " + Inicio.empleadoActual.getNombre());
		} else if (mostrar.equals("finalizar")) {
			setTitle("Finalizar orden de trabajo | " + Inicio.empleadoActual.getNombre());
		}

		setBounds(100, 100, 790, 720);
		getContentPane().setPreferredSize(new Dimension(790, 720));
		pack();

		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		btnFinalizar = new JButton("Finalizar orden");
		btnFinalizar.setBounds(403, 670, 180, 40);
		panelPrincipal.add(btnFinalizar);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(207, 670, 180, 40);
		panelPrincipal.add(btnVolver);

		btnAgregar = new JButton("Agregar reparación");
		btnAgregar.setBounds(35, 585, 230, 60);
		panelPrincipal.add(btnAgregar);

		btnEditar = new JButton("Editar reparación");
		btnEditar.setBounds(280, 585, 230, 60);
		panelPrincipal.add(btnEditar);

		btnEliminar = new JButton("Eliminar reparación");
		btnEliminar.setBounds(525, 585, 230, 60);
		panelPrincipal.add(btnEliminar);

		JLabel lblCodigo = new JLabel("Código orden de trabajo:");
		lblCodigo.setBounds(10, 185, 150, 35);
		panelPrincipal.add(lblCodigo);

		lblCodigoTxt = new JLabel("");
		lblCodigoTxt.setBounds(160, 185, 250, 35);
		panelPrincipal.add(lblCodigoTxt);

		lblComentarioTxt = new JLabel("");
		lblComentarioTxt.setVerticalAlignment(SwingConstants.TOP);
		lblComentarioTxt.setBounds(10, 255, 500, 100);
		panelPrincipal.add(lblComentarioTxt);

		JLabel lblComentario = new JLabel("Comentario:");
		lblComentario.setBounds(10, 220, 150, 35);
		panelPrincipal.add(lblComentario);

		// ===== barras de desplazamiento =====
		// --- reparaciones ---
		scrollReparaciones = new JScrollPane();
		scrollReparaciones.setBounds(10, 370, 770, 200);
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
		dtmReparaciones.addColumn("ID");
		dtmReparaciones.addColumn("Descripción");
		dtmReparaciones.addColumn("Coste");
		dtmReparaciones.addColumn("Material");
		dtmReparaciones.addColumn("Cantidad");

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

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		btnVolver.addActionListener(this);
		btnFinalizar.addActionListener(this);
		btnAgregar.addActionListener(this);
		btnEditar.addActionListener(this);
		btnEliminar.addActionListener(this);

		// ===== ajustes de usuario =====
		// --- fuente y color ---
		panelPrincipal.setBackground(Inicio.colorFondo);

		ArrayList<JLabel> etiquetas = new ArrayList<JLabel>();
		etiquetas.addAll(Arrays.asList(lblCodigo, lblCodigoTxt, lblComentario, lblComentarioTxt));
		for (JLabel lbl : etiquetas) {
			lbl.setFont(Inicio.fuente);
			lbl.setForeground(Inicio.colorFuente);
		}

		tblReparaciones.getTableHeader().setFont(Inicio.fuenteObjetos);
		tblReparaciones.getTableHeader().setBackground(Inicio.colorFondoObjetos);

		tblReparaciones.setFont(Inicio.fuente);
		tblReparaciones.setBackground(Inicio.colorFondoObjetos);
		tblReparaciones.setForeground(Inicio.colorFuenteObjetos);

		btnVolver.setFont(Inicio.fuenteObjetos);
		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnVolver.setForeground(Inicio.colorFuenteObjetos);

		btnFinalizar.setFont(Inicio.fuenteObjetos);
		btnFinalizar.setBackground(Inicio.colorFondoObjetos);
		btnFinalizar.setForeground(Inicio.colorFuenteObjetos);

		btnAgregar.setFont(Inicio.fuenteObjetos);
		btnAgregar.setBackground(Inicio.colorFondoObjetos);
		btnAgregar.setForeground(Inicio.colorFuenteObjetos);

		btnEditar.setFont(Inicio.fuenteObjetos);
		btnEditar.setBackground(Inicio.colorFondoObjetos);
		btnEditar.setForeground(Inicio.colorFuenteObjetos);

		btnEliminar.setFont(Inicio.fuenteObjetos);
		btnEliminar.setBackground(Inicio.colorFondoObjetos);
		btnEliminar.setForeground(Inicio.colorFuenteObjetos);

		scrollReparaciones.setBackground(Inicio.colorFondoObjetos);

		// --- tablas verticales ---
		tblCliente.setFont(Inicio.fuente);
		tblVehiculo.setFont(Inicio.fuente);

		tblCliente.setBackground(Inicio.colorFondoObjetos);
		tblVehiculo.setBackground(Inicio.colorFondoObjetos);

		tblCliente.setForeground(Inicio.colorFuenteObjetos);
		tblVehiculo.setForeground(Inicio.colorFuenteObjetos);
	}

	public void cargarDatos(Orden o) {
		orden = new Orden(o);

		if (!mostrar.equals("finalizar")) {
			if (mostrar.equals("mostrar")) {
				btnFinalizar.setVisible(false);
				btnVolver.setBounds(305, 670, 180, 40);
			}

			btnAgregar.setVisible(false);
			btnEditar.setVisible(false);
			btnEliminar.setVisible(false);

			btnFinalizar.setText("Generar factura");

			// ===== reparaciones =====
			// --- cargar vehículo ---
			ArrayList<Reparacion> alReparaciones = Datos.cargarReparaciones(orden.getCodigo());

			// --- escribir vehículo ---
			DefaultTableModel dtmReparaciones = (DefaultTableModel) tblReparaciones.getModel();

			for (Reparacion r : alReparaciones) {
				dtmReparaciones.addRow(new Object[] { r.getCodigo(), r.getDescripcion(), String.valueOf(r.getPrecio()),
						Datos.getNombreMaterial(r.getIdMaterial()), r.getCantidadMaterial() });
			}

			Tablas.ajustarColumnas(tblReparaciones);
		}

		// ===== datos vehículo =====
		// --- cargar vehículo ---
		Vehiculo v = Datos.cargarVehiculo(orden.getMatricula());

		// --- escribir vehículo ---
		DefaultTableModel dtmVehiculo = (DefaultTableModel) tblVehiculo.getModel();
		dtmVehiculo.addRow(new Object[] { "Matrícula", v.getMatricula() });
		dtmVehiculo.addRow(new Object[] { "Bastidor", v.getBastidor() });

		dtmVehiculo.addRow(new Object[] { "Modelo", v.getMarca() + " " + v.getModelo() });

		dtmVehiculo.addRow(new Object[] { "Cilindrada", v.getFechaFabricacion() });

		dtmVehiculo.addRow(new Object[] { "Tipo", v.getTipo() });

		// ===== datos cliente =====
		// --- cargar cliente ---
		Cliente c = Datos.cargarCliente(v.getPropietario());

		// --- escribir cliente ---
		DefaultTableModel dtmCliente = (DefaultTableModel) tblCliente.getModel();
		dtmCliente.addRow(new Object[] { "DNI", c.getDNI() });

		dtmCliente.addRow(new Object[] { "Nombre", c.getNombre() });
		dtmCliente.addRow(new Object[] { "Apellidos", c.getApellidos() });

		dtmCliente.addRow(new Object[] { "Tel.", c.getTelefono() });
		dtmCliente.addRow(new Object[] { "Email", c.getEmail() });

		dtmCliente.addRow(new Object[] { "Fecha de alta", c.getFechaAlta() });
		dtmCliente.addRow(new Object[] { "Dirección", c.getDireccion() });

		// ===== datos orden primaria =====
		lblCodigoTxt.setText(orden.getCodigo());
		lblComentarioTxt.setText(orden.getComentarios());

		// ===== estilizar tablas =====
		Tablas.vertical(tblCliente);
		Tablas.vertical(tblVehiculo);
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
		btnAgregar.setEnabled(estado);
		btnEditar.setEnabled(estado);
		btnEliminar.setEnabled(estado);
		btnVolver.setEnabled(estado);
		btnFinalizar.setEnabled(estado);

		bloqueado = !estado;
	}

	public static ArrayList<Reparacion> getReparaciones() {
		return alReparaciones;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o == btnFinalizar) {
			if (tblReparaciones.getRowCount() > 0) {
				if (mostrar.equals("finalizar")) {
					int horas = 0;
					for (Reparacion r : alReparaciones) {
						horas += r.getHoras();
					}

					Datos.guardarOrden(new Orden(orden.getCodigo(), horas, new Fecha()), true);

					Datos.guardarReparaciones(alReparaciones, orden.getCodigo());

					JOptionPane.showMessageDialog(this, (String) "Se ha finalizado la orden " + orden.getCodigo(),
							"INFO", JOptionPane.INFORMATION_MESSAGE);

					ListaOrdenes lo = new ListaOrdenes();
					lo.setVisible(true);

					this.dispose();
				} else {
					GenerarFactura gf = new GenerarFactura();
					boolean nueva = true;
					gf.cargarDatos(orden, nueva);
					gf.setVisible(true);

					this.dispose();
				}
			} else {
				JOptionPane.showMessageDialog(this, (String) "No se ha agregado ninguna reparación", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (o == btnAgregar) {
			EditarReparacion er = new EditarReparacion();
			er.setVisible(true);
		} else if (o == btnEditar) {
			EditarReparacion er = new EditarReparacion();

			int row = tblReparaciones.getSelectedRow();
			if (row >= 0) {
				er.modoEdicion(alReparaciones.get(row), row);
				er.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, (String) "No hay ninguna reparación seleccionada", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (o == btnEliminar) {
			int row = tblReparaciones.getSelectedRow();
			if (row >= 0) {
				alReparaciones.remove(row);
				DefaultTableModel dtm = (DefaultTableModel) tblReparaciones.getModel();
				dtm.removeRow(row);

				actualizarTablas();
			} else {
				JOptionPane.showMessageDialog(this, (String) "No hay ninguna reparación seleccionada", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (o == btnVolver) {
			alReparaciones.clear();

			ListaOrdenes lo = new ListaOrdenes();
			lo.setVisible(true);

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