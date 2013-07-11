package vista;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class EditorDeImagenes {
	static public ImageIcon mixDesdeBufferedImage(LinkedList<BufferedImage> listaDeImagenes){
		//Combina una lista de imagenes en formato BufferedImage y devuelve un ImageIcon

		// Creo la nueva imagen con el tamaño de cualquiera de las obtenidas
		int ancho = listaDeImagenes.get(0).getWidth();
		int alto = listaDeImagenes.get(0).getHeight();
		BufferedImage combined = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);

		// Cargo todas las imagenes manteniedo la trasnparencia
		Graphics graficos = combined.getGraphics();
		for (BufferedImage imagen : listaDeImagenes){
			graficos.drawImage(imagen, 0, 0, null);
		}
		return new ImageIcon(combined);
		
	}
	static public ImageIcon mixDesdeIconImage(LinkedList<ImageIcon> listaDeImagenes){
		//Combina una lista de imagenes en formato ImageIcon y devuelve un ImageIcon
		LinkedList<BufferedImage> listaDeImagenesCasteadasBufferImage = new LinkedList<BufferedImage>(); 
		for ( ImageIcon imagen : listaDeImagenes){
			listaDeImagenesCasteadasBufferImage.add((BufferedImage) imagen.getImage());
		}
		return mixDesdeBufferedImage(listaDeImagenesCasteadasBufferImage);
	}

}
