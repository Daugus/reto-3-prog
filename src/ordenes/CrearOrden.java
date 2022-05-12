package ordenes;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import administracion.AdministrarClientes;
import administracion.AdministrarEmpleados;
import administracion.AdministrarVehiculos;
import clases.Fecha;
import clases.Orden;
import clases.Vehiculo;
import funciones.Datos;
import funciones.Log;
import funciones.Salir;
import navegacion.Inicio;
import navegacion.MenuAdmin;

/**
 * ventana de creación de órdenes
 */
public class CrearOrden extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private DefaultComboBoxModel<String> dcbmEmpleados;
	private DefaultComboBoxModel<String> dcbmClientes;
	private DefaultComboBoxModel<String> dcbmVehiculos;

	private JComboBox<String> cmbEmpleados;
	private JComboBox<String> cmbClientes;
	private JComboBox<String> cmbVehiculos;

	private JButton btnEmpleados;
	private JButton btnClientes;
	private JButton btnVehiculos;

	private JButton btnVolver;
	private JButton btnCrearOrden;

	private JTextArea txtComentario;

	private ArrayList<Vehiculo> alVehiculos;

	/**
	 * carga los elementos de la ventana
	 */
	public CrearOrden() {
		setResizable(false);
		setTitle("Crear orden de trabajo | " + Inicio.empleadoActual.getNombre());

		setBounds(100, 100, 600, 430);
		getContentPane().setPreferredSize(new Dimension(600, 430));
		pack();

		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		JLabel lblComentario = new JLabel("Agregar comentario para la orden:");
		lblComentario.setBounds(50, 220, 300, 35);
		panelPrincipal.add(lblComentario);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(112, 380, 180, 40);
		panelPrincipal.add(btnVolver);

		btnCrearOrden = new JButton("Crear orden primaria");
		btnCrearOrden.setBounds(308, 380, 180, 40);
		panelPrincipal.add(btnCrearOrden);

		txtComentario = new JTextArea();
		txtComentario.setLineWrap(true);

		JLabel lblVehiculo = new JLabel("Vehículo:");
		lblVehiculo.setBounds(50, 172, 100, 36);
		panelPrincipal.add(lblVehiculo);

		cmbVehiculos = new JComboBox<String>();
		cmbVehiculos.setBounds(150, 172, 150, 36);
		panelPrincipal.add(cmbVehiculos);

		btnVehiculos = new JButton("Administrar vehiculos");
		btnVehiculos.setBounds(320, 160, 230, 60);
		panelPrincipal.add(btnVehiculos);

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(50, 97, 100, 36);
		panelPrincipal.add(lblCliente);

		cmbClientes = new JComboBox<String>();
		cmbClientes.setBounds(150, 97, 150, 36);
		panelPrincipal.add(cmbClientes);

		btnClientes = new JButton("Administrar clientes");
		btnClientes.setBounds(320, 85, 230, 60);
		panelPrincipal.add(btnClientes);

		JLabel lblEmpleado = new JLabel("Empleado:");
		lblEmpleado.setBounds(50, 22, 100, 36);
		panelPrincipal.add(lblEmpleado);

		cmbEmpleados = new JComboBox<String>();
		cmbEmpleados.setBounds(150, 22, 150, 36);
		panelPrincipal.add(cmbEmpleados);

		btnEmpleados = new JButton("Administrar empleados");
		btnEmpleados.setBounds(320, 10, 230, 60);
		panelPrincipal.add(btnEmpleados);

		// ===== barras de desplazamiento =====
		JScrollPane barraScroll = new JScrollPane();
		barraScroll.setBounds(50, 255, 500, 100);
		panelPrincipal.add(barraScroll);

		barraScroll.setViewportView(txtComentario);

		// ===== modelos =====
		// --- crear ---
		dcbmEmpleados = new DefaultComboBoxModel<String>();
		dcbmEmpleados.addAll(Datos.listarEmpleados(""));

		dcbmClientes = new DefaultComboBoxModel<String>();
		dcbmClientes.addAll(Datos.listarClientes());

		dcbmVehiculos = new DefaultComboBoxModel<String>();
		alVehiculos = Datos.listarVehiculos();

		// --- asignar ---
		cmbClientes.setModel(dcbmClientes);
		cmbVehiculos.setModel(dcbmVehiculos);
		cmbEmpleados.setModel(dcbmEmpleados);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		cmbClientes.addActionListener(this);

		btnVolver.addActionListener(this);

		btnEmpleados.addActionListener(this);
		btnClientes.addActionListener(this);
		btnVehiculos.addActionListener(this);

		btnCrearOrden.addActionListener(this);

		// ===== ajustes de usuario =====
		// --- fuente ---
		lblEmpleado.setFont(Inicio.fuente);
		lblCliente.setFont(Inicio.fuente);
		lblVehiculo.setFont(Inicio.fuente);
		lblComentario.setFont(Inicio.fuente);

		txtComentario.setFont(Inicio.fuenteObjetos);

		cmbEmpleados.setFont(Inicio.fuenteObjetos);
		cmbClientes.setFont(Inicio.fuenteObjetos);
		cmbVehiculos.setFont(Inicio.fuenteObjetos);

		btnEmpleados.setFont(Inicio.fuenteObjetos);
		btnClientes.setFont(Inicio.fuenteObjetos);
		btnVehiculos.setFont(Inicio.fuenteObjetos);
		btnVolver.setFont(Inicio.fuenteObjetos);
		btnCrearOrden.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		txtComentario.setBackground(Inicio.colorFondoObjetos);

		cmbEmpleados.setBackground(Inicio.colorFondoObjetos);
		cmbClientes.setBackground(Inicio.colorFondoObjetos);
		cmbVehiculos.setBackground(Inicio.colorFondoObjetos);

		btnEmpleados.setBackground(Inicio.colorFondoObjetos);
		btnClientes.setBackground(Inicio.colorFondoObjetos);
		btnVehiculos.setBackground(Inicio.colorFondoObjetos);
		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnCrearOrden.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		lblEmpleado.setForeground(Inicio.colorFuente);
		lblCliente.setForeground(Inicio.colorFuente);
		lblVehiculo.setForeground(Inicio.colorFuente);
		lblComentario.setForeground(Inicio.colorFuente);

		txtComentario.setForeground(Inicio.colorFuenteObjetos);

		cmbEmpleados.setForeground(Inicio.colorFuenteObjetos);
		cmbClientes.setForeground(Inicio.colorFuenteObjetos);
		cmbVehiculos.setForeground(Inicio.colorFuenteObjetos);

		btnEmpleados.setForeground(Inicio.colorFuenteObjetos);
		btnClientes.setForeground(Inicio.colorFuenteObjetos);
		btnVehiculos.setForeground(Inicio.colorFuenteObjetos);
		btnVolver.setForeground(Inicio.colorFuenteObjetos);
		btnCrearOrden.setForeground(Inicio.colorFuenteObjetos);
	}

	/**
	 * invocado cuando ocurren una acción
	 * 
	 * @param ae el evento de acción
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object o = ae.getSource();

		if (o == cmbClientes && cmbClientes.getSelectedIndex() >= 0) {
			dcbmVehiculos.removeAllElements();
			String dniCliente = (String) cmbClientes.getSelectedItem();

			ArrayList<String> alVehiculosCliente = new ArrayList<String>();
			for (Vehiculo v : alVehiculos) {
				if (v.getPropietario().equals(dniCliente))
					alVehiculosCliente.add(v.getMatricula());
			}

			dcbmVehiculos.addAll(alVehiculosCliente);
		} else if (o == btnEmpleados) {
			AdministrarEmpleados aem = new AdministrarEmpleados(false);
			aem.setVisible(true);

			this.dispose();
		} else if (o == btnClientes) {
			AdministrarClientes ac = new AdministrarClientes();
			ac.setVisible(true);

			this.dispose();
		} else if (o == btnVehiculos) {
			AdministrarVehiculos av = new AdministrarVehiculos();
			av.setVisible(true);

			this.dispose();
		} else if (o == btnVolver) {
			MenuAdmin ma = new MenuAdmin();
			ma.setVisible(true);

			this.dispose();
		} else if (o == btnCrearOrden) {
			// --- empleado ---
			if (cmbEmpleados.getSelectedIndex() >= 0) {
				String dniEmpleado = (String) cmbEmpleados.getSelectedItem();

				// --- cliente ---
				if (cmbClientes.getSelectedIndex() >= 0) {
					// --- vehículo ---
					if (cmbVehiculos.getSelectedIndex() >= 0) {
						String matricula = (String) cmbVehiculos.getSelectedItem();

						String codigo = Datos.generarCodigoOrden();
						Datos.guardarOrden(
								new Orden(codigo, txtComentario.getText(), matricula, dniEmpleado, new Fecha()), false);

						JOptionPane.showMessageDialog(this, (String) "Se ha creado la order " + codigo, "INFO",
								JOptionPane.INFORMATION_MESSAGE);

						cmbEmpleados.setSelectedIndex(-1);
						cmbClientes.setSelectedIndex(-1);
						dcbmVehiculos.removeAllElements();
						txtComentario.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "Por favor seleccione un vehículo", "ERROR",
								JOptionPane.ERROR_MESSAGE);
						Log.error("No se ha seleccionado ningún vehiculo");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Por favor seleccione un cliente", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					Log.error("No se ha seleccionado ningún cliente");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Por favor seleccione un empleado", "ERROR",
						JOptionPane.ERROR_MESSAGE);
				Log.error("No se ha seleccionado ningún empleado");
			}
		}
	}

	/**
	 * invocado cuando se cierra la ventana
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowClosing(WindowEvent we) {
		Salir.general(this);
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