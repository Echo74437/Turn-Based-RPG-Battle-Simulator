/**
	This class is for creating the BattleAxe class to be equipped
	by the Warrior class. It inherits the Weapon superclass
	that it shares with the Dagger and Sword class.

	@author James Benedict Marquez
*/
public class BattleAxe extends Weapon {
	
/**
	This constructor initializes the attributes of the
	class according to the values listed in the specs
*/
	public BattleAxe(){
		super("Battle Axe", 40, -20);
	}
}