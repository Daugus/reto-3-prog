package navegacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.OrdenPrim;
import funciones.Archivos;
import funciones.Salir;

/**
 * 
 * @author Grupo 2
 *
 */

public class ListaOrdenesPrim extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = 1531539371445418371L;
	/**
	 * JComboBox declarados public porque estan llamados, asignados valor desde otras
	 * clases
	 */
	private JPanel panelPrincipal;

	private JComboBox<String> cmbPrimarias;

	private JButton btnVolver;
	private JButton btnAcceder;

	private static OrdenPrim ordenPrim;

	public ListaOrdenesPrim()
	{
		setResizable(false);
		setTitle("Lista de Órdenes Primarias");
		setBounds(100, 100, 750, 429);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Inicio.colorFondo);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		JLabel lblTitulo = new JLabel("Listado de Órdenes Primarias");
		lblTitulo.setForeground(Inicio.colorFuente);
		lblTitulo.setFont(new Font(Inicio.fuente, Font.BOLD, 21));
		lblTitulo.setBounds(27, 22, 354, 26);
		panelPrincipal.add(lblTitulo);
		
		cmbPrimarias = new JComboBox<String>();
		cmbPrimarias.setBounds(214, 137, 300, 40);
		panelPrincipal.add(cmbPrimarias);
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font(Inicio.fuente, Font.PLAIN, 14));
		btnVolver.setBounds(197, 271, 137, 46);
		panelPrincipal.add(btnVolver);
		
		btnAcceder = new JButton("Acceder");
		btnAcceder.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		btnAcceder.setBounds(399, 271, 137, 46);
		panelPrincipal.add(btnAcceder);

		// ===== modelos =====
		// --- crear ---
		DefaultComboBoxModel<String> dcbmPrimarias = new DefaultComboBoxModel<String>();
		ArrayList<String> listaPrimarias = Archivos.listarOrdenPrim();
		listaPrimarias.sort(Comparator.reverseOrder());
		dcbmPrimarias.addAll(listaPrimarias);
		
		// --- asignar ---
		cmbPrimarias.setModel(dcbmPrimarias);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		// - JButton -
		btnAcceder.addActionListener(this);
		btnVolver.addActionListener(this);
	}
	
	public static OrdenPrim getOrdenPrim()
	{
		return ordenPrim;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();

		if (o == btnAcceder)
		{
			String codOrdenPrim = (String) cmbPrimarias.getSelectedItem();
			
			try
			{
				ordenPrim = Archivos.cargarOrdenPrim(codOrdenPrim);

				CrearOrdenPend cop = new CrearOrdenPend();
				cop.cargarDatos();

				cop.setVisible(true);
				cop.setLocationRelativeTo(null);
			}
			catch (NullPointerException npe)
			{
				JOptionPane.showMessageDialog (null, "La Orden Primaria introducida no existe", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			// btnVolver
			if (Inicio.cuentaActual.getMecanico())
			{
				MenuMec mm = new MenuMec();
				mm.setLocationRelativeTo(null);
				mm.setVisible(true);
			}
			else {
				ListaOrdenes lo = new ListaOrdenes();
				lo.setLocationRelativeTo(null);
				lo.setVisible(true);
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