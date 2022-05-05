package administracion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.Vehiculo;
import edicion.EditarVehiculo;
import funciones.Datos;
import funciones.General;
import funciones.Salir;
import funciones.Tablas;
import navegacion.Inicio;
import ordenes.CrearPrimaria;

public class AdministrarVehiculos extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	public static JTable tblVehiculos;

	private static JButton btnVolver;
	private static JButton btnEditar;
	private static JButton btnAgregar;

	private static ArrayList<Vehiculo> vehiculos;
	private Vehiculo vehiculo;

	private static boolean bloqueado;

	/**
	 * carga los elementos de la ventana
	 */
	public AdministrarVehiculos() {
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Administrar vehículos | " + Inicio.cuentaActual.getNombre());

		setBounds(100, 100, 700, 360);
		getContentPane().setPreferredSize(new Dimension(700, 360));
		pack();

		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Inicio.colorFondo);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(260, 310, 180, 40);
		panelPrincipal.add(btnVolver);

		btnAgregar = new JButton("Agregar vehículo");
		btnAgregar.setBounds(50, 10, 230, 60);
		panelPrincipal.add(btnAgregar);

		btnEditar = new JButton("Editar vehículo");
		btnEditar.setBounds(420, 10, 230, 60);
		panelPrincipal.add(btnEditar);

		// ===== barras de desplazamiento =====
		JScrollPane scrollVehiculos = new JScrollPane();
		scrollVehiculos.setBounds(50, 85, 600, 200);
		panelPrincipal.add(scrollVehiculos);

		// ===== modelos =====
		// --- crear ---
		DefaultTableModel dtmVehiculos = new DefaultTableModel();
		dtmVehiculos.addColumn("Matrícula");
		dtmVehiculos.addColumn("Propietario");
		dtmVehiculos.addColumn("Modelo");
		dtmVehiculos.addColumn("Estado");

		// --- asignar ---
		tblVehiculos = new JTable(dtmVehiculos) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblVehiculos.setFillsViewportHeight(true);
		tblVehiculos.getTableHeader().setReorderingAllowed(false);
		tblVehiculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblVehiculos.getTableHeader().setBackground(Inicio.colorFondoObjetos);
		tblVehiculos.getTableHeader().setFont(Inicio.fuenteObjetos);

		actualizarTabla();

		scrollVehiculos.setViewportView(tblVehiculos);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		btnVolver.addActionListener(this);
		btnAgregar.addActionListener(this);
		btnEditar.addActionListener(this);

		// ===== ajustes de usuario =====
		// --- fuente ---
		tblVehiculos.setFont(Inicio.fuente);

		btnVolver.setFont(Inicio.fuenteObjetos);
		btnAgregar.setFont(Inicio.fuenteObjetos);
		btnEditar.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		tblVehiculos.setBackground(Inicio.colorFondoObjetos);

		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnAgregar.setBackground(Inicio.colorFondoObjetos);
		btnEditar.setBackground(Inicio.colorFondoObjetos);

		scrollVehiculos.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		tblVehiculos.setForeground(Inicio.colorFuenteObjetos);

		btnVolver.setForeground(Inicio.colorFuenteObjetos);
		btnAgregar.setForeground(Inicio.colorFuenteObjetos);
		btnEditar.setForeground(Inicio.colorFuenteObjetos);
	}

	public static void actualizarTabla() {
		DefaultTableModel dtm = (DefaultTableModel) tblVehiculos.getModel();

		dtm.setRowCount(0);

		vehiculos = Datos.cargarTodosVehiculos();
		for (Vehiculo v : vehiculos) {
			String estado = General.estadoAString(v.isActivo());

			dtm.addRow(new Object[] { v.getMatricula(), v.getPropietario(), v.getMarca() + " " + v.getModelo(), estado });
		}

		Tablas.ajustarColumnas(tblVehiculos);
	}

	public static void botones(boolean estado) {
		btnAgregar.setEnabled(estado);
		btnEditar.setEnabled(estado);
		btnVolver.setEnabled(estado);

		bloqueado = !estado;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object o = ae.getSource();

		if (o == btnAgregar) {
			botones(false);

			EditarVehiculo ev = new EditarVehiculo();
			ev.setVisible(true);
		} else if (o == btnEditar) {
			int row = tblVehiculos.getSelectedRow();
			if (row >= 0) {
				vehiculo = vehiculos.get(row);

				botones(false);

				EditarVehiculo ev = new EditarVehiculo();
				ev.modoEdicion(vehiculo);

				ev.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, (String) "No hay ningún vehículo seleccionado", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (o == btnVolver) {
			CrearPrimaria cop = new CrearPrimaria();
			cop.setVisible(true);

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
	public void windowOpened(WindowEvent we) {
		// comportamiento por defecto
	}

	@Override
	public void windowClosed(WindowEvent we) {
		// comportamiento por defecto
	}

	@Override
	public void windowIconified(WindowEvent we) {
		// comportamiento por defecto
	}

	@Override
	public void windowDeiconified(WindowEvent we) {
		// comportamiento por defecto
	}

	@Override
	public void windowActivated(WindowEvent we) {
		// comportamiento por defecto
	}

	@Override
	public void windowDeactivated(WindowEvent we) {
		// comportamiento por defecto
	}
}