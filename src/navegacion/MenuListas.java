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

public class MenuListas extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;
	private JButton btnOrdenes;
	private JButton btnFacturas;
	private JButton btnVolver;

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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

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