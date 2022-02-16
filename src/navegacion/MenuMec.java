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

import edicion.EditarAjustes;
import funciones.Archivos;
import funciones.Log;
import funciones.Salir;
import ordenes.ListaPrimarias;

/**
 * esta clase difiene la ventana de mecanico
 * 
 * @author Grupo 2
 * @version 2.0.1
 */
public class MenuMec extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private JButton btnAjustes;
	private JButton btnOrdenPrim;
	private JButton btnCerrarSesion;

	/**
	 * contiene la ventana de acceso mecanico
	 */
	public MenuMec() {
		setResizable(false);
		setTitle("Menú mecánico | " + Inicio.cuentaActual.getNombre());

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

		btnOrdenPrim = new JButton("Lista órdenes de trabajo");
		btnOrdenPrim.setBounds(173, 68, 230, 60);
		panelPrincipal.add(btnOrdenPrim);

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
		btnOrdenPrim.addActionListener(this);
		btnAjustes.addActionListener(this);

		// ===== ajustes de usuario =====
		// recargar los ajustes en caso de que se hayan editado
		Archivos.cargarAjustes();

		// --- fuente ---
		btnCerrarSesion.setFont(Inicio.fuenteObjetos);
		btnOrdenPrim.setFont(Inicio.fuenteObjetos);
		btnAjustes.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		btnCerrarSesion.setBackground(Inicio.colorFondoObjetos);
		btnOrdenPrim.setBackground(Inicio.colorFondoObjetos);
		btnAjustes.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		btnCerrarSesion.setForeground(Inicio.colorFuenteObjetos);
		btnOrdenPrim.setForeground(Inicio.colorFuenteObjetos);
		btnAjustes.setForeground(Inicio.colorFuenteObjetos);
	}

	/**
	 * invocado cuando una accion ocurre sobre los elementos
	 * 
	 * @param ae el evento a procesar
	 * @see Actionlistener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o == btnOrdenPrim) {
			ListaPrimarias lop = new ListaPrimarias();
			this.setVisible(false);
			lop.setVisible(true);

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
	 * invocado cuando el usuario intenta cerrar la ventana
	 * 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		Salir.general(this);
	}

	/**
	 * Invocado la primera vez una ventana se ha hecho visible
	 * 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		// comportamiento por defecto
	}

	/**
	 * Invocado cuando una ventana se cerro como resultado llamando a dispose en la
	 * ventana
	 * 
	 * @param e evento a procesar
	 */
	@Override
	public void windowClosed(WindowEvent e) {
		// comportamiento por defecto
	}

	/**
	 * Invocado cuando a una ventana se cambio de normal a minimizado por varias
	 * plataformas una minimizada ventana se procesa como el icono especificado en
	 * la propiedad de siconImage
	 * 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowIconified(WindowEvent e) {
		// comportamiento por defecto
	}

	/**
	 * cuando una ventana cambia de minimizado a ventana normal
	 * 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowDeiconified(WindowEvent e) {
		// comportamiento por defecto
	}

	/**
	 * Invocado cuando la ventana es capacitado a ser ventana activa solo un frame o
	 * un dialog puede ser ventana activa
	 * 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		// comportamiento por defecto
	}

	/**
	 * Invocado cuando una ventana no es langer la ventana activa solo un Frame o un
	 * Dialog puede ser ventana activa
	 * 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowDeactivated(WindowEvent e) {
		// comportamiento por defecto
	}
}