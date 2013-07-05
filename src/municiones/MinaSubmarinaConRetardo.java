package municiones;

import naves.SeccionDeNave;
import tablero.Casillero;
import tablero.Tablero;

public abstract class MinaSubmarinaConRetardo extends MinaSubmarina {
	final static int miRetardo = 3;
	protected int rango;
	
	public MinaSubmarinaConRetardo(int unCosto, int unRango){
		super(unCosto, miRetardo);
		this.rango = unRango;
	}
	
	public void impactar(Tablero tablero, Casillero miCasillero){
		for (Casillero unCasillero : tablero.obtenerAledanyos(miCasillero, this.rango)){
			for (SeccionDeNave seccion : unCasillero.devolverSeccionesDeNave()){
				seccion.recibirImpacto(this);
			}
		}
	}
}
