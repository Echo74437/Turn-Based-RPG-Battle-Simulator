/**
	This class is for creating the Heavy class to be equipped
	by the Warrior class. It inherits the Armor superclass
	that it shares with the Light and Medium class.

	@author James Benedict Marquez
*/
public class Heavy extends Armor {
	
/**
	This constructor initializes the attributes of the
	class according to the values listed in the specs
*/
	public Heavy(){
		super("Heavy", 40, -15);
	}
}