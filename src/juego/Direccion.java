package juego;

/*Los enum van con la primer letra en mayusculas*/ 


public class Direccion {
	/*Es una idea, hay que ver si es lo correcto, pero como siempre dijeron
	 * que no hay que usar strings, lo que podemos hacer es usar tipos enumerados
	 * como estos:
	 */
	Sentido miSentido;

	
	public Direccion (Sentido unSentido){
		this.miSentido = unSentido;		
	}
	public Sentido sentido(){
		return this.miSentido;
	}
	
	public void invertirSentido(){
		if (this.miSentido == Sentido.NORTE) this.miSentido = Sentido.SUR;
		if (this.miSentido == Sentido.SUR) this.miSentido = Sentido.NORTE;
		if (this.miSentido == Sentido.NOROESTE) this.miSentido = Sentido.SUDOESTE;
		if (this.miSentido == Sentido.NORESTE) this.miSentido = Sentido.SUDOESTE;
		if (this.miSentido == Sentido.SUDESTE) this.miSentido = Sentido.NOROESTE;
		if (this.miSentido == Sentido.SUDOESTE) this.miSentido = Sentido.NORESTE;
	}
}
