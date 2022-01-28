package navegacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import clases.Cuenta;
import funciones.Archivos;
import funciones.Salir;

/**
 * 
 * @author Grupo 2
 *
 */
public class Login extends JFrame implements ActionListener, WindowListener, FocusListener
{
	private static final long serialVersionUID = 1531539371445418371L;
	private JPanel panelPrincipal;

	public JButton btnAcceder;

	private JPasswordField pwdPassword;
	private JTextField txtDNI;
	
	/**
	 * crea frame de la clase.
	 */
	public Login()
	{
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Login");
		
		setBounds(100, 100, 497, 296);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.DARK_GRAY);
		panelPrincipal.setForeground(Color.WHITE);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		btnAcceder = new JButton("Acceder");
		btnAcceder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAcceder.setBounds(187, 186, 120, 36);
		panelPrincipal.add(btnAcceder);
		
		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setForeground(Color.WHITE);
		lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContrasena.setBounds(104, 127, 98, 13);
		panelPrincipal.add(lblContrasena);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(212, 117, 165, 34);
		panelPrincipal.add(pwdPassword);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setForeground(Color.WHITE);
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDni.setBounds(104, 69, 96, 13);
		panelPrincipal.add(lblDni);
		
		txtDNI = new JTextField();
		txtDNI.setText("");
		txtDNI.setColumns(10);
		txtDNI.setBounds(210, 58, 167, 34);
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
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// recoje los datos introducidos
		String dni = txtDNI.getText();
		String password = new String(pwdPassword.getPassword());
			
		// intenta cargar la cuenta con el dni especificado
		if (dni.equals("") || password.equals(""))
		{
			JOptionPane.showMessageDialog(this, (String) "Campo vacío", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			try
			{
				Inicio.cuentaActual = new Cuenta(Archivos.cargarCuenta(dni));
				
				Inicio.colorFondo = Inicio.cuentaActual.getAjustes().getFondo();
				if (Inicio.colorFondo.equals(Color.LIGHT_GRAY))
				{
					Inicio.colorFuente = Color.BLACK;
				}
				else
				{
					Inicio.colorFuente = Color.WHITE;
				}
				
				Inicio.fuente = Inicio.cuentaActual.getAjustes().getFuente();
				
				if (!password.equals(Inicio.cuentaActual.getPassword()))
				{
					// si la contraseña es incorrecta saca ventana de error
					JOptionPane.showMessageDialog(this, (String) "Contraseña errónea", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if (Inicio.cuentaActual.getMecanico())
					{
						// mecánico
						Inicio.cuentaActual.setMecanico(true);
						
						MenuMec mm = new MenuMec();
						this.setVisible(false);
						mm.setLocationRelativeTo(null);
						mm.setVisible(true);
						
						this.dispose();
					}
					else
					{
						// atención cliente (admin)
						Inicio.cuentaActual.setMecanico(false);
						
						MenuAtc ma = new MenuAtc();
						this.setVisible(false);
						ma.setLocationRelativeTo(null);
						ma.setVisible(true);
						
						this.dispose();
					}
				}
			}
			catch (NullPointerException npe)
			{
				// si la cuenta no existe saca ventana de error
				JOptionPane.showMessageDialog(this, (String) "La cuenta no existe", "ERROR",
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