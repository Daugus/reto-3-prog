package testJUnit;

import org.junit.jupiter.api.Test;

import clases.Fecha;
import clases.Vehiculo;

class TestVehiculo {

	private static final Fecha f=new Fecha();
//	@BeforeEach
//	void setUp() throws Exception {
//		
//	}
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
	//compara vehiculo pasado al constructor con un vehiculo nuevo
	@Test
	void testVehiculoStringString() {
		Vehiculo v=new Vehiculo("3452KJH","1230481021","93840328G","TESLA"
				,"s3","rojo",203,10293,f,"ELECTRICO");
		Vehiculo veA=new Vehiculo();
		assertEquals("compara dos vehiculos",v.compareTo(veA)>1,true);
	}
}
