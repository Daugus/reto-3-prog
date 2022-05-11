package ordenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import clases.Factura;
import clases.Fecha;
import clases.Orden;
import funciones.Datos;
import funciones.Salir;
import navegacion.Inicio;
import navegacion.MenuListas;
import navegacion.MenuMecanico;

public class ListaOrdenes extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1531539371445418371L;
	private JPanel panelPrincipal;

	private JTable tblOrdenes;

	private JButton btnVolver;
	private JButton btnCargar;

	private ArrayList<Orden> alOrdenes;
	private TableRowSorter<TableModel> sorter;

	private static Orden orden;

	private JRadioButton rdbActivas;
	private JRadioButton rdbFinalizadas;
	private JRadioButton rdbTodas;

	public ListaOrdenes() {
		setResizable(false);
		setTitle("Lista de órdenes de trabajo | " + Inicio.empleadoActual.getNombre());

		setBounds(100, 100, 700, 305);
		getContentPane().setPreferredSize(new Dimension(700, 305));
		pack();

		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		ButtonGroup grupo = new ButtonGroup();

		rdbActivas = new JRadioButton("Activas");
		rdbActivas.setHorizontalAlignment(SwingConstants.CENTER);
		rdbActivas.setOpaque(false);
		rdbActivas.setBounds(380, 220, 90, 20);
		panelPrincipal.add(rdbActivas);

		rdbFinalizadas = new JRadioButton("Finalizadas");
		rdbFinalizadas.setHorizontalAlignment(SwingConstants.CENTER);
		rdbFinalizadas.setOpaque(false);
		rdbFinalizadas.setBounds(470, 220, 90, 20);
		panelPrincipal.add(rdbFinalizadas);

		rdbTodas = new JRadioButton("Todas");
		rdbTodas.setHorizontalAlignment(SwingConstants.CENTER);
		rdbTodas.setOpaque(false);
		rdbTodas.setBounds(560, 220, 90, 20);
		panelPrincipal.add(rdbTodas);

		grupo.add(rdbActivas);
		grupo.add(rdbFinalizadas);
		grupo.add(rdbTodas);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(162, 255, 180, 40);
		panelPrincipal.add(btnVolver);

		btnCargar = new JButton("Cargar");
		btnCargar.setBounds(358, 255, 180, 40);
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

		sorter = new TableRowSorter<TableModel>(dtmOrdenes);
		tblOrdenes.setRowSorter(sorter);

		scrollPendientes.setViewportView(tblOrdenes);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		rdbActivas.addActionListener(this);
		rdbFinalizadas.addActionListener(this);
		rdbTodas.addActionListener(this);

		btnCargar.addActionListener(this);
		btnVolver.addActionListener(this);

		// ===== ajustes de usuario =====
		// --- fuente ---
		tblOrdenes.setFont(Inicio.fuente);
		tblOrdenes.getTableHeader().setFont(Inicio.fuenteObjetos);

		rdbActivas.setFont(Inicio.fuente);
		rdbFinalizadas.setFont(Inicio.fuente);
		rdbTodas.setFont(Inicio.fuente);

		btnVolver.setFont(Inicio.fuenteObjetos);
		btnCargar.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		tblOrdenes.getTableHeader().setBackground(Inicio.colorFondoObjetos);
		tblOrdenes.setBackground(Inicio.colorFondoObjetos);

		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnCargar.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		tblOrdenes.setForeground(Inicio.colorFuenteObjetos);

		rdbActivas.setForeground(Inicio.colorFuente);
		rdbFinalizadas.setForeground(Inicio.colorFuente);
		rdbTodas.setForeground(Inicio.colorFuente);
		
		btnVolver.setForeground(Inicio.colorFuenteObjetos);
		btnCargar.setForeground(Inicio.colorFuenteObjetos);

		// ===== filtrar tabla =====
		if (Inicio.empleadoActual.getTipo().equals("Mecanico")) {
			rdbActivas.setSelected(true);
		} else {
			rdbFinalizadas.setSelected(true);
		}

		filtrarOrdenes();
	}

	private void filtrarOrdenes() {
		int columna = 4;

		if (rdbActivas.isSelected()) {
			sorter.setRowFilter(RowFilter.regexFilter("-", columna));
		} else if (rdbFinalizadas.isSelected()) {
			sorter.setRowFilter(RowFilter.regexFilter("[^-]", columna));
		} else {
			sorter.setRowFilter(null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o == btnCargar) {
			int row = tblOrdenes.getSelectedRow();
			if (row >= 0) {
				int absRow = tblOrdenes.convertRowIndexToModel(row);
				orden = alOrdenes.get(absRow);

				Factura factura = Datos.cargarFactura(orden.getCodigo());
				boolean ordenFinalizada = orden.getFechaFin() != null;
				String mostrar = "mostrar";

//				if ((ordenFinalizada && Inicio.empleadoActual.getTipo().equals("Mecanico"))
//						|| (!ordenFinalizada && !Inicio.empleadoActual.getTipo().equals("Mecanico"))) {
//					mostrar = "mostrar";
//				}

				boolean nueva = true;

				if (factura == null) {
					if (ordenFinalizada && !Inicio.empleadoActual.getTipo().equals("Mecanico")) {
						mostrar = "generar";
					}

					if (!ordenFinalizada && Inicio.empleadoActual.getTipo().equals("Mecanico")) {
						mostrar = "finalizar";
					}
				} else {
					nueva = false;
				}

				MostrarOrden mo = new MostrarOrden(mostrar);
				mo.cargarDatos(orden);

				mo.setNueva(nueva);

				mo.setVisible(true);

				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, (String) "No hay ninguna orden seleccionada", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (o == btnVolver) {
			JFrame ventana = null;

			if (Inicio.empleadoActual.getTipo().equals("Mecanico")) {
				ventana = new MenuMecanico();
			} else {
				ventana = new MenuListas();
			}

			ventana.setVisible(true);

			this.dispose();
		} else {
			filtrarOrdenes();
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