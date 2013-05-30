package tablero;
import java.util.Hashtable;

public class Tablero {
	private Hashtable<int[], Casillero> coleccionCasilleros;
	
	public Tablero (){
		this.coleccionCasilleros = new Hashtable<>();
	}
	
	public Casillero obtenerCasillero(int[] id){
		IdCasillero.validarId(id);
		if ( this.coleccionCasilleros.contains(id) ){
		return this.coleccionCasilleros.get(id);
		}
		Casillero casillero = new Casillero(id);
		this.coleccionCasilleros.put(id, casillero);
		return casillero;
		
		
	}
	
	public boolean estaVacio (){
		return coleccionCasilleros.isEmpty();
	}
	
	

}
