package funciones;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Frame;

import navegacion.Login;
/**
 * esta clase difiene modo de salir del programa
 * 
 * @author Grupo 2
 * @version 2.0.1
 */
public class Salir {
	/**
	 * muestra Jframe con el mensaje y las opciones segun la elegida interactua de
	 * una manera
	 * <h5>Cerrar sesion:</h5> guarda mensaje cerre, abre nueva ventana de inicio
	 * y busca si hay alguna abierta y la cierra <br>
	 * <h5>Cerrar el programa:</h5>guarda mensaje de cierre, y cierra el
	 * programa<br>
	 * <h5>Cancelar:</h5> deshace de la ventana y sigue  con el programa
	 * 
	 * @param actual Jframe
	 * @see JOptionPane
	 */
	public static void general(JFrame actual) {
		int opcion = JOptionPane.showOptionDialog(null, (String) "¿Qué desea hacer?", "INFO",
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
	 * se intenta cerrar una ventana habiendo habierto orta lo impede
	 * 
	 * @return devuelve JOptionPane con el correspondiente mensaje
	 */
	public static int edicion() {
		return JOptionPane.showOptionDialog(null, (String) "¿Desea guardar antes de salir?", "INFO",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new Object[] { "Sí", "No", "Cancelar" }, "Sí");
	}

	/**
	 * se intenta cerrar una ventana habiendo habierto orta lo impede
	 * 
	 * @return devuelve JOptionPane con el correspondiente mensaje
	 */
	public static void error() {
		JOptionPane.showOptionDialog(null, (String) "No puede cerrar esta ventana ahora mismo", "INFO",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "OK" }, "OK");
	}
}