package axohEngine2.project;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Inventory 
{
	public void render(JFrame frame, Graphics2D g2d)
	{
		//-------------USED TO GENERATE BLACK BOX BACKGROUND--------//
		g2d.setColor(Color.white);
		g2d.drawRect(770, 100, 350, 460);  
        g2d.setColor(Color.black);  
        g2d.fillRect(770, 100, 350, 460);
        
        String text1;
        String text2;
        String text3;
        Font f;
        TextLayout t1;
        Shape shape;
        //-----------SCRUM CYCLE 1 NEW-------------//
        
        // read pic for item from ./res/textures/items/, 
     	// not sure if the directory works right on everyone's eclipse
     	// we can try
        try {
        	BufferedImage img_bomb = ImageIO.read(new File("./res/textures/items/bomb.png"));
        	g2d.drawImage(img_bomb, 780, 110, 40, 40, null);
        } catch (IOException ioe) {
        	ioe.printStackTrace();
        }
        text1 = "Bomb   * 1";	
		f = new Font("Helvetica", Font.PLAIN, 30); //Font Helvetica Size 30
		t1 = new TextLayout(text1, f, g2d.getFontRenderContext());
		shape = t1.getOutline(null);
        g2d.setColor(Color.white); 
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));//Keep at one to render properly
		g2d.translate(840, 140);
		g2d.draw(shape);
		g2d.fill(shape); //Fill the outline so it is readable
		g2d.translate(-840, -140);
		
		try {
        	BufferedImage img_bomb = ImageIO.read(new File("./res/textures/items/potion.png"));
        	g2d.drawImage(img_bomb, 780, 150, 40, 40, null);
        } catch (IOException ioe) {
        	ioe.printStackTrace();
        }
		
		text2 = "Potion  * 1";	
		f = new Font("Helvetica", Font.PLAIN, 30); //Font Helvetica Size 30
		t1 = new TextLayout(text2, f, g2d.getFontRenderContext());
		shape = t1.getOutline(null);
        g2d.setColor(Color.white); 
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));//Keep at one to render properly
		g2d.translate(840, 180);
		g2d.draw(shape);
		g2d.fill(shape); //Fill the outline so it is readable
		g2d.translate(-840, -180);
		
		text3 = "-----------Press X to exit-----------";	
		f = new Font("Helvetica", Font.PLAIN, 25); //Font Helvetica Size 30
		t1 = new TextLayout(text3, f, g2d.getFontRenderContext());
		shape = t1.getOutline(null);
        g2d.setColor(Color.white); 
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));//Keep at one to render properly
		g2d.translate(772, 550);
		g2d.draw(shape);
		g2d.fill(shape); //Fill the outline so it is readable
		g2d.translate(-772, -550);
	}
}
