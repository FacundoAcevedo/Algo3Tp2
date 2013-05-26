package tablero;
import naves.Nave;
import municiones.Municion;

public class Casillero {
	IdCasillero id;
	ContenidoCasillero contenido;
	
	public Casillero(int x, int y){
		id = new	IdCasillero(x, y);
		contenido = new ContenidoCasillero(); 
	}
	
	public void ponerNave(Nave nave){
		contenido.ponerNave(nave);
	}
	
	public void ponerMunicion(Municion municion){
		contenido.ponerMunicion(municion);
	}	
	

}
