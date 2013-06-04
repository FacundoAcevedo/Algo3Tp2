package naves;


import municiones.Municion;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Nave implements Iterable<SeccionDeNave>{
	protected int largo;
	protected Direccion direccion;
	protected EstadoDeSalud estado;
	protected List<SeccionDeNave> secciones = new LinkedList<SeccionDeNave>();
	protected int porcentajeDeVida;

	public Nave(int largoNave, Direccion direccionNave) {
		this.largo = largoNave;
		this.direccion = direccionNave;
		this.estado = EstadoDeSalud.SANO;
		porcentajeDeVida = 100;

		// Genero las secciones de la nave
		for (int i = 0; i < this.largo; i++) {
			SeccionDeNave seccion = new SeccionDeNave(this);
			this.secciones.add(seccion);
		}
	}

	public Sentido direccion() {
		return this.direccion.sentido();
	}

	public int largo() {
		return this.largo;
	}

	public EstadoDeSalud estado() {
		return this.estado;
	}

	protected void actualizarEstado() {
		// Reviso todas las secciones
		int i = 0;	
		int seccionesSanas = 0, seccionesDestruidas = 0, seccionesDanadas = 0;
		SeccionDeNave seccion;
		for (i = 0; i < this.largo; i++){
			seccion = this.secciones.get(i);
			if (seccion.estado() == EstadoDeSalud.SANO) seccionesSanas++;
			if (seccion.estado() == EstadoDeSalud.DANADO) seccionesDanadas++;
			if (seccion.estado() == EstadoDeSalud.DESTRUIDO) seccionesDestruidas++;
		}
		
		if (seccionesDestruidas == this.largo){
			this.estado = EstadoDeSalud.DESTRUIDO;
			this.porcentajeDeVida = 0;
		}
		else if (seccionesSanas == this.largo){
			this.estado = EstadoDeSalud.SANO;
			this.porcentajeDeVida = 100;
		}
		else{
			this.estado = EstadoDeSalud.DANADO;
			this.porcentajeDeVida = (int)( ((seccionesDanadas)/2 + seccionesSanas) * ((float)100/this.largo));			
		}
		
		
	}
	
	public float porcentajeVida(){
		return this.porcentajeDeVida;
	}

	public void recibirImpacto(Municion municion) {
		this.actualizarEstado();

	}
	
	public EstadoDeSalud vulnerable(Municion municion){
		//Devuelve el grado de vulnerabilidad a ese tipo de municion
		return EstadoDeSalud.DESTRUIDO;
	}
	
	public List<SeccionDeNave> secciones(){
		return this.secciones;
	}
	
	public void invertirSentido(){
		this.direccion.invertirSentido();
	}
	
	public SeccionDeNave proa(){
		return secciones.get(0);
	}
	
	@Override
	public Iterator<SeccionDeNave> iterator() {
		Iterator<SeccionDeNave> iterador = new Iterator<SeccionDeNave>(){
			
			private Iterator<SeccionDeNave> iteradorDeSecciones = secciones.iterator();
			
			@Override
			public boolean hasNext(){
				return iteradorDeSecciones.hasNext();
			}
			
			@Override
			public SeccionDeNave next(){
				return iteradorDeSecciones.next(); 
			}
			
			@Override
			public void remove(){
				//no hace nada pero me obliga a definirlo.
			}
		};
		return iterador;
	}
	

}
