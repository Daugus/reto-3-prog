package pruebas;

import java.io.File;

public class Borrar
{
	public static void main(String[] args)
	{
		File archivo = new File("D:\\tmp\\dir");
//		archivo.delete();
		
		if (archivo.delete())
		{
			System.out.println("deleted: " + archivo.getName());
		}
		else
		{
			System.out.println("error when deleting " + archivo);
		}
	}
}
