package navegacion;

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
	
	// ===== datos OrdenPrim =====
	private JLabel lblCodigoTxt;
	private JLabel lblComentarioTxt;

	// --- Cliente ---
	private JLabel lblDNITxt;

	private JLabel lblNombreTxt;
	private JLabel lblApellidosTxt;

	private JLabel lblTelefonoTxt;
	private JLabel lblEmailTxt;

	private JLabel lblFechaNacimientoTxt;

	private JLabel lblDireccionTxt;

	private JLabel lblFechaAltaTxt;

	// --- Vehiculo ---
	private JLabel lblMatriculaTxt;
	private JLabel lblBastidorTxt;
	
	private JLabel lblMarcaTxt;
	private JLabel lblModeloTxt;
	private JLabel lblColorTxt;

	private JLabel lblCilindradaTxt;

	private JLabel lblKmRecorridosTxt;
	private JLabel lblFechaITVTxt;

	private JLabel lblTipoTxt;

	private JTable tblReparaciones;
	private static JTable tblMateriales;

	public CrearOrdenPend()
	{
		setResizable(false);
		setTitle("Crear orden pendiente");
		
		setBounds(100, 100, 843, 827);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
	
		/**
		 * Boton implementado con Action Listener
		 */
		btnGenerar = new JButton("Validar orden");
		btnGenerar.setBounds(10, 681, 186, 31);
		panelPrincipal.add(btnGenerar);
		/**
		 * Boton implementado con Action Listener
		 */
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 733, 186, 31);
		panelPrincipal.add(btnVolver);

		btnAgregar = new JButton("Agregar materiales");
		btnAgregar.setBounds(450, 654, 186, 31);
		panelPrincipal.add(btnAgregar);
		
		JLabel lblCodigo = new JLabel("Código orden primaria:");
		lblCodigo.setBounds(10, 239, 157, 19);
		panelPrincipal.add(lblCodigo);
		
		lblCodigoTxt = new JLabel("");
		lblCodigoTxt.setBounds(171, 239, 250, 19);
		panelPrincipal.add(lblCodigoTxt);
		
		JLabel lblMatricula = new JLabel("Matrícula:");
		lblMatricula.setBounds(466, 11, 66, 13);
		panelPrincipal.add(lblMatricula);
		
		JLabel lblBastidor = new JLabel("Nº bastidor:");
		lblBastidor.setBounds(466, 34, 82, 13);
		panelPrincipal.add(lblBastidor);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(466, 58, 54, 13);
		panelPrincipal.add(lblMarca);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(466, 81, 54, 13);
		panelPrincipal.add(lblModelo);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(466, 104, 39, 19);
		panelPrincipal.add(lblTipo);
		
		JLabel lblCilindrada = new JLabel("Cilindrada:");
		lblCilindrada.setBounds(466, 133, 82, 13);
		panelPrincipal.add(lblCilindrada);
		
		JLabel lblFechaITV = new JLabel("ITV:");
		lblFechaITV.setBounds(466, 156, 39, 13);
		panelPrincipal.add(lblFechaITV);
		
		JLabel lblColor = new JLabel("Color:");
		lblColor.setBounds(466, 179, 47, 13);
		panelPrincipal.add(lblColor);
		
		JLabel lblKmRecorridos = new JLabel("KM Recorridos:");
		lblKmRecorridos.setBounds(466, 203, 102, 19);
		panelPrincipal.add(lblKmRecorridos);
		
		lblMatriculaTxt = new JLabel("");
		lblMatriculaTxt.setBounds(542, 13, 203, 13);
		panelPrincipal.add(lblMatriculaTxt);
		
		lblBastidorTxt = new JLabel("");
		lblBastidorTxt.setBounds(552, 36, 203, 13);
		panelPrincipal.add(lblBastidorTxt);
		
		lblMarcaTxt = new JLabel("");
		lblMarcaTxt.setBounds(522, 54, 203, 19);
		panelPrincipal.add(lblMarcaTxt);
		
		lblModeloTxt = new JLabel("");
		lblModeloTxt.setBounds(511, 81, 203, 13);
		panelPrincipal.add(lblModeloTxt);
		
		lblTipoTxt = new JLabel("");
		lblTipoTxt.setBounds(503, 109, 197, 13);
		panelPrincipal.add(lblTipoTxt);
		
		lblCilindradaTxt = new JLabel("");
		lblCilindradaTxt.setBounds(542, 135, 203, 13);
		panelPrincipal.add(lblCilindradaTxt);
		
		lblFechaITVTxt = new JLabel("");
		lblFechaITVTxt.setBounds(503, 158, 203, 13);
		panelPrincipal.add(lblFechaITVTxt);
		
		lblColorTxt = new JLabel("");
		lblColorTxt.setBounds(511, 179, 203, 13);
		panelPrincipal.add(lblColorTxt);
		
		lblKmRecorridosTxt = new JLabel("");
		lblKmRecorridosTxt.setBounds(573, 203, 203, 19);
		panelPrincipal.add(lblKmRecorridosTxt);
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setBounds(10, 11, 45, 19);
		panelPrincipal.add(lblDNI);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 33, 68, 19);
		panelPrincipal.add(lblNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(10, 50, 68, 31);
		panelPrincipal.add(lblApellidos);
		
		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setBounds(10, 74, 68, 31);
		panelPrincipal.add(lblTelefono);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha nacimiento:");
		lblFechaNacimiento.setBounds(10, 104, 123, 15);
		panelPrincipal.add(lblFechaNacimiento);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(8, 169, 51, 15);
		panelPrincipal.add(lblEmail);
		
		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setBounds(8, 208, 68, 19);
		panelPrincipal.add(lblDireccion);
		
		lblDNITxt = new JLabel("");
		lblDNITxt.setBounds(46, 11, 123, 20);
		panelPrincipal.add(lblDNITxt);
		
		lblNombreTxt = new JLabel("");
		lblNombreTxt.setBounds(79, 33, 152, 20);
		panelPrincipal.add(lblNombreTxt);
		
		lblApellidosTxt = new JLabel("");
		lblApellidosTxt.setBounds(79, 50, 187, 31);
		panelPrincipal.add(lblApellidosTxt);
		
		lblTelefonoTxt = new JLabel("");
		lblTelefonoTxt.setBounds(79, 74, 123, 31);
		panelPrincipal.add(lblTelefonoTxt);
		
		lblFechaNacimientoTxt = new JLabel("");
		lblFechaNacimientoTxt.setBounds(133, 99, 152, 25);
		panelPrincipal.add(lblFechaNacimientoTxt);
		
		lblEmailTxt = new JLabel("");
		lblEmailTxt.setBounds(55, 159, 230, 33);
		panelPrincipal.add(lblEmailTxt);
		
		lblDireccionTxt = new JLabel("");
		lblDireccionTxt.setBounds(77, 208, 250, 20);
		panelPrincipal.add(lblDireccionTxt);
		
		JLabel lblPortal = new JLabel("");
		lblPortal.setBounds(231, 164, 33, 20);
		panelPrincipal.add(lblPortal);
		
		JLabel lblPiso = new JLabel("");
		lblPiso.setBounds(274, 164, 33, 20);
		panelPrincipal.add(lblPiso);
		
		JLabel lblPuerta = new JLabel("");
		lblPuerta.setBounds(317, 164, 119, 20);
		panelPrincipal.add(lblPuerta);
		
		lblComentarioTxt = new JLabel("");
		lblComentarioTxt.setBounds(10, 300, 817, 159);
		panelPrincipal.add(lblComentarioTxt);
		
		JLabel lblFechaAlta = new JLabel("Fecha alta:");
		lblFechaAlta.setBounds(10, 138, 123, 15);
		panelPrincipal.add(lblFechaAlta);
		
		lblFechaAltaTxt = new JLabel("");
		lblFechaAltaTxt.setBounds(133, 133, 152, 25);
		panelPrincipal.add(lblFechaAltaTxt);
		
		JLabel lblComentario = new JLabel("Comentario:");
		lblComentario.setBounds(10, 269, 157, 19);
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
		scrollReparaciones.setBounds(34, 470, 358, 159);
		panelPrincipal.add(scrollReparaciones);
		
		scrollReparaciones.setViewportView(tblReparaciones);

		// --- piezas ---
		JScrollPane scrollMateriales = new JScrollPane();
		scrollMateriales.setBounds(450, 470, 358, 159);
		panelPrincipal.add(scrollMateriales);
		
		scrollMateriales.setViewportView(tblMateriales);

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
		etiquetas.addAll(Arrays.asList(lblCodigoTxt, lblComentarioTxt,
				lblDNITxt, lblNombreTxt, lblApellidosTxt,
				lblTelefonoTxt, lblEmailTxt,
				lblFechaNacimientoTxt, lblDireccionTxt, lblFechaAltaTxt,
				lblMatriculaTxt, lblBastidorTxt,
				lblMarcaTxt, lblModeloTxt, lblColorTxt,	lblCilindradaTxt,
				lblKmRecorridosTxt,	lblFechaITVTxt,	lblTipoTxt,

				lblCodigo, lblComentario,
				lblDNI, lblNombre, lblApellidos,
				lblTelefono, lblEmail,
				lblFechaNacimiento, lblDireccion, lblFechaAlta,
				lblMatricula, lblBastidor,
				lblMarca, lblModelo, lblColor, lblCilindrada,
				lblKmRecorridos, lblFechaITV, lblTipo));
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
		lblDNITxt.setText(c.getDNI());

		lblNombreTxt.setText(c.getNombre());
		lblApellidosTxt.setText(c.getApellidos());

		lblTelefonoTxt.setText(String.valueOf(c.getTelefono()));
		lblEmailTxt.setText(c.getEmail());

		lblFechaNacimientoTxt.setText(String.valueOf(c.getFechaNacimiento()));

		lblDireccionTxt.setText(String.valueOf(c.getDireccion()));
		
		// ===== datos vehículo =====
		// --- cargar vehículo ---
		Vehiculo v = ListaOrdenesPrim.getOrdenPrim().getVehiculo();

		// --- escribir vehículo ---
		lblMatriculaTxt.setText(v.getMatricula());
		lblBastidorTxt.setText(v.getBastidor());

		lblMarcaTxt.setText(v.getMarca());
		lblModeloTxt.setText(v.getModelo());
		lblColorTxt.setText(v.getColor());

		lblCilindradaTxt.setText(String.valueOf(v.getCilindrada()));

		lblKmRecorridosTxt.setText(String.valueOf(v.getKmRecorridos()));
		lblFechaITVTxt.setText(String.valueOf(v.getFechaITV()));

		lblTipoTxt.setText(v.getTipo());
				
		// --- escribir OrdenPrim ---
		lblCodigoTxt.setText(ListaOrdenesPrim.getOrdenPrim().getCodOrdenPrim());
		lblComentarioTxt.setText(ListaOrdenesPrim.getOrdenPrim().getComentarios());
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