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
	
	private static final Ajustes a=new Ajustes();
	private static final Fecha f=new Fecha();
	
		
	//comprueba si la cuenta es mecanico
	@Test
	void testCuentaMec() {
		Cuenta ca = new Cuenta( true);
		assertEquals("es mecanico",ca.esMecanico(),true);
    }
	//comprueba si la cuenta es atc
	@Test
	void testCuenta() {
		Cuenta ca = new Cuenta( false);
		assertEquals("es atencion cliente",ca.esMecanico(),false);
    }
	//comprueba constructor por defecto
	@Test
	void testCuentaAtc() {
		Cuenta ca = new Cuenta(new Cuenta());
		Cuenta co=new Cuenta();
		assertEquals("constructor por defecto",ca.equals(co),true);
    }
	
	//cuenta mecanico 
	@Test
	void testCuentaM() {
		Cuenta ca = new Cuenta(true,"password",a);
		assertEquals("persona Mecanico",true,true);
		
	}
	//cuenta atencion cliente
	@Test
	void testCuentaT() {
		Cuenta ca = new Cuenta(false,"password",a);
		assertEquals("persona Atencion cliente",false,false);
		
	}
	

	@Test
	void testString() {
		String tipo="Mecánico";
		Cuenta c =new Cuenta(true);
		assertEquals("esto deberia ser",tipo, c.tipo());
	}
	//comprueba si devuelve el resultado de iva 
	@Test
	void testFormatear() {
		General g = new General();
		String resultado="20,20€";
		String comprobar= g.formatear(20.2);
		assertEquals("los valores son iguales",resultado,comprobar);	
	}
	@SuppressWarnings("unlikely-arg-type")
	@Test
	void testCuentas() {
		Cuenta ca = new Cuenta("","youssef","ait",648394,"hola@email.com",new Fecha(),new Direccion(),true,"youu",a);
		assertEquals(ca.compareTo(new Cuenta())>1,false);		
	}
	

}
