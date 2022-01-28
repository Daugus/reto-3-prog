package administracion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import clases.Cliente;
import edicion.EditarCliente;
import funciones.Archivos;
import funciones.Salir;
import navegacion.CrearOrdenPrim;
import navegacion.Inicio;

/**
 * 
 * @author Grupo 2
 *
 */
public class AdministrarClientes extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;
	
	public static DefaultListModel<Cliente> dlmClientes;
	private JList<Cliente> lstClientes;

	private JButton btnVolver;
	private JButton btnEditar;
	private JButton btnAgregar;
	
	private Cliente cliente;

	public AdministrarClientes()
	{
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Administrar clientes");
		
		setBounds(100, 100, 750, 553);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		lstClientes = new JList<Cliente>();
		lstClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 445, 174, 58);
		panelPrincipal.add(btnVolver);
		
		btnAgregar = new JButton("Agregar cliente");
		btnAgregar.setBounds(62, 60, 269, 62);
		panelPrincipal.add(btnAgregar);
		
		btnEditar = new JButton("Editar cliente");
		btnEditar.setBounds(380, 60, 269, 62);
		panelPrincipal.add(btnEditar);

		// ===== barras de desplazamiento =====
		JScrollPane scrollClientes = new JScrollPane();
		scrollClientes.setBounds(62, 159, 584, 203);
		panelPrincipal.add(scrollClientes);

		scrollClientes.setViewportView(lstClientes);
		
		// ===== modelos =====
		// --- crear ---
		dlmClientes = new DefaultListModel<Cliente>();
		dlmClientes.addAll(Archivos.cargarTodosClientes());

		// --- asignar ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		lstClientes.setModel(dlmClientes);

		// ===== Listeners =====
		// --- Window ---
		addWindowListener(this);

		// --- Action ---
		btnVolver.addActionListener(this);
		btnAgregar.addActionListener(this);
		btnEditar.addActionListener(this);
		
		// ===== ajustes de usuario =====
		// --- fuente ---
		lstClientes.setFont(Inicio.fuenteObjetos);

		btnVolver.setFont(Inicio.fuenteObjetos);
		btnAgregar.setFont(Inicio.fuenteObjetos);
		btnEditar.setFont(Inicio.fuenteObjetos);
		
		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		lstClientes.setBackground(Inicio.colorFondoObjetos);

		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnAgregar.setBackground(Inicio.colorFondoObjetos);
		btnEditar.setBackground(Inicio.colorFondoObjetos);

		scrollClientes.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		lstClientes.setForeground(Inicio.colorFuenteObjetos);

		btnVolver.setForeground(Inicio.colorFuenteObjetos);
		btnAgregar.setForeground(Inicio.colorFuenteObjetos);
		btnEditar.setForeground(Inicio.colorFuenteObjetos);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		
		if (o == btnAgregar)
		{
			EditarCliente ec = new EditarCliente();
			ec.setLocationRelativeTo(null);
			ec.setVisible(true);
		}
		else if (o == btnEditar)
		{
			if (lstClientes.getSelectedIndex() >= 0)
			{
				cliente = lstClientes.getSelectedValue();

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