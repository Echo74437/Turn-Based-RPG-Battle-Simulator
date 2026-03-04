import java.util.Scanner;
import java.util.InputMismatchException;
/**
    This class handles the battle logic between the player (Warrior)
    and an Opponent, including the effects of the environment.

    @author Jericho G. Ocampo
*/
public class BattleSystem {
    private Warrior player;
    private Opponent opponent;
    private Environment environment;
    private Scanner scanner = new Scanner(System.in);
	
/**
    This constructor initializes the player, opponent, and environment
    for the battle system.

    @param player - the object warrior controlled by the player
    @param opponent - the object opponent
    @param environment - the object environment
*/
	public BattleSystem(Warrior player, Opponent opponent, Environment environment) {
        this.player = player;
        this.opponent = opponent;
        this.environment = environment;
	}
	
/**
    This method starts the battle and runs the game loop
    until either the player or opponent is defeated.
*/
    public void startBattle() {
        System.out.println("=== BATTLE BEGINS ===");
        
        while (player.getHP() > 0 && opponent.getHP() > 0) {
            playTurn();
        }
        
        endBattle();
    }
    
/**
    This method executes one turn of the battle,
    allowing the player and opponent to take actions.
*/
    public void playTurn() {
		int playerStoredAttack = player.getAtk();
		int oppStoredAttack = opponent.getAtk();
		String playerAction;
		String opponentAction;
		
        System.out.println("\n--- NEW TURN ---");
        
		displayBattleStats();
		
        // Get player and opponent action
        playerAction = getPlayerAction();
		opponentAction = opponent.think();
        System.out.println();
		
		System.out.println("=====================\n");
		System.out.println("=== BATTLE PHASE ===\n");
		
        // Apply environment effects
		if(environment.getEnvironmentName() == "Arena"){
			System.out.println("No Environment Effect!");
		}
		else{
			environment.applyPlayerEffect(player);
			environment.applyOpponentEffect(opponent);
		}
		
        // Determine turn order based on actions and speed
		if(player.getHP() != 0 && opponent.getHP() != 0){
			if (playerAction.equals("defend")) {
				executePlayerAction(playerAction, playerStoredAttack);
				executeOpponentAction(opponentAction, oppStoredAttack);
			}
			else if (opponentAction.equals("defend")) {
				executeOpponentAction(opponentAction, oppStoredAttack);
				executePlayerAction(playerAction, playerStoredAttack);
			}
			else{
				if (player.getSpd() >= opponent.getSpd()) {
					System.out.println("\nYou are faster! You execute your action first!");
					executePlayerAction(playerAction, playerStoredAttack);
					if (opponent.getHP() > 0) { 
						executeOpponentAction(opponentAction, oppStoredAttack);
					}
				}
				else {
					System.out.println("\nYour opponent is faster! They execute their action first!");
					executeOpponentAction(opponentAction, oppStoredAttack);
					if (player.getHP() > 0) { 
						executePlayerAction(playerAction, playerStoredAttack);
					}
				}
			}
		}
		
		// Reset defend and/or charge status for player and opponent
		player.resetStatus(playerAction);
		opponent.resetStatus(opponentAction);
		
        displayBattleStats();
		
		if(player.getHP() > 0 && opponent.getHP() > 0){
			System.out.print("\nPress enter to continue..");
			scanner.nextLine();
			scanner.nextLine();
		}
		
    }
    
/**
    This method prompts the player for their action and reads the input.

    @return the chosen action as an integer (1 = Attack, 2 = Defend, 3 = Charge)
*/
    public String getPlayerAction() {
        System.out.println("Choose your action:");
        System.out.println("1. Attack");
        System.out.println("2. Defend");
        System.out.println("3. Charge");
        
        while (true) {
            System.out.print("Enter your choice (1-3): ");
			try{
				int input = scanner.nextInt();
				
				switch (input){
                case 1:
                    return "attack";
                case 2:
                    return "defend";
				case 3:
                    return "charge";
                default:
                    System.out.println("Invalid choice! Please enter 1, 2, or 3.");
				}
			} catch(InputMismatchException e) {
				System.out.println(e);
				System.out.println("\nInvalid input type! Please enter a number from 1 to 3.");
				scanner.next();
			}
        }
    }

/**
    This method executes the player's chosen action.

    @param action - the player's chosen action
*/
    public void executePlayerAction(String action, int storedAtk) {
        switch (action) {
            case "attack":
				player.attack(opponent);
				break;
				
            case "defend":
				player.defend();
                break;
                
            case "charge":
				player.charge(storedAtk);
                break;
        }
    }

/**
    This method makes the opponent take its turn,
    which includes attacking the player and checking for defense.
	
	@param action - the opponent's action
*/
    public void executeOpponentAction(String action, int storedAtk){
		switch (action) {
            case "attack":
				opponent.attack(player);
                break;
                
            case "defend":
				opponent.defend();
                break;
                
            case "charge":
                opponent.charge(storedAtk);
				break;
        }
    }
    
/**
    This method prints the current stats of the player and the opponent.
*/
    public void displayBattleStats() {
        System.out.println("\n=== BATTLE STATUS ===");
		System.out.println("Environment: " + environment.getEnvironmentName());
        player.displayStats();
        opponent.displayStats();
        
        if (player.getCharging()) {
            System.out.println("You are CHARGED for next attack!");
        }
        System.out.println("=====================");
    }
    
/**
    This method prints the result of the battle
    based on the victor (who is still alive).
*/
    public void endBattle() {
        System.out.println("\n=== BATTLE ENDED ===\n");
        
        if (player.getHP() <= 0) {
            System.out.println("DEFEAT! You have been knocked out!");
            System.out.println("The " + opponent.getName() + " stands victorious...");
        } 
        else if (opponent.getHP() <= 0) {
            System.out.println("VICTORY! You have defeated the " + opponent.getName() + "!");
            System.out.println("You emerge triumphant from the " + environment.getEnvironmentName() + "!");
        }
        
		scanner.close();
        System.out.println("\nThanks for playing!");
    }
}