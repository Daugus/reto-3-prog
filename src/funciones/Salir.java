package funciones;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Frame;

import navegacion.Login;

public class Salir {
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

	public static int edicion() {
		return JOptionPane.showOptionDialog(null, (String) "¿Desea guardar antes de salir?", "AVISO",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new Object[] { "Sí", "No", "Cancelar" }, "Sí");
	}

	public static void errorBloqueado() {
		JOptionPane.showOptionDialog(null, (String) "No puede cerrar esta ventana ahora mismo", "INFO",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "OK" }, "OK");
	}
}