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
		EstadoDeSalud gradoDeVulnerabilidad = this.nave.vulnerable(municion);
		
		if (gradoDeVulnerabilidad == EstadoDeSalud.DESTRUIDO){
			this.destruir();
			this.nave.recibirImpacto(municion);
		}
		
		else if (gradoDeVulnerabilidad == EstadoDeSalud.DANADO){
			if(this.estado == EstadoDeSalud.SANO){
				this.estado = EstadoDeSalud.DANADO;
				this.nave.recibirImpacto(municion);
			}
			if(this.estado == EstadoDeSalud.DANADO){
				this.estado = EstadoDeSalud.DESTRUIDO;
				this.nave.recibirImpacto(municion);
			}
		}
		
	}
	
	
	public EstadoDeSalud estado(){
		return this.estado;
	}
	
	
	

}
