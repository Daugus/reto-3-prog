package pruebas;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import clases.Cuenta;
import clases.Vehiculo;
import funciones.Archivos;
import navegacion.Inicio;

public class TablaVertical extends JFrame
{
	private static final long serialVersionUID = -6801124658754324322L;
	private JPanel contentPane;
	private JTable tblCliente;
	private JTable tblVehiculo;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					TablaVertical frame = new TablaVertical();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public TablaVertical()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 843, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Cuenta c = Archivos.cargarCuenta("Y0723663M");
		
		DefaultTableModel dtmCliente = new DefaultTableModel();
		dtmCliente.addColumn("");
		dtmCliente.addColumn("");

		dtmCliente.addRow(new Object[] { "DNI", c.getDNI() });

		dtmCliente.addRow(new Object[] { "Nombre", c.getNombre() });
		dtmCliente.addRow(new Object[] { "Apellidos", c.getApellidos() });
		
		dtmCliente.addRow(new Object[] { "Tel.", c.getTelefono() });
		dtmCliente.addRow(new Object[] { "Email", c.getEmail() });

		dtmCliente.addRow(new Object[] { "Fecha nacimiento", c.getFechaNacimiento() });
		dtmCliente.addRow(new Object[] { "Dirección", c.getDireccion() });

		tblCliente = new JTable(dtmCliente);
		tblCliente.setRowHeight(20);
		tblCliente.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblCliente.setBounds(10, 11, 400, 140);
		tblCliente.setFillsViewportHeight(true);
		contentPane.add(tblCliente);
		
		Vehiculo v = Archivos.cargarVehiculo("4580BMK");

		DefaultTableModel dtmVehiculo = new DefaultTableModel();
		dtmVehiculo.addColumn("");
		dtmVehiculo.addColumn("");

		dtmVehiculo.addRow(new Object[] { "Matrícula", v.getMatricula() });
		dtmVehiculo.addRow(new Object[] { "Bastidor", v.getBastidor() });

		dtmVehiculo.addRow(new Object[] { "Modelo", v.getMarca() + " " + v.getModelo() });
		dtmVehiculo.addRow(new Object[] { "Color", v.getColor() });

		dtmVehiculo.addRow(new Object[] { "Cilindrada", v.getCilindrada() });

		dtmVehiculo.addRow(new Object[] { "KMs recorridos", v.getKmRecorridos() });
		dtmVehiculo.addRow(new Object[] { "Año ITV", v.getFechaITV() });

		dtmVehiculo.addRow(new Object[] { "Tipo", v.getTipo() });

		tblVehiculo = new JTable(dtmVehiculo);
		tblVehiculo.setRowHeight(20);
		tblVehiculo.setFillsViewportHeight(true);
		tblVehiculo.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblVehiculo.setBounds(417, 11, 400, 160);
		contentPane.add(tblVehiculo);

		tblCliente.setFont(Inicio.fuente);
		tblVehiculo.setFont(Inicio.fuente);
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer()
		{
			private static final long serialVersionUID = 1L;
			Font font = Inicio.fuenteObjetos;
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
			{
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				setFont(font);
				
				return this;
			}
		};
		
		tblCliente.getColumnModel().getColumn(0).setCellRenderer(r);
		tblVehiculo.getColumnModel().getColumn(0).setCellRenderer(r);

		tblCliente.getColumnModel().getColumn(0).setPreferredWidth(120);
		tblVehiculo.getColumnModel().getColumn(0).setPreferredWidth(120);
		tblCliente.getColumnModel().getColumn(0).setMaxWidth(120);
		tblVehiculo.getColumnModel().getColumn(0).setMaxWidth(120);
	}
}
