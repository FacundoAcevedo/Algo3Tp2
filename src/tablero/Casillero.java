package tablero;
import java.util.List;

import naves.SeccionDeNave;
import municiones.Municion;

public class Casillero {
	IdCasillero id;
	ContenidoCasillero contenido;
	
	public Casillero(int x, int y){
		this.id = new	IdCasillero(x, y);
		this.contenido = new ContenidoCasillero(); 
	}
	public Casillero(int [] id){
		this(id[0],id[1]); 
	}
	
	public void ponerSeccionDeNave(SeccionDeNave seccionDeNave){
		this.contenido.ponerSeccionDeNave(seccionDeNave);
	}
	
	public List<SeccionDeNave> devolverSeccionesDeNave(){
		return this.contenido.devolverSeccionesDeNave();
	}
	
	public void ponerMunicion(Municion municion){
		//Deberia verificar que la municion este "activa" 
		//y ver que impacte...
		this.contenido.ponerMunicion(municion);
	}	
	public List<Municion> devolverMuniciones(){
		return this.contenido.devolverMuniciones();
	}
	
	public int[] id(){
		return this.id.id();
	}
	public	boolean estaVacio(){
		return contenido.estaVacio();
	}

}
