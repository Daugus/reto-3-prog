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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import clases.Pendiente;
import clases.Reparacion;
import clases.Vehiculo;
import funciones.Archivos;
import funciones.General;
import funciones.Salir;
import funciones.Tablas;
import navegacion.Inicio;

/**
 * esta clase difiene la ventana generar factura
 * @author Grupo 2
 * @version 2.0.1
 */
public class GenerarFactura extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private JButton btnVolver;
	private JButton btnGenerar;

	private JLabel lblCodigoTxt;

	private JTable tblCliente;
	private JTable tblVehiculo;
	private JTable tblReparaciones;
	private JTable tblMateriales;
	
	private Pendiente pendiente;

	private ArrayList<Reparacion> alReparaciones = new ArrayList<Reparacion>();
	private ArrayList<MaterialUsado> alMateriales = new ArrayList<MaterialUsado>();

	private JCheckBox chkAprobar;
	/**
	 * constructor que carga la ventana GenerarFactura
	 */
	public GenerarFactura()
	{
		setResizable(false);
		setTitle("Generar factura | " + Inicio.cuentaActual.getNombre());
		
		setBounds(100, 100, 790, 510);
		getContentPane().setPreferredSize(new Dimension(790, 510));
		pack();
		
		setLocationRelativeTo(null);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
	
		btnGenerar = new JButton("Generar factura");
		btnGenerar.setBounds(403, 460, 180, 40);
		panelPrincipal.add(btnGenerar);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(207, 460, 180, 40);
		panelPrincipal.add(btnVolver);

		JLabel lblCodigo = new JLabel("Código orden primaria:");
		lblCodigo.setBounds(10, 185, 150, 35);
		panelPrincipal.add(lblCodigo);
		
		lblCodigoTxt = new JLabel("");
		lblCodigoTxt.setBounds(160, 185, 250, 35);
		panelPrincipal.add(lblCodigoTxt);

		chkAprobar = new JCheckBox("¿Factura aprobada?");
		chkAprobar.setBounds(595, 470, 150, 23);
		panelPrincipal.add(chkAprobar);

		// ===== barras de desplazamiento =====
		// --- reparaciones ---
		JScrollPane scrollReparaciones = new JScrollPane();
		scrollReparaciones.setBounds(10, 235, 420, 200);
		panelPrincipal.add(scrollReparaciones);

		// --- piezas ---
		JScrollPane scrollMateriales = new JScrollPane();
		scrollMateriales.setBounds(445, 235, 335, 200);
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
			/**
			 * 
			 * @param row  cuyo valor se va a consultar
			 * @param column  cuyo valor se va a consultar
			 * @return Devuelve verdadero si la celda en la fila y la columna es editable. De lo contrario,
			 *  invocar setValueAt en la celda no tendrá ningún efecto.
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

		tblVehiculo = new JTable(dtmVehiculo)
		{
			private static final long serialVersionUID = -3909141556237115067L;
			/**
			 * 
			 * @param row  cuyo valor se va a consultar
			 * @param column  cuyo valor se va a consultar
			 * @return Devuelve verdadero si la celda en la fila y la columna es editable. De lo contrario,
			 *  invocar setValueAt en la celda no tendrá ningún efecto.
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
			 * @param row  cuyo valor se va a consultar
			 * @param column  cuyo valor se va a consultar
			 * @return Devuelve verdadero si la celda en la fila y la columna es editable. De lo contrario,
			 *  invocar setValueAt en la celda no tendrá ningún efecto.
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
			 * @param row  cuyo valor se va a consultar
			 * @param column  cuyo valor se va a consultar
			 * @return Devuelve verdadero si la celda en la fila y la columna es editable. De lo contrario,
			 *  invocar setValueAt en la celda no tendrá ningún efecto.
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
		btnGenerar.addActionListener(this);
		chkAprobar.addActionListener(this);

		// ===== ajustes de usuario =====
		// --- fuente y color ---
		panelPrincipal.setBackground(Inicio.colorFondo);

		lblCodigo.setFont(Inicio.fuente);
		lblCodigo.setForeground(Inicio.colorFuente);
		lblCodigoTxt.setFont(Inicio.fuente);
		lblCodigoTxt.setForeground(Inicio.colorFuente);
		
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

		scrollReparaciones.setBackground(Inicio.colorFondoObjetos);
		scrollMateriales.setBackground(Inicio.colorFondoObjetos);
		
		// --- tablas verticales ---
		tblCliente.setFont(Inicio.fuente);
		tblVehiculo.setFont(Inicio.fuente);

		tblCliente.setBackground(Inicio.colorFondoObjetos);
		tblVehiculo.setBackground(Inicio.colorFondoObjetos);

		tblCliente.setForeground(Inicio.colorFuenteObjetos);
		tblVehiculo.setForeground(Inicio.colorFuenteObjetos);
		
		chkAprobar.setFont(Inicio.fuenteObjetos);
		chkAprobar.setBackground(Inicio.colorFondoObjetos);
		chkAprobar.setForeground(Inicio.colorFuenteObjetos);
	}
	/**
	 * carga datos de una orden pendiente pasado como parametro
	 * @param op objeto ordenPendiente 
	 * @see Pendiente
	 */
	public void cargarDatos(Pendiente op)
	{
		pendiente = new Pendiente(op);

		// ===== datos cliente =====
		// --- cargar cliente ---
		Cliente c = pendiente.getPropietario();
				
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
		Vehiculo v = pendiente.getVehiculo();

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

		// ===== datos orden pendiente =====
		lblCodigoTxt.setText(pendiente.getCodigo());
		
		// ===== datos reparaciones =====
		// --- cargar reparaciones  ---
		alReparaciones = pendiente.getReparaciones();
		
		// --- escribir reparaciones y materiales usados ---
		alReparaciones.sort(Comparator.naturalOrder());

		DefaultTableModel dtmReparaciones = (DefaultTableModel) tblReparaciones.getModel();

		for (Reparacion r : alReparaciones)
		{
			dtmReparaciones.addRow(new Object[] {r.getDescripcion(), r.getHoras(), General.formatear(r.getManoObra())});
			alMateriales.addAll(r.getMaterialesUsados());
		}
		
		Tablas.ajustarColumnas(tblReparaciones);
		
		alMateriales.sort(Comparator.naturalOrder());

		DefaultTableModel dtmMateriales = (DefaultTableModel) tblMateriales.getModel();
		
		for (MaterialUsado mu : alMateriales)
		{
			dtmMateriales.addRow(new Object[] {mu.getNombre(), General.formatear(mu.getPrecio()), mu.getCantidad()});
		}
				
		// ===== estilizar tablas =====
		Tablas.vertical(tblCliente);
		Tablas.vertical(tblVehiculo);
		
		// ===== deshabilitar botón de generar factura =====
		btnGenerar.setEnabled(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();

		if (o == chkAprobar)
		{
			btnGenerar.setEnabled(chkAprobar.isSelected());
		}
		else
		{
			if (o == btnGenerar)
			{
				Archivos.borrarPendiente(pendiente.getCodigo());
				
				Archivos.guardarFactura(new Factura(pendiente));
				
				JOptionPane.showMessageDialog(this, (String) "Se ha generado la factura", "INFO",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
			ListaPendientes lp = new ListaPendientes();
			lp.setVisible(true);
			
			this.dispose();
		}
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