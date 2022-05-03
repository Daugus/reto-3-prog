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

import clases.Material;
import edicion.EditarMaterial;
import funciones.Datos;
import funciones.General;
import funciones.Salir;
import funciones.Tablas;
import navegacion.Inicio;
import navegacion.MenuAdmin;

public class AdministrarMateriales extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private static JTable tblMateriales;
	private static JButton btnVolver;
	private static JButton btnEditar;
	private static JButton btnAgregar;

	private Material material;

	private static boolean bloqueado;

	/**
	 * carga los elementos de la ventana
	 */
	public AdministrarMateriales() {
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Administrar materiales | " + Inicio.cuentaActual.getNombre());

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

		btnAgregar = new JButton("Agregar material");
		btnAgregar.setBounds(50, 10, 230, 60);
		panelPrincipal.add(btnAgregar);

		btnEditar = new JButton("Editar material");
		btnEditar.setBounds(420, 10, 230, 60);
		panelPrincipal.add(btnEditar);

		// ===== barras de desplazamiento =====
		JScrollPane scrollMateriales = new JScrollPane();
		scrollMateriales.setBounds(50, 85, 600, 200);
		panelPrincipal.add(scrollMateriales);

		// ===== modelos =====
		// --- crear ---
		DefaultTableModel dtmMateriales = new DefaultTableModel();
		dtmMateriales.addColumn("Nombre");
		dtmMateriales.addColumn("Precio");

		// --- asignar ---
		tblMateriales = new JTable(dtmMateriales) {
			private static final long serialVersionUID = -6533314169471135820L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblMateriales.setRowHeight(20);
		tblMateriales.setFillsViewportHeight(true);
		tblMateriales.getTableHeader().setReorderingAllowed(false);
		tblMateriales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblMateriales.getTableHeader().setBackground(Inicio.colorFondoObjetos);
		tblMateriales.getTableHeader().setFont(Inicio.fuenteObjetos);

		actualizarTabla();

		scrollMateriales.setViewportView(tblMateriales);

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
		tblMateriales.setFont(Inicio.fuente);

		btnVolver.setFont(Inicio.fuenteObjetos);
		btnAgregar.setFont(Inicio.fuenteObjetos);
		btnEditar.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		tblMateriales.setBackground(Inicio.colorFondoObjetos);

		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnAgregar.setBackground(Inicio.colorFondoObjetos);
		btnEditar.setBackground(Inicio.colorFondoObjetos);

		scrollMateriales.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		tblMateriales.setForeground(Inicio.colorFuenteObjetos);

		btnVolver.setForeground(Inicio.colorFuenteObjetos);
		btnAgregar.setForeground(Inicio.colorFuenteObjetos);
		btnEditar.setForeground(Inicio.colorFuenteObjetos);
	}

	public static void actualizarTabla() {
		DefaultTableModel dtm = (DefaultTableModel) tblMateriales.getModel();

		dtm.setRowCount(0);

		ArrayList<Material> materiales = Datos.cargarTodosMateriales();
		for (Material m : materiales) {
			dtm.addRow(new Object[] { m.getNombre(), General.formatear(m.getPrecio()) });
		}

		Tablas.ajustarColumnas(tblMateriales);
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

			EditarMaterial em = new EditarMaterial();
			em.setVisible(true);
		} else if (o == btnEditar) {
			int row = tblMateriales.getSelectedRow();
			if (row >= 0) {
				material = Datos.cargarMaterial((String) tblMateriales.getValueAt(row, 0));

				botones(false);

				EditarMaterial em = new EditarMaterial();
				em.modoEdicion(material);

				em.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, (String) "No hay ningún material seleccionado", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (o == btnVolver) {
			MenuAdmin ma = new MenuAdmin();
			ma.setVisible(true);

			this.dispose();
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (bloqueado) {
			Salir.error();
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