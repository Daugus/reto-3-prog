package edicion;

import java.awt.Color;
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
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import clases.Fecha;
import clases.Vehiculo;
import funciones.Archivos;
import funciones.Salir;
import navegacion.Inicio;

/**
 * 
 * @author Grupo 2
 * 
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
	
	public EditarVehiculo() {
		setResizable(false);
		setTitle("Agregar nuevo vehículo");
		
		setBounds(100, 100, 539, 355);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(78, 250, 94, 46);
		panelPrincipal.add(btnCancelar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(this);
		btnGuardar.setBounds(249, 250, 173, 46);
		panelPrincipal.add(btnGuardar);
		
		JLabel lblKmRecorridos = new JLabel("KM recorridos:");
		lblKmRecorridos.setBounds(10, 161, 152, 56);
		panelPrincipal.add(lblKmRecorridos);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(10, 126, 116, 49);
		panelPrincipal.add(lblMarca);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(10, 87, 116, 49);
		panelPrincipal.add(lblModelo);
		
		JLabel lblBastidor = new JLabel("Nº Bastidor:");
		lblBastidor.setBounds(10, 47, 101, 56);
		panelPrincipal.add(lblBastidor);
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setBounds(10, 11, 140, 56);
		panelPrincipal.add(lblMatricula);
		
		txtMatricula = new JTextField();
		txtMatricula.setColumns(10);
		txtMatricula.setBounds(124, 26, 95, 31);
		panelPrincipal.add(txtMatricula);
		
		txtBastidor = new JTextField();
		txtBastidor.setColumns(10);
		txtBastidor.setBounds(124, 62, 95, 31);
		panelPrincipal.add(txtBastidor);
		
		txtModelo = new JTextField();
		txtModelo.setColumns(10);
		txtModelo.setBounds(124, 98, 95, 31);
		panelPrincipal.add(txtModelo);
		
		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		txtMarca.setBounds(124, 137, 95, 31);
		panelPrincipal.add(txtMarca);
		
		txtKmRecorridos = new JTextField();
		txtKmRecorridos.setColumns(10);
		txtKmRecorridos.setBounds(124, 175, 94, 31);
		panelPrincipal.add(txtKmRecorridos);
		
		cmbClientes = new JComboBox<String>();
		cmbClientes.setBounds(408, 180, 95, 31);
		panelPrincipal.add(cmbClientes);
		
		JLabel lblPropietario = new JLabel("Propietario:");
		lblPropietario.setBounds(312, 161, 81, 56);
		panelPrincipal.add(lblPropietario);
		
		JLabel lblFechaITV = new JLabel("Año ITV:");
		lblFechaITV.setBounds(312, 126, 81, 56);
		panelPrincipal.add(lblFechaITV);
		
		txtFechaITV = new JTextField();
		txtFechaITV.setColumns(10);
		txtFechaITV.setBounds(408, 141, 95, 31);
		panelPrincipal.add(txtFechaITV);
		
		txtTipo = new JTextField();
		txtTipo.setColumns(10);
		txtTipo.setBounds(408, 105, 95, 31);
		panelPrincipal.add(txtTipo);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(348, 83, 53, 56);
		panelPrincipal.add(lblTipo);
		
		txtColor = new JTextField();
		txtColor.setColumns(10);
		txtColor.setBounds(408, 66, 95, 31);
		panelPrincipal.add(txtColor);
		
		JLabel lblColor = new JLabel("Color:");
		lblColor.setBounds(355, 47, 53, 56);
		panelPrincipal.add(lblColor);
		
		JLabel lblCilindrada = new JLabel("Cilindrada:");
		lblCilindrada.setBounds(324, 11, 73, 56);
		panelPrincipal.add(lblCilindrada);
		
		txtCilindrada = new JTextField();
		txtCilindrada.setColumns(10);
		txtCilindrada.setBounds(408, 26, 95, 31);
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
	
	public void modoEdicion(Vehiculo vehiculo)
	{
		edicion = true;

		setTitle("Editar " + vehiculo.getMatricula());
		
		txtMatricula.setText(vehiculo.getMatricula());
		txtMatricula.setEnabled(false);
		txtBastidor.setText(vehiculo.getBastidor());
		txtBastidor.setEnabled(false);

		txtMarca.setText(vehiculo.getMarca());
		txtMarca.setEnabled(false);
		txtModelo.setText(vehiculo.getModelo());
		txtModelo.setEnabled(false);
		txtColor.setText(vehiculo.getColor());

		txtCilindrada.setText(String.valueOf(vehiculo.getCilindrada()));

		txtKmRecorridos.setText(String.valueOf(vehiculo.getKmRecorridos()));
		txtFechaITV.setText(String.valueOf(vehiculo.getFechaITV().getYear()));

		txtTipo.setText(vehiculo.getTipo());

		dcbmClientes.setSelectedItem(vehiculo.getPropietario());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o == btnCancelar)
		{
			this.dispose();
		}
		else
		{
			try
			{
				String matricula = txtMatricula.getText();
				String bastidor = txtBastidor.getText();
				String propietario = (String) cmbClientes.getSelectedItem();

				String marca = txtMarca.getText();
				String modelo = txtModelo.getText();
				String color = txtColor.getText();

				int cilindrada = Integer.parseInt(txtCilindrada.getText());

				int kmRecorridos = Integer.parseInt(txtKmRecorridos.getText());
				int aITV = Integer.parseInt(txtFechaITV.getText());
			
				String tipo = txtTipo.getText();

				ArrayList<String> camposTxt = new ArrayList<String>();
				camposTxt.addAll(Arrays.asList(matricula, bastidor, propietario, marca, modelo, color, tipo));
		    
				if (camposTxt.contains(""))
				{
					JOptionPane.showMessageDialog(this, (String) "Campo vacío", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
				else 
				{
					Fecha fechaITV = new Fecha(aITV);
					
					if (!edicion && Archivos.listarVehiculos().contains(matricula))
					{
						JOptionPane.showMessageDialog(this, (String) "Vehiculo ya existe", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						Archivos.guardarVehiculo(new Vehiculo(matricula, bastidor, propietario,
								marca, modelo, color, cilindrada, kmRecorridos, fechaITV, tipo));
					}
				}

				this.dispose();
			}
			catch (NumberFormatException npe)
			{
				JOptionPane.showMessageDialog(this, (String) "Campo numérico vacío o incorrecto", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
		
	@Override
	public void focusGained(FocusEvent fg) {
		JTextComponent txt = (JTextComponent) fg.getSource();
		txt.select(0, txt.getText().length());
	}

	@Override
	public void focusLost(FocusEvent fl) {
		JTextComponent txt = (JTextComponent) fl.getSource();
		txt.select(0, 0);
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