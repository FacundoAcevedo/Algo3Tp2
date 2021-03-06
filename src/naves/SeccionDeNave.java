package naves;
import java.io.Serializable;

import municiones.Municion;
public class SeccionDeNave implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1650899062038379271L;
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
	
	public boolean esPopa(){
		return (this.tipoDeSeccion == TipoDeSeccion.POPA);
	}
	
	public boolean esProa(){
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
	
	public String obtenerTipoDeNave(){
		return this.nave.getClass().getSimpleName();
	}
	
	public boolean naveEstaDestruida(){
		return (this.nave.estado() == EstadoDeSalud.DESTRUIDO);
	}
	
	public TipoDeSeccion obtenerTipoDeSeccion(){
		return this.tipoDeSeccion;
	}

	public int obtenerPorcentajeDeSalud() {
		return this.nave.porcentajeDeVida;
	}
	
}
