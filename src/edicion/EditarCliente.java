package edicion;

import java.awt.Color;
import java.awt.Component;
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
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import administracion.AdministrarClientes;
import clases.Cliente;
import clases.Direccion;
import clases.Fecha;
import clases.OrdenTabulacion;
import clases.Vehiculo;
import funciones.Archivos;
import funciones.Salir;
import navegacion.Inicio;
import javax.swing.SwingConstants;

/**
 * esta clase edita datos asignadoo al cliente
 * @author Grupo 2
 * @version 2.0.1
 */
public class EditarCliente extends JFrame implements ActionListener, FocusListener, WindowListener
{
	private static final long serialVersionUID = 1531539371445418371L;
	
	private JPanel panelPrincipal;

	private JButton btnCancelar;
	private JButton btnGuardar;
	private JButton btnEliminarMatricula;
	private JButton btnAgregarMatricula;

	private JTextField txtDNI;
	private JTextField txtNombre;
	private JTextField txtApellidos;

	private JTextField txtTel;
	private JTextField txtEmail;

	private JTextField txtFechaNacimientoD;
	private JTextField txtFechaNacimientoM;
	private JTextField txtFechaNacimientoA;

	private JTextField txtCodPostal;
	private JTextField txtCalle;
	private JTextField txtPortal;
	private JTextField txtPiso;
	private JTextField txtPuerta;
	
	private JTextField txtFechaAltaD;
	private JTextField txtFechaAltaM;
	private JTextField txtFechaAltaA;
	
	private JTextField txtMatricula;
	public DefaultComboBoxModel<String> dcbmVehiculos;
	private JComboBox<String> cmbVehiculos;
	
	private ArrayList<String> alMatriculasBorradas = new ArrayList<String>();
	private Vector<Component> vectorOrden;
	private boolean edicion;
	/**
	 * constructor añade los elementos de la ventana
	 *  
	 */
	public EditarCliente() {
		setResizable(false);
		setTitle("Agregar nuevo cliente | " + Inicio.cuentaActual.getNombre());
		
		setBounds(100, 100, 730, 480);
		getContentPane().setPreferredSize(new Dimension(730, 480));
		pack();
		
		setLocationRelativeTo(null);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Inicio.colorFondo);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(177, 415, 180, 40);
		panelPrincipal.add(btnCancelar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(373, 415, 180, 40);
		panelPrincipal.add(btnGuardar);
		
		JLabel lblPuerta = new JLabel("Puerta:");
		lblPuerta.setHorizontalAlignment(SwingConstants.LEFT);
		lblPuerta.setBounds(380, 250, 150, 35);
		panelPrincipal.add(lblPuerta);
		
		txtPuerta = new JTextField();
		txtPuerta.setColumns(10);
		txtPuerta.setBounds(530, 250, 150, 35);
		panelPrincipal.add(txtPuerta);
		
		JLabel lblPiso = new JLabel("Piso:");
		lblPiso.setHorizontalAlignment(SwingConstants.LEFT);
		lblPiso.setBounds(380, 205, 150, 35);
		panelPrincipal.add(lblPiso);
		
		txtPiso = new JTextField();
		txtPiso.setColumns(10);
		txtPiso.setBounds(530, 205, 150, 35);
		panelPrincipal.add(txtPiso);
		
		JLabel lblPortal = new JLabel("Nº Portal:");
		lblPortal.setHorizontalAlignment(SwingConstants.LEFT);
		lblPortal.setBounds(380, 160, 150, 35);
		panelPrincipal.add(lblPortal);
		
		txtPortal = new JTextField();
		txtPortal.setColumns(10);
		txtPortal.setBounds(530, 160, 150, 35);
		panelPrincipal.add(txtPortal);
		
		txtCalle = new JTextField();
		txtCalle.setColumns(10);
		txtCalle.setBounds(530, 115, 150, 35);
		panelPrincipal.add(txtCalle);
		
		JLabel lblCalle = new JLabel("Calle:");
		lblCalle.setHorizontalAlignment(SwingConstants.LEFT);
		lblCalle.setBounds(380, 115, 150, 35);
		panelPrincipal.add(lblCalle);
		
		JLabel lblCodPostal = new JLabel("Código Postal:");
		lblCodPostal.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodPostal.setBounds(380, 70, 150, 35);
		panelPrincipal.add(lblCodPostal);
		
		txtCodPostal = new JTextField();
		txtCodPostal.setColumns(10);
		txtCodPostal.setBounds(530, 70, 150, 35);
		panelPrincipal.add(txtCodPostal);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(130, 205, 220, 35);
		panelPrincipal.add(txtEmail);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setBounds(50, 205, 80, 35);
		panelPrincipal.add(lblEmail);
		
		txtMatricula = new JTextField();
		txtMatricula.setColumns(10);
		txtMatricula.setBounds(275, 355, 150, 35);
		panelPrincipal.add(txtMatricula);
		
		btnAgregarMatricula = new JButton("Agregar");
		btnAgregarMatricula.setBounds(435, 355, 120, 35);
		panelPrincipal.add(btnAgregarMatricula);
		
		cmbVehiculos = new JComboBox<String>();
		cmbVehiculos.setBounds(275, 310, 150, 35);
		panelPrincipal.add(cmbVehiculos);
		
		btnEliminarMatricula = new JButton("Eliminar");
		btnEliminarMatricula.setBounds(435, 310, 120, 35);
		panelPrincipal.add(btnEliminarMatricula);
		
		JLabel lblMatricula = new JLabel("Matrícula");
		lblMatricula.setHorizontalAlignment(SwingConstants.LEFT);
		lblMatricula.setBounds(175, 355, 100, 35);
		panelPrincipal.add(lblMatricula);
		
		JLabel lblVehiculos = new JLabel("Vehículos:");
		lblVehiculos.setHorizontalAlignment(SwingConstants.LEFT);
		lblVehiculos.setBounds(175, 310, 100, 35);
		panelPrincipal.add(lblVehiculos);
		
		JLabel lblFechaAlta = new JLabel("Fecha de alta:");
		lblFechaAlta.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaAlta.setBounds(50, 250, 150, 35);
		panelPrincipal.add(lblFechaAlta);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha nacimiento:");
		lblFechaNacimiento.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaNacimiento.setBounds(380, 25, 150, 35);
		panelPrincipal.add(lblFechaNacimiento);
		
		JLabel lblTel = new JLabel("Teléfono:");
		lblTel.setHorizontalAlignment(SwingConstants.LEFT);
		lblTel.setBounds(50, 160, 150, 35);
		panelPrincipal.add(lblTel);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setHorizontalAlignment(SwingConstants.LEFT);
		lblApellidos.setBounds(50, 115, 150, 35);
		panelPrincipal.add(lblApellidos);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(200, 115, 150, 35);
		panelPrincipal.add(txtApellidos);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setBounds(50, 70, 150, 35);
		panelPrincipal.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(200, 70, 150, 35);
		panelPrincipal.add(txtNombre);
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setHorizontalAlignment(SwingConstants.LEFT);
		lblDNI.setBounds(50, 25, 150, 35);
		panelPrincipal.add(lblDNI);
		
		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(200, 25, 150, 35);
		panelPrincipal.add(txtDNI);
		
		txtTel = new JTextField();
		txtTel.setColumns(10);
		txtTel.setBounds(200, 160, 150, 35);
		panelPrincipal.add(txtTel);
		
		txtFechaNacimientoD = new JTextField();
		txtFechaNacimientoD.setColumns(10);
		txtFechaNacimientoD.setBounds(530, 25, 35, 35);
		panelPrincipal.add(txtFechaNacimientoD);
		
		txtFechaAltaD = new JTextField("");
		txtFechaAltaD.setBounds(200, 250, 35, 35);
		panelPrincipal.add(txtFechaAltaD);
		
		txtFechaAltaM = new JTextField("");
		txtFechaAltaM.setBounds(240, 250, 35, 35);
		panelPrincipal.add(txtFechaAltaM);
		
		txtFechaNacimientoM = new JTextField();
		txtFechaNacimientoM.setColumns(10);
		txtFechaNacimientoM.setBounds(570, 25, 35, 35);
		panelPrincipal.add(txtFechaNacimientoM);
		
		txtFechaNacimientoA = new JTextField();
		txtFechaNacimientoA.setColumns(10);
		txtFechaNacimientoA.setBounds(610, 25, 70, 35);
		panelPrincipal.add(txtFechaNacimientoA);
		
		txtFechaAltaA = new JTextField("");
		txtFechaAltaA.setBounds(280, 250, 70, 35);
		panelPrincipal.add(txtFechaAltaA);

		// ===== modelos =====
		// --- crear ---
		dcbmVehiculos = new DefaultComboBoxModel<String>();

		// --- asignar ---
		cmbVehiculos.setModel(dcbmVehiculos);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action && Focus ---
		// - JTextField -
		ArrayList<JTextField> camposTexto = new ArrayList<JTextField>();
		camposTexto.addAll(Arrays.asList(txtDNI, txtNombre, txtApellidos,
				txtTel, txtEmail, txtFechaAltaD, txtFechaAltaM, txtFechaAltaA,
				txtFechaNacimientoD, txtFechaNacimientoM, txtFechaNacimientoA,
				txtCodPostal, txtCalle, txtPortal, txtPiso, txtPuerta,
				txtMatricula));
		for (JTextField txt : camposTexto)
		{
			txt.addActionListener(this);
			txt.addFocusListener(this);
			
			// color del texto cuando el campo está deshabilitado
			txt.setDisabledTextColor(Color.DARK_GRAY);
		}

		// - JButton -
		btnCancelar.addActionListener(this);
		btnGuardar.addActionListener(this);
		btnAgregarMatricula.addActionListener(this);
		btnEliminarMatricula.addActionListener(this);
		
		// ===== ajustes de usuario =====
		// --- fuente y color ---
		panelPrincipal.setBackground(Inicio.colorFondo);

		ArrayList<JLabel> etiquetas = new ArrayList<JLabel>();
		etiquetas.addAll(Arrays.asList(lblDNI, lblNombre, lblApellidos,
				lblTel, lblEmail, lblFechaNacimiento,
				lblCodPostal, lblCalle, lblPortal, lblPiso, lblPuerta,
				lblFechaAlta, lblVehiculos, lblMatricula));
		for (JLabel lbl : etiquetas)
		{
			lbl.setFont(Inicio.fuente);
			lbl.setForeground(Inicio.colorFuente);
		}

		for (JTextField txt : camposTexto)
		{
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

		btnAgregarMatricula.setFont(Inicio.fuenteObjetos);
		btnAgregarMatricula.setBackground(Inicio.colorFondoObjetos);
		btnAgregarMatricula.setForeground(Inicio.colorFuenteObjetos);

		btnEliminarMatricula.setFont(Inicio.fuenteObjetos);
		btnEliminarMatricula.setBackground(Inicio.colorFondoObjetos);
		btnEliminarMatricula.setForeground(Inicio.colorFuenteObjetos);

		cmbVehiculos.setFont(Inicio.fuenteObjetos);
		cmbVehiculos.setBackground(Inicio.colorFondoObjetos);
		cmbVehiculos.setForeground(Inicio.colorFuenteObjetos);

		// ===== orden de tabulación =====
		vectorOrden = new Vector<Component>();
		vectorOrden.addAll(camposTexto);
		vectorOrden.remove(txtMatricula);
		vectorOrden.add(cmbVehiculos);
		vectorOrden.add(txtMatricula);

		OrdenTabulacion orden = new OrdenTabulacion(vectorOrden);
		setFocusTraversalPolicy(orden);
	}
	/**
	 * accede a datos almacenados de cliente pasado como parametro,
	 * habilita siertos campos para poder modificarlos
	 * @param cliente objeto cliente
	 */
	public void modoEdicion(Cliente cliente)
	{
		edicion = true;
		
		setTitle("Editar " + cliente.getDNI() + " | " + Inicio.cuentaActual.getNombre());

		txtDNI.setText(cliente.getDNI());
		txtDNI.setEnabled(false);
		vectorOrden.remove(txtDNI);

		txtNombre.setText(cliente.getNombre());
		txtApellidos.setText(cliente.getApellidos());
		
		txtTel.setText(String.valueOf(cliente.getTelefono()));
		txtEmail.setText(cliente.getEmail());

		txtFechaNacimientoD.setText(String.valueOf(cliente.getFechaNacimiento().getDay()));
		txtFechaNacimientoD.setEnabled(false);
		vectorOrden.remove(txtFechaNacimientoD);
		txtFechaNacimientoM.setText(String.valueOf(cliente.getFechaNacimiento().getMonth()));
		txtFechaNacimientoM.setEnabled(false);
		vectorOrden.remove(txtFechaNacimientoM);
		txtFechaNacimientoA.setText(String.valueOf(cliente.getFechaNacimiento().getYear()));
		txtFechaNacimientoA.setEnabled(false);
		vectorOrden.remove(txtFechaNacimientoA);

		txtFechaAltaD.setText(String.valueOf(cliente.getFechaAlta().getDay()));
		txtFechaAltaD.setEnabled(false);
		vectorOrden.remove(txtFechaAltaD);
		txtFechaAltaM.setText(String.valueOf(cliente.getFechaAlta().getMonth()));
		txtFechaAltaM.setEnabled(false);
		vectorOrden.remove(txtFechaAltaM);
		txtFechaAltaA.setText(String.valueOf(cliente.getFechaAlta().getYear()));
		txtFechaAltaA.setEnabled(false);
		vectorOrden.remove(txtFechaAltaA);

		txtCodPostal.setText(String.valueOf(cliente.getDireccion().getCodPostal()));
		txtCalle.setText(cliente.getDireccion().getCalle());
		txtPortal.setText(String.valueOf(cliente.getDireccion().getPortal()));
		txtPiso.setText(String.valueOf(cliente.getDireccion().getPiso()));
		txtPuerta.setText(cliente.getDireccion().getPuerta());

		dcbmVehiculos.addAll(cliente.getVehiculos());
		dcbmVehiculos.setSelectedItem(cmbVehiculos.getSelectedItem());

		OrdenTabulacion orden = new OrdenTabulacion(vectorOrden);
		setFocusTraversalPolicy(orden);
	}
	
	private boolean guardar()
	{
		try
		{
			String dni = txtDNI.getText();
			
			String nombre = txtNombre.getText();
			String apellidos = txtApellidos.getText();
			
			int tel = Integer.parseInt(txtTel.getText());
			String email = txtEmail.getText();
			
			int dN = Integer.parseInt(txtFechaNacimientoD.getText());
			int mN = Integer.parseInt(txtFechaNacimientoM.getText());
			int aN = Integer.parseInt(txtFechaNacimientoA.getText());
			
			int codPostal = Integer.parseInt(txtCodPostal.getText());
			String calle = txtCalle.getText();
			int portal = Integer.parseInt(txtPortal.getText());
			int piso = Integer.parseInt(txtPiso.getText());
			String puerta = txtPuerta.getText();
			
			Fecha fechaAlta;
			Calendar now = Calendar.getInstance();
			int dA = now.get(Calendar.DATE);
			int mA = now.get(Calendar.MONTH) + 1;
			int aA = now.get(Calendar.YEAR);
			fechaAlta = new Fecha(dA ,mA ,aA);
			
			ArrayList<String> matriculas = new ArrayList<String>();
			for (int i = 0; i < dcbmVehiculos.getSize(); i++)
			{
				matriculas.add(dcbmVehiculos.getElementAt(i));
			}
			
			ArrayList<String> camposTxt = new ArrayList<String>();
			camposTxt.addAll(Arrays.asList(dni, nombre, apellidos, email, calle, puerta));
			
			if (camposTxt.contains(""))
			{
				JOptionPane.showMessageDialog(this, (String) "Campo vacío", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
			else if (tel < 1 || codPostal < 1 || portal < 1 || piso < 1)
			{
				JOptionPane.showMessageDialog(this, (String) "Campo numérico incorrecto", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
			else if (dN < 1 || mN < 1 || aN < 1 || dN > 31 || mN > 12)
			{
				JOptionPane.showMessageDialog(this, (String) "Fecha no válida", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
			else if (matriculas.size() == 0)
			{
				JOptionPane.showMessageDialog(this, (String) "No hay ningún vehículo agregado", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
			else 
			{
				Fecha fechaNacimiento = new Fecha(dN, mN, aN);
				Direccion direccion = new Direccion(codPostal, calle, portal, piso, puerta);
				
				if (!edicion && Archivos.listarClientes().contains(dni))
				{
					JOptionPane.showMessageDialog(this, (String) "Cliente ya existe", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					Archivos.guardarCliente(new Cliente(dni, nombre, apellidos, tel,
							email, fechaNacimiento, direccion, fechaAlta, matriculas));
					
					for (String ma : alMatriculasBorradas)
					{
						Vehiculo v = Archivos.cargarVehiculo(ma);
						v.setPropietario("");
						Archivos.guardarVehiculo(v);
						
						JOptionPane.showMessageDialog(this, (String) "El vehículo con la matrícula " + ma + " ya no tiene un propietario seleccionado", "INFO",
								JOptionPane.INFORMATION_MESSAGE);
					}
					
					for (String ma : matriculas)
					{
						if (!Archivos.listarVehiculos().contains(ma))
						{
							Archivos.guardarVehiculo(new Vehiculo(ma, dni));
						}
						else
						{
							Vehiculo v = Archivos.cargarVehiculo(ma);
							if (!v.getPropietario().equals(dni))
							{
								v.setPropietario(dni);
								Archivos.guardarVehiculo(v);
							}
						}
					}

					return true;
				}
			}
		}
		catch (NumberFormatException npe)
		{
			JOptionPane.showMessageDialog(this, (String) "Campo numérico vacío o incorrecto", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}

		return false;
	}

	/**
	 * invocado cuando una accion ocurre sobre los elementos
	 * @param ae el evento a procesar
	 * @see actionPerformed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o == txtMatricula || o == btnAgregarMatricula)
		{
			String matricula = txtMatricula.getText();
			if (matricula.equals(""))
			{
				JOptionPane.showMessageDialog(this, (String) "Campo de matrícula vacío", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				if (dcbmVehiculos.getIndexOf(matricula) != -1)
				{
					JOptionPane.showMessageDialog(this, (String) "El vehículo ya está agregado", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					dcbmVehiculos.addElement(matricula);
					txtMatricula.setText("");
					cmbVehiculos.setSelectedItem(matricula);
				}
			}
		}
		else if (o == btnEliminarMatricula)
		{
			if (dcbmVehiculos.getSize() > 0)
			{
				String seleccion = (String) cmbVehiculos.getSelectedItem();

				if (cmbVehiculos.getSelectedIndex() >= 0)
				{
					dcbmVehiculos.removeElement(seleccion);
					if (edicion)
					{
						alMatriculasBorradas.add(seleccion);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, (String) "No hay ninguna matrícula seleccionada", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, (String) "No hay matrículas que eliminar", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			int guardar = JOptionPane.YES_OPTION;
			if (o == btnCancelar)
			{
				guardar = Salir.edicion();
			}

			boolean valido = false;
			
			if (guardar == JOptionPane.YES_OPTION)
			{
				valido = guardar();
			}
			
			if (guardar == JOptionPane.NO_OPTION || valido)
			{
				AdministrarClientes.actualizarTabla();
				AdministrarClientes.botones(true);
				this.dispose();
			}
		}
	}

	/**
	 * invocado cuando un componente de texto esta infocado
	 * @param fg evnto a prosesar
	 * 
	 */
	@Override
	public void focusGained(FocusEvent fg)
	{
		JTextComponent txt = (JTextComponent) fg.getSource();
		txt.select(0, txt.getText().length());
	}

	/**
	 * Invocado cuando un componente pierde el keyboard focus
	 * @param fl el evento a procesar
	 */
	@Override
	public void focusLost(FocusEvent fl)
	{
		JTextComponent txt = (JTextComponent) fl.getSource();
		txt.select(0, 0);
	}

	/**
	 * invocado cuando el usuario intenta cerrar la ventana 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowClosing(WindowEvent e)
	{
		btnCancelar.doClick();
	}

	/**
	 * Invocado la primera vez una ventana se ha checho visible
	 * @param e el evento a procesar
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		// comportamiento por defecto
	}

	/**
	 * Invocado cuando una ventana se cerro como resultado llamando a dispose en la ventana
	 * @param e evento a procesar
	 */
	@Override
	public void windowClosed(WindowEvent e) {
		// comportamiento por defecto
	}

	/**
	 * Invocado cuando a una ventana se cambio de normal a minimizado por varias plataformas
	 * una minimizada ventana se procesa como el icono especificado en la propiedad de siconImage
	 * @param e el evento a procesar
	 */
	@Override
	public void windowIconified(WindowEvent e) {
		// comportamiento por defecto
	}

	/**
	 * cuando una ventana cambia de minimizado a ventana normal
	 * @param e el evento a procesar
	 */
	@Override
	public void windowDeiconified(WindowEvent e) {
		// comportamiento por defecto
	}

	/**
	 * Invocado cuando la ventana es capacitado a ser ventana activa 
	 * solo un frame o un dialog puede ser ventana activa 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		// comportamiento por defecto
	}

	/**
	 *  Invocado cuando una ventana no es langer la ventana activa
	 *  solo un Frame o un Dialog puede ser ventana activa
	 *  @param e el evento a procesar
	 */
	@Override
	public void windowDeactivated(WindowEvent e) {
		// comportamiento por defecto
	}
}