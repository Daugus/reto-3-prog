package ordenes;

import java.awt.Dimension;
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

public class GenerarFactura extends JFrame implements ActionListener, WindowListener, FocusListener {
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

	public GenerarFactura() {
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

	public void cargarDatos(Orden o, boolean nueva) {
		idOrden = o.getCodigo();

		if (!nueva) {
			edicion = true;

			factura = Datos.cargarFactura(idOrden);

			chkPagada.setSelected(factura.isPagada());

			if (factura.getMetodoPago() != null) {
				cmbMetodoPago.setSelectedItem(factura.getMetodoPago());
			}

			if (Double.valueOf(factura.getDescuento()) != null) {
				txtDescuento.setText(String.valueOf(factura.getDescuento()));
			}
		}
	}

	private boolean guardar() {
		boolean pagada = chkPagada.isSelected();
		Fecha fecha = new Fecha();

		if (edicion) {

		} else {
			String idFactura = Datos.generarCodigoFactura();

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

					Datos.guardarFactura(new Factura(idFactura, idOrden, metodoPago, pagada, descuento, fecha), false);

					return true;
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(this, (String) "Formato de descuento inválido", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				Datos.guardarFactura(new Factura(idFactura, idOrden, pagada, fecha), false);
				
				return true;
			}
		}

		return false;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object o = ae.getSource();

		int guardar = JOptionPane.YES_OPTION;
		if (o == btnCancelar) {
			guardar = Salir.edicion();
		}

		boolean valido = false;

		if (guardar == JOptionPane.YES_OPTION) {
			valido = guardar();
		}

		if (guardar == JOptionPane.NO_OPTION || valido) {
			ListaOrdenes lo = new ListaOrdenes();
			lo.setVisible(true);

			this.dispose();
		}
	}

	@Override
	public void focusGained(FocusEvent fg) {
		JTextComponent txt = (JTextComponent) fg.getSource();
		txt.select(0, txt.getText().length());
	}

	@Override
	public void focusLost(FocusEvent fl) {
		JTextComponent txt = (JTextComponent) fl.getSource();
		txt.select(0, 0);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		btnCancelar.doClick();
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