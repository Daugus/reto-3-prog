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

import clases.Factura;
import clases.Pendiente;
import funciones.Archivos;
import funciones.Salir;
import navegacion.Inicio;
import navegacion.ListaOrdenes;

/**
 * esta clase difiene la ventana ListaFacturas
 * 
 * @author Grupo 2
 * @version 2.0.1
 */
public class ListaFacturas extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1531539371445418371L;
	private JPanel panelPrincipal;

	private JTable tblFacturas;

	private JButton btnVolver;
	private JButton btnCargar;

	private ArrayList<Factura> alFacturas;

	private static Factura factura;

	/**
	 * constructor que carga la ventana ListaFacturas
	 */
	public ListaFacturas() {
		setResizable(false);
		setTitle("Lista de Facturas | " + Inicio.cuentaActual.getNombre());

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
		dtmFacturas.addColumn("Fecha");
		dtmFacturas.addColumn("Cliente");
		dtmFacturas.addColumn("Vehículo");

		alFacturas = Archivos.cargarTodosFacturas();
		alFacturas.sort(Comparator.reverseOrder());
		for (Pendiente op : alFacturas) {
			dtmFacturas.addRow(
					new Object[] { op.getFecha(), op.getPropietario().getDNI(), op.getVehiculo().getMatricula() });
		}

		// --- asignar ---
		tblFacturas = new JTable(dtmFacturas) {
			private static final long serialVersionUID = 1L;

			/**
			 * 
			 * @param row    cuyo valor se va a consultar
			 * @param column cuyo valor se va a consultar
			 * @return Devuelve verdadero si la celda en la fila y la columna es editable.
			 *         De lo contrario, invocar setValueAt en la celda no tendrá ningún
			 *         efecto.
			 */
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

	/**
	 * 
	 * invocado cuando una accion ocurre sobre los elementos
	 * 
	 * @param e el evento a procesar
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o == btnCargar) {
			int row = tblFacturas.getSelectedRow();
			if (row >= 0) {
				try {
					factura = alFacturas.get(row);

					MostrarFactura mf = new MostrarFactura();
					mf.cargarDatos(factura);

					mf.setVisible(true);

					this.dispose();
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
			ListaOrdenes lo = new ListaOrdenes();
			lo.setVisible(true);

			this.dispose();
		}
	}

	/**
	 * invocado cuando el usuario intenta cerrar la ventana
	 * 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		Salir.general(this);
	}

	/**
	 * Invocado la primera vez una ventana se ha checho visible
	 * 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		// comportamiento por defecto
	}

	/**
	 * Invocado cuando una ventana se cerro como resultado llamando a dispose en la
	 * ventana
	 * 
	 * @param e evento a procesar
	 */
	@Override
	public void windowClosed(WindowEvent e) {
		// comportamiento por defecto
	}

	/**
	 * Invocado cuando a una ventana se cambio de normal a minimizado por varias
	 * plataformas una minimizada ventana se procesa como el icono especificado en
	 * la propiedad de siconImage
	 * 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowIconified(WindowEvent e) {
		// comportamiento por defecto
	}

	/**
	 * cuando una ventana cambia de minimizado a ventana normal
	 * 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowDeiconified(WindowEvent e) {
		// comportamiento por defecto
	}

	/**
	 * Invocado cuando la ventana es capacitado a ser ventana activa solo un frame o
	 * un dialog puede ser ventana activa
	 * 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		// comportamiento por defecto
	}

	/**
	 * Invocado cuando una ventana no es langer la ventana activa solo un Frame o un
	 * Dialog puede ser ventana activa
	 * 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowDeactivated(WindowEvent e) {
		// comportamiento por defecto
	}
}