package edicion;

import java.awt.Color;
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

/**
 * 
 * @author Grupo_4
 * 
 */
public class EditarMaterial extends JFrame implements ActionListener, WindowListener, FocusListener
{
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;

	private JTextField txtNombre;
	private JTextField txtPrecio;
	
	private JButton btnCancelar;
	private JButton btnGuardar;
	
	private boolean edicion;

	public EditarMaterial() {
		setResizable(false);
		setTitle("Agregar material");
		
		setBounds(100, 100, 377, 285);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(10, 193, 108, 42);
		panelPrincipal.add(btnCancelar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(160, 193, 188, 42);
		panelPrincipal.add(btnGuardar);
		
		JLabel lblPrecio = new JLabel("Precio por unidad:");
		lblPrecio.setBounds(10, 85, 131, 56);
		panelPrincipal.add(lblPrecio);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(63, 43, 78, 56);
		panelPrincipal.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(151, 58, 182, 31);
		panelPrincipal.add(txtNombre);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(151, 100, 89, 31);
		panelPrincipal.add(txtPrecio);
		
		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action && Focus ---
		// - JTextField -
		txtNombre.addActionListener(this);
		txtNombre.addFocusListener(this);
		txtNombre.setDisabledTextColor(Color.DARK_GRAY);
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
		txtPrecio.setForeground(Inicio.colorFuenteObjetos);
		
		btnCancelar.setForeground(Inicio.colorFuenteObjetos);
		btnGuardar.setForeground(Inicio.colorFuenteObjetos);
	}

	public void modoEdicion(Material material)
	{
		edicion = true;
		
		setTitle("Editar " + material.getNombre());
			
		txtNombre.setText(material.getNombre());
		txtNombre.setEnabled(false);

		txtPrecio.setText(String.valueOf(material.getPrecio()));
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		Object o = ae.getSource();
		
		if (o == btnCancelar)
		{
			this.dispose();
		}
		else
		{
			try
			{
				String nombre = txtNombre.getText();
				String p = txtPrecio.getText();

				if (nombre.equals("") || p.equals(""))
				{
					JOptionPane.showMessageDialog(this, (String) "Campo vacío", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					p = p.replaceAll(",", ".");
					double precio = Double.parseDouble(p);
					
					if (!edicion && Archivos.listarMateriales().contains(nombre))
					{
						JOptionPane.showMessageDialog(this, (String) "Material ya existe", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						Archivos.guardarMaterial(new Material(nombre,precio));
						
						AdministrarMateriales.actualizarTabla();

						this.dispose();
					}
				}
			}
			catch (NumberFormatException npe)
			{
				JOptionPane.showMessageDialog(this, (String) "Campo numérico vacío o incorrecto", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
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
	public void windowClosing(WindowEvent e)
	{
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