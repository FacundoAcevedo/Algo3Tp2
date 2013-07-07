package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vista.VistaBatalla;

public class ControladorBotonesTablero implements ActionListener{
	public ControladorBotonesTablero(){

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton boton = (JButton) arg0.getSource();

		int x = ((int[])boton.getClientProperty("id"))[0];
		int y = ((int[])boton.getClientProperty("id"))[1];
		System.out.print(x+","+y+"\n");
		
	}

}
