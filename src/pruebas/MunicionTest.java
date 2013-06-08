package pruebas;

import municiones.Municion;
import static org.junit.Assert.*;

import org.junit.Test;

public class MunicionTest {

	@Test
	public void testCrear() {
		Municion municion = new Municion(0,0);
		assertTrue(municion != null);
	}
	
	@Test
	public void testCosto0(){
		Municion municion = new Municion(0,0);
		assertTrue(municion.costo() == 0);
	}
	
	@Test
	public void testCosto50(){
		Municion municion = new Municion(50,0);
		assertTrue(municion.costo() == 50);
	}
	
	@Test
	public void testRetardo0(){
		Municion municion = new Municion(0,0);
		assertTrue(municion.retardo() == 0);
	}
	
	@Test
	public void testRetardo3(){
		Municion municion = new Municion(0,3);
		assertTrue(municion.retardo() == 3);
	}
	
	@Test
	public void testDisminuirRetardo(){
		Municion municion = new Municion(0,3);
		municion.disminuirRetardo();
		assertTrue(municion.retardo() == 2);
	}
	
	@Test
	public void testDisminuirRetardo0(){
		Municion municion = new Municion(0,0);
		municion.disminuirRetardo();
		assertTrue(municion.retardo() == 0);
	}
	
}
