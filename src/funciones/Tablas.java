package funciones;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import navegacion.Inicio;

/**
 * métodos para tablas
 */
public class Tablas {
	/**
	 * ajusta el ancho de las columnas para que se pueda ver todo en contenido
	 * 
	 * @param tabla la tabla que se va a ajustar
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
	 * estiliza una tabla para que sea vertical
	 * 
	 * @param tabla la tabla que se va a formatear
	 */
	public static void vertical(JTable tabla) {
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				setFont(Inicio.fuenteObjetos);

				return this;
			}
		};

		tabla.getColumnModel().getColumn(0).setCellRenderer(renderer);

		tabla.getColumnModel().getColumn(0).setPreferredWidth(150);
		tabla.getColumnModel().getColumn(0).setMaxWidth(150);
	}
}