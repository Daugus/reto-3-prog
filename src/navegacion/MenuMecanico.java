package navegacion;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edicion.EditarAjustes;
import funciones.Datos;
import funciones.Log;
import funciones.Salir;
import ordenes.ListaOrdenes;

/**
 * menú del mecánico
 */
public class MenuMecanico extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private JButton btnAjustes;
	private JButton btnOrdenes;
	private JButton btnCerrarSesion;

	/**
	 * carga los elementos de la ventana
	 */
	public MenuMecanico() {
		setResizable(false);
		setTitle("Menú del mecánico | " + Inicio.empleadoActual.getNombre());
		setIconImage(new ImageIcon(getClass().getResource("/icono.png")).getImage());

		setBounds(100, 100, 576, 221);
		getContentPane().setPreferredSize(new Dimension(576, 221));
		pack();

		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		btnAjustes = new JButton("Ajustes de usuario");
		btnAjustes.setBounds(385, 171, 180, 40);
		panelPrincipal.add(btnAjustes);

		btnOrdenes = new JButton("Administrar órdenes de trabajo");
		btnOrdenes.setBounds(173, 68, 230, 60);
		panelPrincipal.add(btnOrdenes);

		btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.setBounds(10, 171, 180, 40);
		panelPrincipal.add(btnCerrarSesion);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		// - JButton -
		btnCerrarSesion.addActionListener(this);
		btnOrdenes.addActionListener(this);
		btnAjustes.addActionListener(this);

		// ===== ajustes de usuario =====
		// recargar los ajustes en caso de que se hayan editado
		Inicio.empleadoActual.setAjustes(Datos.cargarAjustes(Inicio.empleadoActual.getDNI(), true));

		// --- fuente ---
		btnCerrarSesion.setFont(Inicio.fuenteObjetos);
		btnOrdenes.setFont(Inicio.fuenteObjetos);
		btnAjustes.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		btnCerrarSesion.setBackground(Inicio.colorFondoObjetos);
		btnOrdenes.setBackground(Inicio.colorFondoObjetos);
		btnAjustes.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		btnCerrarSesion.setForeground(Inicio.colorFuenteObjetos);
		btnOrdenes.setForeground(Inicio.colorFuenteObjetos);
		btnAjustes.setForeground(Inicio.colorFuenteObjetos);
	}

	/**
	 * invocado cuando ocurren una acción
	 * 
	 * @param ae el evento de acción
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object o = ae.getSource();

		if (o == btnOrdenes) {
			ListaOrdenes lo = new ListaOrdenes();
			lo.setVisible(true);

			this.dispose();
		} else if (o == btnCerrarSesion) {
			Login l = new Login();
			l.setVisible(true);
			Log.logout();

			this.dispose();
		} else if (o == btnAjustes) {
			EditarAjustes ea = new EditarAjustes();

			ea.setVisible(true);

			this.dispose();
		}
	}

	/**
	 * invocado cuando se cierra la ventana
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowClosing(WindowEvent we) {
		Salir.general(this);
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