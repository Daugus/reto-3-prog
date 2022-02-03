package navegacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import administracion.AdministrarClientes;
import administracion.AdministrarVehiculos;
import clases.Cliente;
import clases.Fecha;
import clases.OrdenPrim;
import clases.Vehiculo;
import funciones.Archivos;
import funciones.Logs;
import funciones.Salir;

/**
 * 
 * @author Grupo 2
 *
 */
public class CrearOrdenPrim extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private DefaultComboBoxModel<String> dcbmClientes;
	private DefaultComboBoxModel<String> dcbmVehiculos;

	private JComboBox<String> cmbClientes;
	private JComboBox<String> cmbVehiculos;

	private JLabel lblComentario;
	private JLabel lblVehiculo;
	private JLabel lblCliente;

	private JButton btnVehiculos;
	private JButton btnClientes;
	
	private JButton btnVolver;
	private JButton btnCrearOrden;
	
	private JTextArea txtComentario;
	
	private static Cliente cliente;
	private static Vehiculo vehiculo;

	public CrearOrdenPrim()
	{
		setResizable(false);
		setTitle("Crear orden de trabajo");
		
		setBounds(100, 100, 750, 452);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		lblComentario = new JLabel("Agregar comentario para la orden");
		lblComentario.setBounds(44, 167, 226, 27);
		panelPrincipal.add(lblComentario);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(143, 371, 133, 31);
		panelPrincipal.add(btnVolver);
		
		btnCrearOrden = new JButton("Crear orden primaria");
		btnCrearOrden.setBounds(286, 371, 272, 31);
		panelPrincipal.add(btnCrearOrden);
		
		txtComentario = new JTextArea();
		txtComentario.setLineWrap(true);
		
		lblVehiculo = new JLabel("Vehículo");
		lblVehiculo.setBounds(54, 89, 99, 13);
		panelPrincipal.add(lblVehiculo);
		
		cmbVehiculos = new JComboBox<String>();
		cmbVehiculos.setBounds(149, 79, 151, 33);
		panelPrincipal.add(cmbVehiculos);
		
		btnVehiculos = new JButton("Administrar vehiculos");
		btnVehiculos.setBounds(334, 79, 222, 33);
		panelPrincipal.add(btnVehiculos);
		
		lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(54, 11, 85, 33);
		panelPrincipal.add(lblCliente);
		
		cmbClientes = new JComboBox<String>();
		cmbClientes.setBounds(149, 13, 151, 33);
		panelPrincipal.add(cmbClientes);
		
		btnClientes = new JButton("Administrar clientes");
		btnClientes.setBounds(334, 13, 222, 33);
		panelPrincipal.add(btnClientes);
		
		// ===== barras de desplazamiento =====
		JScrollPane barraScroll = new JScrollPane();
		barraScroll.setBounds(34, 196, 637, 118);
		panelPrincipal.add(barraScroll);

		barraScroll.setViewportView(txtComentario);
		
		// ===== modelos =====
		// --- crear ---
		dcbmClientes = new DefaultComboBoxModel<String>();
		dcbmClientes.addAll(Archivos.listarClientes());
		
		dcbmVehiculos = new DefaultComboBoxModel<String>();
		
		// --- asignar ---
		cmbClientes.setModel(dcbmClientes);
		cmbVehiculos.setModel(dcbmVehiculos);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		
		// --- Action ---
		cmbClientes.addActionListener(this);
		
		btnVolver.addActionListener(this);

		btnVehiculos.addActionListener(this);
		btnClientes.addActionListener(this);

		btnCrearOrden.addActionListener(this);

		// ===== ajustes de usuario =====
		// --- fuente ---
		lblCliente.setFont(Inicio.fuente);
		lblVehiculo.setFont(Inicio.fuente);
		lblComentario.setFont(Inicio.fuente);

		cmbClientes.setFont(Inicio.fuenteObjetos);
		cmbVehiculos.setFont(Inicio.fuenteObjetos);

		btnClientes.setFont(Inicio.fuenteObjetos);
		btnVehiculos.setFont(Inicio.fuenteObjetos);
		btnVolver.setFont(Inicio.fuenteObjetos);
		btnCrearOrden.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		cmbClientes.setBackground(Inicio.colorFondoObjetos);
		cmbVehiculos.setBackground(Inicio.colorFondoObjetos);

		btnClientes.setBackground(Inicio.colorFondoObjetos);
		btnVehiculos.setBackground(Inicio.colorFondoObjetos);
		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnCrearOrden.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		lblCliente.setForeground(Inicio.colorFuente);
		lblVehiculo.setForeground(Inicio.colorFuente);
		lblComentario.setForeground(Inicio.colorFuente);

		cmbClientes.setForeground(Inicio.colorFuenteObjetos);
		cmbVehiculos.setForeground(Inicio.colorFuenteObjetos);

		btnClientes.setForeground(Inicio.colorFuenteObjetos);
		btnVehiculos.setForeground(Inicio.colorFuenteObjetos);
		btnVolver.setForeground(Inicio.colorFuenteObjetos);
		btnCrearOrden.setForeground(Inicio.colorFuenteObjetos);
	}
	
	public static Cliente getCliente()
	{
		return cliente;
	}
	
	public static Vehiculo getVehiculo()
	{
		return vehiculo;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();

		if (o == cmbClientes && cmbClientes.getSelectedIndex() >= 0)
		{
			dcbmVehiculos.removeAllElements();
			cliente = Archivos.cargarCliente((String) cmbClientes.getSelectedItem());
			dcbmVehiculos.addAll(cliente.getVehiculos());
		}
		else if (o == btnClientes)
		{
			AdministrarClientes ac = new AdministrarClientes();
			ac.setLocationRelativeTo(null);
			ac.setVisible(true);
			
			this.dispose();
		}
		else if (o == btnVehiculos)
		{
			AdministrarVehiculos av = new AdministrarVehiculos();
			av.setLocationRelativeTo(null);
			av.setVisible(true);

			this.dispose();
		}
		else if (o == btnVolver)
		{
			MenuAtc ma = new MenuAtc();
			ma.setLocationRelativeTo(null);
			ma.setVisible(true);

			this.dispose();
		}
		else if (o == btnCrearOrden)
		{
		    // --- cliente ---
			if (cmbClientes.getSelectedIndex() >= 0)
			{
		    	String dni = (String) cmbClientes.getSelectedItem();
		    	cliente = Archivos.cargarCliente(dni);

		    	// --- vehículo ---
		    	if (cmbVehiculos.getSelectedIndex() >= 0)
		    	{
		    		String matricula = (String) cmbVehiculos.getSelectedItem();
		    		//System.out.println(matricula);
		    		vehiculo = Archivos.cargarVehiculo(matricula);
		    		//System.out.println(vehiculo);
		    		// ---- guarda la orden en archiivo log
		    		Logs.ordenLog("nueva orden: "+ vehiculo.toString());

		    		// --- orden primaria ---
		    		Calendar calendar = Calendar.getInstance();
		    		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		    		String codOrdenPrim = formatter.format(calendar.getTime());
		    		String comentarios = txtComentario.getText();
		    		
		    		cmbClientes.setSelectedIndex(-1);
		    		cmbVehiculos.setSelectedIndex(-1);
		    		txtComentario.setText("");
		    		
		    		Archivos.guardarOrdenPrim(new OrdenPrim(codOrdenPrim, comentarios, new Fecha(), cliente, vehiculo));
		    		
		    	}
		    	else
		    	{
		    		JOptionPane.showMessageDialog (null, "Por favor seleccione un vehículo", "ERROR",
		    				JOptionPane.ERROR_MESSAGE);
		    		Logs.erroresLog("No se ha seleccionado ningun vehiculo");
		    	}
			}
			else
			{
				JOptionPane.showMessageDialog (null, "Por favor seleccione un cliente", "ERROR",
						JOptionPane.ERROR_MESSAGE);
				Logs.erroresLog("No se ha seleccionado ningun cliente");
			}

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