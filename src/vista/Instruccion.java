package vista;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
 
 public class Instruccion extends JFrame
 {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private JButton botonSalir;
    private JTextArea areaDeInformacion;
    private String informacion;
 
    public Instruccion()
    {
       super( "Instrucciones" );
       Container contenedor = getContentPane();
       Image icono = Toolkit.getDefaultToolkit().getImage("estaticos/icono.png");
       setIconImage(icono);
 
       informacion = "                        Detruye las naves de la computadora!"+ "\n" + "\n"+
       		"Tu objetivo es terminar el juego destruyendo las naves con la mayor cantidad de puntos posible." +"\n" +
       				"Descuento de puntos:"+"\n"+ "-Paso del tiempo: 10 puntos por turno"+"\n"+
       		"-Disparos: distinta cantidad de putos segun el disparo"+"\n"+"\n"+ "Naves:"+ "\n"+
       				"-2 lanchas"+"\n"+"-2 destructores: solo lo impactan los disparos directos"+"\n"+
       		"-1 buque: cualquier impacto en cualquier parte lo destruye por completo"+"\n"+
       				"-1 portaaviones" + "\n" + "-1 rompehielos: Cada parte requiere dos impactos para ser destruida";
       
       final ImageIcon imageIcon = new ImageIcon("estaticos/Fondos/fondoInstrucciones.jpg");
       areaDeInformacion = new JTextArea(){
       	private static final long serialVersionUID = 1L;
			Image image = imageIcon.getImage();
           {
             setOpaque(false);
           }

           public void paint(Graphics g) {
             g.drawImage(image, 0, 0, this);
             super.paint(g);
           }
         };
       this.setResizable(false);
       areaDeInformacion.setText(informacion);
       areaDeInformacion.setForeground(Color.WHITE);
       areaDeInformacion.setEditable(false);
       areaDeInformacion.setLineWrap(true);
       areaDeInformacion.setWrapStyleWord(true);

       contenedor.add(areaDeInformacion);
       
 
       botonSalir = new JButton("OK");
       botonSalir.addActionListener(new ActionListener ()
       {
       	   public void actionPerformed (ActionEvent e)
       	   {
       		   if (e.getSource()== botonSalir) {
       	            dispose();
       	   } 
       	}
       }); 
       
       contenedor.add( botonSalir, BorderLayout.SOUTH );
 
       setSize( 420, 420 );
       setVisible( true );
       Border border = BorderFactory.createLineBorder(Color.BLACK);
       areaDeInformacion.setBorder(BorderFactory.createCompoundBorder(border, 
               BorderFactory.createEmptyBorder(20, 20, 20, 20)));
 
    } 
 
    public static void main( String args[] )
    {
       Instruccion aplicacion = new Instruccion();
       aplicacion.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }
 
 } 