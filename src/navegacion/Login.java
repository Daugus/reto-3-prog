package navegacion;

import java.awt.Dimension;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import clases.Cuenta;
import funciones.Archivos;
import funciones.Log;

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
	
	public Login()
	{
		Archivos.reiniciarAjustes();

		setResizable(false);
		setTitle("Login");
		
		setBounds(100, 100, 350, 200);
		getContentPane().setPreferredSize(new Dimension(350, 200));
		pack();

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
				
				Archivos.cargarAjustes();
				
				if (!password.equals(Inicio.cuentaActual.getPassword()))
				{
					// si la contraseña es incorrecta saca ventana de error
					JOptionPane.showMessageDialog(this, (String) "Contraseña incorrecta", "ERROR",
							JOptionPane.ERROR_MESSAGE);

					Log.error("Se introducido una contraseña incorrecta");
				}
				else
				{
					Log.login();

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
				JOptionPane.showMessageDialog(this, (String) "La cuenta " + dni + " no está registrada", "ERROR",
						JOptionPane.ERROR_MESSAGE);
				
				Log.error("La cuenta " + dni + " no está registrada");
			}
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
		System.exit(0);
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