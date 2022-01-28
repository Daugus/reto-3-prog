package funciones;

import javax.swing.JOptionPane;

public class Salir
{
	public static void siNo()
	{
		// mensaje al intentar cerrar la ventana
		int opcion = JOptionPane.showConfirmDialog(null, (String) "¿Deseas salir? (Se perderán los cambios sin guardar)", "INFO",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
		
		switch (opcion)
		{
			case JOptionPane.YES_OPTION:
				System.exit(0);
				break;
		}
	}
	
	public static int siNoCancelar()
	{
		// mensaje al intentar cerrar la ventana
		return JOptionPane.showConfirmDialog(null, (String) "¿Deseas guardar antes de salir?", "INFO",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
	}
}