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

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(357, 405, 180, 40);
		panelPrincipal.add(btnGuardar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(162, 405, 180, 40);
		panelPrincipal.add(btnCancelar);

		txtHoras = new JTextField();
		txtHoras.setBounds(455, 70, 50, 35);
		panelPrincipal.add(txtHoras);

		txtPrecio = new JTextField();
		txtPrecio.setBounds(305, 70, 50, 35);
		panelPrincipal.add(txtPrecio);

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

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecio.setBounds(195, 70, 110, 35);
		panelPrincipal.add(lblPrecio);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(260, 130, 80, 35);
		panelPrincipal.add(lblCantidad);

		txtCantidad = new JTextField();
		txtCantidad.setBounds(340, 130, 50, 35);
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
	}

	public void modoEdicion(Reparacion r, int indice) {
		edicion = true;
		setTitle("Editar " + r.getDescripcion() + " | " + Inicio.empleadoActual.getNombre());

		txtDescripcion.setText(r.getDescripcion());
		txtDescripcion.setEnabled(false);

		txtPrecio.setText(String.valueOf(r.getPrecio()));
		cmbMaterial.setSelectedIndex(alMateriales.indexOf(new Material(r.getIdMaterial())));
		txtHoras.setText(String.valueOf(r.getHoras()));
		txtCantidad.setText(String.valueOf(r.getCantidadMaterial()));

		codigo = r.getCodigo();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
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