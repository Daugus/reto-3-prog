package navegacion;

import java.awt.Color;
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
import clases.OrdenPrim;
import clases.Reparacion;
import clases.Vehiculo;
import edicion.EditarReparacion;
import funciones.Salir;
import funciones.Tablas;

/**
 * 
 * @author Grupo 2
 *
 */
public class CrearOrdenPend extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private JButton btnVolver;
	private JButton btnGenerar;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnEliminar;

	private JLabel lblCodigoTxt;
	private JLabel lblComentarioTxt;

	private JTable tblCliente;
	private JTable tblVehiculo;
	private static JTable tblReparaciones;
	private static JTable tblMateriales;
	
	private static ArrayList<Reparacion> alReparaciones = new ArrayList<Reparacion>();
	private static ArrayList<MaterialUsado> alMaterialesGeneral = new ArrayList<MaterialUsado>();
	
	private static OrdenPrim primaria;

	public CrearOrdenPend()
	{
		setResizable(false);
		setTitle("Crear orden pendiente");
		
		setBounds(100, 100, 750, 730);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
	
		btnGenerar = new JButton("Validar orden");
		btnGenerar.setBounds(240, 630, 180, 40);
		panelPrincipal.add(btnGenerar);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 630, 180, 40);
		panelPrincipal.add(btnVolver);
		
		btnAgregar = new JButton("Agregar reparación");
		btnAgregar.setBounds(10, 529, 200, 40);
		panelPrincipal.add(btnAgregar);

		btnEditar = new JButton("Editar reparación");
		btnEditar.setBounds(220, 529, 200, 40);
		panelPrincipal.add(btnEditar);

		btnEliminar = new JButton("Eliminar reparación");
		btnEliminar.setBounds(430, 529, 200, 40);
		panelPrincipal.add(btnEliminar);

		JLabel lblCodigo = new JLabel("Código orden primaria:");
		lblCodigo.setBounds(10, 180, 160, 25);
		panelPrincipal.add(lblCodigo);
		
		lblCodigoTxt = new JLabel("");
		lblCodigoTxt.setBounds(170, 180, 250, 25);
		panelPrincipal.add(lblCodigoTxt);
		
		lblComentarioTxt = new JLabel("");
		lblComentarioTxt.setVerticalAlignment(SwingConstants.TOP);
		lblComentarioTxt.setBounds(10, 230, 500, 100);
		panelPrincipal.add(lblComentarioTxt);
		
		JLabel lblComentario = new JLabel("Comentario:");
		lblComentario.setBounds(10, 205, 160, 25);
		panelPrincipal.add(lblComentario);

		// ===== barras de desplazamiento =====
		// --- reparaciones ---
		JScrollPane scrollReparaciones = new JScrollPane();
		scrollReparaciones.setBounds(10, 340, 400, 180);
		panelPrincipal.add(scrollReparaciones);

		// --- piezas ---
		JScrollPane scrollMateriales = new JScrollPane();
		scrollMateriales.setBounds(420, 340, 300, 180);
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
		tblCliente = new JTable(dtmCliente)
		{
			private static final long serialVersionUID = -3909141556237115067L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblCliente.setBounds(10, 11, 400, 140);
		tblCliente.setRowHeight(20);
		tblCliente.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblCliente.getTableHeader().setReorderingAllowed(false);
		tblCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelPrincipal.add(tblCliente);

		tblVehiculo = new JTable(dtmVehiculo)
		{
			private static final long serialVersionUID = -3909141556237115067L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblVehiculo.setBounds(420, 11, 300, 160);
		tblVehiculo.setRowHeight(20);
		tblVehiculo.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblVehiculo.getTableHeader().setReorderingAllowed(false);
		tblVehiculo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelPrincipal.add(tblVehiculo);

		tblReparaciones = new JTable(dtmReparaciones) {
			private static final long serialVersionUID = -3909141556237115067L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblReparaciones.setFillsViewportHeight(true);
		tblReparaciones.getTableHeader().setReorderingAllowed(false);
		tblReparaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollReparaciones.setViewportView(tblReparaciones);

		tblMateriales = new JTable(dtmMateriales) {
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
		btnVolver.addActionListener(this);
		btnGenerar.addActionListener(this);
		btnAgregar.addActionListener(this);
		btnEditar.addActionListener(this);
		btnEliminar.addActionListener(this);

		// ===== ajustes de usuario =====
		// --- fuente y color ---
		panelPrincipal.setBackground(Inicio.colorFondo);

		ArrayList<JLabel> etiquetas = new ArrayList<JLabel>();
		etiquetas.addAll(Arrays.asList(lblCodigo, lblCodigoTxt, lblComentario, lblComentarioTxt));
		for (JLabel lbl : etiquetas)
		{
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
		
		btnGenerar.setFont(Inicio.fuenteObjetos);
		btnGenerar.setBackground(Inicio.colorFondoObjetos);
		btnGenerar.setForeground(Inicio.colorFuenteObjetos);
		
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
	
	public void cargarDatos(OrdenPrim op)
	{
		primaria = new OrdenPrim(op);
		if (!Inicio.cuentaActual.getMecanico())
		{
			btnAgregar.setVisible(false);
			btnGenerar.setVisible(false);
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

		// --- escribir OrdenPrim ---
		lblCodigoTxt.setText(primaria.getCodOrden());
		lblComentarioTxt.setText(primaria.getComentarios());
				
		// ===== estilizar tablas =====
		Tablas.vertical(tblCliente);
		Tablas.vertical(tblVehiculo);
	}
	
	public static void actualizarTablas()
	{
		alReparaciones.sort(Comparator.naturalOrder());
		alMaterialesGeneral.clear();

		DefaultTableModel dtmReparaciones = (DefaultTableModel) tblReparaciones.getModel();
		dtmReparaciones.setRowCount(0);

		for (Reparacion r : alReparaciones)
		{
			dtmReparaciones.addRow(new Object[] {r.getDescripcion(), r.getHoras(), r.getManoObra()});
			alMaterialesGeneral.addAll(r.getMaterialesUsados());
		}
		
		Tablas.ajustarColumnas(tblReparaciones);
		
		alMaterialesGeneral.sort(Comparator.naturalOrder());

		DefaultTableModel dtmMateriales = (DefaultTableModel) tblMateriales.getModel();
		dtmMateriales.setRowCount(0);
		
		for (MaterialUsado mu : alMaterialesGeneral)
		{
			dtmMateriales.addRow(new Object[] {mu.getNombre(), mu.getPrecio(), mu.getCantidad()});
		}
	}
	
	public static ArrayList<Reparacion> getReparaciones()
	{
		return alReparaciones;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();

		if (o == btnGenerar)
		{
		}
		else if (o == btnAgregar)
		{
			EditarReparacion er = new EditarReparacion();
			er.setLocationRelativeTo(null);
			er.setVisible(true);
		}
		else if (o == btnEditar)
		{
			EditarReparacion er = new EditarReparacion();

			int row = tblReparaciones.getSelectedRow();
			if (row >= 0)
			{
				er.modoEdicion(alReparaciones.get(row));
				er.setLocationRelativeTo(null);
				er.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(this, (String) "No hay ninguna reparación seleccionada", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (o == btnEliminar)
		{
			int row = tblReparaciones.getSelectedRow();
			if (row >= 0)
			{
				alReparaciones.remove(row);
				DefaultTableModel dtm = (DefaultTableModel) tblReparaciones.getModel();
				dtm.removeRow(row);
				
				actualizarTablas();
			}
			else
			{
				JOptionPane.showMessageDialog(this, (String) "No hay ninguna reparación seleccionada", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (o == btnVolver)
		{
			ListaOrdenesPrim mat = new ListaOrdenesPrim();
			mat.setLocationRelativeTo(null);
			mat.setVisible(true);
			
			this.dispose();
		}
	}
	
	// ===== Overrides =======
	@Override
	public void windowClosing(WindowEvent e) {
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