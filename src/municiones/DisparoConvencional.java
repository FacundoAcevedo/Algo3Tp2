package municiones;

import naves.SeccionDeNave;
import tablero.Casillero;
import tablero.Tablero;

public class DisparoConvencional extends Municion{
	final static int miCosto = 200; 
	final static int miRetardo = 0;
	
	public DisparoConvencional(){
		super(miCosto, miRetardo);
	}
	
	public void impactar(Tablero tablero, Casillero miCasillero){
		for (SeccionDeNave seccion : miCasillero.devolverSeccionesDeNave()){
			seccion.recibirImpacto(this);
		}
	}
}
