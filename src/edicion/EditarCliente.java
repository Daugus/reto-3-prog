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
import java.util.Calendar;

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

import administracion.AdministrarClientes;
import clases.Cliente;
import clases.Direccion;
import clases.Fecha;
import funciones.Archivos;
import funciones.Salir;
import navegacion.Inicio;

/**
 * 
 * @author Grupo 2
 * 
 */
public class EditarCliente extends JFrame implements ActionListener, FocusListener, WindowListener
{
	private static final long serialVersionUID = 1531539371445418371L;
	
	private JPanel panelPrincipal;

	private JButton btnCancelar;
	private JButton btnGuardar;
	private JButton btnEliminarMatricula;
	private JButton btnAgregarMatricula;

	private JTextField txtDNI;
	private JTextField txtNombre;
	private JTextField txtApellidos;

	private JTextField txtTel;
	private JTextField txtEmail;

	private JTextField txtFechaNacimientoD;
	private JTextField txtFechaNacimientoM;
	private JTextField txtFechaNacimientoA;

	private JTextField txtCodPostal;
	private JTextField txtCalle;
	private JTextField txtPortal;
	private JTextField txtPiso;
	private JTextField txtPuerta;
	
	private JTextField txtFechaAltaD;
	private JTextField txtFechaAltaM;
	private JTextField txtFechaAltaA;
	
	private JTextField txtMatricula;
	public DefaultComboBoxModel<String> dcbmVehiculos;
	private JComboBox<String> cmbVehiculos;
	
	private boolean edicion;

	public EditarCliente() {
		setResizable(false);
		setTitle("Agregar nuevo cliente");
		
		setBounds(100, 100, 638, 510);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Inicio.colorFondo);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(123, 396, 108, 42);
		panelPrincipal.add(btnCancelar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(273, 396, 188, 42);
		panelPrincipal.add(btnGuardar);
		
		JLabel lblPuerta = new JLabel("Puerta:");
		lblPuerta.setBounds(434, 223, 61, 56);
		panelPrincipal.add(lblPuerta);
		
		txtPuerta = new JTextField();
		txtPuerta.setColumns(10);
		txtPuerta.setBounds(496, 238, 118, 31);
		panelPrincipal.add(txtPuerta);
		
		JLabel lblPiso = new JLabel("Piso:");
		lblPiso.setBounds(448, 178, 42, 56);
		panelPrincipal.add(lblPiso);
		
		txtPiso = new JTextField();
		txtPiso.setColumns(10);
		txtPiso.setBounds(496, 193, 118, 31);
		panelPrincipal.add(txtPiso);
		
		JLabel lblPortal = new JLabel("Nº Portal:");
		lblPortal.setBounds(418, 136, 74, 56);
		panelPrincipal.add(lblPortal);
		
		txtPortal = new JTextField();
		txtPortal.setColumns(10);
		txtPortal.setBounds(496, 151, 118, 31);
		panelPrincipal.add(txtPortal);
		
		txtCalle = new JTextField();
		txtCalle.setColumns(10);
		txtCalle.setBounds(496, 109, 118, 31);
		panelPrincipal.add(txtCalle);
		
		JLabel lblCalle = new JLabel("Calle:");
		lblCalle.setBounds(448, 94, 38, 56);
		panelPrincipal.add(lblCalle);
		
		JLabel lblCodPostal = new JLabel("Código Postal:");
		lblCodPostal.setBounds(395, 63, 100, 49);
		panelPrincipal.add(lblCodPostal);
		
		txtCodPostal = new JTextField();
		txtCodPostal.setColumns(10);
		txtCodPostal.setBounds(496, 70, 118, 31);
		panelPrincipal.add(txtCodPostal);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(414, 26, 200, 31);
		panelPrincipal.add(txtEmail);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(368, 11, 48, 56);
		panelPrincipal.add(lblEmail);
		
		txtMatricula = new JTextField();
		txtMatricula.setColumns(10);
		txtMatricula.setBounds(108, 333, 118, 34);
		panelPrincipal.add(txtMatricula);
		
		btnAgregarMatricula = new JButton("Agregar");
		btnAgregarMatricula.setBounds(234, 333, 118, 34);
		panelPrincipal.add(btnAgregarMatricula);
		
		cmbVehiculos = new JComboBox<String>();
		cmbVehiculos.setBounds(108, 291, 118, 34);
		panelPrincipal.add(cmbVehiculos);
		
		btnEliminarMatricula = new JButton("Eliminar");
		btnEliminarMatricula.setBounds(234, 291, 118, 34);
		panelPrincipal.add(btnEliminarMatricula);
		
		JLabel lblMatricula = new JLabel("Matrícula");
		lblMatricula.setBounds(20, 323, 66, 56);
		panelPrincipal.add(lblMatricula);
		
		JLabel lblVehiculos = new JLabel("Vehículos:");
		lblVehiculos.setBounds(20, 289, 78, 46);
		panelPrincipal.add(lblVehiculos);
		
		JLabel lblFechaAlta = new JLabel("Fecha de alta:");
		lblFechaAlta.setBounds(12, 234, 101, 46);
		panelPrincipal.add(lblFechaAlta);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha nacimiento:");
		lblFechaNacimiento.setBounds(10, 180, 142, 56);
		panelPrincipal.add(lblFechaNacimiento);
		
		JLabel lblTel = new JLabel("Teléfono:");
		lblTel.setBounds(85, 138, 66, 46);
		panelPrincipal.add(lblTel);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(20, 97, 65, 49);
		panelPrincipal.add(lblApellidos);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(86, 108, 193, 31);
		panelPrincipal.add(txtApellidos);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(86, 53, 65, 56);
		panelPrincipal.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(161, 68, 118, 31);
		panelPrincipal.add(txtNombre);
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setBounds(96, 11, 55, 56);
		panelPrincipal.add(lblDNI);
		
		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(161, 26, 118, 31);
		panelPrincipal.add(txtDNI);
		
		txtTel = new JTextField();
		txtTel.setColumns(10);
		txtTel.setBounds(161, 148, 118, 31);
		panelPrincipal.add(txtTel);
		
		txtFechaNacimientoD = new JTextField();
		txtFechaNacimientoD.setColumns(10);
		txtFechaNacimientoD.setBounds(134, 195, 34, 31);
		panelPrincipal.add(txtFechaNacimientoD);
		
		txtFechaAltaD = new JTextField("");
		txtFechaAltaD.setBounds(129, 239, 32, 34);
		panelPrincipal.add(txtFechaAltaD);
		
		txtFechaAltaM = new JTextField("");
		txtFechaAltaM.setBounds(171, 239, 32, 34);
		panelPrincipal.add(txtFechaAltaM);
		
		txtFechaNacimientoM = new JTextField();
		txtFechaNacimientoM.setColumns(10);
		txtFechaNacimientoM.setBounds(172, 195, 34, 31);
		panelPrincipal.add(txtFechaNacimientoM);
		
		txtFechaNacimientoA = new JTextField();
		txtFechaNacimientoA.setColumns(10);
		txtFechaNacimientoA.setBounds(209, 195, 70, 31);
		panelPrincipal.add(txtFechaNacimientoA);
		
		txtFechaAltaA = new JTextField("");
		txtFechaAltaA.setBounds(213, 239, 66, 34);
		panelPrincipal.add(txtFechaAltaA);

		// ===== modelos =====
		// --- crear ---
		dcbmVehiculos = new DefaultComboBoxModel<String>();

		// --- asignar ---
		cmbVehiculos.setModel(dcbmVehiculos);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action && Focus ---
		// - JTextField -
		ArrayList<JTextField> camposTexto = new ArrayList<JTextField>();
		camposTexto.addAll(Arrays.asList(txtDNI, txtNombre, txtApellidos,
				txtTel, txtEmail, txtFechaNacimientoD, txtFechaNacimientoM, txtFechaNacimientoA,
				txtCodPostal, txtCalle, txtPortal, txtPiso, txtPuerta,
				txtFechaAltaD, txtFechaAltaM, txtFechaAltaA,
				txtMatricula));
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
		btnAgregarMatricula.addActionListener(this);
		btnEliminarMatricula.addActionListener(this);
		
		// ===== ajustes de usuario =====
		// --- fuente y color ---
		panelPrincipal.setBackground(Inicio.colorFondo);

		ArrayList<JLabel> etiquetas = new ArrayList<JLabel>();
		etiquetas.addAll(Arrays.asList(lblDNI, lblNombre, lblApellidos,
				lblTel, lblEmail, lblFechaNacimiento,
				lblCodPostal, lblCalle, lblPortal, lblPiso, lblPuerta,
				lblFechaAlta, lblVehiculos, lblMatricula));
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

		btnAgregarMatricula.setFont(Inicio.fuenteObjetos);
		btnAgregarMatricula.setBackground(Inicio.colorFondoObjetos);
		btnAgregarMatricula.setForeground(Inicio.colorFuenteObjetos);

		btnEliminarMatricula.setFont(Inicio.fuenteObjetos);
		btnEliminarMatricula.setBackground(Inicio.colorFondoObjetos);
		btnEliminarMatricula.setForeground(Inicio.colorFuenteObjetos);

		cmbVehiculos.setFont(Inicio.fuenteObjetos);
		cmbVehiculos.setBackground(Inicio.colorFondoObjetos);
		cmbVehiculos.setForeground(Inicio.colorFuenteObjetos);
	}

	public void modoEdicion(Cliente cliente)
	{
		edicion = true;
		
		setTitle("Editar " + cliente.getDNI());

		txtDNI.setText(cliente.getDNI());
		txtDNI.setEnabled(false);

		txtNombre.setText(cliente.getNombre());
		txtApellidos.setText(cliente.getApellidos());
		
		txtTel.setText(String.valueOf(cliente.getTelefono()));
		txtEmail.setText(cliente.getEmail());

		txtFechaNacimientoD.setText(String.valueOf(cliente.getFechaNacimiento().getDay()));
		txtFechaNacimientoD.setEnabled(false);
		txtFechaNacimientoM.setText(String.valueOf(cliente.getFechaNacimiento().getMonth()));
		txtFechaNacimientoM.setEnabled(false);
		txtFechaNacimientoA.setText(String.valueOf(cliente.getFechaNacimiento().getYear()));
		txtFechaNacimientoA.setEnabled(false);

		txtFechaAltaD.setText(String.valueOf(cliente.getFechaAlta().getDay()));
		txtFechaAltaD.setEnabled(false);
		txtFechaAltaM.setText(String.valueOf(cliente.getFechaAlta().getMonth()));
		txtFechaAltaM.setEnabled(false);
		txtFechaAltaA.setText(String.valueOf(cliente.getFechaAlta().getYear()));
		txtFechaAltaA.setEnabled(false);

		txtCodPostal.setText(String.valueOf(cliente.getDireccion().getCodPostal()));
		txtCalle.setText(cliente.getDireccion().getCalle());
		txtPortal.setText(String.valueOf(cliente.getDireccion().getPortal()));
		txtPiso.setText(String.valueOf(cliente.getDireccion().getPiso()));
		txtPuerta.setText(cliente.getDireccion().getPuerta());

		dcbmVehiculos.addAll(cliente.getVehiculos());
		dcbmVehiculos.setSelectedItem(cmbVehiculos.getSelectedItem());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o == btnCancelar)
		{
			this.dispose();
		}
		else if (o == txtMatricula || o == btnAgregarMatricula)
		{
			String matricula = txtMatricula.getText();
			if (matricula.equals(""))
			{
				JOptionPane.showMessageDialog(this, (String) "Campo de matrícula vacío", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				if (dcbmVehiculos.getIndexOf(matricula) != -1)
				{
					JOptionPane.showMessageDialog(this, (String) "El vehículo ya está agregado", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					dcbmVehiculos.addElement(matricula);
					txtMatricula.setText("");
					cmbVehiculos.setSelectedItem(matricula);
				}
			}
		}
		else if (o == btnEliminarMatricula)
		{
			if (dcbmVehiculos.getSize() > 0)
			{
				int seleccionado = cmbVehiculos.getSelectedIndex();

				if (seleccionado >= 0)
				{
					dcbmVehiculos.removeElementAt(seleccionado);
				}
				else
				{
					JOptionPane.showMessageDialog(this, (String) "No hay ninguna matrícula seleccionada", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, (String) "No hay matrículas que eliminar", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			try
			{
				String dni = txtDNI.getText();

				String nombre = txtNombre.getText();
				String apellidos = txtApellidos.getText();

				int tel = Integer.parseInt(txtTel.getText());
				String email = txtEmail.getText();

				int diaN = Integer.parseInt(txtFechaNacimientoD.getText());
				int mesN = Integer.parseInt(txtFechaNacimientoM.getText());
				int anoN = Integer.parseInt(txtFechaNacimientoA.getText());

				int codPostal = Integer.parseInt(txtCodPostal.getText());
				String calle = txtCalle.getText();
				int portal = Integer.parseInt(txtPortal.getText());
				int piso = Integer.parseInt(txtPiso.getText());
				String puerta = txtPuerta.getText();
			
				Fecha fechaAlta;
		    	Calendar now = Calendar.getInstance();
		    	int ano = now.get(Calendar.YEAR);
		    	int mes = (now.get(Calendar.MONTH) + 1);
		    	int dia = now.get(Calendar.DATE);
		    	fechaAlta = new Fecha(dia,mes,ano);
		    
		    	ArrayList<String> matriculas = new ArrayList<String>();
		    	for (int i = 0; i < dcbmVehiculos.getSize(); i++)
		    	{
		    		matriculas.add(dcbmVehiculos.getElementAt(i));
		    	}
		    	String matricula = (String) cmbVehiculos.getSelectedItem();

				ArrayList<String> camposTxt = new ArrayList<String>();
				camposTxt.addAll(Arrays.asList(dni, nombre, apellidos, email, calle, puerta, matricula));

				if (camposTxt.contains(""))
				{
					JOptionPane.showMessageDialog(this, (String) "Campo vacío", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
				else 
				{
					Fecha fechaNacimiento = new Fecha(diaN,mesN,anoN);
					Direccion direccion = new Direccion(codPostal, calle, portal, piso, puerta);

					if (!edicion && Archivos.listarMateriales().contains(dni))
					{
						JOptionPane.showMessageDialog(this, (String) "Material ya existe", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						Archivos.guardarCliente(new Cliente(dni, nombre, apellidos, tel,
								email, fechaNacimiento,
								direccion, fechaAlta, matriculas));
						
						AdministrarClientes.actualizarTabla();
						
						this.dispose();
					}
				}
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