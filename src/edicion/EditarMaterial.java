package edicion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import administracion.AdministrarMateriales;
import clases.Material;
import funciones.Datos;
import funciones.Salir;
import navegacion.Inicio;

public class EditarMaterial extends JFrame implements ActionListener, WindowListener, FocusListener {
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private JTextField txtID;
	private JTextField txtMarca;
	private JTextField txtNombre;
	private JTextField txtStock;
	private JTextField txtPVP;
	private JTextField txtPrecioCompra;
	private JCheckBox chkActivo;

	private JButton btnCancelar;
	private JButton btnGuardar;

	private boolean edicion;

	public EditarMaterial() {
		setResizable(false);
		setTitle("Agregar material | " + Inicio.empleadoActual.getNombre());

		setBounds(100, 100, 396, 500);
		getContentPane().setPreferredSize(new Dimension(396, 500));
		pack();

		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(10, 434, 180, 40);
		panelPrincipal.add(btnCancelar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(206, 434, 180, 40);
		panelPrincipal.add(btnGuardar);

		JLabel lblID = new JLabel("ID:");
		lblID.setBounds(59, 28, 150, 35);
		panelPrincipal.add(lblID);

		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(59, 74, 150, 35);
		panelPrincipal.add(lblMarca);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(59, 120, 150, 35);
		panelPrincipal.add(lblNombre);

		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(59, 166, 150, 35);
		panelPrincipal.add(lblStock);

		JLabel lblPVP = new JLabel("PVP:");
		lblPVP.setBounds(59, 212, 150, 35);
		panelPrincipal.add(lblPVP);

		JLabel lblPrecioCompra = new JLabel("Precio de compra:");
		lblPrecioCompra.setBounds(59, 258, 150, 35);
		panelPrincipal.add(lblPrecioCompra);

		JLabel lblActivo = new JLabel("Activo:");
		lblActivo.setBounds(59, 313, 150, 35);
		panelPrincipal.add(lblActivo);

		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setBounds(209, 28, 150, 35);
		panelPrincipal.add(txtID);

		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		txtMarca.setBounds(209, 74, 150, 35);
		panelPrincipal.add(txtMarca);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(209, 120, 150, 35);
		panelPrincipal.add(txtNombre);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(215, 166, 70, 35);
		panelPrincipal.add(txtStock);

		txtPVP = new JTextField();
		txtPVP.setColumns(10);
		txtPVP.setBounds(215, 212, 70, 35);
		panelPrincipal.add(txtPVP);

		txtPrecioCompra = new JTextField();
		txtPrecioCompra.setColumns(10);
		txtPrecioCompra.setBounds(215, 258, 70, 35);
		panelPrincipal.add(txtPrecioCompra);

		chkActivo = new JCheckBox("");
		chkActivo.setHorizontalAlignment(SwingConstants.CENTER);
		chkActivo.setOpaque(false);
		chkActivo.setSelected(true);
		chkActivo.setBounds(215, 320, 70, 35);
		panelPrincipal.add(chkActivo);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action && Focus ---
		// - JTextField -
		txtMarca.addActionListener(this);
		txtMarca.addFocusListener(this);
		txtNombre.addActionListener(this);
		txtNombre.addFocusListener(this);
		txtStock.addActionListener(this);
		txtStock.addFocusListener(this);
		txtPVP.addActionListener(this);
		txtPVP.addFocusListener(this);
		txtPrecioCompra.addActionListener(this);
		txtPrecioCompra.addFocusListener(this);

		// - JButton -
		btnCancelar.addActionListener(this);
		btnGuardar.addActionListener(this);

		// ===== ajustes de usuario =====
		// --- fuente ---
		lblID.setFont(Inicio.fuente);
		lblMarca.setFont(Inicio.fuente);
		lblNombre.setFont(Inicio.fuente);
		lblStock.setFont(Inicio.fuente);
		lblPVP.setFont(Inicio.fuente);
		lblPrecioCompra.setFont(Inicio.fuente);
		lblActivo.setFont(Inicio.fuente);

		txtID.setFont(Inicio.fuenteObjetos);
		txtMarca.setFont(Inicio.fuenteObjetos);
		txtNombre.setFont(Inicio.fuenteObjetos);
		txtStock.setFont(Inicio.fuenteObjetos);
		txtPVP.setFont(Inicio.fuenteObjetos);
		txtPrecioCompra.setFont(Inicio.fuenteObjetos);

		btnCancelar.setFont(Inicio.fuenteObjetos);
		btnGuardar.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		txtID.setBackground(Inicio.colorFondoObjetos);
		txtMarca.setBackground(Inicio.colorFondoObjetos);
		txtNombre.setBackground(Inicio.colorFondoObjetos);
		txtStock.setBackground(Inicio.colorFondoObjetos);
		txtPVP.setBackground(Inicio.colorFondoObjetos);
		txtPrecioCompra.setBackground(Inicio.colorFondoObjetos);

		btnCancelar.setBackground(Inicio.colorFondoObjetos);
		btnGuardar.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		lblID.setForeground(Inicio.colorFuente);
		lblMarca.setForeground(Inicio.colorFuente);
		lblNombre.setForeground(Inicio.colorFuente);
		lblStock.setForeground(Inicio.colorFuente);
		lblPVP.setForeground(Inicio.colorFuente);
		lblPrecioCompra.setForeground(Inicio.colorFuente);
		lblActivo.setForeground(Inicio.colorFuente);

		txtID.setForeground(Inicio.colorFuenteObjetos);
		txtID.setDisabledTextColor(Color.DARK_GRAY);
		txtMarca.setForeground(Inicio.colorFuenteObjetos);
		txtNombre.setForeground(Inicio.colorFuenteObjetos);
		txtStock.setForeground(Inicio.colorFuenteObjetos);
		txtPVP.setForeground(Inicio.colorFuenteObjetos);
		txtPrecioCompra.setForeground(Inicio.colorFuenteObjetos);

		btnCancelar.setForeground(Inicio.colorFuenteObjetos);
		btnGuardar.setForeground(Inicio.colorFuenteObjetos);

		// valores por defecto
		txtID.setEnabled(false);
		txtMarca.setText("Marca");
		txtNombre.setText("Nombre");
		txtStock.setText("1");
		txtPVP.setText("100.0");
		txtPrecioCompra.setText("75.0");
		chkActivo.setSelected(true);
	}

	public void setID(String idUltimo) {
		int id = Integer.valueOf(idUltimo.substring(1));
		id++;
		txtID.setText("P" + id);
	}

	public void modoEdicion(Material material) {
		edicion = true;

		setTitle("Editar " + material.getID() + " | " + Inicio.empleadoActual.getNombre());

		txtID.setText(material.getID());
		txtMarca.setText(material.getMarca());
		txtNombre.setText(material.getNombre());
		txtStock.setText(String.valueOf(material.getStock()));
		txtPVP.setText(String.valueOf(material.getPVP()));
		txtPrecioCompra.setText(String.valueOf(material.getPrecioCompra()));
		chkActivo.setSelected(material.isActivo());
	}

	private boolean guardar() {
		try {
			String id = txtID.getText();
			String marca = txtMarca.getText();
			String nombre = txtNombre.getText();
			String s = txtStock.getText();
			String p = txtPVP.getText();
			String pc = txtPrecioCompra.getText();
			boolean activo = chkActivo.isSelected();

			if (marca.equals("")) {
				JOptionPane.showMessageDialog(this, (String) "Campo Nombre vacío", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else if (nombre.equals("")) {
				JOptionPane.showMessageDialog(this, (String) "Campo Marca vacío", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else if (s.equals("")) {
				JOptionPane.showMessageDialog(this, (String) "Campo Stock vacío", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else if (p.equals("")) {
				JOptionPane.showMessageDialog(this, (String) "Campo PVP vacío", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else if (pc.equals("")) {
				JOptionPane.showMessageDialog(this, (String) "Campo Precio de Compra vacío", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else {
				p = p.replaceAll(",", ".");
				pc = pc.replaceAll(",", ".");

				int stock = Integer.parseInt(s);
				double pvp = Double.parseDouble(p);
				double precioCompra = Double.parseDouble(pc);

				if (pvp <= 0) {
					JOptionPane.showMessageDialog(this, (String) "PVP no válido, PVP debe ser mayor que 0", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else if (precioCompra <= 0) {
					JOptionPane.showMessageDialog(this,
							(String) "Precio de Compra no válido, Precio de Compra debe ser mayor que 0", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else if (stock < 0) {
					JOptionPane.showMessageDialog(this, (String) "Stock no válido, Stock no puede ser menor que 0",
							"ERROR", JOptionPane.ERROR_MESSAGE);
				} else {
					Datos.guardarMaterial(new Material(id, marca, nombre, stock, pvp, precioCompra, activo), edicion);
					return true;
				}
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, (String) "Campo numérico vacío o incorrecto", "ERROR",
					JOptionPane.ERROR_MESSAGE);
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
			AdministrarMateriales.actualizarTabla();
			AdministrarMateriales.botones(true);

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