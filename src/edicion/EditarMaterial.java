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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import administracion.AdministrarMateriales;
import clases.Material;
import funciones.Archivos;
import funciones.Salir;
import navegacion.Inicio;

public class EditarMaterial extends JFrame implements ActionListener, WindowListener, FocusListener {
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private JTextField txtNombre;
	private JTextField txtPrecio;

	private JButton btnCancelar;
	private JButton btnGuardar;

	private boolean edicion;

	public EditarMaterial() {
		setResizable(false);
		setTitle("Agregar material | " + Inicio.cuentaActual.getNombre());

		setBounds(100, 100, 396, 200);
		getContentPane().setPreferredSize(new Dimension(396, 200));
		pack();

		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(10, 135, 180, 40);
		panelPrincipal.add(btnCancelar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(206, 135, 180, 40);
		panelPrincipal.add(btnGuardar);

		JLabel lblPrecio = new JLabel("Precio por unidad:");
		lblPrecio.setBounds(48, 75, 150, 35);
		panelPrincipal.add(lblPrecio);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(48, 25, 150, 35);
		panelPrincipal.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(198, 25, 150, 35);
		panelPrincipal.add(txtNombre);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(198, 75, 70, 35);
		panelPrincipal.add(txtPrecio);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action && Focus ---
		// - JTextField -
		txtNombre.addActionListener(this);
		txtNombre.addFocusListener(this);
		txtPrecio.addActionListener(this);
		txtPrecio.addFocusListener(this);

		// - JButton -
		btnCancelar.addActionListener(this);
		btnGuardar.addActionListener(this);

		// ===== ajustes de usuario =====
		// --- fuente ---
		lblNombre.setFont(Inicio.fuente);
		lblPrecio.setFont(Inicio.fuente);

		txtNombre.setFont(Inicio.fuenteObjetos);
		txtPrecio.setFont(Inicio.fuenteObjetos);

		btnCancelar.setFont(Inicio.fuenteObjetos);
		btnGuardar.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		txtNombre.setBackground(Inicio.colorFondoObjetos);
		txtPrecio.setBackground(Inicio.colorFondoObjetos);

		btnCancelar.setBackground(Inicio.colorFondoObjetos);
		btnGuardar.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		lblNombre.setForeground(Inicio.colorFuente);
		lblPrecio.setForeground(Inicio.colorFuente);

		txtNombre.setForeground(Inicio.colorFuenteObjetos);
		txtNombre.setDisabledTextColor(Color.DARK_GRAY);
		txtPrecio.setForeground(Inicio.colorFuenteObjetos);

		btnCancelar.setForeground(Inicio.colorFuenteObjetos);
		btnGuardar.setForeground(Inicio.colorFuenteObjetos);
	}

	public void modoEdicion(Material material) {
		edicion = true;

		setTitle("Editar " + material.getNombre() + " | " + Inicio.cuentaActual.getNombre());

		txtNombre.setText(material.getNombre());
		txtNombre.setEnabled(false);

		txtPrecio.setText(String.valueOf(material.getPrecio()));
	}

	private boolean guardar() {
		try {
			String nombre = txtNombre.getText();
			String p = txtPrecio.getText();

			if (nombre.equals("") || p.equals("")) {
				JOptionPane.showMessageDialog(this, (String) "Campo vacío", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else {
				p = p.replaceAll(",", ".");
				double precio = Double.parseDouble(p);
				if (precio > 0) {
					if (!edicion && Archivos.listarMateriales().contains(nombre)) {
						JOptionPane.showMessageDialog(this, (String) "Material ya existe", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Archivos.guardarMaterial(new Material(nombre, precio));

						return true;
					}
				} else {
					JOptionPane.showMessageDialog(this, (String) "Precio no válido, precio no puede ser menor que 0",
							"ERROR", JOptionPane.ERROR_MESSAGE);
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