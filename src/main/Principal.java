package main;

import vista.VistaBatalla;
import controlador.Controlador;
import juego.BatallaNavalgo;

public class Principal {

	public static void main(String[] args) {
		// creamos el modelo
		BatallaNavalgo modelo = new BatallaNavalgo();
		// creamos el controlador que atiende a la vista
		Controlador control = new Controlador();
		control.cargarModelo(modelo);
		// creamos la vista
		VistaBatalla vista = new VistaBatalla(modelo, control);
		//cargo la vista en el controlado
		control.cargarVista(vista);
       

		// esto es necesario para que la vista se actualice la 1ra vez
		modelo.ActualizarObservadores();
		 vista.frameBatalla.setVisible(true);
	}

}
