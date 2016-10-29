/**
 *  This class is responsible for rendering the menu for the tutorial screen.
 */
package axohEngine2;
import java.awt.Graphics2D;
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

	public void render(JFrame frame, Graphics2D g2d){ //Main Frame and Graphics 2D From Judgement.java
	
		//Get and display the image
		try{
			BufferedImage img = ImageIO.read(new File("./res/tutorials/keys/Menu.png"));
			g2d.drawImage(img, 0,0,frame.getWidth(),frame.getHeight(), null); //Used to fill the entire frame with the image.
					
		}
		catch (IOException ioe){
			System.out.println("Image Error"); //The Image Cannot Be Found
		}
	}
}




