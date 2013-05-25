package pruebas;
import juego.Direccion;
import juego.Sentido;
import static org.junit.Assert.*;

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
	public void testInvertirSentido() {
		
	}

}
