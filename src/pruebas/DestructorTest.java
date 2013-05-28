package pruebas;

import static org.junit.Assert.*;

import municiones.DisparoConvencional;
import municiones.MinaSubmarinaDobleConRetardo;
import municiones.MinaSubmarinaPorContacto;
import municiones.MinaSubmarinaPuntualConRetardo;
import municiones.MinaSubmarinaTripleConRetardo;
import municiones.Municion;
import naves.Destructor;
import naves.Direccion;
import naves.EstadoDeSalud;
import naves.SeccionDeNave;
import naves.Sentido;

import org.junit.Before;
import org.junit.Test;

public class DestructorTest {
	
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
		Destructor destructor = new Destructor(direccion);
		assertTrue(destructor != null);
	}

	@Test
	public void testRecibirImpactoDirectoEnSeccion() {
		Destructor destructor = new Destructor(direccion);
		SeccionDeNave seccion;
		seccion = destructor.secciones().get(1);
		seccion.destruir();
		
		destructor.recibirImpacto(disparo);
		assertTrue(destructor.estado() == EstadoDeSalud.DANADO);
	}
	@Test
	public void testRecibirImpactoDirectoCantidadNecesariaParaDestruirLaNave() {
		Destructor destructor = new Destructor(direccion);
		SeccionDeNave seccion;
		for (int i=0; i < destructor.largo(); i++){
			seccion = destructor.secciones().get(i);
			seccion.destruir();
		}
		
		assertTrue(destructor.estado() == EstadoDeSalud.DESTRUIDO);
	}

	@Test
	public void testRecibirImpactoDeMinaSubmarinaDobleConRetardo() {
		Destructor destructor = new Destructor(direccion);
		destructor.recibirImpacto(minaSubmarinaDobleConRetardo);
		assertTrue(destructor.estado() == EstadoDeSalud.SANO);
	}

	@Test
	public void testRecibirImpactoDeMinaSubmarinaPuntualConRetardo() {
		Destructor destructor = new Destructor(direccion);
		destructor.recibirImpacto(minaSubmarinaPuntualConRetardo);
		assertTrue(destructor.estado() == EstadoDeSalud.SANO);
	}

	@Test
	public void testRecibirImpactoDeMinaSubmarinaTripleConRetardo() {
		Destructor destructor = new Destructor(direccion);
		destructor.recibirImpacto(minaSubmarinaTripleConRetardo);
		assertTrue(destructor.estado() == EstadoDeSalud.SANO);
	}

	@Test
	public void testRecibirImpactoDeMinaSubmarinaPorContacto() {
		Destructor destructor = new Destructor(direccion);
		destructor.recibirImpacto(minaSubmarinaPorContacto);
		assertTrue(destructor.estado() == EstadoDeSalud.SANO);
	}
	
}
