package funciones;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class Tablas
{
	public static void ajustarColumnas(JTable tabla)
	{
	    TableColumnModel tcm = tabla.getColumnModel();
	    	
	    for (int columna = 0; columna < tabla.getColumnCount(); columna++)
	    {
	        int ancho = 15;

	        for (int row = 0; row < tabla.getRowCount(); row++)
	        {
	            TableCellRenderer renderer = tabla.getCellRenderer(row, columna);

	            Component comp = tabla.prepareRenderer(renderer, row, columna);

	            ancho = Math.max(comp.getPreferredSize().width + 1, ancho);
	        }

	        if (ancho > 300)
	        {
	            ancho = 300;
	        }

	        tcm.getColumn(columna).setPreferredWidth(ancho);
	    }
	}
}
