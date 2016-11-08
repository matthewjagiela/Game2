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

// SCRUM CYCLE 3 NEW
// For pop-up msg on item dropping
public class ItemGetsScreen {
	
	private String message;
	private String itemName;
	
	
	Font f;
    TextLayout t1;
    Shape shape;
    
    // info setter to be called before rendering the pop up screen
    public Boolean setMessage(String msg, String in){
    	message = msg;
    	itemName = in;
    	return true;
    }
	
	public void render(JFrame frame, Graphics2D g2d)
	{
		//Item itemShow;
		
	    f = new Font("Helvetica", Font.PLAIN, 25);
		// for description card    
		// print icon and item name, item count
		showIcon("./res/textures/items/" + itemName + ".png", 530, 175, 33, 33, g2d);
		printString(message, 565, 200, g2d);
	}
	
	// print new info onto the rectangle
	private void printString(String text, int axis_x, int axis_y, Graphics2D g2d)
	{
		t1 = new TextLayout(text, f, g2d.getFontRenderContext());
		shape = t1.getOutline(null);
	    g2d.setColor(Color.black); 
	    g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));//Keep at one to render properly
	    g2d.translate(axis_x, axis_y);
		g2d.draw(shape);
		g2d.fill(shape); //Fill the outline so it is readable
		g2d.translate(-axis_x, -axis_y);
	}
		
	// print icons onto the rectangle
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
}
