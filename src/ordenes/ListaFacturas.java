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

	private ArrayList<Factura> alFacturas;

	private static Factura factura;

	public ListaFacturas() {
		setResizable(false);
		setTitle("Lista de Facturas | " + Inicio.empleadoActual.getNombre());

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
		DefaultTableModel dtmFacturas = new DefaultTableModel();
		dtmFacturas.addColumn("ID Factura");
		dtmFacturas.addColumn("ID Orden");
		dtmFacturas.addColumn("Estado");
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

		scrollPendientes.setViewportView(tblFacturas);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		// - JButton -
		btnCargar.addActionListener(this);
		btnVolver.addActionListener(this);

		tblFacturas.getTableHeader().setFont(Inicio.fuenteObjetos);
		tblFacturas.setFont(Inicio.fuente);

		btnVolver.setFont(Inicio.fuenteObjetos);
		btnCargar.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		tblFacturas.getTableHeader().setBackground(Inicio.colorFondoObjetos);
		tblFacturas.setBackground(Inicio.colorFondoObjetos);

		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnCargar.setBackground(Inicio.colorFondoObjetos);

		tblFacturas.setForeground(Inicio.colorFuenteObjetos);

		btnVolver.setForeground(Inicio.colorFuenteObjetos);
		btnCargar.setForeground(Inicio.colorFuenteObjetos);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o == btnCargar) {
			int row = tblFacturas.getSelectedRow();
			if (row >= 0) {
				try {
					factura = alFacturas.get(row);

					// TODO: implementar
					JOptionPane.showMessageDialog(null, "Se ha cargado la factura " + factura.getCodigo(),
							"Sin implementar", JOptionPane.INFORMATION_MESSAGE);

//					MostrarFactura mf = new MostrarFactura();
//					mf.cargarDatos(factura);
//
//					mf.setVisible(true);
//
//					this.dispose();
				} catch (NullPointerException npe) {
					JOptionPane.showMessageDialog(null, "La factura seleccionada no existe", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, (String) "No hay ninguna factura seleccionada", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			// btnVolver
			MenuListas lo = new MenuListas();
			lo.setVisible(true);

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