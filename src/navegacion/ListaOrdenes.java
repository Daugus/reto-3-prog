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
import ordenes.ListaPendientes;
import ordenes.ListaPrimarias;

/**
 * 
 * @author Grupo 2
 *
 */
public class ListaOrdenes extends JFrame implements ActionListener, WindowListener
{
	public ListaOrdenes() {
	}
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private JButton btnPrimarias;
	private JButton btnPendientes;
	private JButton btnFacturas;
	private JButton btnVolver;

	{
		setResizable(false);
		setTitle("Órdenes | " + Inicio.cuentaActual.getNombre());
		
		setBounds(100, 100, 330, 295);
		getContentPane().setPreferredSize(new Dimension(330, 295));
		pack();
		
		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		btnPrimarias = new JButton("Órdenes primarias");
		btnPrimarias.setBounds(50, 10, 230, 60);
		panelPrincipal.add(btnPrimarias);
		
		btnPendientes = new JButton("Órdenes pendientes");
		btnPendientes.setBounds(50, 85, 230, 60);
		panelPrincipal.add(btnPendientes);
		
		btnFacturas = new JButton("Facturas aprobadas");
		btnFacturas.setBounds(50, 160, 230, 60);
		panelPrincipal.add(btnFacturas);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 245, 180, 40);
		panelPrincipal.add(btnVolver);
		
		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action && Focus ---
		// - JButton -
		btnPrimarias.addActionListener(this);
		btnPendientes.addActionListener(this);
		btnFacturas.addActionListener(this);
		btnVolver.addActionListener(this);

		btnPrimarias.setFont(Inicio.fuenteObjetos);
		btnPendientes.setFont(Inicio.fuenteObjetos);
		btnFacturas.setFont(Inicio.fuenteObjetos);
		btnVolver.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		btnPrimarias.setBackground(Inicio.colorFondoObjetos);
		btnPendientes.setBackground(Inicio.colorFondoObjetos);
		btnFacturas.setBackground(Inicio.colorFondoObjetos);
		btnVolver.setBackground(Inicio.colorFondoObjetos);

		btnPrimarias.setForeground(Inicio.colorFuenteObjetos);
		btnPendientes.setForeground(Inicio.colorFuenteObjetos);
		btnFacturas.setForeground(Inicio.colorFuenteObjetos);
		btnVolver.setForeground(Inicio.colorFuenteObjetos);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		
		if (o == btnVolver)
		{
			if (Inicio.cuentaActual.esMecanico())
			{
				MenuMec mm = new MenuMec();
				mm.setVisible(true);
			}
			else
			{
				MenuAtc ma = new MenuAtc();
				ma.setVisible(true);
			}
			
			this.dispose();
		}
		else
		{
			JFrame lista = null;
			if (o == btnPrimarias)
			{
				lista = new ListaPrimarias();
			}
			else if (o == btnPendientes)
			{
				lista = new ListaPendientes();
			}
			else if (o == btnFacturas)
			{
				lista = new ListaFacturas();
			}

			lista.setVisible(true);
			
			this.dispose();
		}
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
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