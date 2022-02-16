package ordenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import clases.Cliente;
import clases.MaterialUsado;
import clases.Pendiente;
import clases.Primaria;
import clases.Reparacion;
import clases.Vehiculo;
import edicion.EditarReparacion;

import funciones.Archivos;
import funciones.General;
import funciones.Salir;
import funciones.Tablas;
import navegacion.Inicio;

/**
 * 
 * @author Grupo 2
 *
 */
public class CrearPendiente extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private static JButton btnVolver;
	private static JButton btnCrear;
	private static JButton btnAgregar;
	private static JButton btnEditar;
	private static JButton btnEliminar;

	private JLabel lblCodigoTxt;
	private JLabel lblComentarioTxt;

	private JTable tblCliente;
	private JTable tblVehiculo;

	private JScrollPane scrollReparaciones;
	private JScrollPane scrollMateriales;
	private static JTable tblReparaciones;
	private static JTable tblMateriales;

	private static ArrayList<Reparacion> alReparaciones = new ArrayList<Reparacion>();
	private static ArrayList<MaterialUsado> alMaterialesGeneral = new ArrayList<MaterialUsado>();

	private Primaria primaria;

	private static boolean bloqueado;

	public CrearPendiente() {
		setResizable(false);

		if (Inicio.cuentaActual.esMecanico()) {
			setTitle("Crear orden pendiente | " + Inicio.cuentaActual.getNombre());
			setBounds(100, 100, 790, 720);
			getContentPane().setPreferredSize(new Dimension(790, 720));
		} else {
			setTitle("Mostrar orden primaria | " + Inicio.cuentaActual.getNombre());
			setBounds(100, 100, 790, 430);
			getContentPane().setPreferredSize(new Dimension(790, 430));
		}
		pack();

		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		btnCrear = new JButton("Crear orden pendiente");
		btnCrear.setBounds(403, 670, 180, 40);
		panelPrincipal.add(btnCrear);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(207, 670, 180, 40);
		panelPrincipal.add(btnVolver);

		btnAgregar = new JButton("Agregar reparación");
		btnAgregar.setBounds(35, 585, 230, 60);
		panelPrincipal.add(btnAgregar);

		btnEditar = new JButton("Editar reparación");
		btnEditar.setBounds(280, 585, 230, 60);
		panelPrincipal.add(btnEditar);

		btnEliminar = new JButton("Eliminar reparación");
		btnEliminar.setBounds(525, 585, 230, 60);
		panelPrincipal.add(btnEliminar);

		JLabel lblCodigo = new JLabel("Código orden primaria:");
		lblCodigo.setBounds(10, 185, 150, 35);
		panelPrincipal.add(lblCodigo);

		lblCodigoTxt = new JLabel("");
		lblCodigoTxt.setBounds(160, 185, 250, 35);
		panelPrincipal.add(lblCodigoTxt);

		lblComentarioTxt = new JLabel("");
		lblComentarioTxt.setVerticalAlignment(SwingConstants.TOP);
		lblComentarioTxt.setBounds(10, 255, 500, 100);
		panelPrincipal.add(lblComentarioTxt);

		JLabel lblComentario = new JLabel("Comentario:");
		lblComentario.setBounds(10, 220, 150, 35);
		panelPrincipal.add(lblComentario);

		// ===== barras de desplazamiento =====
		// --- reparaciones ---
		scrollReparaciones = new JScrollPane();
		scrollReparaciones.setBounds(10, 370, 420, 200);
		panelPrincipal.add(scrollReparaciones);

		// --- piezas ---
		scrollMateriales = new JScrollPane();
		scrollMateriales.setBounds(445, 370, 335, 200);
		panelPrincipal.add(scrollMateriales);

		// ===== modelos =====
		// --- crear ---
		DefaultTableModel dtmCliente = new DefaultTableModel();
		dtmCliente.addColumn("");
		dtmCliente.addColumn("");

		DefaultTableModel dtmVehiculo = new DefaultTableModel();
		dtmVehiculo.addColumn("");
		dtmVehiculo.addColumn("");

		DefaultTableModel dtmReparaciones = new DefaultTableModel();
		dtmReparaciones.addColumn("Descripción");
		dtmReparaciones.addColumn("Horas");
		dtmReparaciones.addColumn("Mano de obra");

		DefaultTableModel dtmMateriales = new DefaultTableModel();
		dtmMateriales.addColumn("Nombre");
		dtmMateriales.addColumn("Precio");
		dtmMateriales.addColumn("Cantidad");

		// --- asignar ---
		tblCliente = new JTable(dtmCliente) {
			private static final long serialVersionUID = -3909141556237115067L;

			/**
			 * 
			 * @param row    cuyo valor se va a consultar
			 * @param column cuyo valor se va a consultar
			 * @return Devuelve verdadero si la celda en la fila y la columna es editable.
			 *         De lo contrario, invocar setValueAt en la celda no tendrá ningún
			 *         efecto.
			 */
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblCliente.setBounds(10, 10, 420, 160);
		tblCliente.setRowHeight(20);
		tblCliente.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblCliente.getTableHeader().setReorderingAllowed(false);
		tblCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelPrincipal.add(tblCliente);

		tblVehiculo = new JTable(dtmVehiculo) {
			private static final long serialVersionUID = -3909141556237115067L;

			/**
			 * 
			 * @param row    cuyo valor se va a consultar
			 * @param column cuyo valor se va a consultar
			 * @return Devuelve verdadero si la celda en la fila y la columna es editable.
			 *         De lo contrario, invocar setValueAt en la celda no tendrá ningún
			 *         efecto.
			 */
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblVehiculo.setBounds(445, 10, 335, 160);
		tblVehiculo.setRowHeight(20);
		tblVehiculo.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblVehiculo.getTableHeader().setReorderingAllowed(false);
		tblVehiculo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelPrincipal.add(tblVehiculo);

		tblReparaciones = new JTable(dtmReparaciones) {
			private static final long serialVersionUID = -3909141556237115067L;

			/**
			 * 
			 * @param row    cuyo valor se va a consultar
			 * @param column cuyo valor se va a consultar
			 * @return Devuelve verdadero si la celda en la fila y la columna es editable.
			 *         De lo contrario, invocar setValueAt en la celda no tendrá ningún
			 *         efecto.
			 */
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblReparaciones.setRowHeight(20);
		tblReparaciones.setFillsViewportHeight(true);
		tblReparaciones.getTableHeader().setReorderingAllowed(false);
		tblReparaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollReparaciones.setViewportView(tblReparaciones);

		tblMateriales = new JTable(dtmMateriales) {
			private static final long serialVersionUID = -3909141556237115067L;

			/**
			 * 
			 * @param row    cuyo valor se va a consultar
			 * @param column cuyo valor se va a consultar
			 * @return Devuelve verdadero si la celda en la fila y la columna es editable.
			 *         De lo contrario, invocar setValueAt en la celda no tendrá ningún
			 *         efecto.
			 */
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
		btnVolver.addActionListener(this);
		btnCrear.addActionListener(this);
		btnAgregar.addActionListener(this);
		btnEditar.addActionListener(this);
		btnEliminar.addActionListener(this);

		// ===== ajustes de usuario =====
		// --- fuente y color ---
		panelPrincipal.setBackground(Inicio.colorFondo);

		ArrayList<JLabel> etiquetas = new ArrayList<JLabel>();
		etiquetas.addAll(Arrays.asList(lblCodigo, lblCodigoTxt, lblComentario, lblComentarioTxt));
		for (JLabel lbl : etiquetas) {
			lbl.setFont(Inicio.fuente);
			lbl.setForeground(Inicio.colorFuente);
		}

		tblReparaciones.getTableHeader().setFont(Inicio.fuenteObjetos);
		tblReparaciones.getTableHeader().setBackground(Inicio.colorFondoObjetos);

		tblMateriales.getTableHeader().setFont(Inicio.fuenteObjetos);
		tblMateriales.getTableHeader().setBackground(Inicio.colorFondoObjetos);

		tblReparaciones.setFont(Inicio.fuente);
		tblReparaciones.setBackground(Inicio.colorFondoObjetos);
		tblReparaciones.setForeground(Inicio.colorFuenteObjetos);

		tblMateriales.setFont(Inicio.fuente);
		tblMateriales.setBackground(Inicio.colorFondoObjetos);
		tblMateriales.setForeground(Inicio.colorFuenteObjetos);

		btnVolver.setFont(Inicio.fuenteObjetos);
		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnVolver.setForeground(Inicio.colorFuenteObjetos);

		btnCrear.setFont(Inicio.fuenteObjetos);
		btnCrear.setBackground(Inicio.colorFondoObjetos);
		btnCrear.setForeground(Inicio.colorFuenteObjetos);

		btnAgregar.setFont(Inicio.fuenteObjetos);
		btnAgregar.setBackground(Inicio.colorFondoObjetos);
		btnAgregar.setForeground(Inicio.colorFuenteObjetos);

		btnEditar.setFont(Inicio.fuenteObjetos);
		btnEditar.setBackground(Inicio.colorFondoObjetos);
		btnEditar.setForeground(Inicio.colorFuenteObjetos);

		btnEliminar.setFont(Inicio.fuenteObjetos);
		btnEliminar.setBackground(Inicio.colorFondoObjetos);
		btnEliminar.setForeground(Inicio.colorFuenteObjetos);

		scrollReparaciones.setBackground(Inicio.colorFondoObjetos);
		scrollMateriales.setBackground(Inicio.colorFondoObjetos);

		// --- tablas verticales ---
		tblCliente.setFont(Inicio.fuente);
		tblVehiculo.setFont(Inicio.fuente);

		tblCliente.setBackground(Inicio.colorFondoObjetos);
		tblVehiculo.setBackground(Inicio.colorFondoObjetos);

		tblCliente.setForeground(Inicio.colorFuenteObjetos);
		tblVehiculo.setForeground(Inicio.colorFuenteObjetos);
	}

	/**
	 * carga datos de una orden primaria en caso el usuario no sea el mecanico
	 * deshabilita los seguientes buttones
	 * 
	 * @param op objeto orden primaria
	 */
	public void cargarDatos(Primaria op) {
		primaria = new Primaria(op);

		if (!Inicio.cuentaActual.esMecanico()) {
			btnAgregar.setVisible(false);
			btnCrear.setVisible(false);
			btnEditar.setVisible(false);
			btnEliminar.setVisible(false);

			btnVolver.setBounds(305, 380, 180, 40);

			scrollReparaciones.setVisible(false);
			scrollMateriales.setVisible(false);
		}

		// ===== datos cliente =====
		// --- cargar cliente ---
		Cliente c = primaria.getPropietario();

		// --- escribir cliente ---
		DefaultTableModel dtmCliente = (DefaultTableModel) tblCliente.getModel();
		dtmCliente.addRow(new Object[] { "DNI", c.getDNI() });

		dtmCliente.addRow(new Object[] { "Nombre", c.getNombre() });
		dtmCliente.addRow(new Object[] { "Apellidos", c.getApellidos() });

		dtmCliente.addRow(new Object[] { "Tel.", c.getTelefono() });
		dtmCliente.addRow(new Object[] { "Email", c.getEmail() });

		dtmCliente.addRow(new Object[] { "Fecha nacimiento", c.getFechaNacimiento() });
		dtmCliente.addRow(new Object[] { "Dirección", c.getDireccion() });

		dtmCliente.addRow(new Object[] { "Fecha alta", c.getFechaAlta() });

		// ===== datos vehículo =====
		// --- cargar vehículo ---
		Vehiculo v = primaria.getVehiculo();

		// --- escribir vehículo ---
		DefaultTableModel dtmVehiculo = (DefaultTableModel) tblVehiculo.getModel();
		dtmVehiculo.addRow(new Object[] { "Matrícula", v.getMatricula() });
		dtmVehiculo.addRow(new Object[] { "Bastidor", v.getBastidor() });

		dtmVehiculo.addRow(new Object[] { "Modelo", v.getMarca() + " " + v.getModelo() });
		dtmVehiculo.addRow(new Object[] { "Color", v.getColor() });

		dtmVehiculo.addRow(new Object[] { "Cilindrada", v.getCilindrada() });

		dtmVehiculo.addRow(new Object[] { "KMs recorridos", v.getKmRecorridos() });
		dtmVehiculo.addRow(new Object[] { "Año ITV", v.getFechaITV() });

		dtmVehiculo.addRow(new Object[] { "Tipo", v.getTipo() });

		// ===== datos orden primaria =====
		lblCodigoTxt.setText(primaria.getCodigo());
		lblComentarioTxt.setText(primaria.getComentarios());

		// ===== estilizar tablas =====
		Tablas.vertical(tblCliente);
		Tablas.vertical(tblVehiculo);
	}

	/**
	 * este metodo actualiza la tabla y carga los datos de la tabla reparaciones
	 * 
	 * @see Reparacion
	 */
	public static void actualizarTablas() {
		alReparaciones.sort(Comparator.naturalOrder());
		alMaterialesGeneral.clear();

		DefaultTableModel dtmReparaciones = (DefaultTableModel) tblReparaciones.getModel();
		dtmReparaciones.setRowCount(0);

		for (Reparacion r : alReparaciones) {
			dtmReparaciones
					.addRow(new Object[] { r.getDescripcion(), r.getHoras(), General.formatear(r.getManoObra()) });
			alMaterialesGeneral.addAll(r.getMaterialesUsados());
		}

		Tablas.ajustarColumnas(tblReparaciones);

		alMaterialesGeneral.sort(Comparator.naturalOrder());

		DefaultTableModel dtmMateriales = (DefaultTableModel) tblMateriales.getModel();
		dtmMateriales.setRowCount(0);

		for (MaterialUsado mu : alMaterialesGeneral) {
			dtmMateriales.addRow(new Object[] { mu.getNombre(), General.formatear(mu.getPrecio()), mu.getCantidad() });
		}
	}

	/**
	 * modifica la vicivilidad de buttones segun el estado pasado como paramento
	 * 
	 * @param estado si el estado es verdadero enabilita los seguientes buttones
	 */
	public static void botones(boolean estado) {
		btnAgregar.setEnabled(estado);
		btnEditar.setEnabled(estado);
		btnEliminar.setEnabled(estado);
		btnVolver.setEnabled(estado);
		btnCrear.setEnabled(estado);

		bloqueado = !estado;
	}

	/**
	 * getter de ArrayList Reparaciones
	 * 
	 * @return alreparaciones
	 */
	public static ArrayList<Reparacion> getReparaciones() {
		return alReparaciones;
	}

	/**
	 * invocado cuando una accion ocurre sobre los elementos
	 * 
	 * @param ae el evento a procesar
	 * @see actionPerformed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o == btnCrear) {
			if (tblReparaciones.getRowCount() > 0 && tblMateriales.getRowCount() > 0) {
				Archivos.borrarPrimaria(primaria.getCodigo());

				Archivos.guardarPendiente(new Pendiente(primaria, Inicio.cuentaActual, alReparaciones));

				JOptionPane.showMessageDialog(this,
						(String) "Se ha convertido la order primaria en una orden pendiente", "INFO",
						JOptionPane.INFORMATION_MESSAGE);

				ListaPrimarias lp = new ListaPrimarias();
				lp.setVisible(true);

				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, (String) "No se ha agregado ninguna reparación", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (o == btnAgregar) {
			EditarReparacion er = new EditarReparacion();
			er.setVisible(true);
		} else if (o == btnEditar) {
			EditarReparacion er = new EditarReparacion();

			int row = tblReparaciones.getSelectedRow();
			if (row >= 0) {
				er.modoEdicion(alReparaciones.get(row));
				er.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, (String) "No hay ninguna reparación seleccionada", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (o == btnEliminar) {
			int row = tblReparaciones.getSelectedRow();
			if (row >= 0) {
				alReparaciones.remove(row);
				DefaultTableModel dtm = (DefaultTableModel) tblReparaciones.getModel();
				dtm.removeRow(row);

				actualizarTablas();
			} else {
				JOptionPane.showMessageDialog(this, (String) "No hay ninguna reparación seleccionada", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (o == btnVolver) {
			ListaPrimarias lop = new ListaPrimarias();
			lop.setVisible(true);

			this.dispose();
		}
	}

	// ===== Overrides =======
	/**
	 * invocado cuando el usuario intenta cerrar la ventana
	 * 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		if (bloqueado) {
			Salir.error();
		} else {
			Salir.general(this);
		}
	}

	/**
	 * Invocado la primera vez una ventana se ha hecho visible
	 * 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		// comportamiento por defecto
	}

	/**
	 * Invocado cuando una ventana se cerro como resultado llamando a dispose en la
	 * ventana
	 * 
	 * @param e evento a procesar
	 */
	@Override
	public void windowClosed(WindowEvent e) {
		// comportamiento por defecto
	}

	/**
	 * Invocado cuando a una ventana se cambio de normal a minimizado por varias
	 * plataformas una minimizada ventana se procesa como el icono especificado en
	 * la propiedad de siconImage
	 * 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowIconified(WindowEvent e) {
		// comportamiento por defecto
	}

	/**
	 * cuando una ventana cambia de minimizado a ventana normal
	 * 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowDeiconified(WindowEvent e) {
		// comportamiento por defecto
	}

	/**
	 * Invocado cuando la ventana es capacitado a ser ventana activa solo un frame o
	 * un dialog puede ser ventana activa
	 * 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		// comportamiento por defecto
	}

	/**
	 * Invocado cuando una ventana no es langer la ventana activa solo un Frame o un
	 * Dialog puede ser ventana activa
	 * 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowDeactivated(WindowEvent e) {
		// comportamiento por defecto
	}
}