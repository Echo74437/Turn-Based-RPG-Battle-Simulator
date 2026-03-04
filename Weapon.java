/**
	This class is for creating the Weapon to be equipped by the
	Warrior class. It is the parent class of the Dagger, Sword
	and BattleAxe class which contains the attributes and
	methods utilized by the child classes. It inherits the Equipment
	superclass that it shares with Armor class.

	@author James Benedict Marquez
*/
public class Weapon extends Equipment{
	
	private int atk = 0;
	private boolean daggerCD = false;
	
/**
	This constructor initializes the attributes of the
	class according to the given parameters which will
	be based on the Player's decision

	@param name - name of the weapon
	@param atk - attack value of the weapon
	@param spd - speed value of the weapon
*/
	public Weapon(String name, int atk, int spd){
		super(name, spd);
		this.atk = atk;
	}

/**
	This method allows other classes to set the daggerCD attribute
	of the class according to the given parameter
	
	@param set - The value to be set to the daggerCD of the class
*/
	public void setDaggerCD(boolean set){
		this.daggerCD = set;
	}

/**
	This method gets the Attack attribute of the class
	
	@return This method returns the atk of the class
*/	
	public int getAtk(){
		return atk;
	}

/**
    This method gets the dagger cooldown status of the class
	
	@return This method returns the daggerCD variable of the class
*/
	public boolean getDaggerCD(){
		return daggerCD;
	}
}