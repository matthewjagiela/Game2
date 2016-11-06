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

import axohEngine2.entities.Mob;

// --------------------SCRUM CYCLE 2 NEW--------------------------//

public class Status 
{
	 String textLevel = "LEVEL ";
     String textHp = "HP: ";
     String textAttack = "Atk: ";
     String textDefense = "Def: ";
     String textIntelligence = "Int:  ";
     String textExit = "-----------Press X to exit-----------";
     String filename;
     
     // status -- will be substituted by real numbers in the future
     int level = 1;
     int hp;
     int attack = 10;
     int defense = 7;
     int intelligence = 10;
     
     Font f;
     TextLayout t1;
     Shape shape;
     
	public void render(JFrame frame, Graphics2D g2d)
	{
		//-------------USED TO GENERATE BLACK BOX BACKGROUND--------//
		g2d.setColor(Color.white);
		g2d.drawRect(770, 140, 350, 360);  
        g2d.setColor(Color.black);  
        g2d.fillRect(770, 140, 350, 360);
        
        // set font
        f = new Font("Helvetica", Font.PLAIN, 30); //Font Helvetica Size 30
        
        // level info
        printString(textLevel + level, 780, 175, g2d);
        
        // corresponding status
        // hp
        showIcon("./res/textures/status/hp.png", 780, 190, 40, 40, g2d);
        printString(textHp + hp, 840, 220, g2d);
        // attack
        showIcon("./res/textures/status/attack.png", 780, 230, 40, 40, g2d);
        printString(textAttack + attack, 840, 260, g2d);
        // defense
        showIcon("./res/textures/status/defense.png", 780, 270, 40, 40, g2d);
        printString(textDefense + defense, 840, 300, g2d);
        // intelligence
        showIcon("./res/textures/status/intel.png", 780, 310, 40, 40, g2d);
        printString(textIntelligence + intelligence, 840, 340, g2d);
        	
		
        // exit
        f = new Font("Helvetica", Font.PLAIN, 25); 
        printString(textExit, 772, 480, g2d);
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
	
	public void setLevel(int level) {
		this.level = level;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	// print icons onto the rectangle
	private void showIcon(String filename, int axis_x, int axis_y, int width, int length, Graphics2D g2d)
	{
		try {
        	BufferedImage img = ImageIO.read(new File(filename));
        	g2d.drawImage(img, axis_x, axis_y, width, length, null);
        } catch (IOException ioe) {
        	ioe.printStackTrace();
        }
	}
	public void setHealth(int health){
		hp = health;
	}
	public void setDefense(int defense){
		this.defense = defense;
	}
}

