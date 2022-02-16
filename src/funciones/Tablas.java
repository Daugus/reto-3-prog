package funciones;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import navegacion.Inicio;

/**
 * esta clase difiene la forma de crear las tablas
 * 
 * @author Grupo 2
 * @version 2.0.1
 */
public class Tablas {

	/**
	 * cambiar tamaño de columnas según su contenido
	 * 
	 * @param tabla objeto tabla a modificar
	 * @see TableColumnModel
	 */
	public static void ajustarColumnas(JTable tabla) {
		TableColumnModel tcm = tabla.getColumnModel();

		for (int columna = 0; columna < tabla.getColumnCount(); columna++) {
			int ancho = 15;

			for (int row = 0; row < tabla.getRowCount(); row++) {
				TableCellRenderer renderer = tabla.getCellRenderer(row, columna);

				Component comp = tabla.prepareRenderer(renderer, row, columna);

				ancho = Math.max(comp.getPreferredSize().width + 1, ancho);
			}

			if (ancho > 300) {
				ancho = 300;
			}

			tcm.getColumn(columna).setPreferredWidth(ancho);
		}
	}

	/**
	 * estilizar tabla vertical
	 * 
	 * @param tbl objeto tabla a modificar
	 * @see Component
	 */
	public static void vertical(JTable tbl) {
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			/**
			 * @return la tabla
			 * @see Component
			 * @return the default table cell renderer
			 */
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				setFont(Inicio.fuenteObjetos);

				return this;
			}
		};

		tbl.getColumnModel().getColumn(0).setCellRenderer(renderer);

		tbl.getColumnModel().getColumn(0).setPreferredWidth(150);
		tbl.getColumnModel().getColumn(0).setMaxWidth(150);
	}
}
