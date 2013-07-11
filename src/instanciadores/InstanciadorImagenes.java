package instanciadores;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import vista.EditorDeImagenes;

import municiones.Municion;
import naves.EstadoDeSalud;
import naves.SeccionDeNave;
import naves.Sentido;

public class InstanciadorImagenes {
	// Instancia las imagenes segun el tipo

	// static public ImageIcon nave(SeccionDeNave seccion){
	static public ImageIcon nave(List<SeccionDeNave> coleccionSeccionesDeNave) {

		// Se fija que el tipo de seccion, en donde esta ubicada(proa, popa,
		// etc), el sentido y el estado
		// Va formando el nombre de la imagen segun su estado
		String tipo;
		String estado;
		String posicion;
		String sentido;
		Sentido sentidoPreProcesado;

		String rutaImagen;
		LinkedList<BufferedImage> listaImagenes = new LinkedList<BufferedImage>();
		// V= vertical
		// U = up
		// H= horizontal
		// D= diagonal
		// R= right
		// L =left
		// D= down
		for (SeccionDeNave seccion : coleccionSeccionesDeNave) {
			// Obtengo el tipo:
			tipo = seccion.obtenerTipoDeNave();

			// Obtengo la ubicacion de la seccion
			if (!seccion.esProa() && !seccion.esPopa()) {
				posicion = "seccionMedia";
			} else if (seccion.esProa()) {
				posicion = "proa";
			} else { // popa
				posicion = "popa";
			}

			// obtengo el sentido

			sentidoPreProcesado = seccion.sentido();
			if (sentidoPreProcesado == Sentido.OESTE) {
				sentido = "HL";
			} else if (sentidoPreProcesado == Sentido.ESTE) {
				sentido = "HR";
			} else if (sentidoPreProcesado == Sentido.NORESTE) {
				sentido = "DRU";
			} else if (sentidoPreProcesado == Sentido.NOROESTE) {
				sentido = "DLU";
			} else if (sentidoPreProcesado == Sentido.SUDESTE) {
				sentido = "DLD";
			} else if (sentidoPreProcesado == Sentido.SUDOESTE) {
				sentido = "DRD";
			} else if (sentidoPreProcesado == Sentido.SUR) {
				sentido = "VD";
			} else {// NORTE
				sentido = "VU";
			}

			// obtengo el estado:

			if (seccion.estado() == EstadoDeSalud.SANO) {
				estado = "Sana";
			} else {
				estado = "Daniada";
			}

			rutaImagen = "estaticos/Naves/" + posicion + estado + sentido
					+ ".png";

			BufferedImage imagen = null;
			try {
				imagen = ImageIO.read(new File(rutaImagen));
				listaImagenes.add(imagen);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return EditorDeImagenes.mixDesdeBufferedImage(listaImagenes);
	}

	//
	static public ImageIcon municion(List<Municion> municiones) {
		LinkedList<BufferedImage> listaImagenes = new LinkedList<BufferedImage>();

		for (Municion municion : municiones) {
			String nombreMunicion = municion.obtenerNombre();
			String rutaImagen = "estaticos/Minas/" + nombreMunicion + ".png";
			BufferedImage imagen = null;
			try {
				imagen = ImageIO.read(new File(rutaImagen));
				listaImagenes.add(imagen);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return EditorDeImagenes.mixDesdeBufferedImage(listaImagenes);

	}

}
