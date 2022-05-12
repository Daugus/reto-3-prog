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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import clases.Material;
import clases.Reparacion;
import funciones.Datos;
import funciones.Salir;
import navegacion.Inicio;
import ordenes.MostrarOrden;

/**
 * ventana de edición de reparación
 */
public class EditarReparacion extends JFrame implements ActionListener, WindowListener, FocusListener {
	private static final long serialVersionUID = 4959238857767892877L;

	private JPanel panelPrincipal;

	private JComboBox<String> cmbMaterial;
	private DefaultComboBoxModel<String> dcbmMaterial;

	private JButton btnGuardar;

	private JTextField txtCantidad;
	private JTextField txtPrecio;
	private JTextField txtHoras;

	private JButton btnCancelar;
	private JTextField txtDescripcion;

	private ArrayList<Material> alMateriales;

	private int posicion;

	private String codigo;
	private boolean edicion;

	/**
	 * carga los elementos de la ventana
	 */
	public EditarReparacion() {
		setResizable(false);
		setTitle("Agregar reparación | " + Inicio.empleadoActual.getNombre());

		setBounds(100, 100, 420, 240);
		getContentPane().setPreferredSize(new Dimension(420, 240));
		pack();

		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		cmbMaterial = new JComboBox<String>();
		cmbMaterial.addItem("Oscuro");
		cmbMaterial.addItem("Claro");
		cmbMaterial.setBounds(25, 115, 180, 35);
		panelPrincipal.add(cmbMaterial);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(217, 175, 180, 40);
		panelPrincipal.add(btnGuardar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(22, 175, 180, 40);
		panelPrincipal.add(btnCancelar);

		txtHoras = new JTextField();
		txtHoras.setBounds(325, 70, 70, 35);
		panelPrincipal.add(txtHoras);

		txtPrecio = new JTextField();
		txtPrecio.setBounds(135, 70, 70, 35);
		panelPrincipal.add(txtPrecio);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(135, 25, 260, 35);
		panelPrincipal.add(txtDescripcion);

		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescripcion.setBounds(25, 25, 110, 35);
		panelPrincipal.add(lblDescripcion);

		JLabel lblHoras = new JLabel("Horas:");
		lblHoras.setHorizontalAlignment(SwingConstants.LEFT);
		lblHoras.setBounds(215, 70, 110, 35);
		panelPrincipal.add(lblHoras);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecio.setBounds(25, 70, 110, 35);
		panelPrincipal.add(lblPrecio);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(215, 115, 110, 35);
		panelPrincipal.add(lblCantidad);

		txtCantidad = new JTextField();
		txtCantidad.setBounds(325, 115, 70, 35);
		panelPrincipal.add(txtCantidad);
		txtCantidad.setColumns(10);

		// ====== Modelos ======
		// --- crear ---
		dcbmMaterial = new DefaultComboBoxModel<String>();
		boolean todos = false;
		alMateriales = Datos.cargarMateriales(todos);
		for (Material m : alMateriales)
			dcbmMaterial.addElement(m.getNombre());

		// --- asignar ---
		cmbMaterial.setModel(dcbmMaterial);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		txtDescripcion.addActionListener(this);
		txtPrecio.addActionListener(this);
		txtCantidad.addActionListener(this);
		txtHoras.addActionListener(this);

		btnCancelar.addActionListener(this);
		btnGuardar.addActionListener(this);

		// --- Focus ---
		txtDescripcion.addFocusListener(this);
		txtPrecio.addFocusListener(this);
		txtCantidad.addFocusListener(this);
		txtHoras.addFocusListener(this);

		// ===== ajustes de usuario =====
		// --- fuente ---
		cmbMaterial.setFont(Inicio.fuenteObjetos);

		lblDescripcion.setFont(Inicio.fuente);
		lblPrecio.setFont(Inicio.fuente);

		lblHoras.setFont(Inicio.fuente);
		lblCantidad.setFont(Inicio.fuente);

		txtDescripcion.setFont(Inicio.fuenteObjetos);
		txtPrecio.setFont(Inicio.fuenteObjetos);
		txtHoras.setFont(Inicio.fuenteObjetos);
		txtCantidad.setFont(Inicio.fuenteObjetos);

		btnCancelar.setFont(Inicio.fuenteObjetos);
		btnGuardar.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		txtDescripcion.setBackground(Inicio.colorFondoObjetos);
		txtPrecio.setBackground(Inicio.colorFondoObjetos);
		txtCantidad.setBackground(Inicio.colorFondoObjetos);
		txtHoras.setBackground(Inicio.colorFondoObjetos);
		cmbMaterial.setBackground(Inicio.colorFondoObjetos);

		btnCancelar.setBackground(Inicio.colorFondoObjetos);
		btnGuardar.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		lblDescripcion.setForeground(Inicio.colorFuente);
		lblPrecio.setForeground(Inicio.colorFuente);

		lblHoras.setForeground(Inicio.colorFuente);
		lblCantidad.setForeground(Inicio.colorFuente);

		txtDescripcion.setForeground(Inicio.colorFuenteObjetos);
		txtDescripcion.setDisabledTextColor(Color.DARK_GRAY);
		txtPrecio.setForeground(Inicio.colorFuenteObjetos);
		txtCantidad.setForeground(Inicio.colorFuenteObjetos);
		txtHoras.setForeground(Inicio.colorFuenteObjetos);
		cmbMaterial.setForeground(Inicio.colorFuenteObjetos);

		btnCancelar.setForeground(Inicio.colorFuenteObjetos);
		btnGuardar.setForeground(Inicio.colorFuenteObjetos);

		// ===== valores por defecto =====
		txtDescripcion.setText("Descripción");
		txtPrecio.setText("20.00");
		txtHoras.setText("1");
		txtCantidad.setText("1");
	}

	/**
	 * escribe los datos de la reparación que está siendo editado en los campos
	 * 
	 * @param reparacion reparación que está siendo editado
	 * @param indice     posición de la reparación en el ArrayList de reparaciones
	 *                   de Finalizar Orden
	 */
	public void modoEdicion(Reparacion reparacion, int indice) {
		edicion = true;
		setTitle("Editar " + reparacion.getDescripcion() + " | " + Inicio.empleadoActual.getNombre());

		txtDescripcion.setText(reparacion.getDescripcion());
		txtDescripcion.setEnabled(false);

		txtPrecio.setText(String.valueOf(reparacion.getPrecio()));
		cmbMaterial.setSelectedIndex(alMateriales.indexOf(new Material(reparacion.getIdMaterial())));
		txtHoras.setText(String.valueOf(reparacion.getHoras()));
		txtCantidad.setText(String.valueOf(reparacion.getCantidadMaterial()));

		codigo = reparacion.getCodigo();
		posicion = indice;
	}

	private boolean agregar() {
		try {
			String descripcion = txtDescripcion.getText();
			double precio = Double.parseDouble(txtPrecio.getText());
			String material = alMateriales.get(cmbMaterial.getSelectedIndex()).getID();
			int cantidad = Integer.parseInt(txtCantidad.getText());
			int horas = Integer.parseInt(txtHoras.getText());

			if (descripcion.equals("")) {
				JOptionPane.showMessageDialog(this, (String) "Campo de descripción vacío", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else if (precio < 1) {
				JOptionPane.showMessageDialog(this, (String) "Precio no válido", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else if (cantidad < 1) {
				JOptionPane.showMessageDialog(this, (String) "Cantidad no válida", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else if (horas < 1) {
				JOptionPane.showMessageDialog(this, (String) "Cantidad de horas no válida", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else {
				ArrayList<Reparacion> alReparaciones = MostrarOrden.getReparaciones();

				if (edicion) {
					alReparaciones.remove(posicion);
				} else {
					if (alReparaciones.size() == 0) {
						codigo = Datos.generarCodigoReparacion("");
					} else {
						codigo = Datos
								.generarCodigoReparacion(alReparaciones.get(alReparaciones.size() - 1).getCodigo());
					}
				}

				alReparaciones.add(new Reparacion(codigo, descripcion, precio, horas, material, cantidad, true));

				return true;
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, (String) "Campo numérico vacío o incorrecto", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}

		return false;
	}

	/**
	 * invocado cuando ocurren una acción
	 * 
	 * @param ae el evento de acción
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object o = ae.getSource();
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

	/**
	 * invocado cuando se enfoca un campo de texto
	 * 
	 * @param fg el evento de enfoque
	 */
	public void focusGained(FocusEvent fg) {
		JTextComponent txt = (JTextComponent) fg.getSource();
		txt.select(0, txt.getText().length());
	}

	/**
	 * invocado cuando se deja de enfocar un campo de texto
	 * 
	 * @param fg el evento de enfoque
	 */
	@Override
	public void focusLost(FocusEvent fl) {
		JTextComponent txt = (JTextComponent) fl.getSource();
		txt.select(0, 0);
	}

	/**
	 * invocado cuando se cierra la ventana
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowClosing(WindowEvent we) {
		btnCancelar.doClick();
	}

	/**
	 * invocado cuando se abre la ventana
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowOpened(WindowEvent we) {
		// comportamiento por defecto
	}

	/**
	 * invocado después de que se cierre la ventana
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowClosed(WindowEvent we) {
		// comportamiento por defecto
	}

	/**
	 * invocado cuando se minimiza la ventana
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowIconified(WindowEvent we) {
		// comportamiento por defecto
	}

	/**
	 * invocado cuando se maximiza la ventana
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowDeiconified(WindowEvent we) {
		// comportamiento por defecto
	}

	/**
	 * invocado cuando la ventana se convierte en la ventana activa
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowActivated(WindowEvent we) {
		// comportamiento por defecto
	}

	/**
	 * invocado cuando la ventana deja de ser la ventana activa
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowDeactivated(WindowEvent we) {
		// comportamiento por defecto
	}
}