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

/**
 * 
 * @author matthewjagiela
 * This will handle rendering the chest inventory screen,
 * the items that appear in the chest
 * 
 */
public class Chest {
	private String[] chestItems = new String[3];
	Font f;
	TextLayout t1;
	Shape shape;
	//Just for rendering purposes.
	public void render(JFrame frame, Graphics2D g2d){ //Render the screen
		g2d.setColor(Color.white);
		g2d.drawRect(770, 100, 350, 460);
		g2d.setColor(Color.black);
		g2d.fillRect(770, 100, 350, 460);
		chestItems[0] = "Bomb   *            1";
		chestItems[1] = "Potion  *            1";
		chestItems[2] = "Sword  *            1";
		f = new Font("Helvetica", Font.PLAIN, 30);
		//--------------------------------//
        showIcon("./res/textures/items/bomb.png", 810, 110, 40, 40, g2d);
        printString(chestItems[0], 870, 140, g2d);
        setStringColor(chestItems[0], 0, 870, 140, g2d);
        
        showIcon("./res/textures/items/potion.png", 810, 150, 40, 40, g2d);
        printString(chestItems[1], 870, 180, g2d);
        setStringColor(chestItems[1], 1, 870, 180, g2d);
        
        showIcon("./res/textures/items/sword.png", 810, 190, 40, 40, g2d);
        printString(chestItems[2], 870, 220, g2d);
        setStringColor(chestItems[2], 2, 870, 220, g2d);
        f = new Font("Helvetica", Font.PLAIN, 25); //Font Helvetica Size 25
		printString("-----------Press X to exit-----------", 772, 550, g2d);
        
	}
	private void showIcon(String filename, int axis_x, int axis_y, int width, int length, Graphics2D g2d)
	{
		try 
		{
	        BufferedImage img = ImageIO.read(new File(filename));
	        g2d.drawImage(img, axis_x, axis_y, width, length, null);
	        } catch (IOException ioe) {
	        	ioe.printStackTrace();
	        }
	}
	
	private void printString(String text, int axis_x, int axis_y, Graphics2D g2d)
	{
		t1 = new TextLayout(text, f, g2d.getFontRenderContext());
		shape = t1.getOutline(null);
	    g2d.setColor(Color.white); 
	    g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));//Keep at one to render properly
	    g2d.translate(axis_x, axis_y);
		g2d.draw(shape);
		g2d.fill(shape); //Fill the outline so it is readable
		g2d.translate(-axis_x, -axis_y);
	}
	private void setStringColor(String text, int i, int axis_x, int axis_y, Graphics2D g2d)
	{
		g2d.setColor(Color.WHITE);
		
		t1 = new TextLayout(text, f, g2d.getFontRenderContext());
		shape = t1.getOutline(null);
		g2d.translate(axis_x, axis_y);
		g2d.fill(shape);
		g2d.translate(-axis_x, -axis_y);
	}
}
