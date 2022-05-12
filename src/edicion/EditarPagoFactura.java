package edicion;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import clases.Factura;
import clases.Fecha;
import clases.Orden;
import funciones.Datos;
import funciones.Salir;
import navegacion.Inicio;
import ordenes.ListaFacturas;
import ordenes.ListaOrdenes;

/**
 * ventana de edición de facturas
 */
public class EditarPagoFactura extends JFrame implements ActionListener, WindowListener, FocusListener {
	private static final long serialVersionUID = -3097458972006921427L;

	private JPanel panelPrincipal;

	private JComboBox<String> cmbMetodoPago;
	private JCheckBox chkPagada;
	private JTextField txtDescuento;

	private JButton btnCancelar;
	private JButton btnGenerar;

	private String idOrden;

	private Factura factura;

	private boolean edicion = false;

	/**
	 * carga los elementos de la ventana
	 */
	public EditarPagoFactura() {
		setResizable(false);
		setTitle("Generar factura | " + Inicio.empleadoActual.getNombre());

		setBounds(100, 100, 450, 300);
		getContentPane().setPreferredSize(new Dimension(450, 300));
		pack();

		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		JLabel lblMetodoPago = new JLabel("Método de pago:");
		lblMetodoPago.setBounds(30, 29, 150, 35);
		panelPrincipal.add(lblMetodoPago);

		JLabel lblPagada = new JLabel("Pagada:");
		lblPagada.setBounds(30, 75, 150, 35);
		panelPrincipal.add(lblPagada);

		JLabel lblDescuento = new JLabel("Descuento:");
		lblDescuento.setBounds(30, 121, 150, 35);
		panelPrincipal.add(lblDescuento);

		cmbMetodoPago = new JComboBox<String>();
		cmbMetodoPago.addItem("Metálico");
		cmbMetodoPago.addItem("Tarjeta");
		cmbMetodoPago.addItem("Cripto");
		cmbMetodoPago.setBounds(217, 38, 100, 22);
		panelPrincipal.add(cmbMetodoPago);

		chkPagada = new JCheckBox("");
		chkPagada.setHorizontalAlignment(SwingConstants.CENTER);
		chkPagada.setOpaque(false);
		chkPagada.setSelected(true);
		chkPagada.setBounds(198, 79, 100, 35);
		panelPrincipal.add(chkPagada);

		txtDescuento = new JTextField();
		txtDescuento.setColumns(10);
		txtDescuento.setBounds(208, 121, 100, 35);
		panelPrincipal.add(txtDescuento);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(46, 210, 180, 40);
		panelPrincipal.add(btnCancelar);

		btnGenerar = new JButton("Generar");
		btnGenerar.setBounds(230, 210, 180, 40);
		panelPrincipal.add(btnGenerar);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		txtDescuento.addActionListener(this);

		btnCancelar.addActionListener(this);
		btnGenerar.addActionListener(this);

		// --- Focus ---
		txtDescuento.addFocusListener(this);

		// --- Item ---
		chkPagada.addActionListener(this);

		// ===== ajustes de usuario =====
		// --- fuente ---
		lblMetodoPago.setFont(Inicio.fuente);
		lblPagada.setFont(Inicio.fuente);
		lblDescuento.setFont(Inicio.fuente);

		cmbMetodoPago.setFont(Inicio.fuenteObjetos);
		txtDescuento.setFont(Inicio.fuenteObjetos);

		btnCancelar.setFont(Inicio.fuenteObjetos);
		btnGenerar.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		cmbMetodoPago.setBackground(Inicio.colorFondoObjetos);
		txtDescuento.setBackground(Inicio.colorFondoObjetos);

		panelPrincipal.setBackground(Inicio.colorFondo);

		btnCancelar.setBackground(Inicio.colorFondoObjetos);
		btnGenerar.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		lblMetodoPago.setForeground(Inicio.colorFuente);
		lblPagada.setForeground(Inicio.colorFuente);
		lblDescuento.setForeground(Inicio.colorFuente);

		cmbMetodoPago.setForeground(Inicio.colorFuenteObjetos);
		txtDescuento.setForeground(Inicio.colorFuenteObjetos);

		btnCancelar.setForeground(Inicio.colorFuenteObjetos);
		btnGenerar.setForeground(Inicio.colorFuenteObjetos);

		// ===== valores por defecto ======
		txtDescuento.setText("0");
	}

	/**
	 * carga los datos del objeto pasado como parámetro
	 * 
	 * @param of    objeto, Orden o Factura
	 * @param nueva si es {@code true} se carga el código de la orden, si es
	 *              {@code false} se cargan los datos de la factura
	 */
	public void cargarDatos(Object of, boolean nueva) {
		if (nueva) {
			idOrden = ((Orden) of).getCodigo();
		} else {
			setTitle("Editar factura");

			edicion = true;

			factura = (Factura) of;

			chkPagada.setSelected(factura.isPagada());
			cmbMetodoPago.setEnabled(chkPagada.isSelected());
			txtDescuento.setEnabled(chkPagada.isSelected());

			if (factura.getMetodoPago() != null) {
				cmbMetodoPago.setSelectedItem(factura.getMetodoPago());
			}

			txtDescuento.setText(String.valueOf(factura.getDescuento()));
		}
	}

	private boolean guardar() {
		boolean pagada = chkPagada.isSelected();

		String idFactura = Datos.generarCodigoFactura();
		Fecha fecha = new Fecha();

		if (pagada) {
			try {
				String metodoPago = (String) cmbMetodoPago.getSelectedItem();
				int descuento = Integer.parseInt(txtDescuento.getText());

				if (descuento < 0) {
					JOptionPane.showMessageDialog(this,
							(String) "Campo de descuento inválido, el descuento no puede ser menor que 0", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return false;
				}

				if (descuento > 99) {
					JOptionPane.showMessageDialog(this,
							(String) "Campo de descuento inválido, el descuento no puede ser mayor que 99", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return false;
				}

				if (edicion) {
					Datos.guardarFactura(new Factura(factura.getCodigo(), factura.getCodigoOrden(), metodoPago, pagada,
							descuento, fecha), edicion);
				} else {
					Datos.guardarFactura(new Factura(idFactura, idOrden, metodoPago, pagada, descuento, fecha),
							edicion);
				}

				return true;
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(this, (String) "Formato de descuento inválido", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			if (edicion) {
				Datos.guardarFactura(new Factura(factura.getCodigo(), factura.getCodigoOrden(), pagada, fecha),
						edicion);
			} else {
				Datos.guardarFactura(new Factura(idFactura, idOrden, pagada, fecha), edicion);
			}

			return true;
		}

		return false;
	}

	/**
	 * invocado cuando ocurren una acción
	 * 
	 * @param ae el evento de acción
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object o = ae.getSource();

		if (o == chkPagada) {
			cmbMetodoPago.setEnabled(chkPagada.isSelected());
			txtDescuento.setEnabled(chkPagada.isSelected());

			return;
		}

		int guardar = JOptionPane.YES_OPTION;
		if (o == btnCancelar) {
			guardar = Salir.edicion();
		}

		boolean valido = false;

		if (guardar == JOptionPane.YES_OPTION) {
			valido = guardar();
		}

		if (guardar == JOptionPane.NO_OPTION || valido) {
			Frame lista;

			if (edicion) {
				lista = new ListaFacturas();
			} else {
				lista = new ListaOrdenes();
			}

			lista.setVisible(true);

			this.dispose();
		}
	}

	/**
	 * invocado cuando se enfoca un campo de texto
	 * 
	 * @param fg el evento de enfoque
	 */
	public void focusGained(FocusEvent fg) {
		JTextComponent txt = (JTextComponent) fg.getSource();
		txt.select(0, txt.getText().length());
	}

	/**
	 * invocado cuando se deja de enfocar un campo de texto
	 * 
	 * @param fg el evento de enfoque
	 */
	@Override
	public void focusLost(FocusEvent fl) {
		JTextComponent txt = (JTextComponent) fl.getSource();
		txt.select(0, 0);
	}

	/**
	 * invocado cuando se cierra la ventana
	 * 
	 * @param we el evento de ventana
	 */
	@Override
	public void windowClosing(WindowEvent we) {
		btnCancelar.doClick();
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