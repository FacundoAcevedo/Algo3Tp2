package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vista.VistaBatalla;

public class ControladorBotonesTablero implements ActionListener{
	private VistaBatalla vista;
	public ControladorBotonesTablero(){

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton boton = (JButton) arg0.getSource();

		
		System.out.print(boton.getClientProperty("id"));
		
	}

}
