package axohEngine2.map;

import java.util.LinkedList;

import axohEngine2.entities.DIRECTION;
import axohEngine2.project.Item;

// NOTE: This class has many different constructors based on what you want your tile's event to be.
// ENCOURAGED TO ADD ONE IF ONE DOES NOT EXIST, it's very fucking easy.
// The event is actually handled other places, but this is where it's set up...
// For example, the chest constructor -- when you press action button on chest, it opens, and you get whatever items are sent to you
public class TileEvent 
{
	String name;
	private LinkedList<Item> itemsGiven;
	private LinkedList<DIRECTION> direction;
	
	// No Event
	public TileEvent()
	{
		this.name = "Nothing";
	}
	
	// Chests
	public TileEvent(String name, LinkedList<Item> itemsGiven, LinkedList<DIRECTION> direction)
	{
		if (name.equals("Chest"))
		{
			this.name = "Chest";
			this.itemsGiven = itemsGiven;
			this.direction = direction;
		}
	}
	
	public String getName() { return name; }
	public LinkedList<Item> getItemsGiven() { return itemsGiven; }
	public LinkedList<DIRECTION> getDirection() { return direction; }
	
	
}
