package edicion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import clases.Ajustes;
import clases.Cuenta;
import funciones.Archivos;
import funciones.Salir;
import navegacion.Inicio;
import navegacion.MenuAtc;
import navegacion.MenuMec;

/**
 * 
 * @author Grupo_4
 * 
 */
public class EditarAjustes extends JFrame implements ActionListener, WindowListener, FocusListener
{
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private JLabel lblFuente;
	private JLabel lblFondo;
	
	public JComboBox<String> cmbFondo;
	public JComboBox<String> cmbFuente;

	private JButton btnCancelar;
	private JButton btnGuardar;

	public EditarAjustes() {
		setResizable(false);
		setTitle("Editar ajustes");
		
		setBounds(100, 100, 377, 285);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Inicio.colorFondo);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		btnCancelar.setBounds(10, 193, 160, 42);
		panelPrincipal.add(btnCancelar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		btnGuardar.setBounds(191, 193, 160, 42);
		panelPrincipal.add(btnGuardar);
		
		lblFuente = new JLabel("Fuente:");
		lblFuente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuente.setForeground(Inicio.colorFuente);
		lblFuente.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblFuente.setBounds(42, 105, 130, 40);
		panelPrincipal.add(lblFuente);
		
		lblFondo = new JLabel("Color de fondo:");
		lblFondo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFondo.setForeground(Inicio.colorFuente);
		lblFondo.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblFondo.setBounds(42, 55, 130, 40);
		panelPrincipal.add(lblFondo);
		
		cmbFondo = new JComboBox<String>();
		cmbFondo.addItem("Gris claro");
		cmbFondo.addItem("Gris oscuro");
		cmbFondo.addItem("Negro");
		cmbFondo.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		cmbFondo.setBounds(193, 55, 130, 40);
		panelPrincipal.add(cmbFondo);
		
		cmbFuente = new JComboBox<String>();
		cmbFuente.addItem("Tahoma");
		cmbFuente.addItem("Dialog");
		cmbFuente.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		cmbFuente.setBounds(193, 105, 130, 40);
		panelPrincipal.add(cmbFuente);
		
		cargarAjustes();

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		btnCancelar.addActionListener(this);
		btnGuardar.addActionListener(this);
	}
	
	private void cargarAjustes()
	{
		Color color = Inicio.cuentaActual.getAjustes().getFondo();
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
			
		String fuente = Inicio.cuentaActual.getAjustes().getFuente();
		
		cmbFondo.setSelectedItem(fondo);
		cmbFuente.setSelectedItem(fuente);
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		Object o = ae.getSource();
		
		if (o == btnCancelar)
		{
			if (Inicio.cuentaActual.getMecanico())
			{
				MenuMec mm = new MenuMec();
				this.setVisible(false);
				mm.setLocationRelativeTo(null);
				mm.setVisible(true);
			}
			else
			{
				MenuAtc ma = new MenuAtc();
				this.setVisible(false);
				ma.setLocationRelativeTo(null);
				ma.setVisible(true);
			}

			this.dispose();
		}
		else if (o == btnGuardar)
		{
			String fondo = (String) cmbFondo.getSelectedItem();
			String fuente = (String) cmbFuente.getSelectedItem();

			if (fondo.equals("Gris claro"))
			{
				Archivos.guardarAjustes(new Ajustes(Color.LIGHT_GRAY, fuente));
			}
			else if (fondo.equals("Gris oscuro"))
			{
				Archivos.guardarAjustes(new Ajustes(Color.DARK_GRAY, fuente));
			}
			else if (fondo.equals("Negro"))
			{
				Archivos.guardarAjustes(new Ajustes(Color.BLACK, fuente));
			}
			
			Inicio.colorFondo = Inicio.cuentaActual.getAjustes().getFondo();
			Inicio.cuentaActual = new Cuenta(Archivos.cargarCuenta(Inicio.cuentaActual.getDNI()));
			if (Inicio.colorFondo.equals(Color.LIGHT_GRAY))
			{
				Inicio.colorFuente = Color.BLACK;
			}
			else
			{
				Inicio.colorFuente = Color.WHITE;
			}

			Inicio.fuente = Inicio.cuentaActual.getAjustes().getFuente();

			if (Inicio.cuentaActual.getMecanico())
			{
				MenuMec mm = new MenuMec();
				this.setVisible(false);
				mm.setLocationRelativeTo(null);
				mm.setVisible(true);
			}
			else
			{
				MenuAtc ma = new MenuAtc();
				this.setVisible(false);
				ma.setLocationRelativeTo(null);
				ma.setVisible(true);
			}
			
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