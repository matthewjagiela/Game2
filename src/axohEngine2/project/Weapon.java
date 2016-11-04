package axohEngine2.project;

public class Weapon {
	private String name;
	private int attack;

	public Weapon(String name, int attack){
		this.name = name;
		this.attack = attack;
	}
	
	public String getName() {
		return name;
	}

	public int getAttact() {
		return attack;
	}
	
	public Boolean setName(String name){
		this.name = name;
		return true;
	}
	
	public Boolean setAttack(int attack){
		this.attack = attack;
		return true;
	}
	
	public Boolean isSame(Weapon anotherWeapon){
		if(name == anotherWeapon.getName() && attack == anotherWeapon.getAttact()){
			return true;
		} else {
			return false;
		}
	}
}
