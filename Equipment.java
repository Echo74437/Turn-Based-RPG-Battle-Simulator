/**
    This class is the parent class of the Armor and Weapon
	class which contains the attributes and methods utilized by
	both child classes

    @author James Benedict Marquez
*/
public class Equipment{

	protected String name;
	protected int spd;

/**
	This constructor initializes the attributes of the
	class according to the given parameters
	
	@param name - name of the class
	@param spd - speed value of the class
*/
	public Equipment(String name, int spd){
		this.name = name;
		this.spd = spd;
	}
	
/**
	This method gets the name attribute of the class
	
	@return This method returns the name of the class
*/	
	public String getName(){
		return name;
	}
	
/**
	This method gets the speed attribute of the class
	
	@return This method returns the spd of the class
*/	
	public int getSpd(){
		return spd;
	}
}