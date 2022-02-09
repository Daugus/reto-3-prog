package edicion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import administracion.AdministrarVehiculos;
import clases.Cliente;
import clases.Fecha;
import clases.Reparacion;
import clases.Vehiculo;
import funciones.Archivos;
import funciones.Salir;
import navegacion.Inicio;

/**
 * esta clase edita datos de un vehiculo
 * @author Grupo 2
 * @version 2.0.1
 */
public class EditarVehiculo extends JFrame implements ActionListener, FocusListener, WindowListener
{
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private DefaultComboBoxModel<String> dcbmClientes;
	private JComboBox<String> cmbClientes;

	private JTextField txtMatricula;
	private JTextField txtBastidor;

	private JTextField txtMarca;
	private JTextField txtModelo;
	private JTextField txtColor;

	private JTextField txtCilindrada;

	private JTextField txtKmRecorridos;
	private JTextField txtFechaITV;

	private JTextField txtTipo;
	
	private JButton btnCancelar;
	private JButton btnGuardar;
	
	private boolean edicion;
	/**
	 * constructor añade los elementos de la ventana
	 *  
	 */
	public EditarVehiculo() {
		setResizable(false);
		setTitle("Agregar nuevo vehículo | " + Inicio.cuentaActual.getNombre());
		
		setBounds(100, 100, 630, 330);
		getContentPane().setPreferredSize(new Dimension(630, 330));
		pack();
		
		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(127, 265, 180, 40);
		panelPrincipal.add(btnCancelar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(323, 265, 180, 40);
		panelPrincipal.add(btnGuardar);
		
		JLabel lblKmRecorridos = new JLabel("KM recorridos:");
		lblKmRecorridos.setHorizontalAlignment(SwingConstants.LEFT);
		lblKmRecorridos.setBounds(50, 205, 100, 35);
		panelPrincipal.add(lblKmRecorridos);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setHorizontalAlignment(SwingConstants.LEFT);
		lblMarca.setBounds(50, 115, 100, 35);
		panelPrincipal.add(lblMarca);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setHorizontalAlignment(SwingConstants.LEFT);
		lblModelo.setBounds(330, 115, 100, 35);
		panelPrincipal.add(lblModelo);
		
		JLabel lblBastidor = new JLabel("Nº Bastidor:");
		lblBastidor.setHorizontalAlignment(SwingConstants.LEFT);
		lblBastidor.setBounds(50, 70, 100, 35);
		panelPrincipal.add(lblBastidor);
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setHorizontalAlignment(SwingConstants.LEFT);
		lblMatricula.setBounds(50, 25, 100, 35);
		panelPrincipal.add(lblMatricula);
		
		txtMatricula = new JTextField();
		txtMatricula.setColumns(10);
		txtMatricula.setBounds(150, 25, 150, 35);
		panelPrincipal.add(txtMatricula);
		
		txtBastidor = new JTextField();
		txtBastidor.setColumns(10);
		txtBastidor.setBounds(150, 70, 150, 35);
		panelPrincipal.add(txtBastidor);
		
		txtModelo = new JTextField();
		txtModelo.setColumns(10);
		txtModelo.setBounds(430, 115, 150, 35);
		panelPrincipal.add(txtModelo);
		
		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		txtMarca.setBounds(150, 115, 150, 35);
		panelPrincipal.add(txtMarca);
		
		txtKmRecorridos = new JTextField();
		txtKmRecorridos.setColumns(10);
		txtKmRecorridos.setBounds(150, 205, 150, 35);
		panelPrincipal.add(txtKmRecorridos);
		
		cmbClientes = new JComboBox<String>();
		cmbClientes.setBounds(430, 205, 150, 35);
		panelPrincipal.add(cmbClientes);
		
		JLabel lblPropietario = new JLabel("Propietario:");
		lblPropietario.setHorizontalAlignment(SwingConstants.LEFT);
		lblPropietario.setBounds(330, 205, 100, 35);
		panelPrincipal.add(lblPropietario);
		
		JLabel lblFechaITV = new JLabel("Año ITV:");
		lblFechaITV.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaITV.setBounds(330, 160, 100, 35);
		panelPrincipal.add(lblFechaITV);
		
		txtFechaITV = new JTextField();
		txtFechaITV.setColumns(10);
		txtFechaITV.setBounds(430, 160, 150, 35);
		panelPrincipal.add(txtFechaITV);
		
		txtTipo = new JTextField();
		txtTipo.setColumns(10);
		txtTipo.setBounds(150, 160, 150, 35);
		panelPrincipal.add(txtTipo);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipo.setBounds(50, 160, 100, 35);
		panelPrincipal.add(lblTipo);
		
		txtColor = new JTextField();
		txtColor.setColumns(10);
		txtColor.setBounds(430, 70, 150, 35);
		panelPrincipal.add(txtColor);
		
		JLabel lblColor = new JLabel("Color:");
		lblColor.setHorizontalAlignment(SwingConstants.LEFT);
		lblColor.setBounds(330, 70, 100, 35);
		panelPrincipal.add(lblColor);
		
		JLabel lblCilindrada = new JLabel("Cilindrada:");
		lblCilindrada.setHorizontalAlignment(SwingConstants.LEFT);
		lblCilindrada.setBounds(330, 25, 100, 35);
		panelPrincipal.add(lblCilindrada);
		
		txtCilindrada = new JTextField();
		txtCilindrada.setColumns(10);
		txtCilindrada.setBounds(430, 25, 150, 35);
		panelPrincipal.add(txtCilindrada);
		
		// ===== modelos =====
		// --- crear ---
		dcbmClientes = new DefaultComboBoxModel<String>();
		dcbmClientes.addAll(Archivos.listarClientes());
		
		// --- asignar ---
		cmbClientes.setModel(dcbmClientes);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Actions ---
		// - JTextField -
		ArrayList<JTextField> camposTexto = new ArrayList<JTextField>();
		camposTexto.addAll(Arrays.asList(txtMatricula, txtBastidor,
				txtMarca, txtModelo, txtColor,
				txtCilindrada, txtKmRecorridos, txtFechaITV,
				txtTipo));
		for (JTextField txt : camposTexto)
		{
			txt.addActionListener(this);
			txt.addFocusListener(this);

			// color del texto cuando el campo está deshabilitado
			txt.setDisabledTextColor(Color.DARK_GRAY);
		}
		
		// - JButton -
		btnCancelar.addActionListener(this);
		btnGuardar.addActionListener(this);

		// ===== ajustes de usuario =====
		// --- fuente y color ---
		panelPrincipal.setBackground(Inicio.colorFondo);
		
		ArrayList<JLabel> etiquetas = new ArrayList<JLabel>();
		etiquetas.addAll(Arrays.asList(lblMatricula, lblBastidor,
				lblMarca, lblModelo, lblColor,
				lblCilindrada, lblKmRecorridos, lblFechaITV,
				lblTipo, lblPropietario));

		for (JLabel lbl : etiquetas)
		{
			lbl.setFont(Inicio.fuente);
			lbl.setForeground(Inicio.colorFuente);
		}

		for (JTextField txt : camposTexto)
		{
			txt.setFont(Inicio.fuenteObjetos);
			txt.setBackground(Inicio.colorFondoObjetos);
			txt.setForeground(Inicio.colorFuenteObjetos);
		}

		btnCancelar.setFont(Inicio.fuenteObjetos);
		btnCancelar.setBackground(Inicio.colorFondoObjetos);
		btnCancelar.setForeground(Inicio.colorFuenteObjetos);
		
		btnGuardar.setFont(Inicio.fuenteObjetos);
		btnGuardar.setBackground(Inicio.colorFondoObjetos);
		btnGuardar.setForeground(Inicio.colorFuenteObjetos);

		cmbClientes.setFont(Inicio.fuenteObjetos);
		cmbClientes.setBackground(Inicio.colorFondoObjetos);
		cmbClientes.setForeground(Inicio.colorFuenteObjetos);
	}
	/**
	 * accede a datos almacenados de reparacion pasado como parametro,
	 * habilita siertos campos para poder modificarlos
	 * @param r objeto Reparacion
	 * @see Vehiculo
	 */
	public void modoEdicion(Vehiculo vehiculo)
	{
		edicion = true;

		setTitle("Editar " + vehiculo.getMatricula() + " | " + Inicio.cuentaActual.getNombre());
		
		txtMatricula.setText(vehiculo.getMatricula());
		txtBastidor.setText(vehiculo.getBastidor());

		txtMarca.setText(vehiculo.getMarca());
		txtModelo.setText(vehiculo.getModelo());
		txtColor.setText(vehiculo.getColor());

		txtCilindrada.setText(String.valueOf(vehiculo.getCilindrada()));

		txtKmRecorridos.setText(String.valueOf(vehiculo.getKmRecorridos()));
		txtFechaITV.setText(String.valueOf(vehiculo.getFechaITV().getYear()));

		txtTipo.setText(vehiculo.getTipo());

		if (!vehiculo.getPropietario().equals(""))
		{
			dcbmClientes.setSelectedItem(vehiculo.getPropietario());
		}

		txtMatricula.setEnabled(false);
		if (!vehiculo.getBastidor().equals(""))
		{
			txtBastidor.setEnabled(false);
			txtMarca.setEnabled(false);
			txtModelo.setEnabled(false);
		}
	}
	
	private boolean guardar()
	{
		try
		{
			String matricula = txtMatricula.getText();
			String bastidor = txtBastidor.getText();
			
			String marca = txtMarca.getText();
			String modelo = txtModelo.getText();
			String color = txtColor.getText();
			
			int cilindrada = Integer.parseInt(txtCilindrada.getText());
			
			int kmRecorridos = Integer.parseInt(txtKmRecorridos.getText());
			int aITV = Integer.parseInt(txtFechaITV.getText());
			
			String tipo = txtTipo.getText();
			
			ArrayList<String> camposTxt = new ArrayList<String>();
			camposTxt.addAll(Arrays.asList(matricula, bastidor, marca, modelo, color, tipo));
			
			if (camposTxt.contains("") || cmbClientes.getSelectedIndex() < 0)
			{
				JOptionPane.showMessageDialog(this, (String) "Campo vacío", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
			else if (cilindrada < 1 || kmRecorridos < 0 || aITV < 1)
			{
				JOptionPane.showMessageDialog(this, (String) "Campo numérico incorrecto", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
			else 
			{
				String propietario = (String) cmbClientes.getSelectedItem();
				
				Fecha fechaITV = new Fecha(aITV);
				
				if (!edicion && Archivos.listarVehiculos().contains(matricula))
				{
					JOptionPane.showMessageDialog(this, (String) "Vehículo ya existe", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					Archivos.guardarVehiculo(new Vehiculo(matricula, bastidor, propietario,
							marca, modelo, color, cilindrada, kmRecorridos, fechaITV, tipo));
					
					for (Cliente c : Archivos.cargarTodosClientes())
					{
						if (c.getVehiculos().contains(matricula) && !c.getDNI().equals(propietario))
						{
							c.getVehiculos().remove(matricula);
							Archivos.guardarCliente(c);
						}
						
						if (c.getDNI().equals(propietario) && !c.getVehiculos().contains(matricula))
						{
							c.getVehiculos().add(matricula);
							Archivos.guardarCliente(c);
						}
					}
					
					return true;
				}
			}
		}
		catch (NumberFormatException npe)
		{
			JOptionPane.showMessageDialog(this, (String) "Campo numérico vacío o incorrecto", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}

		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		int guardar = JOptionPane.YES_OPTION;

		if (o == btnCancelar)
		{
			guardar = Salir.edicion();
		}
		
		boolean valido = false;
		
		if (guardar == JOptionPane.YES_OPTION)
		{
			valido = guardar();
		}
		
		if (guardar == JOptionPane.NO_OPTION || valido)
		{
			AdministrarVehiculos.actualizarTabla();
			AdministrarVehiculos.botones(true);
			this.dispose();
		}
	}
		
	@Override
	public void focusGained(FocusEvent fg)
	{
		JTextComponent txt = (JTextComponent) fg.getSource();
		txt.select(0, txt.getText().length());
	}

	@Override
	public void focusLost(FocusEvent fl)
	{
		JTextComponent txt = (JTextComponent) fl.getSource();
		txt.select(0, 0);
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		btnCancelar.doClick();
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