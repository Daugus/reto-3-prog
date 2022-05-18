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
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import administracion.AdministrarVehiculos;
import clases.Fecha;
import clases.Vehiculo;
import funciones.Datos;
import funciones.Salir;
import navegacion.Inicio;

/**
 * ventana de edición de vehículo
 */
public class EditarVehiculo extends JFrame implements ActionListener, FocusListener, WindowListener {
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private DefaultComboBoxModel<String> dcbmClientes;
	private JComboBox<String> cmbClientes;

	private JComboBox<String> cmbTipo;

	private JTextField txtMatricula;
	private JTextField txtBastidor;

	private JTextField txtMarca;
	private JTextField txtModelo;

	private JTextField txtFechaFabricacionD;
	private JTextField txtFechaFabricacionM;
	private JTextField txtFechaFabricacionA;

	private JCheckBox chkActivo;

	private JButton btnCancelar;
	private JButton btnGuardar;

	private ArrayList<String> alMatriculas = new ArrayList<String>();

	private boolean edicion;

	/**
	 * carga los elementos de la ventana
	 */
	public EditarVehiculo() {
		setResizable(false);
		setTitle("Agregar nuevo vehículo | " + Inicio.empleadoActual.getNombre());
		setIconImage(new ImageIcon(getClass().getResource("/icono.png")).getImage());

		setBounds(100, 100, 630, 285);
		getContentPane().setPreferredSize(new Dimension(630, 285));
		pack();

		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(127, 220, 180, 40);
		panelPrincipal.add(btnCancelar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(323, 220, 180, 40);
		panelPrincipal.add(btnGuardar);

		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setHorizontalAlignment(SwingConstants.LEFT);
		lblMarca.setBounds(50, 115, 100, 35);
		panelPrincipal.add(lblMarca);

		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setHorizontalAlignment(SwingConstants.LEFT);
		lblModelo.setBounds(330, 115, 100, 35);
		panelPrincipal.add(lblModelo);

		JLabel lblBastidor = new JLabel("Nº Bastidor:");
		lblBastidor.setHorizontalAlignment(SwingConstants.LEFT);
		lblBastidor.setBounds(50, 70, 100, 35);
		panelPrincipal.add(lblBastidor);

		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setHorizontalAlignment(SwingConstants.LEFT);
		lblMatricula.setBounds(50, 25, 100, 35);
		panelPrincipal.add(lblMatricula);

		txtMatricula = new JTextField();
		txtMatricula.setColumns(10);
		txtMatricula.setBounds(150, 25, 150, 35);
		panelPrincipal.add(txtMatricula);

		txtBastidor = new JTextField();
		txtBastidor.setColumns(10);
		txtBastidor.setBounds(150, 70, 150, 35);
		panelPrincipal.add(txtBastidor);

		txtModelo = new JTextField();
		txtModelo.setColumns(10);
		txtModelo.setBounds(430, 115, 150, 35);
		panelPrincipal.add(txtModelo);

		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		txtMarca.setBounds(150, 115, 150, 35);
		panelPrincipal.add(txtMarca);

		cmbClientes = new JComboBox<String>();
		cmbClientes.setBounds(150, 160, 150, 35);
		panelPrincipal.add(cmbClientes);

		JLabel lblPropietario = new JLabel("Propietario:");
		lblPropietario.setHorizontalAlignment(SwingConstants.LEFT);
		lblPropietario.setBounds(50, 160, 100, 35);
		panelPrincipal.add(lblPropietario);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipo.setBounds(330, 70, 100, 35);
		panelPrincipal.add(lblTipo);

		cmbTipo = new JComboBox<String>();
		cmbTipo.addItem("Diésel");
		cmbTipo.addItem("Gasolina");
		cmbTipo.addItem("Eléctrico");
		cmbTipo.setBounds(430, 70, 150, 35);
		panelPrincipal.add(cmbTipo);

		JLabel lblFechaFabricacion = new JLabel("Fabricación:");
		lblFechaFabricacion.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaFabricacion.setBounds(330, 25, 100, 35);
		panelPrincipal.add(lblFechaFabricacion);

		txtFechaFabricacionD = new JTextField();
		txtFechaFabricacionD.setColumns(10);
		txtFechaFabricacionD.setBounds(430, 25, 35, 35);
		panelPrincipal.add(txtFechaFabricacionD);

		txtFechaFabricacionM = new JTextField();
		txtFechaFabricacionM.setColumns(10);
		txtFechaFabricacionM.setBounds(470, 25, 35, 35);
		panelPrincipal.add(txtFechaFabricacionM);

		txtFechaFabricacionA = new JTextField();
		txtFechaFabricacionA.setColumns(10);
		txtFechaFabricacionA.setBounds(510, 25, 70, 35);
		panelPrincipal.add(txtFechaFabricacionA);

		JLabel lblActivo = new JLabel("Activo:");
		lblActivo.setHorizontalAlignment(SwingConstants.LEFT);
		lblActivo.setBounds(330, 160, 100, 35);
		panelPrincipal.add(lblActivo);

		chkActivo = new JCheckBox("");
		chkActivo.setSelected(true);
		chkActivo.setOpaque(false);
		chkActivo.setHorizontalAlignment(SwingConstants.CENTER);
		chkActivo.setBounds(430, 160, 150, 35);
		panelPrincipal.add(chkActivo);

		// ===== modelos =====
		// --- crear ---
		dcbmClientes = new DefaultComboBoxModel<String>();
		dcbmClientes.addAll(Datos.listarClientes());

		// --- asignar ---
		cmbClientes.setModel(dcbmClientes);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Actions ---
		// - JTextField -
		ArrayList<JTextField> camposTexto = new ArrayList<JTextField>();
		camposTexto.addAll(Arrays.asList(txtMatricula, txtBastidor, txtMarca, txtModelo, txtFechaFabricacionD,
				txtFechaFabricacionM, txtFechaFabricacionA));
		for (JTextField txt : camposTexto) {
			txt.addActionListener(this);
			txt.addFocusListener(this);

			txt.setDisabledTextColor(Color.DARK_GRAY);
		}

		// - JButton -
		btnCancelar.addActionListener(this);
		btnGuardar.addActionListener(this);

		// ===== ajustes de usuario =====
		// --- fuente y color ---
		panelPrincipal.setBackground(Inicio.colorFondo);

		ArrayList<JLabel> etiquetas = new ArrayList<JLabel>();
		etiquetas.addAll(Arrays.asList(lblMarca, lblModelo, lblBastidor, lblMatricula, lblPropietario, lblTipo,
				lblFechaFabricacion, lblActivo));

		for (JLabel lbl : etiquetas) {
			lbl.setFont(Inicio.fuente);
			lbl.setForeground(Inicio.colorFuente);
		}

		for (JTextField txt : camposTexto) {
			txt.setFont(Inicio.fuenteObjetos);
			txt.setBackground(Inicio.colorFondoObjetos);
			txt.setForeground(Inicio.colorFuenteObjetos);
		}

		btnCancelar.setFont(Inicio.fuenteObjetos);
		btnCancelar.setBackground(Inicio.colorFondoObjetos);
		btnCancelar.setForeground(Inicio.colorFuenteObjetos);

		btnGuardar.setFont(Inicio.fuenteObjetos);
		btnGuardar.setBackground(Inicio.colorFondoObjetos);
		btnGuardar.setForeground(Inicio.colorFuenteObjetos);

		cmbTipo.setFont(Inicio.fuenteObjetos);
		cmbTipo.setBackground(Inicio.colorFondoObjetos);
		cmbTipo.setForeground(Inicio.colorFuenteObjetos);
		JTextField etf = (JTextField) cmbTipo.getEditor().getEditorComponent();
		etf.setDisabledTextColor(Color.DARK_GRAY);
		etf.setBackground(Inicio.colorFondoObjetos);

		cmbClientes.setFont(Inicio.fuenteObjetos);
		cmbClientes.setBackground(Inicio.colorFondoObjetos);
		cmbClientes.setForeground(Inicio.colorFuenteObjetos);

		// ===== valores por defecto =====
		txtMatricula.setText("123456A");
		txtBastidor.setText("1234567891234567A");
		txtMarca.setText("Marca");
		txtModelo.setText("Modelo");
		txtFechaFabricacionD.setText("1");
		txtFechaFabricacionM.setText("1");
		txtFechaFabricacionA.setText("2000");
	}

	/**
	 * guarda en un ArrayList las matrículas de los vehículos existentes para
	 * comprobar si está duplicada
	 * 
	 * @param vehiculos lista de vehículos
	 */
	public void setAlMatricuals(ArrayList<Vehiculo> vehiculos) {
		for (Vehiculo v : vehiculos) {
			alMatriculas.add(v.getMatricula());
		}
	}

	/**
	 * escribe los datos del vehículo que está siendo editado en los campos
	 * 
	 * @param vehiculo vehículo que está siendo editado
	 */
	public void modoEdicion(Vehiculo vehiculo) {
		edicion = true;

		setTitle("Editar " + vehiculo.getMatricula() + " | " + Inicio.empleadoActual.getNombre());

		txtMatricula.setText(vehiculo.getMatricula());
		txtMatricula.setEnabled(false);
		txtBastidor.setText(vehiculo.getBastidor());
		txtBastidor.setEnabled(false);

		txtMarca.setText(vehiculo.getMarca());
		txtMarca.setEnabled(false);
		txtModelo.setText(vehiculo.getModelo());
		txtModelo.setEnabled(false);

		txtFechaFabricacionD.setText(String.valueOf(vehiculo.getFechaFabricacion().getDay()));
		txtFechaFabricacionD.setEnabled(false);
		txtFechaFabricacionM.setText(String.valueOf(vehiculo.getFechaFabricacion().getMonth()));
		txtFechaFabricacionM.setEnabled(false);
		txtFechaFabricacionA.setText(String.valueOf(vehiculo.getFechaFabricacion().getYear()));
		txtFechaFabricacionA.setEnabled(false);

		chkActivo.setSelected(vehiculo.isActivo());

		cmbTipo.setSelectedItem(vehiculo.getTipo());
		cmbTipo.setEditable(true);
		cmbTipo.setEnabled(false);
		dcbmClientes.setSelectedItem(vehiculo.getPropietario());
	}

	private boolean guardar() {
		try {
			String matricula = txtMatricula.getText();
			String bastidor = txtBastidor.getText();

			String marca = txtMarca.getText();
			String modelo = txtModelo.getText();

			int fechaFabricacionD = Integer.parseInt(txtFechaFabricacionD.getText());
			int fechaFabricacionM = Integer.parseInt(txtFechaFabricacionM.getText());
			int fechaFabricacionA = Integer.parseInt(txtFechaFabricacionA.getText());

			ArrayList<String> camposTxt = new ArrayList<String>();
			camposTxt.addAll(Arrays.asList(marca, modelo));

			if (camposTxt.contains("")) {
				JOptionPane.showMessageDialog(this, (String) "Campo de texto vacío", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else if (matricula.length() != 7) {
				JOptionPane.showMessageDialog(this, (String) "Matrícula inválida", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else if (bastidor.length() != 17) {
				JOptionPane.showMessageDialog(this, (String) "Bastidor inválido", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else if (fechaFabricacionD < 1 || fechaFabricacionD > 31 || fechaFabricacionM < 1
					|| fechaFabricacionM > 12 || fechaFabricacionA < 1900
					|| fechaFabricacionA > Calendar.getInstance().get(Calendar.YEAR)) {
				JOptionPane.showMessageDialog(this, (String) "Fecha no válida", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else if (cmbTipo.getSelectedIndex() < 0) {
				JOptionPane.showMessageDialog(this, (String) "Seleccione un tipo", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else if (cmbClientes.getSelectedIndex() < 0) {
				JOptionPane.showMessageDialog(this, (String) "Seleccione un propietario", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else if (!edicion && alMatriculas.contains(matricula)) {
				JOptionPane.showMessageDialog(this, (String) "Vehículo ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else {
				String propietario = (String) cmbClientes.getSelectedItem();
				String tipo = (String) cmbTipo.getSelectedItem();
				boolean activo = chkActivo.isSelected();

				Datos.guardarVehiculo(
						new Vehiculo(matricula, bastidor, propietario, marca, modelo,
								new Fecha(fechaFabricacionD, fechaFabricacionM, fechaFabricacionA), tipo, activo),
						edicion);

				return true;
			}

		} catch (NumberFormatException npe) {
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
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		int guardar = JOptionPane.YES_OPTION;

		if (o == btnCancelar) {
			guardar = Salir.edicion();
		}

		boolean valido = false;

		if (guardar == JOptionPane.YES_OPTION) {
			valido = guardar();
		}

		if (guardar == JOptionPane.NO_OPTION || valido) {
			AdministrarVehiculos.actualizarTabla();
			AdministrarVehiculos.botones(true);
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