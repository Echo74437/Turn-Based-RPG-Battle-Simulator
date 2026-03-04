/**
	This class is for creating the Armor to be equipped by the
	Warrior class. It is the parent class of the Light, Medium
	and Heavy class which contains the attributes and methods
	utilized by the child classes. It inherits the Equipment
	superclass that it shares with Armor class.

	@author James Benedict Marquez
*/
public class Armor extends Equipment{
	
	private int def = 0;
	
/**
	This constructor initializes the attributes of the
	class according to the given parameters which will
	be based on the Player's decision

	@param name - name of the armor
	@param def - defense value of the armor
	@param spd - speed value of the armor
*/
	public Armor(String name, int def, int spd){
		super(name, spd);
		this.def = def;
	}
	
/**
	This method gets the name attribute of the class
	
	@return This method returns the name of the class
*/	
	public String getName(){
		return name;
	}

/**
	This method gets the Defense attribute of the class
	
	@return This method returns the def of the class
*/	
	public int getDef(){
		return def;
	}

/**
	This method gets the Speed attribute of the class
	
	@return This method returns the spd of the class
*/	
	public int getSpd(){
		return spd;
	}
	
}