package pruebas;

import static org.junit.Assert.*;

import municiones.MinaSubmarinaPuntualConRetardo;

import org.junit.Test;

public class MinaSubmarinaPuntualConRetardoTest {

	@Test
	public void testCrear() {
		MinaSubmarinaPuntualConRetardo minaSubmarinaPuntualConRetardo = new MinaSubmarinaPuntualConRetardo();
		assertTrue(minaSubmarinaPuntualConRetardo != null);
	}
	
	@Test
	public void testCosto(){
		MinaSubmarinaPuntualConRetardo minaSubmarinaPuntualConRetardo = new MinaSubmarinaPuntualConRetardo();
		assertTrue(minaSubmarinaPuntualConRetardo.costo() == 50);
	}
	
}
