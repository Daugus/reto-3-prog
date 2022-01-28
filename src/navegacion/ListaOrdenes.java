package navegacion;

import java.awt.Font;
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
		panelPrincipal.setBackground(Inicio.colorFondo);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		JLabel lblTitulo = new JLabel("Listado de órdenes de trabajo");
		lblTitulo.setForeground(Inicio.colorFuente);
		lblTitulo.setFont(new Font(Inicio.fuente, Font.BOLD, 21));
		lblTitulo.setBounds(22, 22, 354, 26);
		panelPrincipal.add(lblTitulo);
		
		btnOrdenesPrim = new JButton("Órdenes primarias");
		btnOrdenesPrim.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		btnOrdenesPrim.setBounds(172, 130, 291, 41);
		panelPrincipal.add(btnOrdenesPrim);
		
		btnOrdenesPend = new JButton("Órdenes pendientes");
		btnOrdenesPend.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		btnOrdenesPend.setBounds(172, 207, 291, 41);
		panelPrincipal.add(btnOrdenesPend);
		
		btnFacturasAprob = new JButton("Facturas aprobadas");
		btnFacturasAprob.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		btnFacturasAprob.setBounds(172, 291, 291, 41);
		panelPrincipal.add(btnFacturasAprob);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
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