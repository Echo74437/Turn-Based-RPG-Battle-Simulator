/**
	This class is for creating the Sword class to be equipped
	by the Warrior class. It inherits the Weapon superclass
	that it shares with the Dagger and BattleAxe class.

	@author James Benedict Marquez
*/
public class Sword extends Weapon {
	
/**
	This constructor initializes the attributes of the
	class according to the values listed in the specs
*/
	public Sword(){
		super("Sword", 30, -10);
	}
}