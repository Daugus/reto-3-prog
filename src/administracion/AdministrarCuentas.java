package administracion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.Cuenta;
import edicion.EditarCuenta;
import funciones.Archivos;
import funciones.Salir;
import funciones.Tablas;
import navegacion.Inicio;
import navegacion.MenuAtc;

/**
 * esta clase administra cuentas del programa lo cual carga datos de la misma 
 * @author Grupo 2
 * @version 2.0.1
 */
public class AdministrarCuentas extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = 1531539371445418371L;

	private JPanel panelPrincipal;
	
	private static JTable tblCuentas;

	private static JButton btnVolver;
	private static JButton btnAgregar;
	private static JButton btnEditar;
	
	private Cuenta cuenta;
	
	private static boolean bloqueado;
	
	/**
	 * constructor carga los elementos de la ventana
	 *  
	 */
	public AdministrarCuentas()
	{
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Administrar cuentas | " + Inicio.cuentaActual.getNombre());
		
		setBounds(100, 100, 700, 360);
		getContentPane().setPreferredSize(new Dimension(700, 360));
		pack();
		
		setLocationRelativeTo(null);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Inicio.colorFondo);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(260, 310, 180, 40);
		panelPrincipal.add(btnVolver);
		
		btnAgregar = new JButton("Agregar cuenta");
		btnAgregar.setBounds(50, 10, 230, 60);
		panelPrincipal.add(btnAgregar);
		
		btnEditar = new JButton("Editar cuentas");
		btnEditar.setBounds(420, 10, 230, 60);
		panelPrincipal.add(btnEditar);

		// ===== barras de desplazamiento =====
		JScrollPane scrollCuentas = new JScrollPane();
		scrollCuentas.setBounds(50, 85, 600, 200);
		panelPrincipal.add(scrollCuentas);

		// ===== modelos =====
		// --- crear ---
		DefaultTableModel dtmCuentas = new DefaultTableModel();
		dtmCuentas.addColumn("DNI");
		dtmCuentas.addColumn("Nombre");
		dtmCuentas.addColumn("Apellidos");
		dtmCuentas.addColumn("Tipo");

		// --- asignar ---
		tblCuentas = new JTable(dtmCuentas)
		{
			private static final long serialVersionUID = 1L;
			/**
			 * @param row  cuyo valor se va a consultar
			 * @param column  cuyo valor se va a consultar
			 * @return Devuelve verdadero si la celda en la fila y la columna es editable. De lo contrario,
			 *  invocar setValueAt en la celda no tendrá ningún efecto.
			 */
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		tblCuentas.setRowHeight(20);
		tblCuentas.setFillsViewportHeight(true);
		tblCuentas.getTableHeader().setReorderingAllowed(false);
		tblCuentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCuentas.getTableHeader().setBackground(Inicio.colorFondoObjetos);
		tblCuentas.getTableHeader().setFont(Inicio.fuenteObjetos);
		
		actualizarTabla();

		scrollCuentas.setViewportView(tblCuentas);

		// ===== Listeners =====
		// --- Window ---
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// --- Action ---
		btnVolver.addActionListener(this);
		btnAgregar.addActionListener(this);
		btnEditar.addActionListener(this);
		
		// ===== ajustes de usuario =====
		// --- fuente ---
		tblCuentas.setFont(Inicio.fuente);

		btnVolver.setFont(Inicio.fuenteObjetos);
		btnAgregar.setFont(Inicio.fuenteObjetos);
		btnEditar.setFont(Inicio.fuenteObjetos);

		// --- color ---
		// - fondo -
		panelPrincipal.setBackground(Inicio.colorFondo);

		tblCuentas.setBackground(Inicio.colorFondoObjetos);

		btnVolver.setBackground(Inicio.colorFondoObjetos);
		btnAgregar.setBackground(Inicio.colorFondoObjetos);
		btnEditar.setBackground(Inicio.colorFondoObjetos);

		scrollCuentas.setBackground(Inicio.colorFondoObjetos);

		// - fuente -
		tblCuentas.setForeground(Inicio.colorFuenteObjetos);

		btnVolver.setForeground(Inicio.colorFuenteObjetos);
		btnAgregar.setForeground(Inicio.colorFuenteObjetos);
		btnEditar.setForeground(Inicio.colorFuenteObjetos);
	}
	/**
	 * este metodo actualiza la tabla y carga los datos de la tabla cuentas
	 * @see Archivos
	 */
	public static void actualizarTabla()
	{
		DefaultTableModel dtm = (DefaultTableModel) tblCuentas.getModel();
		
		dtm.setRowCount(0);
		
		ArrayList<Cuenta> cuentas = Archivos.cargarTodosCuentas();
		for (Cuenta c : cuentas)
		{
			dtm.addRow(new Object[] {c.getDNI(), c.getNombre(), c.getApellidos(), c.tipo()});
		}
		
		Tablas.ajustarColumnas(tblCuentas);
	}
	/**
	 * modifica la vicivilidad de buttones segun el estado pasado como paramento
	 * @param estado si el estado es verdadero enabilita los seguientes buttones
	 */
	public static void botones(boolean estado)
	{
		btnAgregar.setEnabled(estado);
		btnEditar.setEnabled(estado);
		btnVolver.setEnabled(estado);
		
		bloqueado = !estado;
	}

	@Override
	/**
	 * invocado cuando una accion ocurre sobre los elementos
	 * @param e el evento a procesar
	 * @throws si no encuenta ninguna cuenta
	 */
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		
		if (o == btnAgregar)
		{
			botones(false);

			EditarCuenta ec = new EditarCuenta();
			ec.setVisible(true);
		}
		else if (o == btnEditar)
		{
			int row = tblCuentas.getSelectedRow();
			if (row >= 0)
			{
				cuenta = Archivos.cargarCuenta((String) tblCuentas.getValueAt(row, 0));

				botones(false);

				EditarCuenta ec = new EditarCuenta();
				ec.modoEdicion(cuenta);
			
				ec.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(this, (String) "No hay ninguna cuenta seleccionada", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (o == btnVolver)
		{
			MenuAtc ma = new MenuAtc();
			ma.setVisible(true);
			
			this.dispose();
		} 
	}
	
	/**
	 * invocado cuando el usuario intenta cerrar la ventana 
	 * @param e el evento a procesar
	 */
	@Override
	public void windowClosing(WindowEvent e)
	{
		if (bloqueado)
		{
			Salir.error();
		}
		else
		{
			Salir.general(this);
		}
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