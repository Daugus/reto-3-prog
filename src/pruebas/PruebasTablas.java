package pruebas;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import clases.Cuenta;
import funciones.Archivos;
import funciones.Tablas;
import navegacion.Inicio;

public class PruebasTablas extends JFrame implements ActionListener
{
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PruebasTablas frame = new PruebasTablas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;
	
	private JButton btnAgregarLargo;
	private JButton btnPK;
	private JButton btnBorrarLargo;
	private JButton btnPDF;

	private DefaultTableModel dtmCuentas;
	private JTable tblCuentas;

	public PruebasTablas()
	{
		setBackground(new Color(255, 255, 255));
//		setResizable(false);
		setTitle("Pruebas tablas");
		
		setBounds(100, 100, 750, 553);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		btnPK = new JButton("PK");
		btnPK.setBackground(Color.WHITE);
		btnPK.setBounds(62, 373, 269, 62);
		panelPrincipal.add(btnPK);
		
		btnAgregarLargo = new JButton("Agregar largo y reajustar");
		btnAgregarLargo.setBackground(Color.WHITE);
		btnAgregarLargo.setBounds(62, 64, 269, 62);
		panelPrincipal.add(btnAgregarLargo);

		btnBorrarLargo = new JButton("Borrar largo y reajustar");
		btnBorrarLargo.setBounds(377, 64, 269, 62);
		panelPrincipal.add(btnBorrarLargo);

		// ===== barras de desplazamiento =====
		JScrollPane scrollCuentas = new JScrollPane();
		scrollCuentas.setBounds(62, 159, 584, 203);
		panelPrincipal.add(scrollCuentas);
		
		// ===== modelos =====
		// --- crear ---
		dtmCuentas = new DefaultTableModel();
		dtmCuentas.addColumn("DNI");
		dtmCuentas.addColumn("Nombre");
		dtmCuentas.addColumn("Apellidos");
		dtmCuentas.addColumn("Tipo");

		// --- asignar ---
		tblCuentas = new JTable(dtmCuentas)
		{
			private static final long serialVersionUID = -1689014003054751445L;

			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		tblCuentas.setFillsViewportHeight(true);
		tblCuentas.getTableHeader().setReorderingAllowed(false);
		tblCuentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCuentas.getTableHeader().setBackground(Inicio.colorFondoObjetos);
		tblCuentas.getTableHeader().setFont(Inicio.fuenteObjetos);
		

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// --- Action ---
		btnPK.addActionListener(this);
		btnBorrarLargo.addActionListener(this);
		btnAgregarLargo.addActionListener(this);

		DefaultTableModel dtm = (DefaultTableModel) tblCuentas.getModel();
		ArrayList<Cuenta> cuentas = Archivos.cargarTodosCuentas();
		for (Cuenta c : cuentas)
		{
			if (!c.getNombre().contains("Augusto"))
				for (int i = 0; i < 5; i++)
					dtm.addRow(new Object[] {c.getDNI(), c.getNombre(), c.getApellidos(), c.tipo()});
		}

		scrollCuentas.setViewportView(tblCuentas);
		
		// ===== ajustes de usuario =====
		// --- fuente ---
		tblCuentas.setFont(Inicio.fuente);

		btnPK.setFont(Inicio.fuenteObjetos);
		btnBorrarLargo.setFont(Inicio.fuenteObjetos);
		btnAgregarLargo.setFont(Inicio.fuenteObjetos);
		
		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		tblCuentas.setBackground(Inicio.colorFondoObjetos);

		btnPK.setBackground(Inicio.colorFondoObjetos);
		btnBorrarLargo.setBackground(Inicio.colorFondoObjetos);
		btnAgregarLargo.setBackground(Inicio.colorFondoObjetos);
		
		scrollCuentas.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		tblCuentas.setForeground(Inicio.colorFuenteObjetos);

		btnPK.setForeground(Inicio.colorFuenteObjetos);
		btnBorrarLargo.setForeground(Inicio.colorFuenteObjetos);
		btnAgregarLargo.setForeground(Inicio.colorFuenteObjetos);

		Tablas.ajustarColumnas(tblCuentas);
		
		btnPDF = new JButton("Guardar como PDF");
		btnPDF.addActionListener(this);
		btnPDF.setForeground(Inicio.colorFuenteObjetos);
		btnPDF.setFont(Inicio.fuenteObjetos);
		btnPDF.setBackground(Inicio.colorFondoObjetos);
		btnPDF.setBounds(377, 373, 269, 62);
		panelPrincipal.add(btnPDF);
	}
	
	public void printToPDF(java.awt.Image awtImage, String fileName)
	{
		try
		{
			Document d = new Document();
			PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(fileName));
			d.open();
			
			Image iTextImage = Image.getInstance(writer, awtImage, 1);
	         iTextImage.scalePercent(80);
	         d.add(iTextImage);
			
			d.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	
	public static java.awt.Image getImageFromPanel(Component component)
	{
        BufferedImage image = new BufferedImage(component.getWidth(),
                component.getHeight(), BufferedImage.TYPE_INT_RGB);
        component.paint(image.getGraphics());
        return image;
    }

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		if (o == btnPDF)
		{
			btnPDF.setVisible(false);
			panelPrincipal.setBackground(Color.WHITE);
			java.awt.Image image = getImageFromPanel(panelPrincipal);
			String fileName = "D:\\tmp\\newfile.pdf";
			printToPDF(image, fileName);
			btnPDF.setVisible(true);
			panelPrincipal.setBackground(Color.DARK_GRAY);
		}
		else if (o == btnPK)
		{
			int row = tblCuentas.getSelectedRow();
			if (row >= 0)
			{
				System.out.println("pk: " + tblCuentas.getValueAt(row, 0));
			}
			}
		else if (o == btnAgregarLargo)
		{
			DefaultTableModel dtm = (DefaultTableModel) tblCuentas.getModel();
			ArrayList<Cuenta> cuentas = Archivos.cargarTodosCuentas();
			for (Cuenta c : cuentas)
			{
			if (c.getNombre().contains("Augusto"))
				for (int i = 0; i < 5; i++)
					dtm.addRow(new Object[] {c.getDNI(), c.getNombre(), c.getApellidos(), c.tipo()});
			}

			Tablas.ajustarColumnas(tblCuentas);
		}
		else
		{
			DefaultTableModel dtm = (DefaultTableModel) tblCuentas.getModel();
			int row = tblCuentas.getRowCount() - 1;
			int tamano = 0;

			while (row > tamano)
			{
				String a = (String) tblCuentas.getValueAt(row, 1);

				if (a.contains("Augusto"))
				{
					dtm.removeRow(row);
				}
				row--;
			}
			Tablas.ajustarColumnas(tblCuentas);
		}
	}
}