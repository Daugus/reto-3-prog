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

import clases.Pendiente;
import funciones.Archivos;
import funciones.Salir;
import navegacion.Inicio;
import navegacion.ListaOrdenes;

/**
 * 
 * @author Grupo 2
 *
 */
public class ListaPendientes extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = 1531539371445418371L;
	private JPanel panelPrincipal;
	
	private JTable tblPendientes;

	private JButton btnVolver;
	private JButton btnCargar;

	ArrayList<Pendiente> alPendientes;

	private static Pendiente ordenPend;

	public ListaPendientes()
	{
		setResizable(false);
		setTitle("Lista de Órdenes Pendientes | " + Inicio.cuentaActual.getNombre());

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
		DefaultTableModel dtmPendientes = new DefaultTableModel();
		dtmPendientes.addColumn("Fecha");
		dtmPendientes.addColumn("Cliente");
		dtmPendientes.addColumn("Vehículo");
		
		alPendientes = Archivos.cargarTodosPendientes();
		alPendientes.sort(Comparator.reverseOrder());
		for (Pendiente op : alPendientes)
		{
			dtmPendientes.addRow(new Object[] {op.getFecha(), op.getPropietario().getDNI(), op.getVehiculo().getMatricula()});
		}

		// --- asignar ---
		tblPendientes = new JTable(dtmPendientes)
		{
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		tblPendientes.setRowHeight(20);
		tblPendientes.setFillsViewportHeight(true);
		tblPendientes.getTableHeader().setReorderingAllowed(false);
		tblPendientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPendientes.setViewportView(tblPendientes);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		// - JButton -
		btnCargar.addActionListener(this);
		btnVolver.addActionListener(this);
		
		tblPendientes.getTableHeader().setFont(Inicio.fuenteObjetos);
		tblPendientes.setFont(Inicio.fuente);

		btnVolver.setFont(Inicio.fuenteObjetos);
		btnCargar.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		tblPendientes.getTableHeader().setBackground(Inicio.colorFondoObjetos);
		tblPendientes.setBackground(Inicio.colorFondoObjetos);

		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnCargar.setBackground(Inicio.colorFondoObjetos);
		
		tblPendientes.setForeground(Inicio.colorFuenteObjetos);

		btnVolver.setForeground(Inicio.colorFuenteObjetos);
		btnCargar.setForeground(Inicio.colorFuenteObjetos);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();

		if (o == btnCargar)
		{
			int row = tblPendientes.getSelectedRow();
			if (row >= 0)
			{
				try
				{
					ordenPend = alPendientes.get(row);
					
					GenerarFactura gf = new GenerarFactura();
					gf.cargarDatos(ordenPend);
					
					gf.setVisible(true);
					
					this.dispose();
				}
				catch (NullPointerException npe)
				{
					JOptionPane.showMessageDialog (null, "La Orden Pendiente seleccionada no existe", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, (String) "No hay ninguna orden seleccionada", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			// btnVolver
			ListaOrdenes lo = new ListaOrdenes();
			lo.setVisible(true);

			this.dispose();
		}
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
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