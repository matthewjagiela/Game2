package axohEngine2.project;

public class Healer {
	
	private String name;
	private int healingPt;
	
	// public constructor
	public Healer (String name, int healingPt)
	{
		this.name = name;
		this.healingPt = healingPt;
	}
	
	// getters
	public String getName()
	{
		return this.name;
	}
	
	public int getHealingPt()
	{
		return this.healingPt;
	}
	
	// setters
	public boolean setName(String name)
	{
		this.name = name;
		return true;
	}
	
	public boolean setHealingPt(int healingPt) 
	{
		this.healingPt = healingPt;
		return true;
	}
	
	// check name & healing point to see which item is this
	public Boolean isSame(Healer anotherHealer){
		if(name == anotherHealer.getName() && healingPt == anotherHealer.getHealingPt()){
			return true;
		} else {
			return false;
		}
	}
	
}
