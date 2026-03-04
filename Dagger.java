/**
	This class is for creating the Dagger class to be equipped
	by the Warrior class. It inherits the Weapon superclass
	that it shares with the Sword and BattleAxe class.

	@author James Benedict Marquez
*/
public class Dagger extends Weapon {
	
/**
	This constructor initializes the attributes of the
	class according to the values listed in the specs
*/
	public Dagger(){
		super("Dagger", 20, 0);
	}
}