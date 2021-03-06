package administracion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.Cliente;
import edicion.EditarCliente;
import funciones.Datos;
import funciones.Salir;
import funciones.Tablas;
import navegacion.Inicio;
import ordenes.CrearOrden;

/**
 * ventana de administración de clientes
 */
public class AdministrarClientes extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private static JTable tblClientes;

	private static JButton btnVolver;
	private static JButton btnEditar;
	private static JButton btnAgregar;

	private static ArrayList<Cliente> clientes;
	private Cliente cliente;

	private static boolean bloqueado;

	/**
	 * carga los elementos de la ventana
	 */
	public AdministrarClientes() {
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Administrar clientes | " + Inicio.empleadoActual.getNombre());
		setIconImage(new ImageIcon(getClass().getResource("/icono.png")).getImage());

		setBounds(100, 100, 700, 360);
		getContentPane().setPreferredSize(new Dimension(700, 360));
		pack();

		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(260, 310, 180, 40);
		panelPrincipal.add(btnVolver);

		btnAgregar = new JButton("Agregar cliente");
		btnAgregar.setBounds(50, 10, 230, 60);
		panelPrincipal.add(btnAgregar);

		btnEditar = new JButton("Editar cliente");
		btnEditar.setBounds(420, 10, 230, 60);
		panelPrincipal.add(btnEditar);

		// ===== barras de desplazamiento =====
		JScrollPane scrollClientes = new JScrollPane();
		scrollClientes.setBounds(50, 85, 600, 200);
		panelPrincipal.add(scrollClientes);

		// ===== modelos =====
		// --- crear ---
		DefaultTableModel dtmClientes = new DefaultTableModel();
		dtmClientes.addColumn("DNI");
		dtmClientes.addColumn("Nombre");
		dtmClientes.addColumn("Apellidos");
		dtmClientes.addColumn("Teléfono");
		dtmClientes.addColumn("Fecha Alta");

		// --- asignar ---
		tblClientes = new JTable(dtmClientes) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblClientes.setRowHeight(20);
		tblClientes.setFillsViewportHeight(true);
		tblClientes.getTableHeader().setReorderingAllowed(false);
		tblClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblClientes.getTableHeader().setBackground(Inicio.colorFondoObjetos);
		tblClientes.getTableHeader().setFont(Inicio.fuenteObjetos);

		actualizarTabla();

		scrollClientes.setViewportView(tblClientes);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		btnVolver.addActionListener(this);
		btnAgregar.addActionListener(this);
		btnEditar.addActionListener(this);

		// ===== ajustes de usuario =====
		// --- fuente
		tblClientes.setFont(Inicio.fuente);

		btnVolver.setFont(Inicio.fuenteObjetos);
		btnAgregar.setFont(Inicio.fuenteObjetos);
		btnEditar.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		tblClientes.setBackground(Inicio.colorFondoObjetos);

		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnAgregar.setBackground(Inicio.colorFondoObjetos);
		btnEditar.setBackground(Inicio.colorFondoObjetos);

		scrollClientes.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		tblClientes.setForeground(Inicio.colorFuenteObjetos);

		btnVolver.setForeground(Inicio.colorFuenteObjetos);
		btnAgregar.setForeground(Inicio.colorFuenteObjetos);
		btnEditar.setForeground(Inicio.colorFuenteObjetos);
	}

	/**
	 * actualiza los datos de la tabla usando el ArrayList clientes
	 */
	public static void actualizarTabla() {
		DefaultTableModel dtm = (DefaultTableModel) tblClientes.getModel();

		dtm.setRowCount(0);

		clientes = Datos.cargarClientes();
		for (Cliente c : clientes) {
			dtm.addRow(new Object[] { c.getDNI(), c.getNombre(), c.getApellidos(), c.getTelefono(), c.getFechaAlta() });
		}

		Tablas.ajustarColumnas(tblClientes);
	}

	/**
	 * cambia el estado de los botones
	 * 
	 * @param estado indica si los botones están habilitados
	 */
	public static void botones(boolean estado) {
		btnAgregar.setEnabled(estado);
		btnEditar.setEnabled(estado);
		btnVolver.setEnabled(estado);

		bloqueado = !estado;
	}

	/**
	 * invocado cuando ocurren una acción
	 * 
	 * @param ae el evento de acción
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object o = ae.getSource();

		if (o == btnAgregar) {
			botones(false);

			EditarCliente ec = new EditarCliente();
			ec.setVisible(true);
		} else if (o == btnEditar) {
			int row = tblClientes.getSelectedRow();
			if (row >= 0) {
				cliente = clientes.get(row);

				botones(false);

				EditarCliente ec = new EditarCliente();
				ec.modoEdicion(cliente);
				ec.setAlDNIs(clientes);

				ec.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, (String) "No hay ningún cliente seleccionado", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (o == btnVolver) {
			CrearOrden cop = new CrearOrden();
			cop.setVisible(true);

			this.dispose();
		}
	}

	/**
	 * invocado cuando se cierra la ventana
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowClosing(WindowEvent we) {
		if (bloqueado) {
			Salir.errorBloqueado();
		} else {
			Salir.general(this);
		}
	}

	/**
	 * invocado cuando se abre la ventana
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowOpened(WindowEvent we) {
		// comportamiento por defecto
	}

	/**
	 * invocado después de que se cierre la ventana
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowClosed(WindowEvent we) {
		// comportamiento por defecto
	}

	/**
	 * invocado cuando se minimiza la ventana
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowIconified(WindowEvent we) {
		// comportamiento por defecto
	}

	/**
	 * invocado cuando se maximiza la ventana
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowDeiconified(WindowEvent we) {
		// comportamiento por defecto
	}

	/**
	 * invocado cuando la ventana se convierte en la ventana activa
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowActivated(WindowEvent we) {
		// comportamiento por defecto
	}

	/**
	 * invocado cuando la ventana deja de ser la ventana activa
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowDeactivated(WindowEvent we) {
		// comportamiento por defecto
	}
}