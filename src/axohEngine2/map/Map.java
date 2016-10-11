/****************************************************************************************************
 * @author Travis R. Dewitt
 * @version 1.1
 * Date: June 18, 2015
 * 
 * 
 * Title: Map
 * Description: This class takes in an array of tile blueprints and then returns back a working "map"
 * in the design you made it with the graphics you told each tile to have.
 * 
 * This work is licensed under a Attribution-NonCommercial 4.0 International
 * CC BY-NC-ND license. http://creativecommons.org/licenses/by-nc/4.0/
 ****************************************************************************************************/
//Package
package axohEngine2.map;

//Imports
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.TextLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;

import axohEngine2.entities.Mob;
import axohEngine2.entities.RI;
import axohEngine2.project.TYPE;

public class Map {
	
	/*******************
	 * Variables
	 *******************/
	//mapHeight - In tiles, how tall the map is
	//mapWIdth - In tiles, how wide the map is
	//mapTiles - The array that holds all of the finished tiles
	//spriteSize - The size in pixels of each tile
	//_name - The name of the given map, does nothing more than to give the use and idea of what map they are looking at
	private int mapHeight;
	private int mapWidth;
	private Tile[] mapTiles;
	private Tile[] overlayTiles;
	private int spriteSize;
	private String _name;
	private ArrayList<Mob> mobs;
	
	private LinkedList<Tile> layeredTiles = new LinkedList<Tile>();
	private Rectangle camera;
	Boolean first;
	
	private LinkedList<Mob> npcMobsOnScreen = new LinkedList<Mob>(); // used for collisions to only check relevant sprites
	
	
		
	/************************************************************************
	 * Constructor
	 * 
	 * The constructor automatically duplicates all of the blueprinted tiles given in the Tile[]
	 * in to the needed shape of a map. The spriteSize is also taken from the first Tile in the array.
	 * The purpose of all of this is because having tons of tile data means nothing, by instantiating
	 * each individual tile, each tile becomes unique and can be accessed with its own specific properties.
	 * 
	 * @param frame - A JFrame that the graphics will be dsiplayed on (The window)
	 * @param g2d - The Graphics2D object that will be used to disaplay images on the JFrame
	 * @param tiles - An array of tile blueprints which will be set up in the map for display in game.
	 * @param mapWidth - An int identifying the width of the map in tiles
	 * @param mapHeight - An int identifying the height of an array in tiles
	 * @param name - A String which identifies the map, this is strictly a user identifier, it isn't used in any logic 
	 *************************************************************************/
	public Map(JFrame frame, Graphics2D g2d, Tile[] tiles, int mapWidth, int mapHeight, String name, ArrayList<Mob> mobs, Tile[] overlayTiles) {
		this.mapHeight = mapHeight;
		this.mapWidth = mapWidth;
		mapTiles = tiles;
		_name = name;
		
	//	for(int i = 0; i < mapTiles.length; i++) {
	//		mapTiles[i] = new Tile(mapTiles[i], frame, g2d);
	//	}
		spriteSize = tiles[0].getSpriteSize();
		
		this.mobs = mobs;
		this.overlayTiles = overlayTiles;
	}
	
	/****************************************************************************************
	 * The Render method goes to each tile in the specified map and renders it at a specific location.
	 * It also renders any mobs from each tile. These mobs start at the tile position, but when their x or y
	 * moves it is relative to the tile and does appear moving on the world map. 
	 * 
	 * About the 1D Array:
	 * The map is a one dimensional array of individual unique Tiles. In order to put these tiles in the
	 * correct spot, relative to how it was designed in the map database, a special way in moving around
	 * the array was needed. Using this specific way, a one dimensional array can be used instead of 2D, which
	 * saves space and optimizes the framerate of the game, allowing for larger more complicated maps.
	 * x + y * width
	 * 
	 * @param frame - The JFrame which the map will be rendered in (The window)
	 * @param g2d - The Graphics2D object wich is used to display images
	 * @param xx - An offset in the x direction which moves the map around
	 * @param yy - An offset in the y direction which moves the map around.
	 * 
	 * Offsets are used to render a piece of a map in the window at a time to indicate placement
	 * in the world.
	 ********************************************************************************************/	
	
	 /*********************************************************************************************************************
	 * ***** REACTION TECH SOLUTIONS EDIT ***** 
	 * ****************************************
	 * MODIFICATIONS TO THIS UP THERE ^^ 
	 * THE CODE FOR UP THERE IS BELOW THIS NEW RENDER METHOD, I LEFT IT COMMENTED OUT 
	 * I LIKE MINE BETTER but either will work 
	 *
	 * The entire reason that this needed revisions is because the old render method drew the entire map each frame 
	 * Instead, it should draw only the part of the map that is viewable on the screen 
	 * This makes the map drawing always a constant time, rather than a variable time, which means maps can be as big as they want without any difference to the FPS
	 * Also, it just makes sense, why render what the user can't see? Waste not want not.
	 * ***********************************************************************************************************************/	
	// shiftOffset variables, are changed depending on which direction player is moving in
	int tilesNumberWidth = 19;
	int tilesNumberHeight = 11;
	
	int shiftOffsetX = 0; 
	int shiftOffsetY = 0;
	int tileSize = 64; // The size of each tile (16x16, but the scale of the game is 4, so it's 64).
	Tile tile;
	Tile overlayTile;
	Rectangle bounds;
	public void render(JFrame frame, Graphics2D g2d, int mapX, int mapY, int currentTileX, int currentTileY,
			int previousMapX, int previousMapY, int previousTileX, int previousTileY, int mapOffsetX, int mapOffsetY, int xa, int ya) 
	{
		layeredTiles.clear();
		
		// mapOffsets are multiplied by -1 because the camera position and screen position are inverses...
		// don't ask me why just trust me, it makes sense when you think about it but it sucks to think about
		mapOffsetX *= -1;
		mapOffsetY *= -1;
		
		// checks if you moved to a new tile or not
		// if you did, a shift is needed in order to maintain the correct placement of tiles when drawn
		// the shift is always given/taken a tileSize (which in this case is 64)
		if (previousTileX != currentTileX) // Left/Right shifts
		{
			if (previousMapX < mapX && xa > 0) // Left
			{
				shiftOffsetX -= tileSize;
			}
			if (previousMapX > mapX && xa < 0) // Right
			{
				shiftOffsetX += tileSize;
			}
		}
		if (previousTileY != currentTileY) // Down/Up shifts
		{
			if (previousMapY > mapY && ya < 0) // Down
			{
				shiftOffsetY += tileSize;
			}
			if (previousMapY < mapY && ya > 0) // Up
			{
				shiftOffsetY -= tileSize;
			}
		}
		
		// mapX and mapY are the top left camera location
		// mapOffsetX and Y are how much the map is moved at the start of the game
		// shiftOffsetX and Y are described above
		// the subtraction of tileSize is necessary to start drawing the map one tile above where the player can see, which is necessary in cases where you can see a part of the next tile before moving tile locations
		int drawLocX = mapX + mapOffsetX + shiftOffsetX - tileSize;
		int drawLocY = mapY + mapOffsetY + shiftOffsetY - tileSize;
	//	int locX = mapX + mapOffsetX + shiftOffsetX - tileSize;
	//	int locY = mapY + mapOffsetY + shiftOffsetY - tileSize;
		int locX = mapX;
		int locY = mapY;
		
		first = true;
		
		// loops through each map tile that can possibly be visible on screen (plus the one layer not visible) and draws them
		// it looks at your currentTile and then draws all of the stuff above, below, and to the sides of it that the camera can see
		//for(int y = currentTileY - 6; y < currentTileY + 7; y++) {
		//	for(int x = currentTileX - 10; x < currentTileX + 11; x++) {
		for(int y = tilesNumberHeight * -1; y < mapHeight; y++)
		{
			for(int x = tilesNumberWidth * -1; x < mapWidth; x++)
			{
				//if (x >= 0 && y >= 0 && x < mapWidth && y < mapHeight) // if tile exists, draw it (if not leave it black)
				if (y >= currentTileY - 6 && y < currentTileY + 7 && x >= currentTileX - 10 && x < currentTileX + 11 &&
						x >= 0 && y >= 0 && x < mapWidth && y < mapHeight) 
						//&&
						//currentTileY - 6 >= 0 && currentTileY + 7 < mapHeight && currentTileX - 10 >= 0 && currentTileX + 11 < mapWidth)
				{
					tile = mapTiles[x + y * mapWidth];
					// because map is a one dimensional array, x + y * mapWidth will give you the tile you want
					tile.renderTile(drawLocX, drawLocY, g2d, frame);

					tile.setLocation(new Rectangle(drawLocX, drawLocY, tileSize, tileSize));

					bounds = tile.getSolidBounds();					
					tile.setSolidBoundsLocation(new Rectangle(drawLocX + bounds.x, drawLocY + bounds.y, bounds.width, bounds.height));
					
					if(first)
					{
						first = false;
						camera = new Rectangle(drawLocX, drawLocY, 1200 + 128, 700 + 64);

					}
					

					
					
//					float thickness = 1;
//					Stroke oldStroke = g2d.getStroke();
//					g2d.setStroke(new BasicStroke(thickness));
//					g2d.drawRect(camera.x, camera.y, camera.width, camera.height);
//					g2d.setStroke(oldStroke);
					
					overlayTile = overlayTiles[x + y * mapWidth];
					if (!overlayTile._name.equals("empty"))
					{					
						if (overlayTile.getLayer() == 0 || y < currentTileY - 1 || y > currentTileY + 1 || x < currentTileX - 1 || x > currentTileX + 1)
							//if((overlayTile.getLayer() == 0 || y< currentTileY - 1)	
							//&& (x == currentTileX || x == currentTileX - 1 ||
							//x == currentTileX + 1 || y == currentTileY || y == currentTileY - 1 || y == currentTileY + 1))
						{
							overlayTile.renderTile(drawLocX,  drawLocY, g2d, frame);
							overlayTile.setLocation(new Rectangle(drawLocX, drawLocY, tileSize, tileSize));					
							bounds = overlayTile.getSolidBounds();					
							overlayTile.setSolidBoundsLocation(new Rectangle(drawLocX + bounds.x, drawLocY + bounds.y, bounds.width, bounds.height));
						}
						else
						{
							layeredTiles.add(overlayTile);
							overlayTile.setLocation(new Rectangle(drawLocX, drawLocY, tileSize, tileSize));					
							bounds = tile.getSolidBounds();					
							overlayTile.setSolidBoundsLocation(new Rectangle(drawLocX + bounds.x, drawLocY + bounds.y, bounds.width, bounds.height));
						}
					}

				}
				if (y >= currentTileY - 6 && y < currentTileY + 7 && x >= currentTileX - 10 && x < currentTileX + 11)
						//&&
						//currentTileY - 6 >= 0 && currentTileY + 7 < mapHeight && currentTileX - 10 >= 0 && currentTileX + 11 < mapWidth)
				{
					drawLocX += tileSize; // increase X by a tileSize to draw the next tile so they won't overlap
					locX += tileSize;
				}
				else if (x >= 0 && y >= 0 && x < mapWidth && y < mapHeight)
				{				
					tile = mapTiles[x + y * mapWidth];
					tile.setLocation(new Rectangle(locX, locY, tileSize, tileSize));
					locX += tileSize;
				}
				
				
			}
			if (y >= currentTileY - 6 && y < currentTileY + 7)
					//&&
					//currentTileY - 6 >= 0 && currentTileY + 7 < mapHeight)  //&& y - 6 >= 0 && y + 7 < mapHeight
			{
				drawLocX = mapX + mapOffsetX + shiftOffsetX - tileSize; // reset startX for the next layer
				drawLocY += tileSize; // increase Y by a tilesize to draw the next layer of tiles below the old layer so they won't overlap
				locX = mapX;
				locY += tileSize;
			}
			else if (y >= 0 && y < mapHeight)
			{
				locX = mapX;
				locY += tileSize;
			}
			
		}
	}
	
	boolean isTrue = false;
	public void renderMobs(JFrame frame, Graphics2D g2d, int mapX, int mapY, int currentTileX, int currentTileY,
			int previousMapX, int previousMapY, int previousTileX, int previousTileY, int mapOffsetX, int mapOffsetY,
			int startPosX, int startPosY, int xa, int ya, int currentPosX, int currentPosY)
	{

		npcMobsOnScreen.clear();
		
		Mob mob;
		for (int i = 0; i < mobs.size(); i++)
		{			
			mob = mobs.get(i);
			
			if (mob.getType() != TYPE.PLAYER)
			{
				checkRI(mob, currentPosX, currentPosY);
				
				Rectangle bounds = mob.getBounds_();
				Rectangle boundsFull = mob.getFullBounds();
				
				tile = mapTiles[mob.getCurrentTile().x + mob.getCurrentTile().y * mapWidth];
				Rectangle tileBounds = new Rectangle(tile.getLocation().x, tile.getLocation().y, 64, 64);  
				
				mob.setBoundsLocation_(new Rectangle(bounds.x + tileBounds.x, bounds.y + tileBounds.y, bounds.width, bounds.height));
				mob.setFullBoundsLocation_(new Rectangle(boundsFull.x + tileBounds.x, boundsFull.y + tileBounds.y, boundsFull.width, boundsFull.height));
				mob.setHurtBoxLocation(new Rectangle(mob.getHurtBox().x + tileBounds.x, mob.getHurtBox().y + tileBounds.y, mob.getHurtBox().width, mob.getHurtBox().height));
				
				mob.setHealthBarLocation(new Rectangle(boundsFull.x + tileBounds.x, boundsFull.y + tileBounds.y, boundsFull.width, boundsFull.height));
				//mob.setCurrentLocation(new Point(mob.getCurrentLocation().x + 5, mob.getCurrentLocation().y));
				//mob.setAnimTo(48);
//				if (!isTrue)
//				{
//					mob.setAnimTo(48);
//					mob.startAnim();
//					isTrue = true;
//				}
//				Rectangle test = mob.getHurtBoxLocation();
//				float thickness = 1;
//				Stroke oldStroke = g2d.getStroke();
//				g2d.setStroke(new BasicStroke(thickness));
//				g2d.drawRect(test.x, test.y, test.width, test.height);
//				g2d.setStroke(oldStroke);
				
				if (mob.getFullBoundsLocation().intersects(camera))
				{
					mob.renderMob(tile.getLocation().x + mob.getCurrentLocation().x, tile.getLocation().y + mob.getCurrentLocation().y, g2d, frame);
					
					g2d.setColor(Color.red);
					g2d.fillRect(mob.getHealthBarLocation().x - 10, mob.getHealthBarLocation().y - 25, 80, 12);
					g2d.setColor(Color.green);
					double ratio = ((double)mob.getCurrHealth()/(double)mob.getMaxHealth() * 80);
					g2d.fillRect(mob.getHealthBarLocation().x + - 10, mob.getHealthBarLocation().y - 25, (int)(ratio), 12);
					g2d.setColor(Color.black);
					g2d.setFont(new Font("Times New Roman", Font.BOLD, 14));
					g2d.drawString("" + mob.getCurrHealth() + " / " + mob.getMaxHealth(), mob.getHealthBarLocation().x + 12, mob.getHealthBarLocation().y - 14);
					
//					String text;
//			        Font f;
//			        TextLayout t1;
//			        Shape shape;
//			        
//			        text = "Health: " + mob.health();	
//					f = new Font("Times New Roman", Font.PLAIN, 24);
//					t1 = new TextLayout(text, f, g2d.getFontRenderContext());
//					shape = t1.getOutline(null);
//			      
//					g2d.setColor(Color.black); 
//					g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
//					g2d.translate(mob.getFullBoundsLocation().x + mob.getFullBoundsLocation().width / 2, 
//							mob.getFullBoundsLocation().y + mob.getFullBoundsLocation().height + 10);
//					g2d.draw(shape);
//					g2d.translate(-(mob.getFullBoundsLocation().x + mob.getFullBoundsLocation().width / 2), 
//							-(mob.getFullBoundsLocation().y + mob.getFullBoundsLocation().height + 10));
//
//			       	g2d.setColor(Color.white);
//					g2d.translate(mob.getFullBoundsLocation().x + mob.getFullBoundsLocation().width / 2, 
//							mob.getFullBoundsLocation().y + mob.getFullBoundsLocation().height + 10);
//					g2d.fill(shape);
//					g2d.translate(-(mob.getFullBoundsLocation().x + mob.getFullBoundsLocation().width / 2), 
//							-(mob.getFullBoundsLocation().y + mob.getFullBoundsLocation().height + 10));
					
					
					
					npcMobsOnScreen.add(mob);
					
//					Rectangle test = mob.getAttack("sword").getAttackBounds();
//					float thickness = 1;
//					Stroke oldStroke = g2d.getStroke();
//					g2d.setStroke(new BasicStroke(thickness));
//					g2d.drawRect(test.x, test.y, test.width, test.height);
//					g2d.setStroke(oldStroke);
				}
			}
			else
			{
//				mob.getAttack("sword").setAttackBoundsLocation(new Rectangle(startPosX + mob.getAttack("sword").getAttackBounds().x, startPosY + mob.getAttack("sword").getAttackBounds().y, mob.getAttack("sword").getAttackBounds().width, mob.getAttack("sword").getAttackBounds().height));
				//mob.setFullBoundsLocation_(new Rectangle(startPosX + mob.getFullBounds().x, startPosY + mob.getFullBounds().y, mob.getFullBounds().width, mob.getFullBounds().height));
				
//				g2d.setColor(Color.red);
//				g2d.fillRect((int)entity.getX() + 18, (int)entity.getY() - 25, 80, 12);
//				g2d.setColor(Color.green);
//				double ratio = ((double)currHealth/(double)maxHealth * 80;
//				g2d.fillRect((int)entity.getX() + 18, (int)entity.getY() - 25, (int)(ratio), 12);
//				g2d.setColor(Color.black)
//				g2d.setFont(new Font("Times New Roman", Font.BOLD, 14));
//				g2d.drawString("" + currHealth + " / " + maxHealth, (int)this.getXLoc() + 40, (int)this.getYLoc() - 14);
				g2d.setColor(Color.red);
				g2d.fillRect(mob.getHealthBarLocation().x - 14, mob.getHealthBarLocation().y - 85, 80, 12);
				g2d.setColor(Color.green);
				double ratio = ((double)mob.getCurrHealth()/(double)mob.getMaxHealth() * 80);
				g2d.fillRect(mob.getHealthBarLocation().x + -14, mob.getHealthBarLocation().y - 85, (int)(ratio), 12);
				g2d.setColor(Color.black);
				g2d.setFont(new Font("Times New Roman", Font.BOLD, 14));
				g2d.drawString("" + mob.getCurrHealth() + " / " + mob.getMaxHealth(), mob.getHealthBarLocation().x + 8, mob.getHealthBarLocation().y - 74);
				
				mob.renderMob(startPosX, startPosY, g2d, frame);			
				
//				Rectangle test = mob.getHurtBoxLocation();
//				float thickness = 1;
//				Stroke oldStroke = g2d.getStroke();
//				g2d.setStroke(new BasicStroke(thickness));
//				g2d.drawRect(test.x, test.y, test.width, test.height);
//				g2d.setStroke(oldStroke);
			}
			
		}
		
		mobs = orderMobs(mobs);
	}
	
	public void checkRI(Mob mob, int currentLocX, int currentLocY)
	{
		
		if (mob.getRI() == RI.Attack)
		{
			if (mob.getIsAttacked())
			{
				Random rn = new Random();
				int answer = rn.nextInt(24 + 1) + 1;
				System.out.println(answer);
				if (!mob.animating() && answer == 1)
				{
					mob.attack();
				}
				
				
				

//			//	mob.setAnimTo(48);
//			//	mob.startAnim();
//				tile = mapTiles[mob.getCurrentTile().x + mob.getCurrentTile().y * mapWidth];
//			//	Rectangle tileBounds = new Rectangle(tile.getLocation().x, tile.getLocation().y, 64, 64);  
//				if (tile.getLocation().x + mob.getCurrentLocation().x > currentLocX)
//				{
//					mob.setCurrentLocation(new Point(mob.getCurrentLocation().x - 5, mob.getCurrentLocation().y));
//					System.out.println(currentLocX + " THISIS PLAYER");
//					int fuck = tile.getBounds().x + mob.getCurrentLocation().x;
//					System.out.println(fuck);
//				}
//			    if (tile.getLocation().x + mob.getCurrentLocation().x < currentLocX)
//				{
//					mob.setCurrentLocation(new Point(mob.getCurrentLocation().x + 5, mob.getCurrentLocation().y));
//					System.out.println(currentLocX + " THISIS PLAYER");
//					int fuck = tile.getBounds().x + mob.getCurrentLocation().x;
//					System.out.println(fuck);
//				}

		
	
		
			}
		}
	}
	
	public void renderLayeredTiles(JFrame frame, Graphics2D g2d, int mapX, int mapY, int currentTileX, int currentTileY,
			int previousMapX, int previousMapY, int previousTileX, int previousTileY, int mapOffsetX, int mapOffsetY)
	{
		Tile tile;
		for (int i = 0; i < layeredTiles.size(); i++)
		{
			tile = layeredTiles.get(i);
			tile.renderTile(tile.getLocation().x, tile.getLocation().y, g2d, frame);
		}
	}
	
	public ArrayList<Mob> orderMobs(ArrayList<Mob> mobs)
	{
		Mob mob;
		for(int i = 0; i < mobs.size(); i++)
		{			
			//System.out.println("mobs.size = " + mobs.size());//Get the count of the array
			if(i + 1 > mobs.size() - 1)
			{
				break;
			}

			if (mobs.get(i).getFullBoundsLocation().y > mobs.get(i).getFullBoundsLocation().y) //Changed mobs.get(i+1) to just i to fix the crashing issue along with unlocking special character.
			{
				mob = mobs.get(i);
				mobs.set(i, mobs.get(i + 1));
				mobs.set(i + 1, mob);
			}
		}
		return mobs;
	}
	
/* *** OLD WAY of rendering the map, saved just in case I need to see how something is done
*** NEW WAY is above ***/	
//	public void render(JFrame frame, Graphics2D g2d, int xx, int yy) {
//		int xt = xx;
//		for(int y = 0; y < mapHeight; y++) {
//			for(int x = 0; x < mapWidth; x++) {
//				mapTiles[x + y * mapWidth].renderTile(xx, yy, g2d, frame);
//				if(mapTiles[x + y * mapWidth].hasMob()) mapTiles[x + y * mapWidth].mob().renderMob(xx, yy);
//				xx = xx + spriteSize;
//			}
//			xx = xt;
//			yy = yy + spriteSize;
//		}
//	}
	
	/*****************************************************************************
	 * As the method name implies, access the tile at a specific index in the map
	 * The map is in a 1D array, so think x + y * width of the map when accessing the tile.
	 * 
	 * @param index - An int of total x + y * width of the map to access a tile
	 * @return - A Tile which is accessed and can be changed
	 *****************************************************************************/
	public Tile accessTile(int index) 
	{
		return mapTiles[index];
	}
	public void setTile(int x, int y, Tile tile)
	{
		mapTiles[x + y * mapWidth] = tile;
	}
	
	public ArrayList<Mob> getMobs() { return mobs; }
	public void setMobs(ArrayList<Mob> mobs) { this.mobs = mobs; }
	
	public LinkedList<Mob> getNpcMobsOnScreen() { return npcMobsOnScreen; }
	public void setNpcMobsOnScreen(LinkedList<Mob> npcMobsOnScreen) { this.npcMobsOnScreen = npcMobsOnScreen; }
	
	public Tile accessOverlayTile(int index)
	{
		return overlayTiles[index];
	}
	public void setOverlayTile(int x, int y, Tile tile)
	{
		overlayTiles[x + y * mapWidth] = tile;
	}
	
	/*****************************************************************
	 * Get the maximum x value the player could move to in the map.
	 * maxX is the right border, unlike y, when X goes up, x goes right.
	 * Always gives back the opposite number found
	 * 
	 * @param screenWidth - The width of the window screen given at the start of the Game
	 * @return - The maximum or minimum int x value 
	 ******************************************************************/
	public int getMaxX(int screenWidth) { return -1 * (mapWidth * spriteSize - screenWidth); }
	public int getMinX() { return 0; }
	
	/*****************************************************************
	 * Get the maximum y value the player could move to in the map.
	 * maxY is the bottom edge, as Y goes down, the y value goes up.
	 * Always gives back the opposite number found
	 * 
	 * @param screenHeight - The height of the window screen given at the start of the Game
	 * @return - The maximum or minimum int y value 
	 ******************************************************************/
	public int getMaxY(int screenHeight) { return -1 * (mapHeight * spriteSize - screenHeight); }
	public int getMinY() { return 0; }
	
	/************************************************************
	 * Currently an unused section of code which obtains the tile in the map based on an X and Y parameter
	 * 
	 * Does not work yet but should!
	 ************************************************************/
	//Get a tile based on a location and direction of a mob
	//playerX and playerY only matter if the mob in the first parameter is a player, otherwise they dont matter
	//TODO: This method doesn't actually work, don't use it
	/*public Tile getFrontTile(Mob mob, int playerX, int playerY, int centerX, int centerY){
		int xx = (int) Math.floor(Math.abs(mob.getXLoc())/spriteSize);
		int yy = (int) Math.floor(Math.abs(mob.getYLoc())/spriteSize);
		if(mob.getType() == TYPE.PLAYER){
			if(playerX < 0) xx = (int) Math.floor(Math.abs(playerX - centerX)/spriteSize); //width what about the black spaces
			if(playerX > 0) xx = (int) Math.floor((playerX + centerX)/spriteSize);
			if(playerY < 0) yy = (int) Math.floor(Math.abs(playerY - centerY)/spriteSize); //height
			if(playerY > 0) yy = (int) Math.floor((playerY + centerY)/spriteSize); //height
		}
		System.out.println((xx + " xx " + yy + " yy ") + " ufiusfhsidu " + spriteSize);
		if(mob.facingLeft) return mapTiles[(xx - 1) + yy * mapWidth]; //left tile
		if(mob.facingRight) return mapTiles[(xx + 1) + yy * mapWidth]; //right
		if(mob.facingUp) return mapTiles[xx + (yy - 1) * mapWidth]; //up
		if(mob.facingDown) return mapTiles[xx + (yy + 1) * mapWidth]; //down
		return mapTiles[xx + yy * mapWidth]; //This line should never run, it's here for formality
	}*/
	
	/**********************************************************
	 * Getters which return the width of the map, the height, or the name of the map
	 * 
	 * @return - An int of height or width or a String name
	 **********************************************************/
	public int getWidth() { return mapWidth; }
	public int getHeight() { return mapHeight; }
	public String mapName() { return _name; }
}