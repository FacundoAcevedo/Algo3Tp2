package municiones;

import naves.SeccionDeNave;
import tablero.Casillero;
import tablero.Tablero;

public class DisparoConvencional extends Municion{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6446895073975670020L;
	final static int miCosto = 200; 
	final static int miRetardo = 0;
	final static boolean efectoInmediato = true;
	
	public DisparoConvencional(){
		super(miCosto, miRetardo, efectoInmediato);
	}
	
	public void impactar(Tablero tablero, Casillero miCasillero){
		for (SeccionDeNave seccion : miCasillero.devolverSeccionesDeNave()){
			seccion.recibirImpacto(this);
		}
	}
}
