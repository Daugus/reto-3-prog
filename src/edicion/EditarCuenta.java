package edicion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import administracion.AdministrarCuentas;
import clases.Ajustes;
import clases.Cuenta;
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
public class EditarCuenta extends JFrame implements ActionListener, WindowListener, FocusListener
{
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private JLabel lblFondo;
	private JLabel lblFuente;

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
	
	private JPasswordField pwdPassword;

	private JComboBox<String> cmbCuenta;
	private JComboBox<String> cmbFondo;
	private JComboBox<String> cmbFuente;
	
	private JButton btnCancelar;
	private JButton btnGuardar;
	
	private boolean edicion;
	
	public EditarCuenta()
	{
		setResizable(false);
		setTitle("Agregar cuenta");
		
		setBounds(100, 100, 657, 524);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Inicio.colorFondo);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha de nacimiento:");
		lblFechaNacimiento.setForeground(Inicio.colorFuente);
		lblFechaNacimiento.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblFechaNacimiento.setBounds(10, 169, 142, 56);
		panelPrincipal.add(lblFechaNacimiento);
		
		txtFechaNacimientoD = new JTextField();
		txtFechaNacimientoD.setColumns(10);
		txtFechaNacimientoD.setBounds(162, 184, 34, 31);
		panelPrincipal.add(txtFechaNacimientoD);
		
		txtFechaNacimientoM = new JTextField();
		txtFechaNacimientoM.setColumns(10);
		txtFechaNacimientoM.setBounds(200, 184, 34, 31);
		panelPrincipal.add(txtFechaNacimientoM);
		
		txtFechaNacimientoA = new JTextField();
		txtFechaNacimientoA.setColumns(10);
		txtFechaNacimientoA.setBounds(237, 184, 70, 31);
		panelPrincipal.add(txtFechaNacimientoA);
		
		JLabel lblTel = new JLabel("Teléfono:");
		lblTel.setForeground(Inicio.colorFuente);
		lblTel.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblTel.setBounds(85, 127, 66, 46);
		panelPrincipal.add(lblTel);
		
		txtTel = new JTextField();
		txtTel.setColumns(10);
		txtTel.setBounds(189, 137, 118, 31);
		panelPrincipal.add(txtTel);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setForeground(Inicio.colorFuente);
		lblApellidos.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblApellidos.setBounds(27, 86, 65, 49);
		panelPrincipal.add(lblApellidos);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(102, 97, 205, 31);
		panelPrincipal.add(txtApellidos);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Inicio.colorFuente);
		lblNombre.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblNombre.setBounds(86, 42, 65, 56);
		panelPrincipal.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(189, 57, 118, 31);
		panelPrincipal.add(txtNombre);
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setForeground(Inicio.colorFuente);
		lblDNI.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblDNI.setBounds(96, 0, 55, 56);
		panelPrincipal.add(lblDNI);
		
		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(189, 15, 118, 31);
		panelPrincipal.add(txtDNI);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(Inicio.colorFuente);
		lblEmail.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblEmail.setBounds(368, 5, 48, 56);
		panelPrincipal.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(414, 20, 219, 31);
		panelPrincipal.add(txtEmail);
		
		JLabel lblCodPostal = new JLabel("Codigo Postal:");
		lblCodPostal.setForeground(Inicio.colorFuente);
		lblCodPostal.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblCodPostal.setBounds(414, 57, 94, 49);
		panelPrincipal.add(lblCodPostal);
		
		txtCodPostal = new JTextField();
		txtCodPostal.setColumns(10);
		txtCodPostal.setBounds(515, 64, 118, 31);
		panelPrincipal.add(txtCodPostal);
		
		JLabel lblCalle = new JLabel("Calle:");
		lblCalle.setForeground(Inicio.colorFuente);
		lblCalle.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblCalle.setBounds(467, 88, 48, 56);
		panelPrincipal.add(lblCalle);
		
		txtCalle = new JTextField();
		txtCalle.setColumns(10);
		txtCalle.setBounds(515, 103, 118, 31);
		panelPrincipal.add(txtCalle);
		
		JLabel lblPortal = new JLabel("Nº Portal:");
		lblPortal.setForeground(Inicio.colorFuente);
		lblPortal.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblPortal.setBounds(437, 130, 71, 56);
		panelPrincipal.add(lblPortal);
		
		txtPortal = new JTextField();
		txtPortal.setColumns(10);
		txtPortal.setBounds(515, 145, 118, 31);
		panelPrincipal.add(txtPortal);
		
		JLabel lblPiso = new JLabel("Piso:");
		lblPiso.setForeground(Inicio.colorFuente);
		lblPiso.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblPiso.setBounds(467, 172, 40, 56);
		panelPrincipal.add(lblPiso);
		
		txtPiso = new JTextField();
		txtPiso.setColumns(10);
		txtPiso.setBounds(515, 187, 118, 31);
		panelPrincipal.add(txtPiso);
		
		JLabel lblPuerta = new JLabel("Puerta:");
		lblPuerta.setForeground(Inicio.colorFuente);
		lblPuerta.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblPuerta.setBounds(453, 217, 64, 56);
		panelPrincipal.add(lblPuerta);
		
		txtPuerta = new JTextField();
		txtPuerta.setColumns(10);
		txtPuerta.setBounds(515, 232, 118, 31);
		panelPrincipal.add(txtPuerta);
		
		cmbFondo = new JComboBox<String>();
		cmbFondo.addItem("Gris claro");
		cmbFondo.addItem("Gris oscuro");
		cmbFondo.addItem("Negro");
		cmbFondo.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		cmbFondo.setBounds(491, 289, 130, 40);
		panelPrincipal.add(cmbFondo);
		
		cmbFuente = new JComboBox<String>();
		cmbFuente.addItem("Tahoma");
		cmbFuente.addItem("Dialog");
		cmbFuente.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		cmbFuente.setBounds(491, 339, 130, 40);
		panelPrincipal.add(cmbFuente);
		
		lblFuente = new JLabel("Fuente:");
		lblFuente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuente.setForeground(Inicio.colorFuente);
		lblFuente.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblFuente.setBounds(340, 345, 130, 40);
		panelPrincipal.add(lblFuente);
		
		lblFondo = new JLabel("Color de fondo:");
		lblFondo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFondo.setForeground(Inicio.colorFuente);
		lblFondo.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblFondo.setBounds(340, 295, 130, 40);
		panelPrincipal.add(lblFondo);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setColumns(10);
		pwdPassword.setBounds(111, 289, 211, 31);
		panelPrincipal.add(pwdPassword);
		
		cmbCuenta = new JComboBox<String>();
		cmbCuenta.addItem("Mecánico");
		cmbCuenta.addItem("Atención al cliente");
		cmbCuenta.setBounds(135, 345, 169, 31);
		panelPrincipal.add(cmbCuenta);
		
		JLabel lblCuenta = new JLabel("Tipo de Cuenta:");
		lblCuenta.setForeground(Inicio.colorFuente);
		lblCuenta.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblCuenta.setBounds(20, 335, 116, 46);
		panelPrincipal.add(lblCuenta);
		
		JLabel lblPassword = new JLabel("Contraseña:");
		lblPassword.setForeground(Inicio.colorFuente);
		lblPassword.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblPassword.setBounds(20, 279, 94, 46);
		panelPrincipal.add(lblPassword);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		btnCancelar.setBounds(105, 421, 108, 42);
		panelPrincipal.add(btnCancelar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font(Inicio.fuente, Font.PLAIN, 16));
		btnGuardar.setBounds(255, 421, 188, 42);
		panelPrincipal.add(btnGuardar);
		
		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action && Focus ---
		// - JTextField -
		ArrayList<JTextField> camposTexto = new ArrayList<JTextField>();
		camposTexto.addAll(Arrays.asList(txtDNI, txtNombre, txtApellidos,
				txtTel, txtEmail,
				txtFechaNacimientoD, txtFechaNacimientoM, txtFechaNacimientoA,
				txtCodPostal, txtCalle, txtPortal, txtPiso, txtPuerta));
		for (JTextField txt : camposTexto)
		{
			txt.addActionListener(this);
			txt.addFocusListener(this);
		}

		// - JPasswordField -
		pwdPassword.addActionListener(this);
		pwdPassword.addFocusListener(this);
		
		// - JButton -
		btnCancelar.addActionListener(this);
		btnGuardar.addActionListener(this);
	}
	
	public void modoEdicion(Cuenta cuenta)
	{
		edicion = true;
		
		setTitle("Editar " + cuenta.getDNI());
				
		txtDNI.setText(cuenta.getDNI());
		txtDNI.setEnabled(false);
				
		txtNombre.setText(cuenta.getNombre());
		txtApellidos.setText(cuenta.getApellidos());
				
		txtTel.setText(String.valueOf(cuenta.getTelefono()));
		txtEmail.setText(cuenta.getEmail());
				
		txtFechaNacimientoD.setText(String.valueOf(cuenta.getFechaNacimiento().getDay()));
		txtFechaNacimientoD.setEnabled(false);
		txtFechaNacimientoM.setText(String.valueOf(cuenta.getFechaNacimiento().getMonth()));
		txtFechaNacimientoM.setEnabled(false);
		txtFechaNacimientoA.setText(String.valueOf(cuenta.getFechaNacimiento().getYear()));
		txtFechaNacimientoA.setEnabled(false);
				
		txtCodPostal.setText(String.valueOf(cuenta.getDireccion().getCodPostal()));
		txtCalle.setText(cuenta.getDireccion().getCalle());
		txtPortal.setText(String.valueOf(cuenta.getDireccion().getPortal()));
		txtPiso.setText(String.valueOf(cuenta.getDireccion().getPiso()));
		txtPuerta.setText(cuenta.getDireccion().getPuerta());
		
		pwdPassword.setText(cuenta.getPassword());
				
		if (!cuenta.getMecanico())
		{
			cmbCuenta.setSelectedItem("Atención al cliente");
		}
		
		Color color = cuenta.getAjustes().getFondo();
		String fondo = null;
		if (color.equals(Color.LIGHT_GRAY))
		{
			fondo = "Gris claro";
		}
		else if (color.equals(Color.DARK_GRAY))
		{
			fondo = "Gris oscuro";
		}
		else if (color.equals(Color.BLACK))
		{
			fondo = "Negro";
		}
	
		String fuente = cuenta.getAjustes().getFuente();

		cmbFondo.setSelectedItem(fondo);
		cmbFuente.setSelectedItem(fuente);
				
		lblFondo.setVisible(false);
		lblFuente.setVisible(false);
		cmbFondo.setVisible(false);
		cmbFuente.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object o = ae.getSource();
		if (o == btnCancelar)
		{
			this.dispose();
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
				
				int dN = Integer.parseInt(txtFechaNacimientoD.getText());
				int mN = Integer.parseInt(txtFechaNacimientoM.getText());
				int aN = Integer.parseInt(txtFechaNacimientoA.getText());
				
				int codPostal = Integer.parseInt(txtCodPostal.getText());
				String calle = txtCalle.getText();
				int portal = Integer.parseInt(txtPortal.getText());
				int piso = Integer.parseInt(txtPiso.getText());
				String puerta = txtPuerta.getText();
				
				String password = new String(pwdPassword.getPassword());
				boolean codigo = false;

				ArrayList<String> camposTxt = new ArrayList<String>();
				camposTxt.addAll(Arrays.asList(dni, nombre, apellidos, email, calle, puerta, password));
				
				if (camposTxt.contains(""))
				{
					throw new Exception("Campo vacio");
				}
				else 
				{
					if (!edicion && Archivos.listarCuentas().contains(dni))
					{
						JOptionPane.showMessageDialog(this, (String) "La cuenta ya existe", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						Fecha fechaNacimiento = new Fecha(dN, mN, aN);
						Direccion direccion = new Direccion(codPostal, calle, portal, piso, puerta);
						
						int opcion = cmbCuenta.getSelectedIndex();
						switch (opcion)
						{
						case 0:
							// mecánico
							codigo = true;
							break;
						case 1:
							// atención al cliente
							codigo = false;
							break;
						}
						
						String fondo = (String) cmbFondo.getSelectedItem();
						String fuente = (String) cmbFuente.getSelectedItem();
						
						if (fondo.equals("Gris claro"))
						{
							Archivos.guardarCuenta(new Cuenta(dni, nombre, apellidos, tel,
									email, fechaNacimiento,
									direccion, codigo, password, new Ajustes(Color.LIGHT_GRAY, fuente)));
						}
						else if (fondo.equals("Gris oscuro"))
						{
							Archivos.guardarCuenta(new Cuenta(dni, nombre, apellidos, tel,
									email, fechaNacimiento,
									direccion, codigo, password, new Ajustes(Color.DARK_GRAY, fuente)));
						}
						else if (fondo.equals("Negro"))
						{
							Archivos.guardarCuenta(new Cuenta(dni, nombre, apellidos, tel,
									email, fechaNacimiento,
									direccion, codigo, password, new Ajustes(Color.BLACK, fuente)));
						}
						else {
							System.out.println("error color");
						}
						
						AdministrarCuentas.dlmCuentas.removeAllElements();
						AdministrarCuentas.dlmCuentas.addAll(Archivos.cargarTodosCuentas());

						edicion = false;
						this.dispose();
					}
				}
			}
			catch (Exception cv)
			{
				JOptionPane.showMessageDialog(this, (String) "Campo vacío o incorrecto", "ERROR",
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