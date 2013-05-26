package tablero;
import java.util.Hashtable;

public class Tablero {
	private Hashtable<int[], Casillero> coleccionCasilleros;
	
	public Tablero (){
		this.coleccionCasilleros = new Hashtable<>();
	}
	
	public Casillero obtenerCasillero(int[] id){
		IdCasillero.validarId(id);
		return this.coleccionCasilleros.get(id);
		
	}
	
	

}
