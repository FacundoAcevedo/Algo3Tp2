package instanciadores;

import municiones.DisparoConvencional;
import municiones.MinaSubmarinaDobleConRetardo;
import municiones.MinaSubmarinaPorContacto;
import municiones.MinaSubmarinaPuntualConRetardo;
import municiones.MinaSubmarinaTripleConRetardo;
import municiones.Municion;

public class InstanciadorMuniciones {
	//Clase encargada de devolver un objeto a partir de una cadena
	
	public Municion instanciardor(String nombreDelObjeto){
		if(nombreDelObjeto == "Disparo Convencional"){
			return new DisparoConvencional();
			
		}
		else if(nombreDelObjeto == "Mina Por Contacto"){
			return new MinaSubmarinaPorContacto();
		}
		else if(nombreDelObjeto == "Mina Puntual Con Retardo" ){
			return new MinaSubmarinaPuntualConRetardo();
		}
		else if(nombreDelObjeto == "Mina Doble Con Retardo"){
			return new MinaSubmarinaDobleConRetardo();
		}
		else{
			//"Mina Triple Con Retardo"
			return new MinaSubmarinaTripleConRetardo();
			
		}
		
	}

}
