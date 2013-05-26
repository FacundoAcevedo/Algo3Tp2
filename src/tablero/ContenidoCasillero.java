package tablero;

import municiones.Municion;
import naves.Nave;

import java.util.LinkedList;
import java.util.List;

public class ContenidoCasillero {
	protected List<Nave> coleccionNaves = new LinkedList<Nave>();
	protected List<Municion> coleccionMuniciones= new LinkedList<Municion>();
	
	public void ponerNave(Nave nave) {
		// TODO Auto-generated method stub
		
	}

	public void ponerMunicion(Municion municion) {
		// TODO Auto-generated method stub
		
	}

	public boolean estaVacio() {
		if (this.coleccionNaves.isEmpty() && this.coleccionMuniciones.isEmpty()) return true;
		return false;
	}

}
