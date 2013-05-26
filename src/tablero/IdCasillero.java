package tablero;

import excepciones.ErrorIdCasilleroInvalido;

public class IdCasillero {
	protected int x,y;
	public IdCasillero(int x, int y) throws ErrorIdCasilleroInvalido{
		if (x < 0 || x > 99 || x < 0 || x > 99){
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

}
