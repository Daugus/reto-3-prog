package administracion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import clases.Cuenta;
import edicion.EditarCuenta;
import funciones.Archivos;
import funciones.Salir;
import navegacion.Inicio;
import navegacion.MenuAtc;

/**
 * 
 * @author Grupo 2
 *
 */
public class AdministrarCuentas extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;
	
	public static DefaultListModel<Cuenta> dlmCuentas;
	private JList<Cuenta> lstCuentas;

	private JButton btnVolver;
	private JButton btnAgregar;
	private JButton btnEditar;
	
	private Cuenta cuenta;

	public AdministrarCuentas()
	{
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Administrar cuentas");
		
		setBounds(100, 100, 750, 553);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Inicio.colorFondo);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		lstCuentas = new JList<Cuenta>();
		lstCuentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 445, 174, 58);
		panelPrincipal.add(btnVolver);
		
		btnAgregar = new JButton("Agregar cuenta");
		btnAgregar.setBounds(62, 60, 269, 62);
		panelPrincipal.add(btnAgregar);
		
		btnEditar = new JButton("Editar cuentas");
		btnEditar.setBounds(380, 60, 269, 62);
		panelPrincipal.add(btnEditar);

		// ===== barras de desplazamiento =====
		JScrollPane scrollCuentas = new JScrollPane();
		scrollCuentas.setBounds(62, 159, 584, 203);
		panelPrincipal.add(scrollCuentas);

		scrollCuentas.setViewportView(lstCuentas);
		
		// ===== modelos =====
		// --- crear ---
		dlmCuentas = new DefaultListModel<Cuenta>();
		dlmCuentas.addAll(Archivos.cargarTodosCuentas());

		// --- asignar ---
		lstCuentas.setModel(dlmCuentas);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		btnVolver.addActionListener(this);
		btnAgregar.addActionListener(this);
		btnEditar.addActionListener(this);
		
		// ===== ajustes de usuario =====
		// --- fuente ---
		lstCuentas.setFont(Inicio.fuenteObjetos);

		btnVolver.setFont(Inicio.fuenteObjetos);
		btnAgregar.setFont(Inicio.fuenteObjetos);
		btnEditar.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		lstCuentas.setBackground(Inicio.colorFondoObjetos);

		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnAgregar.setBackground(Inicio.colorFondoObjetos);
		btnEditar.setBackground(Inicio.colorFondoObjetos);

		scrollCuentas.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		lstCuentas.setForeground(Inicio.colorFuenteObjetos);

		btnVolver.setForeground(Inicio.colorFuenteObjetos);
		btnAgregar.setForeground(Inicio.colorFuenteObjetos);
		btnEditar.setForeground(Inicio.colorFuenteObjetos);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		
		if (o == btnAgregar)
		{
			EditarCuenta ec = new EditarCuenta();
			ec.setLocationRelativeTo(null);
			ec.setVisible(true);
		}
		else if (o == btnEditar)
		{
			if (lstCuentas.getSelectedIndex() >= 0)
			{
				cuenta = lstCuentas.getSelectedValue();

				EditarCuenta ec = new EditarCuenta();
				ec.modoEdicion(cuenta);
			
				ec.setLocationRelativeTo(null);
				ec.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(this, (String) "No hay ninguna cuenta seleccionada", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (o == btnVolver)
		{
			MenuAtc ma = new MenuAtc();
			ma.setLocationRelativeTo(null);
			ma.setVisible(true);
			
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