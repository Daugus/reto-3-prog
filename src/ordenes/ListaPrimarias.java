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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.Primaria;
import funciones.Archivos;
import funciones.Salir;
import navegacion.Inicio;
import navegacion.ListaOrdenes;
import navegacion.MenuMec;

public class ListaPrimarias extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1531539371445418371L;
	private JPanel panelPrincipal;

	private JTable tblPrimarias;

	private JButton btnVolver;
	private JButton btnCargar;

	ArrayList<Primaria> alPrimarias;

	private static Primaria ordenPrim;

	public ListaPrimarias() {
		setResizable(false);
		setTitle("Lista de Órdenes Primarias | " + Inicio.cuentaActual.getNombre());

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
		JScrollPane scrollPrimarias = new JScrollPane();
		scrollPrimarias.setBackground(Color.LIGHT_GRAY);
		scrollPrimarias.setBounds(50, 10, 600, 200);
		panelPrincipal.add(scrollPrimarias);

		// ===== modelos =====
		// --- crear ---
		DefaultTableModel dtmPrimarias = new DefaultTableModel();
		dtmPrimarias.addColumn("Fecha");
		dtmPrimarias.addColumn("Cliente");
		dtmPrimarias.addColumn("Vehículo");

		alPrimarias = Archivos.cargarTodosPrimarias();
		alPrimarias.sort(Comparator.reverseOrder());
		for (Primaria op : alPrimarias) {
			dtmPrimarias.addRow(
					new Object[] { op.getFecha(), op.getPropietario().getDNI(), op.getVehiculo().getMatricula() });
		}

		// --- asignar ---
		tblPrimarias = new JTable(dtmPrimarias) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblPrimarias.setRowHeight(20);
		tblPrimarias.setFillsViewportHeight(true);
		tblPrimarias.getTableHeader().setReorderingAllowed(false);
		tblPrimarias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPrimarias.setViewportView(tblPrimarias);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		// - JButton -
		btnCargar.addActionListener(this);
		btnVolver.addActionListener(this);

		tblPrimarias.getTableHeader().setFont(Inicio.fuenteObjetos);
		tblPrimarias.setFont(Inicio.fuente);

		btnVolver.setFont(Inicio.fuenteObjetos);
		btnCargar.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		tblPrimarias.getTableHeader().setBackground(Inicio.colorFondoObjetos);
		tblPrimarias.setBackground(Inicio.colorFondoObjetos);

		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnCargar.setBackground(Inicio.colorFondoObjetos);

		tblPrimarias.setForeground(Inicio.colorFuenteObjetos);

		btnVolver.setForeground(Inicio.colorFuenteObjetos);
		btnCargar.setForeground(Inicio.colorFuenteObjetos);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o == btnCargar) {
			int row = tblPrimarias.getSelectedRow();
			if (row >= 0) {
				try {
					ordenPrim = alPrimarias.get(row);

					CrearPendiente cop = new CrearPendiente();
					cop.cargarDatos(ordenPrim);

					cop.setVisible(true);

					this.dispose();
				} catch (NullPointerException npe) {
					JOptionPane.showMessageDialog(null, "La Orden Primaria seleccionada no existe", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, (String) "No hay ninguna orden seleccionada", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JFrame ventana = null;
			if (Inicio.cuentaActual.esMecanico()) {
				ventana = new MenuMec();
			} else {
				ventana = new ListaOrdenes();
			}

			ventana.setVisible(true);
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