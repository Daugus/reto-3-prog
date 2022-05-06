package ordenes;

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

import clases.Fecha;
import clases.Orden;
import funciones.Datos;
import funciones.Salir;
import navegacion.Inicio;
import navegacion.MenuListas;

public class ListaOrdenes extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1531539371445418371L;
	private JPanel panelPrincipal;

	private JTable tblOrdenes;

	private JButton btnVolver;
	private JButton btnCargar;

	ArrayList<Orden> alOrdenes;

	private static Orden orden;

	public ListaOrdenes() {
		setResizable(false);
		setTitle("Lista de órdenes de trabajo | " + Inicio.empleadoActual.getNombre());

		setBounds(100, 100, 700, 285);
		getContentPane().setPreferredSize(new Dimension(700, 285));
		pack();

		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(162, 235, 180, 40);
		panelPrincipal.add(btnVolver);

		btnCargar = new JButton("Cargar");
		btnCargar.setBounds(358, 235, 180, 40);
		panelPrincipal.add(btnCargar);

		// ===== barras de desplazamiento =====
		JScrollPane scrollPendientes = new JScrollPane();
		scrollPendientes.setBackground(Color.LIGHT_GRAY);
		scrollPendientes.setBounds(50, 10, 600, 200);
		panelPrincipal.add(scrollPendientes);

		// ===== modelos =====
		// --- crear ---
		DefaultTableModel dtmOrdenes = new DefaultTableModel();
		dtmOrdenes.addColumn("ID");
		dtmOrdenes.addColumn("Vehículo");
		dtmOrdenes.addColumn("Empleado");
		dtmOrdenes.addColumn("Fecha Inicio");
		dtmOrdenes.addColumn("Fecha Fin");

		alOrdenes = Datos.cargarOrdenes();
		// alOrdenes.sort(Comparator.reverseOrder());
		for (Orden o : alOrdenes) {
			Fecha fecFin = o.getFechaFin();
			String fechaFin = fecFin == null ? "-" : fecFin.toString();

			dtmOrdenes.addRow(
					new Object[] { o.getCodigo(), o.getMatricula(), o.getEmpleado(), o.getFechaInicio(), fechaFin });
		}

		// --- asignar ---
		tblOrdenes = new JTable(dtmOrdenes) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblOrdenes.setRowHeight(20);
		tblOrdenes.setFillsViewportHeight(true);
		tblOrdenes.getTableHeader().setReorderingAllowed(false);
		tblOrdenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPendientes.setViewportView(tblOrdenes);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		// - JButton -
		btnCargar.addActionListener(this);
		btnVolver.addActionListener(this);

		tblOrdenes.getTableHeader().setFont(Inicio.fuenteObjetos);
		tblOrdenes.setFont(Inicio.fuente);

		btnVolver.setFont(Inicio.fuenteObjetos);
		btnCargar.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		tblOrdenes.getTableHeader().setBackground(Inicio.colorFondoObjetos);
		tblOrdenes.setBackground(Inicio.colorFondoObjetos);

		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnCargar.setBackground(Inicio.colorFondoObjetos);

		tblOrdenes.setForeground(Inicio.colorFuenteObjetos);

		btnVolver.setForeground(Inicio.colorFuenteObjetos);
		btnCargar.setForeground(Inicio.colorFuenteObjetos);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o == btnCargar) {
			int row = tblOrdenes.getSelectedRow();
			if (row >= 0) {
				orden = alOrdenes.get(row);

				// TODO: implementar
				JOptionPane.showMessageDialog(null, "Se ha cargado la orden " + orden.getCodigo(), "Sin implementar",
						JOptionPane.INFORMATION_MESSAGE);

				MostrarOrden mo = new MostrarOrden();
				// mo.cargarDatos(orden);

				mo.setVisible(true);

				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, (String) "No hay ninguna orden seleccionada", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			// btnVolver
			MenuListas ml = new MenuListas();
			ml.setVisible(true);

			this.dispose();
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		Salir.general(this);
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