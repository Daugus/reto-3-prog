package navegacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.Material;
import clases.Vehiculo;
import funciones.Archivos;
import funciones.Tablas;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class AsignarMaterial extends JFrame implements ActionListener, WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private static JTable tblMaterial;
	private JComboBox<String> combMaterial;
	private DefaultComboBoxModel<String> dcbmMaterial;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnValidar;
	private Material material;
	private JTextField textCantidad;

	/**
	 * Crear la frame de la clase.
	 */
	public AsignarMaterial() {
		setTitle("Materiales");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 300);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Inicio.colorFondo);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		combMaterial = new JComboBox();
		combMaterial.addItem("Oscuro");
		combMaterial.addItem("Claro");
		combMaterial.setBounds(38, 26, 184, 33);
		panelPrincipal.add(combMaterial);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(428, 26, 98, 33);
		panelPrincipal.add(btnAgregar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(552, 26, 98, 33);
		panelPrincipal.add(btnEliminar);

		Panel panel_footer = new Panel();
		panel_footer.setBounds(38, 201, 612, 62);
		panelPrincipal.add(panel_footer);
		panel_footer.setLayout(null);

		btnValidar = new JButton("Validar");
		btnValidar.setBounds(201, 10, 138, 42);
		panel_footer.add(btnValidar);

		// ==== Barras de desplazamiento ====
		JScrollPane scrollMateriales = new JScrollPane();
		scrollMateriales.setBounds(38, 69, 613, 113);
		panelPrincipal.add(scrollMateriales);

		// ====== Modelos ======
		dcbmMaterial = new DefaultComboBoxModel<String>();
		dcbmMaterial.addAll(Archivos.listarMateriales());
		DefaultTableModel dtmMaterial = new DefaultTableModel();
		dtmMaterial.addColumn("Nombre");
		dtmMaterial.addColumn("Precio");
		dtmMaterial.addColumn("Cantidad");
		

		// ====== Asignar =======
		combMaterial.setModel(dcbmMaterial);
		
		

		

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		btnEliminar.addActionListener(this);
		btnAgregar.addActionListener(this);
		btnValidar.addActionListener(this);

		combMaterial.setFont(Inicio.fuenteObjetos);

		btnEliminar.setFont(Inicio.fuenteObjetos);
		btnAgregar.setFont(Inicio.fuenteObjetos);
		btnValidar.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		combMaterial.setBackground(Inicio.colorFondoObjetos);
		btnEliminar.setBackground(Inicio.colorFondoObjetos);
		btnAgregar.setBackground(Inicio.colorFondoObjetos);
		btnValidar.setBackground(Inicio.colorFondoObjetos);

		scrollMateriales.setBackground(Inicio.colorFondoObjetos);

		tblMaterial = new JTable(dtmMaterial) {
			private static final long serialVersionUID = -6533314169471135820L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tblMaterial.setFillsViewportHeight(true);
		tblMaterial.getTableHeader().setReorderingAllowed(false);
		tblMaterial.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblMaterial.getTableHeader().setBackground(Inicio.colorFondoObjetos);
		tblMaterial.getTableHeader().setFont(Inicio.fuenteObjetos);
		
		//actualizarTabla();
		
		scrollMateriales.setViewportView(tblMaterial);
		// ===== ajustes de usuario =====
		// --- fuente ---
		tblMaterial.setFont(Inicio.fuente);

		tblMaterial.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		tblMaterial.setForeground(Inicio.colorFuenteObjetos);

		// ajustar tamaño de columnas según contenido
		Tablas.ajustarColumnas(tblMaterial);
		combMaterial.setForeground(Inicio.colorFuenteObjetos);
		btnEliminar.setForeground(Inicio.colorFuenteObjetos);
		btnAgregar.setForeground(Inicio.colorFuenteObjetos);
		btnValidar.setForeground(Inicio.colorFuenteObjetos);
		
		JLabel lblNewLabel = new JLabel("Cantidad");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(252, 26, 69, 33);
		panelPrincipal.add(lblNewLabel);
		
		textCantidad = new JTextField();
		textCantidad.setBounds(329, 26, 83, 33);
		panelPrincipal.add(textCantidad);
		textCantidad.setColumns(10);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o == btnAgregar) {
			int cantidad= Integer.parseInt(textCantidad.getText());
			
		}

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	
}
