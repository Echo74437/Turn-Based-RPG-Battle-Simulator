/**
	This class is for creating the Light class to be equipped
	by the Warrior class. It inherits the Armor superclass
	that it shares with the Medium and Heavy class.

	@author James Benedict Marquez
*/
public class Light extends Armor {
	
/**
	This constructor initializes the attributes of the
	class according to the values listed in the specs
*/
	public Light(){
		super("Light", 20, -5);
	}
}