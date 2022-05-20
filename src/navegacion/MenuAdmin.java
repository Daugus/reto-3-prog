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

import administracion.AdministrarEmpleados;
import administracion.AdministrarMateriales;
import edicion.EditarAjustes;
import funciones.Datos;
import funciones.Log;
import funciones.Salir;
import ordenes.CrearOrden;

/**
 * menú del administrador
 */
public class MenuAdmin extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private JButton btnCerrarSesion;
	private JButton btnOrden;
	private JButton btnCrearOrden;
	private JButton btnEmpleados;
	private JButton btnMateriales;
	private JButton btnAjustes;

	/**
	 * carga los elementos de la ventana
	 */
	public MenuAdmin() {
		setResizable(false);
		setTitle("Menú del administrador | " + Inicio.empleadoActual.getNombre());
		setIconImage(new ImageIcon(getClass().getResource("/icono.png")).getImage());

		setBounds(100, 100, 575, 220);
		getContentPane().setPreferredSize(new Dimension(575, 220));
		pack();

		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		btnAjustes = new JButton("Ajustes de usuario");
		btnAjustes.setBounds(385, 170, 180, 40);
		panelPrincipal.add(btnAjustes);

		btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.setBounds(10, 170, 180, 40);
		panelPrincipal.add(btnCerrarSesion);

		btnOrden = new JButton("Administrar órdenes y facturas");
		btnOrden.setBounds(50, 10, 230, 60);
		panelPrincipal.add(btnOrden);

		btnCrearOrden = new JButton("Crear orden de trabajo");
		btnCrearOrden.setBounds(50, 85, 230, 60);
		panelPrincipal.add(btnCrearOrden);

		btnEmpleados = new JButton("Administrar empleados");
		btnEmpleados.setBounds(295, 10, 230, 60);
		panelPrincipal.add(btnEmpleados);

		btnMateriales = new JButton("Administrar materiales");
		btnMateriales.setBounds(295, 85, 230, 60);
		panelPrincipal.add(btnMateriales);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		// - JButton -
		btnCerrarSesion.addActionListener(this);
		btnOrden.addActionListener(this);
		btnCrearOrden.addActionListener(this);
		btnEmpleados.addActionListener(this);
		btnMateriales.addActionListener(this);
		btnAjustes.addActionListener(this);

		// ===== ajustes de usuario =====
		// recargar los ajustes en caso de que se hayan editado
		Inicio.empleadoActual.setAjustes(Datos.cargarAjustes(Inicio.empleadoActual.getDNI(), true));

		// --- fuente ---
		btnCerrarSesion.setFont(Inicio.fuenteObjetos);
		btnOrden.setFont(Inicio.fuenteObjetos);
		btnCrearOrden.setFont(Inicio.fuenteObjetos);
		btnEmpleados.setFont(Inicio.fuenteObjetos);
		btnMateriales.setFont(Inicio.fuenteObjetos);
		btnAjustes.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		btnCerrarSesion.setBackground(Inicio.colorFondoObjetos);
		btnOrden.setBackground(Inicio.colorFondoObjetos);
		btnCrearOrden.setBackground(Inicio.colorFondoObjetos);
		btnEmpleados.setBackground(Inicio.colorFondoObjetos);
		btnMateriales.setBackground(Inicio.colorFondoObjetos);
		btnAjustes.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		btnCerrarSesion.setForeground(Inicio.colorFuenteObjetos);
		btnOrden.setForeground(Inicio.colorFuenteObjetos);
		btnCrearOrden.setForeground(Inicio.colorFuenteObjetos);
		btnEmpleados.setForeground(Inicio.colorFuenteObjetos);
		btnMateriales.setForeground(Inicio.colorFuenteObjetos);
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
		if (o == btnOrden) {
			MenuListas ml = new MenuListas();
			ml.setVisible(true);

			this.dispose();
		} else if (o == btnCrearOrden) {
			CrearOrden co = new CrearOrden();
			co.setVisible(true);

			this.dispose();
		} else if (o == btnCerrarSesion) {
			Login l = new Login();
			l.setVisible(true);
			Log.logout();

			this.dispose();
		} else if (o == btnEmpleados) {
			AdministrarEmpleados ac = new AdministrarEmpleados(true);
			ac.setVisible(true);

			this.dispose();
		} else if (o == btnMateriales) {
			AdministrarMateriales am = new AdministrarMateriales();
			am.setVisible(true);

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