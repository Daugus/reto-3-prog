package ordenes;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
import clases.Primaria;
import clases.Vehiculo;
import funciones.Archivos;
import funciones.Log;
import funciones.Salir;
import navegacion.Inicio;
import navegacion.MenuAtc;

/**
 * 
 * @author Grupo 2
 *
 */
public class CrearPrimaria extends JFrame implements ActionListener, WindowListener
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
	
	private Cliente cliente;
	private Vehiculo vehiculo;

	public CrearPrimaria()
	{
		setResizable(false);
		setTitle("Crear orden de trabajo");
		
		setBounds(100, 100, 600, 355);
		getContentPane().setPreferredSize(new Dimension(600, 355));
		pack();

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		lblComentario = new JLabel("Agregar comentario para la orden:");
		lblComentario.setBounds(50, 145, 300, 35);
		panelPrincipal.add(lblComentario);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(112, 305, 180, 40);
		panelPrincipal.add(btnVolver);
		
		btnCrearOrden = new JButton("Crear orden primaria");
		btnCrearOrden.setBounds(308, 305, 180, 40);
		panelPrincipal.add(btnCrearOrden);
		
		txtComentario = new JTextArea();
		txtComentario.setLineWrap(true);
		
		lblVehiculo = new JLabel("Vehículo:");
		lblVehiculo.setBounds(50, 97, 100, 36);
		panelPrincipal.add(lblVehiculo);
		
		cmbVehiculos = new JComboBox<String>();
		cmbVehiculos.setBounds(150, 97, 150, 36);
		panelPrincipal.add(cmbVehiculos);
		
		btnVehiculos = new JButton("Administrar vehiculos");
		btnVehiculos.setBounds(320, 85, 230, 60);
		panelPrincipal.add(btnVehiculos);
		
		lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(50, 22, 100, 36);
		panelPrincipal.add(lblCliente);
		
		cmbClientes = new JComboBox<String>();
		cmbClientes.setBounds(150, 22, 150, 36);
		panelPrincipal.add(cmbClientes);
		
		btnClientes = new JButton("Administrar clientes");
		btnClientes.setBounds(320, 10, 230, 60);
		panelPrincipal.add(btnClientes);
		
		// ===== barras de desplazamiento =====
		JScrollPane barraScroll = new JScrollPane();
		barraScroll.setBounds(50, 180, 500, 100);
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

		txtComentario.setFont(Inicio.fuenteObjetos);

		cmbClientes.setFont(Inicio.fuenteObjetos);
		cmbVehiculos.setFont(Inicio.fuenteObjetos);

		btnClientes.setFont(Inicio.fuenteObjetos);
		btnVehiculos.setFont(Inicio.fuenteObjetos);
		btnVolver.setFont(Inicio.fuenteObjetos);
		btnCrearOrden.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		txtComentario.setBackground(Inicio.colorFondoObjetos);

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

		txtComentario.setForeground(Inicio.colorFuenteObjetos);

		cmbClientes.setForeground(Inicio.colorFuenteObjetos);
		cmbVehiculos.setForeground(Inicio.colorFuenteObjetos);

		btnClientes.setForeground(Inicio.colorFuenteObjetos);
		btnVehiculos.setForeground(Inicio.colorFuenteObjetos);
		btnVolver.setForeground(Inicio.colorFuenteObjetos);
		btnCrearOrden.setForeground(Inicio.colorFuenteObjetos);
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
		    		vehiculo = Archivos.cargarVehiculo(matricula);

		    		if (!vehiculo.getBastidor().equals(""))
		    		{
		    			// --- orden primaria ---
		    			String comentarios = txtComentario.getText();
		    			
		    			cmbClientes.setSelectedIndex(-1);
		    			cmbVehiculos.setSelectedIndex(-1);
		    			txtComentario.setText("");
		    			
		    			Archivos.guardarPrimaria(new Primaria(comentarios, cliente, vehiculo, Inicio.cuentaActual));
		    			
		    			JOptionPane.showMessageDialog(this, (String) "Se ha creado la order primaria", "INFO",
		    					JOptionPane.INFORMATION_MESSAGE);
		    		}
		    		else
		    		{
						JOptionPane.showMessageDialog (null, "El vehículo seleccionado no tiene la información necesaria", "ERROR",
								JOptionPane.ERROR_MESSAGE);
		    		}
		    	}
		    	else
		    	{
		    		JOptionPane.showMessageDialog (null, "Por favor seleccione un vehículo", "ERROR",
		    				JOptionPane.ERROR_MESSAGE);
		    		Log.error("No se ha seleccionado ningun vehiculo");
		    	}
			}
			else
			{
				JOptionPane.showMessageDialog (null, "Por favor seleccione un cliente", "ERROR",
						JOptionPane.ERROR_MESSAGE);
				Log.error("No se ha seleccionado ningun cliente");
			}

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