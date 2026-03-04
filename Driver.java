import java.util.Scanner;
import java.util.InputMismatchException;
/**
    This class acts as the entry point for the Warrior Game.
    It handles character creation, opponent and environment selection,
    and starts the battle using the BattleSystem.

    @author James Benedict Marquez and Jericho G. Ocampo
*/
public class Driver {
    private Scanner scanner = new Scanner(System.in);
    
/**
    Starts the game by initializing all objects
    and launching the battle.
*/
    public void startGame() {
        System.out.println("=== WARRIOR GAME ===");
        System.out.println("\nWelcome to the battlefield!\n");
        
        // Create and setup warrior
        Warrior player = createWarrior();
        
        // Select opponent
        Opponent opponent = selectOpponent();
        
        // Select environment
        Environment environment = selectEnvironment();
        
        // Start battle
        BattleSystem battle = new BattleSystem(player, opponent, environment);
        battle.startBattle();
        
        scanner.close();
    }
    
/**
    Handles character creation that allows the player to choose their
    armor and weapon, and returns the configured Warrior object.

    @return the customized Warrior object
*/
    public Warrior createWarrior() {
        Warrior warrior = new Warrior();
        int armorChoice = -1;
		int weaponChoice = -1;
		
        System.out.println("=== CHARACTER CREATION ===");
        // Select armor
        System.out.println("\nChoose your armor:");
		System.out.println("0. No Armor");
        System.out.println("1. Light Armor (+20 Def, -5 Spd)");
        System.out.println("2. Medium Armor (+30 Def, -15 Spd)");
        System.out.println("3. Heavy Armor (+40 Def, -25 Spd)");
        
        while (true) {
            System.out.print("Enter armor choice (0-3): ");
			try{
				armorChoice = scanner.nextInt();
				if (armorChoice >= 0 && armorChoice <= 3) {
					break;
				}
				else{
					System.out.println("Invalid choice! Please enter a number from 0 to 3.");
				}
			} catch(InputMismatchException e) {
				System.out.println(e);
				System.out.println("\nInvalid input type! Please enter a number from 0 to 3.");
				scanner.next();
			}
        }
        
        
		if (armorChoice == 0) {
			System.out.println("\nNo Armor equipped");
		}
        else if (armorChoice == 1) {
            Light a = new Light();
            warrior.equipArmor(a);
        } 
        else if (armorChoice == 2) {
            Medium a = new Medium();
            warrior.equipArmor(a);
        } 
        else if (armorChoice == 3) {
            Heavy a = new Heavy();
            warrior.equipArmor(a);
        }
        
        // Select weapon
        System.out.println("\nChoose your weapon:");
		System.out.println("0. No Weapon");
        System.out.println("1. Dagger (+20 Atk, 0 Spd)");
        System.out.println("2. Sword (+30 Atk, -10 Spd)");
        System.out.println("3. Battle Axe (+40 Atk, -20 Spd)");
		
        while (true) {
            System.out.print("Enter weapon choice (0-3): ");
			try{
                weaponChoice = scanner.nextInt();
                if (weaponChoice >= 0 && weaponChoice <= 3) {
                    break;
                } 
                else {
                    System.out.println("Invalid choice! Please enter a number from 0 to 3.");
                }
            } catch(InputMismatchException e) {
				System.out.println(e);
				System.out.println("\nInvalid input type! Please enter a number from 0 to 3.");
				scanner.next();
			}
        }
        
		if (weaponChoice == 0) {
			System.out.println("\nNo Weapon equipped");
		}
        if (weaponChoice == 1) {
            Dagger w = new Dagger();
            warrior.equipWeapon(w);
        } 
		else if (weaponChoice == 2) {
            Sword w = new Sword();
            warrior.equipWeapon(w);
        } 
		else if (weaponChoice == 3) {
            BattleAxe w = new BattleAxe();
            warrior.equipWeapon(w);
        }
        
        System.out.println("\n=== YOUR FINAL STATS ===");
        warrior.displayStats();
        
		System.out.print("Press enter to continue..\n");
		scanner.nextLine();
		scanner.nextLine();
		
        return warrior;
    }
    
/**
    Allows the player to select an opponent from predefined options.

    @return the selected Opponent object
*/
    public Opponent selectOpponent() {
		Opponent o = null;
        int opponentChoice = 0;
		
        System.out.println("=== SELECT OPPONENT ===");
        System.out.println("\nChoose your foe:");
        System.out.println("1. Thief (150 HP, 20 Atk, 20 Def, 40 Spd)");
        System.out.println("2. Viking (250 HP, 30 Atk, 30 Def, 30 Spd)");
        System.out.println("3. Minotaur (350 HP, 40 Atk, 40 Def, 20 Spd)");
        
        while (true) {
            System.out.print("Enter opponent choice (1-3): ");
            try{
                opponentChoice = scanner.nextInt();
                if (opponentChoice >= 1 && opponentChoice <= 3) {
                    break;
                } 
				else {
                    System.out.println("Invalid choice! Please enter a number from 1 to 3.");
                }
            } catch(InputMismatchException e) {
				System.out.println(e);
				System.out.println("\nInvalid input type! Please enter a number from 1 to 3.");
				scanner.next();
			}
		}
        
        
        if (opponentChoice == 1) {
            o = new Opponent("Thief", 150, 20, 20, 40);
        } 
		else if (opponentChoice == 2) {
            o = new Opponent("Viking", 250, 30, 30, 30);
        } 
		else if (opponentChoice == 3) {
            o = new Opponent("Minotaur", 350, 40, 40, 20);
        }
        
        System.out.println("\n=== OPPONENT STATS ===\n");
        o.displayStats();
        
		System.out.print("Press enter to continue..\n");
		scanner.nextLine();
		scanner.nextLine();
		
        return o;
    }
    
/**
    Lets the player choose the environment in which the battle takes place.

    @return the selected Environment object
*/
    public Environment selectEnvironment() {
		Environment environment = null;
        int envChoice;
        System.out.println("=== SELECT ENVIRONMENT ===\n");
        System.out.println("Choose your battlefield:");
        System.out.println("1. Arena (No penalties)");
        System.out.println("2. Swamp (Player takes 1 damage/turn, Opponent gains 1 atk/turn)");
        System.out.println("3. Colosseum (Player gains 1 atk/turn, Opponent loses 1 def/turn)");
        
        while (true) {
            System.out.print("Enter environment choice (1-3): ");
            try{
                envChoice = scanner.nextInt();
                if (envChoice >= 1 && envChoice <= 3) {
                    break;
                } 
				else {
                    System.out.println("Invalid choice! Please enter a number from 1 to 3.");
                }
            } catch(InputMismatchException e) {
				System.out.println(e);
				System.out.println("\nInvalid input type! Please enter a number from 1 to 3.");
				scanner.next();
			}
        }
        
        if (envChoice == 1) {
            environment = new Environment("Arena");
        } 
		else if (envChoice == 2) {
            environment = new Environment("Swamp");
        } 
		else if (envChoice == 3) {
            environment = new Environment("Colosseum");
        }
        
        System.out.println("\nYou have chosen: " + environment.getEnvironmentName());
        
		System.out.print("\nPress enter to continue..");
		scanner.nextLine();
		scanner.nextLine();
		
        return environment;
    }

/**
    The main method. Launches the game by creating a Driver object
    and calling the startGame method.

    @param args stores Java command-line arguments 
*/
    public static void main(String[] args) {
        Driver game = new Driver();
        game.startGame();
    }
}