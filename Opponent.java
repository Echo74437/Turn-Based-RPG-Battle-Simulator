/**
	This class is for the Opponent which the player will fight
	against in the game. It inherits the Character superclass
	which it shares with the Warrior class.

	@author James Benedict Marquez
*/
public class Opponent extends Character{

	private String name;
	private int ctr = 0;
	
/**
	This constructor initializes the attributes of the
	class according to the given parameters which will
	be based on the Player's decision

	@param name - name of the opponent
	@param hp - hp value of the opponent
	@param atk - atk value of the opponent
	@param def - def value of the opponent
	@param spd - speed value of the opponent
*/
	public Opponent(String name, int hp, int atk, int def, int spd){
		super(hp, atk, def, spd);
		this.name = name;
	}

	public void decrementDef(){
		def -= 1;
	}
	
/**
	This method gets the name attribute of the class
	
	@return This method returns the name of the class
*/		
	public String getName(){
		return name;
	}

/**
	This method is a faux-ai designed to make the opponent
	"think" and choose an action based on a three action cycle
	
	@return This method returns the chosen action of the class
*/
	public String think(){
		// Return action based on ctr and opponent's name (Faux-AI)
		if(ctr == 0){
			ctr++;
			return "attack";
		}
		
		// Thief always attacks
		if(ctr == 1 && name == "Thief"){
			ctr++;
			return "attack";
		}
		// Viking defends on the 2nd cycle of the cycle
		else if(ctr == 1 && name == "Viking"){
			ctr++;
			return "defend";
		}
		// Minotaur charges on the 2nd action of the cycle
		else if(ctr == 1 && name == "Minotaur"){
			ctr++;
			return "charge";
		}
		// Reset cycle, all opponent types attack on the 3rd action
		else{
			ctr -= 2;
			return "attack";
		}
	}

/**
	This method will execute the attack action of the class
	
	@param player - The Warrior object that the class will attack
*/
	public void attack(Warrior player){
		int attack;
		
		// Check if the class previously charged to use storedChargeAttack
		if (isCharging){
			attack = storedChargeAttack * 3;
			System.out.println("CHARGED ATTACK! Your opponent strikes with tremendous force!");
		}
		else{
			attack = atk;
		}
		
		// Check if the player is evading and the Evade ability of the Dagger weapon is active
		if (player.getDefending() && player.getEvading()) {
			attack = 0;
		}
		else if(player.getDefending()){
			attack /= 2;
		}
        
		// Calculate damage and set to 0 if first parameter is negative
		int damage = Math.max(attack - player.getDef(), 0);
		
		// Check if damage is higher than the opponent's hp and adjust to prevent negative values
		if(damage > player.getHP()){
			damage = player.getHP();
		}
		
		// Attack the opponent
		player.setHP(player.getHP() - damage);
		
		// Check if opponent is defending/evading and print accordingly
		if(player.getDefending() && player.getEvading()) {
			System.out.println("The "+ name + " attacks you for " + damage + " damage! (Evaded with Dagger)");
		}
		else if(player.getDefending()) {
			System.out.println("The "+ name + " attacks you for " + damage + " damage! (Reduced by defense)");
		}
		else {
			System.out.println("The " + name + " attacks you for " + damage + " damage!");
		}
	}

/**
    This method executes the defend action of the class
*/	
	public void defend(){
		isDefending = true;
		System.out.println("Your opponent raises their guard, ready to defend!");
	}

/**
    This method executes the charge action of the class
	
	@param storedAtk - value to be stored to storedChargeAttack
*/
	public void charge(int storedAtk){
		isCharging = true;
		storedChargeAttack = storedAtk;
        System.out.println("Your opponents gathers their strength for a powerful attack next turn!");
	}
/**
	This method will display the current stats of the
	class for transparency
*/
	public void displayStats(){
		System.out.println("Opponent Stats\n");
		System.out.println("Opponent: " + name);
		System.out.println("Health Points: " + hitP);
		System.out.println("Attack Power: " + atk);
		System.out.println("Defense Power: " + def);
		System.out.println("Speed Points: " + spd);
		System.out.println();
	}
}