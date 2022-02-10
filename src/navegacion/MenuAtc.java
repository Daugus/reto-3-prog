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

import administracion.AdministrarCuentas;
import administracion.AdministrarMateriales;
import edicion.EditarAjustes;

import funciones.Archivos;
import funciones.Log;
import funciones.Salir;
import ordenes.CrearPrimaria;

/**
 * esta clase difiene la ventana de atencion al cliente
 * @author Grupo 2
 * @version 2.0.1
 */
public class MenuAtc extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private JButton btnCerrarSesion;
	private JButton btnOrden;
	private JButton btnNuevaOrdenPrim;
	private JButton btnCuentas;
	private JButton btnMateriales;
	private JButton btnAjustes;
	/**
	 * contiene la ventana de acceso atencion al cliente
	 */
	public MenuAtc()
	{
		setResizable(false);
		setTitle("Menú de atención al cliente | " + Inicio.cuentaActual.getNombre());
		
		setBounds(100, 100, 575, 220);
		getContentPane().setPreferredSize(new Dimension(575, 220));
		pack();
		
		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		btnAjustes = new JButton("Ajustes de usuario");
		btnAjustes.setBounds(385, 170, 180, 40);
		panelPrincipal.add(btnAjustes);
		
		btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.setBounds(10, 170, 180, 40);
		panelPrincipal.add(btnCerrarSesion);
		
		btnOrden = new JButton("Lista órdenes de trabajo");
		btnOrden.setBounds(50, 10, 230, 60);
		panelPrincipal.add(btnOrden);
		
		btnNuevaOrdenPrim = new JButton("Crear orden de trabajo");
		btnNuevaOrdenPrim.setBounds(50, 85, 230, 60);
		panelPrincipal.add(btnNuevaOrdenPrim);
		
		btnCuentas = new JButton("Administrar cuentas");
		btnCuentas.setBounds(295, 10, 230, 60);
		panelPrincipal.add(btnCuentas);
		
		btnMateriales = new JButton("Administrar materiales");
		btnMateriales.setBounds(295, 85, 230, 60);
		panelPrincipal.add(btnMateriales);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		// - JButton -
		btnCerrarSesion.addActionListener(this);
		btnOrden.addActionListener(this);
		btnNuevaOrdenPrim.addActionListener(this);
		btnCuentas.addActionListener(this);
		btnMateriales.addActionListener(this);
		btnAjustes.addActionListener(this);

		// ===== ajustes de usuario =====
		// recargar los ajustes en caso de que se hayan editado
		Archivos.cargarAjustes();

		// --- fuente ---
		btnCerrarSesion.setFont(Inicio.fuenteObjetos);
		btnOrden.setFont(Inicio.fuenteObjetos);
		btnNuevaOrdenPrim.setFont(Inicio.fuenteObjetos);
		btnCuentas.setFont(Inicio.fuenteObjetos);
		btnMateriales.setFont(Inicio.fuenteObjetos);
		btnAjustes.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		btnCerrarSesion.setBackground(Inicio.colorFondoObjetos);
		btnOrden.setBackground(Inicio.colorFondoObjetos);
		btnNuevaOrdenPrim.setBackground(Inicio.colorFondoObjetos);
		btnCuentas.setBackground(Inicio.colorFondoObjetos);
		btnMateriales.setBackground(Inicio.colorFondoObjetos);
		btnAjustes.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		btnCerrarSesion.setForeground(Inicio.colorFuenteObjetos);
		btnOrden.setForeground(Inicio.colorFuenteObjetos);
		btnNuevaOrdenPrim.setForeground(Inicio.colorFuenteObjetos);
		btnCuentas.setForeground(Inicio.colorFuenteObjetos);
		btnMateriales.setForeground(Inicio.colorFuenteObjetos);
		btnAjustes.setForeground(Inicio.colorFuenteObjetos);
	}
	/**
	 * invocado cuando una accion ocurre sobre los elementos
	 * @param ae el evento a procesar
	 * @see actionPerformed
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		if (o == btnOrden)
		{
			ListaOrdenes lo = new ListaOrdenes();
			lo.setVisible(true);
			
			this.dispose();
		}
		else if (o == btnNuevaOrdenPrim)
		{
			CrearPrimaria cop = new CrearPrimaria();
			cop.setVisible(true);
			
			this.dispose();
		}
		else if (o == btnCerrarSesion)
		{
			Login l = new Login();
			l.setVisible(true);
			Log.logout();
			
			this.dispose();
		} 
		else if (o == btnCuentas)
		{
			AdministrarCuentas ac = new AdministrarCuentas();
			ac.setVisible(true);
			
			this.dispose();
		} 
		else if (o == btnMateriales)
		{
			AdministrarMateriales am = new AdministrarMateriales();
			am.setVisible(true);

			this.dispose();
		}
		else if (o == btnAjustes)
		{
			EditarAjustes ea = new EditarAjustes();

			ea.setVisible(true);

			this.dispose();
		}
	}
	/**
	 * invocado cuando el usuario intenta cerrar la ventana 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowClosing(WindowEvent e)
	{
		Salir.general(this);
	}
	/**
	 * Invocado la primera vez una ventana se ha hecho visible
	 * @param e el evento a procesar
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		// comportamiento por defecto
	}
	/**
	 * Invocado cuando una ventana se cerro como resultado llamando a dispose en la ventana
	 * @param e evento a procesar
	 */
	@Override
	public void windowClosed(WindowEvent e) {
		// comportamiento por defecto
	}
	/**
	 * Invocado cuando a una ventana se cambio de normal a minimizado por varias plataformas
	 * una minimizada ventana se procesa como el icono especificado en la propiedad de siconImage
	 * @param e el evento a procesar
	 */
	@Override
	public void windowIconified(WindowEvent e) {
		// comportamiento por defecto
	}
	/**
	 * cuando una ventana cambia de minimizado a ventana normal
	 * @param e el evento a procesar
	 */
	@Override
	public void windowDeiconified(WindowEvent e) {
		// comportamiento por defecto
	}
	/**
	 * Invocado cuando la ventana es capacitado a ser ventana activa 
	 * solo un frame o un dialog puede ser ventana activa 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		// comportamiento por defecto
	}
	/**
	 *  Invocado cuando una ventana no es langer la ventana activa
	 *  solo un Frame o un Dialog puede ser ventana activa
	 *  @param e el evento a procesar
	 */
	@Override
	public void windowDeactivated(WindowEvent e) {
		// comportamiento por defecto
	}
}