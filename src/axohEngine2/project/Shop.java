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
import java.util.concurrent.CountDownLatch;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Shop {

	int index = 0;
	int maxItemType;
	
	// item counter
	int count[] = {999, 999, 999};
	
    String textExit;
    String text1, text2, text3;
    Font f;
    TextLayout t1;
    Shape shape;
	
    public Shop (int ind, int num) {
    	this.index = ind;
    	this.maxItemType = num;
    }
    
	public int getIndex()
	{
		return index;
	}
	
	public void setIndex(int i)
	{
		index = i;
		if(index >= maxItemType){
			index = 0;
		}
		
		if(index < 0){
			index = maxItemType -1;
		}
	}
	
	public void indexDown()
	{
		this.setIndex(this.getIndex() + 1);
	}
	
	public void indexUp()
	{
		this.setIndex(this.getIndex() - 1);
	}
	
	public void render(JFrame frame, Graphics2D g2d)
	{
		//-------------USED TO GENERATE BLACK BOX BACKGROUND--------//
		g2d.setColor(Color.white);
		g2d.drawRect(770, 100, 350, 460);  
        g2d.setColor(Color.black);  
        g2d.fillRect(770, 100, 350, 460);
        
       //-----------SCRUM CYCLE 2 NEW-------------//
        int x = 780;
		int y = 120 + this.index * 40;
		g2d.setColor(Color.yellow);
		g2d.fillOval(x, y, 24, 24);
        
        f = new Font("Helvetica", Font.PLAIN, 30);
        
        text1 = "Bomb   *        " + count[0];
        showIcon("./res/textures/items/bomb.png", 810, 110, 40, 40, g2d);
        printString(text1, 870, 140, g2d);
        setStringColor(text1, 0, 870, 140, g2d);
        
        text2 = "Potion  *        " + count[1];
        showIcon("./res/textures/items/potion.png", 810, 150, 40, 40, g2d);
        printString(text2, 870, 180, g2d);
        setStringColor(text2, 1, 870, 180, g2d);
        
        text3 = "Sword  *        " + count[2];
        showIcon("./res/textures/items/sword.png", 810, 190, 40, 40, g2d);
        printString(text3, 870, 220, g2d);
        setStringColor(text3, 2, 870, 220, g2d);
        
        
        
        /*
		textExit = "-----Thanks for shopping with us!------";	
		f = new Font("Helvetica", Font.PLAIN, 25); //Font Helvetica Size 25
		printString(textExit, 772, 550, g2d);
		*/
        
		// for description card
		g2d.setColor(Color.white);
		g2d.drawRect(550, 100, 200, 200);  
        g2d.setColor(Color.black);  
        g2d.fillRect(550, 100, 200, 200);
        
        f = new Font("Helvetica", Font.PLAIN, 25); //Font Helvetica Size 25
        
        if (this.index == 0)
        {
        	showIcon("./res/textures/items/bomb.png", 560, 110, 40, 40, g2d);
        	printString("Bomb", 620, 140, g2d);
        	printString("HP - 7", 560, 180, g2d);
        	printString("Price: 50 Ruby", 560, 220, g2d);
        }
        else if (this.index == 1)
        {
        	showIcon("./res/textures/items/potion.png", 560, 110, 40, 40, g2d);
        	printString("Potion", 620, 140, g2d);
        	printString("HP + 30", 560, 180, g2d);
        	printString("Price: 25 Ruby", 560, 220, g2d);
        }
        
        else
        {
        	showIcon("./res/textures/items/sword.png", 560, 110, 40, 40, g2d);
        	printString("Sword", 620, 140, g2d);
        	printString("Attack + 10", 560, 180, g2d);
        	printString("Price: 200 Ruby", 560, 220, g2d);
        }
	}
	
	// count down the quantity of the bought item by 1
	public boolean itemBought(int ind)
	{
		count[ind]--;
		return true;
	}
	
	// print new info onto the rectangle
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
	
	private void setStringColor(String text, int i, int axis_x, int axis_y, Graphics2D g2d)
	{
		if (this.index == i) 
        {
        	g2d.setColor(Color.yellow);
        }
        else
        {
        	g2d.setColor(Color.white);
        }
		
		t1 = new TextLayout(text, f, g2d.getFontRenderContext());
		shape = t1.getOutline(null);
		g2d.translate(axis_x, axis_y);
		g2d.fill(shape);
		g2d.translate(-axis_x, -axis_y);
	}
}
