package tablero;
import naves.Nave;
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
	
	public void ponerNave(Nave nave){
		this.contenido.ponerNave(nave);
	}
	
	public void ponerMunicion(Municion municion){
		this.contenido.ponerMunicion(municion);
	}	
	public int[] id(){
		return this.id.id();
	}
	public	boolean estaVacio(){
		return contenido.estaVacio();
	}

}
