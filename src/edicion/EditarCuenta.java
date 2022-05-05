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
import java.util.Calendar;
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
import clases.Fecha;
import clases.OrdenTabulacion;
import funciones.Datos;
import funciones.Salir;
import navegacion.Inicio;
import javax.swing.JCheckBox;

public class EditarCuenta extends JFrame implements ActionListener, WindowListener, FocusListener {
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private JLabel lblTema;
	private JLabel lblFuente;

	private JTextField txtDNI;
	private JTextField txtNombre;
	private JTextField txtApellidos;

	private JTextField txtTelefono;
	private JTextField txtEmail;

	private JTextField txtFechaNacimientoD;
	private JTextField txtFechaNacimientoM;
	private JTextField txtFechaNacimientoA;

	private JTextField txtDireccion;
	private JTextField txtDniJefe;
	private JTextField txtSalario;
	private JTextField txtComision;

	private JCheckBox chkActivo;

	private JPasswordField pwdPassword;

	private JComboBox<String> cmbCuenta;
	private JComboBox<String> cmbTema;
	private JComboBox<String> cmbFuente;

	private JButton btnCancelar;
	private JButton btnGuardar;

	private ArrayList<String> alDNIs = new ArrayList<String>();

	private Vector<Component> vectorOrden;

	private boolean edicion;
	
	private Fecha fechaAlta;

	public EditarCuenta() {
		setResizable(false);
		setTitle("Agregar cuenta | " + Inicio.cuentaActual.getNombre());

		setBounds(100, 100, 730, 480);
		getContentPane().setPreferredSize(new Dimension(730, 480));
		pack();

		setLocationRelativeTo(null);

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

		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(200, 160, 150, 35);
		panelPrincipal.add(txtTelefono);

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

		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setHorizontalAlignment(SwingConstants.LEFT);
		lblDireccion.setBounds(380, 70, 150, 35);
		panelPrincipal.add(lblDireccion);

		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(530, 70, 150, 35);
		panelPrincipal.add(txtDireccion);

		JLabel lblDniJefe = new JLabel("DNI Jefe:");
		lblDniJefe.setHorizontalAlignment(SwingConstants.LEFT);
		lblDniJefe.setBounds(380, 115, 150, 35);
		panelPrincipal.add(lblDniJefe);

		txtDniJefe = new JTextField();
		txtDniJefe.setColumns(10);
		txtDniJefe.setBounds(530, 115, 150, 35);
		panelPrincipal.add(txtDniJefe);

		JLabel lblSalario = new JLabel("Salario:");
		lblSalario.setHorizontalAlignment(SwingConstants.LEFT);
		lblSalario.setBounds(380, 160, 150, 35);
		panelPrincipal.add(lblSalario);

		txtSalario = new JTextField();
		txtSalario.setColumns(10);
		txtSalario.setBounds(530, 160, 150, 35);
		panelPrincipal.add(txtSalario);

		JLabel lblComision = new JLabel("Comision");
		lblComision.setHorizontalAlignment(SwingConstants.LEFT);
		lblComision.setBounds(380, 205, 150, 35);
		panelPrincipal.add(lblComision);

		txtComision = new JTextField();
		txtComision.setColumns(10);
		txtComision.setBounds(530, 205, 150, 35);
		panelPrincipal.add(txtComision);

		JLabel lblActivo = new JLabel("Activo:");
		lblActivo.setHorizontalAlignment(SwingConstants.LEFT);
		lblActivo.setBounds(380, 250, 150, 35);
		panelPrincipal.add(lblActivo);

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
		cmbCuenta.addItem("Administrador");
		cmbCuenta.addItem("Recepcionista");
		cmbCuenta.addItem("Mecánico");
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

		chkActivo = new JCheckBox("");
		chkActivo.setSelected(true);
		chkActivo.setOpaque(false);
		chkActivo.setHorizontalAlignment(SwingConstants.CENTER);
		chkActivo.setBounds(530, 250, 150, 35);
		panelPrincipal.add(chkActivo);

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
		camposTexto.addAll(Arrays.asList(txtDNI, txtNombre, txtApellidos, txtTelefono, txtEmail, txtFechaNacimientoD,
				txtFechaNacimientoM, txtFechaNacimientoA, txtDireccion, txtDniJefe, txtSalario, txtComision));
		for (JTextField txt : camposTexto) {
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
		etiquetas.addAll(
				Arrays.asList(lblDNI, lblNombre, lblApellidos, lblTel, lblEmail, lblFechaNacimiento, lblDireccion,
						lblDniJefe, lblSalario, lblComision, lblActivo, lblPassword, lblCuenta, lblTema, lblFuente));
		for (JLabel lbl : etiquetas) {
			lbl.setFont(Inicio.fuente);
			lbl.setForeground(Inicio.colorFuente);
		}

		for (JTextField txt : camposTexto) {
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
		vectorOrden.add(chkActivo);
		vectorOrden.add(pwdPassword);
		vectorOrden.add(cmbTema);
		vectorOrden.add(cmbFuente);

		OrdenTabulacion orden = new OrdenTabulacion(vectorOrden);
		setFocusTraversalPolicy(orden);
	}

	public void setAlDNIs(ArrayList<Cuenta> cuentas) {
		for (Cuenta c : cuentas) {
			alDNIs.add(c.getDNI());
		}
	}

	public void modoEdicion(Cuenta cuenta) {
		edicion = true;

		setTitle("Editar " + cuenta.getDNI() + " | " + Inicio.cuentaActual.getNombre());

		txtDNI.setText(cuenta.getDNI());
		txtDNI.setEnabled(false);
		vectorOrden.remove(txtDNI);

		txtNombre.setText(cuenta.getNombre());
		txtApellidos.setText(cuenta.getApellidos());

		txtTelefono.setText(cuenta.getTelefono());
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

		txtDireccion.setText(cuenta.getDireccion());

		if (cuenta.getDniJefe() != null) {
			txtDniJefe.setText(cuenta.getDniJefe());
		}

		txtSalario.setText(String.valueOf(cuenta.getSalario()));
		txtComision.setText(String.valueOf(cuenta.getComision()));

		chkActivo.setSelected(cuenta.isActivo());

		pwdPassword.setText(cuenta.getPassword());
		if (!cuenta.getTipo().equals("Mecánico") && !Inicio.cuentaActual.getDNI().equals(cuenta.getDNI())) {
			pwdPassword.setEnabled(false);
			vectorOrden.remove(pwdPassword);
		}

		cmbCuenta.setSelectedItem(cuenta.getTipo());

		if (cuenta.getAjustes() != null) {
			String tema = cuenta.getAjustes().temaOscuro() ? "Oscuro" : "Claro";
			String fuente = cuenta.getAjustes().getFuente().getFamily();

			cmbTema.setSelectedItem(tema);
			cmbFuente.setSelectedItem(fuente);
		} else {
			cmbTema.setSelectedIndex(0);
			cmbFuente.setSelectedIndex(0);
		}

		lblTema.setVisible(false);
		lblFuente.setVisible(false);
		cmbTema.setVisible(false);
		vectorOrden.remove(cmbTema);
		cmbFuente.setVisible(false);
		vectorOrden.remove(cmbFuente);
		
		fechaAlta = cuenta.getFechaAlta();

		OrdenTabulacion orden = new OrdenTabulacion(vectorOrden);
		setFocusTraversalPolicy(orden);
	}

	private boolean guardar() {
		String dni = txtDNI.getText();

		String nombre = txtNombre.getText();
		String apellidos = txtApellidos.getText();

		int telefono = Integer.parseInt(txtTelefono.getText());
		String email = txtEmail.getText();

		int dN = Integer.parseInt(txtFechaNacimientoD.getText());
		int mN = Integer.parseInt(txtFechaNacimientoM.getText());
		int aN = Integer.parseInt(txtFechaNacimientoA.getText());

		String direccion = txtDireccion.getText();
		String dniJefe = txtDniJefe.getText();
		double salario = Double.parseDouble(txtSalario.getText());
		double comision = Double.parseDouble(txtComision.getText());
		
		if (!edicion) {
			fechaAlta = new Fecha();
		}
		
		boolean activo = chkActivo.isSelected();

		String password = new String(pwdPassword.getPassword());

		ArrayList<String> camposTxt = new ArrayList<String>();
		camposTxt.addAll(Arrays.asList(dni, nombre, apellidos, email, direccion, password));

		if (camposTxt.contains("")) {
			JOptionPane.showMessageDialog(this, (String) "Campo vacío", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else if (dni.length() != 9) {
			JOptionPane.showMessageDialog(this, (String) "DNI inválido", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else if (telefono < 600000000 || telefono > 999999999) {
			JOptionPane.showMessageDialog(this, (String) "Teléfono inválido", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else if (dN < 1 || dN > 31 || mN < 1 || mN > 12 || aN < 1900
				|| aN > Calendar.getInstance().get(Calendar.YEAR)) {
			JOptionPane.showMessageDialog(this, (String) "Fecha no válida", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else if (salario <= 0) {
			JOptionPane.showMessageDialog(this, (String) "El salario debe ser mayor que 0", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} else if (comision < 0) {
			JOptionPane.showMessageDialog(this, (String) "La comisión no puede ser menor que 0", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} else if (cmbCuenta.getSelectedIndex() < 0) {
			JOptionPane.showMessageDialog(this, (String) "Seleccione un tipo de cuenta", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} else if (cmbFuente.getSelectedIndex() < 0 || cmbTema.getSelectedIndex() < 0) {
			JOptionPane.showMessageDialog(this, (String) "Seleccione la configuración de la cuenta", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} else if (!edicion && alDNIs.contains(dni)) {
			JOptionPane.showMessageDialog(this, (String) "La cuenta ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else if (!dniJefe.equals("") && dniJefe.length() != 9) {
			JOptionPane.showMessageDialog(this,
					(String) "DNI del Jefe inválido, si el empleado no tiene jefe el campo debe estar vacío.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} else if (!dniJefe.equals("") && !alDNIs.contains(dniJefe)) {
			JOptionPane.showMessageDialog(this,
					(String) "DNI del Jefe inválido, no existe un empleado con el DNI " + dniJefe, "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} else {
			Fecha fechaNacimiento = new Fecha(dN, mN, aN);

			String tipo = (String) cmbCuenta.getSelectedItem();

			String tema = (String) cmbTema.getSelectedItem();
			String fuente = (String) cmbFuente.getSelectedItem();

			boolean temaOscuro = tema.equals("Oscuro") ? true : false;

			Datos.guardarCuenta(new Cuenta(dni, nombre, apellidos, String.valueOf(telefono), email, direccion,
					new Ajustes(temaOscuro, fuente), dniJefe, password, salario, comision, fechaNacimiento, tipo,
					fechaAlta, activo), edicion);

			return true;
		}

		return false;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object o = ae.getSource();

		int guardar = JOptionPane.YES_OPTION;

		if (o == btnCancelar) {
			guardar = Salir.edicion();
		}

		boolean valido = false;

		if (guardar == JOptionPane.YES_OPTION) {
			valido = guardar();
		}

		if (guardar == JOptionPane.NO_OPTION || valido) {
			AdministrarCuentas.actualizarTabla();
			AdministrarCuentas.botones(true);
			this.dispose();
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
	public void windowClosing(WindowEvent e) {
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