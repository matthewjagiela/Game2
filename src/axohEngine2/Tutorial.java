/**
 *  This class is responsible for rendering the menu for the tutorial screen.
 */
package axohEngine2;

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
 * @author matthewjagiela
 *
 */
public class Tutorial {

	public void render(JFrame frame, Graphics2D g2d){
		g2d.setColor(Color.white);
		g2d.drawRect(770,100,1200,700);
		g2d.setColor(Color.black);
		g2d.fillRect(770,100,1200,700);
		
		String movementKeys;
		String actionKeys;
		Font f;
		TextLayout t1;
		Shape shape;
		
		try{
			BufferedImage img = ImageIO.read(new File("./res/tutorials/keys/Menu.png"));
			g2d.drawImage(img, 0,0,1200,650, null);
					
		}
		catch (IOException ioe){
			System.out.println("Image Error");
		}
	}
}
