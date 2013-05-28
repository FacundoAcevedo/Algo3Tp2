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
import naves.PortaAviones;
import naves.SeccionDeNave;
import naves.Sentido;

import org.junit.Before;
import org.junit.Test;

public class PortaAvionesTest {

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
		PortaAviones portaAviones = new PortaAviones(direccion);
		assertTrue(portaAviones != null);
	}

	@Test
	public void testRecibirImpactoDirectoEnSeccion() {
		PortaAviones portaAviones = new PortaAviones(direccion);
		SeccionDeNave seccion;
		seccion = portaAviones.secciones().get(1);
		seccion.recibirImpacto(disparo);
		
		
		assertTrue(portaAviones.estado() == EstadoDeSalud.DANADO);
	}
	@Test
	public void testRecibirImpactoDirectoCantidadNecesariaParaDestruirLaNave() {
		PortaAviones portaAviones = new PortaAviones(direccion);
		SeccionDeNave seccion;
		for (int i=0; i < portaAviones.largo(); i++){
			seccion = portaAviones.secciones().get(i);
			seccion.recibirImpacto(disparo);
		}
		
		assertTrue(portaAviones.estado() == EstadoDeSalud.DESTRUIDO);
	}

	@Test
	public void testEsVulnerableAMinaSubmarinaDobleConRetardo() {
		PortaAviones portaAviones = new PortaAviones(direccion);
		assertTrue(portaAviones.vulnerable(minaSubmarinaDobleConRetardo) == EstadoDeSalud.DESTRUIDO);
	}

	@Test
	public void testEsVulnerableAMinaSubmarinaPuntualConRetardo() {
		PortaAviones portaAviones = new PortaAviones(direccion);
		assertTrue(portaAviones.vulnerable(minaSubmarinaPuntualConRetardo) == EstadoDeSalud.DESTRUIDO);
	}

	@Test
	public void testEsVulnerableAMinaSubmarinaTripleConRetardo() {
		PortaAviones portaAviones = new PortaAviones(direccion);
		assertTrue(portaAviones.vulnerable(minaSubmarinaTripleConRetardo) == EstadoDeSalud.DESTRUIDO);
	}

	@Test
	public void testEsVulnerableAMinaSubmarinaPorContacto() {
		PortaAviones portaAviones = new PortaAviones(direccion);
		assertTrue(portaAviones.vulnerable(minaSubmarinaPorContacto) == EstadoDeSalud.DESTRUIDO);
	}
}
