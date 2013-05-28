package pruebas;

import static org.junit.Assert.*;
import municiones.DisparoConvencional;
import municiones.Municion;
import naves.Direccion;
import naves.EstadoDeSalud;
import naves.Nave;
import naves.SeccionDeNave;
import naves.Sentido;
import org.junit.Before;
import org.junit.Test;

public class NaveTest {
	private int largo;
	private Direccion direccion;
	private Nave nave;
	private Municion disparo;
		
	@Test
	public void testCrear() {
		Nave unaNave= new Nave(1, direccion);
		assertTrue(unaNave != null);
	}
	
	@Before
	public void prepararObjetos(){
	largo = 6;
	disparo = new DisparoConvencional();
	direccion = new Direccion(Sentido.SUR);
	nave = new Nave(largo, direccion);
	}
	@Test
	public void largoTest(){
		assertEquals(nave.largo(), largo);
	}
	@Test
	public void direccionTest(){
		assertTrue(nave.direccion() == Sentido.SUR);
	}
	@Test
	public void invertirDireccionTest(){
		nave.invertirSentido();
		assertEquals(nave.direccion() ,  Sentido.NORTE);
	}
	@Test
	public void estadoDeSaludSinHaberSidoDanadoTest(){
		assertTrue(nave.estado() == EstadoDeSalud.SANO);
	}
	@Test
	public void porcentajeVidaSinHaberSidoDanadoTest(){
		assertTrue(nave.porcentajeVida() == (float)100);
	}
	@Test
	public void vulnerableTest(){
		//por defecto las naves son vulnerables a cualquier municion, salvo que sobreescriban el metodo
		assertEquals(nave.vulnerable(disparo) ,  EstadoDeSalud.DESTRUIDO);
	}
	@Test
	public void seccionesTest(){
		assertTrue(nave.secciones().size() == largo);
		SeccionDeNave seccion;
		for (int i = 0; i < largo; i++){
			seccion = nave.secciones().get(i);
			assertTrue(seccion instanceof SeccionDeNave);
		}
	}
	@Test
	public void estadosSiDestruyoUnaSeccionTest(){
		SeccionDeNave seccion;
		seccion = nave.secciones().get(1);
		seccion.recibirImpacto(disparo);
		assertTrue(nave.estado() == EstadoDeSalud.DANADO);
		
	}
	
	@Test
	public void porcentajeDeVidaConUnaDeSeisPartesDestruidas(){
		int porcentajeVidaCorrecto = (int)(5*100/6);
		SeccionDeNave seccion;
		seccion = nave.secciones().get(1);
		seccion.recibirImpacto(disparo);
		assertTrue(nave.porcentajeVida() == porcentajeVidaCorrecto);

		
	}
	@Test
	public void porcentajeDeVidaConDosDeSeisPartesDestruidas(){
		int porcentajeVidaCorrecto = (int)(4*100/6);
		SeccionDeNave seccion;
		for (int i = 0; i < 2; i++){
			seccion = nave.secciones().get(i);
			seccion.recibirImpacto(disparo);

		}
		
		assertTrue(nave.porcentajeVida() == porcentajeVidaCorrecto);

		
	}
	@Test
	public void porcentajeDeVidaConTresDeSeisPartesDestruidas(){
		int porcentajeVidaCorrecto = (int)(3*100/6);
		SeccionDeNave seccion;
		for (int i = 0; i < 3; i++){
			seccion = nave.secciones().get(i);
			seccion.recibirImpacto(disparo);
		}
		
		assertTrue(nave.porcentajeVida() == porcentajeVidaCorrecto);

		
	}
	@Test
	public void porcentajeDeVidaConSeisDeSeisPartesDestruidas(){
		int porcentajeVidaCorrecto = (int)(0*100/6);
		SeccionDeNave seccion;
		for (int i = 0; i < 6; i++){
			seccion = nave.secciones().get(i);
					seccion.recibirImpacto(disparo);
		}
		
		assertTrue(nave.porcentajeVida() == porcentajeVidaCorrecto);

		
	}
	
	public void porcentajeDeVidaDestruyendoPartesYaDestruidas(){
		int porcentajeVidaCorrecto = (int)(0*100/6);
		SeccionDeNave seccion;
		for (int i = 0; i < 6; i++){
			seccion = nave.secciones().get(i);
					seccion.recibirImpacto(disparo);
		}
		for (int i = 0; i < 6; i++){
			seccion = nave.secciones().get(i);
					seccion.recibirImpacto(disparo);
		}
		
		assertTrue(nave.porcentajeVida() == porcentajeVidaCorrecto);

		
	}
	
		
		
	
	

}
