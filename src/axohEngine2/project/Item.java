package axohEngine2.project;

public class Item 
{
	private String name;
	private int quantity;
	
	public Item(String name, int quantity)
	{
		this.name = name;
		this.quantity = quantity;
	}
	
	public String getName() { return name; }
	public void getQuantity(int quantity) { this.quantity = quantity; }
}
