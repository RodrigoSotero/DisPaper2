/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Rodrigo Sotero
 */
public class Fondo extends JPanel { // formulario dentro de un formularios JPanel
    
    public Fondo(){
        setLayout(null);
    }
    
    @Override
    public void paintComponent(Graphics g){
      Dimension tamaño=getSize();
      String ruta="Dis-paperfondo.jpg";
      ImageIcon imageFondo=new ImageIcon(getClass().getResource(ruta));
      g.drawImage(imageFondo.getImage(), 0,0, tamaño.width, tamaño.height,this);
      setOpaque(false);
      super.paintComponent(g);
    }
    
}
