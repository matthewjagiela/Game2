package axohEngine2.project;

import java.util.ArrayList;
import java.util.Vector;
import axohEngine2.project.Weapon;

public class Item 
{
	private int itemTotal = 0;
	private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	private ArrayList<Healer> healers = new ArrayList<Healer>();
	private Vector<Integer> numWeapon = new Vector<Integer>();
	private Vector<Integer> numHealer = new Vector<Integer>();
	
	public Boolean addWeapon(Weapon weapon, int nWeapon){
		
		Boolean find = false;
		for(int i = 0; i < weapons.size(); i++){
			if(weapons.get(i).isSame(weapon)){
				numWeapon.set(i, numWeapon.get(i) + nWeapon);
				find = true;
			}
		}
		if(!find){
			weapons.add(weapon);
			numWeapon.add(nWeapon);
			itemTotal = itemTotal + 1;
		}
		return true;
	}
	
	public Boolean addHealer(Healer healer, int nHealer) {
		
		Boolean find = false;
		for(int i = 0; i < healers.size(); i++) {
			if(healers.get(i).isSame(healer))
			{
				numHealer.set(i, numHealer.get(i) + nHealer);
				find = true;
			}
			if(!find)
			{
				healers.add(healer);
				numHealer.add(nHealer);
				itemTotal = itemTotal + 1;
			}
		}
		
		return true;
	}
	
	// get the total number of a specific type of items
	public int getNWeapon(){
		return weapons.size();
	}
	
	public int getNHealer(){
		return healers.size();
	}
	
	// get the total number of the item types
	public int getItemTotal(){
		return itemTotal;
	}
	
	
	// get the name of a specific type of items
	// for item description card print
	public String getWeaponDescription(int i)
	{
		if(i < 0 || i > (weapons.size()-1)){
			return "";
		}
		
		String tmp;
		tmp = "Attack = " + weapons.get(i).getAttact();
		return tmp;
	}
	
	public String getHealerDescription(int i) 
	{
		if(i < 0 || i > (healers.size()-1))
		{
			return "";
		}
		
		String tmp;
		tmp = "HP + " + healers.get(i).getHealingPt();
		return tmp;
	}
	
	// get info of a certain type of items
	// for inventory display
	public String getWeapon(int i)
	{
		if(i < 0 || i > (weapons.size()-1)){
			return "";
		}
		
		String tmp;
		tmp = weapons.get(i).getName() + "    *" + numWeapon.get(i);
		return tmp;
	}
	
	public String getHealer(int i)
	{
		if(i < 0 || i > (healers.size()-1)){
			return "";
		}
		
		String tmp;
		tmp = healers.get(i).getName() + "    *" + numHealer.get(i);
		return tmp;
	}
	
	//get name
	public String getWeaponName(int i)
	{
		if(i < 0 || i > (weapons.size()-1)){
			return "";
		}
		
		String tmp;
		tmp = weapons.get(i).getName();
		return tmp;
	}
	
	public String getHealerName(int i)
	{
		if(i < 0 || i > (healers.size()-1)){
			return "";
		}
		
		String tmp;
		tmp = healers.get(i).getName();
		return tmp;
	}
	
	
	
}
