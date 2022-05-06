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

import clases.Empleado;
import edicion.EditarEmpleado;
import funciones.Datos;
import funciones.General;
import funciones.Salir;
import funciones.Tablas;
import navegacion.Inicio;
import navegacion.MenuAdmin;
import ordenes.CrearOrden;

public class AdministrarEmpleados extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private static JTable tblCuentas;

	private static JButton btnVolver;
	private static JButton btnAgregar;
	private static JButton btnEditar;

	private static ArrayList<Empleado> empleados;
	private Empleado empleado;

	private static boolean bloqueado;
	private boolean origenMenu;

	/**
	 * carga los elementos de la ventana
	 */
	public AdministrarEmpleados(boolean origenMenu) {
		this.origenMenu = origenMenu;

		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Administrar empleados | " + Inicio.empleadoActual.getNombre());

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

		btnAgregar = new JButton("Agregar cuenta");
		btnAgregar.setBounds(50, 10, 230, 60);
		panelPrincipal.add(btnAgregar);

		btnEditar = new JButton("Editar cuentas");
		btnEditar.setBounds(420, 10, 230, 60);
		panelPrincipal.add(btnEditar);

		// ===== barras de desplazamiento =====
		JScrollPane scrollCuentas = new JScrollPane();
		scrollCuentas.setBounds(50, 85, 600, 200);
		panelPrincipal.add(scrollCuentas);

		// ===== modelos =====
		// --- crear ---
		DefaultTableModel dtmCuentas = new DefaultTableModel();
		dtmCuentas.addColumn("DNI");
		dtmCuentas.addColumn("Nombre");
		dtmCuentas.addColumn("Apellidos");
		dtmCuentas.addColumn("Tipo");
		dtmCuentas.addColumn("DNI Jefe");
		dtmCuentas.addColumn("Estado");

		// --- asignar ---
		tblCuentas = new JTable(dtmCuentas) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblCuentas.setRowHeight(20);
		tblCuentas.setFillsViewportHeight(true);
		tblCuentas.getTableHeader().setReorderingAllowed(false);
		tblCuentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCuentas.getTableHeader().setBackground(Inicio.colorFondoObjetos);
		tblCuentas.getTableHeader().setFont(Inicio.fuenteObjetos);

		actualizarTabla();

		scrollCuentas.setViewportView(tblCuentas);

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
		tblCuentas.setFont(Inicio.fuente);

		btnVolver.setFont(Inicio.fuenteObjetos);
		btnAgregar.setFont(Inicio.fuenteObjetos);
		btnEditar.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		tblCuentas.setBackground(Inicio.colorFondoObjetos);

		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnAgregar.setBackground(Inicio.colorFondoObjetos);
		btnEditar.setBackground(Inicio.colorFondoObjetos);

		scrollCuentas.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		tblCuentas.setForeground(Inicio.colorFuenteObjetos);

		btnVolver.setForeground(Inicio.colorFuenteObjetos);
		btnAgregar.setForeground(Inicio.colorFuenteObjetos);
		btnEditar.setForeground(Inicio.colorFuenteObjetos);
	}
	
	public static void actualizarTabla() {
		DefaultTableModel dtm = (DefaultTableModel) tblCuentas.getModel();

		dtm.setRowCount(0);

		empleados = Datos.cargarEmpleados();
		for (Empleado e : empleados) {
			String dniJefe = e.getDniJefe();
			if (dniJefe == null)
				dniJefe = "-";

			String estado = General.estadoAString(e.isActivo());
			dtm.addRow(new Object[] { e.getDNI(), e.getNombre(), e.getApellidos(), e.getTipo(), dniJefe, estado });
		}

		Tablas.ajustarColumnas(tblCuentas);
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

			EditarEmpleado ec = new EditarEmpleado();
			ec.setAlDNIs(empleados);
			ec.setVisible(true);
		} else if (o == btnEditar) {
			int row = tblCuentas.getSelectedRow();
			if (row >= 0) {
				empleado = empleados.get(row);

				botones(false);

				EditarEmpleado ec = new EditarEmpleado();
				ec.modoEdicion(empleado);
				ec.setAlDNIs(empleados);

				ec.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, (String) "No hay ningún empleado seleccionado", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (o == btnVolver) {
			JFrame ventana = null;

			if (origenMenu) {
				ventana = new MenuAdmin();
			} else {
				ventana = new CrearOrden();
			}

			ventana.setVisible(true);
			
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