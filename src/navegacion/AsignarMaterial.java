package navegacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.JTextComponent;

import clases.Material;
import clases.MaterialUsado;
import funciones.Archivos;
import funciones.Tablas;

public class AsignarMaterial extends JFrame implements ActionListener, WindowListener, FocusListener
{
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	
	private JComboBox<String> cmbMaterial;
	private DefaultComboBoxModel<String> dcbmMaterial;

	private JTable tblMateriales;
	private ArrayList<MaterialUsado> alMaterialesUsados = new ArrayList<MaterialUsado>();

	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnAceptar;

	private JTextField txtCantidad;

	private Material material;

	public AsignarMaterial()
	{
		setTitle("Materiales");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 300);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Inicio.colorFondo);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		cmbMaterial = new JComboBox<String>();
		cmbMaterial.addItem("Oscuro");
		cmbMaterial.addItem("Claro");
		cmbMaterial.setBounds(38, 26, 184, 33);
		panelPrincipal.add(cmbMaterial);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(428, 26, 98, 33);
		panelPrincipal.add(btnAgregar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(552, 26, 98, 33);
		panelPrincipal.add(btnEliminar);

		btnAceptar = new JButton("Validar");
		btnAceptar.setBounds(275, 208, 138, 42);
		panelPrincipal.add(btnAceptar);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(252, 26, 69, 33);
		panelPrincipal.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(329, 26, 83, 33);
		panelPrincipal.add(txtCantidad);
		txtCantidad.setColumns(10);

		// ==== Barras de desplazamiento ====
		JScrollPane scrollMateriales = new JScrollPane();
		scrollMateriales.setBounds(38, 69, 613, 113);
		panelPrincipal.add(scrollMateriales);

		// ====== Modelos ======
		// --- crear ---
		dcbmMaterial = new DefaultComboBoxModel<String>();
		dcbmMaterial.addAll(Archivos.listarMateriales());

		DefaultTableModel dtmMaterial = new DefaultTableModel();
		dtmMaterial.addColumn("Nombre");
		dtmMaterial.addColumn("Precio");
		dtmMaterial.addColumn("Cantidad");
		
		// --- asignar ---
		cmbMaterial.setModel(dcbmMaterial);

		tblMateriales = new JTable(dtmMaterial) {
			private static final long serialVersionUID = -3909141556237115067L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblMateriales.setFillsViewportHeight(true);
		tblMateriales.getTableHeader().setReorderingAllowed(false);
		tblMateriales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollMateriales.setViewportView(tblMateriales);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		txtCantidad.addActionListener(this);

		btnEliminar.addActionListener(this);
		btnAgregar.addActionListener(this);
		btnAceptar.addActionListener(this);
		
		// --- ListSelection ---
		tblMateriales.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
	        public void valueChanged(ListSelectionEvent lse)
	        {
	        	if (!lse.getValueIsAdjusting() && tblMateriales.getSelectedRow() >= 0)
	        	{
	        		int row = tblMateriales.getSelectedRow();
	        		cmbMaterial.setSelectedItem(tblMateriales.getValueAt(row, 0));
	        		txtCantidad.setText(tblMateriales.getValueAt(row, 2).toString());
	        	}
	        }
	    });

		// ===== ajustes de usuario =====
		// --- fuente ---
		cmbMaterial.setFont(Inicio.fuenteObjetos);

		lblCantidad.setFont(Inicio.fuente);
		txtCantidad.setFont(Inicio.fuenteObjetos);
		
		btnEliminar.setFont(Inicio.fuenteObjetos);
		btnAgregar.setFont(Inicio.fuenteObjetos);
		btnAceptar.setFont(Inicio.fuenteObjetos);

		tblMateriales.getTableHeader().setFont(Inicio.fuenteObjetos);
		tblMateriales.setFont(Inicio.fuente);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		txtCantidad.setBackground(Inicio.colorFondoObjetos);
		cmbMaterial.setBackground(Inicio.colorFondoObjetos);

		btnEliminar.setBackground(Inicio.colorFondoObjetos);
		btnAgregar.setBackground(Inicio.colorFondoObjetos);
		btnAceptar.setBackground(Inicio.colorFondoObjetos);

		tblMateriales.getTableHeader().setBackground(Inicio.colorFondoObjetos);
		tblMateriales.setBackground(Inicio.colorFondoObjetos);

		scrollMateriales.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		lblCantidad.setForeground(Inicio.colorFuente);

		txtCantidad.setForeground(Inicio.colorFuenteObjetos);
		cmbMaterial.setForeground(Inicio.colorFuenteObjetos);

		btnEliminar.setForeground(Inicio.colorFuenteObjetos);
		btnAgregar.setForeground(Inicio.colorFuenteObjetos);
		btnAceptar.setForeground(Inicio.colorFuenteObjetos);

		tblMateriales.setForeground(Inicio.colorFuenteObjetos);
	}
	
	public void modoEdicion(TableModel tm)
	{
		for (int i = 0; i < tm.getRowCount(); i++)
		{
			MaterialUsado mu = new MaterialUsado((String) tm.getValueAt(i, 0),
					(Double) tm.getValueAt(i, 1),
					(Integer) tm.getValueAt(i, 2));

			alMaterialesUsados.add(mu);
		}
		
		actualizarTabla();
	}
	
	private void actualizarTabla()
	{
		alMaterialesUsados.sort(Comparator.naturalOrder());
		
		DefaultTableModel dtm = (DefaultTableModel) tblMateriales.getModel();
		dtm.setRowCount(0);

		for (MaterialUsado m : alMaterialesUsados)
		{
			dtm.addRow(new Object[] {m.getNombre(), m.getPrecio(), m.getCantidad()});
		}
		
		Tablas.ajustarColumnas(tblMateriales);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == btnEliminar)
		{
			int row = tblMateriales.getSelectedRow();
			if (row >= 0)
			{
				alMaterialesUsados.remove(row);
				actualizarTabla();
			}
			else
			{
				JOptionPane.showMessageDialog(this, (String) "No hay ningún material seleccionado", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (o == btnAceptar)
		{
			CrearOrdenPend.setTablaMateriales(tblMateriales.getModel());

			this.dispose();
		}
		else
		{
			try
			{
				int cantidad = Integer.parseInt(txtCantidad.getText());
				
				material = Archivos.cargarMaterial((String) cmbMaterial.getSelectedItem());

				MaterialUsado mu = new MaterialUsado(material, cantidad);

				ArrayList<MaterialUsado> alMaterialesTmp = new ArrayList<MaterialUsado>();
				for (MaterialUsado m : alMaterialesUsados)
				{
					if (!m.getNombre().equals(mu.getNombre()))
					{
						alMaterialesTmp.add(m);
					}
				}
				
				if (alMaterialesTmp.size() > 0)
				{
					alMaterialesUsados = new ArrayList<MaterialUsado>(alMaterialesTmp);
				}
				
				alMaterialesUsados.add(mu);
				
				actualizarTabla();
			}
			catch (NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(this, (String) "Campo de cantidad vacío o incorrecto", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void focusGained(FocusEvent fg) {
		JTextComponent txt = (JTextComponent) fg.getSource();
		txt.select(0, txt.getText().length());
	}

	@Override
	public void focusLost(FocusEvent fl) {
		JTextComponent txt = (JTextComponent) fl.getSource();
		txt.select(0, 0);
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		this.dispose();
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