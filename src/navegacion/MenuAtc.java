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
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Menú de atención al cliente");
		
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
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font(Inicio.fuente, Font.PLAIN, 14));
		btnVolver.setBounds(10, 442, 174, 58);
		panelPrincipal.add(btnVolver);
		
		btnOrden = new JButton("Lista órdenes de trabajo");
		btnOrden.setFont(new Font(Inicio.fuente, Font.PLAIN, 17));
		btnOrden.setBounds(118, 171, 230, 70);
		panelPrincipal.add(btnOrden);
		
		btnNuevaOrdenPrim = new JButton("Crear orden de trabajo");
		btnNuevaOrdenPrim.setFont(new Font(Inicio.fuente, Font.PLAIN, 17));
		btnNuevaOrdenPrim.setBounds(118, 256, 230, 70);
		panelPrincipal.add(btnNuevaOrdenPrim);
		
		btnCuentas = new JButton("Administrar cuentas");
		btnCuentas.setFont(new Font(Inicio.fuente, Font.PLAIN, 17));
		btnCuentas.setBounds(376, 171, 230, 70);
		panelPrincipal.add(btnCuentas);
		
		btnMateriales = new JButton("Administrar materiales");
		btnMateriales.setFont(new Font(Inicio.fuente, Font.PLAIN, 17));
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
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		/**
		 * Si se pulsa el boton btnOrdenesTrabajo, instanciar LaC de la clase ListaAtClientes
		 */
		if (o == btnOrden)
		{
			ListaOrdenes lo = new ListaOrdenes();
			lo.setLocationRelativeTo(null);
			lo.setVisible(true);
			
			this.dispose();
		}
		/**
		 * Si se pulsa el boton btnNuevaOrden, instanciar BI de la clase Base_Inicial
		 */
		else if (o == btnNuevaOrdenPrim)
		{
			CrearOrdenPrim cop = new CrearOrdenPrim();
			cop.setLocationRelativeTo(null);
			cop.setVisible(true);
			
			this.dispose();
		}
		/**
		 * Si se pulsa el boton btnVolver, instanciar LaC de ListaAtClientes
		 */	
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