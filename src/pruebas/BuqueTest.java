package pruebas;
import naves.*;
import municiones.*;
import naves.EstadoDeSalud;
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class BuqueTest {
	private Direccion direccion;
	private Municion disparo;

	private MinaSubmarinaTripleConRetardo minaSubmarinaTripleConRetardo;
	private MinaSubmarinaPuntualConRetardo minaSubmarinaPuntualConRetardo;
	private MinaSubmarinaDobleConRetardo minaSubmarinaDobleConRetardo;
	private MinaSubmarinaPorContacto minaSubmarinaPorContacto;
	
	@Before
	public void preparaObjetos(){
		direccion = new Direccion(Sentido.SUR);
		disparo = new DisparoConvencional();	
		minaSubmarinaDobleConRetardo = new MinaSubmarinaDobleConRetardo();
		minaSubmarinaPuntualConRetardo = new MinaSubmarinaPuntualConRetardo();
		minaSubmarinaTripleConRetardo = new MinaSubmarinaTripleConRetardo();
		minaSubmarinaPorContacto = new MinaSubmarinaPorContacto();
		
	}
	
	@Test
	public void testCrear() {
		Buque buque = new Buque(direccion);
		assertTrue(buque != null);
	}
	
	@Test
	public void testRecibirImpactoDirecto() {
		Buque buque = new Buque(direccion);
		buque.recibirImpacto(disparo);
		assertTrue(buque.estado() == EstadoDeSalud.DESTRUIDO);	
	}
	
	@Test
	public void testRecibirImpactoDeMinaSubmarinaDobleConRetardo() {
		Buque buque = new Buque(direccion);
		buque.recibirImpacto(minaSubmarinaDobleConRetardo);
		assertTrue(buque.estado() == EstadoDeSalud.DESTRUIDO);	
	}
	@Test
	public void testRecibirImpactoDeMinaSubmarinaPuntualConRetardo() {
		Buque buque = new Buque(direccion);
		buque.recibirImpacto(minaSubmarinaPuntualConRetardo);
		assertTrue(buque.estado() == EstadoDeSalud.DESTRUIDO);	
	}
	@Test
	public void testRecibirImpactoDeMinaSubmarinaTripleConRetardo() {
		Buque buque = new Buque(direccion);
		buque.recibirImpacto(minaSubmarinaTripleConRetardo);
		assertTrue(buque.estado() == EstadoDeSalud.DESTRUIDO);	
	}
	@Test
	public void testRecibirImpactoDeMinaSubmarinaPorContacto() {
		Buque buque = new Buque(direccion);
		buque.recibirImpacto(minaSubmarinaPorContacto);
		assertTrue(buque.estado() == EstadoDeSalud.DESTRUIDO);	
	}
	
	



}
