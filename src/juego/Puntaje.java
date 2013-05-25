package juego;

public class Puntaje {

    private int puntos;

    public Puntaje() {
            puntos = 10000;
    }

    public int puntos() {
            return puntos;
    }

    public void descontarPuntos(int puntosPerdidos) {
            puntos -= puntosPerdidos;
    }

}
