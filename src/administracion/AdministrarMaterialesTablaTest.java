package administracion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.Material;
import funciones.Archivos;
import navegacion.Inicio;

/**
 * 
 * @author Grupo 2
 *
 */
public class AdministrarMaterialesTablaTest extends JFrame implements ActionListener
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

	public AdministrarMaterialesTablaTest()
	{
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Administrar materiales tabla");
		
		setBounds(100, 100, 750, 553);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.LIGHT_GRAY);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		btnVolver = new JButton("Volver");
		btnVolver.setBackground(Color.WHITE);
		btnVolver.setBounds(10, 445, 174, 58);
		panelPrincipal.add(btnVolver);
		
		btnAgregar = new JButton("Agregar material");
		btnAgregar.setBackground(Color.WHITE);
		btnAgregar.setBounds(62, 60, 269, 62);
		panelPrincipal.add(btnAgregar);
		
		btnEditar = new JButton("Editar material");
		btnEditar.setBackground(Color.WHITE);
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
		tblMateriales.getTableHeader().setReorderingAllowed(false);
		tblMateriales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblMateriales.getTableHeader().setBackground(Inicio.colorFondoObjetos);
		tblMateriales.getTableHeader().setFont(Inicio.fuenteObjetos);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// --- Action ---
		btnVolver.addActionListener(this);
		btnAgregar.addActionListener(this);
		btnEditar.addActionListener(this);

		DefaultTableModel dtm = (DefaultTableModel) tblMateriales.getModel();
		Material m = Archivos.cargarMaterial("test");
		dtm.addRow(new Object[] {m.getNombre(), m.getPrecio()});
		m = new Material(Archivos.cargarMaterial("atest"));
		dtm.addRow(new Object[] {m.getNombre(), m.getPrecio()});
		m = new Material(Archivos.cargarMaterial("btest"));
		for (int i = 0; i <= 20; i++)
		{
			dtm.addRow(new Object[] {m.getNombre(), m.getPrecio()});
		}

		scrollMateriales.setViewportView(tblMateriales);
		
		// ===== ajustes de usuario =====
		// --- fuente ---
		tblMateriales.setFont(Inicio.fuente);

		btnVolver.setFont(Inicio.fuenteObjetos);
		btnAgregar.setFont(Inicio.fuenteObjetos);
		btnEditar.setFont(Inicio.fuenteObjetos);
		
		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		tblMateriales.setBackground(Inicio.colorFondoObjetos);

		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnAgregar.setBackground(Inicio.colorFondoObjetos);
		btnEditar.setBackground(Inicio.colorFondoObjetos);
		
		scrollMateriales.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		tblMateriales.setForeground(Inicio.colorFuenteObjetos);

		btnVolver.setForeground(Inicio.colorFuenteObjetos);
		btnAgregar.setForeground(Inicio.colorFuenteObjetos);
		btnEditar.setForeground(Inicio.colorFuenteObjetos);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		int row = tblMateriales.getSelectedRow();
		if (row >= 0)
		{
			System.out.println(tblMateriales.getValueAt(row, 0));
		}
	}
}