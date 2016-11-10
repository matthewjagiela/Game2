/****************************************************************************************************
 * @author Travis R. Dewitt
 * @version 1.0
 * Date: July 5, 2015
 * 
 * Title: Map Database
 * Description: A data handling class used for large projects. This class contains all of the spritesheets,
 * tiles, events, items, mobs and map creations since they all interlock together.
 * 
 * This work is licensed under a Attribution-NonCommercial 4.0 International
 * CC BY-NC-ND license. http://creativecommons.org/licenses/by-nc/4.0/
 ****************************************************************************************************/
//Package
package axohEngine2.project;

//Imports
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;

import axohEngine2.entities.Mob;
import axohEngine2.entities.RI;
import axohEngine2.entities.SpriteSheet;
import axohEngine2.Judgement;
import axohEngine2.entities.Attack;
import axohEngine2.map.Event;
import axohEngine2.map.Map;
import axohEngine2.map.Tile;
import axohEngine2.map.TileEventType;

public class MapDatabase
{
	
	//SpriteSheets
	SpriteSheet misc;
	SpriteSheet buildings;
	SpriteSheet environment32;
	SpriteSheet extras2;
	SpriteSheet mainCharacter;
	SpriteSheet Chests;
	SpriteSheet professors;
	SpriteSheet evil;
	
	//Maps
	Map city;
	Map cityO;
	Map houses;
	Map housesO;
	
	//Tiles - Names are defined in the constructor for better identification
//	Tile d;
//	Tile g;
//	Tile f;
//	Tile b;
//	Tile r;
//	Tile e;
//	Tile ro;
//	Tile h;
//	Tile hf;
//	Tile c;
//	Tile k;
	String b = "bricks";
	String bT = "bricksTop";
	String r = "walkway";
	String c = "chest";
	String ro = "rock";
	String g = "grass";
	String t1 = "tree1";
	String t2 = "tree2";
	String t3 = "tree3";
	String t4 = "tree4";
	String f = "flower";
	String bg = "brickGold";
	String co = "chestOpen";
	String gd = "grassDarker";
	String e = "empty";
	String h1 = "house1";
	String h2 = "house2";
	String h3 = "house3";
	String h4 = "house4";
	String h5 = "house5";
	String h6 = "house6";
	String h7 = "house7";
	String h8 = "house8";
	
	
	
	//Events
	Event warp1;
	Event warp2;
	Event getPotion;
	Event getMpotion;
	
	//Items
	Items potion;
	Items mpotion;
	
	//NPC's and Monsters
	Mob npc;
	
	//Array of maps
	public Map[] maps;
	
	
	ArrayList<Mob> mobs = new ArrayList<Mob>();
	
	int width;
	int height;
	
	/****************************************************************
	 * Constructor
	 * Instantiate all variables for the game
	 * 
	 * @param frame - JFrame Window for the map to be displayed on
	 * @param g2d - Graphics2D object needed to display images
	 * @param scale - Number to be multiplied by each image for correct on screen display
	 *******************************************************************/
	public MapDatabase(JFrame frame, Graphics2D g2d, int scale) {
		//Currently a maximum of 200 maps possible(Can be changed if needed)
		maps = new Map[200];
		
		//Set up spriteSheets
		misc = new SpriteSheet("/textures/environments/environments1.png", 16, 16, 16, scale);
		buildings = new SpriteSheet("/textures/environments/4x4buildings.png", 4, 4, 64, scale);
		environment32 = new SpriteSheet("/textures/environments/32SizeEnvironment.png", 8, 8, 32,scale);
		extras2 = new SpriteSheet("/textures/extras/extras2.png", 16, 16, 16, scale);
		mainCharacter = new SpriteSheet("/textures/characters/mainCharacter.png", 8, 8, 32, scale);
		Chests = new SpriteSheet("/textures/extras/Chests.png", 16, 16, 16, scale);
		professors = new SpriteSheet("/textures/characters/Duncan.png", 8, 8, 32, scale);
		evil = new SpriteSheet("/textures/characters/evilMainCharacter.png", 8, 8, 32, scale);
		
		//Set the tile blueprints in an array for the Map
		String[] cityTilesStructure =  {g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g, 
									    t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4,
									    t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  bT, g,  g,  g,  g,  g,  g,  g,  g,  g,  g, 
									    t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  bT, g,  g,  g,  g,  g,  g,  g,  g,  t3, t4,
									    g,  g,  g,  g,  f,  g,  f,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  f,  g,  f,  g,  g,  g,  bT, g,  g,  g,  g,  g,  g,  g,  g,  g,  g, 
									    t3, t4, g,  g,  g,  f,  g,  g,  g,  g,  g,  g,  g,  g,  g,  bT, b,  b,  b,  b,  b,  b,  g,  g,  f,  g,  g,  g,  g,  bT, g,  g,  g,  g,  g,  g,  g,  g,  t3, t4,
									    g,  g,  g,  g,  f,  g,  f,  g,  g,  g,  g,  g,  g,  g,  g,  b,  r,  r,  r,  r,  r,  r,  g,  f,  g,  f,  g,  g,  g,  bT, g,  g,  g,  g,  g,  g,  g,  g,  g,  g, 
									    t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  r,  r,  r,  r,  r,  r,  r,  g,  g,  g,  g,  g,  g,  g,  bT, g,  g,  g,  g,  g,  g,  g,  g,  t3, t4,
									    g,  g,  g,  g,  g,  g,  g,  g,  g,  f,  g,  f,  g,  g,  g,  r,  r,  r,  r,  r,  r,  bT, g,  g,  g,  g,  g,  g,  g,  bT, g,  g,  g,  g,  g,  g,  g,  g,  g,  g, 
									    t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  f,  g,  g,  g,  g,  b,  bT, r,  r,  r,  r,  bT, g,  g,  g,  g,  g,  g,  g,  b,  b,  b, g,  g,   b,  b,  b,  b, t3, t4,
									    g,  g,  g,  g,  g,  g,  g,  g,  g,  f,  g,  f,  g,  g,  g,  g,  bT, r,  r,  r,  r,  bT, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g, 
									    t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  b,  b,  b,  b,  b,  b,  g,  g,  t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  t3, t4,
									    g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g, 
									    t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  t3, t4, g,  g,  g,  g,  g,  g,  t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  t3, t4,
									    g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g, 
									    t3, t4, t3, t4, g,  g,  t3, t4, g,  g,  g,  g,  g,  g,  t3, t4, t3, t4, g,  g,  g,  t3, t4, t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  t3, t4,
									    g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g, 
									    t3, t4, t3, t4, g,  g,  t3, t4, t3, t4, t3, t4, t3, t4, g,  g,  g,  g,  g,  g,  t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  t3, t4,
									    g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g, 
									    t3, t4, t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  t3, t4,
									    g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g, 
									    t3, t4, t3, t4, t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  t3, t4,
									    g,  g,  g,  g,  g,  g,  t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g, 
									    t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  t3, t4,
									    g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g, 
									    t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  f,  g,  f,  g,  g,  g,  g,  g,  g,  g,  g,  t3, t4,
									    g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  f,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g, 
									    t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  f,  g,  f,  g,  g,  g,  g,  g,  g,  g,  g,  t3, t4,
									    g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g, 
									    t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  t3, t4,
									    g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g, 
									    t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  bT, b,  r,  r,  b,  b,  b,  bT, g,  t3, t4,
									    g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  bT, r,  r,  r,  r,  r,  r,  bT, g,  g,  g, 
									    t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  bT, r,  r,  r,  r,  r,  r,  bT, g,  t3, t4,
									    g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  bT, r,  r,  r,  r,  r,  r,  bT, g,  g,  g, 
									    t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  bT, r,  r,  r,  r,  r,  r,  bT, g,  t3, t4,
									    g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  bT, r,  r,  r,  r,  r,  r,  bT, g,  g,  g, 
									    t3, t4, g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  b,  b,  b,  b,  b,  b,  b,  b,  g,  t3, t4,
									    g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g,  g, 
									    t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4, t3, t4};

		Tile[] cityTiles = new Tile[cityTilesStructure.length];
		TileCreator tileCreator = new TileCreator();
		int width = 40;
		int height = 40;
		
		int coordX = 0;
		int coordY = 0;
		for (int i = 0; i < cityTiles.length; i++)
		{
			cityTiles[i] = tileCreator.create(frame, g2d, scale, cityTilesStructure[i]);
			if(coordX > width - 1)
			{
				coordX = 0;
				coordY++;
			}
			
			cityTiles[i].setMapCoordX(coordX);
			cityTiles[i].setMapCoordY(coordY);
			
			coordX++;
			
		}
		
	    String[] cityOTilesStructure = {t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2,
									    t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e, 
									    t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  t1, t2,
									    e,  e,  e,  e,  e,  h1, h2, h3, h4, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  c,  e,  e,  e,  e,  e,  e, 
									    t1, t2, e,  e,  e,  h5, h6, h7, h8, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  c,  e,  e,  e,  c,  e,  e,  t1, t2,
									    e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e, 
									    t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  ro, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  ro, e,  e,  e,  e,  e,  t1, t2,
									    e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  c,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  ro, e,  e,  e,  e, 
									    t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  t1, t2,
									    e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  c,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e, 
									    t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  t1, t2,
									    e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e, 
									    t1, t2, e,  e,  e,  e,  ro, e,  e,  e,  e,  e,  e,  e,  e,  e,  t1, t2, e,  e,  e,  e,  e,  e,  t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  t1, t2,
									    e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e, 
									    t1, t2, t1, t2, e,  e,  t1, t2, e,  e,  e,  e,  e,  e,  t1, t2, t1, t2, e,  e,  e,  t1, t2, t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  t1, t2,
									    e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e, 
									    t1, t2, t1, t2, e,  e,  t1, t2, t1, t2, t1, t2, t1, t2, e,  e,  e,  e,  e,  e,  t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  t1, t2,
									    e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e, 
									    t1, t2, t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  t1, t2,
									    e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e, 
									    t1, t2, t1, t2, t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  t1, t2,
									    e,  e,  e,  e,  e,  e,  t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e, 
									    t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  t1, t2,
									    e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e, 
									    t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  t1, t2,
									    e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e, 
									    t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  t1, t2,
									    e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e, 
									    t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  t1, t2,
									    e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e, 
									    t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  t1, t2,
									    e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e, 
									    t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  t1, t2,
									    e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  c,  e,  e,  e,  e,  e,  e,  e,  e, 
									    t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  c,  c,  e,  e,  t1, t2,
									    e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e, 
									    t1, t2, e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  t1, t2,
									    e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e, 
									    t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2,
									    e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  e,  t4};
	    
	    
		    Tile[] cityOverlayTiles = new Tile[cityOTilesStructure.length];
			TileCreator tileCreator2 = new TileCreator();
			int width2 = 40;
			int height2 = 40;
			
			int coordX2 = 0;
			int coordY2 = 0;
			for (int i = 0; i < cityTiles.length; i++)
			{
				cityOverlayTiles[i] = tileCreator2.create(frame, g2d, scale, cityOTilesStructure[i]);
				if(coordX2 > width - 1)
				{
					coordX2 = 0;
					coordY2++;
				}
				
				cityOverlayTiles[i].setMapCoordX(coordX2);
				cityOverlayTiles[i].setMapCoordY(coordY2);
				
				coordX2++;
			}
			
			String[] map1Tiles = {"t00", "t01", "t02", "t03", "t04", "t05", "t06", "t07", "t08", "t09", "t10", "t11", "t12", "t13", "t14", "t15",
		              "t16", "t17", "t18", "t19", "t20", "t21", "t22", "t23", "t24", "t25", "t26", "t27", "t28", "t29", "t30", "t31",
		              "t32", "t33", "t34", "t35", "t36", "t37", "t38", "t39", "t40", "t41", "t42", "t43", "t44", "t45", "t46", "t47", 
		              "t48", "t49", "t50", "t51", "t52", "t53", "t54", "t55", "t56", "t57", "t58", "t59", "t60", "t61", "t62", "t63", 
		              "t64", "t65", "t66", "t67", "t68", "t69", "t70", "t71", "t72", "t73", "t74", "t75", "t76", "t77", "t78", "t79"};
			
			
			Tile[] map1 = new Tile[map1Tiles.length];
			TileCreator tileCreator3 = new TileCreator();
			for (int i = 0; i < map1Tiles.length; i++)
			{
				map1[i] = tileCreator3.map1(frame, g2d, 5, map1Tiles[i]);
			}
			
			Tile[] map1Overlay = new Tile[map1Tiles.length];
			TileCreator tileCreator4 = new TileCreator();
			for (int i = 0; i < map1Tiles.length; i++)
			{
				map1Overlay[i] = tileCreator4.map1(frame, g2d, 5, map1Tiles[i]);
			}
			
		//Put the maps together and add them to the array of possible maps
		Rectangle bounds = new Rectangle(8 * 4, 22 * 4, 14 * 4, 9 * 4 + 6);
		Rectangle leftBounds = new Rectangle(11 * 4 + 1, 22 * 4 + 12, 1, 9 * 4 - 12);
		Rectangle rightBounds = new Rectangle(11 * 4 + 35, 22 * 4 + 12, 1, 9 * 4 - 12);
		Rectangle upBounds = new Rectangle(11 * 4 + 3, 22 * 4 + 8, 9 * 4 - 7, 1);
		Rectangle downBounds = new Rectangle(11 * 4 + 3, 22 * 4 + 40, 9 * 4 - 7, 1);
		Rectangle boundsLocation = new Rectangle(0,0,0,0);
		Point currentTile = new Point(12, 7);
		Point startTile = new Point(20, 20);
		Point currentLocation = new Point(0, 0);
		Point startLocation = new Point(32, 32);
		Rectangle actionBounds = new Rectangle(11 * 4 + 3, 22 * 4 + 40, 9 * 4 - 7, 1);
		Rectangle fullBounds = new Rectangle(8 * 4, 1 * 4, 14 * 4, 30 * 4);
		Rectangle fullBoundsLocation = new Rectangle(0, 0, 0, 0);
		Rectangle hurtBox = new Rectangle(8 * 4, 21 * 4 - 20, 14 * 4, 10 * 4 + 20); 
		
		
		Mob FredNPC = new Mob(frame, g2d, evil, 40, TYPE.NPC, "test", 56, bounds, leftBounds, rightBounds,
				upBounds, downBounds, boundsLocation,
				currentTile, startTile, currentLocation, startLocation, actionBounds, fullBounds, fullBoundsLocation, true,
				hurtBox);
		
		FredNPC.setMoveAnim(32, 48, 40, 56, 3, 8);
		
		LinkedList<Rectangle> attackLeftBounds = new LinkedList<Rectangle>();
		attackLeftBounds.add(new Rectangle(0,0,0,0));
		attackLeftBounds.add(new Rectangle(8, 46, 38, 68));
		attackLeftBounds.add(new Rectangle(8, 46, 38, 68));
		
		LinkedList<Rectangle> attackRightBounds = new LinkedList<Rectangle>();
		attackRightBounds.add(new Rectangle(0,0,0,0));
		attackRightBounds.add(new Rectangle(90, 46, 38, 68));
		attackRightBounds.add(new Rectangle(90, 46, 38, 68));
		
		LinkedList<Rectangle> attackUpBounds = new LinkedList<Rectangle>();
		attackUpBounds.add(new Rectangle(0,0,0,0));
		attackUpBounds.add(new Rectangle(32, 40, 58, 18));
		attackUpBounds.add(new Rectangle(32, 40, 58, 18));
		
		LinkedList<Rectangle> attackDownBounds = new LinkedList<Rectangle>();
		attackDownBounds.add(new Rectangle(0,0,0,0));
		attackDownBounds.add(new Rectangle(32, 96, 58, 18));
		attackDownBounds.add(new Rectangle(32, 96, 58, 18));
		
		//FredNPC.setHealth(5);
		FredNPC.setMaxHealth(20);
		FredNPC.setHurtBox(hurtBox);
		FredNPC.addAttack("sword", 1, attackLeftBounds, attackRightBounds, attackUpBounds, attackDownBounds);
		FredNPC.getAttack("sword").addMovingAnim(17, 25, 9, 1, 3, 8);
		FredNPC.getAttack("sword").addAttackAnim(20, 28, 12, 4, 3, 6);
		FredNPC.getAttack("sword").addInOutAnim(16, 24, 8, 0, 1, 10);
		FredNPC.setCurrentAttack("sword"); //Starting attack
		//Tile tile = cityTiles[FredNPC.getCurrentTile().x + FredNPC.getCurrentTile().y+40];
		//Rectangle tileBounds = new Rectangle(tile.getLocation().x, tile.getLocation().y, 64, 64);  
		//FredNPC.getAttack("sword").setAttackBoundsLocation(new Rectangle(tileBounds.x + bounds.x, tileBounds.y + bounds.y, bounds.width, bounds.height));
		//playerMob.getAttack("sword").setAttackBoundsLocation(new Rectangle(startPosX + bounds.x, startPosY + bounds.y, bounds.width, bounds.height));
		FredNPC.stopAnim(); // stops the moving in place right when player loads
		FredNPC.setAnimTo(FredNPC.getStartFrame()); // stops the odd animation loading and sets player to start on its wanted start frame
		FredNPC.setHealthBarLocation(new Rectangle(8 * 4, 1 * 4, 14 * 4, 30 * 4));
		
		FredNPC.setRI(RI.Attack);
		Judgement.playerUpdate = true;
		
		
		mobs.add(FredNPC);
		
		 bounds = new Rectangle(8 * 4, 22 * 4, 14 * 4, 9 * 4 + 6);
		 leftBounds = new Rectangle(11 * 4 + 1, 22 * 4 + 12, 1, 9 * 4 - 12);
		 rightBounds = new Rectangle(11 * 4 + 35, 22 * 4 + 12, 1, 9 * 4 - 12);
		 upBounds = new Rectangle(11 * 4 + 3, 22 * 4 + 8, 9 * 4 - 7, 1);
		 downBounds = new Rectangle(11 * 4 + 3, 22 * 4 + 40, 9 * 4 - 7, 1);
		 boundsLocation = new Rectangle(0,0,0,0);
		 currentTile = new Point(12, 11);
		 startTile = new Point(20, 20);
		 currentLocation = new Point(0, 0);
		 startLocation = new Point(32, 32);
		 actionBounds = new Rectangle(11 * 4 + 3, 22 * 4 + 40, 9 * 4 - 7, 1);
		 fullBounds = new Rectangle(8 * 4, 1 * 4, 14 * 4, 30 * 4);
		 fullBoundsLocation = new Rectangle(0, 0, 0, 0);
		 hurtBox = new Rectangle(8 * 4, 21 * 4 - 20, 14 * 4, 10 * 4 + 20); 
		
		 Mob newMob = new Mob(frame, g2d, evil, 40, TYPE.NPC, "test2", 56, bounds, leftBounds, rightBounds,
					upBounds, downBounds, boundsLocation,
					currentTile, startTile, currentLocation, startLocation, actionBounds, fullBounds, fullBoundsLocation, true,
					hurtBox);
		newMob.setMaxHealth(5);
		newMob.addAttack("sword", 1, attackLeftBounds, attackRightBounds, attackUpBounds, attackDownBounds);
		newMob.getAttack("sword").addMovingAnim(17, 25, 9, 1, 3, 8);
		newMob.getAttack("sword").addAttackAnim(20, 28, 12, 4, 3, 6);
		newMob.getAttack("sword").addInOutAnim(16, 24, 8, 0, 1, 10);
		//newMob.getAttack("sword").setAttackBoundsLocation(new Rectangle(
				//tileBounds.x + bounds.x, tileBounds.y + bounds.y, bounds.width, bounds.height));
		newMob.stopAnim(); // stops the moving in place right when player loads
		newMob.setAnimTo(FredNPC.getStartFrame()); // stops the odd animation loading and sets player to start on its wanted start frame
		newMob.setHealthBarLocation(new Rectangle(8 * 4, 1 * 4, 14 * 4, 30 * 4));
		
		//newMob.setRI(RI.Attack); trying to work on attack for later
		
		 mobs.add(newMob);
		 
		 
		 currentTile = new Point(30, 20);
		 Mob blakeMob = new Mob(frame, g2d, professors, 40, TYPE.NPC, "Blake", 56, bounds, leftBounds, rightBounds,
					upBounds, downBounds, boundsLocation,
					currentTile, startTile, currentLocation, startLocation, actionBounds, fullBounds, fullBoundsLocation, false,
					hurtBox); // changed it from false to true so it can't get attacked
		 blakeMob.setMaxHealth(800);
		 blakeMob.stopAnim();
		 blakeMob.setAnimTo(blakeMob.getStartFrame());
		 mobs.add(blakeMob);
		 
		 //When interacted with brings up a text box
		 currentTile = new Point(32, 20);
		 Mob duncanMob = new Mob(frame, g2d, professors, 40, TYPE.NPC, "Duncan", 40, bounds, leftBounds, rightBounds,
					upBounds, downBounds, boundsLocation,
					currentTile, startTile, currentLocation, startLocation, actionBounds, fullBounds, fullBoundsLocation, false,
					hurtBox);
		 System.out.println("Duncan collision");
		 duncanMob.setMaxHealth(50);
		 duncanMob.stopAnim();
		 duncanMob.setAnimTo(duncanMob.getStartFrame());
		 mobs.add(duncanMob);
		// if(maxhelth > current)
		 
		 
		 currentTile = new Point(34, 20);
		 Mob hoffmanMob = new Mob(frame, g2d, professors, 40, TYPE.NPC, "Hoffman", 48, bounds, leftBounds, rightBounds,
					upBounds, downBounds, boundsLocation,
					currentTile, startTile, currentLocation, startLocation, actionBounds, fullBounds, fullBoundsLocation, false,
					hurtBox);
		 System.out.println("hoffman collision");
		 hoffmanMob.setMaxHealth(999);
		 hoffmanMob.stopAnim();
		 hoffmanMob.setAnimTo(hoffmanMob.getStartFrame());
		 mobs.add(hoffmanMob);
		 
		city = new Map(frame, g2d, cityTiles, 40, 40, "city", mobs, cityOverlayTiles);
		
		
		
		
	//	city = new Map(frame, g2d, map1, 16, 5, "city", mobs, map1Overlay);
	//	city = new Map(frame, g2d, cityTiles, 34, 31, "city");
	//	System.out.println(cityTiles[0].getSolidBounds().x + " SD DS ");
		maps[0] = city;
		
//		cityO = new Map(frame, g2d, cityOTiles, 40, 40, "cityO");
//	//    cityO = new Map(frame, g2d, cityOTiles, 34, 31, "cityO");
//		maps[1] = cityO;
//		houses = new Map(frame, g2d, houseTiles, 6, 6, "houses");
//		maps[2] = houses;
//		housesO = new Map(frame, g2d, houseOTiles, 6, 6, "housesO");
//		maps[3] = housesO;
		
		//Put together all items (Dont forget to add these to the count and setup methods in inGameMenu.java)
		potion = new Items(frame, g2d, extras2, 2, "Potion", false);
		potion.setHealItem(25, false, "");
		mpotion = new Items(frame, g2d, extras2, 2, "Mega Potion", false);
		potion.setHealItem(50, false, "");
		
		//Put together all events
		//Warping events
		warp1 = new Event("fromHouse", TYPE.WARP);
		warp1.setWarp("city", "cityO", 200, -50);
		warp2 = new Event("toHouse", TYPE.WARP);
		warp2.setWarp("houses", "housesO", 620, 250);
		
		//Item events
		getPotion = new Event("getPotion", TYPE.ITEM);
		getPotion.setItem(potion);
		getMpotion = new Event("getMpotion", TYPE.ITEM);
		getMpotion.setItem(mpotion);
		
		//Add the events to their specific tiles and maps
//		houses.accessTile(5).addEvent(warp1);
//		cityO.accessTile(92).addEvent(getPotion);
//		cityO.accessTile(242).addEvent(getPotion);
//		cityO.accessTile(328).addEvent(getPotion);
//		cityO.accessTile(327).addEvent(getMpotion);
//		cityO.accessTile(326).addEvent(getMpotion);
//		cityO.accessTile(325).addEvent(getMpotion);
//		cityO.accessTile(93).addEvent(getMpotion);
//		cityO.accessTile(94).addEvent(getMpotion);
//		cityO.accessTile(95).addEvent(getMpotion);
//		cityO.accessTile(96).addEvent(getMpotion);
		
		//Set up Monsters and NPCs
	//	npc = new Mob(frame, g2d, mainCharacter, 40, TYPE.RANDOMPATH, "npc", false, 40, null, null, null, null, null);
	//	npc.setMultBounds(6, 50, 92, 37, 88, 62, 92, 62, 96);
	//	npc.setMoveAnim(32, 48, 40, 56, 3, 8);
	//	npc.setHealth(60);
		
		
		
		//Add the mobs to their tile home
	//	cityO.accessTile(0).addMob(npc);
	}
	
	/************************************************************
	 * Get a map back  based on its index in the array of maps
	 * 
	 * @param index - Position in the maps array
	 * @return - Map
	 *************************************************************/
	public Map getMap(int index) {
		return maps[index];
	}
}