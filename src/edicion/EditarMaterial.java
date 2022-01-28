package edicion;

import java.awt.Font;
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
		panelPrincipal.setBackground(Inicio.colorFondo);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		btnCancelar.setBounds(10, 193, 108, 42);
		panelPrincipal.add(btnCancelar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font(Inicio.fuente, Font.PLAIN, 16));
		btnGuardar.setBounds(160, 193, 188, 42);
		panelPrincipal.add(btnGuardar);
		
		JLabel lblPrecio = new JLabel("Precio por unidad:");
		lblPrecio.setForeground(Inicio.colorFuente);
		lblPrecio.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
		lblPrecio.setBounds(10, 85, 131, 56);
		panelPrincipal.add(lblPrecio);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Inicio.colorFuente);
		lblNombre.setFont(new Font(Inicio.fuente, Font.PLAIN, 15));
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
		txtPrecio.addActionListener(this);
		txtPrecio.addFocusListener(this);
		
		// - JButton -
		btnCancelar.addActionListener(this);
		btnGuardar.addActionListener(this);
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
						
						AdministrarMateriales.dlmMateriales.removeAllElements();
						AdministrarMateriales.dlmMateriales.addAll(Archivos.cargarTodosMateriales());

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