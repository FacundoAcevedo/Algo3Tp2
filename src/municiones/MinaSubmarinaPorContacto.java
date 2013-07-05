package municiones;

import naves.SeccionDeNave;
import tablero.Casillero;
import tablero.Tablero;

public class MinaSubmarinaPorContacto extends MinaSubmarina{
	final static int miCosto = 150;
	final static int miRetardo = 0;
	
	public MinaSubmarinaPorContacto(){
		super(miCosto, miRetardo);
	}
	
	public void impactar(Tablero tablero, Casillero miCasillero){
		for (SeccionDeNave seccion : miCasillero.devolverSeccionesDeNave()){
			seccion.recibirImpacto(this);
		}
	}
}
