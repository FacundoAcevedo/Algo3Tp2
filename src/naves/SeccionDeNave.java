package naves;
import municiones.Municion;
public class SeccionDeNave {
	private EstadoDeSalud estado;
	private Nave nave; //Referencia a la nave donde pertenece esta seccion
	
	public SeccionDeNave(Nave nave) {
		this.nave = nave;
		this.estado = EstadoDeSalud.SANO;
	}
	
	public void destruir(){
		this.estado = EstadoDeSalud.DESTRUIDO;
		this.nave.actualizarEstado();
	}
	public void recibirImpacto(Municion municion){
		if (this.nave.vulnerable(municion)){
			this.destruir();
			this.nave.recibirImpacto(municion);//algunas naves no hacen nada con este metodo, otras si.
		}
		
	}
	
	
	public EstadoDeSalud estado(){
		return this.estado;
	}
	
	
	

}
