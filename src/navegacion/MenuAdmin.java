package navegacion;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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

public class MenuAdmin extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private JButton btnCerrarSesion;
	private JButton btnOrden;
	private JButton btnCrearOrden;
	private JButton btnEmpleados;
	private JButton btnMateriales;
	private JButton btnAjustes;

	public MenuAdmin() {
		setResizable(false);
		setTitle("Menú de atención al cliente | " + Inicio.empleadoActual.getNombre());

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
		Inicio.empleadoActual.setAjustes(Datos.cargarAjustes(Inicio.empleadoActual.getDNI(), false));

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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
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

	@Override
	public void windowClosing(WindowEvent e) {
		Salir.general(this);
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