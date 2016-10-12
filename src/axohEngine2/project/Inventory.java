package axohEngine2.project;

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
		g2d.setColor(Color.white);
		g2d.drawRect(770, 100, 350, 460);  
        g2d.setColor(Color.black);  
        g2d.fillRect(770, 100, 350, 460);
        
        String text;
        Font f;
        TextLayout t1;
        Shape shape;
        
        text = "Bombs";	
		f = new Font("Helvetica", Font.PLAIN, 30);
		t1 = new TextLayout(text, f, g2d.getFontRenderContext());
		shape = t1.getOutline(null);
        g2d.setColor(Color.white); 
		g2d.translate(780, 140);
		g2d.draw(shape);
		g2d.translate(-780, -140);
		
		
		
	}
	// Is it working?
}
