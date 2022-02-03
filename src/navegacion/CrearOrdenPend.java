package navegacion;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import clases.Cliente;
import clases.Vehiculo;
import funciones.Salir;

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

	private JLabel lblCodigoTxt;
	private JLabel lblComentarioTxt;

	private JTable tblCliente;
	private JTable tblVehiculo;
	private JTable tblReparaciones;
	private static JTable tblMateriales;

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
		btnGenerar.setBounds(10, 580, 180, 40);
		panelPrincipal.add(btnGenerar);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 630, 180, 40);
		panelPrincipal.add(btnVolver);

		btnAgregar = new JButton("Agregar materiales");
		btnAgregar.setBounds(470, 530, 180, 40);
		panelPrincipal.add(btnAgregar);
		
		JLabel lblCodigo = new JLabel("Código orden primaria:");
		lblCodigo.setBounds(10, 180, 160, 25);
		panelPrincipal.add(lblCodigo);
		
		lblCodigoTxt = new JLabel("");
		lblCodigoTxt.setBounds(170, 180, 250, 25);
		panelPrincipal.add(lblCodigoTxt);
		
		lblComentarioTxt = new JLabel("");
		lblComentarioTxt.setBounds(10, 230, 500, 100);
		panelPrincipal.add(lblComentarioTxt);
		
		JLabel lblComentario = new JLabel("Comentario:");
		lblComentario.setBounds(10, 205, 160, 25);
		panelPrincipal.add(lblComentario);

		tblReparaciones = new JTable() {
			private static final long serialVersionUID = -3909141556237115067L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblReparaciones.setFillsViewportHeight(true);
		tblReparaciones.getTableHeader().setReorderingAllowed(false);
		tblReparaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tblMateriales = new JTable() {
			private static final long serialVersionUID = -3909141556237115067L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblMateriales.setFillsViewportHeight(true);
		tblMateriales.getTableHeader().setReorderingAllowed(false);
		tblMateriales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// ===== barras de desplazamiento =====
		// --- reparaciones ---
		JScrollPane scrollReparaciones = new JScrollPane();
		scrollReparaciones.setBounds(10, 340, 400, 180);
		panelPrincipal.add(scrollReparaciones);
		
		scrollReparaciones.setViewportView(tblReparaciones);

		// --- piezas ---
		JScrollPane scrollMateriales = new JScrollPane();
		scrollMateriales.setBounds(420, 340, 300, 180);
		panelPrincipal.add(scrollMateriales);
		
		scrollMateriales.setViewportView(tblMateriales);

		// ===== modelos =====
		// --- crear ---
		DefaultTableModel dtmCliente = new DefaultTableModel();
		dtmCliente.addColumn("");
		dtmCliente.addColumn("");

		DefaultTableModel dtmVehiculo = new DefaultTableModel();
		dtmVehiculo.addColumn("");
		dtmVehiculo.addColumn("");
		
		// --- asignar ---
		tblCliente = new JTable(dtmCliente);
		tblCliente.setBounds(10, 11, 400, 140);
		tblCliente.setRowHeight(20);
		tblCliente.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPrincipal.add(tblCliente);

		tblVehiculo = new JTable(dtmVehiculo);
		tblVehiculo.setBounds(420, 11, 300, 160);
		tblVehiculo.setRowHeight(20);
		tblVehiculo.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPrincipal.add(tblVehiculo);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		btnVolver.addActionListener(this);
		btnGenerar.addActionListener(this);
		btnAgregar.addActionListener(this);

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
	
	public void cargarDatos()
	{
		if (!Inicio.cuentaActual.getMecanico())
		{
			btnAgregar.setVisible(false);
			btnGenerar.setVisible(false);
		}

		// ===== datos cliente =====
		// --- cargar cliente ---
		Cliente c = ListaOrdenesPrim.getOrdenPrim().getPropietario();
				
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
		Vehiculo v = ListaOrdenesPrim.getOrdenPrim().getVehiculo();

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
		lblCodigoTxt.setText(ListaOrdenesPrim.getOrdenPrim().getCodOrdenPrim());
		lblComentarioTxt.setText(ListaOrdenesPrim.getOrdenPrim().getComentarios());
				
		// ===== estilizar tablas =====
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer()
		{
			private static final long serialVersionUID = 1L;
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
			{
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				setFont(Inicio.fuenteObjetos);
				
				return this;
			}
		};
		
		tblCliente.getColumnModel().getColumn(0).setCellRenderer(renderer);
		tblVehiculo.getColumnModel().getColumn(0).setCellRenderer(renderer);

		tblCliente.getColumnModel().getColumn(0).setPreferredWidth(120);
		tblVehiculo.getColumnModel().getColumn(0).setPreferredWidth(120);
		tblCliente.getColumnModel().getColumn(0).setMaxWidth(120);
		tblVehiculo.getColumnModel().getColumn(0).setMaxWidth(120);
	}
	
	public static void setTablaMateriales(TableModel tm)
	{
		tblMateriales.setModel(tm);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();

		if (o == btnGenerar)
		{
			
		}
		if (o == btnVolver)
		{
			ListaOrdenesPrim mat = new ListaOrdenesPrim();
			mat.setLocationRelativeTo(null);
			mat.setVisible(true);

			this.dispose();
		}
		else if (o == btnAgregar)
		{
			AsignarMaterial am = new AsignarMaterial();

			if (tblMateriales.getRowCount() > 0)
			{
				am.modoEdicion(tblMateriales.getModel());
			}
			
			am.setLocationRelativeTo(null);
			am.setVisible(true);
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