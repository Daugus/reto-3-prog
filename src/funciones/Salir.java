package funciones;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Frame;

import navegacion.Login;

/**
 * ventanas de confirmación de salida
 */
public class Salir {
	/**
	 * ventana de confirmación de salida por defecto
	 * 
	 * @param actual la ventana abierta
	 */
	public static void general(JFrame actual) {
		int opcion = JOptionPane.showOptionDialog(null, (String) "¿Qué desea hacer?", "AVISO",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new Object[] { "Cerrar sesión", "Cerrar el programa", "Cancelar" }, "Cerrar sesión");

		switch (opcion) {
		case 0: // cerrar sesión
			Log.logout();

			Login l = new Login();
			l.setVisible(true);

			Frame[] frames = Frame.getFrames();
			for (Frame fr : frames) {
				if (!(fr instanceof Login)) {
					fr.dispose();
				}
			}
			break;
		case 1: // cerrar el programa
			Log.logout();
			System.exit(0);
			break;
		case 2: // cancelar (no hacer nada)
		default:
			break;
		}
	}

	/**
	 * ventana de confirmación de salida al editar
	 * 
	 * @return int que representa la opción seleccionada
	 */
	public static int edicion() {
		return JOptionPane.showOptionDialog(null, (String) "¿Desea guardar antes de salir?", "AVISO",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new Object[] { "Sí", "No", "Cancelar" }, "Sí");
	}

	/**
	 * ventana de aviso cuando no se permite la salida
	 */
	public static void errorBloqueado() {
		JOptionPane.showOptionDialog(null, (String) "No puede cerrar esta ventana en este momento", "INFO",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "OK" }, "OK");
	}
}