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

import clases.Cliente;
import edicion.EditarCliente;
import funciones.Archivos;
import funciones.Salir;
import funciones.Tablas;
import navegacion.Inicio;
import ordenes.CrearPrimaria;

/**
 * 
 * @author Grupo 2
 *
 */
public class AdministrarClientes extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;
	private static JTable tblClientes;
	private static JButton btnVolver;
	private static JButton btnEditar;
	private static JButton btnAgregar;
	
	private Cliente cliente;
	
	private static boolean bloqueado;

	public AdministrarClientes()
	{
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Administrar clientes");
		
		setBounds(100, 100, 700, 360);
		getContentPane().setPreferredSize(new Dimension(700, 360));
		pack();

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(260, 310, 180, 40);
		panelPrincipal.add(btnVolver);
		
		btnAgregar = new JButton("Agregar cliente");
		btnAgregar.setBounds(50, 10, 230, 60);
		panelPrincipal.add(btnAgregar);
		
		btnEditar = new JButton("Editar cliente");
		btnEditar.setBounds(420, 10, 230, 60);
		panelPrincipal.add(btnEditar);

		// ===== barras de desplazamiento =====
		JScrollPane scrollClientes = new JScrollPane();
		scrollClientes.setBounds(50, 85, 600, 200);
		panelPrincipal.add(scrollClientes);

		// ===== modelos =====
		// --- crear ---
		DefaultTableModel dtmClientes = new DefaultTableModel();
		dtmClientes.addColumn("DNI");
		dtmClientes.addColumn("Nombre");
		dtmClientes.addColumn("Apellidos");
		dtmClientes.addColumn("Fecha alta");

		// --- asignar ---
		tblClientes = new JTable(dtmClientes)
		{
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		tblClientes.setRowHeight(20);
		tblClientes.setFillsViewportHeight(true);
		tblClientes.getTableHeader().setReorderingAllowed(false);
		tblClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblClientes.getTableHeader().setBackground(Inicio.colorFondoObjetos);
		tblClientes.getTableHeader().setFont(Inicio.fuenteObjetos);
		
		actualizarTabla();

		scrollClientes.setViewportView(tblClientes);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		btnVolver.addActionListener(this);
		btnAgregar.addActionListener(this);
		btnEditar.addActionListener(this);
		
		// ===== ajustes de usuario =====
		// --- fuente 
		tblClientes.setFont(Inicio.fuente);

		btnVolver.setFont(Inicio.fuenteObjetos);
		btnAgregar.setFont(Inicio.fuenteObjetos);
		btnEditar.setFont(Inicio.fuenteObjetos);
		
		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		tblClientes.setBackground(Inicio.colorFondoObjetos);

		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnAgregar.setBackground(Inicio.colorFondoObjetos);
		btnEditar.setBackground(Inicio.colorFondoObjetos);

		scrollClientes.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		tblClientes.setForeground(Inicio.colorFuenteObjetos);

		btnVolver.setForeground(Inicio.colorFuenteObjetos);
		btnAgregar.setForeground(Inicio.colorFuenteObjetos);
		btnEditar.setForeground(Inicio.colorFuenteObjetos);
	}
	
	public static void actualizarTabla()
	{
		DefaultTableModel dtm = (DefaultTableModel) tblClientes.getModel();
		
		dtm.setRowCount(0);
		
		ArrayList<Cliente> clientes = Archivos.cargarTodosClientes();
		for (Cliente c : clientes)
		{
			dtm.addRow(new Object[] {c.getDNI(), c.getNombre(), c.getApellidos(), c.getFechaAlta()});
		}

		Tablas.ajustarColumnas(tblClientes);
	}
	
	public static void botones(boolean estado)
	{
		btnAgregar.setEnabled(estado);
		btnEditar.setEnabled(estado);
		btnVolver.setEnabled(estado);
		
		bloqueado = !estado;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		
		if (o == btnAgregar)
		{
			botones(false);

			EditarCliente ec = new EditarCliente();
			ec.setLocationRelativeTo(null);
			ec.setVisible(true);
		}
		else if (o == btnEditar)
		{
			int row = tblClientes.getSelectedRow();
			if (row >= 0)
			{
				cliente = Archivos.cargarCliente((String) tblClientes.getValueAt(row, 0));
				
				botones(false);

				EditarCliente ec = new EditarCliente();
				ec.modoEdicion(cliente);
			
				ec.setLocationRelativeTo(null);
				ec.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(this, (String) "No hay ningún cliente seleccionado", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (o == btnVolver)
		{
			CrearPrimaria cop = new CrearPrimaria();
			cop.setLocationRelativeTo(null);
			cop.setVisible(true);
			
			this.dispose();
		} 
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		if (bloqueado)
		{
			Salir.error();
		}
		else
		{
			Salir.general(this);
		}
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