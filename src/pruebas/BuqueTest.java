package pruebas;
import naves.*;
import municiones.*;
import naves.EstadoDeSalud;
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class BuqueTest {
	private Direccion direccion;
	private Municion disparo;/*, minaSubmarina, minaSubmarinaConRetardo, minaSubmarinaDobleConRetardo, minaSubmarinaPuntualConRetardo,
	minaSubmarinaTripleConRetardo;*/
	@Before
	public void preparaObjetos(){
		direccion = new Direccion(Sentido.SUR);
		disparo = new DisparoConvencional();
		/*minaSubmarinaConRetardo = new MinaSubmarinaConRetardo();
		
		minaSubmarinaDobleConRetardo = new MinaSubmarinaDobleConRetardoTest();
		minaSubmarinaPuntualConRetardo = new MinaSubmarinaPuntualConRetardoTest();
		minaSubmarinaTripleConRetardo = new MinaSubmarinaTripleConRetardoTest()*/
		
	}
	
	@Test
	public void testCrear() {
		Buque buque = new Buque(direccion);
		assertTrue(buque != null);
	}
	
	@Test
	public void testRecibirImpacto() {
		Buque buque = new Buque(direccion);
		buque.recibirImpacto(disparo);
		assertTrue(buque.estado() == EstadoDeSalud.DESTRUIDO);
		
	}

}
