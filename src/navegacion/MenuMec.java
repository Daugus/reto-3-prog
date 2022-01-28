package navegacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edicion.EditarAjustes;
import funciones.Salir;

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
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Menú mecánico");
		
		setBounds(100, 100, 750, 550);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Inicio.colorFondo);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		btnAjustes = new JButton("Ajustes de usuario");
		btnAjustes.setFont(new Font(Inicio.fuente, Font.PLAIN, 14));
		btnAjustes.setBounds(550, 11, 174, 58);
		panelPrincipal.add(btnAjustes);
		
		btnOrdenPrim = new JButton("Lista órdenes de trabajo");
		btnOrdenPrim.setFont(new Font(Inicio.fuente, Font.PLAIN, 17));
		btnOrdenPrim.setBounds(230, 226, 270, 62);
		panelPrincipal.add(btnOrdenPrim);
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font(Inicio.fuente, Font.PLAIN, 14));
		btnVolver.setBounds(10, 442, 174, 58);
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
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();

		if (o == btnOrdenPrim)
		{
			ListaOrdenesPrim lop = new ListaOrdenesPrim();
			lop.setLocationRelativeTo(null);
			lop.setVisible(true);
			
			this.dispose();
		}
		else if (o == btnVolver)
		{
			Login l = new Login();
			l.setLocationRelativeTo(null);
			l.setVisible(true);
			
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