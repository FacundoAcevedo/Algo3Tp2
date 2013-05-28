package naves;
import municiones.*;

public class Buque extends Nave{
	final static int miLargo = 4;
	
	public Buque (Direccion direccionBuqe){
		//Llamo al constructor de Nave
		super(miLargo, direccionBuqe);
	}
	
	public void recibirImpacto( Municion municion){
		//Cualquier tipo de municion recibida en alguna de sus secciones,
		//La destruye por completo
		
		//Destruyo c/u de las partes
		for(int i = 0; i < this.largo; i++){
			SeccionDeNave seccion = this.secciones.get(i);
			seccion.destruir();
			
		}
		this.actualizarEstado(); 
		
	}
	
}
