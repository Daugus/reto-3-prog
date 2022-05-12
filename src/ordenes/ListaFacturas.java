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
import funciones.Datos;
import funciones.General;
import funciones.Salir;
import navegacion.Inicio;
import navegacion.MenuListas;

public class ListaFacturas extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1531539371445418371L;
	private JPanel panelPrincipal;

	private JTable tblFacturas;

	private JButton btnVolver;
	private JButton btnCargar;
	private JButton btnEliminar;

	private ArrayList<Factura> alFacturas;
	private TableRowSorter<TableModel> sorter;

	private static Factura factura;

	private JRadioButton rdbPendientes;
	private JRadioButton rdbPagadas;
	private JRadioButton rdbTodas;

	public ListaFacturas() {
		setResizable(false);
		setTitle("Lista de Facturas | " + Inicio.empleadoActual.getNombre());

		setBounds(100, 100, 700, 305);
		getContentPane().setPreferredSize(new Dimension(700, 305));
		pack();

		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		ButtonGroup grupo = new ButtonGroup();

		rdbPendientes = new JRadioButton("Pendientes");
		rdbPendientes.setHorizontalAlignment(SwingConstants.CENTER);
		rdbPendientes.setOpaque(false);
		rdbPendientes.setBounds(380, 220, 90, 20);
		panelPrincipal.add(rdbPendientes);

		rdbPagadas = new JRadioButton("Pagadas");
		rdbPagadas.setHorizontalAlignment(SwingConstants.CENTER);
		rdbPagadas.setOpaque(false);
		rdbPagadas.setBounds(470, 220, 90, 20);
		panelPrincipal.add(rdbPagadas);

		rdbTodas = new JRadioButton("Todas");
		rdbTodas.setHorizontalAlignment(SwingConstants.CENTER);
		rdbTodas.setOpaque(false);
		rdbTodas.setBounds(560, 220, 90, 20);
		panelPrincipal.add(rdbTodas);

		grupo.add(rdbPendientes);
		grupo.add(rdbPagadas);
		grupo.add(rdbTodas);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(65, 255, 180, 40);
		panelPrincipal.add(btnVolver);

		btnCargar = new JButton("Cargar");
		btnCargar.setBounds(260, 255, 180, 40);
		panelPrincipal.add(btnCargar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(455, 255, 180, 40);
		panelPrincipal.add(btnEliminar);

		// ===== barras de desplazamiento =====
		JScrollPane scrollPendientes = new JScrollPane();
		scrollPendientes.setBackground(Color.LIGHT_GRAY);
		scrollPendientes.setBounds(50, 10, 600, 200);
		panelPrincipal.add(scrollPendientes);

		// ===== modelos =====
		// --- crear ---
		DefaultTableModel dtmFacturas = new DefaultTableModel();
		dtmFacturas.addColumn("ID Factura");
		dtmFacturas.addColumn("ID Orden");
		dtmFacturas.addColumn("Estado pago");
		dtmFacturas.addColumn("Método de pago");
		dtmFacturas.addColumn("Fecha");

		alFacturas = Datos.cargarFacturas();
		for (Factura f : alFacturas) {
			String pagada = General.pagadaAString(f.isPagada());

			dtmFacturas.addRow(
					new Object[] { f.getCodigo(), f.getCodigoOrden(), pagada, f.getMetodoPago(), f.getFecha() });
		}

		// --- asignar ---
		tblFacturas = new JTable(dtmFacturas) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblFacturas.setRowHeight(20);
		tblFacturas.setFillsViewportHeight(true);
		tblFacturas.getTableHeader().setReorderingAllowed(false);
		tblFacturas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		sorter = new TableRowSorter<TableModel>(dtmFacturas);
		tblFacturas.setRowSorter(sorter);

		scrollPendientes.setViewportView(tblFacturas);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		// - JButton -
		rdbPendientes.addActionListener(this);
		rdbPagadas.addActionListener(this);
		rdbTodas.addActionListener(this);

		btnCargar.addActionListener(this);
		btnVolver.addActionListener(this);
		btnEliminar.addActionListener(this);

		// ===== ajustes de usuario =====
		// --- fuente ---
		tblFacturas.getTableHeader().setFont(Inicio.fuenteObjetos);
		tblFacturas.setFont(Inicio.fuente);

		rdbPendientes.setFont(Inicio.fuente);
		rdbPagadas.setFont(Inicio.fuente);
		rdbTodas.setFont(Inicio.fuente);

		btnVolver.setFont(Inicio.fuenteObjetos);
		btnCargar.setFont(Inicio.fuenteObjetos);
		btnEliminar.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		tblFacturas.getTableHeader().setBackground(Inicio.colorFondoObjetos);
		tblFacturas.setBackground(Inicio.colorFondoObjetos);

		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnCargar.setBackground(Inicio.colorFondoObjetos);
		btnEliminar.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		tblFacturas.setForeground(Inicio.colorFuenteObjetos);

		rdbPendientes.setForeground(Inicio.colorFuente);
		rdbPagadas.setForeground(Inicio.colorFuente);
		rdbTodas.setForeground(Inicio.colorFuente);

		btnVolver.setForeground(Inicio.colorFuenteObjetos);
		btnCargar.setForeground(Inicio.colorFuenteObjetos);
		btnEliminar.setForeground(Inicio.colorFuenteObjetos);

		rdbPendientes.setSelected(true);
		filtrarFacturas();
	}

	private void filtrarFacturas() {
		int columna = 2;

		if (rdbPendientes.isSelected()) {
			sorter.setRowFilter(RowFilter.regexFilter("Pendiente", columna));
		} else if (rdbPagadas.isSelected()) {
			sorter.setRowFilter(RowFilter.regexFilter("Pagada", columna));
		} else {
			sorter.setRowFilter(null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o == btnCargar) {
			int row = tblFacturas.getSelectedRow();
			if (row >= 0) {
				int absRow = tblFacturas.convertRowIndexToModel(row);
				factura = alFacturas.get(absRow);

				MostrarFactura mf = new MostrarFactura();
				mf.cargarDatos(factura, !factura.isPagada());

				mf.setVisible(true);

				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, (String) "No hay ninguna factura seleccionada", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (o == btnEliminar) {
			int row = tblFacturas.getSelectedRow();
			if (row >= 0) {
				int opcion = JOptionPane.showOptionDialog(null, (String) "¿Seguro que quiere borrar la factura?",
						"AVISO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						new Object[] { "Sí", "No" }, "No");

				if (opcion == 0) {
					int absRow = tblFacturas.convertRowIndexToModel(row);

					Datos.borrarFactura(alFacturas.get(absRow));

					DefaultTableModel dtm = (DefaultTableModel) tblFacturas.getModel();
					dtm.removeRow(absRow);

					alFacturas.remove(absRow);
				}
			} else {
				JOptionPane.showMessageDialog(this, (String) "No hay ninguna factura seleccionada", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (o == btnVolver) {
			// btnVolver
			MenuListas lo = new MenuListas();
			lo.setVisible(true);

			this.dispose();
		} else {
			filtrarFacturas();
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