package navegacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.OrdenPrim;
import funciones.Archivos;
import funciones.Logs;
import funciones.Salir;

/**
 * 
 * @author Grupo 2
 *
 */

public class ListaOrdenesPrim extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = 1531539371445418371L;
	private JPanel panelPrincipal;
	
	private JTable tblPrimarias;

	private JButton btnVolver;
	private JButton btnCargar;

	ArrayList<OrdenPrim> alPrimarias;

	private static OrdenPrim ordenPrim;

	public ListaOrdenesPrim()
	{
		setResizable(false);
		setTitle("Lista de Órdenes Primarias");
		setBounds(100, 100, 750, 429);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		JLabel lblTitulo = new JLabel("Listado de Órdenes Primarias");
		lblTitulo.setBounds(27, 22, 354, 26);
		panelPrincipal.add(lblTitulo);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(194, 302, 137, 46);
		panelPrincipal.add(btnVolver);
		
		btnCargar = new JButton("Acceder");
		btnCargar.setBounds(396, 302, 137, 46);
		panelPrincipal.add(btnCargar);
		
		// ===== barras de desplazamiento =====
		JScrollPane scrollPrimarias = new JScrollPane();
		scrollPrimarias.setBackground(Color.LIGHT_GRAY);
		scrollPrimarias.setBounds(59, 59, 584, 203);
		panelPrincipal.add(scrollPrimarias);

		// ===== modelos =====
		// --- crear ---
		DefaultTableModel dtmPrimarias = new DefaultTableModel();
		dtmPrimarias.addColumn("Fecha");
		dtmPrimarias.addColumn("Cliente");
		dtmPrimarias.addColumn("Vehículo");
		
		alPrimarias = Archivos.cargarTodosOrdenPrim();
		alPrimarias.sort(Comparator.reverseOrder());
		for (OrdenPrim op : alPrimarias)
		{
			dtmPrimarias.addRow(new Object[] {op.getFechaEntrada(), op.getPropietario().getDNI(), op.getVehiculo().getMatricula()});
		}
		
		// --- asignar ---
		tblPrimarias = new JTable(dtmPrimarias)
		{
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
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

		// ===== ajustes de usuario =====
		// --- fuente ---
		lblTitulo.setFont(Inicio.fuente);
		
		tblPrimarias.getTableHeader().setFont(Inicio.fuenteObjetos);
		tblPrimarias.setFont(Inicio.fuenteObjetos);

		btnVolver.setFont(Inicio.fuenteObjetos);
		btnCargar.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		tblPrimarias.getTableHeader().setBackground(Inicio.colorFondoObjetos);
		tblPrimarias.setBackground(Inicio.colorFondoObjetos);

		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnCargar.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		lblTitulo.setForeground(Inicio.colorFuente);
		
		tblPrimarias.setForeground(Inicio.colorFuenteObjetos);

		btnVolver.setForeground(Inicio.colorFuenteObjetos);
		btnCargar.setForeground(Inicio.colorFuenteObjetos);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();

		if (o == btnCargar)
		{
			try
			{
				ordenPrim = alPrimarias.get(tblPrimarias.getSelectedRow());

				CrearOrdenPend cop = new CrearOrdenPend();
				cop.cargarDatos(ordenPrim);

				cop.setLocationRelativeTo(null);
				cop.setVisible(true);

				this.dispose();
			}
			catch (NullPointerException npe)
			{
				JOptionPane.showMessageDialog (null, "La Orden Primaria seleccionada no existe", "ERROR",
						JOptionPane.ERROR_MESSAGE);
				Logs.error("Intento de acceder a una Orden Primaria no existe");
			}
		}
		else
		{
			// btnVolver
			if (Inicio.cuentaActual.getMecanico())
			{
				MenuMec mm = new MenuMec();
				mm.setLocationRelativeTo(null);
				mm.setVisible(true);
			}
			else {
				ListaOrdenes lo = new ListaOrdenes();
				lo.setLocationRelativeTo(null);
				lo.setVisible(true);
			}

			this.dispose();
		}
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		Salir.siNo();
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