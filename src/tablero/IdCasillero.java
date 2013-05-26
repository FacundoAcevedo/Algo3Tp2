package tablero;

import excepciones.ErrorIdCasilleroInvalido;

public class IdCasillero {
	protected int x,y;
	public IdCasillero(int x, int y) throws ErrorIdCasilleroInvalido{
		if (x < 0 || x > 99 || y < 0 || y > 99){
			throw  new ErrorIdCasilleroInvalido();
		}
		this.x = x;
		this.y = y;		
		
	}
	
	public int x(){
		return x;
	}

	public int y(){
		return y;
	}

	public int[] id() {
		int[] duplaPosicion = new int[2];
		duplaPosicion[0] = x;
		duplaPosicion[1] = y;
		return duplaPosicion;
		
		
	}

}
