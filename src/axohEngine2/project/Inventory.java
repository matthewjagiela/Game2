package axohEngine2.project;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.TextLayout;
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
        Font f;
        TextLayout t1;
        Shape shape;
        //-----------SCRUM CYCLE 1 NEW-------------//
        text1 = "Bomb   * 1";	
		f = new Font("Helvetica", Font.PLAIN, 30); //Font Helvetica Size 30
		t1 = new TextLayout(text1, f, g2d.getFontRenderContext());
		shape = t1.getOutline(null);
        g2d.setColor(Color.white); 
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));//Keep at one to render properly
		g2d.translate(780, 140);
		g2d.draw(shape);
		g2d.fill(shape); //Fill the outline so it is readable
		g2d.translate(-780, -140);
		
		text2 = "Potion  * 1";	
		f = new Font("Helvetica", Font.PLAIN, 30); //Font Helvetica Size 30
		t1 = new TextLayout(text2, f, g2d.getFontRenderContext());
		shape = t1.getOutline(null);
        g2d.setColor(Color.white); 
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));//Keep at one to render properly
		g2d.translate(780, 180);
		g2d.draw(shape);
		g2d.fill(shape); //Fill the outline so it is readable
		g2d.translate(-780, -180);
	}
}
