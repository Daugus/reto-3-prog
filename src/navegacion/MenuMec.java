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
import funciones.Log;
import funciones.Salir;
import ordenes.ListaOrdenesPrim;

/**
 * 
 * @author Grupo 2
 *
 */
public class MenuMec extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private JButton btnAjustes;
	private JButton btnOrdenPrim;
	private JButton btnVolver;
	
	public MenuMec()
	{
		setResizable(false);
		setTitle("Menú mecánico");
		
		setBounds(100, 100, 576, 221);
		getContentPane().setPreferredSize(new Dimension(576, 221));
		pack();

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
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 171, 180, 40);
		panelPrincipal.add(btnVolver);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		// - JButton -
		btnVolver.addActionListener(this);
		btnOrdenPrim.addActionListener(this);
		btnAjustes.addActionListener(this);

		// ===== ajustes de usuario =====
		// --- fuente ---
		btnVolver.setFont(Inicio.fuenteObjetos);
		btnOrdenPrim.setFont(Inicio.fuenteObjetos);
		btnAjustes.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnOrdenPrim.setBackground(Inicio.colorFondoObjetos);
		btnAjustes.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		btnVolver.setForeground(Inicio.colorFuenteObjetos);
		btnOrdenPrim.setForeground(Inicio.colorFuenteObjetos);
		btnAjustes.setForeground(Inicio.colorFuenteObjetos);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();

		if (o == btnOrdenPrim)
		{
			ListaOrdenesPrim lop = new ListaOrdenesPrim();
			lop.setLocationRelativeTo(null);
			this.setVisible(false);
			lop.setVisible(true);
			
			this.dispose();
		}
		else if (o == btnVolver)
		{
			Login l = new Login();
			l.setLocationRelativeTo(null);
			l.setVisible(true);
			Log.logout();
			
			this.dispose();
		} 
		else if (o == btnAjustes)
		{
			EditarAjustes ea = new EditarAjustes();

			ea.setLocationRelativeTo(null);
			ea.setVisible(true);

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