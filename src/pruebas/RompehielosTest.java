package pruebas;

import static org.junit.Assert.*;

import municiones.DisparoConvencional;
import municiones.MinaSubmarinaDobleConRetardo;
import municiones.MinaSubmarinaPorContacto;
import municiones.MinaSubmarinaPuntualConRetardo;
import municiones.MinaSubmarinaTripleConRetardo;
import municiones.Municion;
import naves.Direccion;
import naves.EstadoDeSalud;
import naves.Rompehielos;
import naves.SeccionDeNave;
import naves.Sentido;

import org.junit.Before;
import org.junit.Test;

public class RompehielosTest {

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
		Rompehielos rompeHielos = new Rompehielos(direccion);
		assertTrue(rompeHielos != null);
	}

	@Test
	public void testRecibirImpactoDirectoEnSeccion() {
		Rompehielos rompeHielos = new Rompehielos(direccion);
		SeccionDeNave seccion;
		seccion = rompeHielos.secciones().get(1);
		seccion.recibirImpacto(disparo);
		
		
		assertTrue(rompeHielos.estado() == EstadoDeSalud.DANADO);
	}
	@Test
	public void testRecibirImpactoDirectoCantidadNecesariaParaDestruirLaNave() {
		Rompehielos rompeHielos = new Rompehielos(direccion);
		SeccionDeNave seccion;
		for (int i=0; i < rompeHielos.largo(); i++){
			seccion = rompeHielos.secciones().get(i);
			seccion.recibirImpacto(disparo);
		}
		
		assertTrue(rompeHielos.estado() == EstadoDeSalud.DANADO);
	}

	@Test
	public void testEsVulnerableAMinaSubmarinaDobleConRetardo() {
		Rompehielos rompeHielos = new Rompehielos(direccion);
		assertTrue(rompeHielos.vulnerable(minaSubmarinaDobleConRetardo) == EstadoDeSalud.DANADO);
	}

	@Test
	public void testEsVulnerableAMinaSubmarinaPuntualConRetardo() {
		Rompehielos rompeHielos = new Rompehielos(direccion);
		assertTrue(rompeHielos.vulnerable(minaSubmarinaPuntualConRetardo) == EstadoDeSalud.DANADO);
	}

	@Test
	public void testEsVulnerableAMinaSubmarinaTripleConRetardo() {
		Rompehielos rompeHielos = new Rompehielos(direccion);
		assertTrue(rompeHielos.vulnerable(minaSubmarinaTripleConRetardo) == EstadoDeSalud.DANADO);
	}

	@Test
	public void testEsVulnerableAMinaSubmarinaPorContacto() {
		Rompehielos rompeHielos = new Rompehielos(direccion);
		assertTrue(rompeHielos.vulnerable(minaSubmarinaPorContacto) == EstadoDeSalud.DANADO);
	}
}
