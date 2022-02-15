package testJUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import clases.Ajustes;
import clases.Cuenta;
import clases.Direccion;
import clases.Fecha;
import funciones.General;
import navegacion.Inicio;

class TestCuenta {

	private static final Ajustes a = new Ajustes();
	private static final Fecha f = new Fecha();
	private static final Direccion d=new Direccion();
	
	// comprueba si la cuenta es mecanico
	@Test
	void testCuentaMec() {
		Cuenta ca = new Cuenta(true);
		assertEquals("es mecanico", ca.esMecanico(), true);
	}

	// comprueba si la cuenta es atc
	@Test
	void testCuenta() {
		Cuenta ca = new Cuenta(false);
		assertEquals("es atencion cliente", ca.esMecanico(), false);
	}

	// comprueba constructor copia
	@Test
	void testCuentaAtc() {
		Cuenta ca = new Cuenta(new Cuenta());
		Cuenta co = new Cuenta();
		assertEquals("constructor copia", ca.equals(co), true);
	}

	
	//tipo de cuenta
	@Test
	void testString() {
		String tipo = "Mecánico";
		Cuenta c = new Cuenta(true);
		assertEquals("esto deberia ser mecanico", tipo, c.tipo());
	}

	// comprueba si devuelve el resultado de iva
	@Test
	void testFormatear() {
		General g = new General();
		String resultado = "20,20€";
		String comprobar = g.formatear(20.2);
		assertEquals("los valores son iguales", resultado, comprobar);
	}
	//comprueba una cuenta pasada al constructor con una cuenta nueva
	@SuppressWarnings("unlikely-arg-type")
	@Test
	void testCuentas() {
		Cuenta ca = new Cuenta("6rjd93ffvr4", "youssef", "ait", 648394,
				"hola@email.com", f, d, true,"youu", a);
		assertEquals(ca.compareTo(new Cuenta()) > 1, true);
	}
	//comprueba el campo de contaseña
	@Test
	void testPassword() {
		Cuenta c = new Cuenta();
		String pas="contra";
		c.setPassword("contra");
		String co=c.getPassword();
		assertEquals("es igual a esto",pas,co);
	}
	
	

}
