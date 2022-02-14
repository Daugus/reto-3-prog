package testJUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import clases.Ajustes;
import clases.Cuenta;


class TestCuenta {
	
	private static final Ajustes a=new Ajustes();

	/*@Before  
    public void setUp() {   
    //Inicialización de la clase caja ejecucion
     * 
     */
		

	@Test
	void testCuenta() {
		Cuenta ca = new Cuenta(true,"hrhhr",a);
		assertEquals(ca.toString(),c,ca);
    }
		
	

	@Test
	void testCuentaBoolean() {
		
	}

	@Test
	void testTipo() {
		fail("Not yet implemented");
	}

}
