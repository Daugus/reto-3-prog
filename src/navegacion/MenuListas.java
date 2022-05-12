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

import funciones.Salir;
import ordenes.ListaFacturas;
import ordenes.ListaOrdenes;

/**
 * menú para acceder a las listas de órdenes y facturas
 */
public class MenuListas extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;
	private JButton btnOrdenes;
	private JButton btnFacturas;
	private JButton btnVolver;

	/**
	 * carga los elementos de la ventana
	 */
	public MenuListas() {
		setResizable(false);
		setTitle("Órdenes y facturas | " + Inicio.empleadoActual.getNombre());

		setBounds(100, 100, 380, 220);
		getContentPane().setPreferredSize(new Dimension(380, 220));
		pack();

		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		btnOrdenes = new JButton("Órdenes de trabajo");
		btnOrdenes.setBounds(75, 10, 230, 60);
		panelPrincipal.add(btnOrdenes);

		btnFacturas = new JButton("Facturas");
		btnFacturas.setBounds(75, 85, 230, 60);
		panelPrincipal.add(btnFacturas);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 170, 180, 40);
		panelPrincipal.add(btnVolver);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		btnOrdenes.addActionListener(this);
		btnFacturas.addActionListener(this);
		btnVolver.addActionListener(this);
		btnOrdenes.setFont(Inicio.fuenteObjetos);
		btnFacturas.setFont(Inicio.fuenteObjetos);
		btnVolver.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);
		btnOrdenes.setBackground(Inicio.colorFondoObjetos);
		btnFacturas.setBackground(Inicio.colorFondoObjetos);
		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnOrdenes.setForeground(Inicio.colorFuenteObjetos);
		btnFacturas.setForeground(Inicio.colorFuenteObjetos);
		btnVolver.setForeground(Inicio.colorFuenteObjetos);
	}

	/**
	 * invocado cuando ocurren una acción
	 * 
	 * @param ae el evento de acción
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object o = ae.getSource();

		if (o == btnVolver) {
			if (Inicio.empleadoActual.getTipo().equals("Mecanico")) {
				MenuMecanico mm = new MenuMecanico();
				mm.setVisible(true);
			} else {
				MenuAdmin ma = new MenuAdmin();
				ma.setVisible(true);
			}

			this.dispose();
		} else {
			JFrame lista = null;
			if (o == btnOrdenes) {
				lista = new ListaOrdenes();
			} else if (o == btnFacturas) {
				lista = new ListaFacturas();
			}

			lista.setVisible(true);

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