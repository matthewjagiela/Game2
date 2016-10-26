/****************************************************************************************************
 * @author Travis R. Dewitt
 * @version 0.53
 * Date: June 14, 2015
 * 
 * 
 * Title: Judgement(The Game)
 * Description: This class extends 'Game.java' in order to run a 2D game with specificly defined
 *  sprites, animatons, and actions.
 *  
 * 
 * This work is licensed under a Attribution-NonCommercial 4.0 International
 * CC BY-NC-ND license. http://creativecommons.org/licenses/by-nc/4.0/
 ****************************************************************************************************/
//Package name
package axohEngine2;

//Imports
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
//import sun.org.mozilla.javascript.internal.ast.Loop;
import axohEngine2.entities.AnimatedSprite;
import axohEngine2.entities.DIRECTION;
import axohEngine2.entities.ImageEntity;
import axohEngine2.entities.Mob;
import axohEngine2.entities.SpriteSheet;
import axohEngine2.map.Map;
import axohEngine2.map.Tile;
import axohEngine2.map.TileEvent;
import axohEngine2.map.TileEventType;
import axohEngine2.player.Player;
import axohEngine2.project.InGameMenu;
import axohEngine2.project.Inventory;
import axohEngine2.project.MapDatabase;
import axohEngine2.project.OPTION;
import axohEngine2.project.Quests;
import axohEngine2.project.STATE;
import axohEngine2.project.TYPE;
import axohEngine2.project.Textbox;
import axohEngine2.project.TileCreator;
import axohEngine2.project.TitleMenu;
import axohEngine2.project.Status;

//Start class by also extending the 'Game.java' engine interface
public class Judgement extends Game {
	//For serializing (The saving system)
	private static final long serialVersionUID = 1L;
	//Hi
	/****************** Variables **********************/
	//--------- Screen ---------
	
	//SCREENWIDTH - Game window width
	//SCREENHEIGHT - Game window height
	//CENTERX/CENTERY - Center of the game window's x/y
	static int SCREENWIDTH = 1200; //1226 //382
	static int SCREENHEIGHT = 700;
	
	// basically keeps player in center of screen
	static int CENTERX = 530;
	static int CENTERY = 270;
	//--------- Miscelaneous ---------
	//booleans - A way of detecting a pushed key in game
	//random - Use this to generate a random number
	//state - Game states used to show specific info ie. pause/running
	//option - In game common choices at given times
	//Fonts - Variouse font sizes in the Arial style for different in game text
	boolean keyLeft, keyRight, keyUp, keyDown, keyInventory, keyAction, keyBack, keyEnter, keySpace, keyChange, keyShift, keyCancel,
	keyChat;
	Random random = new Random();
	STATE state; 
	OPTION option;
	private Font simple = new Font("Arial", Font.PLAIN, 72);
	private Font bold = new Font("Arial", Font.BOLD, 72);
	private Font bigBold = new Font("Arial", Font.BOLD, 96);
	
	//--------- Player and scale ---------
	//scale - All in game art is 16 x 16 pixels, the scale is the multiplier to enlarge the art and give it the pixelated look
	//mapX/mapY - Location of the camera on the map
	//playerX/playerY - Location of the player on the map
	//startPosX/startPosY - Starting position of the player in the map
	//playerSpeed - How many pixels the player moves in a direction each update when told to
	public static int scale;
	private int mapX;
	private int mapY;
	private int playerX;
	private int playerY;
	private int startPosX;
	private int startPosY;
	private int currentPosX;
	private int currentPosY;
	private int playerSpeed;
	private int files;
	private int snack;
	private int keyboard;
	private int gfrisbee;
	private int frisbee;
	private int health;
	
	//----------- Map and input --------
	//currentMap - The currently displayed map the player can explore
	//currentOverlay - The current overlay which usually contains houses, trees, pots, etc.
	//mapBase - The database which contains all variables which pertain to specific maps(NPCs, monsters, chests...)
	//inputWait - How long the system waits for after an action is done on the keyboard
	//confirmUse - After some decisions are made, a second question pops up, true equals continue action from before.
	private Map currentMap;
	private Map currentOverlay;
	private MapDatabase mapBase;
	private Rectangle[] tileTracker;
	private int inputWait = 5;
	private boolean confirmUse = false;
	
	//----------- Menus ----------------
	//inX/inY - In Game Menu starting location for default choice highlight
	//inLocation - Current choice in the in game menu represented by a number, 0 is the top
	//sectionLoc - Current position the player could choose after the first choice has been made in the in game menu(Items -> potion), 0 is the top.
	//titleX, titleY, titleX2, titleY2 - Positions for specific moveable sprites at the title screen (arrow/highlight).
	//titleLocation - Current position the player is choosing in the title screen(File 1, 2, 3) 0 is top
	//currentFile - Name of the currently loaded file
	//wasSaving/wait/waitOn - Various waiting variables to give the player time to react to whats happening on screen
	private int inX = 90, inY = 90;
	private int inLocation;
	private int sectionLoc;
	private int titleX = 520, titleY = 416;
	private int titleX2 = 520, titleY2 = 510;
	private int titleLocation;
	private String currentFile;
	private boolean wasSaving = false;
	private int wait;
	private boolean waitOn = false;
	
	//----------- Game  -----------------
	//SpriteSheets (To be split in to multiple smaller sprites)
	SpriteSheet extras1;
	SpriteSheet mainCharacter;
	
	//ImageEntitys (Basic pictures)
	ImageEntity inGameMenu;
	ImageEntity titleMenu;
	ImageEntity titleMenu2;
	
	//Menu classes
	TitleMenu title;
	InGameMenu inMenu;
	
	//Animated sprites
	AnimatedSprite titleArrow;
	AnimatedSprite titleEraseBomb;
	
	//Player and NPCs
	Mob playerMob;
	Mob randomNPC;
	
	int playerNumber;
	public static boolean playerUpdate;
	int cameraWidth;
	int cameraHeight;
	
	int currentTileX; 
	int currentTileY; 
	
	int mapOffsetX;
	int mapOffsetY;
	
	int previousMapX;
	int previousMapY;
	
	int previousTileX;
	int previousTileY;
	
	//***Initialize textbox***
	Textbox textbox = new Textbox();
	boolean renderTextbox;
	
	boolean renderInGameMenu;
	boolean renderInventory;
	boolean renderStatus;
	boolean renderTutorial;
	
	
	
	
	// get tile you are currently intersecting (if any)
	boolean intersectTile; // checks if you are currently intersecting with a tile or not
	boolean intersectSprite;
	int currentTileIntersectX;
	int currentTileIntersectY;
	
	TileCreator tileCreator = new TileCreator();
	
	Tile intersectedTile;
	String intersectedTileEvent;
	
	int selection = 0;
	
	// Create instances
	Status status = new Status();
	Inventory inventory = new Inventory(0, 2);
	Quests quest = new Quests();
	Tutorial tutorial = new Tutorial(); //Control Screen SCRUM 2
	String[] dialogue = quest.getDialogue();
	private int dialogueTracker = -1;
	private int dialogueWait = 0;
	
	/*********************************************************************** 
	 * Constructor
	 * 
	 * Set up the super class Game and set the window to appear
	 **********************************************************************/
	/**
	 * isWindows determines what operating system is running and will return an FPS suitable for it. 
	 * @return prefered FPS based on Operating System
	 */
	public static int isWindows(){ //NEW METHOD SCRUM CYCLE 1
		if(System.getProperty("os.name").startsWith("Windows")){ //The operating system is windows and can hold a higher FPS
			System.out.println("Windows");
			return 60; //60FPS to make the game look good but not to cause problems on those with slower hardware 
		}
		else{
			System.out.println("Mac");
			return 30; //The Operating system is macOS and needs a lower FPS
		}
	}
	public Judgement() {
		super(isWindows(),SCREENWIDTH,SCREENHEIGHT); //isWindows will determine the correct frame rate for the game to run smoothly based on the OS
		
	//	super(130, 1226, 382); // the 130 is frame rate!!, 1200 is screen width, 700 is screen height
		//super(110, SCREENWIDTH, SCREENHEIGHT);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/****************************************************************************
	 * Inherited Method
	 * This method is called only once by the 'Game.java' class, for startup
	 * Initialize all non-int variables here
	 *****************************************************************************/
	void gameStartUp() {
		/****************************************************************
		 * The "camera" is the mapX and mapY variables. These variables 
		 * can be changed in order to move the map around, simulating the
		 * camera. The player is moved around ONLY when at an edge of a map,
		 * otherwise it simply stays at the center of the screen as the "camera"
		 * is moved around.
		 ****************************************************************/
		//****Initialize Misc Variables
		state = STATE.TITLE;
		option = OPTION.NONE;
		
		// start position of player 
		// this is different than the camera position (mapX and mapY)
		// these will be used to get which tile the player is on
		// Basically CENTERX and CENTERY should have done the trick for putting the player in the center of the map.
		// but it doesn't account for the player's actual size and I'm just not sure how to do that math correctly.
		// I'll figure it out later in the case that we add more than one playable character, for now here's some addition and subtraction.
		
		startPosX = 530 + 16; //CENTERX + 16    546
		startPosY = 270 - 42; //CENTERY - 42    228
	
		mapOffsetX = -128;
		mapOffsetY = -128;
		
		// (is needed to keep track of so we know where the player is at all times)
		// where the player is currently positioned on map (default equal to start position)
		currentPosX = startPosX;
		currentPosY = startPosY;
		
		// gets the tile the player is currently standing on
		// figures it out by using your currentPosition, dividing it by 64 (which is the tile size after they are scaled), and it auto rounds down
		currentTileX = (currentPosX + 64 + (mapOffsetX * -1)) / 64;
		currentTileY = (currentPosY + 128 + (mapOffsetY * -1)) / 64;
		System.out.println(currentTileX + " " + currentTileY);
		
		// start camera position
		mapX = 0 + mapOffsetX;
		mapY = 0 + mapOffsetY;
		
		previousMapX = mapX;
		previousMapY = mapY;
		previousTileX = currentTileX;
		previousTileY = currentTileY;
		
		scale = 4;
		playerSpeed = 4;
		
		cameraWidth = 1200;
		cameraHeight = 700;
			
		//****Initialize spriteSheets*********************************************************************
		extras1 = new SpriteSheet("/textures/extras/extras1.png", 8, 8, 32, scale);
		//mainCharacter = new SpriteSheet("/textures/characters/mainCharacter.png", 8, 8, 32, scale);
		
		//****Initialize and setup AnimatedSprites*********************************************************
		titleArrow = new AnimatedSprite(this, graphics(), extras1, 0, "arrow");
		titleArrow.loadAnim(4, 10);
		sprites().add(titleArrow);
		
		titleEraseBomb = new AnimatedSprite(this, graphics(), extras1, 4, "bomb");
		titleEraseBomb.loadAnim(4, 10);
		sprites().add(titleEraseBomb);
			
		//****Initialize and setup image entities**********************************************************
		inGameMenu = new ImageEntity(this);
		titleMenu = new ImageEntity(this);
		titleMenu2 = new ImageEntity(this);
		inGameMenu.load("/menus/ingamemenu.png");
	//	titleMenu.load("/menus/titlemenu1.png");
	
		
		//titleMenu.load("/menus/PurpleBackground.png");
		titleMenu.load("/menus/Title Screen 2.png");
		
		
	//	titleMenu2.load("/menus/titlemenu2.png");
	//	titleMenu2.load("/menus/titlemenu.png");

	
		//*****Initialize Menus***************************************************************************
		title = new TitleMenu(titleMenu, titleMenu2, titleArrow, SCREENWIDTH, SCREENHEIGHT, simple, bold, bigBold, titleEraseBomb);
		inMenu = new InGameMenu(inGameMenu, SCREENWIDTH, SCREENHEIGHT);

		//****Initialize and setup Mobs*********************************************************************
		
		// Class for initializing player + checking which player you will be
		Player player = new Player();
		playerMob = player.getPlayerMobStart(mainCharacter, playerMob, graphics(), this, sprites(), playerNumber);
		playerNumber++;
		
	//	playerUpdate = true;
		//*****Initialize and setup first Map******************************************************************
		mapBase = new MapDatabase(this, graphics(), scale);
		//Get Map from the database
		for(int i = 0; i < mapBase.maps.length; i++)
		{
			if(mapBase.getMap(i) == null) 
			{
				continue;
			}
			
			if(mapBase.getMap(i).mapName() == "city") 
			{
				currentMap = mapBase.getMap(i);
			}
			
//			if(mapBase.getMap(i).mapName() == "cityO") 
//			{
//				currentOverlay = mapBase.getMap(i);
//			}
		}

//		//Add the tiles from the map to be updated each system cycle
//		for(int i = 0; i < currentMap.getWidth() * currentMap.getHeight(); i++)
//		{
//			addTile(currentMap.accessTile(i));
//			currentMap.accessTile(i).getEntity().setX(-300);
//		//	currentOverlay.accessTile(i).getEntity().setX(-300);
//						
//		}
			
		currentMap.getMobs().add(playerMob);
		
		try{
		Song("/sounds/game_loop.wav");
		}
		catch (Exception ex) {}
		
		requestFocus(); //Make sure the game is focused on
		start(); //Start the game loop
	}
	
	/**************************************************************************** 
	 * Inherited Method
	 * Method that updates with the default 'Game.java' loop method
	 * Add game specific elements that need updating here
	 *****************************************************************************/
	void gameTimedUpdate() 
	{
		updateBounds();	
		combat();
		checkInput(); //Check for user input
		//Update certain specifics based on certain game states
		if(state == STATE.TITLE) title.update(option, titleLocation); //Title Menu update
		if(state == STATE.INGAMEMENU) inMenu.update(option, sectionLoc, playerMob.health()); //In Game Menu update
	//	updateData(currentMap, currentOverlay, playerX, playerY); //Update the current file data for saving later
		//System.out.println(frameRate()); //Print the current framerate to the console
		if(waitOn) wait--;
	}
	Mob npcMob;
	/**
	 * Inherited Method
	 * Obtain the 'graphics' passed down by the super class 'Game.java' and render objects on the screen
	 */
	int is = 0;
	void gameRefreshScreen() {		
		/*********************************************************************
		* Rendering images uses the java class Graphics2D
		* Each frame the screen needs to be cleared and an image is setup as a back buffer which is brought 
		* to the front as a full image at the time it is needed. This way the screen is NOT rendered pixel by 
		* pixel in front of the user, which would have made a strange lag effect.
		* 
		* 'graphics' objects have parameters that can be changed which effect what it renders, two are font and color
		**********************************************************************/
		
	
		Graphics2D g2d = graphics();
		g2d.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT); 
		g2d.setFont(simple);
		
		//GUI rendering for when a specific state is set, only specific groups of data is drawn at specific times
		if(state == STATE.GAME) { 

			//Render the map, the player, any NPCs or Monsters and the player health or status
			currentMap.render(this, g2d, mapX, mapY, currentTileX, currentTileY, previousMapX, previousMapY, previousTileX, previousTileY, mapOffsetX, mapOffsetY, xa, ya);
		//	currentMap.render2(this, g2d, mapX, mapY, currentTileX, currentTileY, previousMapX, previousMapY, previousTileX, previousTileY, mapOffsetX, mapOffsetY);
			

				
			// BECAUSE of a mistake somewhere either in the sprite class, Animated sprite, or Mob...or even sprite sheet...
			// the starting sprite when drawing the player will always mess up, and for some reason it will walk in place at first
			// I tried...idk how to fix it, so here's a work around yay
			// this if statement checks a variable I added into the Mob for which frame to start on
			// playerUpdate is only set to true when first drawing a player into the map, so this if statement happens once
//			if (playerUpdate)
//			{
//				playerMob.stopAnim(); // stops the moving in place right when player loads
//				playerMob.setAnimTo(playerMob.getStartFrame()); // stops the odd animation loading and sets player to start on its wanted start frame
//				playerUpdate = false; 
//			}
			
			

			// STARTING POSITION OF PLAYER 
		//	playerMob.renderMob(startPosX, startPosY);
			////currentMap.orderMobs(currentMap.getMobs());
			currentMap.renderMobs(this, g2d, mapX, mapY, currentTileX, currentTileY, previousMapX, previousMapY, previousTileX, previousTileY, mapOffsetX, mapOffsetY, startPosX, startPosY, xa, ya, currentPosX, currentPosY);
			currentMap.renderLayeredTiles(this, g2d, mapX, mapY, currentTileX, currentTileY, previousMapX, previousMapY, previousTileX, previousTileY, mapOffsetX, mapOffsetY);
			
			if (renderTextbox)
			{
				textbox.renderTextBox(this, g2d);
			//    textbox.renderText("Hey.", g2d, this);
				
					
			//	renderTextbox = false;
				
			}
			
			if (renderInGameMenu)
			{
				inMenu.render(this, g2d, selection);
			}
			if (renderInventory)
			{
				inventory.render(this, g2d);
			}
			
			// add status screen
			if (renderStatus)
			{
				status.render(this, g2d);
			}
			if (renderTutorial){
				tutorial.render(this, g2d);
			}
//			Player player = new Player();
//			npcMob = player.getPlayerMobStart(mainCharacter, npcMob, graphics(), this, sprites(), playerNumber);
//			npcMob.renderMob(100,100);
			
//			Rectangle bounds = playerMob.getLeftBounds();
//			float thickness = 1;
//			Stroke oldStroke = g2d.getStroke();
//			g2d.setStroke(new BasicStroke(thickness));
//			g2d.drawRect(bounds.x + startPosX, bounds.y + startPosY, bounds.width, bounds.height);
//			g2d.setStroke(oldStroke);

		}
		if(state == STATE.INGAMEMENU){
			//Render the in game menu and specific text
			inMenu.render(this, g2d, selection);
			g2d.setColor(Color.red);
			if(confirmUse) g2d.drawString("Use this?", CENTERX, CENTERY);
		}
		if(state == STATE.TITLE) {
			//Render the title screen
			title.render(this, g2d, titleX, titleY, titleX2, titleY2);
		}
		
		//Render save time specific writing
		if(option == OPTION.SAVE){
			drawString(g2d, "Are you sure you\n      would like to save?", 660, 400);
		}
		if(wasSaving && wait > 0) {
			g2d.drawString("Game Saved!", 700, 500);
		}
	}
	
	/*******************************************************************
	 * The next four methods are inherited
	 * Currently these methods are not being used, but they have
	 * been set up to go off at specific times in a game as events.
	 * Actions that need to be done during these times can be added here.
	 ******************************************************************/
	void gameShutDown() {		
	}

	void spriteUpdate(AnimatedSprite sprite) {		
	}

	void spriteDraw(AnimatedSprite sprite) {		
	}

	void spriteDying(AnimatedSprite sprite) {		
	}

	/*************************************************************************
	 * @param AnimatedSprite
	 * @param AnimatedSprite
	 * @param int
	 * @param int
	 * 
	 * Inherited Method
	 * Handling for when a SPRITE contacts a SPRITE
	 * 
	 * hitDir is the hit found when colliding on a specific bounding box on spr1 and hitDir2
	 * is the same thing applied to spr2
	 * hitDir is short for hit direction which can give the data needed to move the colliding sprites
	 * hitDir is a number between and including 0 and 3, these assignments are taken care of in 'Game.java'.
	 * What hitDir is actually referring to is the specific hit box that is on a multi-box sprite.
	 *****************************************************************************/
	void spriteCollision(AnimatedSprite spr1, AnimatedSprite spr2, int hitDir, int hitDir2) {
		//Get the smallest possible overlap between the two problem sprites
		double leftOverlap = (spr1.getBoundX(hitDir) + spr1.getBoundSize() - spr2.getBoundX(hitDir2));
		double rightOverlap = (spr2.getBoundX(hitDir2) + spr2.getBoundSize() - spr1.getBoundX(hitDir));
		double topOverlap = (spr1.getBoundY(hitDir) + spr1.getBoundSize() - spr2.getBoundY(hitDir2));
		double botOverlap = (spr2.getBoundY(hitDir2) + spr2.getBoundSize() - spr1.getBoundY(hitDir));
		double smallestOverlap = Double.MAX_VALUE; 
		double shiftX = 0;
		double shiftY = 0;

		if(leftOverlap < smallestOverlap) { //Left
			smallestOverlap = leftOverlap;
			shiftX -= leftOverlap; 
			shiftY = 0;
		}
		if(rightOverlap < smallestOverlap){ //right
			smallestOverlap = rightOverlap;
			shiftX = rightOverlap;
			shiftY = 0;
		}
		if(topOverlap < smallestOverlap){ //up
			smallestOverlap = topOverlap;
			shiftX = 0;
			shiftY -= topOverlap;
		}
		if(botOverlap < smallestOverlap){ //down
			smallestOverlap = botOverlap;
			shiftX = 0;
			shiftY = botOverlap;
		}

		//Handling very specific collisions
		if(spr1.spriteType() == TYPE.PLAYER && state == STATE.GAME){
			if(spr2 instanceof Mob) ((Mob) spr2).stop(); // stops an NPC from walking through you
			
			//This piece of code is commented out because I still need the capability of getting a tile from an xand y position
			/*if(((Mob) spr1).attacking() && currentOverlay.getFrontTile((Mob) spr1, playerX, playerY, CENTERX, CENTERY).getBounds().intersects(spr2.getBounds())){
				((Mob) spr2).takeDamage(25);
				//TODO: inside of take damage should be a number dependant on the current weapon equipped, change later
			}*/
			
			//WORMS
			//Handle simple push back collision
		//	if(playerX != 0) playerX -= shiftX; // Unnecessary now that the game camera has been changed to move at all times
		//	if(playerY != 0) playerY -= shiftY;
		
			// this checks to make sure the player has been loaded before checking for sprite collisions
			// otherwise, when the game first loads, it automatically counts a collision for some reason...idk why and then moves you as if you collided
			// once again idk why it does this but like everything in this game engine, nothing makes any sense, so here's a work around, which is basically what this entire engine has become
			if (playerUpdate == false) 
			{
				if(playerX == 0) mapX -= shiftX; // if you collide with a sprite, move back
				if(playerY == 0) mapY -= shiftY;
			}
		}
	}
	
	/***********************************************************************
	* @param AnimatedSprite
	* @param Tile
	* @param int
	* @param int
	* 
	* Inherited Method
	* Set handling for when a SPRITE contacts a TILE, this is handy for
	* dealing with Tiles which contain Events. When specifying a new
	* collision method, check for the type of sprite and whether a tile is
	* solid or breakable, both, or even if it contains an event. This is
	* mandatory because the AxohEngine finds details on collision and then 
	* returns it for specific handling by the user.
	* 
	* For more details on this method, refer to the spriteCollision method above
	*************************************************************************/
	void tileCollision(AnimatedSprite spr, Tile tile, int hitDir, int hitDir2) {
		double leftOverlap = (spr.getBoundX(hitDir) + spr.getBoundSize() - tile.getBoundX(hitDir2));
		double rightOverlap = (tile.getBoundX(hitDir2) + tile.getBoundSize() - spr.getBoundX(hitDir));
		double topOverlap = (spr.getBoundY(hitDir) + spr.getBoundSize() - tile.getBoundY(hitDir2));
		double botOverlap = (tile.getBoundY(hitDir2) + tile.getBoundSize() - spr.getBoundY(hitDir));
		double smallestOverlap = Double.MAX_VALUE; 
		double shiftX = 0;
		double shiftY = 0;

		if(leftOverlap < smallestOverlap) { //Left
			smallestOverlap = leftOverlap;
			shiftX -= leftOverlap; 
			shiftY = 0;
		}
		if(rightOverlap < smallestOverlap){ //right
			smallestOverlap = rightOverlap;
			shiftX = rightOverlap;
			shiftY = 0;
		}
		if(topOverlap < smallestOverlap){ //up
			smallestOverlap = topOverlap;
			shiftX = 0;
			shiftY -= topOverlap;
		}
		if(botOverlap < smallestOverlap){ //down
			smallestOverlap = botOverlap;
			shiftX = 0;
			shiftY = botOverlap;
		}
		
		//Deal with a tiles possible event property
		if(tile.hasEvent()){
			if(spr.spriteType() == TYPE.PLAYER) {
				//Warp Events(Doors)
				if(tile.event().getEventType() == TYPE.WARP) {
					tiles().clear();
					sprites().clear();
					sprites().add(playerMob);
					//Get the new map
					for(int i = 0; i < mapBase.maps.length; i++){
						 if(mapBase.getMap(i) == null) continue;
						 if(tile.event().getMapName() == mapBase.getMap(i).mapName()) currentMap = mapBase.getMap(i);
						 if(tile.event().getOverlayName() == mapBase.getMap(i).mapName()) currentOverlay = mapBase.getMap(i);
					}
					//Load in the new maps Tiles and Mobs
					for(int i = 0; i < currentMap.getWidth() * currentMap.getHeight(); i++){
						addTile(currentMap.accessTile(i));
						addTile(currentOverlay.accessTile(i));
						if(currentMap.accessTile(i).hasMob()) sprites().add(currentMap.accessTile(i).mob());
						if(currentOverlay.accessTile(i).hasMob()) sprites().add(currentOverlay.accessTile(i).mob());
					}
					//Move the player to the new position
					playerX = tile.event().getNewX();
					playerY = tile.event().getNewY();
				}	
			} //end warp
			//Item exchange event
			
			// add item to inventory?
			if(spr.spriteType() == TYPE.PLAYER && tile.event().getEventType() == TYPE.ITEM && keyAction){
				if((tile._name).equals("chest")) tile.setFrame(tile.getSpriteNumber() + 1); //Chests should have opened and closed version next to each other
				inMenu.addItem(tile.event().getItem()); //Add item to inventory
				tile.endEvent();
			}
		}//end check events
		
		//If the tile is solid, move the player off of it and exit method immediately
		if(spr.spriteType() == TYPE.PLAYER && tile.solid() && state == STATE.GAME) {
			//WORMS
			if(playerX != 0) playerX -= shiftX;
			if(playerY != 0) playerY -= shiftY;
			if(playerX == 0) mapX -= shiftX;
			if(playerY == 0) mapY -= shiftY;
			return;
		}
		//If an npc is intersecting a solid tile, move it off
		if(spr.spriteType() != TYPE.PLAYER && tile.solid() && state == STATE.GAME){
			if(spr instanceof Mob) {
				((Mob) spr).setLoc((int)shiftX, (int)shiftY);
				((Mob) spr).resetMovement();
			}
		}
	}//end tileCollision method
	
	
	
	// Reaction Tech Solutions tile collision method
	int checkTileCollisionsX(int xa)
	{
		// Tile bounds that are to be checked
		Tile tile;
		Tile tile2;
		Tile tile3;
		Tile tile4;
		
		Tile tileOverlay;
		Tile tileOverlay2;
		Tile tileOverlay3;
		Tile tileOverlay4;
		
		// bounds of player depending on direction
		Rectangle bounds;
		
		boolean collision = false;
	//	String intersectedTileEvent;
		
		if(xa > 0) // Left	
		{	
			// tiles are equal to the location of the tiles we are checking
			// for left, we are checking current tile, below current tile, one tile to the left of current tile, and one tile to the left of the tile below the current tile
			tile = currentMap.accessTile((currentTileX - 1) + currentTileY * currentMap.getWidth());
			tile2 = currentMap.accessTile((currentTileX - 1) + (currentTileY - 1) * currentMap.getWidth());
		    tile3 = currentMap.accessTile(currentTileX + currentTileY * currentMap.getWidth());
			tile4 = currentMap.accessTile(currentTileX + (currentTileY - 1) * currentMap.getWidth());
			
			tileOverlay = currentMap.accessOverlayTile((currentTileX - 1) + currentTileY * currentMap.getWidth());
			tileOverlay2 = currentMap.accessOverlayTile((currentTileX - 1) + (currentTileY - 1) * currentMap.getWidth());
		    tileOverlay3 = currentMap.accessOverlayTile(currentTileX + currentTileY * currentMap.getWidth());
			tileOverlay4 = currentMap.accessOverlayTile(currentTileX + (currentTileY - 1) * currentMap.getWidth());
			
			// set bounds equal to the player's left bounds, and then set the player's "main bounds" to the leftBounds
			bounds = playerMob.getLeftBounds();
			playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
			
			// loop for each pixel moved (based on playerSpeed)
			// the plus one after playerSpeed is because when the code was originally written, an extra one is always added to the playerSpeed when moving
			// I don't know why that design choice was made but oh well
			// Anyway, set the bounds of the player one pixel ahead for each value of i and then check if it intersects with a tile bounds
			// if it does intersect, set the amount the player is allowed to move (xa) to that amount (so it stops right at the intersection of the tile)
			// Also, if it does intersect, set collision to true
			// lastly, reset the player bounds back to normal to prepare it for the next iteration
			// if there was a collision however, just break the loop (the reset of player bounds is still needed however)
			for (int i = 0; i < playerSpeed + 1; i++)
			{
				playerMob.setBounds_(new Rectangle(startPosX + bounds.x - i, startPosY + bounds.y, bounds.width, bounds.height));
				
				if (playerMob.getBounds_().intersects(tile3.getSolidBoundsLocation()))
				{
					xa = i;
					collision = true;
					intersectedTile = tile3;
				}
				else if (playerMob.getBounds_().intersects(tile.getSolidBoundsLocation()))
				{
					xa = i;
					collision = true;
					intersectedTile = tile;
				}
				else if (playerMob.getBounds_().intersects(tileOverlay3.getSolidBoundsLocation()))
				{
					xa = i;
					collision = true;
					intersectedTile = tileOverlay3;
				}
				else if (playerMob.getBounds_().intersects(tileOverlay.getSolidBoundsLocation()))
				{
					xa = i;
					collision = true;
					intersectedTile = tileOverlay;
				}
				else if (playerMob.getBounds_().intersects(tile2.getSolidBoundsLocation()) || 
						 playerMob.getBounds_().intersects(tile4.getSolidBoundsLocation()) || 
						 playerMob.getBounds_().intersects(tileOverlay2.getSolidBoundsLocation()) ||  
						 playerMob.getBounds_().intersects(tileOverlay4.getSolidBoundsLocation()))
				{
					xa = i;	
					collision = true;
					intersectedTile = null;
				}
				else
				{
					intersectedTile = null;
				}
				playerMob.setBounds_(new Rectangle(startPosX + bounds.x + i, startPosY + bounds.y, bounds.width, bounds.height));
				
				if (collision)
				{
					if (!(intersectedTile == null))
					{
						intersectTile = true; // tells game you are currently intersecting a tile (used for action key checks and other stuff)
					}		
					break;
				}
				else
				{
					intersectTile = false;
				}
			}
		}
		else if(xa < 0) // right
		{
			tile = currentMap.accessTile((currentTileX + 1) + currentTileY * currentMap.getWidth());
			tile2 = currentMap.accessTile((currentTileX + 1) + (currentTileY - 1) * currentMap.getWidth());
		    tile3 = currentMap.accessTile(currentTileX + currentTileY * currentMap.getWidth());
			tile4 = currentMap.accessTile(currentTileX + (currentTileY - 1) * currentMap.getWidth());

			tileOverlay = currentMap.accessOverlayTile((currentTileX + 1) + currentTileY * currentMap.getWidth());
			tileOverlay2 = currentMap.accessOverlayTile((currentTileX + 1) + (currentTileY - 1) * currentMap.getWidth());
		    tileOverlay3 = currentMap.accessOverlayTile(currentTileX + currentTileY * currentMap.getWidth());
			tileOverlay4 = currentMap.accessOverlayTile(currentTileX + (currentTileY - 1) * currentMap.getWidth());
			
			
			bounds = playerMob.getRightBounds();
			playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
			
			
			for (int i = 0; i < playerSpeed + 1; i++)
			{
				playerMob.setBounds_(new Rectangle(startPosX + bounds.x + i, startPosY + bounds.y, bounds.width, bounds.height));
				
				if (playerMob.getBounds_().intersects(tile3.getSolidBoundsLocation()))
				{
					xa = i * -1;
					collision = true;
					intersectedTile = tile3;
				}
				else if (playerMob.getBounds_().intersects(tile.getSolidBoundsLocation()))
				{
					xa = i * -1;
					collision = true;
					intersectedTile = tile;
				}
				else if (playerMob.getBounds_().intersects(tileOverlay3.getSolidBoundsLocation()))
				{
					xa = i * -1;
					collision = true;
					intersectedTile = tileOverlay3;
				}
				else if (playerMob.getBounds_().intersects(tileOverlay.getSolidBoundsLocation()))
				{
					xa = i * -1;
					collision = true;
					intersectedTile = tileOverlay;
				}
				else if (playerMob.getBounds_().intersects(tile2.getSolidBoundsLocation()) || 
						 playerMob.getBounds_().intersects(tile4.getSolidBoundsLocation()) || 
						 playerMob.getBounds_().intersects(tileOverlay2.getSolidBoundsLocation()) ||  
						 playerMob.getBounds_().intersects(tileOverlay4.getSolidBoundsLocation()))
				{
					xa = i * -1;	
					collision = true;
					intersectedTile = null;
				}
				else
				{
					intersectedTile = null;
				}
				
				playerMob.setBounds_(new Rectangle(startPosX + bounds.x - i, startPosY + bounds.y, bounds.width, bounds.height));
			
				if (collision)
				{
					if (!(intersectedTile == null))
					{
						intersectTile = true; // tells game you are currently intersecting a tile (used for action key checks and other stuff)
					}	
					break;
				}
				else
				{
					intersectTile = false;
				}
			}
		}
			
		return xa; // return how many pixels you are allowed to move 
	}
	
	int checkTileCollisionsY(int ya)
	{

		Tile tile;
		Tile tile2;
		Tile tile3;
		Tile tile4;
		Tile tile5;
		Tile tile6;
		
		Tile tileOverlay;
		Tile tileOverlay2;
		Tile tileOverlay3;
		Tile tileOverlay4;
		Tile tileOverlay5;
		Tile tileOverlay6;
		
		Rectangle bounds;
		
		boolean collision = false;

		if(ya > 0) // up	
		{
			tile = currentMap.accessTile(currentTileX + currentTileY * currentMap.getWidth());
			tile2 = currentMap.accessTile((currentTileX - 1) + currentTileY * currentMap.getWidth());
			tile3 = currentMap.accessTile((currentTileX + 1) + currentTileY * currentMap.getWidth());
			tile4 = currentMap.accessTile(currentTileX + (currentTileY - 1) * currentMap.getWidth());
			tile5 = currentMap.accessTile((currentTileX - 1) + (currentTileY - 1) * currentMap.getWidth());
			tile6 = currentMap.accessTile((currentTileX + 1) + (currentTileY - 1) * currentMap.getWidth());
			
			tileOverlay = currentMap.accessOverlayTile(currentTileX + currentTileY * currentMap.getWidth());
			tileOverlay2 = currentMap.accessOverlayTile((currentTileX - 1) + currentTileY * currentMap.getWidth());
			tileOverlay3 = currentMap.accessOverlayTile((currentTileX + 1) + currentTileY * currentMap.getWidth());
			tileOverlay4 = currentMap.accessOverlayTile(currentTileX + (currentTileY - 1) * currentMap.getWidth());
			tileOverlay5 = currentMap.accessOverlayTile((currentTileX - 1) + (currentTileY - 1) * currentMap.getWidth());
			tileOverlay6 = currentMap.accessOverlayTile((currentTileX + 1) + (currentTileY - 1) * currentMap.getWidth());
			
			bounds = playerMob.getUpBounds();
			playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
			
			for (int i = 0; i < playerSpeed + 1; i++)
			{
				playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y - i, bounds.width, bounds.height));
				
				if (playerMob.getBounds_().intersects(tile.getSolidBoundsLocation()))
				{
					ya = i;
					collision = true;
					intersectedTile = tile;
				}
				else if (playerMob.getBounds_().intersects(tile4.getSolidBoundsLocation()))
				{
					ya = i;
					collision = true;
					intersectedTile = tile4;
				}
				else if (playerMob.getBounds_().intersects(tileOverlay.getSolidBoundsLocation()))
				{
					ya = i;
					collision = true;
					intersectedTile = tileOverlay;
				}
				else if (playerMob.getBounds_().intersects(tileOverlay4.getSolidBoundsLocation()))
				{
					ya = i;
					collision = true;
					intersectedTile = tileOverlay4;
				}
				else if (playerMob.getBounds_().intersects(tile2.getSolidBoundsLocation()) || 
						 playerMob.getBounds_().intersects(tile3.getSolidBoundsLocation()) ||
						 playerMob.getBounds_().intersects(tile5.getSolidBoundsLocation()) ||
						 playerMob.getBounds_().intersects(tile6.getSolidBoundsLocation()) ||
						 playerMob.getBounds_().intersects(tileOverlay2.getSolidBoundsLocation()) ||  
						 playerMob.getBounds_().intersects(tileOverlay3.getSolidBoundsLocation()) ||
						 playerMob.getBounds_().intersects(tileOverlay5.getSolidBoundsLocation()) ||
						 playerMob.getBounds_().intersects(tileOverlay6.getSolidBoundsLocation()))
						 
				{
					ya = i;
					collision = true;
					intersectedTile = null;
				}
				else
				{
					intersectedTile = null;
				}
			
				playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y + i, bounds.width, bounds.height));
				
				if (collision)
				{
					if (!(intersectedTile == null))
					{
						intersectTile = true; // tells game you are currently intersecting a tile (used for action key checks and other stuff)
					}	
					break;
				}
				else
				{
					intersectTile = false;
				}
			}
		}
		else if(ya < 0) // down
		{
			tile = currentMap.accessTile(currentTileX + currentTileY * currentMap.getWidth());
			tile2 = currentMap.accessTile((currentTileX - 1) + currentTileY * currentMap.getWidth());
			tile3 = currentMap.accessTile((currentTileX + 1) + currentTileY * currentMap.getWidth());
			tile4 = currentMap.accessTile(currentTileX + (currentTileY + 1) * currentMap.getWidth());
			tile5 = currentMap.accessTile((currentTileX - 1) + (currentTileY + 1) * currentMap.getWidth());
			tile6 = currentMap.accessTile((currentTileX + 1) + (currentTileY + 1) * currentMap.getWidth());
			
			tileOverlay = currentMap.accessOverlayTile(currentTileX + currentTileY * currentMap.getWidth());
			tileOverlay2 = currentMap.accessOverlayTile((currentTileX - 1) + currentTileY * currentMap.getWidth());
			tileOverlay3 = currentMap.accessOverlayTile((currentTileX + 1) + currentTileY * currentMap.getWidth());
			tileOverlay4 = currentMap.accessOverlayTile(currentTileX + (currentTileY + 1) * currentMap.getWidth());
			tileOverlay5 = currentMap.accessOverlayTile((currentTileX - 1) + (currentTileY + 1) * currentMap.getWidth());
			tileOverlay6 = currentMap.accessOverlayTile((currentTileX + 1) + (currentTileY + 1) * currentMap.getWidth());
			
			bounds = playerMob.getDownBounds();
			playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
			
			for (int i = 0; i < playerSpeed + 1; i++)
			{
				playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y + i, bounds.width, bounds.height));
				
				if (playerMob.getBounds_().intersects(tile.getSolidBoundsLocation()))
				{
					ya = i * -1;
					collision = true;
					intersectedTile = tile;
				}
				else if (playerMob.getBounds_().intersects(tile4.getSolidBoundsLocation()))
				{
					ya = i * -1;
					collision = true;
					intersectedTile = tile4;
				}
				else if (playerMob.getBounds_().intersects(tileOverlay.getSolidBoundsLocation()))
				{
					ya = i * -1;
					collision = true;
					intersectedTile = tileOverlay;
				}
				else if (playerMob.getBounds_().intersects(tileOverlay4.getSolidBoundsLocation()))
				{
					ya = i * -1;
					collision = true;
					intersectedTile = tileOverlay4;
				}
				else if (playerMob.getBounds_().intersects(tile2.getSolidBoundsLocation()) || 
						 playerMob.getBounds_().intersects(tile3.getSolidBoundsLocation()) ||
						 playerMob.getBounds_().intersects(tile5.getSolidBoundsLocation()) ||
						 playerMob.getBounds_().intersects(tile6.getSolidBoundsLocation()) ||
						 playerMob.getBounds_().intersects(tileOverlay2.getSolidBoundsLocation()) ||  
						 playerMob.getBounds_().intersects(tileOverlay3.getSolidBoundsLocation()) ||
						 playerMob.getBounds_().intersects(tileOverlay5.getSolidBoundsLocation()) ||
						 playerMob.getBounds_().intersects(tileOverlay6.getSolidBoundsLocation()))
						 
				{
					ya = i * -1;
					collision = true;
					intersectedTile = null;
				}
				else
				{
					intersectedTile = null;
				}
			
				playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y - i, bounds.width, bounds.height));
				
				if (collision)
				{
					if (!(intersectedTile == null))
					{
						intersectTile = true; // tells game you are currently intersecting a tile (used for action key checks and other stuff)
					}	
					break;
				}
				else
				{
					intersectTile = false;
				}
			}
		}

		return ya;
	}
	
	int checkSpriteCollisionsX(int xa)
	{
		Rectangle bounds;
		LinkedList<Mob> mobs = currentMap.getNpcMobsOnScreen();
		boolean collision = false;
		
		
			if (xa > 0) // Left
			{
				bounds = playerMob.getLeftBounds();
				playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
				
				mainLoop:
				for (int i = 0; i < mobs.size(); i++)
				{
					for (int j = 0; j < playerSpeed + 1; j++)
					{
						playerMob.setBounds_(new Rectangle(startPosX + bounds.x - j, startPosY + bounds.y, bounds.width, bounds.height));
					
						if (playerMob.getBounds_().intersects(mobs.get(i).getBoundsLocation()))
						{
							xa = j;	
							collision = true;
						}
						
					
						playerMob.setBounds_(new Rectangle(startPosX + bounds.x + j, startPosY + bounds.y, bounds.width, bounds.height));
						
						if (collision)
						{
							if (!mobs.get(i).getEnemy())
							{
								intersectSprite = true;
							}
							break mainLoop;
						}
						else
						{
							intersectSprite = false;
						}
					}
					
				}
			}
			if (xa < 0) // Right
			{
				bounds = playerMob.getRightBounds();
				playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
				
				mainLoop:
				for (int i = 0; i < mobs.size(); i++)
				{
					for (int j = 0; j < playerSpeed + 1; j++)
					{
						playerMob.setBounds_(new Rectangle(startPosX + bounds.x + j, startPosY + bounds.y, bounds.width, bounds.height));
					
						if (playerMob.getBounds_().intersects(mobs.get(i).getBoundsLocation()))
						{
							xa = j * -1;	
							collision = true;
						}
						
					
						playerMob.setBounds_(new Rectangle(startPosX + bounds.x - j, startPosY + bounds.y, bounds.width, bounds.height));
						
						if (collision)
						{
							if (!mobs.get(i).getEnemy())
							{
								intersectSprite = true;
							}
							break mainLoop;
						}
						else
						{
							intersectSprite = false;
						}
					}
				}
			}
		
		return xa;
	}
	
	int checkSpriteCollisionY(int ya)
	{	
		Rectangle bounds;
		LinkedList<Mob> mobs = currentMap.getNpcMobsOnScreen();
		boolean collision = false;
		
		if (ya > 0) // up
		{
			bounds = playerMob.getUpBounds();
			playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
			
			mainLoop:
			for (int i = 0; i < mobs.size(); i++)
			{
				for (int j = 0; j < playerSpeed + 1; j++)
				{
					playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y - j, bounds.width, bounds.height));
				
					if (playerMob.getBounds_().intersects(mobs.get(i).getBoundsLocation()))
					{
						ya = j;	
						collision = true;
					}
					
				
					playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y + j, bounds.width, bounds.height));
					
					if (collision)
					{
						if (!mobs.get(i).getEnemy())
						{
							intersectSprite = true;
						}
						break mainLoop;
					}
					else
					{
						intersectSprite = false;
					}
				}
			}
		}
		if (ya < 0) // down
		{
			bounds = playerMob.getDownBounds();
			playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
			
			mainLoop:
			for (int i = 0; i < mobs.size(); i++)
			{
				for (int j = 0; j < playerSpeed + 1; j++)
				{
					playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y + j, bounds.width, bounds.height));
				
					if (playerMob.getBounds_().intersects(mobs.get(i).getBoundsLocation()))
					{
						ya = j * -1;	
						collision = true;
					}
					
				
					playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y - j, bounds.width, bounds.height));
					
					if (collision)
					{
						if (!mobs.get(i).getEnemy())
						{
							intersectSprite = true;
						}
						break mainLoop;
					}
					else
					{
						intersectSprite = false;
					}
				}
			}
		}
		
		return ya;
	}
	
	private void updateBounds()
	{
		DIRECTION direction = playerMob.direction;
		Rectangle bounds;
		
		if (direction == DIRECTION.LEFT)
		{
			// set full bounds
			bounds = playerMob.getFullLeftBounds();
			playerMob.setFullBounds_(bounds);
			playerMob.setFullBoundsLocation_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
			
			// set hurtBox
			bounds = playerMob.getHurtBoxLeft();
			playerMob.setHurtBox(bounds);
			playerMob.setHurtBoxLocation(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
			
		}
		else if (direction == DIRECTION.RIGHT)
		{
			// set full bounds
			bounds = playerMob.getFullRightBounds();
			playerMob.setFullBounds_(bounds);
			playerMob.setFullBoundsLocation_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
			
			// set hurtBox
			bounds = playerMob.getHurtBoxRight();
			playerMob.setHurtBox(bounds);
			playerMob.setHurtBoxLocation(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
		}
		else if (direction == DIRECTION.UP)
		{
			// set full bounds
			bounds = playerMob.getFullUpBounds();
			playerMob.setFullBounds_(bounds);
			playerMob.setFullBoundsLocation_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
			
			// set hurtBox
			bounds = playerMob.getHurtBoxUp();
			playerMob.setHurtBox(bounds);
			playerMob.setHurtBoxLocation(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
		}
		else // if (direction == DIRECTION.DOWN)
		{
			// set full bounds
			bounds = playerMob.getFullDownBounds();
			playerMob.setFullBounds_(bounds);
			playerMob.setFullBoundsLocation_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
			
			// set hurtBox
			bounds = playerMob.getHurtBoxDown();
			playerMob.setHurtBox(bounds);
			playerMob.setHurtBoxLocation(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
		}
		bounds = playerMob.getHurtBoxDown();
		playerMob.setHealthBarLocation(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
	}
	
	private void updateAttackBounds()
	{
		DIRECTION direction = playerMob.direction;
		Rectangle bounds;
		
		if (direction == DIRECTION.LEFT)
		{
			bounds = playerMob.getAttack("sword").getAttackLeftBounds().get(frameNumber);
			playerMob.getAttack("sword").setAttackBounds(new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height));
		}
		else if (direction == DIRECTION.RIGHT)
		{
			bounds = playerMob.getAttack("sword").getAttackRightBounds().get(frameNumber);
			playerMob.getAttack("sword").setAttackBounds(new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height));
		}
		else if (direction == DIRECTION.UP)
		{
			bounds = playerMob.getAttack("sword").getAttackUpBounds().get(frameNumber);
			playerMob.getAttack("sword").setAttackBounds(new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height));
		}
		else //if (direction == DIRECTION.DOWN)
		{
			bounds = playerMob.getAttack("sword").getAttackDownBounds().get(frameNumber);
			playerMob.getAttack("sword").setAttackBounds(new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height));
		}
		playerMob.getAttack("sword").setAttackBoundsLocation(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
		
//		Rectangle test = playerMob.getAttack("sword").getAttackBoundsLocation();
//		float thickness = 1;
//		Stroke oldStroke = graphics().getStroke();
//		graphics().setStroke(new BasicStroke(thickness));
//		graphics().drawRect(test.x, test.y, test.width, test.height);
//		graphics().setStroke(oldStroke);
	}
	
	int frameNumber = 0;
	int cpuFrameNumber = 0;
	boolean hit;
	boolean playerHit;
	private void combat()
	{
		if (playerMob.attacking())
		{
			if (playerMob.getCurrentAttack().getName().equals("sword"))
			{
				updateAttackBounds();

				if(playerMob.getPrevCurrentFrame() != playerMob.currentFrame())
				{									
					frameNumber++;
					
					if (frameNumber == playerMob.getCurrentAttack().getAttackTotal())
					{
						frameNumber = 0;
						hit = false;
						return;
					}
				}
				
				if (!hit)
				{
					for (int i = 0; i < currentMap.getNpcMobsOnScreen().size(); i++)
					{
						if (playerMob.getAttack("sword").getAttackBoundsLocation().intersects(currentMap.getNpcMobsOnScreen().get(i).getHurtBoxLocation()) 
								&& currentMap.getNpcMobsOnScreen().get(i).getEnemy() != false)
						{
							
							for (int j = 0; j < currentMap.getMobs().size(); j++)
							{
								if (currentMap.getMobs().get(j).getName().equals(currentMap.getNpcMobsOnScreen().get(i).getName()))
								{
									int damage = playerMob.getAttack("sword").getDamage();
									currentMap.getMobs().get(j).setHealth(currentMap.getMobs().get(j).getCurrHealth() - damage);
									
									try{
									JavaAudioPlaySoundExample("/sounds/Hit_Hurt.wav");}
									catch (Exception ex) {}
				
									if (currentMap.getMobs().get(j).getCurrHealth() <= 0)
									{
										currentMap.getMobs().get(j).setHealth(0);
										currentMap.getMobs().remove(j);
									}
									currentMap.getMobs().get(j).setIsAttacked(true);
								}
							}
							hit = true;
						}
					}
				}
			}
				
		}
		else
		{
			updateAttackBounds();
		}
		
		for (int i = 0; i < currentMap.getMobs().size() - 1; i++)
		{
			if (currentMap.getMobs().get(i).attacking())
			{
				if (currentMap.getMobs().get(i).getCurrentAttack().getName().equals("sword"))
				{
					if(currentMap.getMobs().get(i).getPrevCurrentFrame() != currentMap.getMobs().get(i).currentFrame())
					{									
						cpuFrameNumber++;
						
						if (cpuFrameNumber == currentMap.getMobs().get(i).getCurrentAttack().getAttackTotal())
						{
							cpuFrameNumber = 0;
							playerHit = false;
							return;
						}
					}
					Mob player = null;
					int playerIndex = 0;
					if (!playerHit)
					{
						for (int j = 0; j < currentMap.getMobs().size() - 1; j++)
						{
							if (currentMap.getMobs().get(j).getType() == TYPE.PLAYER)
							{
								player = currentMap.getMobs().get(j);
								playerIndex = j;
							}
						
						}
						if (currentMap.getMobs().get(i).getEnemy())
								{
							//if (currentMap.getMobs().get(i).getAttack("sword").getAttackBoundsLocation().intersects(player.getHurtBoxLocation()))
						//	{
									//if (currentMap.getMobs().get(i).getName().equals(currentMap.getNpcMobsOnScreen().get(i).getName()))
									//{
										int damage = currentMap.getMobs().get(i).getAttack("sword").getDamage();
										currentMap.getMobs().get(playerIndex).setHealth(currentMap.getMobs().get(playerIndex).getCurrHealth() - damage);
										
										try{
										JavaAudioPlaySoundExample("/sounds/Hit_Hurt.wav");}
										catch (Exception ex) {}
					
										if (currentMap.getMobs().get(playerIndex).getCurrHealth() <= 0)
										{
											currentMap.getMobs().get(playerIndex).setHealth(0);
											currentMap.getMobs().remove(playerIndex);
										}
									//}
								}
								playerHit = true;
							//}
					}
					}
				}
			}
		}
	
	
	
	/*****************************************************************
	 * @param int
	 * @param int
	 * 
	 *Method to call which moves the player. The player never moves apart from the map
	 *unless the player is at an edge of the generated map. Also, to simulate the movement
	 *of the space around the player like that, the X movement is flipped. 
	 *Which means to move right, you subtract from the X position.
	 ******************************************************************/
	// CHANGED from above ^^ now ONLY moves the map as you move
	void movePlayer() 
	{	
		// check tile collisions
		if (xa != 0) // Check tile collisions X as long as you are moving left or right
		{
			xa = checkTileCollisionsX(xa);
			xa = checkSpriteCollisionsX(xa);
		}
		if (ya != 0) // Check tile collisions X as long as you are moving up or down
		{
			ya = checkTileCollisionsY(ya);
			ya = checkSpriteCollisionY(ya);
		}
		
		if (xa == 0) // Not moving horizontal
		{
			previousMapX = mapX;
			previousTileX = currentTileX;
		}
		if (ya == 0) // Not moving vertical
		{
			previousMapY = mapY;
			previousTileY = currentTileY;
		}
		if(xa > 0) // Left	
		{ 	
			previousMapX = mapX; // sets previousMap to Map before Map changes
			previousTileX = currentTileX; // sets previousTile to currentTile before currentTile possibly changes
			mapX += xa; // moves the map the correct direction by the correct amount
			currentPosX += xa * -1;	// gets the player's current position on map (since it's the player's position and not the camera, we need the opposite value, so that's why it's multiplied by 1)
			currentTileX = (currentPosX + 64 + (mapOffsetX * -1)) / 64; // gets the player's current tile, it works don't question it
		}
		if(xa < 0)  // Right
		{
			previousMapX = mapX;
			previousTileX = currentTileX;
			mapX += xa;
			currentPosX += xa * -1;
			currentTileX = (currentPosX + 64 + (mapOffsetX * -1)) / 64;
		}
		if(ya > 0) // up
		{ 
			previousMapY = mapY;
			previousTileY = currentTileY;
			mapY += ya;
			currentPosY += ya * -1;
			currentTileY = (currentPosY + 128 + (mapOffsetY * -1)) / 64;
		}
		if(ya < 0)  // down
		{
			previousMapY = mapY;
			previousTileY = currentTileY;
			mapY += ya;
			currentPosY += ya * -1;
			currentTileY = (currentPosY + 128 + (mapOffsetY * -1)) / 64;
		}
		
		//updateBounds();

	//	System.out.println(currentTileX + " " + currentTileY);
	//	System.out.println(xa + ", " + ya);
	}
	
	// Activated when pressing the action key on a tile
	// checks if you're intersecting a tile's event bounds box (99% chance it'll be the same as the tile's solid bounds)
	// if you are, it'll get that tile's event, and execute the event accordingly
	public void tileIntersectionEvents()
	{
		DIRECTION currentDirection = playerMob.direction; // player's current direction (which way he/she is facing)
		
//		// location of tile you are intersecting (if there is one)
//		Point tileBoundsIntersect = null;
//		Point tileOverlayBoundsIntersect = null;
//		
//		tileBoundsIntersect = getIntersectedTile(currentDirection);
//		
//		if (tileBoundsIntersect == null)
//		{
//			tileOverlayBoundsIntersect = getIntersectedOverlayTile(currentDirection);
//		}
//		
//				
//		//  will be the tile you are currently intersecting (if there is one)
//		Tile tileIntersected = null;
//		
//		// will be the event type of the tile you are currently intersecting (if there is one)
//		TileEventType tileEvent;
//		String eventType;
//		
//		if (tileBoundsIntersect == null && tileOverlayBoundsIntersect == null) // if you are not intersecting a tile with an event, exit void
//		{
//			//tileEvent = TileEventType.NOTHING;
//			return;
//		}
//		else if (tileBoundsIntersect != null && tileOverlayBoundsIntersect == null) // if you are intersecting with a tile with an event, get the event of the tile
//		{
//			// gets the exact tile intersected based on the Point of tileBoundsIntersect
//		    tileIntersected = currentMap.accessTile(tileBoundsIntersect.x + tileBoundsIntersect.y * currentMap.getWidth());
//			
//		    // gets the tile intersected's tile event
//		    eventType = tileIntersected.getEventType().getName();
//		}
//		else
//		{
//			// gets the exact tile intersected based on the Point of tileBoundsIntersect
//		    tileIntersected = currentMap.accessOverlayTile(tileOverlayBoundsIntersect.x + tileOverlayBoundsIntersect.y * currentMap.getWidth());
//			
//		    // gets the tile intersected's tile event
//		    eventType = tileIntersected.getEventType().getName(); 
//		}
		intersectedTileEvent = intersectedTile.getEventType().getName();
		
		switch(intersectedTileEvent) // find event that matches up
		{
			case "Chest": // event for chests
				if (currentDirection == DIRECTION.UP) // if player is facing up 
				{	
					// depending on the chest's landscape, change the chest tile to the correct chest open tile
					// (for example, a chest on a brick background will need an open chest with a brick background as well)
					if (intersectedTile._name.equals("chest")) 
					{
						Tile tile = tileCreator.create(this, graphics(), scale, "chestOpen");
						currentMap.setOverlayTile(intersectedTile.getMapCoordX(), intersectedTile.getMapCoordY(), tile); // set tile to open chest
						intersectTile = false;
						intersectedTile = null;
						
						try{
							JavaAudioPlaySoundExample("/sounds/Open_Chest.wav");}
							catch (Exception ex) {}
					}
				}
				break;
			case "Nothing":
				break;
		
		}
		
		
	}
	
//	public Point getIntersectedTile(DIRECTION currentDirection)
//	{
//		Point intersectedTile = null;
//		
//		if (currentDirection == DIRECTION.LEFT)
//		{
//		
//			Rectangle bounds = playerMob.getLeftBounds();
//			playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
//			
//			Tile tile = currentMap.accessTile(currentTileX + currentTileY * currentMap.getWidth());
//			Tile tile2 = currentMap.accessTile((currentTileX - 1) + currentTileY * currentMap.getWidth());
//			
//			if (playerMob.getBounds_().intersects(tile.getSolidBoundsLocation()))
//			{
//				intersectedTile = new Point(currentTileX, currentTileY);
//			}
//			else if (playerMob.getBounds_().intersects(tile2.getSolidBoundsLocation()))
//			{
//				intersectedTile = new Point(currentTileX - 1, currentTileY);
//			}
//			else
//			{
//				intersectedTile = null;
//			}
//		}
//		else if (currentDirection == DIRECTION.RIGHT)
//		{
//			Rectangle bounds = playerMob.getRightBounds();
//			playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
//			
//			Tile tile = currentMap.accessTile(currentTileX + currentTileY * currentMap.getWidth());
//			Tile tile2 = currentMap.accessTile((currentTileX + 1) + currentTileY * currentMap.getWidth());
//			
//			if (playerMob.getBounds_().intersects(tile.getSolidBoundsLocation()))
//			{
//				intersectedTile = new Point(currentTileX, currentTileY);
//			}
//			else if (playerMob.getBounds_().intersects(tile2.getSolidBoundsLocation()))
//			{
//				intersectedTile = new Point(currentTileX + 1, currentTileY);
//			}
//			else
//			{
//				intersectedTile = null;
//			}
//		}
//		else if (currentDirection == DIRECTION.UP)
//		{
//			
//			Rectangle bounds = playerMob.getUpBounds();
//			playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
//			
//			Tile tile = currentMap.accessTile(currentTileX + currentTileY * currentMap.getWidth());
//			Tile tile2 = currentMap.accessTile(currentTileX + (currentTileY - 1) * currentMap.getWidth());
//			
//			if (playerMob.getBounds_().intersects(tile.getSolidBoundsLocation()))
//			{
//				intersectedTile = new Point(currentTileX, currentTileY);
//			}
//			else if (playerMob.getBounds_().intersects(tile2.getSolidBoundsLocation()))
//			{
//				intersectedTile = new Point(currentTileX, currentTileY - 1);
//			}
//			else
//			{
//				intersectedTile = null;
//			}
//			
//		}
//		else if (currentDirection == DIRECTION.DOWN)
//		{
//			Rectangle bounds = playerMob.getDownBounds();
//			playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
//			
//			Tile tile = currentMap.accessTile(currentTileX + currentTileY * currentMap.getWidth());
//			Tile tile2 = currentMap.accessTile(currentTileX + (currentTileY + 1) * currentMap.getWidth());
//			
//			if (playerMob.getBounds_().intersects(tile.getSolidBoundsLocation()))
//			{
//				intersectedTile = new Point(currentTileX, currentTileY);
//			}
//			else if (playerMob.getBounds_().intersects(tile2.getSolidBoundsLocation()))
//			{
//				intersectedTile = new Point(currentTileX, currentTileY + 1);
//			}
//			else
//			{
//				intersectedTile = null;
//			}
//		}
//		
//		
//		return intersectedTile;
//	}
//	
//	public Point getIntersectedOverlayTile(DIRECTION currentDirection)
//	{
//		Point intersectedTile = null;
//		
//		if (currentDirection == DIRECTION.LEFT)
//		{
//		
//			Rectangle bounds = playerMob.getLeftBounds();
//			playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
//			
//			Tile tile = currentMap.accessOverlayTile(currentTileX + currentTileY * currentMap.getWidth());
//			Tile tile2 = currentMap.accessOverlayTile((currentTileX - 1) + currentTileY * currentMap.getWidth());
//			
//			if (playerMob.getBounds_().intersects(tile.getSolidBoundsLocation()))
//			{
//				intersectedTile = new Point(currentTileX, currentTileY);
//			}
//			else if (playerMob.getBounds_().intersects(tile2.getSolidBoundsLocation()))
//			{
//				intersectedTile = new Point(currentTileX - 1, currentTileY);
//			}
//			else
//			{
//				intersectedTile = null;
//			}
//		}
//		else if (currentDirection == DIRECTION.RIGHT)
//		{
//			Rectangle bounds = playerMob.getRightBounds();
//			playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
//			
//			Tile tile = currentMap.accessOverlayTile(currentTileX + currentTileY * currentMap.getWidth());
//			Tile tile2 = currentMap.accessOverlayTile((currentTileX + 1) + currentTileY * currentMap.getWidth());
//			
//			if (playerMob.getBounds_().intersects(tile.getSolidBoundsLocation()))
//			{
//				intersectedTile = new Point(currentTileX, currentTileY);
//			}
//			else if (playerMob.getBounds_().intersects(tile2.getSolidBoundsLocation()))
//			{
//				intersectedTile = new Point(currentTileX + 1, currentTileY);
//			}
//			else
//			{
//				intersectedTile = null;
//			}
//		}
//		else if (currentDirection == DIRECTION.UP)
//		{
//			
//			Rectangle bounds = playerMob.getUpBounds();
//			playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
//			
//			Tile tile = currentMap.accessOverlayTile(currentTileX + currentTileY * currentMap.getWidth());
//			Tile tile2 = currentMap.accessOverlayTile(currentTileX + (currentTileY - 1) * currentMap.getWidth());
//			
//			if (playerMob.getBounds_().intersects(tile.getSolidBoundsLocation()))
//			{
//				intersectedTile = new Point(currentTileX, currentTileY);
//			}
//			else if (playerMob.getBounds_().intersects(tile2.getSolidBoundsLocation()))
//			{
//				intersectedTile = new Point(currentTileX, currentTileY - 1);
//			}
//			else
//			{
//				intersectedTile = null;
//			}
//			
//		}
//		else if (currentDirection == DIRECTION.DOWN)
//		{
//			Rectangle bounds = playerMob.getDownBounds();
//			playerMob.setBounds_(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
//			
//			Tile tile = currentMap.accessOverlayTile(currentTileX + currentTileY * currentMap.getWidth());
//			Tile tile2 = currentMap.accessOverlayTile(currentTileX + (currentTileY + 1) * currentMap.getWidth());
//			
//			if (playerMob.getBounds_().intersects(tile.getSolidBoundsLocation()))
//			{
//				intersectedTile = new Point(currentTileX, currentTileY);
//			}
//			else if (playerMob.getBounds_().intersects(tile2.getSolidBoundsLocation()))
//			{
//				intersectedTile = new Point(currentTileX, currentTileY + 1);
//			}
//			else
//			{
//				intersectedTile = null;
//			}
//		}
//		
//		
//		
//		
//		return intersectedTile;
//	}
	public boolean checkDirectionAllowed(LinkedList<DIRECTION> direction)
	{
		for (int i = 0; i < direction.size(); i++)
		{
			if (playerMob.direction == direction.get(i))
			{
				return true;
			}
		}
		return false;
	}
	
	/**********************************************************
	 * Main
	 * 
	 * @param args
	 ********************************************************/
	public static void main(String[] args) { new Judgement(); }
	
	/**********************************************************
	 * The Depths of Judgement Lies Below
	 * 
	 *             Key events - Mouse events
	 *                            
	 ***********************************************************/

	/****************************************************************
	 * Check specifically defined key presses which do various things
	 ****************************************************************/

	String firstKey = "";
	String secondKey = "";
	String thirdKey = "";
	String rightOrLeft = "";
	String upOrDown = "";
	boolean canMove = true;
	boolean canAction = true;
	boolean canEnter = true;
	boolean canInGameMenu = true;
	boolean canInventory = true;
	boolean canStatus = true;
	boolean canTutorial = true;
	
	int xa;
	int ya;
	public void checkInput() {
	//	int xa = 0;
	//	int ya = 0;
		xa = 0;
		ya = 0;
		
		/********************************************
		 * Special actions for In Game
		 *******************************************/
		
// NOTE: here is where inventory goes wrong
// need to check STATE.GAME
		if(state == STATE.GAME && inputWait < 0) 
		{
//			//I(Inventory)
//			if(keyInventory) 
//			{
//				//state = STATE.INGAMEMENU;
//				inputWait = 10;
//			}
			
			// QUIT BUTTON (TEMP)
//			if(keyBack)
//			{
//				state = STATE.TITLE;
//				option = OPTION.NONE;
//				sprites().add(titleArrow);
//				sprites().add(titleEraseBomb);
//				
//				titleLocation = 1;
//				titleX = 520;
//				titleY = 510;
//
//			}

			// if action key is pressed ('z' key)
			if (canAction)
			{
				if(keyAction)
				{
					if (intersectTile && !intersectedTile.getEventType().getName().equals("Nothing") &&
							checkDirectionAllowed(intersectedTile.getEventType().getDirection())) // if you are intersecting a tile, check to see if any events need to be activated
					{
						tileIntersectionEvents();
						inputWait = 10;
						return;
					}
					else if (intersectSprite)
					{
//						try{
//							JavaAudioPlaySoundExample("/sounds/Talk.wav");}
//							catch (Exception ex) {}
						renderTextbox = true;
						//inputWait = 10;
						return;
					}
					else
					{
						playerMob.attack();
						
						inputWait = 24;
						return;
					}
				//	return;
				}
			}
			
			if(keyShift)
			{
				playerSpeed = 14;
			}
			else
			{
				playerSpeed = 4;
			}
			
			if (canEnter)
			{
				if(keyEnter)
				{
					if (!renderInGameMenu)
					{
						renderInGameMenu = true;
						canMove = false;
						canAction = false;
						playerMob.stopAnim();
					}
					else
					{
						renderInGameMenu = false;
						canMove = true;
						canAction = true;
						playerMob.stopAnim();
					}
						inputWait = 10;
				}
			}
			
			// ------------SCRUM CYCLE 2 NEW-----------
			// Inventory items selectable
			if (renderInventory && !canInGameMenu)
			{
				if(keyUp)
				{
					inventory.indexUp();
					inputWait = 10;
				}
				if(keyDown)
				{
					inventory.indexDown();
					inputWait = 10;
				}
			}
			
			if (renderInGameMenu && canInGameMenu)
			{
				if (keyUp)
				{
					selection--;
					if (selection < 0)
					{
						selection = 3;
					}
					inputWait = 10;
				}
				if (keyDown)
				{
					selection++;
					if (selection > 4)
					{
						selection = 0;
					}
					inputWait = 10;
				}
				if (keyAction)
				{
					System.out.println("Selection == " + selection);
					
					// open status screen
					if (selection == 0)
					{
						renderStatus = true;
						canInGameMenu = false;
						canEnter = false;
						inputWait = 10;
					}
					
					
					if (selection == 1)
					{
						renderInventory = true;
						canInGameMenu = false;
						canEnter = false;
						inputWait = 10;
					}
					if (selection == 2)
					{
						// for saving function
					}
					
					if(selection == 3){
						renderTutorial = true;
					}
					
					else if (selection == 4)
					{ 
						System.exit(1); 
						//New Version: Quits the game directly to the desktop!
						
						
						
						// THE OLD QUIT - Bring you back to the main menu
						state = STATE.TITLE;
						option = OPTION.NONE;
						sprites().add(titleArrow);
						sprites().add(titleEraseBomb);
						
						titleLocation = 1;
						titleX = 520;
						titleY = 510;
						
						renderInGameMenu = false;
						selection = 0;
						canMove = true;
						canAction = true;
						inputWait = 10;
						//
					}
				}
				if (keyCancel)
				{
					renderInGameMenu = false;
					canMove = true;
					canAction = true;
					playerMob.stopAnim();
					
					inputWait = 10;
				}
			}
			
			if (renderInventory && canInventory)
			{
				if (keyCancel)
				{
					renderInventory = false;
					canEnter = true;
					canInGameMenu = true;
					
					inputWait = 10;
				}
			}
			
			
			// close status screen and reverse states
			if (renderStatus && canStatus)
			{
				if (keyCancel)
				{
					renderStatus = false;
					canEnter = true;
					canInGameMenu = true;
					
					inputWait = 10;
				}
			}
			
			if (renderTutorial && canTutorial)
			{
				if (keyCancel)
				{
					renderTutorial = false;
					canEnter = true;
					canInGameMenu = true;
					
					inputWait = 10;
				}
			}
			
			if (keyChat) {
				if (dialogueWait <= 0) {
					if (dialogueTracker <= 71) {
						dialogueTracker++;
						textbox.setTextBox(dialogue[dialogueTracker]);
						dialogueWait = 6;
					}
				}
			}

//			if(keyAction)
//			{
//				if (!renderTextbox)
//				{
//					renderTextbox = true;
//				}
//				else
//				{
//					renderTextbox = false;
//				}
//				inputWait = 10;
//			}
				
			//SpaceBar(action button)
		/*	if(keySpace) 
			{
				try{
				JavaAudioPlaySoundExample("/sounds/Blip_Select.wav");
				}
				catch (Exception ex) {}
				
				
				for (int i = 0; i < currentMap.getMobs().size(); i++)
				{
					if (currentMap.getMobs().get(i).getType() == TYPE.NPC)
					{

					//	currentMap.getMobs().get(i).attack();
						if (currentMap.getMobs().get(i).animating())
						{
							currentMap.getMobs().get(i).stopAnim();
						}
						else
						{
							currentMap.getMobs().get(i).startAnim();
						}
						
						

					}
				}
				//playerMob.inOutItem();

				inputWait = 20;
			}
			*/
//			else
//			{
//				canMove = true;
//			}
//			
			

			
			if(keyChange)
			{
				Player player = new Player();
				playerMob = player.getNewPlayer(mainCharacter, playerMob, graphics(), this, sprites(), playerNumber);
				inputWait = 10;
				if (playerNumber == 1)
				{
					playerNumber++;
				}
				else if (playerNumber == 2)
				{
					playerNumber--;
				}
				for (int i = 0; i < currentMap.getMobs().size(); i++)
				{
					if (currentMap.getMobs().get(i).getName() == "Original")
					{
						currentMap.getMobs().remove(i);
						break;
					}
				}
				currentMap.getMobs().add(playerMob);
			}
			
			// Begin movement
			// It might be best to just not touch movement ever again after this
			if (canMove)
			{	
				//A or left arrow(move left)
				if(keyLeft && !keyRight && !keyUp && !keyDown) {
					xa = xa + 1 + playerSpeed;
					playerMob.updatePlayer(keyLeft, keyRight, keyUp, keyDown);
					firstKey = "Left";
				}
				//D or right arrow(move right)
				if(keyRight && !keyLeft && !keyUp && !keyDown) {
					xa = xa - 1 - playerSpeed;
					playerMob.updatePlayer(keyLeft, keyRight, keyUp, keyDown);
					firstKey = "Right";
				}
				//W or up arrow(move up)
			    if(keyUp && !keyLeft && !keyRight && !keyDown) {
					ya = ya + 1 + playerSpeed;
					playerMob.updatePlayer(keyLeft, keyRight, keyUp, keyDown);
					firstKey = "Up";
				}
				//S or down arrow(move down)
				if(keyDown && !keyLeft && !keyRight && !keyUp) {
					ya = ya - 1 - playerSpeed;
					playerMob.updatePlayer(keyLeft, keyRight, keyUp, keyDown);
					firstKey = "Down";
				}
				
				// Diagonal
				if(keyLeft && keyDown && !keyRight && !keyUp)
				{
					xa = xa + 1 + playerSpeed;
					ya = ya - 1 - playerSpeed;
					playerMob.updatePlayer(false, keyRight, keyUp, keyDown);
					
					if (firstKey.equals("Left"))
					{
						secondKey = "Down";
					}
					else if (firstKey.equals("Down"))
					{
						secondKey = "Left";
					}
				}
				
				if(keyLeft && keyUp && !keyRight && !keyDown)
				{
					xa = xa + 1 + playerSpeed;
					ya = ya + 1 + playerSpeed;
					playerMob.updatePlayer(false, keyRight, keyUp, keyDown);
					
					if (firstKey.equals("Left"))
					{
						secondKey = "Up";
					}
					else if (firstKey.equals("Up"))
					{
						secondKey = "Left";
					}
				}
				
				if(keyRight && keyDown && !keyLeft && !keyUp)
				{
					xa = xa - 1 - playerSpeed;
					ya = ya - 1 - playerSpeed;
					playerMob.updatePlayer(keyLeft, false, keyUp, keyDown);
					
					if (firstKey.equals("Right"))
					{
						secondKey = "Down";
					}
					else if (firstKey.equals("Down"))
					{
						secondKey = "Right";
					}
				}
				
				if(keyRight && keyUp && !keyLeft && !keyDown)
				{
					xa = xa - 1 - playerSpeed;
					ya = ya + 1 + playerSpeed;
					playerMob.updatePlayer(keyLeft, false, keyUp, keyDown);
					
					if (firstKey.equals("Right"))
					{
						secondKey = "Up";
					}
					else if (firstKey.equals("Up"))
					{
						secondKey = "Right";
					}
				}
				
				// Solves what happens if you press opposite keys (example, if you hold left and then press right)
				// The second key will always overwrite the first
				if(keyLeft && keyRight && !keyUp && !keyDown) {
					if (firstKey.equals("Left"))
					{
						xa = xa - 1 - playerSpeed;
						playerMob.updatePlayer(false, true, keyUp, keyDown);
						secondKey = "Right";
					}
				    if (firstKey.equals("Right"))
					{
						xa = xa + 1 + playerSpeed;
						playerMob.updatePlayer(true, false, keyUp, keyDown);
						secondKey = "Left";
					}
				    if (firstKey.equals("Up") || firstKey.equals("Down"))
				    {
				    	if (rightOrLeft.equals("Left"))
				    	{
				    		xa = xa + 1 + playerSpeed;
							playerMob.updatePlayer(true, false, keyUp, keyDown);
				    	}
				    	if (rightOrLeft.equals("Right"))
				    	{
				    		xa = xa - 1 - playerSpeed;
							playerMob.updatePlayer(false, true, keyUp, keyDown);
				    	}
				    }
				}
				
				if(keyUp && keyDown && !keyLeft && !keyRight) {
					if (firstKey.equals("Up"))
					{
						ya = ya - 1 - playerSpeed;
						playerMob.updatePlayer(keyLeft, keyRight, false, true);
						secondKey = "Down";
					}
				    if (firstKey.equals("Down"))
					{
				    	ya = ya + 1 + playerSpeed;
						playerMob.updatePlayer(keyLeft, keyRight, true, false);
						secondKey = "Up";
					}
				    if (firstKey.equals("Left") || firstKey.equals("Right"))
				    {
				    	if (upOrDown.equals("Up"))
				    	{
				    		ya = ya + 1 + playerSpeed;
							playerMob.updatePlayer(false, false, true, false);
				    	}
				    	if (upOrDown.equals("Down"))
				    	{
							ya = ya - 1 - playerSpeed;
							playerMob.updatePlayer(false, false, false, true);
				    	}
				    }
				}
				
				// Solves what happens when three keys are pressed at once
				// The third key will overwrite 
				// Will always lead to a diagonal movement
				if(keyLeft && keyRight && keyUp && !keyDown) {
					if (firstKey.equals("Left"))
					{
						if (secondKey.equals("Right"))
						{
							xa = xa - 1 - playerSpeed;
							ya = ya + 1 + playerSpeed;
							playerMob.updatePlayer(false, false, true, keyDown);
							thirdKey = "Up";
						}
						if (secondKey.equals("Up"))
						{
							xa = xa - 1 - playerSpeed;
							ya = ya + 1 + playerSpeed;
							playerMob.updatePlayer(false, false, true, keyDown);
							thirdKey = "Right";
						}
						if (secondKey.equals("Down"))
						{						
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Down"))
					    	{
						    	xa = xa - 1 - playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Up"))
					    	{
					    		xa = xa + 1 + playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Up"))
					    	{
					    		xa = xa - 1 - playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Down"))
					    	{
					    		xa = xa + 1 + playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}						    
						}
	
					}
				    if (firstKey.equals("Right"))
					{
				    	if (secondKey.equals("Left"))
						{
				    		xa = xa + 1 + playerSpeed;
				    		ya = ya + 1 + playerSpeed;
							playerMob.updatePlayer(false, false, true, keyDown);
							thirdKey = "Up";
						}
						if (secondKey.equals("Up"))
						{
							xa = xa + 1 + playerSpeed;
							ya = ya + 1 + playerSpeed;
							playerMob.updatePlayer(false, false, true, keyDown);
							thirdKey = "Left";
						}
						if (secondKey.equals("Down"))
						{						
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Down"))
					    	{
						    	xa = xa - 1 - playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Up"))
					    	{
					    		xa = xa + 1 + playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Up"))
					    	{
					    		xa = xa - 1 - playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Down"))
					    	{
					    		xa = xa + 1 + playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}						    
						}
					}
				    
				    if (firstKey.equals("Up"))
					{
				    	if (secondKey.equals("Left"))
						{
				    		xa = xa - 1 - playerSpeed;
				    		ya = ya + 1 + playerSpeed;
							playerMob.updatePlayer(false, false, true, keyDown);
							thirdKey = "Right";
						}
						if (secondKey.equals("Right"))
						{
							xa = xa + 1 + playerSpeed;
							ya = ya + 1 + playerSpeed;
							playerMob.updatePlayer(false, false, true, keyDown);
							thirdKey = "Left";
						}
						if (secondKey.equals("Down"))
						{						
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Down"))
					    	{
						    	xa = xa - 1 - playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Up"))
					    	{
					    		xa = xa + 1 + playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Up"))
					    	{
					    		xa = xa - 1 - playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Down"))
					    	{
					    		xa = xa + 1 + playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}						    
						}
					}
				    if (firstKey.equals("Down")) 
				    {
				    	if (rightOrLeft.equals("Right") && upOrDown.equals("Down"))
				    	{
					    	xa = xa - 1 - playerSpeed;
							ya = ya - 1 - playerSpeed;
							playerMob.updatePlayer(false, false, false, true);
				    	}
				    	if (rightOrLeft.equals("Left") && upOrDown.equals("Up"))
				    	{
				    		xa = xa + 1 + playerSpeed;
				    		ya = ya + 1 + playerSpeed;
							playerMob.updatePlayer(false, false, true, false);
				    	}
				    	if (rightOrLeft.equals("Right") && upOrDown.equals("Up"))
				    	{
				    		xa = xa - 1 - playerSpeed;
				    		ya = ya + 1 + playerSpeed;
							playerMob.updatePlayer(false, false, true, false);
				    	}
				    	if (rightOrLeft.equals("Left") && upOrDown.equals("Down"))
				    	{
				    		xa = xa + 1 + playerSpeed;
							ya = ya - 1 - playerSpeed;
							playerMob.updatePlayer(false, false, false, true);
				    	}
				    }
				}
				if(keyLeft && keyRight && !keyUp && keyDown) {
					if (firstKey.equals("Left"))
					{
						if (secondKey.equals("Right"))
						{
							xa = xa - 1 - playerSpeed;
							ya = ya - 1 - playerSpeed;
							playerMob.updatePlayer(false, false, keyUp, true);
							thirdKey = "Down";
						}
						if (secondKey.equals("Down"))
						{
							xa = xa - 1 - playerSpeed;
							ya = ya - 1 - playerSpeed;
							playerMob.updatePlayer(false, false, keyUp, true);
							thirdKey = "Right";
						}
						if (secondKey.equals("Up"))
						{						
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Down"))
					    	{
						    	xa = xa - 1 - playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Up"))
					    	{
					    		xa = xa + 1 + playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Up"))
					    	{
					    		xa = xa - 1 - playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Down"))
					    	{
					    		xa = xa + 1 + playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}						    
						}
	
					}
				    if (firstKey.equals("Right"))
					{
				    	if (secondKey.equals("Left"))
						{
				    		xa = xa + 1 + playerSpeed;
				    		ya = ya - 1 - playerSpeed;
							playerMob.updatePlayer(false, false, keyUp, true);
							thirdKey = "Down";
						}
						if (secondKey.equals("Down"))
						{
							xa = xa + 1 + playerSpeed;
							ya = ya - 1 - playerSpeed;
							playerMob.updatePlayer(false, false, keyUp, true);
							thirdKey = "Left";
						}
						if (secondKey.equals("Up"))
						{						
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Down"))
					    	{
						    	xa = xa - 1 - playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Up"))
					    	{
					    		xa = xa + 1 + playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Up"))
					    	{
					    		xa = xa - 1 - playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Down"))
					    	{
					    		xa = xa + 1 + playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}						    
						}
					}
				    
				    if (firstKey.equals("Down"))
					{
				    	if (secondKey.equals("Left"))
						{
				    		xa = xa - 1 - playerSpeed;
				    		ya = ya - 1 - playerSpeed;
							playerMob.updatePlayer(false, false, keyUp, true);
							thirdKey = "Right";
						}
						if (secondKey.equals("Right"))
						{
							xa = xa + 1 + playerSpeed;
							ya = ya - 1 - playerSpeed;
							playerMob.updatePlayer(false, false, keyUp, true);
							thirdKey = "Left";
						}
						if (secondKey.equals("Up"))
						{						
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Down"))
					    	{
						    	xa = xa - 1 - playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Up"))
					    	{
					    		xa = xa + 1 + playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Up"))
					    	{
					    		xa = xa - 1 - playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Down"))
					    	{
					    		xa = xa + 1 + playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}						    
						}
					}
				    if (firstKey.equals("Up")) 
				    {
				    	if (rightOrLeft.equals("Right") && upOrDown.equals("Down"))
				    	{
					    	xa = xa - 1 - playerSpeed;
							ya = ya - 1 - playerSpeed;
							playerMob.updatePlayer(false, false, false, true);
				    	}
				    	if (rightOrLeft.equals("Left") && upOrDown.equals("Up"))
				    	{
				    		xa = xa + 1 + playerSpeed;
				    		ya = ya + 1 + playerSpeed;
							playerMob.updatePlayer(false, false, true, false);
				    	}
				    	if (rightOrLeft.equals("Right") && upOrDown.equals("Up"))
				    	{
				    		xa = xa - 1 - playerSpeed;
				    		ya = ya + 1 + playerSpeed;
							playerMob.updatePlayer(false, false, true, false);
				    	}
				    	if (rightOrLeft.equals("Left") && upOrDown.equals("Down"))
				    	{
				    		xa = xa + 1 + playerSpeed;
							ya = ya - 1 - playerSpeed;
							playerMob.updatePlayer(false, false, false, true);
				    	}
				    }
				}
				if(!keyLeft && keyRight && keyUp && keyDown) {
					if (firstKey.equals("Right"))
					{
						if (secondKey.equals("Up"))
						{
							xa = xa - 1 - playerSpeed;
							ya = ya - 1 - playerSpeed;
							playerMob.updatePlayer(false, false, false, true);
							thirdKey = "Down";
						}
						if (secondKey.equals("Down"))
						{
							xa = xa - 1 - playerSpeed;
							ya = ya + 1 + playerSpeed;
							playerMob.updatePlayer(false, false, true, false);
							thirdKey = "Up";
						}
						if (secondKey.equals("Left"))
						{						
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Down"))
					    	{
						    	xa = xa - 1 - playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Up"))
					    	{
					    		xa = xa + 1 + playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Up"))
					    	{
					    		xa = xa - 1 - playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Down"))
					    	{
					    		xa = xa + 1 + playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}						    
						}
	
					}
				    if (firstKey.equals("Up"))
					{
				    	if (secondKey.equals("Right"))
						{
				    		xa = xa - 1 - playerSpeed;
				    		ya = ya - 1 - playerSpeed;
							playerMob.updatePlayer(false, false, false, true);
							thirdKey = "Down";
						}
						if (secondKey.equals("Down"))
						{
							xa = xa - 1 - playerSpeed;
							ya = ya - 1 - playerSpeed;
							playerMob.updatePlayer(false, false, false, true);
							thirdKey = "Right";
						}
						if (secondKey.equals("Left"))
						{						
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Down"))
					    	{
						    	xa = xa - 1 - playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Up"))
					    	{
					    		xa = xa + 1 + playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Up"))
					    	{
					    		xa = xa - 1 - playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Down"))
					    	{
					    		xa = xa + 1 + playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}						    
						}
					}
				    
				    if (firstKey.equals("Down"))
					{
				    	if (secondKey.equals("Right"))
						{
				    		xa = xa - 1 - playerSpeed;
				    		ya = ya + 1 + playerSpeed;
							playerMob.updatePlayer(false, false, true, false);
							thirdKey = "Up";
						}
						if (secondKey.equals("Up"))
						{
							xa = xa - 1 - playerSpeed;
							ya = ya + 1 + playerSpeed;
							playerMob.updatePlayer(false, false, true, false);
							thirdKey = "Right";
						}
						if (secondKey.equals("Left"))
						{						
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Down"))
					    	{
						    	xa = xa - 1 - playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Up"))
					    	{
					    		xa = xa + 1 + playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Up"))
					    	{
					    		xa = xa - 1 - playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Down"))
					    	{
					    		xa = xa + 1 + playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}						    
						}
					}
				    if (firstKey.equals("Left")) 
				    {
				    	if (rightOrLeft.equals("Right") && upOrDown.equals("Down"))
				    	{
					    	xa = xa - 1 - playerSpeed;
							ya = ya - 1 - playerSpeed;
							playerMob.updatePlayer(false, false, false, true);
				    	}
				    	if (rightOrLeft.equals("Left") && upOrDown.equals("Up"))
				    	{
				    		xa = xa + 1 + playerSpeed;
				    		ya = ya + 1 + playerSpeed;
							playerMob.updatePlayer(false, false, true, false);
				    	}
				    	if (rightOrLeft.equals("Right") && upOrDown.equals("Up"))
				    	{
				    		xa = xa - 1 - playerSpeed;
				    		ya = ya + 1 + playerSpeed;
							playerMob.updatePlayer(false, false, true, false);
				    	}
				    	if (rightOrLeft.equals("Left") && upOrDown.equals("Down"))
				    	{
				    		xa = xa + 1 + playerSpeed;
							ya = ya - 1 - playerSpeed;
							playerMob.updatePlayer(false, false, false, true);
				    	}
				    }
				}
				if(keyLeft && !keyRight && keyUp && keyDown) {
					if (firstKey.equals("Left"))
					{
						if (secondKey.equals("Up"))
						{
							xa = xa + 1 + playerSpeed;
							ya = ya - 1 - playerSpeed;
							playerMob.updatePlayer(false, false, false, true);
							thirdKey = "Down";
						}
						if (secondKey.equals("Down"))
						{
							xa = xa + 1 + playerSpeed;
							ya = ya + 1 + playerSpeed;
							playerMob.updatePlayer(false, false, true, false);
							thirdKey = "Up";
						}
						if (secondKey.equals("Right"))
						{						
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Down"))
					    	{
						    	xa = xa - 1 - playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Up"))
					    	{
					    		xa = xa + 1 + playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Up"))
					    	{
					    		xa = xa - 1 - playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Down"))
					    	{
					    		xa = xa + 1 + playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}						    
						}
					}
				    if (firstKey.equals("Up"))
					{
				    	if (secondKey.equals("Left"))
						{
				    		xa = xa + 1 + playerSpeed;
				    		ya = ya - 1 - playerSpeed;
							playerMob.updatePlayer(false, false, false, true);
							thirdKey = "Down";
						}
						if (secondKey.equals("Down"))
						{
							xa = xa + 1 + playerSpeed;
							ya = ya - 1 - playerSpeed;
							playerMob.updatePlayer(false, false, false, true);
							thirdKey = "Left";
						}
						if (secondKey.equals("Right"))
						{						
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Down"))
					    	{
						    	xa = xa - 1 - playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Up"))
					    	{
					    		xa = xa + 1 + playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Up"))
					    	{
					    		xa = xa - 1 - playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Down"))
					    	{
					    		xa = xa + 1 + playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}						    
						}
					}
				    
				    if (firstKey.equals("Down"))
					{
				    	if (secondKey.equals("Left"))
						{
				    		xa = xa + 1 + playerSpeed;
				    		ya = ya + 1 + playerSpeed;
							playerMob.updatePlayer(false, false, true, false);
							thirdKey = "Up";
						}
						if (secondKey.equals("Up"))
						{
							xa = xa + 1 + playerSpeed;
							ya = ya + 1 + playerSpeed;
							playerMob.updatePlayer(false, false, true, false);
							thirdKey = "Left";
						}
						if (secondKey.equals("Right"))
						{						
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Down"))
					    	{
						    	xa = xa - 1 - playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Up"))
					    	{
					    		xa = xa + 1 + playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Right") && upOrDown.equals("Up"))
					    	{
					    		xa = xa - 1 - playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
					    	}
					    	if (rightOrLeft.equals("Left") && upOrDown.equals("Down"))
					    	{
					    		xa = xa + 1 + playerSpeed;
								ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
					    	}						    
						}
					}
				    if (firstKey.equals("Right")) 
				    {
				    	if (rightOrLeft.equals("Right") && upOrDown.equals("Down"))
				    	{
					    	xa = xa - 1 - playerSpeed;
							ya = ya - 1 - playerSpeed;
							playerMob.updatePlayer(false, false, false, true);
				    	}
				    	if (rightOrLeft.equals("Left") && upOrDown.equals("Up"))
				    	{
				    		xa = xa + 1 + playerSpeed;
				    		ya = ya + 1 + playerSpeed;
							playerMob.updatePlayer(false, false, true, false);
				    	}
				    	if (rightOrLeft.equals("Right") && upOrDown.equals("Up"))
				    	{
				    		xa = xa - 1 - playerSpeed;
				    		ya = ya + 1 + playerSpeed;
							playerMob.updatePlayer(false, false, true, false);
				    	}
				    	if (rightOrLeft.equals("Left") && upOrDown.equals("Down"))
				    	{
				    		xa = xa + 1 + playerSpeed;
							ya = ya - 1 - playerSpeed;
							playerMob.updatePlayer(false, false, false, true);
				    	}
				    }
				}
				
				// Solves what happens if all four keys are pressed at the same time
				// last two opposites will overwrite previous two (will always result in diagonal movement)
				if(keyLeft && keyRight && keyUp && keyDown &&
						!firstKey.equals("") && !secondKey.equals("") && !thirdKey.equals(""))
				{
					if (firstKey.equals("Left"))
					{
						if (secondKey.equals("Right"))
						{
							if (thirdKey.equals("Up"))
							{
								xa = xa - 1 - playerSpeed;
					    		ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
								rightOrLeft = "Right";
								upOrDown = "Down";
							}
							if (thirdKey.equals("Down"))
							{
								xa = xa - 1 - playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
								rightOrLeft = "Right";
								upOrDown = "Up";
	
							}
						}
						if (secondKey.equals("Up"))
						{
							if (thirdKey.equals("Right"))
							{
								xa = xa - 1 - playerSpeed;
					    		ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
								rightOrLeft = "Right";
								upOrDown = "Down";
							}
							if (thirdKey.equals("Down"))
							{
								xa = xa - 1 - playerSpeed;
					    		ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
								rightOrLeft = "Right";
								upOrDown = "Down";
							}
						}
						if (secondKey.equals("Down"))
						{
							if (thirdKey.equals("Right"))
							{
								xa = xa - 1 - playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
								rightOrLeft = "Right";
								upOrDown = "Up";
							}
							if (thirdKey.equals("Up"))
							{
								xa = xa - 1 - playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
								rightOrLeft = "Right";
								upOrDown = "Up";
							}
						}
					}
					if (firstKey.equals("Right"))
					{
						if (secondKey.equals("Left"))
						{
							if (thirdKey.equals("Up"))
							{
								xa = xa + 1 + playerSpeed;
					    		ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
								rightOrLeft = "Left";
								upOrDown = "Down";
							}
							if (thirdKey.equals("Down"))
							{
								xa = xa + 1 + playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
								rightOrLeft = "Left";
								upOrDown = "Up";
							}
						}
						if (secondKey.equals("Up"))
						{
							if (thirdKey.equals("Left"))
							{
								xa = xa + 1 + playerSpeed;
					    		ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
								rightOrLeft = "Left";
								upOrDown = "Down";
							}
							if (thirdKey.equals("Down"))
							{
								xa = xa + 1 + playerSpeed;
					    		ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
								rightOrLeft = "Left";
								upOrDown = "Down";
							}
						}
						if (secondKey.equals("Down"))
						{
							if (thirdKey.equals("Left"))
							{
								xa = xa + 1 + playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
								rightOrLeft = "Left";
								upOrDown = "Up";
							}
							if (thirdKey.equals("Up"))
							{
								xa = xa + 1 + playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
								rightOrLeft = "Left";
								upOrDown = "Up";
							}
						}
					}
					if (firstKey.equals("Up"))
					{
						if (secondKey.equals("Left"))
						{
							if (thirdKey.equals("Down"))
							{
								xa = xa - 1 - playerSpeed;
					    		ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
								rightOrLeft = "Right";
								upOrDown = "Down";
							}
							if (thirdKey.equals("Right"))
							{
								xa = xa - 1 - playerSpeed;
					    		ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
								rightOrLeft = "Right";
								upOrDown = "Down";
							}
						}
						if (secondKey.equals("Right"))
						{
							if (thirdKey.equals("Left"))
							{
								xa = xa + 1 + playerSpeed;
					    		ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
								rightOrLeft = "Left";
								upOrDown = "Down";
							}
							if (thirdKey.equals("Down"))
							{
								xa = xa + 1 + playerSpeed;
					    		ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
								rightOrLeft = "Left";
								upOrDown = "Down";
							}
						}
						if (secondKey.equals("Down"))
						{
							if (thirdKey.equals("Left"))
							{
								xa = xa - 1 - playerSpeed;
					    		ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
								rightOrLeft = "Right";
								upOrDown = "Down";
							}
							if (thirdKey.equals("Right"))
							{
								xa = xa + 1 + playerSpeed;
					    		ya = ya - 1 - playerSpeed;
								playerMob.updatePlayer(false, false, false, true);
								rightOrLeft = "Left";
								upOrDown = "Down";
							}
						}
					}
					if (firstKey.equals("Down"))
					{
						if (secondKey.equals("Left"))
						{
							if (thirdKey.equals("Up"))
							{
								xa = xa - 1 - playerSpeed;
					    		ya = ya + 1 + playerSpeed;
								playerMob.updatePlayer(false, false, true, false);
								rightOrLeft = "Right";
								upOrDown = "Up";
							}
							if (thirdKey.equals("Right"))
							{
								xa = xa - 1 - playerSpeed;
					    		ya = ya + 1 + playerSpeed;
					    		playerMob.updatePlayer(false, false, true, false);
					    		rightOrLeft = "Right";
								upOrDown = "Up";
							}
						}
						if (secondKey.equals("Right"))
						{
							if (thirdKey.equals("Left"))
							{
								xa = xa + 1 + playerSpeed;
					    		ya = ya + 1 + playerSpeed;
					    		playerMob.updatePlayer(false, false, true, false);
					    		rightOrLeft = "Left";
								upOrDown = "Up";
							}
							if (thirdKey.equals("Up"))
							{
								xa = xa + 1 + playerSpeed;
					    		ya = ya + 1 + playerSpeed;
					    		playerMob.updatePlayer(false, false, true, false);
					    		rightOrLeft = "Left";
								upOrDown = "Up";
							}
						}
						if (secondKey.equals("Up"))
						{
							if (thirdKey.equals("Left"))
							{
								xa = xa - 1 - playerSpeed;
					    		ya = ya + 1 + playerSpeed;
					    		playerMob.updatePlayer(false, false, true, false);
					    		rightOrLeft = "Right";
								upOrDown = "Up";
							}
							if (thirdKey.equals("Right"))
							{
								xa = xa + 1 + playerSpeed;
					    		ya = ya + 1 + playerSpeed;
					    		playerMob.updatePlayer(false, false, true, false);
					    		rightOrLeft = "Left";
								upOrDown = "Up";
							}
						}
					}
				}
				else if (keyLeft && keyRight && keyUp && keyDown &&
					(firstKey.equals("") || secondKey.equals("") || thirdKey.equals("")))
				{
					firstKey = "Left";
					secondKey = "Right";
					thirdKey = "Up";
					xa = xa - 1 - playerSpeed;
		    		ya = ya - 1 - playerSpeed;
					playerMob.updatePlayer(false, false, false, true);
					rightOrLeft = "Right";
					upOrDown = "Down";
				}
				
				//No keys are pressed
				if(!keyLeft && !keyRight && !keyUp && !keyDown) {
					playerMob.updatePlayer(keyLeft, keyRight, keyUp, keyDown);
				}
				
				movePlayer();
				// End Movement
			}
			
				
		}//end in game choices
		
		/*****************************************
		 * Special actions for the Title Menu
		 *****************************************/
		if(state == STATE.TITLE && inputWait < 0) {
			//For when no initial choice has been made
			if(option == OPTION.NONE)
			{
				//S or down arrow(Change selection)
				if(keyDown && titleLocation < 1) {
					titleX = 520;
					titleY = 510;
					titleLocation++;
					inputWait = 5;
				}
				//W or up arrow(Change selection
				if(keyUp && titleLocation > 0) {
					titleX = 520;
					titleY = 416;
					titleLocation--;
					inputWait = 5;
				}
				//Enter key(Make a choice)
				if(keyEnter) {
					if(titleLocation == 0 && !title.isFull()) // checks to make sure there is a save slot left for a new game (3 max)
					{
						try{
							JavaAudioPlaySoundExample("/sounds/Blip_Select.wav");}
							catch (Exception ex) {}
						
						renderTutorial = true; //On a new game this will cause the tutorial screen to be rendered.
						option = OPTION.NEWGAME;
						titleLocation = 0;
						inputWait = 10;
						keyEnter = false;
						titleX2 = 1000; // hide arrow far off screen forever away
						titleY2 = 1000; // bye
					}
					if(titleLocation == 1 && !title.isEmpty()) // checks to make sure there is a game to load
					{
						try{
							JavaAudioPlaySoundExample("/sounds/Blip_Select.wav");}
							catch (Exception ex) {}
						
						option = OPTION.LOADGAME;
						titleLocation = 0;
						inputWait = 10;
						keyEnter = false;
						
						titleX2 = 520; // sets location of arrow to file
						titleY2 = 194; // y value
					}
					inputWait = 10;
				}
			}//end option none
			
			if(option == OPTION.NEWGAME) //NOTE: Compare with loadGame() or option == OPTION.LOADGAME to see differences w/collision or camera....
			{
				//Backspace(Exit choice)
				
				System.out.println("New Game Option");
				if(keyBack && !title.isGetName() && title.getFileName().length() == 0)
				{
					titleLocation = 0;
					inputWait = 5;
					titleX2 = 340;
					titleY2 = 310;
					option = OPTION.NONE;
				}
				
				//The following is for when a new file needs to be created - Typesetting
				title.setFileName(currentChar);
				currentChar = '\0'; //null
				//Back space(Delete last character)
				if(keyBack) {
					title.deleteChar();
					inputWait = 5;
				}
				//Back space(exit name entry if name has no characters)
				if(keyBack && title.getFileName().length() == 0) {
					title.setGetName(false);
					titleX2 -= 40;
					inputWait = 5;
				}
				//Enter key(Write the file using the currently typed name and save it)
				if(keyEnter && title.getFileName().length() > 0) {
					try{
						JavaAudioPlaySoundExample("/sounds/Blip_Select.wav");}
						catch (Exception ex) {}
					
					save.newFile(title.getFileName());
					title.setGetName(false);
					currentFile = title.getFileName();
					
					
					state = STATE.GAME;
					option = OPTION.NONE;
					inputWait = 10;
					setGameState(STATE.GAME);
				}
					
					
				//end get name
			}
			
			if (option == OPTION.LOADGAME)
			{
				//Backspace(Exit choice)
				if(keyBack && !title.isGetName())
				{
					if (title.getTitleSymbol().equals("arrow"))
					{
						titleLocation = 1;
						inputWait = 5;
						titleX2 = 340;
						titleY2 = 310;
						option = OPTION.NONE;
					}
					else if (title.getTitleSymbol().equals("bomb"))
					{
						title.setTitleSymbol("arrow");
						inputWait = 10;
					}
				}
				
				//S or down arrow(Change selection)
				if(keyDown && !title.isGetName() && title.checkFilesNumber(titleLocation, "Down")) {
					titleLocation++;
				//	if (titleLocation != 3) // checks to see if arrow is about to be on erase game (if it is, it increments less)
					if (titleLocation != title.files().length)
					{
						titleY2 += 120;
					}
					else
					{
						titleY2 += 110;
					}
					inputWait = 7;
				}
				//W or up arrow(Change selection)
				if(keyUp && !title.isGetName() && title.checkFilesNumber(titleLocation, "Up")) {
					titleLocation--;
					if (titleLocation != title.files().length - 1) // checks to see if arrow is on erase game or not (if it is, it deccrements less)
					{
						titleY2 -= 120;
					}
					else
					{	
						titleY2 -= 110;
					}
						inputWait = 7;
				}
				
				//Enter key(Make a choice)
				// If one of the three files is selected (not the erase file)
				if(keyEnter && titleLocation != title.files().length) 
				{
					try{
						JavaAudioPlaySoundExample("/sounds/Blip_Select.wav");}
						catch (Exception ex) {}
					
					if (title.getTitleSymbol().equals("arrow")) // if not erasing a file, load the file and the game
					{
				//	Load the currently selected file
						currentFile = title.enter();
						if(currentFile != "") { //File is empty
							//loadGame(); //NOTE: If this line is nonexistant then our collision error/camera movement error doesn't occur! The errors are somewhere in this method!
							inputWait = 10;
							option = OPTION.NONE;
							state = STATE.GAME;
							setGameState(STATE.GAME);
						}
					}
					else if(title.getTitleSymbol().equals("bomb")) // DELETE THE FILE
					{
						title.deleteFile(titleLocation); // delete
						inputWait = 10; // wait
						if (title.files().length == 0) // if all files are deleted, go back to title screen
						{

							titleLocation = 1;
							inputWait = 7;
							titleX2 = 340;
							titleY2 = 310;
							option = OPTION.NONE;
							title.setTitleSymbol("arrow");
						}
					}
				}				
				 // if Erase file is selected
				else if(keyEnter && titleLocation == title.files().length)
				{
					if (title.getTitleSymbol().equals("arrow"))
					{
						title.setTitleSymbol("bomb");
					}
					else if(title.getTitleSymbol().equals("bomb"))
					{
						title.setTitleSymbol("arrow");
					}
					inputWait = 11;
				}
	
			}
			
		}//end title state
		
		
		/******************************************
		 * Special actions for In Game Menu
		 ******************************************/
		if(state == STATE.INGAMEMENU && inputWait < 0) {
			//I(Close inventory)
			if(keyInventory) {
				state = STATE.GAME;
				option = OPTION.NONE;
				inLocation = 0;
				inY = 90;
				inputWait = 8;
			}
			//No option is chosen yet
			if(option == OPTION.NONE){ 
				if(wait == 0) wasSaving = false;
				//W or up arrow(Move selection)
				if(keyUp) {
					if(inLocation > 0) {
						inY -= 108;
						inLocation--;
						inputWait = 10;
					}
				}
				//S or down arrow(move selection)
				if(keyDown) {
					if(inLocation < 4) {
						inY += 108;
						inLocation++;
						inputWait = 10;
					}
				}
				//Enter key(Make a choice)
				if(keyEnter) {
					if(inLocation == 0){
						option = OPTION.ITEMS;
						inputWait = 5;
					}
					if(inLocation == 1){
						option = OPTION.EQUIPMENT;
						inputWait = 5;
					}
					if(inLocation == 2){
						option = OPTION.MAGIC;
						inputWait = 5;
					}
					if(inLocation == 3){
						option = OPTION.STATUS;
						inputWait = 5;
					}
					if(inLocation == 4){
						option = OPTION.SAVE;
						inputWait = 20;
					}
					keyEnter = false;
				}
			}
			
			//Set actions for specific choices in the menu
			//Items
			if(option == OPTION.ITEMS) {
				//W or up arrow(move selection)
				if(keyUp){
					if(sectionLoc == 0) inMenu.loadOldItems();
					if(sectionLoc - 1 != -1) sectionLoc--;
					inputWait = 8;
				}
				//S or down arrow(move selection)
				if(keyDown) {
					if(sectionLoc == 3) inMenu.loadNextItems();
					if(inMenu.getTotalItems() > sectionLoc + 1 && sectionLoc < 3) sectionLoc++;
					inputWait = 8;
				}
				//Enter key(Make a choice)
				if(keyEnter){
					if(confirmUse) {
						inMenu.useItem(); //then use item
						confirmUse = false;
						keyEnter = false;
					}
					if(inMenu.checkCount() > 0 && keyEnter) confirmUse = true;
					inputWait = 10;
				}
				//Back space(Go back on your last choice)
				if(keyBack) confirmUse = false;
			}
			
			//Equipment
			if(option == OPTION.EQUIPMENT) {
				//W or up arrow(move selection)
				if(keyUp){
					if(sectionLoc == 0) inMenu.loadOldItems();
					if(sectionLoc - 1 != -1) sectionLoc--;
					inputWait = 8;
				}
				//S or down arrow(move selection)
				if(keyDown) {
					if(sectionLoc == 3) inMenu.loadNextEquipment();
					if(inMenu.getTotalEquipment() > sectionLoc + 1 && sectionLoc < 3) sectionLoc++;
					inputWait = 8;
				}
			}
			
			//Saving
			if(option == OPTION.SAVE){
				//Key enter(Save the file)
				if(keyEnter){
					save.saveState(currentFile, data());
					inputWait = 20;
					wait = 200;
					waitOn = true;
					wasSaving = true;
					option = OPTION.NONE;
				}
			}
			
			//Backspace(if a choice has been made, this backs out of it)
			if(keyBack && option != OPTION.NONE) {
				option = OPTION.NONE;
				inMenu.setItemLoc(0);
				sectionLoc = 0;
				inputWait = 8;
				keyBack = false;
			}
			//Backspace(if a choice has not been made, this closes the inventory)
			if(keyBack && option == OPTION.NONE) {
				state = STATE.GAME;
				option = OPTION.NONE;
				inLocation = 0;
				sectionLoc = 0;
				inY = 90;
				inputWait = 8;
			}
		}
		inputWait--;
		dialogueWait--;
	}
	
	/**
	 * Inherited method
	 * @param keyCode
	 * 
	 * Set keys for a new game action here using a switch statement
	 * dont forget gameKeyUp
	 */
	void gameKeyDown(int keyCode) {
		switch(keyCode) {
	        case KeyEvent.VK_LEFT:
	            keyLeft = true;
	            break;
	        case KeyEvent.VK_A:
	        	keyLeft = true;
        		break;
	        case KeyEvent.VK_RIGHT:
	            keyRight = true;
	            break;
	        case KeyEvent.VK_D:
	        	keyRight = true;
	        	break;
	        case KeyEvent.VK_UP:
	            keyUp = true;
	            break;
	        case KeyEvent.VK_W:
	        	keyUp = true;
	        	break;
	        case KeyEvent.VK_DOWN:
	            keyDown = true;
	            break;
	        case KeyEvent.VK_S:
	        	keyDown = true;
	        	break;
	        case KeyEvent.VK_I:
	        	keyInventory = true;
	        	break;
	        	//old attacking start with z as input
	       // case KeyEvent.VK_Z:
	        //	keyAction = true;
	        	//break;
	        	
	        	//new attacking input as space bar
	        case KeyEvent.VK_SPACE:
	        	keyAction = true;
	        	break;
	        case KeyEvent.VK_ENTER:
	        	keyEnter = true;
	        	break;
	        case KeyEvent.VK_BACK_SPACE:
	        	keyBack = true;
	        	break;
	        	
	        	//old useless function that played a noise and cause character to stop moving. 
	        //case KeyEvent.VK_SPACE:
	        	//keySpace = true;
	        //	break;
	        case KeyEvent.VK_Y:
	        	keyChange = true;
	        	break;
	        case KeyEvent.VK_SHIFT:
	        	keyShift = true;
	        	break;
	        case KeyEvent.VK_X:
	        	keyCancel = true;
	        	break;
	        case KeyEvent.VK_C:
	        	keyChat = true;
	        	break;
	        case KeyEvent.VK_ESCAPE:
	        	keyEnter = true;
	        	break;
        }
	}

	/**
	 * Inherited method
	 * @param keyCode
	 * 
	 * Set keys for a new game action here using a switch statement
	 * Dont forget gameKeyDown
	 */
	void gameKeyUp(int keyCode) {
		switch(keyCode) {
        case KeyEvent.VK_LEFT:
            keyLeft = false;
            break;
        case KeyEvent.VK_A:
        	keyLeft = false;
        	break;
        case KeyEvent.VK_RIGHT:
            keyRight = false;
            break;
        case KeyEvent.VK_D:
        	keyRight = false;
        	break;
        case KeyEvent.VK_UP:
            keyUp = false;
            break;
        case KeyEvent.VK_W:
        	keyUp = false;
        	break;
        case KeyEvent.VK_DOWN:
            keyDown = false;
            break;
        case KeyEvent.VK_S:
        	keyDown = false;
        	break;
        case KeyEvent.VK_I:
	    	keyInventory = false;
	    	break;
	    	
	    	
	    	//old action for attacking with Z as input
	  //  case KeyEvent.VK_Z:
	   // 	keyAction = false;
	    //	break;
	    case KeyEvent.VK_ENTER:
	    	keyEnter = false;
	    	break;
	    case KeyEvent.VK_ESCAPE:
        	keyEnter = false;
        	break;
	    case KeyEvent.VK_BACK_SPACE:
	    	keyBack = false;
	    	break;
	    	//An event that used to cause the character input to freeze and 
	    	//played a noise. 
	   // case KeyEvent.VK_SPACE:
	    //	keySpace = false;
	    //	break;
	    	//end the action for attacking with space bar as input
	    case KeyEvent.VK_SPACE:
	    	keyAction = false;
	    	break;
	    	
	    case KeyEvent.VK_Y:
	    	keyChange = false;
	    	break;
	    case KeyEvent.VK_SHIFT:
	    	keyShift = false;
	    	break;
	    case KeyEvent.VK_X:
        	keyCancel = false;
        	break;
	    case KeyEvent.VK_C:
	    	keyChat = false;
	    	break;
		}
	}

	/**
	 * Inherited method
	 * Currently unused
	 */
	void gameMouseDown() {	
	}

	/**
	 * Inherited method
	 * Currently if the game is running and the sword is out, the player attacks with it
	 */
	void gameMouseUp() { 
		if(getMouseButtons(1) == true && playerMob.isTakenOut() && inputWait < 0) {
			playerMob.attack();
			inputWait = 24;
		}
		inputWait--;
	}

	/**
	 * Inherited Method
	 * Currently unused
	 */
	void gameMouseMove() {
	}
	 
	 //From the title screen, load a game file by having the super class get the data,
	 // then handling where the pieces of the data will be assigned here.
	/**
	 * Inherited Method
	 * 
	 * The title screen calls this method when a currently existing file is chosen
	 * Add new saved game details here as well as in the 'Data.java' class
	 * 
	 * Currently only the player x and y location and the current map is saved
	 */
	 void loadGame() { //NOTE: This is where camera tracking and collisions get messed up (rendering with black boxes/collisions).... Best to compare with a new game(wherever that is)
		 if(currentFile != "") 
		 {
			 System.out.println("Loading...");
			 loadData(currentFile);
			 tiles().clear();
			 sprites().clear();
			 for(int i = 0; i < mapBase.maps.length; i++)
			 {
				 if(mapBase.getMap(i) == null) continue;
				 if(data().getMapName() == mapBase.getMap(i).mapName()) currentMap = mapBase.getMap(i);
			//	 if(data().getOverlayName() == mapBase.getMap(i).mapName()) currentOverlay = mapBase.getMap(i);
			 }
			 mapX = data().getMapX();
			 mapY = data().getMapY();
			 health = data().getHealth();
			 frisbee = data().getFrisbee();
			 gfrisbee = data().getGFrisbee();
			 files = data().getFiles();
			 snack = data().getSnack();
			 keyboard = data().getKeyboard();
			 playerX = data().getPlayerX();
			 playerY = data().getPlayerY();
			// sprites().add(playerMob);
			 for (int i = 0; i < currentMap.getMobs().size(); i++)
			 {
				 sprites().add(currentMap.getMobs().get(i));
			 }
			 
			 for(int i = 0; i < currentMap.getWidth() * currentMap.getHeight(); i++)
			 {
					addTile(currentMap.accessTile(i));
				//	addTile(currentOverlay.accessTile(i));
					if(currentMap.accessTile(i).hasMob()) sprites().add(currentMap.accessTile(i).mob());
				//	if(currentOverlay.accessTile(i).hasMob()) sprites().add(currentOverlay.accessTile(i).mob());
			}//end for
			System.out.println("Load Successful");
		 } //end file is not empty check
	 } //end load method
	 
	 Clip newClip;
	 /**
	  * A simple Java sound file example (i.e., Java code to play a sound file).
	  * AudioStream and AudioPlayer code comes from a javaworld.com example.
	  * @author alvin alexander, devdaily.com.
	 * @throws IOException 
	 * @throws LineUnavailableException 
	 * @throws UnsupportedAudioFileException 
	 * @throws InterruptedException 
	  */
	 public void JavaAudioPlaySoundExample(String file) throws IOException, LineUnavailableException, UnsupportedAudioFileException, InterruptedException
	 {   
		 
		 try 
		 {
				ClassLoader classloader = Thread.currentThread().getContextClassLoader();
				InputStream is = getClass().getResourceAsStream(file);
		        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(is);
		        Clip clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.start();
		 } 
		 catch(Exception ex) 
		 {
		        System.out.println("Error with playing sound.");
		        ex.printStackTrace();
		 }
		  
	 }
	 public void Song(String file) throws IOException, LineUnavailableException, UnsupportedAudioFileException, InterruptedException
	 {   
		 
		 try 
		 {
				ClassLoader classloader = Thread.currentThread().getContextClassLoader();
				InputStream is = getClass().getResourceAsStream(file);
		        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(is);
		        newClip = AudioSystem.getClip();
		        newClip.open(audioInputStream);
		        newClip.loop(newClip.LOOP_CONTINUOUSLY);
		 } 
		 catch(Exception ex) 
		 {
		        System.out.println("Error with playing sound.");
		        ex.printStackTrace();
		 }
		  
	 }
	 

} //end class