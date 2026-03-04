/**
    This class defines an Environment that influences the battle by
    applying specific effects to the Warrior and Opponent based on its type.
    
    @author Jericho G. Ocampo
*/
public class Environment {
    private String environmentName;
	
/**
    This constructor initializes the environment name and penalties.

    @param environmentName - the name of the environment
*/
    public Environment(String environmentName) {
        this.environmentName = environmentName;
    }
    
    // Getters

/**
    This method returns the name of the environment.

    @return string value for the environment name
*/
    public String getEnvironmentName() { 
        return environmentName; 
    }
   
/**
    This method applies environmental effects to the player (Warrior),
    depending on the environment type.

    @param warrior - the Warrior object to apply effects to
*/
    public void applyPlayerEffect(Warrior warrior) {
        switch (environmentName) {
            case "Swamp":
                // Player takes 1 damage every turn
                warrior.setHP(warrior.getHP() - 1);
                System.out.println("The swamp drains your energy! You take 1 damage from the environment.");
                break;
            case "Colosseum":
                // Player gains 1 attack every turn
                warrior.incrementAtk();
                System.out.println("The crowd cheers for you! Your attack increases by 1!");
                break;
        }
    }
   
/**
    This method applies environmental effects to the opponent,
    depending on the environment type.

    @param opponent - the Opponent object to apply the effects to
*/
    public void applyOpponentEffect(Opponent opponent) {
        switch (environmentName) {
            case "Swamp":
                // Opponent gains 1 attack every turn
                opponent.incrementAtk();
                System.out.println("The " + opponent.getName() + " feeds off the swamp's dark energy! Attack increased by 1.");
                break;
            case "Colosseum":
                // Opponent loses 1 defense each turn
				if(opponent.getDef() == 0){
					System.out.println("The crowd has drained the " + opponent.getName() + " of their defense!");
				}
				else{
					opponent.decrementDef();
					System.out.println("The " + opponent.getName() + " is intimidated by the crowd! Defense decreased by 1.");
				}
                break;
        }
    }
}