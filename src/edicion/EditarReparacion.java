package edicion;

import java.awt.Color;
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
import javax.swing.text.JTextComponent;

import clases.Fecha;
import clases.Material;
import clases.MaterialUsado;
import clases.Reparacion;
import funciones.Archivos;
import funciones.Salir;
import funciones.Tablas;
import navegacion.CrearOrdenPend;
import navegacion.Inicio;

public class EditarReparacion extends JFrame implements ActionListener, WindowListener, FocusListener
{
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	
	private JComboBox<String> cmbMaterial;
	private DefaultComboBoxModel<String> dcbmMaterial;

	private JTable tblMateriales;
	private ArrayList<MaterialUsado> alMaterialesUsados = new ArrayList<MaterialUsado>();

	private JButton btnAgregarMaterial;
	private JButton btnEliminar;
	private JButton btnGuardar;

	private JTextField txtCantidad;
	private JTextField txtHoras;
	private JTextField txtManoObra;

	private Material material;
	private JButton btnCancelar;
	private JTextField txtDescripcion;
	
	private boolean edicion;

	public EditarReparacion()
	{
		setResizable(false);
		setTitle("Agregar reparación");

		setBounds(100, 100, 708, 524);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		cmbMaterial = new JComboBox<String>();
		cmbMaterial.addItem("Oscuro");
		cmbMaterial.addItem("Claro");
		cmbMaterial.setBounds(33, 216, 184, 33);
		panelPrincipal.add(cmbMaterial);

		btnAgregarMaterial = new JButton("Agregar");
		btnAgregarMaterial.setBounds(423, 216, 98, 33);
		panelPrincipal.add(btnAgregarMaterial);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(547, 216, 98, 33);
		panelPrincipal.add(btnEliminar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(270, 398, 138, 42);
		panelPrincipal.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(89, 398, 138, 42);
		panelPrincipal.add(btnCancelar);
		
		txtHoras = new JTextField();
		txtHoras.setBounds(253, 93, 50, 30);
		panelPrincipal.add(txtHoras);
		
		txtManoObra = new JTextField();
		txtManoObra.setBounds(253, 134, 50, 30);
		panelPrincipal.add(txtManoObra);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(253, 49, 200, 30);
		panelPrincipal.add(txtDescripcion);
		
		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setBounds(143, 48, 100, 33);
		panelPrincipal.add(lblDescripcion);
		
		JLabel lblHoras = new JLabel("Horas");
		lblHoras.setBounds(143, 92, 100, 33);
		panelPrincipal.add(lblHoras);
		
		JLabel lblManoObra = new JLabel("Mano de obra");
		lblManoObra.setBounds(143, 133, 100, 33);
		panelPrincipal.add(lblManoObra);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(247, 216, 69, 33);
		panelPrincipal.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(324, 216, 83, 33);
		panelPrincipal.add(txtCantidad);
		txtCantidad.setColumns(10);

		// ==== Barras de desplazamiento ====
		JScrollPane scrollMateriales = new JScrollPane();
		scrollMateriales.setBounds(33, 259, 613, 113);
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
		txtDescripcion.addActionListener(this);
		txtHoras.addActionListener(this);
		txtManoObra.addActionListener(this);
		txtCantidad.addActionListener(this);

		btnEliminar.addActionListener(this);
		btnAgregarMaterial.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnGuardar.addActionListener(this);
		
		// --- Focus ---
		txtDescripcion.addFocusListener(this);
		txtHoras.addFocusListener(this);
		txtManoObra.addFocusListener(this);
		txtCantidad.addFocusListener(this);
		
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

		lblDescripcion.setFont(Inicio.fuente);
		lblHoras.setFont(Inicio.fuente);
		lblManoObra.setFont(Inicio.fuente);

		lblCantidad.setFont(Inicio.fuente);

		txtDescripcion.setFont(Inicio.fuenteObjetos);
		txtHoras.setFont(Inicio.fuenteObjetos);
		txtManoObra.setFont(Inicio.fuenteObjetos);

		txtCantidad.setFont(Inicio.fuenteObjetos);
		
		btnEliminar.setFont(Inicio.fuenteObjetos);
		btnAgregarMaterial.setFont(Inicio.fuenteObjetos);
		btnCancelar.setFont(Inicio.fuenteObjetos);
		btnGuardar.setFont(Inicio.fuenteObjetos);

		tblMateriales.getTableHeader().setFont(Inicio.fuenteObjetos);
		tblMateriales.setFont(Inicio.fuente);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		txtDescripcion.setBackground(Inicio.colorFondoObjetos);
		txtHoras.setBackground(Inicio.colorFondoObjetos);
		txtManoObra.setBackground(Inicio.colorFondoObjetos);
		txtCantidad.setBackground(Inicio.colorFondoObjetos);
		cmbMaterial.setBackground(Inicio.colorFondoObjetos);

		btnEliminar.setBackground(Inicio.colorFondoObjetos);
		btnAgregarMaterial.setBackground(Inicio.colorFondoObjetos);
		btnCancelar.setBackground(Inicio.colorFondoObjetos);
		btnGuardar.setBackground(Inicio.colorFondoObjetos);

		tblMateriales.getTableHeader().setBackground(Inicio.colorFondoObjetos);
		tblMateriales.setBackground(Inicio.colorFondoObjetos);

		scrollMateriales.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		lblDescripcion.setForeground(Inicio.colorFuente);
		lblHoras.setForeground(Inicio.colorFuente);
		lblManoObra.setForeground(Inicio.colorFuente);

		lblCantidad.setForeground(Inicio.colorFuente);

		txtDescripcion.setForeground(Inicio.colorFuenteObjetos);
		txtDescripcion.setDisabledTextColor(Color.DARK_GRAY);
		txtHoras.setForeground(Inicio.colorFuenteObjetos);
		txtManoObra.setForeground(Inicio.colorFuenteObjetos);
		txtCantidad.setForeground(Inicio.colorFuenteObjetos);
		cmbMaterial.setForeground(Inicio.colorFuenteObjetos);

		btnEliminar.setForeground(Inicio.colorFuenteObjetos);
		btnAgregarMaterial.setForeground(Inicio.colorFuenteObjetos);
		btnCancelar.setForeground(Inicio.colorFuenteObjetos);
		btnGuardar.setForeground(Inicio.colorFuenteObjetos);

		tblMateriales.setForeground(Inicio.colorFuenteObjetos);
	}
	
	public void modoEdicion(Reparacion r)
	{
		edicion = true;
		setTitle("Editar " + r.getDescripcion());
		
		txtDescripcion.setText(r.getDescripcion());
		txtDescripcion.setEnabled(false);

		txtHoras.setText(String.valueOf(r.getHoras()));
		txtManoObra.setText(String.valueOf(r.getManoObra()));
		alMaterialesUsados.addAll(r.getMaterialesUsados());

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
		else if (o == btnAgregarMaterial || o == txtCantidad)
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
		else if (o == btnCancelar)
		{
//			Salir.siNo();
			this.dispose();
		}
		else
		{
			if (tblMateriales.getRowCount() > 0)
			{
				try
				{
					String descripcion = txtDescripcion.getText();
					int horas = Integer.parseInt(txtHoras.getText());
					double manoObra = Double.parseDouble(txtManoObra.getText());
					
					ArrayList<Reparacion> al = CrearOrdenPend.getReparaciones();
					if (!edicion)
					{
						int posicion = 0;
						boolean borrar = false;
						for (int i = 0; i < al.size(); i++)
						{
							System.out.println(al.get(i).getDescripcion().equals(descripcion));
							if (al.get(i).getDescripcion().equals(descripcion))
							{
								posicion = i;
								borrar = true;
							}
						}
						
						if (borrar)
						{
							al.remove(posicion);
						}
					}
					
					al.add(new Reparacion(descripcion, horas, manoObra,
							new Fecha(), Inicio.cuentaActual, alMaterialesUsados));
					
					CrearOrdenPend.actualizarTablas();
					
					this.dispose();
				}
				catch (NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(this, (String) "Campo numérico vacío o incorrecto", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, (String) "No se ha agregado ningún material", "ERROR",
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