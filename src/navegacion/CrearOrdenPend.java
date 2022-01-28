package navegacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

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
	
	private DefaultListModel<String> dlmReparaciones;
	private DefaultListModel<String> dlmPiezas;
	private JList<String> lstReparaciones;
	private JList<String> lstPiezas;
	
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

	public CrearOrdenPend()
	{
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Crear orden pendiente");
		
		setBounds(100, 100, 843, 827);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Inicio.colorFondo);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
	
		/**
		 * Boton implementado con Action Listener
		 */
		btnGenerar = new JButton("Generar factura");
		btnGenerar.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		btnGenerar.setBounds(10, 681, 186, 31);
		panelPrincipal.add(btnGenerar);
		/**
		 * Boton implementado con Action Listener
		 */
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font(Inicio.fuente, Font.PLAIN, 14));
		btnVolver.setBounds(10, 732, 186, 31);
		panelPrincipal.add(btnVolver);
		
		JLabel lblCodigo = new JLabel("Código orden primaria:");
		lblCodigo.setForeground(Inicio.colorFuente);
		lblCodigo.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblCodigo.setBounds(10, 239, 157, 19);
		panelPrincipal.add(lblCodigo);
		
		lblCodigoTxt = new JLabel("");
		lblCodigoTxt.setForeground(Inicio.colorFuente);
		lblCodigoTxt.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblCodigoTxt.setBounds(171, 239, 250, 19);
		panelPrincipal.add(lblCodigoTxt);
		
		JLabel lblMatricula = new JLabel("Matrícula:");
		lblMatricula.setForeground(Inicio.colorFuente);
		lblMatricula.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblMatricula.setBounds(466, 11, 66, 13);
		panelPrincipal.add(lblMatricula);
		
		JLabel lblBastidor = new JLabel("Nº bastidor:");
		lblBastidor.setForeground(Inicio.colorFuente);
		lblBastidor.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblBastidor.setBounds(466, 34, 82, 13);
		panelPrincipal.add(lblBastidor);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setForeground(Inicio.colorFuente);
		lblMarca.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblMarca.setBounds(466, 58, 54, 13);
		panelPrincipal.add(lblMarca);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setForeground(Inicio.colorFuente);
		lblModelo.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblModelo.setBounds(466, 81, 54, 13);
		panelPrincipal.add(lblModelo);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setForeground(Inicio.colorFuente);
		lblTipo.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblTipo.setBounds(466, 104, 39, 19);
		panelPrincipal.add(lblTipo);
		
		JLabel lblCilindrada = new JLabel("Cilindrada:");
		lblCilindrada.setForeground(Inicio.colorFuente);
		lblCilindrada.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblCilindrada.setBounds(466, 133, 82, 13);
		panelPrincipal.add(lblCilindrada);
		
		JLabel lblFechaITV = new JLabel("ITV:");
		lblFechaITV.setForeground(Inicio.colorFuente);
		lblFechaITV.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblFechaITV.setBounds(466, 156, 39, 13);
		panelPrincipal.add(lblFechaITV);
		
		JLabel lblColor = new JLabel("Color:");
		lblColor.setForeground(Inicio.colorFuente);
		lblColor.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblColor.setBounds(466, 179, 47, 13);
		panelPrincipal.add(lblColor);
		
		JLabel lblKmRecorridos = new JLabel("KM Recorridos:");
		lblKmRecorridos.setForeground(Inicio.colorFuente);
		lblKmRecorridos.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblKmRecorridos.setBounds(466, 203, 102, 19);
		panelPrincipal.add(lblKmRecorridos);
		
		lblMatriculaTxt = new JLabel("");
		lblMatriculaTxt.setForeground(Inicio.colorFuente);
		lblMatriculaTxt.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblMatriculaTxt.setBounds(542, 13, 203, 13);
		panelPrincipal.add(lblMatriculaTxt);
		
		lblBastidorTxt = new JLabel("");
		lblBastidorTxt.setForeground(Inicio.colorFuente);
		lblBastidorTxt.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblBastidorTxt.setBounds(552, 36, 203, 13);
		panelPrincipal.add(lblBastidorTxt);
		
		lblMarcaTxt = new JLabel("");
		lblMarcaTxt.setForeground(Inicio.colorFuente);
		lblMarcaTxt.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblMarcaTxt.setBounds(522, 54, 203, 19);
		panelPrincipal.add(lblMarcaTxt);
		
		lblModeloTxt = new JLabel("");
		lblModeloTxt.setForeground(Inicio.colorFuente);
		lblModeloTxt.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblModeloTxt.setBounds(511, 81, 203, 13);
		panelPrincipal.add(lblModeloTxt);
		
		lblTipoTxt = new JLabel("");
		lblTipoTxt.setForeground(Inicio.colorFuente);
		lblTipoTxt.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblTipoTxt.setBounds(503, 109, 197, 13);
		panelPrincipal.add(lblTipoTxt);
		
		lblCilindradaTxt = new JLabel("");
		lblCilindradaTxt.setForeground(Inicio.colorFuente);
		lblCilindradaTxt.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblCilindradaTxt.setBounds(542, 135, 203, 13);
		panelPrincipal.add(lblCilindradaTxt);
		
		lblFechaITVTxt = new JLabel("");
		lblFechaITVTxt.setForeground(Inicio.colorFuente);
		lblFechaITVTxt.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblFechaITVTxt.setBounds(503, 158, 203, 13);
		panelPrincipal.add(lblFechaITVTxt);
		
		lblColorTxt = new JLabel("");
		lblColorTxt.setForeground(Inicio.colorFuente);
		lblColorTxt.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblColorTxt.setBounds(511, 179, 203, 13);
		panelPrincipal.add(lblColorTxt);
		
		lblKmRecorridosTxt = new JLabel("");
		lblKmRecorridosTxt.setForeground(Inicio.colorFuente);
		lblKmRecorridosTxt.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblKmRecorridosTxt.setBounds(573, 203, 203, 19);
		panelPrincipal.add(lblKmRecorridosTxt);
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setForeground(Inicio.colorFuente);
		lblDNI.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblDNI.setBounds(10, 11, 45, 19);
		panelPrincipal.add(lblDNI);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Inicio.colorFuente);
		lblNombre.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblNombre.setBounds(10, 33, 68, 19);
		panelPrincipal.add(lblNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setForeground(Inicio.colorFuente);
		lblApellidos.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblApellidos.setBounds(10, 50, 68, 31);
		panelPrincipal.add(lblApellidos);
		
		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setForeground(Inicio.colorFuente);
		lblTelefono.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblTelefono.setBounds(10, 74, 68, 31);
		panelPrincipal.add(lblTelefono);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha nacimiento:");
		lblFechaNacimiento.setForeground(Inicio.colorFuente);
		lblFechaNacimiento.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblFechaNacimiento.setBounds(10, 104, 123, 15);
		panelPrincipal.add(lblFechaNacimiento);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(Inicio.colorFuente);
		lblEmail.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblEmail.setBounds(8, 169, 51, 15);
		panelPrincipal.add(lblEmail);
		
		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setForeground(Inicio.colorFuente);
		lblDireccion.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblDireccion.setBounds(8, 208, 68, 19);
		panelPrincipal.add(lblDireccion);
		
		lblDNITxt = new JLabel("");
		lblDNITxt.setForeground(Inicio.colorFuente);
		lblDNITxt.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblDNITxt.setBounds(46, 11, 123, 20);
		panelPrincipal.add(lblDNITxt);
		
		lblNombreTxt = new JLabel("");
		lblNombreTxt.setForeground(Inicio.colorFuente);
		lblNombreTxt.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblNombreTxt.setBounds(79, 33, 152, 20);
		panelPrincipal.add(lblNombreTxt);
		
		lblApellidosTxt = new JLabel("");
		lblApellidosTxt.setForeground(Inicio.colorFuente);
		lblApellidosTxt.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblApellidosTxt.setBounds(79, 50, 187, 31);
		panelPrincipal.add(lblApellidosTxt);
		
		lblTelefonoTxt = new JLabel("");
		lblTelefonoTxt.setForeground(Inicio.colorFuente);
		lblTelefonoTxt.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblTelefonoTxt.setBounds(79, 74, 123, 31);
		panelPrincipal.add(lblTelefonoTxt);
		
		lblFechaNacimientoTxt = new JLabel("");
		lblFechaNacimientoTxt.setForeground(Inicio.colorFuente);
		lblFechaNacimientoTxt.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblFechaNacimientoTxt.setBounds(133, 99, 152, 25);
		panelPrincipal.add(lblFechaNacimientoTxt);
		
		lblEmailTxt = new JLabel("");
		lblEmailTxt.setForeground(Inicio.colorFuente);
		lblEmailTxt.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblEmailTxt.setBounds(55, 159, 230, 33);
		panelPrincipal.add(lblEmailTxt);
		
		lblDireccionTxt = new JLabel("");
		lblDireccionTxt.setForeground(Inicio.colorFuente);
		lblDireccionTxt.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblDireccionTxt.setBounds(77, 208, 142, 20);
		panelPrincipal.add(lblDireccionTxt);
		
		JLabel lblPortal = new JLabel("");
		lblPortal.setForeground(Inicio.colorFuente);
		lblPortal.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblPortal.setBounds(231, 164, 33, 20);
		panelPrincipal.add(lblPortal);
		
		JLabel lblPiso = new JLabel("");
		lblPiso.setForeground(Inicio.colorFuente);
		lblPiso.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblPiso.setBounds(274, 164, 33, 20);
		panelPrincipal.add(lblPiso);
		
		JLabel lblPuerta = new JLabel("");
		lblPuerta.setForeground(Inicio.colorFuente);
		lblPuerta.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblPuerta.setBounds(317, 164, 119, 20);
		panelPrincipal.add(lblPuerta);
		
		lstReparaciones = new JList<String>();
		lstReparaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		lstPiezas = new JList<String>();
		lstPiezas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		lblComentarioTxt = new JLabel("");
		lblComentarioTxt.setForeground(Inicio.colorFuente);
		lblComentarioTxt.setBounds(10, 300, 817, 159);
		panelPrincipal.add(lblComentarioTxt);
		
		JLabel lblFechaAlta = new JLabel("Fecha alta:");
		lblFechaAlta.setForeground(Inicio.colorFuente);
		lblFechaAlta.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblFechaAlta.setBounds(10, 138, 123, 15);
		panelPrincipal.add(lblFechaAlta);
		
		lblFechaAltaTxt = new JLabel("");
		lblFechaAltaTxt.setForeground(Inicio.colorFuente);
		lblFechaAltaTxt.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblFechaAltaTxt.setBounds(133, 133, 152, 25);
		panelPrincipal.add(lblFechaAltaTxt);
		
		JLabel lblComentario = new JLabel("Comentario:");
		lblComentario.setForeground(Inicio.colorFuente);
		lblComentario.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblComentario.setBounds(10, 269, 157, 19);
		panelPrincipal.add(lblComentario);

		// ===== barras de desplazamiento =====
		// --- reparaciones ---
		JScrollPane scrollReparaciones = new JScrollPane();
		scrollReparaciones.setBounds(34, 470, 358, 159);
		panelPrincipal.add(scrollReparaciones);

		scrollReparaciones.setViewportView(lstReparaciones);

		// --- piezas ---
		JScrollPane scrollPiezas = new JScrollPane();
		scrollPiezas.setBounds(450, 470, 358, 159);
		panelPrincipal.add(scrollPiezas);

		scrollPiezas.setViewportView(lstPiezas);

		// ===== modelos =====
		// --- crear ---
		dlmReparaciones = new DefaultListModel<String>();
		dlmPiezas = new DefaultListModel<String>();
		
		// --- asignar ---
		lstReparaciones.setModel(dlmReparaciones);
		lstPiezas.setModel(dlmPiezas);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		btnVolver.addActionListener(this);
		btnGenerar.addActionListener(this);
	}
	
	public void cargarDatos()
	{
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

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();

		if (o == btnGenerar)
		{
			
		}
		else
		{
			this.dispose();
		}
	}

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