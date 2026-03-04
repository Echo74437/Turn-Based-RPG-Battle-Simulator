/**
    This class is the parent class of the Warrior and Opponent
	class which contains the attributes and methods utilized by
	both child classes

    @author James Benedict Marquez
*/
public abstract class Character{
	
	protected int hitP;
	protected int atk;
	protected int def;
	protected int spd;
	protected int storedChargeAttack;
	protected boolean isDefending = false;
	protected boolean isCharging = false;
	
/**
	This constructor initializes the attributes of the
	class according to the given parameters
	
	@param hp - hp value of the class
	@param atk - atk value of the class
	@param def - def value of the class
	@param spd - speed value of the class
*/
	public Character(int hp, int atk, int def, int spd){;
		this.hitP = hp;
		this.atk = atk;
		this.def = def;
		this.spd = spd;
	}
	
/**
	This method allows other classes to set the HP attribute
	of the class according to the given parameter.
	
	@param hp - the value to be set to the hitP attribute of the class
*/
	public void setHP(int hp){
		this.hitP = hp;
	}

/**
    This method will add 1 to the atk value of the class
*/
	public void incrementAtk(){
		atk += 1;
	}

/**
    This method allows other classes to set the isDefending attribute
	of the class according to the given parameter.
	
	@param set - the value to be set to the isDefending attribute of the class
*/
	public void setDefending(boolean set){
		this.isDefending = set;
	}

/**
	This method allows other classes to set the isCharging attribute
	of the class according to the given parameter.
	
	@param set - the value to be set to the isCharging attribute of the class
*/
	public void setCharging(boolean set){
		this.isCharging = set;
	}
	
/**
	This method gets the HP attribute of the class
	
	@return This method returns the hitP of the class
*/	
	public int getHP(){
		return hitP;
	}

/**
	This method gets the Attack attribute of the class
	
	@return This method returns the atk of the class
*/	
	public int getAtk(){
		return atk;
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
	
/**
    This method gets the defend status of the class
	
	@return This method returns the isDefending variable of the class
*/
	public boolean getDefending(){
		return isDefending;
	}
	
/**
    This method gets the charge status of the class
	
	@return This method returns the isCharging variable of the class
*/
	public boolean getCharging(){
		return isCharging;
	}
	
/**
    This method executes the defend action of the class
*/	
	public abstract void defend();
	
/**
    This method executes the charge action of the class
	
	@param storedAtk - value to be stored to storedChargeAttack
*/
	public abstract void charge(int storedAtk);
	
/**
    This method resets the isDefending and/or isCharging variables
	of the class
	
	@param action - Action chosen by the class
*/
	public void resetStatus(String action){
		isDefending = false;
		
        if (!action.equals("charge")) {
            isCharging = false;
        }
	}

/**
	This method will display the current stats of the
	class for transparency
*/
	public abstract void displayStats();
}