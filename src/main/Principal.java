package main;

import vista.VistaBatalla;
import controlador.Controlador;
import controlador.ControladorBotonesTablero;
import juego.BatallaNavalgo;

public class Principal {

	public static void main(String[] args) {
		// creamos el modelo
		BatallaNavalgo batalla = new BatallaNavalgo();
		// creamos el controlador que atiende a la vista
		Controlador control = new Controlador(batalla);
		//Controlador de los botones
		ControladorBotonesTablero controladorBotonesTablero = new ControladorBotonesTablero();
		// creamos la vista
		new VistaBatalla(batalla, control, controladorBotonesTablero);
		// esto es necesario para que la vista se actualice la 1ra vez
		batalla.ActualizarObservadores();
	}

}
