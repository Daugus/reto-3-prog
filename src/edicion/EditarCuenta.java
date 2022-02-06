package edicion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

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
import clases.OrdenTabulacion;
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

	private JLabel lblTema;
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
	private JComboBox<String> cmbTema;
	private JComboBox<String> cmbFuente;
	
	private JButton btnCancelar;
	private JButton btnGuardar;
	
	Vector<Component> vectorOrden;

	private boolean edicion;
	
	public EditarCuenta()
	{
		setResizable(false);
		setTitle("Agregar cuenta");
		
		setBounds(100, 100, 730, 480);
		getContentPane().setPreferredSize(new Dimension(730, 480));
		pack();

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha de nacimiento:");
		lblFechaNacimiento.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaNacimiento.setBounds(380, 25, 150, 35);
		panelPrincipal.add(lblFechaNacimiento);
		
		txtFechaNacimientoD = new JTextField();
		txtFechaNacimientoD.setColumns(10);
		txtFechaNacimientoD.setBounds(530, 25, 35, 35);
		panelPrincipal.add(txtFechaNacimientoD);
		
		txtFechaNacimientoM = new JTextField();
		txtFechaNacimientoM.setColumns(10);
		txtFechaNacimientoM.setBounds(570, 25, 35, 35);
		panelPrincipal.add(txtFechaNacimientoM);
		
		txtFechaNacimientoA = new JTextField();
		txtFechaNacimientoA.setColumns(10);
		txtFechaNacimientoA.setBounds(610, 25, 70, 35);
		panelPrincipal.add(txtFechaNacimientoA);
		
		JLabel lblTel = new JLabel("Teléfono:");
		lblTel.setHorizontalAlignment(SwingConstants.LEFT);
		lblTel.setBounds(50, 160, 150, 35);
		panelPrincipal.add(lblTel);
		
		txtTel = new JTextField();
		txtTel.setColumns(10);
		txtTel.setBounds(200, 160, 150, 35);
		panelPrincipal.add(txtTel);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setHorizontalAlignment(SwingConstants.LEFT);
		lblApellidos.setBounds(50, 115, 150, 35);
		panelPrincipal.add(lblApellidos);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(200, 115, 150, 35);
		panelPrincipal.add(txtApellidos);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setBounds(50, 70, 150, 35);
		panelPrincipal.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(200, 70, 150, 35);
		panelPrincipal.add(txtNombre);
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setHorizontalAlignment(SwingConstants.LEFT);
		lblDNI.setBounds(50, 25, 150, 35);
		panelPrincipal.add(lblDNI);
		
		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(200, 25, 150, 35);
		panelPrincipal.add(txtDNI);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setBounds(50, 205, 80, 35);
		panelPrincipal.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(130, 205, 220, 35);
		panelPrincipal.add(txtEmail);
		
		JLabel lblCodPostal = new JLabel("Codigo Postal:");
		lblCodPostal.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodPostal.setBounds(380, 70, 150, 35);
		panelPrincipal.add(lblCodPostal);
		
		txtCodPostal = new JTextField();
		txtCodPostal.setColumns(10);
		txtCodPostal.setBounds(530, 70, 150, 35);
		panelPrincipal.add(txtCodPostal);
		
		JLabel lblCalle = new JLabel("Calle:");
		lblCalle.setHorizontalAlignment(SwingConstants.LEFT);
		lblCalle.setBounds(380, 115, 150, 35);
		panelPrincipal.add(lblCalle);
		
		txtCalle = new JTextField();
		txtCalle.setColumns(10);
		txtCalle.setBounds(530, 115, 150, 35);
		panelPrincipal.add(txtCalle);
		
		JLabel lblPortal = new JLabel("Nº Portal:");
		lblPortal.setHorizontalAlignment(SwingConstants.LEFT);
		lblPortal.setBounds(380, 160, 150, 35);
		panelPrincipal.add(lblPortal);
		
		txtPortal = new JTextField();
		txtPortal.setColumns(10);
		txtPortal.setBounds(530, 160, 150, 35);
		panelPrincipal.add(txtPortal);
		
		JLabel lblPiso = new JLabel("Piso:");
		lblPiso.setHorizontalAlignment(SwingConstants.LEFT);
		lblPiso.setBounds(380, 205, 150, 35);
		panelPrincipal.add(lblPiso);
		
		txtPiso = new JTextField();
		txtPiso.setColumns(10);
		txtPiso.setBounds(530, 205, 150, 35);
		panelPrincipal.add(txtPiso);
		
		JLabel lblPuerta = new JLabel("Puerta:");
		lblPuerta.setHorizontalAlignment(SwingConstants.LEFT);
		lblPuerta.setBounds(380, 250, 150, 35);
		panelPrincipal.add(lblPuerta);
		
		txtPuerta = new JTextField();
		txtPuerta.setColumns(10);
		txtPuerta.setBounds(530, 250, 150, 35);
		panelPrincipal.add(txtPuerta);
		
		cmbTema = new JComboBox<String>();
		cmbTema.addItem("Oscuro");
		cmbTema.addItem("Claro");
		cmbTema.setBounds(530, 310, 150, 35);
		panelPrincipal.add(cmbTema);
		
		cmbFuente = new JComboBox<String>();
		cmbFuente.addItem("Segoe UI");
		cmbFuente.addItem("Tahoma");
		cmbFuente.setBounds(530, 355, 150, 35);
		panelPrincipal.add(cmbFuente);
		
		lblFuente = new JLabel("Fuente:");
		lblFuente.setHorizontalAlignment(SwingConstants.LEFT);
		lblFuente.setBounds(380, 355, 150, 35);
		panelPrincipal.add(lblFuente);
		
		lblTema = new JLabel("Tema:");
		lblTema.setHorizontalAlignment(SwingConstants.LEFT);
		lblTema.setBounds(380, 310, 150, 35);
		panelPrincipal.add(lblTema);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setColumns(10);
		pwdPassword.setBounds(200, 332, 150, 36);
		panelPrincipal.add(pwdPassword);
		
		cmbCuenta = new JComboBox<String>();
		cmbCuenta.addItem("Mecánico");
		cmbCuenta.addItem("Atención al cliente");
		cmbCuenta.setBounds(200, 250, 150, 35);
		panelPrincipal.add(cmbCuenta);
		
		JLabel lblCuenta = new JLabel("Tipo de Cuenta:");
		lblCuenta.setHorizontalAlignment(SwingConstants.LEFT);
		lblCuenta.setBounds(50, 250, 150, 35);
		panelPrincipal.add(lblCuenta);
		
		JLabel lblPassword = new JLabel("Contraseña:");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setBounds(50, 332, 150, 36);
		panelPrincipal.add(lblPassword);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(177, 415, 180, 40);
		panelPrincipal.add(btnCancelar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(373, 415, 180, 40);
		panelPrincipal.add(btnGuardar);
		
		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action && Focus ---
		// - JTextField -
		ArrayList<JTextField> camposTexto = new ArrayList<JTextField>();
		camposTexto.addAll(Arrays.asList(txtDNI, txtNombre, txtApellidos,
				txtTel, txtEmail, txtFechaNacimientoD, txtFechaNacimientoM, txtFechaNacimientoA,
				txtCodPostal, txtCalle, txtPortal, txtPiso, txtPuerta));
		for (JTextField txt : camposTexto)
		{
			txt.addActionListener(this);
			txt.addFocusListener(this);

			// color del texto cuando el campo está deshabilitado
			txt.setDisabledTextColor(Color.DARK_GRAY);
		}

		// - JPasswordField -
		pwdPassword.addActionListener(this);
		pwdPassword.addFocusListener(this);
		
		// - JButton -
		btnCancelar.addActionListener(this);
		btnGuardar.addActionListener(this);
		
		// ===== ajustes de usuario =====
		// --- fuente y color ---
		panelPrincipal.setBackground(Inicio.colorFondo);

		ArrayList<JLabel> etiquetas = new ArrayList<JLabel>();
		etiquetas.addAll(Arrays.asList(lblDNI, lblNombre, lblApellidos,
				lblTel, lblEmail, lblFechaNacimiento,
				lblCodPostal, lblCalle, lblPortal, lblPiso, lblPuerta,
				lblPassword, lblCuenta, lblTema, lblFuente));
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
		
		pwdPassword.setFont(Inicio.fuenteObjetos);
		pwdPassword.setBackground(Inicio.colorFondoObjetos);
		pwdPassword.setForeground(Inicio.colorFuenteObjetos);

		btnCancelar.setFont(Inicio.fuenteObjetos);
		btnCancelar.setBackground(Inicio.colorFondoObjetos);
		btnCancelar.setForeground(Inicio.colorFuenteObjetos);
		
		btnGuardar.setFont(Inicio.fuenteObjetos);
		btnGuardar.setBackground(Inicio.colorFondoObjetos);
		btnGuardar.setForeground(Inicio.colorFuenteObjetos);

		cmbCuenta.setFont(Inicio.fuenteObjetos);
		cmbCuenta.setBackground(Inicio.colorFondoObjetos);
		cmbCuenta.setForeground(Inicio.colorFuenteObjetos);

		cmbTema.setFont(Inicio.fuenteObjetos);
		cmbTema.setBackground(Inicio.colorFondoObjetos);
		cmbTema.setForeground(Inicio.colorFuenteObjetos);

		cmbFuente.setFont(Inicio.fuenteObjetos);
		cmbFuente.setBackground(Inicio.colorFondoObjetos);
		cmbFuente.setForeground(Inicio.colorFuenteObjetos);
		
		// ===== orden de tabulación =====
		vectorOrden = new Vector<Component>();
		vectorOrden.addAll(camposTexto);
		vectorOrden.add(cmbCuenta);
		vectorOrden.add(pwdPassword);
		vectorOrden.add(cmbTema);
		vectorOrden.add(cmbFuente);

		OrdenTabulacion orden = new OrdenTabulacion(vectorOrden);
		setFocusTraversalPolicy(orden);
	}
	
	public void modoEdicion(Cuenta cuenta)
	{
		edicion = true;
		
		setTitle("Editar " + cuenta.getDNI());
				
		txtDNI.setText(cuenta.getDNI());
		txtDNI.setEnabled(false);
		vectorOrden.remove(txtDNI);
				
		txtNombre.setText(cuenta.getNombre());
		txtApellidos.setText(cuenta.getApellidos());
				
		txtTel.setText(String.valueOf(cuenta.getTelefono()));
		txtEmail.setText(cuenta.getEmail());
				
		txtFechaNacimientoD.setText(String.valueOf(cuenta.getFechaNacimiento().getDay()));
		txtFechaNacimientoD.setEnabled(false);
		vectorOrden.remove(txtFechaNacimientoD);
		txtFechaNacimientoM.setText(String.valueOf(cuenta.getFechaNacimiento().getMonth()));
		txtFechaNacimientoM.setEnabled(false);
		vectorOrden.remove(txtFechaNacimientoM);
		txtFechaNacimientoA.setText(String.valueOf(cuenta.getFechaNacimiento().getYear()));
		txtFechaNacimientoA.setEnabled(false);
		vectorOrden.remove(txtFechaNacimientoA);
				
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

		String tema = null;
		if (cuenta.getAjustes().temaOscuro())
		{
			tema = "Oscuro";
		}
		else
		{
			tema = "Claro";
		}
	
		String fuente = cuenta.getAjustes().getFuente().getFamily();

		cmbTema.setSelectedItem(tema);
		cmbFuente.setSelectedItem(fuente);
				
		lblTema.setVisible(false);
		lblFuente.setVisible(false);
		cmbTema.setVisible(false);
		vectorOrden.remove(cmbTema);
		cmbFuente.setVisible(false);
		vectorOrden.remove(cmbFuente);

		OrdenTabulacion orden = new OrdenTabulacion(vectorOrden);
		setFocusTraversalPolicy(orden);
	}
	
	private boolean guardar()
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
			else if (tel < 1 || codPostal < 1 || portal < 1 || piso < 1)
			{
				JOptionPane.showMessageDialog(this, (String) "Campo numérico incorrecto", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
			else if (dN < 1 || mN < 1 || aN < 1 || dN > 31 || mN > 12)
			{
				JOptionPane.showMessageDialog(this, (String) "Fecha no válida", "ERROR",
						JOptionPane.ERROR_MESSAGE);
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
					
					String tema = (String) cmbTema.getSelectedItem();
					String fuente = (String) cmbFuente.getSelectedItem();
					
					boolean temaOscuro = true;
					if (tema.equals("Oscuro"))
					{
						temaOscuro = true;
					}
					else if (tema.equals("Claro"))
					{
						temaOscuro = false;
					}
					
					Archivos.guardarCuenta(new Cuenta(dni, nombre, apellidos, tel,
							email, fechaNacimiento,
							direccion, codigo, password, new Ajustes(temaOscuro, fuente)));
					
					return true;
				}
			}
		}
		catch (Exception cv)
		{
			JOptionPane.showMessageDialog(this, (String) "Campo vacío o incorrecto", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}

		return false;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object o = ae.getSource();

		int guardar = JOptionPane.YES_OPTION;

		if (o == btnCancelar)
		{
			guardar = Salir.edicion(true);
		}
		
		boolean valido = false;
		
		if (guardar == JOptionPane.YES_OPTION)
		{
			valido = guardar();
		}
		
		if (guardar == JOptionPane.NO_OPTION || valido)
		{
			AdministrarCuentas.actualizarTabla();
			AdministrarCuentas.botones(true);
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