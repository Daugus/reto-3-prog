package pruebas;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import clases.Empleado;
import clases.Fecha;
import clases.Total;
import funciones.General;

public class Pruebas {
	// comprueba el calculo del total
	@Test
	void testTotalFactura() {
		double costeReparaciones = 100;
		double costeMateriales = 75;
		int descuento = 10;

		Total t = new Total(costeReparaciones, costeMateriales, descuento);
		assertEquals("Calcula el total correctamente", Double.valueOf(190.575), Double.valueOf(t.getTotal()));
	}

	// comprueba si se convierte un estado booleano en un String para almacenarlo en
	// la BBDD
	@Test
	void testConvertirEstadoAString() {
		assertEquals("El empleado por defecto es mecánico", "Activo", General.estadoAString(true));
	}

	// comprueba si se convierte un estado en String en un boolean para crear un
	// objeto
	@Test
	void testConvertirEstadoABoolean() {
		assertEquals("El empleado por defecto es mecánico", true, General.estadoABoolean("Activo"));
	}

	// comprueba que el método toSring formatee la fecha correctamente
	@Test
	void testFechaString() {
		Fecha f = new Fecha(26, 12, 2002);
		assertEquals("La fecha está formateada correctamente", "2002-12-26", f.toSQLDate());
	}

	// comprueba que el método devuelve una fecha en el formato usado por sql
	@Test
	void testFechaStringSQL() {
		Fecha f = new Fecha(26, 12, 2002);
		assertEquals("La fecha está formateada correctamente", "26/12/2002", f.toString());
	}

	// comprueba si el empleado por defecto es mecanico
	@Test
	void testEmpleadoPorDefectoEsMecanico() {
		Empleado e = new Empleado();
		assertEquals("El empleado por defecto es mecánico", "Mecanico", e.getTipo());
	}

	// comprueba constructor copia
	@Test
	void testConstructorCopia() {
		Empleado e1 = new Empleado(new Empleado());
		Empleado e2 = new Empleado();
		assertEquals("Constructor copia funciona correctamente", true, e1.equals(e2));
	}

	// comprueba si devuelve el resultado de iva
	@Test
	void testFormatear() {
		String input = General.formatearPrecio(20.2);
		assertEquals("Formatea correctamente", "20,20€", input);
	}
}