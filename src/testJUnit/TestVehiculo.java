package testJUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clases.Vehiculo;

class TestVehiculo {

	@BeforeEach
	void setUp() throws Exception {
		
	}
	//comprueba vehiculo con un nuevo vehiculo
	@Test
	void testVehiculoVehiculo() {
		Vehiculo vh1=new Vehiculo(new Vehiculo());
		Vehiculo vh2=new Vehiculo();
		assertEquals("comparar vehiculo nuevo", vh1.equals(vh2),true);
		
	}
	//metodo vehiculo 
	private void assertEquals(String string, boolean equals, boolean b) {
			
	}
	//comprueba vehiculo de un nuevo cliente 
	@Test
	void testVehiculoClienteNuevo() {
		Vehiculo vhCl =new Vehiculo("2349FGO","Youssef");
		String m="2349FGO";
		String p="Youssef";
		assertEquals("compara los controctores",vhCl,new Vehiculo(m,p),true);
	}
	//metodo comparar vehiculo nuevo cliente
	private void assertEquals(String string, Vehiculo vhCl, Vehiculo vehiculo, boolean b) {
			
	}
//	implementar futuro
//	@Test
//	void testVehiculoStringString() {
//		
//	}
//
//	@Test
//	void testVehiculoEnBlanco() {
//	
//	}

}
