package tablero;

import java.util.LinkedList;

import excepciones.ErrorIdCasilleroInvalido;

import municiones.Municion;
import naves.Nave;

public interface TableroComunicable {
	public  String estandarizarId(int[] id);
	public void construirYPosicionarLasNavesAleatoriamente();
	public LinkedList<Nave> devolverNaves();
	public Casillero obtenerCasillero(int[] id) throws ErrorIdCasilleroInvalido;
	
	public boolean tieneNaves();
	public int cantidadTotalDeNaves();
	public int cantidadDeNavesActivas();
	public int cantidadDeNavesDestruidas();
	public void actualizarTablero();
	public void posicionarNaveEnTablero(Nave nave, int[] posicionDeProa);
	public void ponerMuncion(Municion municion, int[] id);
	public void imprimirTablero();

}
