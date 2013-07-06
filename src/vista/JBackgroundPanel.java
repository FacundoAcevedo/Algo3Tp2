package vista;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
//...
 
public class JBackgroundPanel extends JPanel {
 
    private Image imagen;
 
    public JBackgroundPanel(){
//    imagen = new ImageIcon(getClass().getResource("./estaticos/fondoAgua.jpg")).getImage();
    imagen = new ImageIcon("estaticos/fondoAgua.jpg").getImage();

    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(),
                        this);
 
        setOpaque(false);
        super.paint(g);
    }
 
}