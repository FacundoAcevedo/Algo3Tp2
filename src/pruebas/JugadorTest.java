package pruebas;
import static org.junit.Assert.*;

import org.junit.Test;


import juego.Jugador;

public class JugadorTest {
	
	@Test
    public void testCrear() {
        Jugador jugador = new Jugador();
        assertTrue (jugador.puntaje().puntos() == 10000);
	}

}
