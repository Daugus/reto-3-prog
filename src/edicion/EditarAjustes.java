package edicion;

import java.awt.Dimension;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import clases.Ajustes;
import funciones.Datos;
import funciones.Salir;
import navegacion.Inicio;
import navegacion.MenuAdmin;
import navegacion.MenuMecanico;

/**
 * ventana de edición de ajustes
 */
public class EditarAjustes extends JFrame implements ActionListener, WindowListener, FocusListener {
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private JLabel lblFuente;
	private JLabel lblFondo;
	private JComboBox<String> cmbFondo;
	private JComboBox<String> cmbFuente;

	private JButton btnCancelar;
	private JButton btnGuardar;

	/**
	 * carga los elementos de la ventana
	 */
	public EditarAjustes() {
		setResizable(false);
		setTitle("Editar ajustes | " + Inicio.empleadoActual.getNombre());

		setBounds(100, 100, 396, 195);
		getContentPane().setPreferredSize(new Dimension(396, 195));
		pack();

		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(10, 130, 180, 40);
		panelPrincipal.add(btnCancelar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(206, 130, 180, 40);
		panelPrincipal.add(btnGuardar);

		lblFuente = new JLabel("Fuente:");
		lblFuente.setHorizontalAlignment(SwingConstants.LEFT);
		lblFuente.setBounds(73, 70, 100, 35);
		panelPrincipal.add(lblFuente);

		lblFondo = new JLabel("Tema:");
		lblFondo.setHorizontalAlignment(SwingConstants.LEFT);
		lblFondo.setBounds(73, 25, 100, 35);
		panelPrincipal.add(lblFondo);

		cmbFondo = new JComboBox<String>();
		cmbFondo.addItem("Oscuro");
		cmbFondo.addItem("Claro");
		cmbFondo.setBounds(173, 25, 150, 35);
		panelPrincipal.add(cmbFondo);

		cmbFuente = new JComboBox<String>();
		cmbFuente.addItem("Segoe UI");
		cmbFuente.addItem("Tahoma");
		cmbFuente.setBounds(173, 70, 150, 35);
		panelPrincipal.add(cmbFuente);

		cargarAjustes();

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		btnCancelar.addActionListener(this);
		btnGuardar.addActionListener(this);

		// ===== ajustes de usuario =====
		// --- fuente ---
		lblFuente.setFont(Inicio.fuente);
		lblFondo.setFont(Inicio.fuente);

		btnCancelar.setFont(Inicio.fuenteObjetos);
		btnGuardar.setFont(Inicio.fuenteObjetos);

		cmbFondo.setFont(Inicio.fuenteObjetos);
		cmbFuente.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		btnCancelar.setBackground(Inicio.colorFondoObjetos);
		btnGuardar.setBackground(Inicio.colorFondoObjetos);

		cmbFondo.setBackground(Inicio.colorFondoObjetos);
		cmbFuente.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		lblFuente.setForeground(Inicio.colorFuente);
		lblFondo.setForeground(Inicio.colorFuente);

		btnCancelar.setForeground(Inicio.colorFuenteObjetos);
		btnGuardar.setForeground(Inicio.colorFuenteObjetos);

		cmbFondo.setForeground(Inicio.colorFuenteObjetos);
		cmbFuente.setForeground(Inicio.colorFuenteObjetos);
	}

	private void cargarAjustes() {
		String tema = null;
		if (Inicio.empleadoActual.getAjustes().isTemaOscuro()) {
			tema = "Oscuro";
		} else {
			tema = "Claro";
		}

		String fuente = Inicio.empleadoActual.getAjustes().getFuente().getFamily();

		cmbFondo.setSelectedItem(tema);
		cmbFuente.setSelectedItem(fuente);
	}

	private void guardar() {
		String tema = (String) cmbFondo.getSelectedItem();
		String fuente = (String) cmbFuente.getSelectedItem();

		boolean temaOscuro = true;
		if (tema.equals("Oscuro")) {
			temaOscuro = true;
		} else if (tema.equals("Claro")) {
			temaOscuro = false;
		}

		Datos.guardarAjustes(new Ajustes(Inicio.empleadoActual.getDNI(), temaOscuro, fuente));

		Datos.cargarAjustes(Inicio.empleadoActual.getDNI(), true);
	}

	/**
	 * invocado cuando ocurren una acción
	 * 
	 * @param ae el evento de acción
	 */
	public void actionPerformed(ActionEvent ae) {
		Object o = ae.getSource();

		int guardar = JOptionPane.YES_OPTION;
		if (o == btnCancelar) {
			guardar = Salir.edicion();
		}

		if (guardar != JOptionPane.CANCEL_OPTION) {
			if (guardar == JOptionPane.YES_OPTION) {
				guardar();
			}

			JFrame menu;
			if (Inicio.empleadoActual.getTipo().equals("Mecanico")) {
				menu = new MenuMecanico();
			} else {
				menu = new MenuAdmin();
			}

			menu.setVisible(true);

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
	 * @param fg el evento de enfoque
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