package pruebas;

import static org.junit.Assert.*;

import municiones.DisparoConvencional;
import municiones.MinaSubmarinaDobleConRetardo;
import municiones.MinaSubmarinaPorContacto;
import municiones.MinaSubmarinaPuntualConRetardo;
import municiones.MinaSubmarinaTripleConRetardo;
import municiones.Municion;
import naves.Lancha;
import naves.Direccion;
import naves.EstadoDeSalud;
import naves.SeccionDeNave;
import naves.Sentido;

import org.junit.Before;
import org.junit.Test;

public class LanchaTest {

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
		Lancha lancha = new Lancha(direccion);
		assertTrue(lancha != null);
	}

	@Test
	public void testRecibirImpactoDirectoEnSeccion() {
		Lancha lancha = new Lancha(direccion);
		SeccionDeNave seccion;
		seccion = lancha.secciones().get(1);
		seccion.recibirImpacto(disparo);
		
		
		assertTrue(lancha.estado() == EstadoDeSalud.DANADO);
	}
	@Test
	public void testRecibirImpactoDirectoCantidadNecesariaParaDestruirLaNave() {
		Lancha lancha = new Lancha(direccion);
		SeccionDeNave seccion;
		for (int i=0; i < lancha.largo(); i++){
			seccion = lancha.secciones().get(i);
			seccion.recibirImpacto(disparo);
		}
		
		assertTrue(lancha.estado() == EstadoDeSalud.DESTRUIDO);
	}

	@Test
	public void testEsVulnerableAMinaSubmarinaDobleConRetardo() {
		Lancha lancha = new Lancha(direccion);
		assertTrue(lancha.vulnerable(minaSubmarinaDobleConRetardo) == EstadoDeSalud.DESTRUIDO);
	}

	@Test
	public void testEsVulnerableAMinaSubmarinaPuntualConRetardo() {
		Lancha lancha = new Lancha(direccion);
		assertTrue(lancha.vulnerable(minaSubmarinaPuntualConRetardo) == EstadoDeSalud.DESTRUIDO);
	}

	@Test
	public void testEsVulnerableAMinaSubmarinaTripleConRetardo() {
		Lancha lancha = new Lancha(direccion);
		assertTrue(lancha.vulnerable(minaSubmarinaTripleConRetardo) == EstadoDeSalud.DESTRUIDO);
	}

	@Test
	public void testEsVulnerableAMinaSubmarinaPorContacto() {
		Lancha lancha = new Lancha(direccion);
		assertTrue(lancha.vulnerable(minaSubmarinaPorContacto) == EstadoDeSalud.DESTRUIDO);
	}

}
