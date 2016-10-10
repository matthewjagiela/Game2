package axohEngine2.project;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class Inventory 
{
	public void render(JFrame frame, Graphics2D g2d) 
	{
		g2d.setColor(Color.white);
		g2d.drawRect(770, 100, 350, 460);  
        g2d.setColor(Color.black);  
        g2d.fillRect(770, 100, 350, 460);
		
	}
}
