package navegacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import administracion.AdministrarCuentas;
import administracion.AdministrarMateriales;
import edicion.EditarAjustes;
import funciones.Salir;

/**
 * 
 * @author Grupo 2
 *
 */
public class MenuAtc extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private JButton btnVolver;
	private JButton btnOrden;
	private JButton btnNuevaOrdenPrim;
	private JButton btnCuentas;
	private JButton btnMateriales;
	private JButton btnAjustes;
	
	public MenuAtc()
	{
		setResizable(false);
		setTitle("Menú de atención al cliente");
		
		setBounds(100, 100, 750, 550);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		btnAjustes = new JButton("Ajustes de usuario");
		btnAjustes.setBounds(550, 11, 174, 58);
		panelPrincipal.add(btnAjustes);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 442, 174, 58);
		panelPrincipal.add(btnVolver);
		
		btnOrden = new JButton("Lista órdenes de trabajo");
		btnOrden.setBounds(118, 171, 230, 70);
		panelPrincipal.add(btnOrden);
		
		btnNuevaOrdenPrim = new JButton("Crear orden de trabajo");
		btnNuevaOrdenPrim.setBounds(118, 256, 230, 70);
		panelPrincipal.add(btnNuevaOrdenPrim);
		
		btnCuentas = new JButton("Administrar cuentas");
		btnCuentas.setBounds(376, 171, 230, 70);
		panelPrincipal.add(btnCuentas);
		
		btnMateriales = new JButton("Administrar materiales");
		btnMateriales.setBounds(376, 256, 230, 70);
		panelPrincipal.add(btnMateriales);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		// - JButton -
		btnVolver.addActionListener(this);
		btnOrden.addActionListener(this);
		btnNuevaOrdenPrim.addActionListener(this);
		btnCuentas.addActionListener(this);
		btnMateriales.addActionListener(this);
		btnAjustes.addActionListener(this);

		// ===== ajustes de usuario =====
		// --- fuente ---
		btnVolver.setFont(Inicio.fuenteObjetos);
		btnOrden.setFont(Inicio.fuenteObjetos);
		btnNuevaOrdenPrim.setFont(Inicio.fuenteObjetos);
		btnCuentas.setFont(Inicio.fuenteObjetos);
		btnMateriales.setFont(Inicio.fuenteObjetos);
		btnAjustes.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnOrden.setBackground(Inicio.colorFondoObjetos);
		btnNuevaOrdenPrim.setBackground(Inicio.colorFondoObjetos);
		btnCuentas.setBackground(Inicio.colorFondoObjetos);
		btnMateriales.setBackground(Inicio.colorFondoObjetos);
		btnAjustes.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		btnVolver.setForeground(Inicio.colorFuenteObjetos);
		btnOrden.setForeground(Inicio.colorFuenteObjetos);
		btnNuevaOrdenPrim.setForeground(Inicio.colorFuenteObjetos);
		btnCuentas.setForeground(Inicio.colorFuenteObjetos);
		btnMateriales.setForeground(Inicio.colorFuenteObjetos);
		btnAjustes.setForeground(Inicio.colorFuenteObjetos);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		if (o == btnOrden)
		{
			ListaOrdenes lo = new ListaOrdenes();
			lo.setLocationRelativeTo(null);
			lo.setVisible(true);
			
			this.dispose();
		}
		else if (o == btnNuevaOrdenPrim)
		{
			CrearOrdenPrim cop = new CrearOrdenPrim();
			cop.setLocationRelativeTo(null);
			cop.setVisible(true);
			
			this.dispose();
		}
		else if (o == btnVolver)
		{
			Login l = new Login();
			l.setLocationRelativeTo(null);
			l.setVisible(true);
			
			this.dispose();
		} 
		else if (o == btnCuentas)
		{
			AdministrarCuentas ac = new AdministrarCuentas();
			ac.setLocationRelativeTo(null);
			ac.setVisible(true);
			
			this.dispose();
		} 
		else if (o == btnMateriales)
		{
			AdministrarMateriales am = new AdministrarMateriales();
			am.setLocationRelativeTo(null);
			am.setVisible(true);

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