package edicion;

import java.awt.Color;
import java.awt.Dimension;
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

import clases.Material;
import clases.MaterialUsado;
import clases.Reparacion;
import funciones.Salir;
import funciones.Tablas;
import navegacion.Inicio;
import ordenes.MostrarOrden;
import javax.swing.SwingConstants;

public class EditarReparacion extends JFrame implements ActionListener, WindowListener, FocusListener {
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

	public EditarReparacion() {
		setResizable(false);
		setTitle("Agregar reparación | " + Inicio.empleadoActual.getNombre());

		setBounds(100, 100, 700, 470);
		getContentPane().setPreferredSize(new Dimension(700, 470));
		pack();

		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		cmbMaterial = new JComboBox<String>();
		cmbMaterial.addItem("Oscuro");
		cmbMaterial.addItem("Claro");
		cmbMaterial.setBounds(50, 130, 200, 35);
		panelPrincipal.add(cmbMaterial);

		btnAgregarMaterial = new JButton("Agregar");
		btnAgregarMaterial.setBounds(400, 130, 120, 35);
		panelPrincipal.add(btnAgregarMaterial);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(530, 130, 120, 35);
		panelPrincipal.add(btnEliminar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(357, 405, 180, 40);
		panelPrincipal.add(btnGuardar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(162, 405, 180, 40);
		panelPrincipal.add(btnCancelar);

		txtHoras = new JTextField();
		txtHoras.setBounds(455, 70, 50, 35);
		panelPrincipal.add(txtHoras);

		txtManoObra = new JTextField();
		txtManoObra.setBounds(305, 70, 50, 35);
		panelPrincipal.add(txtManoObra);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(305, 25, 200, 35);
		panelPrincipal.add(txtDescripcion);

		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescripcion.setBounds(195, 25, 110, 35);
		panelPrincipal.add(lblDescripcion);

		JLabel lblHoras = new JLabel("Horas:");
		lblHoras.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoras.setBounds(365, 70, 90, 35);
		panelPrincipal.add(lblHoras);

		JLabel lblManoObra = new JLabel("Mano de obra:");
		lblManoObra.setHorizontalAlignment(SwingConstants.LEFT);
		lblManoObra.setBounds(195, 70, 110, 35);
		panelPrincipal.add(lblManoObra);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(260, 130, 80, 35);
		panelPrincipal.add(lblCantidad);

		txtCantidad = new JTextField();
		txtCantidad.setBounds(340, 130, 50, 35);
		panelPrincipal.add(txtCantidad);
		txtCantidad.setColumns(10);

		// ==== Barras de desplazamiento ====
		JScrollPane scrollMateriales = new JScrollPane();
		scrollMateriales.setBounds(50, 180, 600, 200);
		panelPrincipal.add(scrollMateriales);

		// ====== Modelos ======
		// --- crear ---
		dcbmMaterial = new DefaultComboBoxModel<String>();
		// TODO: arreglar
		// dcbmMaterial.addAll(Datos.listarMateriales());

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
		tblMateriales.setRowHeight(20);
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
		tblMateriales.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting() && tblMateriales.getSelectedRow() >= 0) {
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

	public void modoEdicion(Reparacion r) {
		edicion = true;
		setTitle("Editar " + r.getDescripcion() + " | " + Inicio.empleadoActual.getNombre());

		txtDescripcion.setText(r.getDescripcion());
		txtDescripcion.setEnabled(false);

		// TODO: arreglar
		// txtHoras.setText(String.valueOf(r.getHoras()));
		// txtManoObra.setText(String.valueOf(r.getManoObra()));
		// alMaterialesUsados.addAll(r.getMaterialesUsados());

		actualizarTabla();
	}

	private void actualizarTabla() {
		alMaterialesUsados.sort(Comparator.naturalOrder());

		DefaultTableModel dtm = (DefaultTableModel) tblMateriales.getModel();
		dtm.setRowCount(0);

//		for (MaterialUsado m : alMaterialesUsados) {
			// TODO: arreglar
			// dtm.addRow(new Object[] { m.getNombre(),
			// General.formatearPrecio(m.getPrecio()), m.getCantidad() });
//		}

		Tablas.ajustarColumnas(tblMateriales);
	}

	private boolean agregar() {
		if (tblMateriales.getRowCount() > 0) {
			try {
				String descripcion = txtDescripcion.getText();
				int horas = Integer.parseInt(txtHoras.getText());
				double manoObra = Double.parseDouble(txtManoObra.getText());

				if (descripcion.equals("")) {
					JOptionPane.showMessageDialog(this, (String) "Campo de descripción vacío", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else if (horas < 1 || manoObra < 1) {
					JOptionPane.showMessageDialog(this, (String) "Campo numérico no válido", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else {
					ArrayList<Reparacion> al = MostrarOrden.getReparaciones();
					int posicion = 0;
					boolean existe = false;
					for (int i = 0; i < al.size(); i++) {
						if (al.get(i).getDescripcion().equals(descripcion)) {
							posicion = i;
							existe = true;
						}
					}

					if (existe) {
						if (edicion) {
							al.remove(posicion);
							// TODO: arreglar
							// al.add(new Reparacion(descripcion, horas, manoObra, new Fecha(),
							// Inicio.cuentaActual,
							// alMaterialesUsados));

							MostrarOrden.actualizarTablas();

							this.dispose();
						} else {
							JOptionPane.showMessageDialog(this, (String) "La reparación ya ha sido agregada", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						// TODO: arreglar
						// al.add(new Reparacion(descripcion, horas, manoObra, new Fecha(),
						// Inicio.cuentaActual,
						// alMaterialesUsados));

						return true;
					}
				}
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(this, (String) "Campo numérico vacío o incorrecto", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, (String) "No se ha agregado ningún material", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}

		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == btnEliminar) {
			int row = tblMateriales.getSelectedRow();
			if (row >= 0) {
				alMaterialesUsados.remove(row);
				actualizarTabla();
			} else {
				JOptionPane.showMessageDialog(this, (String) "No hay ningún material seleccionado", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (o == btnAgregarMaterial || o == txtCantidad) {
			try {
				int cantidad = Integer.parseInt(txtCantidad.getText());

				// TODO: arreglar
				// material = Datos.cargarMaterial((String) cmbMaterial.getSelectedItem());

				MaterialUsado mu = new MaterialUsado(material, cantidad);

				ArrayList<MaterialUsado> alMaterialesTmp = new ArrayList<MaterialUsado>();
				for (MaterialUsado m : alMaterialesUsados) {
					if (!m.getNombre().equals(mu.getNombre())) {
						alMaterialesTmp.add(m);
					}
				}

				alMaterialesUsados = new ArrayList<MaterialUsado>(alMaterialesTmp);

				alMaterialesUsados.add(mu);

				actualizarTabla();
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(this, (String) "Campo de cantidad vacío o incorrecto", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			int guardar = JOptionPane.YES_OPTION;

			if (o == btnCancelar) {
				guardar = Salir.edicion();
			}

			boolean valido = false;

			if (guardar == JOptionPane.YES_OPTION) {
				valido = agregar();
			}

			if (guardar == JOptionPane.NO_OPTION || valido) {
				MostrarOrden.actualizarTablas();
				this.dispose();
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
	public void windowClosing(WindowEvent e) {
		btnCancelar.doClick();
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