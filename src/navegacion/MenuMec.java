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

public class MenuMec extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private JButton btnAjustes;
	private JButton btnOrdenPrim;
	private JButton btnCerrarSesion;

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