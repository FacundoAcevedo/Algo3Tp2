package tablero;

import excepciones.ErrorIdCasilleroInvalido;

public class IdCasillero {
	protected int x,y;
	public IdCasillero(int x, int y) throws ErrorIdCasilleroInvalido{
		IdCasillero.validarId(x, y);
		this.x = x;
		this.y = y;		
		
	}
	
	public int x(){
		return this.x;
	}

	public int y(){
		return this.y;
	}

	public int[] id() {
		int[] duplaPosicion = new int[2];
		duplaPosicion[0] = this.x;
		duplaPosicion[1] = this.y;
		return duplaPosicion;
		
		
	}
	// metodo estatio para validar id
	static public void validarId(int x, int y) throws ErrorIdCasilleroInvalido{
		if (x < 0 || x > 9 || y < 0 || y > 9){
			throw  new ErrorIdCasilleroInvalido();
		}
	}
	static public void validarId(int [] id) throws ErrorIdCasilleroInvalido{
		int x = id[0], y = id[1];
		if (x < 0 || x > 9 || y < 0 || y > 9){
			throw  new ErrorIdCasilleroInvalido();
		}
	}

}
