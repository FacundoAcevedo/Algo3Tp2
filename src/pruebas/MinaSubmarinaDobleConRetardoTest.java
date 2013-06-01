package pruebas;

import static org.junit.Assert.*;

import municiones.MinaSubmarinaDobleConRetardo;

import org.junit.Test;

public class MinaSubmarinaDobleConRetardoTest {

	@Test
	public void testCrear() {
		MinaSubmarinaDobleConRetardo minaSubmarinaDobleConRetardo = new MinaSubmarinaDobleConRetardo();
		assertTrue(minaSubmarinaDobleConRetardo != null);
	}
	
	@Test
	public void testCosto(){
		MinaSubmarinaDobleConRetardo minaSubmarinaDobleConRetardo = new MinaSubmarinaDobleConRetardo();
		assertTrue(minaSubmarinaDobleConRetardo.costo() == 100);
	}
}
