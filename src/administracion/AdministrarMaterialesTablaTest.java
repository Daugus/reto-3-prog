package administracion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.events.MouseEvent;

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
public class AdministrarMaterialesTablaTest extends JFrame implements ActionListener, WindowListener, MouseListener
{
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministrarMaterialesTablaTest frame = new AdministrarMaterialesTablaTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;
	
	private JButton btnVolver;
	private JButton btnEditar;
	private JButton btnAgregar;

	private DefaultTableModel dtmMateriales;
	private JTable tblMateriales;

	private Material material;

	public AdministrarMaterialesTablaTest()
	{
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Administrar materiales tabla");
		
		setBounds(100, 100, 750, 553);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Inicio.colorFondo);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

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
		

		
		// ===== modelos =====
		// --- crear ---
		dtmMateriales = new DefaultTableModel();
		dtmMateriales.addColumn("Nombre");
		dtmMateriales.addColumn("Precio");

		// --- asignar ---
		tblMateriales = new JTable(dtmMateriales)
		{
			private static final long serialVersionUID = -1689014003054751445L;

			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		tblMateriales.setFillsViewportHeight(true);
		tblMateriales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// ===== Listeners =====
		// --- Window ---
		addWindowListener(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// --- Action ---
		btnVolver.addActionListener(this);
		btnAgregar.addActionListener(this);
		btnEditar.addActionListener(this);

		DefaultTableModel dtm = (DefaultTableModel) tblMateriales.getModel();
		Material m = Archivos.cargarMaterial("test");
		dtm.addRow(new Object[] {m.getNombre(), m.getPrecio()});

		scrollMateriales.setViewportView(tblMateriales);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		
		if (o == btnAgregar)
		{
//			EditarMaterial em = new EditarMaterial();
//			em.setLocationRelativeTo(null);
//			em.setVisible(true);
			int columna = tblMateriales.getSelectedRow();
			if (columna >= 0)
			{
				System.out.println(tblMateriales.getValueAt(0, columna));
			}
		}
		else if (o == btnEditar)
		{
				EditarMaterial em = new EditarMaterial();
				em.modoEdicion(material);
			
				em.setLocationRelativeTo(null);
				em.setVisible(true);
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