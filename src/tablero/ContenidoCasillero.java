package tablero;

import municiones.Municion;
import naves.Nave;
import naves.SeccionDeNave;

import java.util.LinkedList;
import java.util.List;

public class ContenidoCasillero {
	protected List<SeccionDeNave> coleccionDeSeccionesDeNave = new LinkedList<SeccionDeNave>();
	protected List<Municion> coleccionMuniciones= new LinkedList<Municion>();
	
	public void ponerNave(Nave nave) {
		// TODO Auto-generated method stub
		
	}

	public void ponerMunicion(Municion municion) {
		// TODO Auto-generated method stub
		
	}

	public boolean estaVacio() {
		//Devuelve que esta vacio, si NO HAY secciones de naves NI municiones en el casillero.
		if (this.coleccionDeSeccionesDeNave.isEmpty() && this.coleccionMuniciones.isEmpty()) return true;
		return false;
	}

}
