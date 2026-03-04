/**
	This class is for creating the Medium class to be equipped
	by the Warrior class. It inherits the Armor superclass
	that it shares with the Light and Heavy class.

	@author James Benedict Marquez
*/
public class Medium extends Armor {
	
/**
	This constructor initializes the attributes of the
	class according to the values listed in the specs
*/
	public Medium(){
		super("Medium", 30, -15);
	}
}