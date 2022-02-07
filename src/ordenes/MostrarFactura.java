package ordenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import clases.Cliente;
import clases.Factura;
import clases.MaterialUsado;
import clases.Reparacion;
import clases.Vehiculo;
import funciones.General;
import funciones.Salir;
import funciones.Tablas;
import navegacion.Inicio;

/**
 * 
 * @author Grupo 2
 *
 */
public class MostrarFactura extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private JButton btnVolver;

	private JTable tblCliente;
	private JTable tblVehiculo;
	private JTable tblReparaciones;
	private JTable tblMateriales;
	private JTable tblTotal;
	
	private Factura factura;
	private ArrayList<Reparacion> alReparaciones = new ArrayList<Reparacion>();
	private ArrayList<MaterialUsado> alMateriales = new ArrayList<MaterialUsado>();

	public MostrarFactura()
	{
		setResizable(false);
		setTitle("Generar factura | " + Inicio.cuentaActual.getNombre());
		
		setBounds(100, 100, 850, 520);
		getContentPane().setPreferredSize(new Dimension(850, 520));
		pack();
		
		setLocationRelativeTo(null);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
	
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 470, 180, 40);
		panelPrincipal.add(btnVolver);

		// ===== barras de desplazamiento =====
		// --- reparaciones ---
		JScrollPane scrollReparaciones = new JScrollPane();
		scrollReparaciones.setBounds(10, 185, 450, 200);
		panelPrincipal.add(scrollReparaciones);

		// --- piezas ---
		JScrollPane scrollMateriales = new JScrollPane();
		scrollMateriales.setBounds(475, 185, 365, 200);
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
		dtmReparaciones.addColumn("Coste");
		
		DefaultTableModel dtmMateriales = new DefaultTableModel();
		dtmMateriales.addColumn("Nombre");
		dtmMateriales.addColumn("Precio");
		dtmMateriales.addColumn("Cantidad");
		dtmMateriales.addColumn("Coste");
		
		DefaultTableModel dtmTotal = new DefaultTableModel();
		dtmTotal.addColumn("");
		dtmTotal.addColumn("");
		
		// --- asignar ---
		tblCliente = new JTable(dtmCliente)
		{
			private static final long serialVersionUID = -3909141556237115067L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblCliente.setBounds(10, 10, 450, 160);
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
		tblVehiculo.setBounds(475, 10, 365, 160);
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
		tblReparaciones.setRowHeight(20);
		tblReparaciones.setFillsViewportHeight(true);
		tblReparaciones.getTableHeader().setReorderingAllowed(false);
		tblReparaciones.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblReparaciones.getTableHeader().setBorder(new LineBorder(new Color(0, 0, 0)));
		tblReparaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollReparaciones.setViewportView(tblReparaciones);

		tblMateriales = new JTable(dtmMateriales) {
			private static final long serialVersionUID = -3909141556237115067L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblMateriales.setRowHeight(20);
		tblMateriales.setFillsViewportHeight(true);
		tblMateriales.getTableHeader().setReorderingAllowed(false);
		tblMateriales.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblMateriales.getTableHeader().setBorder(new LineBorder(new Color(0, 0, 0)));
		tblMateriales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollMateriales.setViewportView(tblMateriales);

		tblTotal = new JTable(dtmTotal) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblTotal.setBounds(640, 410, 200, 100);
		tblTotal.setRowHeight(20);
		tblTotal.getTableHeader().setReorderingAllowed(false);
		tblTotal.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblTotal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelPrincipal.add(tblTotal);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		btnVolver.addActionListener(this);

		// ===== ajustes de usuario =====
		// --- fuente y color ---
		panelPrincipal.setBackground(Inicio.colorFondo);
		
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
		
		scrollReparaciones.setBackground(Inicio.colorFondoObjetos);
		scrollMateriales.setBackground(Inicio.colorFondoObjetos);
		
		// --- tablas verticales ---
		tblCliente.setFont(Inicio.fuente);
		tblVehiculo.setFont(Inicio.fuente);
		tblTotal.setFont(Inicio.fuente);

		tblCliente.setBackground(Inicio.colorFondoObjetos);
		tblVehiculo.setBackground(Inicio.colorFondoObjetos);
		tblTotal.setBackground(Inicio.colorFondoObjetos);

		tblCliente.setForeground(Inicio.colorFuenteObjetos);
		tblVehiculo.setForeground(Inicio.colorFuenteObjetos);
		tblTotal.setForeground(Inicio.colorFuenteObjetos);
	}
	
	public void cargarDatos(Factura f)
	{
		factura = new Factura(f);

		// ===== datos cliente =====
		// --- cargar cliente ---
		Cliente c = factura.getPropietario();
				
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
		Vehiculo v = factura.getVehiculo();

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

		// ===== datos reparaciones =====
		// --- cargar reparaciones  ---
		alReparaciones = factura.getReparaciones();
		
		// --- escribir reparaciones y materiales usados ---
		alReparaciones.sort(Comparator.naturalOrder());

		DefaultTableModel dtmReparaciones = (DefaultTableModel) tblReparaciones.getModel();

		for (Reparacion r : alReparaciones)
		{
			dtmReparaciones.addRow(new Object[] {r.getDescripcion(), r.getHoras(), General.formatear(r.getManoObra()), General.formatear(r.getHoras() * r.getManoObra())});
			alMateriales.addAll(r.getMaterialesUsados());
		}
		
		Tablas.ajustarColumnas(tblReparaciones);
		
		alMateriales.sort(Comparator.naturalOrder());

		DefaultTableModel dtmMateriales = (DefaultTableModel) tblMateriales.getModel();
		
		for (MaterialUsado mu : alMateriales)
		{
			dtmMateriales.addRow(new Object[] {mu.getNombre(), General.formatear(mu.getPrecio()), mu.getCantidad(), General.formatear(mu.getPrecio() * mu.getCantidad())});
		}
		
		// ===== total =====
		DefaultTableModel dtmTotal = (DefaultTableModel) tblTotal.getModel();
		dtmTotal.addRow(new Object[] {"Total reparaciones", General.formatear(factura.getCosteReparaciones())});
		dtmTotal.addRow(new Object[] {"Total materiales",   General.formatear(factura.getCosteMateriales())});
		dtmTotal.addRow(new Object[] {"Subtotal",           General.formatear(factura.getSubtotal())});
		dtmTotal.addRow(new Object[] {"IVA (21%)",          General.formatear(factura.getIva())});
		dtmTotal.addRow(new Object[] {"Total",              General.formatear(factura.getTotal())});
				
		// ===== estilizar tablas =====
		Tablas.vertical(tblCliente);
		Tablas.vertical(tblVehiculo);
		Tablas.vertical(tblTotal);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		ListaFacturas lf = new ListaFacturas();
		lf.setVisible(true);
		
		this.dispose();
	}
	
	// ===== Overrides =======
	@Override
	public void windowClosing(WindowEvent e)
	{
		Salir.general(this);
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