package axohEngine2.project;

import java.util.ArrayList;
import java.util.Vector;


public class Item 
{
	private ArrayList<Weapon> weapons;
	private Vector<Integer> numWeapon;
	
	public Boolean addWeapon(Weapon weapon, int nWeapon){
		Boolean find = false;
		for(int i=0; i<weapons.size();i++){
			if(weapons.get(i).isSame(weapon)){
				numWeapon.set(i, numWeapon.get(i) + nWeapon);
				find = true;
			}
		}
		if(!find){
			weapons.add(weapon);
			numWeapon.add(nWeapon);
		}
		return true;
	}
	
	public int getNWeapon(){
		return weapons.size();
	}
	
	public String getWeapon(int i){
		if(i < 0 || i > (weapons.size()-1)){
			return "";
		}
		
		String tmp;
		tmp =weapons.get(i).getName() + " " + weapons.get(i).getAttact() + " " + numWeapon.get(i);
		return tmp;
	}
	
}
