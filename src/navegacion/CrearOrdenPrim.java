package navegacion;

import java.awt.Font;
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

	private JLabel lblAgregraComentario;
	private JLabel lblAccesoVehiculo;
	private JLabel lblAccesoCliente;

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
		panelPrincipal.setForeground(Inicio.colorFuente);
		panelPrincipal.setBackground(Inicio.colorFondo);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		lblAgregraComentario = new JLabel("Agregar comentario para la orden");
		lblAgregraComentario.setForeground(Inicio.colorFuente);
		lblAgregraComentario.setFont(new Font(Inicio.fuente, Font.PLAIN, 13));
		lblAgregraComentario.setBounds(44, 167, 226, 27);
		panelPrincipal.add(lblAgregraComentario);
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		btnVolver.setBounds(143, 371, 133, 31);
		panelPrincipal.add(btnVolver);
		
		btnCrearOrden = new JButton("Crear orden primaria");
		btnCrearOrden.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		btnCrearOrden.setBounds(286, 371, 272, 31);
		panelPrincipal.add(btnCrearOrden);
		
		txtComentario = new JTextArea();
		txtComentario.setLineWrap(true);
		
		lblAccesoVehiculo = new JLabel("Vehículo");
		lblAccesoVehiculo.setForeground(Inicio.colorFuente);
		lblAccesoVehiculo.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblAccesoVehiculo.setBounds(54, 89, 99, 13);
		panelPrincipal.add(lblAccesoVehiculo);
		
		cmbVehiculos = new JComboBox<String>();
		cmbVehiculos.setBounds(149, 79, 151, 33);
		panelPrincipal.add(cmbVehiculos);
		
		btnVehiculos = new JButton("Administrar vehiculos");
		btnVehiculos.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		btnVehiculos.setBounds(334, 79, 222, 33);
		panelPrincipal.add(btnVehiculos);
		
		lblAccesoCliente = new JLabel("Cliente");
		lblAccesoCliente.setForeground(Inicio.colorFuente);
		lblAccesoCliente.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblAccesoCliente.setBounds(54, 11, 85, 33);
		panelPrincipal.add(lblAccesoCliente);
		
		cmbClientes = new JComboBox<String>();
		cmbClientes.setBounds(149, 13, 151, 33);
		panelPrincipal.add(cmbClientes);
		
		btnClientes = new JButton("Administrar clientes");
		btnClientes.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
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
		    		System.out.println(matricula);
		    		vehiculo = Archivos.cargarVehiculo(matricula);
		    		System.out.println(vehiculo);

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
		    	}
			}
			else
			{
				JOptionPane.showMessageDialog (null, "Por favor seleccione un cliente", "ERROR",
						JOptionPane.ERROR_MESSAGE);
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