package vista;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
//...
 
public class JBackgroundPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image imagen;
 
    public JBackgroundPanel(){
    imagen = new ImageIcon("estaticos/Fondos/fondoAgua.jpg").getImage();

    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(),
                        this);
 
        setOpaque(false);
        super.paint(g);
    }
 
}