package administracion;

import java.awt.Color;
import java.awt.Font;
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

import clases.Material;
import edicion.EditarMaterial;
import funciones.Archivos;
import funciones.Salir;
import navegacion.Inicio;
import navegacion.MenuAtc;

/**
 * 
 * @author Grupo 2
 *
 */
public class AdministrarMateriales extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;
	
	public static DefaultListModel<Material> dlmMateriales;
	private JList<Material> lstMateriales;

	private JButton btnVolver;
	private JButton btnEditar;
	private JButton btnAgregar;

	private Material material;

	public AdministrarMateriales()
	{
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Administrar materiales");
		
		setBounds(100, 100, 750, 553);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Inicio.colorFondo);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		lstMateriales = new JList<Material>();
		lstMateriales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font(Inicio.fuente, Font.PLAIN, 14));
		btnVolver.setBounds(10, 445, 174, 58);
		panelPrincipal.add(btnVolver);
		
		btnAgregar = new JButton("Agregar material");
		btnAgregar.setFont(new Font(Inicio.fuente, Font.PLAIN, 17));
		btnAgregar.setBounds(62, 60, 269, 62);
		panelPrincipal.add(btnAgregar);
		
		btnEditar = new JButton("Editar material");
		btnEditar.setFont(new Font(Inicio.fuente, Font.PLAIN, 17));
		btnEditar.setBounds(380, 60, 269, 62);
		panelPrincipal.add(btnEditar);

		// ===== barras de desplazamiento =====
		JScrollPane scrollMateriales = new JScrollPane();
		scrollMateriales.setBounds(62, 159, 584, 203);
		panelPrincipal.add(scrollMateriales);

		scrollMateriales.setViewportView(lstMateriales);
		
		// ===== modelos =====
		// --- crear ---
		dlmMateriales = new DefaultListModel<Material>();
		dlmMateriales.addAll(Archivos.cargarTodosMateriales());

		// --- asignar ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		lstMateriales.setModel(dlmMateriales);

		// ===== Listeners =====
		// --- Window ---
		addWindowListener(this);

		// --- Action ---
		btnVolver.addActionListener(this);
		btnAgregar.addActionListener(this);
		btnEditar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		
		if (o == btnAgregar)
		{
			EditarMaterial em = new EditarMaterial();
			em.setLocationRelativeTo(null);
			em.setVisible(true);
		}
		else if (o == btnEditar)
		{
			if (lstMateriales.getSelectedIndex() >= 0)
			{
				material = lstMateriales.getSelectedValue();

				EditarMaterial em = new EditarMaterial();
				em.modoEdicion(material);
			
				em.setLocationRelativeTo(null);
				em.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(this, (String) "No hay ningún material seleccionado", "ERROR",
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