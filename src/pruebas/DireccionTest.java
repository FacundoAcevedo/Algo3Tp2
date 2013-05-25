package pruebas;
import static org.junit.Assert.*;

import naves.Direccion;
import naves.Sentido;

import org.junit.Test;

public class DireccionTest {

	@Test
	public void testDireccion() {
		Direccion direccion = new Direccion(Sentido.NORTE);
		assertTrue(direccion != null);
	}
	
	@Test
	public void testAsignarSentidoNorte(){
		Direccion direccion = new Direccion(Sentido.NORTE);
		assertEquals(direccion.sentido() , Sentido.NORTE);
	}
	@Test
	public void testAsignarSentidoSur(){
		Direccion direccion = new Direccion(Sentido.SUR);
		assertEquals(direccion.sentido() , Sentido.SUR);
	}
	@Test
	public void testAsignarSentidoNoreste(){
		Direccion direccion = new Direccion(Sentido.NORESTE);
		assertEquals(direccion.sentido() , Sentido.NORESTE);
	}
	@Test
	public void testAsignarSentidoNoroeste(){
		Direccion direccion = new Direccion(Sentido.NOROESTE);
		assertEquals(direccion.sentido() , Sentido.NOROESTE);
	}
	@Test
	public void testAsignarSentidoSudeste(){
		Direccion direccion = new Direccion(Sentido.SUDESTE);
		assertEquals(direccion.sentido() , Sentido.SUDESTE);
	}
	@Test
	public void testAsignarSentidoSudoeste(){
		Direccion direccion = new Direccion(Sentido.SUDOESTE);
		assertEquals(direccion.sentido() , Sentido.SUDOESTE);
	}
	


	@Test
	public void testInvertirSentidoNorte() {
		Direccion direccion = new Direccion(Sentido.NORTE);
		direccion.invertirSentido();
		assertEquals(direccion.sentido() , Sentido.SUR);
	
	}
	@Test
	public void testInvertirSentidoSur() {
		Direccion direccion = new Direccion(Sentido.SUR);
		direccion.invertirSentido();
		assertEquals(direccion.sentido() , Sentido.NORTE);
	
	}
	@Test
	public void testInvertirSentidoNoreste() {
		Direccion direccion = new Direccion(Sentido.NORESTE);
		direccion.invertirSentido();
		assertEquals(direccion.sentido() , Sentido.SUDOESTE);
	
	}
	@Test
	public void testInvertirSentidoNoroeste() {
		Direccion direccion = new Direccion(Sentido.NOROESTE);
		direccion.invertirSentido();
		assertEquals(direccion.sentido() , Sentido.SUDOESTE);
	
	}
	@Test
	public void testInvertirSentidoSudeste() {
		Direccion direccion = new Direccion(Sentido.SUDESTE);
		direccion.invertirSentido();
		assertEquals(direccion.sentido() , Sentido.NOROESTE);
	
	}
	@Test
	public void testInvertirSentidoSudoeste() {
		Direccion direccion = new Direccion(Sentido.SUDOESTE);
		direccion.invertirSentido();
		assertEquals(direccion.sentido() , Sentido.NORESTE);
	
	}

}
