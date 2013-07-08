package naves;
import municiones.Municion;
public class SeccionDeNave {
	private EstadoDeSalud estado;
	private Nave nave; //Referencia a la nave donde pertenece esta seccion
	private TipoDeSeccion tipoDeSeccion;
	
	public SeccionDeNave(Nave nave, TipoDeSeccion tipoDeSeccion) {
		this.nave = nave;
		this.estado = EstadoDeSalud.SANO;
		this.tipoDeSeccion = tipoDeSeccion;
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
			else if(this.estado == EstadoDeSalud.DANADO){
				this.estado = EstadoDeSalud.DESTRUIDO;
				this.nave.recibirImpacto(municion);
			}
		}
		
	}
	
	public Sentido sentido(){
		return this.nave.direccion();
	}
	
	private boolean esPopa(){
		return (this.tipoDeSeccion == TipoDeSeccion.POPA);
	}
	
	private boolean esProa(){
		return (this.tipoDeSeccion == TipoDeSeccion.PROA);
	}
	
	public void invertirPopaProa(){
		if (this.esProa()){
			this.tipoDeSeccion = TipoDeSeccion.POPA;
		}else if(this.esPopa()){
			this.tipoDeSeccion = TipoDeSeccion.PROA;
		}
	}
	
	public void invertirSentido(){
		this.nave.invertirSentido();
	}
	
	public EstadoDeSalud estado(){
		return this.estado;
	}
	
	public String obtenerTipo(){
		return this.nave.getClass().getSimpleName();
	}
	
	public TipoDeSeccion obtenerTipoDeSeccion(){
		return this.tipoDeSeccion;
	}
	
}
