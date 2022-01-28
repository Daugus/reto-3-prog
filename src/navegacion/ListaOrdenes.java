package navegacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import funciones.Salir;

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

	private JButton btnOrdenesPrim;
	private JButton btnOrdenesPend;
	private JButton btnFacturasAprob;
	private JButton btnVolver;

	{
		setResizable(false);
		setTitle("Órdenes");
		
		setBounds(100, 100, 750, 515);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		JLabel lblTitulo = new JLabel("Listado de órdenes de trabajo");
		lblTitulo.setBounds(22, 22, 354, 26);
		panelPrincipal.add(lblTitulo);
		
		btnOrdenesPrim = new JButton("Órdenes primarias");
		btnOrdenesPrim.setBounds(172, 130, 291, 41);
		panelPrincipal.add(btnOrdenesPrim);
		
		btnOrdenesPend = new JButton("Órdenes pendientes");
		btnOrdenesPend.setBounds(172, 207, 291, 41);
		panelPrincipal.add(btnOrdenesPend);
		
		btnFacturasAprob = new JButton("Facturas aprobadas");
		btnFacturasAprob.setBounds(172, 291, 291, 41);
		panelPrincipal.add(btnFacturasAprob);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 407, 174, 58);
		panelPrincipal.add(btnVolver);
		
		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action && Focus ---
		// - JButton -
		btnOrdenesPrim.addActionListener(this);
		btnOrdenesPend.addActionListener(this);
		btnFacturasAprob.addActionListener(this);
		btnVolver.addActionListener(this);

		// ===== ajustes de usuario =====
		// --- fuente ---
		lblTitulo.setFont(Inicio.fuente);

		btnOrdenesPrim.setFont(Inicio.fuenteObjetos);
		btnOrdenesPend.setFont(Inicio.fuenteObjetos);
		btnFacturasAprob.setFont(Inicio.fuenteObjetos);
		btnVolver.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		btnOrdenesPrim.setBackground(Inicio.colorFondoObjetos);
		btnOrdenesPend.setBackground(Inicio.colorFondoObjetos);
		btnFacturasAprob.setBackground(Inicio.colorFondoObjetos);
		btnVolver.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		lblTitulo.setForeground(Inicio.colorFuente);

		btnOrdenesPrim.setForeground(Inicio.colorFuenteObjetos);
		btnOrdenesPend.setForeground(Inicio.colorFuenteObjetos);
		btnFacturasAprob.setForeground(Inicio.colorFuenteObjetos);
		btnVolver.setForeground(Inicio.colorFuenteObjetos);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		
		if (o == btnOrdenesPrim)
		{
			ListaOrdenesPrim lop = new ListaOrdenesPrim();
			lop.setLocationRelativeTo(null);
			lop.setVisible(true);

			this.dispose();
		}
		else if (o == btnOrdenesPend)
		{
			
		}
		else if (o == btnFacturasAprob)
		{
			
		}
		else if (o == btnVolver)
		{
			if (Inicio.cuentaActual.getMecanico())
			{
				MenuMec mm = new MenuMec();
				mm.setLocationRelativeTo(null);
				mm.setVisible(true);
			}
			else
			{
				MenuAtc ma = new MenuAtc();
				ma.setLocationRelativeTo(null);
				ma.setVisible(true);
			}

			this.dispose();
		}
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