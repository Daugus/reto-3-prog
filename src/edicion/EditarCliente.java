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
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import administracion.AdministrarClientes;
import clases.Cliente;
import clases.Fecha;
import funciones.Datos;
import funciones.Salir;
import navegacion.Inicio;

/**
 * ventana de edición de cliente
 */
public class EditarCliente extends JFrame implements ActionListener, FocusListener, WindowListener {
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private JButton btnCancelar;
	private JButton btnGuardar;

	private JTextField txtDNI;
	private JTextField txtNombre;
	private JTextField txtApellidos;

	private JTextField txtTelefono;
	private JTextField txtEmail;

	private JTextField txtDireccion;

	private JTextField txtFechaAltaD;
	private JTextField txtFechaAltaM;
	private JTextField txtFechaAltaA;

	private JCheckBox chkActivo;

	private boolean edicion;

	private ArrayList<String> alDNIs = new ArrayList<String>();

	/**
	 * carga los elementos de la ventana
	 */
	public EditarCliente() {
		setResizable(false);
		setTitle("Agregar nuevo cliente | " + Inicio.empleadoActual.getNombre());
		setIconImage(new ImageIcon(getClass().getResource("/icono.png")).getImage());

		setBounds(100, 100, 730, 285);
		getContentPane().setPreferredSize(new Dimension(730, 285));
		pack();

		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Inicio.colorFondo);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(177, 220, 180, 40);
		panelPrincipal.add(btnCancelar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(373, 220, 180, 40);
		panelPrincipal.add(btnGuardar);

		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setHorizontalAlignment(SwingConstants.LEFT);
		lblDireccion.setBounds(380, 25, 150, 35);
		panelPrincipal.add(lblDireccion);

		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(530, 25, 150, 35);
		panelPrincipal.add(txtDireccion);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(460, 115, 220, 35);
		panelPrincipal.add(txtEmail);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setBounds(380, 115, 80, 35);
		panelPrincipal.add(lblEmail);

		JLabel lblFechaAlta = new JLabel("Fecha de alta:");
		lblFechaAlta.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaAlta.setBounds(50, 160, 150, 35);
		panelPrincipal.add(lblFechaAlta);

		JLabel lblTel = new JLabel("Teléfono:");
		lblTel.setHorizontalAlignment(SwingConstants.LEFT);
		lblTel.setBounds(50, 115, 150, 35);
		panelPrincipal.add(lblTel);

		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setHorizontalAlignment(SwingConstants.LEFT);
		lblApellidos.setBounds(380, 70, 150, 35);
		panelPrincipal.add(lblApellidos);

		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(530, 70, 150, 35);
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

		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(200, 115, 150, 35);
		panelPrincipal.add(txtTelefono);

		txtFechaAltaD = new JTextField("");
		txtFechaAltaD.setBounds(200, 160, 35, 35);
		panelPrincipal.add(txtFechaAltaD);

		txtFechaAltaM = new JTextField("");
		txtFechaAltaM.setBounds(240, 160, 35, 35);
		panelPrincipal.add(txtFechaAltaM);

		txtFechaAltaA = new JTextField("");
		txtFechaAltaA.setBounds(280, 160, 70, 35);
		panelPrincipal.add(txtFechaAltaA);

		JLabel lblActivo = new JLabel("Activo:");
		lblActivo.setHorizontalAlignment(SwingConstants.LEFT);
		lblActivo.setBounds(380, 157, 150, 35);
		panelPrincipal.add(lblActivo);

		chkActivo = new JCheckBox("");
		chkActivo.setOpaque(false);
		chkActivo.setHorizontalAlignment(SwingConstants.CENTER);
		chkActivo.setBounds(530, 157, 150, 35);
		panelPrincipal.add(chkActivo);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action && Focus ---
		// - JTextField -
		ArrayList<JTextField> camposTexto = new ArrayList<JTextField>();
		camposTexto.addAll(Arrays.asList(txtDNI, txtNombre, txtApellidos, txtTelefono, txtEmail, txtFechaAltaD,
				txtFechaAltaM, txtFechaAltaA, txtDireccion));
		for (JTextField txt : camposTexto) {
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
		etiquetas.addAll(Arrays.asList(lblDNI, lblNombre, lblApellidos, lblTel, lblEmail, lblDireccion, lblFechaAlta));
		for (JLabel lbl : etiquetas) {
			lbl.setFont(Inicio.fuente);
			lbl.setForeground(Inicio.colorFuente);
		}

		for (JTextField txt : camposTexto) {
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

		// ===== valores por defecto =====
		txtDNI.setText("12345678A");
		txtNombre.setText("Nombre");
		txtTelefono.setText("12345679");
		Fecha f = new Fecha();
		txtFechaAltaD.setText(String.valueOf(f.getDay()));
		txtFechaAltaM.setText(String.valueOf(f.getMonth()));
		txtFechaAltaA.setText(String.valueOf(f.getYear()));
		txtDireccion.setText("Calle 1");
		txtApellidos.setText("Apellidos");
		txtEmail.setText("ejemplo@mail.com");
		chkActivo.setSelected(true);
	}

	/**
	 * guarda en un ArrayList los DNIs de los clientes existentes para comprobar si
	 * está duplicado
	 * 
	 * @param clientes lista de clientes
	 */
	public void setAlDNIs(ArrayList<Cliente> clientes) {
		for (Cliente c : clientes) {
			alDNIs.add(c.getDNI());
		}
	}

	/**
	 * escribe los datos del cliente que está siendo editado en los campos
	 * 
	 * @param cliente cliente que está siendo editado
	 */
	public void modoEdicion(Cliente cliente) {
		edicion = true;

		setTitle("Editar " + cliente.getDNI() + " | " + Inicio.empleadoActual.getNombre());

		txtDNI.setText(cliente.getDNI());
		txtDNI.setEnabled(false);

		txtNombre.setText(cliente.getNombre());
		txtApellidos.setText(cliente.getApellidos());

		txtTelefono.setText(cliente.getTelefono());
		txtEmail.setText(cliente.getEmail());

		txtDireccion.setText(cliente.getDireccion());

		txtFechaAltaD.setText(String.valueOf(cliente.getFechaAlta().getDay()));
		txtFechaAltaD.setEnabled(false);
		txtFechaAltaM.setText(String.valueOf(cliente.getFechaAlta().getMonth()));
		txtFechaAltaM.setEnabled(false);
		txtFechaAltaA.setText(String.valueOf(cliente.getFechaAlta().getYear()));
		txtFechaAltaA.setEnabled(false);

		chkActivo.setSelected(cliente.isActivo());
	}

	private boolean guardar() {
		try {
			String dni = txtDNI.getText();

			String nombre = txtNombre.getText();
			String apellidos = txtApellidos.getText();

			int telefono = Integer.parseInt(txtTelefono.getText());
			String email = txtEmail.getText();

			String direccion = txtDireccion.getText();

			Fecha fechaAlta;
			Calendar now = Calendar.getInstance();
			int dA = now.get(Calendar.DATE);
			int mA = now.get(Calendar.MONTH) + 1;
			int aA = now.get(Calendar.YEAR);
			fechaAlta = new Fecha(dA, mA, aA);

			ArrayList<String> camposTxt = new ArrayList<String>();
			camposTxt.addAll(Arrays.asList(dni, nombre, apellidos, email));

			if (camposTxt.contains("")) {
				JOptionPane.showMessageDialog(this, (String) "Campo de texto inválido", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else if (telefono < 600000000 || telefono > 999999999) {
				JOptionPane.showMessageDialog(this, (String) "Teléfono inválido", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else if (!edicion && Datos.listarClientes().contains(dni)) {
				JOptionPane.showMessageDialog(this, (String) "Cliente ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else {
				boolean activo = chkActivo.isSelected();
				Datos.guardarCliente(new Cliente(dni, nombre, apellidos, String.valueOf(telefono), email, direccion,
						fechaAlta, activo), edicion);

				return true;
			}
		} catch (NumberFormatException npe) {
			JOptionPane.showMessageDialog(this, (String) "Campo numérico vacío o incorrecto", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}

		return false;
	}

	/**
	 * invocado cuando ocurren una acción
	 * 
	 * @param ae el evento de acción
	 */
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
			AdministrarClientes.actualizarTabla();
			AdministrarClientes.botones(true);
			this.dispose();
		}
	}

	/**
	 * invocado cuando se enfoca un campo de texto
	 * 
	 * @param fg el evento de enfoque
	 */
	public void focusGained(FocusEvent fg) {
		JTextComponent txt = (JTextComponent) fg.getSource();
		txt.select(0, txt.getText().length());
	}

	/**
	 * invocado cuando se deja de enfocar un campo de texto
	 * 
	 * @param fl el evento de enfoque
	 */
	@Override
	public void focusLost(FocusEvent fl) {
		JTextComponent txt = (JTextComponent) fl.getSource();
		txt.select(0, 0);
	}

	/**
	 * invocado cuando se cierra la ventana
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowClosing(WindowEvent we) {
		btnCancelar.doClick();
	}

	/**
	 * invocado cuando se abre la ventana
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowOpened(WindowEvent we) {
		// comportamiento por defecto
	}

	/**
	 * invocado después de que se cierre la ventana
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowClosed(WindowEvent we) {
		// comportamiento por defecto
	}

	/**
	 * invocado cuando se minimiza la ventana
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowIconified(WindowEvent we) {
		// comportamiento por defecto
	}

	/**
	 * invocado cuando se maximiza la ventana
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowDeiconified(WindowEvent we) {
		// comportamiento por defecto
	}

	/**
	 * invocado cuando la ventana se convierte en la ventana activa
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowActivated(WindowEvent we) {
		// comportamiento por defecto
	}

	/**
	 * invocado cuando la ventana deja de ser la ventana activa
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowDeactivated(WindowEvent we) {
		// comportamiento por defecto
	}
}