/**
	This class is for the Warrior which will be controlled
	by the Player in the game. It inherits the Character superclass
	which it shares with the Opponent class.

	@author James Benedict Marquez
*/
public class Warrior extends Character{
	
	private Weapon weapon = new Weapon("",0,0);
	private Armor armor = new Armor("",0,0);
	private boolean isEvading = false;

/**
	This constructor initializes the attributes of the Warrior class
	according to the specs
*/
	public Warrior(){
		super(100, 1, 1, 50);
	}
	
/**
	This method will make the Warrior class equip the given
	Armor child class parameter and modify its attributes
	
	@param a - the Light Armor that the class will equip
*/
	public void equipArmor(Light a){
		System.out.println();
		System.out.println(a.getName() + " equipped!");
		this.armor = a;
		this.def += a.getDef();
		this.spd += a.getSpd();
	}

/**
	This method will make the Warrior class equip the given
	Armor child class parameter and modify its attributes
	
	@param a - the Medium Armor that the class will equip
*/
	public void equipArmor(Medium a){
		System.out.println();
		System.out.println(a.getName() + " equipped!");
		this.armor = a;
		this.def += a.getDef();
		this.spd += a.getSpd();
	}

/**
	This method will make the Warrior class equip the given
	Armor child class parameter and modify its attributes
	
	@param a - the Heavy Armor that the class will equip
*/
	public void equipArmor(Heavy a){
		System.out.println();
		System.out.println(a.getName() + " equipped!");
		this.armor = a;
		this.def += a.getDef();
		this.spd += a.getSpd();
	}
	
/**
	This method will make the Warrior class equip the given
	Weapon child class parameter and modify its attributes
	
	@param w - the Dagger that the class will equip
*/
	public void equipWeapon(Dagger w){
		System.out.println();
		System.out.println(w.getName() + " equipped!");
		this.weapon = w;
		this.atk += w.getAtk();
		this.spd += w.getSpd();
	}

/**
	This method will make the Warrior class equip the given
	Weapon child class parameter and modify its attributes
	
	@param w - the Sword that the class will equip
*/
	public void equipWeapon(Sword w){
		System.out.println();
		System.out.println(w.getName() + " equipped!");
		this.weapon = w;
		this.atk += w.getAtk();
		this.spd += w.getSpd();
	}

/**
	This method will make the Warrior class equip the given
	Weapon child class parameter and modify its attributes
	
	@param w - the BattleAxe that the class will equip
*/
	public void equipWeapon(BattleAxe w){
		System.out.println();
		System.out.println(w.getName() + " equipped!");
		this.weapon = w;
		this.atk += w.getAtk();
		this.spd += w.getSpd();
	}

/**
	This method gets the Speed attribute of the class
	
	@return This method returns the spd of the class
*/	
	public int getSpd(){
		if(isCharging && weapon.getName() == "Battle Axe"){
			System.out.println("You temporarily gained 5 speed for charging with the Battle Axe!");
			return spd + 5;
		}
		return spd;
	}

/**
    This method gets the evade status of the class because of the
	Dagger ability
	
	@return This method returns the isEvading variable of the class
*/
	public boolean getEvading(){
		return isEvading;
	}

/**
	This method will execute the attack action of the class
	
	@param opponent - The Opponent class that the class will attack
*/
	public void attack(Opponent opponent){
		int attack;
		
		// Check if the class previously charged to use storedChargeAttack
		if (isCharging){
			attack = storedChargeAttack * 3;
			System.out.println("CHARGED ATTACK! You strike with tremendous force!");
		}
		else{
			attack = atk;
		}
		
		// Check if the opponent is defending and divide the attack value
		if (opponent.getDefending()) {
			attack /= 2;
		}
		
		// Check for weapon effects
		if (weapon.getName() == "Sword"){
			attack += 10;
			System.out.println("You temporarily gained 10 attack power with the Sword!");
		}
		else if (isCharging && weapon.getName() == "Battle Axe"){
			attack += 5;
			System.out.println("You temporarily gained 5 attack power for charging with the Battle Axe!");
		}
		
		// Calculate damage and set to 0 if first parameter is negative
        int damage = Math.max(attack - opponent.getDef(), 0);
			
		// Check if damage is higher than the opponent's hp and adjust to prevent negative values
		if(damage > opponent.getHP()){
			damage = opponent.getHP();
		}
		
		// Attack the opponent
		opponent.setHP(opponent.getHP() - damage);
		
		// Check if opponent is defending and print accordingly
		if(opponent.getDefending() && opponent.getHP() > 0) {
			System.out.println("You attack for " + damage + " damage! (Reduced by defense)");
		}
		else {
			System.out.println("You attack for " + damage + " damage!");
		}
	}
	
/**
    This method executes the defend action of the class
*/	
	public void defend(){
		isDefending = true;
		
		// Check if class previously charged
		if (isCharging) {
            System.out.println("\nYou raise your guard, but your charge energy dissipates!");
        }
        else {
            System.out.println("\nYou raise your guard, ready to defend with all your might!");
        }
		
		// Checks if equipped weapon is Dagger and apply effects accordingly
		if(weapon.getName() == "Dagger" && !weapon.getDaggerCD()){
			isEvading = true;
			System.out.println("With your swift dagger, you evade the next attack!");
		}
	}

/**
    This method executes the charge action of the class
	
	@param storedAtk - value to be stored to storedChargeAttack
*/
	public void charge(int storedAtk){
		// Checks if the class previously charged
		if(isCharging){
			System.out.println("You charge again but realize that it does not stack");
		}
		else{
			isCharging = true;
            storedChargeAttack = storedAtk;
            System.out.println("You gather your strength for a powerful attack next turn!");
		}
	}

/**
    This method resets the isDefending and/or isCharging variables
	of the class
	
	@param action - Action chosen by the class
*/
	public void resetStatus(String action){
		// Reset isDefending to false
		isDefending = false;
		
		// Resets isEvading is false and sets daggerCD as needed
		if(isEvading){
			isEvading = false;
			weapon.setDaggerCD(true);
		}
		else{
			weapon.setDaggerCD(false);
		}
		
		// Checks if the action is not charge and reset isCharging
        if (!action.equals("charge")) {
            isCharging = false;
        }
	}
	
/**
	This method will display the current stats of the
	class for transparency
*/
	public void displayStats(){
		System.out.println("\nPlayer Stats\n");
		System.out.println("Health Points: " + getHP());
		System.out.println("Attack Power: " + getAtk());
		System.out.println("Defense Power: " + getDef());
		System.out.println("Speed Points: " + getSpd());
		System.out.println();
	}
}