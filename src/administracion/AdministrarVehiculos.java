package administracion;

import java.awt.Color;
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

import clases.Vehiculo;
import edicion.EditarVehiculo;
import funciones.Archivos;
import funciones.Salir;
import navegacion.CrearOrdenPrim;
import navegacion.Inicio;

/**
 * 
 * @author Grupo 2
 *
 */
public class AdministrarVehiculos extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;
	
	public static JTable tblVehiculos;

	private JButton btnVolver;
	private JButton btnEditar;
	private JButton btnAgregar;

	private Vehiculo vehiculo;

	public AdministrarVehiculos()
	{
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Administrar vehiculos");
		
		setBounds(100, 100, 750, 553);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Inicio.colorFondo);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 445, 174, 58);
		panelPrincipal.add(btnVolver);
		
		btnAgregar = new JButton("Agregar vehículo");
		btnAgregar.setBounds(62, 60, 269, 62);
		panelPrincipal.add(btnAgregar);
		
		btnEditar = new JButton("Editar vehículo");
		btnEditar.setBounds(380, 60, 269, 62);
		panelPrincipal.add(btnEditar);

		// ===== barras de desplazamiento =====
		JScrollPane scrollVehiculos = new JScrollPane();
		scrollVehiculos.setBounds(62, 159, 584, 203);
		panelPrincipal.add(scrollVehiculos);
		
		// ===== modelos =====
		// --- crear ---
		DefaultTableModel dtmVehiculos = new DefaultTableModel();
		dtmVehiculos.addColumn("Matrícula");
		dtmVehiculos.addColumn("Propietario");
		dtmVehiculos.addColumn("Modelo");
		
		// --- asignar ---
		tblVehiculos = new JTable(dtmVehiculos)
		{
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		tblVehiculos.setFillsViewportHeight(true);
		tblVehiculos.getTableHeader().setReorderingAllowed(false);
		tblVehiculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblVehiculos.getTableHeader().setBackground(Inicio.colorFondoObjetos);
		tblVehiculos.getTableHeader().setFont(Inicio.fuenteObjetos);
		
		actualizarTabla();

		scrollVehiculos.setViewportView(tblVehiculos);

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
		tblVehiculos.setFont(Inicio.fuente);

		btnVolver.setFont(Inicio.fuenteObjetos);
		btnAgregar.setFont(Inicio.fuenteObjetos);
		btnEditar.setFont(Inicio.fuenteObjetos);
		
		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		tblVehiculos.setBackground(Inicio.colorFondoObjetos);

		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnAgregar.setBackground(Inicio.colorFondoObjetos);
		btnEditar.setBackground(Inicio.colorFondoObjetos);
		
		scrollVehiculos.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		tblVehiculos.setForeground(Inicio.colorFuenteObjetos);

		btnVolver.setForeground(Inicio.colorFuenteObjetos);
		btnAgregar.setForeground(Inicio.colorFuenteObjetos);
		btnEditar.setForeground(Inicio.colorFuenteObjetos);
	}
	
	public static void actualizarTabla()
	{
		DefaultTableModel dtm = (DefaultTableModel) tblVehiculos.getModel();
		
		dtm.setRowCount(0);
		
		ArrayList<Vehiculo> vehiculos = Archivos.cargarTodosVehiculos();
		for (Vehiculo v : vehiculos)
		{
			dtm.addRow(new Object[] {v.getMatricula(), v.getPropietario(), v.getMarca() + " " + v.getModelo()});
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		
		if (o == btnAgregar)
		{
			EditarVehiculo ev = new EditarVehiculo();
			ev.setLocationRelativeTo(null);
			ev.setVisible(true);
		}
		else if (o == btnEditar)
		{
			int row = tblVehiculos.getSelectedRow();
			if (row >= 0)
			{
				vehiculo = Archivos.cargarVehiculo((String) tblVehiculos.getValueAt(row, 0));

				EditarVehiculo ev = new EditarVehiculo();
				ev.modoEdicion(vehiculo);
			
				ev.setLocationRelativeTo(null);
				ev.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(this, (String) "No hay ningún vehículo seleccionado", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (o == btnVolver)
		{
			CrearOrdenPrim cop = new CrearOrdenPrim();
			cop.setLocationRelativeTo(null);
			cop.setVisible(true);
			
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