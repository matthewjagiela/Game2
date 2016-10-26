package axohEngine2.project;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JFrame;

import axohEngine2.Judgement;
import axohEngine2.entities.DIRECTION;
import axohEngine2.entities.SpriteSheet;
import axohEngine2.map.Tile;
import axohEngine2.map.TileEvent;


public class TileCreator 
{
	Tile tile;
	Rectangle location;
	Rectangle solidBounds;
	TileEvent eventType;
	int scale = Judgement.scale;
	
	//Set up spriteSheets
	SpriteSheet misc = new SpriteSheet("/textures/environments/environments1.png", 16, 16, 16, scale);
	SpriteSheet buildings = new SpriteSheet("/textures/environments/4x4buildings.png", 4, 4, 64, scale);
	SpriteSheet environment32 = new SpriteSheet("/textures/environments/32SizeEnvironment.png", 8, 8, 32,scale);
	SpriteSheet extras2 = new SpriteSheet("/textures/extras/extras2.png", 16, 16, 16, scale);
	SpriteSheet mainCharacter = new SpriteSheet("/textures/characters/mainCharacter.png", 8, 8, 32, scale);
	SpriteSheet Chests = new SpriteSheet("/textures/extras/Chests.png", 16, 16, 16, 4);
	SpriteSheet house = new SpriteSheet("/textures/environments/house (1).png", 16, 16, 16, 4);
	
	SpriteSheet map1 = new SpriteSheet("/menus/Homestead 2.0 (1).png", 16, 16, 16, 4);
	
	public Tile create(JFrame frame, Graphics2D g2d, int scale, String name)
	{
		switch (name)
		{
			case "bricks":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "bricks", misc, 16, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "bricksTop":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
				solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);	
				eventType = new TileEvent();
				tile = new Tile(frame, g2d, "bricksTop", misc, 17, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "chest":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
				solidBounds = new Rectangle(1 * scale, 2 * scale, 14 * scale - 1, 14 * scale);		
				LinkedList<Item> itemsGiven = new LinkedList<Item>();
				//itemsGiven.add(Item.AttackItem);
				LinkedList<DIRECTION> direction = new LinkedList<DIRECTION>();
				direction.add(DIRECTION.UP);
				eventType = new TileEvent("Chest", itemsGiven, direction);
				tile = new Tile(frame, g2d, "chest", Chests, 0, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "walkway":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);	
			    eventType = new TileEvent();
			    tile = new Tile(frame, g2d, "walkWay", misc, 6, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
			    break;
			case "grass":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);	
			    eventType = new TileEvent();
			    tile = new Tile(frame, g2d, "grass", misc, 0, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
			    break;
			case "grassDarker":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);
			    eventType = new TileEvent();
			    tile = new Tile(frame, g2d, "grassDarker", misc, 3, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
			    break;
			case "tree1":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			//    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);	
				solidBounds = new Rectangle(0, 0, 0, 0);	
				eventType = new TileEvent();
			    tile = new Tile(frame, g2d, "tree1", Chests, 3, false, false, true, location,
						solidBounds, eventType, solidBounds, 1);
				break;
			case "tree2":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			  //  solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);	
				solidBounds = new Rectangle(0, 0, 0, 0);	
				eventType = new TileEvent();
			    tile = new Tile(frame, g2d, "tree2", Chests, 4, false, false, true, location,
						solidBounds, eventType, solidBounds, 1);
			    break;
			case "tree3":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);	
			    eventType = new TileEvent();
			    tile = new Tile(frame, g2d, "tree3", Chests, 5, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
			    break;
			case "tree4":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);	
			    eventType = new TileEvent();
			    tile = new Tile(frame, g2d, "tree4", Chests, 6, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
			    break;
			case "flower":
			    location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);	
			    eventType = new TileEvent();
			    tile = new Tile(frame, g2d, "flower", misc, 1, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
			    break;
			case "brickGold":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);	
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "brickGold", misc, 8, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "rock":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(3 * scale, 3 * scale, 11 * scale, 9 * scale);	
			    eventType = new TileEvent();
			    tile = new Tile(frame, g2d, "rock", Chests, 7, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
			    break;
			case "chestOpen":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
				solidBounds = new Rectangle(1 * scale, 2 * scale, 14 * scale - 1, 14 * scale);	
				eventType = new TileEvent();
				tile = new Tile(frame, g2d, "chestOpen", Chests, 1, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "empty":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
				solidBounds = new Rectangle(0, 0, 0, 0);	
				eventType = new TileEvent();
				tile = new Tile(frame, g2d, "empty", misc, 9, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "house1":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "bricks", house, 32, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "house2":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "bricks", house, 33, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "house3":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "bricks", house, 34, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "house4":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "bricks", house, 35, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "house5":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "bricks", house, 48, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "house6":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "bricks", house, 49, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "house7":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "bricks", house, 50, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "house8":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "bricks", house, 51, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			
		}
		
		
		
		return tile;
	}
	

	
	public Tile map1(JFrame frame, Graphics2D g2d, int scale, String name)
	{
		switch (name)
		{
			case "t00":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t00", map1, 0, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t01":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);	
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t01", map1, 1, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t02":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t02", map1, 2, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t03":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t03", map1, 3, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t04":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t04", map1, 4, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t05":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t05", map1, 5, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t06":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t06", map1, 6, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t07":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t07", map1, 7, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t08":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t08", map1, 8, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t09":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t09", map1, 9, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t10":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t10", map1, 10, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t11":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t11", map1, 11, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t12":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t12", map1, 12, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t13":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t13", map1, 13, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t14":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t14", map1, 14, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t15":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t15", map1, 15, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t16":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t16", map1, 16, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t17":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t17", map1, 17, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t18":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);	
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t18", map1, 18, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t19":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);	
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t19", map1, 19, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t20":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t20", map1, 20, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t21":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t21", map1, 21, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t22":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t22", map1, 22, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t23":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t23", map1, 23, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t24":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t24", map1, 24, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t25":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t25", map1, 25, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t26":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);	
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t26", map1, 26, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t27":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t27", map1, 27, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t28":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t28", map1, 28, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t29":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t29", map1, 29, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t30":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t30", map1, 30, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t31":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t31", map1, 31, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t32":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t32", map1, 32, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t33":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);	
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t33", map1, 33, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t34":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);	
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t34", map1, 34, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t35":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t35", map1, 35, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t36":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t36", map1, 36, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t37":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t37", map1, 37, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t38":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);	
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t38", map1, 38, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t39":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t39", map1, 39, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t40":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t40", map1, 40, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t41":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t41", map1, 41, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t42":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t42", map1, 42, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t43":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t43", map1, 43, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t44":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t44", map1, 44, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t45":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);	
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t45", map1, 45, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t46":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t46", map1, 46, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t47":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t47", map1, 47, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t48":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t48", map1, 48, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t49":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t49", map1, 49, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t50":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);	
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t50", map1, 50, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t51":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t51", map1, 51, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t52":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t52", map1, 52, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t53":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t53", map1, 53, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t54":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t54", map1, 54, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t55":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t55", map1, 55, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t56":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t56", map1, 56, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t57":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t57", map1, 57, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t58":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t58", map1, 58, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t59":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t59", map1, 59, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t60":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t60", map1, 60, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t61":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t61", map1, 61, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t62":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t62", map1, 62, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t63":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t63", map1, 63, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t64":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t64", map1, 64, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t65":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t65", map1, 65, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t66":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t66", map1, 66, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t67":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t67", map1, 67, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t68":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t68", map1, 68, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t69":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t69", map1, 69, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t70":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 16 * scale, 16 * scale);	
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t70", map1, 70, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t71":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t71", map1, 71, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t72":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t72", map1, 72, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t73":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t73", map1, 73, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t74":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t74", map1, 74, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t75":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t75", map1, 75, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t76":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t76", map1, 76, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t77":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t77", map1, 77, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t78":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t78", map1, 78, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
			case "t79":
				location = new Rectangle(0 * scale, 0 * scale, 16 * scale, 16 * scale);
			    solidBounds = new Rectangle(0, 0, 0, 0);		
			    eventType = new TileEvent();
				tile = new Tile(frame, g2d, "t79", map1, 79, false, false, true, location,
						solidBounds, eventType, solidBounds, 0);
				break;
		}
		
		return tile;
	}
}
