package navegacion;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import clases.Empleado;
import funciones.Datos;
import funciones.Log;

/**
 * ventana de inicio de sesión
 */
public class Login extends JFrame implements ActionListener, WindowListener, FocusListener {
	private static final long serialVersionUID = 1531539371445418371L;
	private JPanel panelPrincipal;

	private JButton btnAcceder;

	private JPasswordField pwdPassword;
	private JTextField txtDNI;

	/**
	 * carga los elementos de la ventana
	 */
	public Login() {
		Datos.reiniciarAjustes();

		setResizable(false);
		setTitle("Login");
		setIconImage(new ImageIcon(getClass().getResource("/icono.png")).getImage());

		setBounds(100, 100, 350, 200);
		getContentPane().setPreferredSize(new Dimension(350, 200));
		pack();

		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		btnAcceder = new JButton("Acceder");
		btnAcceder.setBounds(85, 135, 180, 40);
		panelPrincipal.add(btnAcceder);

		JLabel lblPassword = new JLabel("Contraseña: ");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setBounds(50, 75, 100, 35);
		panelPrincipal.add(lblPassword);

		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(150, 75, 150, 35);
		panelPrincipal.add(pwdPassword);

		JLabel lblDNI = new JLabel("DNI: ");
		lblDNI.setHorizontalAlignment(SwingConstants.LEFT);
		lblDNI.setBounds(50, 25, 100, 35);
		panelPrincipal.add(lblDNI);

		txtDNI = new JTextField();
		txtDNI.setText("");
		txtDNI.setColumns(10);
		txtDNI.setBounds(150, 25, 150, 35);
		panelPrincipal.add(txtDNI);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action && Focus ---
		// - JTextField -
		txtDNI.addActionListener(this);
		txtDNI.addFocusListener(this);
		pwdPassword.addActionListener(this);
		pwdPassword.addFocusListener(this);

		// - JButton -
		btnAcceder.addActionListener(this);

		// ===== ajustes de usuario =====
		// --- fuente ---
		lblDNI.setFont(Inicio.fuente);
		lblPassword.setFont(Inicio.fuente);

		txtDNI.setFont(Inicio.fuenteObjetos);
		pwdPassword.setFont(Inicio.fuenteObjetos);
		btnAcceder.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		txtDNI.setBackground(Inicio.colorFondoObjetos);
		pwdPassword.setBackground(Inicio.colorFondoObjetos);
		btnAcceder.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		lblDNI.setForeground(Inicio.colorFuente);
		lblPassword.setForeground(Inicio.colorFuente);

		txtDNI.setForeground(Inicio.colorFuenteObjetos);
		pwdPassword.setForeground(Inicio.colorFuenteObjetos);
		btnAcceder.setForeground(Inicio.colorFuenteObjetos);
	}

	/**
	 * invocado cuando ocurren una acción
	 * 
	 * @param ae el evento de acción
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// recoje los datos introducidos
		String dni = txtDNI.getText();
		String password = new String(pwdPassword.getPassword());

		// intenta cargar la cuenta con el dni especificado
		if (dni.equals("")) {
			JOptionPane.showMessageDialog(this, (String) "Campo de DNI vacío", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (password.equals("")) {
			JOptionPane.showMessageDialog(this, (String) "Campo de contraseña vacío", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			Inicio.empleadoActual = new Empleado(Datos.iniciarSesion(dni));

			if (!password.equals(Inicio.empleadoActual.getPassword())) {
				// si la contraseña es incorrecta saca ventana de error
				JOptionPane.showMessageDialog(this, (String) "Contraseña incorrecta", "ERROR",
						JOptionPane.ERROR_MESSAGE);

				Log.error("Se introducido una contraseña incorrecta");
			} else {
				Inicio.empleadoActual.setAjustes(Datos.cargarAjustes(dni, true));
				Log.login();

				JFrame menu = null;
				if (Inicio.empleadoActual.getTipo().equals("Mecanico")) {
					menu = new MenuMecanico();
				} else {
					menu = new MenuAdmin();
				}

				menu.setVisible(true);

				this.dispose();
			}
		} catch (NullPointerException npe) {
			JOptionPane.showMessageDialog(this, (String) "La cuenta " + dni + " no está registrada", "ERROR",
					JOptionPane.ERROR_MESSAGE);

			Log.error("La cuenta " + dni + " no está registrada");
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
		System.exit(0);
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