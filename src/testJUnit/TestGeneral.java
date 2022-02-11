package testJUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import funciones.General;

class TestGeneral {

	@Test
	void testFormatear() {
		General g = new General();
		String resultado="20,20€";
		String comprobar= g.formatear(20.2);
		assertEquals(resultado,comprobar,"esto es una prueba");	
	}

}
