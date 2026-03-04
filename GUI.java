import javax.swing.*;
import java.awt.*;
/**
   The GUI class represents the main graphical user interface for the game.
   It calls all GUI related classes and manages the game flow including
   character creation, opponent selection, environment selection, and battle.

   @author Jerico G. Ocampo
*/
public class GUI extends JFrame {
    /** Card layout to switch between different panels */
    private CardLayout cardLayout; 

    /** Main panel that holds all card panels */
    private JPanel cardPanel;  

    /** Player warrior object controlled by the player */
    private Warrior player;    

    /** Opponent object to fight against */                        
    private Opponent opponent; 

    /** Environment object for the battle */
    private Environment environment; 

    /** Battle system object to manage combat */
    private BattleSystem battleSystem; 

    /** Player's maximum health point */
    private int playerMaxHP; 

    /** Opponent's maximum health point */
    private int opponentMaxHP; 

    /** Player's equipped weapon name */
    private String playerWeaponName = "No Weapon"; 
    
    // UI Components
    /** Main menu panel */
    private MainMenu mainMenu; 
    
    /** Warrior creation panel */
    private WarriorCreation warriorCreation; 
    
    /** Opponent selection panel */
    private OpponentSelection opponentSelection; 
    
    /** Environment selection panel */
    private EnvironmentSelection environmentSelection; 
    
    /** Battle screen panel */
    private BattleScreen battleScreen; 

/**
    This constructor initializes the main GUI window and sets up
    all the UI components including card layout and panels.
*/
    public GUI() {
        setTitle("Warrior Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Initialize UI components
        mainMenu = new MainMenu(this);
        warriorCreation = new WarriorCreation(this);
        opponentSelection = new OpponentSelection(this);
        environmentSelection = new EnvironmentSelection(this);
        battleScreen = new BattleScreen(this);

        // Add panels to card layout
        cardPanel.add(mainMenu.getPanel(), "main");
        cardPanel.add(warriorCreation.getPanel(), "character");
        cardPanel.add(opponentSelection.getPanel(), "opponent");
        cardPanel.add(environmentSelection.getPanel(), "environment");
        cardPanel.add(battleScreen.getPanel(), "battle");

        add(cardPanel);
        setVisible(true);
    }

/**
    This method switches between different card panels in the GUI.
    
    @param cardName - the name of the card to display
*/
    public void showCard(String cardName) {
        cardLayout.show(cardPanel, cardName);
    }

/**
    This method sets the player warrior object for the GUI.
    
    @param player - the warrior object controlled by the player
*/
    public void setPlayer(Warrior player) {
        this.player = player;
    }

/**
    This method sets the player's weapon name for GUI tracking.
    
    @param weaponName - the name of the weapon equipped
*/
    public void setPlayerWeaponName(String weaponName) {
        this.playerWeaponName = weaponName;
    }

/**
    This method returns the current player warrior object.
    
    @return the warrior object controlled by the player
*/
    public Warrior getPlayer() {
        return player;
    }

/**
    This method sets the opponent object for the GUI.
    
    @param opponent - the opponent object to fight against
*/
    public void setOpponent(Opponent opponent) {
        this.opponent = opponent;
    }

/**
    This method returns the current opponent object.
    
    @return the opponent object being fought against
*/
    public Opponent getOpponent() {
        return opponent;
    }

/**
    This method sets the environment object for the battle.
    
    @param environment - the environment object containing battle effects
*/
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

/**
    This method returns the current environment object.
    
    @return the environment object containing battle effects
*/
    public Environment getEnvironment() {
        return environment;
    }

/**
    This method initializes the battle screen with current player,
    opponent, and environment selections, setting up all UI elements.
*/
    public void initializeBattle() {
        // Store initial max HP values for health bar calculations
        playerMaxHP = player.getHP();
        opponentMaxHP = opponent.getHP();

        battleSystem = new BattleSystem(player, opponent, environment);
        updateOpponentLabel();

        // Restore the original action panel with attack/defend/charge buttons
        JPanel battlePanel = (JPanel) cardPanel.getComponent(4);            // Battle screen
        JPanel mainBattleArea = (JPanel) battlePanel.getComponent(1);       // Main battle area
        JPanel actionPanel = createNewActionPanel();                          // Create fresh action panel
        mainBattleArea.remove(2);                                       // Remove the old action panel
        mainBattleArea.add(actionPanel, BorderLayout.SOUTH);                  // Add the new action panel
        mainBattleArea.revalidate();
        mainBattleArea.repaint();
        
        // Update character portraits based on selections
        updateCharacterPortraits();
        updateBattleBackground();
        updateBattleLog("         === BATTLE BEGINS ===\n");
        updateBattleLog("\nWelcome to the " + environment.getEnvironmentName() + "!\n");
        updateStats();
        updateWeaponAbilityDisplay();
        updateEnvironmentInfo();
    }

/**
    This method creates a new action panel with attack, defend, and charge
    buttons along with the player health bar.
    
    @return the newly created action panel
*/
    private JPanel createNewActionPanel() {
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.setPreferredSize(new Dimension(0, 90));
        actionPanel.setBackground(Color.WHITE); 
        actionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 

        // Small space at top
        actionPanel.add(Box.createVerticalStrut(5));

        // BUTTONS PANEL
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 3, 10, 0)); // 1 row, 3 columns, 10px horizontal gap
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.setMaximumSize(new Dimension(400, 40)); // Constrain the button panel size

        JButton attackButton = new JButton("ATTACK");
        JButton defendButton = new JButton("DEFEND");
        JButton chargeButton = new JButton("CHARGE");

        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Dimension buttonSize = new Dimension(120, 40);
        
        attackButton.setFont(buttonFont);
        attackButton.setPreferredSize(buttonSize);
        attackButton.setBackground(Color.RED);
        attackButton.setForeground(Color.WHITE);
        attackButton.setFocusPainted(false);
        
        defendButton.setFont(buttonFont);
        defendButton.setPreferredSize(buttonSize);
        defendButton.setBackground(Color.BLUE);
        defendButton.setForeground(Color.WHITE);
        defendButton.setFocusPainted(false);
        
        chargeButton.setFont(buttonFont);
        chargeButton.setPreferredSize(buttonSize);
        chargeButton.setBackground(Color.ORANGE);
        chargeButton.setForeground(Color.WHITE);
        chargeButton.setFocusPainted(false);

        attackButton.addActionListener(e -> executeTurn("attack"));
        defendButton.addActionListener(e -> executeTurn("defend"));
        chargeButton.addActionListener(e -> executeTurn("charge"));

        buttonsPanel.add(attackButton);
        buttonsPanel.add(defendButton);
        buttonsPanel.add(chargeButton);

        actionPanel.add(buttonsPanel);
        
        // Small gap between buttons and health bar
        actionPanel.add(Box.createVerticalStrut(3));

        // PLAYER HEALTH BAR - Direct placement without wrapper
        JPanel healthBarPanel = new JPanel(new BorderLayout());
        healthBarPanel.setPreferredSize(new Dimension(400, 35));
        healthBarPanel.setMaximumSize(new Dimension(600, 35));
        healthBarPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        healthBarPanel.setBackground(Color.WHITE);
        
        JProgressBar playerHealthBar = battleScreen.getPlayerHealthBar();
        
        healthBarPanel.add(playerHealthBar, BorderLayout.CENTER);
        actionPanel.add(healthBarPanel);

        // Small space at bottom
        actionPanel.add(Box.createVerticalStrut(3));
        
        return actionPanel;
    }

/**
    This method executes a complete turn including player action,
    opponent AI decision, and battle resolution.
    
    @param playerAction - the action chosen by the player (attack/defend/charge)
*/
    public void executeTurn(String playerAction) {
        if (player.getHP() <= 0 || opponent.getHP() <= 0) {
            return;
        }
		int playerStoredAtk = player.getAtk();
		int oppStoredAtk = opponent.getAtk();

        updateBattleLog("\n           ~~~ NEW TURN ~~~\n");

        // Get opponent action using faux-AI
        String opponentAction = opponent.think();
        updateBattleLog("Opponent chooses: " + opponentAction.toUpperCase() + "\n");

        updateOpponentPortraitByAction(opponentAction);

        // Let BattleSystem handle everything including environment effects
        executeAdvancedTurn(playerAction, opponentAction, playerStoredAtk, oppStoredAtk);
        
        // Reset status for both characters after turn
        player.resetStatus(playerAction);
        opponent.resetStatus(opponentAction);

        updateStats();

        // Check for battle end
        if (player.getHP() <= 0 || opponent.getHP() <= 0) {
            endBattle();
        }
    }

/**
    This method handles the advanced turn execution including environment
    effects, turn order determination, and battle system integration.
    
    @param playerAction - the action chosen by the player
    @param opponentAction - the action chosen by the opponent AI
*/
    private void executeAdvancedTurn(String playerAction, String opponentAction, int playerStoredAtk, int oppStoredAtk) {
        // Capture BattleSystem output to display in GUI
        java.io.ByteArrayOutputStream capturedOutput = new java.io.ByteArrayOutputStream();
        java.io.PrintStream originalOut = System.out;
        System.setOut(new java.io.PrintStream(capturedOutput));
        
        if(environment.getEnvironmentName().equals("Arena")){
            System.out.println("No Environment Effect!");
        }
        else{
            environment.applyPlayerEffect(player);
            environment.applyOpponentEffect(opponent);
        }

        // Execute turn order logic (defender goes first, then speed comparison)
        if (playerAction.equals("defend")) {
            battleSystem.executePlayerAction(playerAction, playerStoredAtk);
            battleSystem.executeOpponentAction(opponentAction, oppStoredAtk);
        }
        else if (opponentAction.equals("defend")) {
            battleSystem.executeOpponentAction(opponentAction, oppStoredAtk);
            battleSystem.executePlayerAction(playerAction, playerStoredAtk);
        }
        else{
            if (player.getSpd() >= opponent.getSpd()) {
                System.out.println("\nYou are faster! You execute your action first!");
                battleSystem.executePlayerAction(playerAction, playerStoredAtk);
                if (opponent.getHP() > 0) battleSystem.executeOpponentAction(opponentAction, oppStoredAtk);
            }
            else {
                System.out.println("\nYour opponent is faster! They execute their action first!");
                battleSystem.executeOpponentAction(opponentAction, oppStoredAtk);
                if (player.getHP() > 0) battleSystem.executePlayerAction(playerAction, playerStoredAtk);
            }
        }
        
        System.setOut(originalOut);
        String battleMessages = capturedOutput.toString();
        
        if (!battleMessages.trim().isEmpty()) {
            updateBattleLog(battleMessages.trim());
        }
    }

/**
    This method handles the end of battle, displaying victory/defeat
    messages and replacing action buttons with new game option.
*/
    private void endBattle() {
        updateBattleLog("\n         === BATTLE ENDED ===\n");
        
        // Add visual victory/defeat effects
        if (player.getHP() <= 0) {
            updateBattleLog("DEFEAT! You have been knocked out!\n");
            updateBattleLog("The " + opponent.getName() + " stands victorious...");
            updateOpponentPortraitByResult(true);
            
            // Turn player health bar red and add defeat effect
            battleScreen.getPlayerHealthBar().setForeground(Color.RED);
            battleScreen.getPlayerHealthBar().setString("DEFEATED");
            
        } 
        else if (opponent.getHP() <= 0) {
            updateBattleLog("VICTORY! You have defeated the " + opponent.getName() + "!\n");
            updateBattleLog("You emerge triumphant from the " + environment.getEnvironmentName() + "!");
            updateOpponentPortraitByResult(false);
            
            // Turn opponent health bar red and add victory effect
            battleScreen.getOpponentHealthBar().setForeground(Color.RED);
            battleScreen.getOpponentHealthBar().setString("DEFEATED");
            
            // Add golden border to player health bar for victory
            battleScreen.getPlayerHealthBar().setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        }

        // Get the action panel in the new layout and replace with new game button
        JPanel battlePanel = (JPanel) cardPanel.getComponent(4);        // Battle screen
        JPanel mainBattleArea = (JPanel) battlePanel.getComponent(1);   // Main battle area
        JPanel actionPanel = (JPanel) mainBattleArea.getComponent(2);   // Action panel (SOUTH)
        actionPanel.removeAll();

        // Create a wrapper panel to center the button
        JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonWrapper.setBackground(Color.WHITE);

        JButton newGameButton = new JButton("NEW GAME");
        newGameButton.setFont(new Font("Arial", Font.BOLD, 16));
        newGameButton.setPreferredSize(new Dimension(200, 50));
        newGameButton.setBackground(Color.GREEN);
        newGameButton.setForeground(Color.WHITE);
        newGameButton.addActionListener(e -> {
            // Reset everything and go back to main menu
            player.setCharging(false);
            player.setDefending(false);
            opponent.setCharging(false);
            opponent.setDefending(false);
            battleScreen.getBattleLog().setText("");
            
            // Reset health bar appearances
            battleScreen.getPlayerHealthBar().setBorder(null);
            battleScreen.getOpponentHealthBar().setBorder(null);
            
            cardLayout.show(cardPanel, "main");
        });

        buttonWrapper.add(newGameButton);
        actionPanel.add(buttonWrapper);
        actionPanel.revalidate();
        actionPanel.repaint();
    }

/**
    This method updates all character stats displays including health bars,
    attack/defense/speed values, and charging status indicators.
*/
    private void updateStats() {
        // Update player health bar
        int playerCurrentHP = player.getHP();
        int playerHealthPercent = (int)((double)playerCurrentHP / playerMaxHP * 100);
        battleScreen.getPlayerHealthBar().setValue(playerHealthPercent);
        battleScreen.getPlayerHealthBar().setString(playerCurrentHP + "/" + playerMaxHP);
        
        // Change health bar color based on health percentage
        if (playerHealthPercent > 60) {
            battleScreen.getPlayerHealthBar().setForeground(Color.GREEN);
        } 
        else if (playerHealthPercent > 30) {
            battleScreen.getPlayerHealthBar().setForeground(Color.ORANGE);
        } 
        else {
            battleScreen.getPlayerHealthBar().setForeground(Color.RED);
        }
        
        // Update opponent health bar
        int opponentCurrentHP = opponent.getHP();
        int opponentHealthPercent = (int)((double)opponentCurrentHP / opponentMaxHP * 100);
        battleScreen.getOpponentHealthBar().setValue(opponentHealthPercent);
        battleScreen.getOpponentHealthBar().setString(opponentCurrentHP + "/" + opponentMaxHP);
        
        // Change opponent health bar color
        if (opponentHealthPercent > 60) {
            battleScreen.getOpponentHealthBar().setForeground(Color.GREEN);
        } 
        else if (opponentHealthPercent > 30) {
            battleScreen.getOpponentHealthBar().setForeground(Color.ORANGE);
        } 
        else {
            battleScreen.getOpponentHealthBar().setForeground(Color.RED);
        }

        // Update player stats in sidebar with charging indicator
        StringBuilder playerStats = new StringBuilder("<html><b>Player:</b><br>");
        playerStats.append("ATK: ").append(player.getAtk()).append("<br>");
        playerStats.append("DEF: ").append(player.getDef()).append("<br>");
        playerStats.append("SPD: ").append(player.getSpd());
        if (player.getCharging()) {
            playerStats.append("<br><font color='red'><b>CHARGED!</b></font>");
            // Pulsing effect on health bar when charging (Player)
            battleScreen.getPlayerHealthBar().setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        } 
        else {
            battleScreen.getPlayerHealthBar().setBorder(null);
        }
        playerStats.append("</html>");
        battleScreen.getPlayerStatsLabel().setText(playerStats.toString());

        // Update opponent stats in sidebar with charging indicator
        StringBuilder oppStats = new StringBuilder("<html><b>").append(opponent.getName()).append(":</b><br>");
        oppStats.append("ATK: ").append(opponent.getAtk()).append("<br>");
        oppStats.append("DEF: ").append(opponent.getDef()).append("<br>");
        oppStats.append("SPD: ").append(opponent.getSpd());
        if (opponent.getCharging()) {
            oppStats.append("<br><font color='red'><b>CHARGED!</b></font>");
            // Pulsing effect on health bar when charging (Opponent)
            battleScreen.getOpponentHealthBar().setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        } 
        else {
            battleScreen.getOpponentHealthBar().setBorder(null);
        }
        oppStats.append("</html>");
        battleScreen.getOpponentStatsLabel().setText(oppStats.toString());

        // Update environment label
        updateEnvironmentInfo();
        
        // Animation effect for health bars
        Timer timer = new Timer(50, null);
        timer.addActionListener(e -> {
            battleScreen.getPlayerHealthBar().repaint();
            battleScreen.getOpponentHealthBar().repaint();
            timer.stop();
        });
        timer.start();
    }

/**
    This method appends a new message to the battle log and
    automatically scrolls to show the latest message.
    
    @param message - the message to add to the battle log
*/
    private void updateBattleLog(String message) {
        battleScreen.getBattleLog().append(message + "\n");
        battleScreen.getBattleLog().setCaretPosition(battleScreen.getBattleLog().getDocument().getLength());
    }

/**
    This method updates the opponent name label with proper
    formatting and styling based on the selected opponent.
*/
    private void updateOpponentLabel() {
        if (opponent != null) {
            String displayName = "";
            switch (opponent.getName()) {
                case "Thief":
                    displayName = "<html><b><font color='red' style='text-shadow: 1px 1px 0px black, -1px -1px 0px black, 1px -1px 0px black, -1px 1px 0px black;'>Blink, the Thief</font></b></html>";
                    break;
                case "Viking":
                    displayName = "<html><b><font color='red' style='text-shadow: 1px 1px 0px black, -1px -1px 0px black, 1px -1px 0px black, -1px 1px 0px black;'>Astrid, the Viking</font></b></html>";
                    break;
                case "Minotaur":
                    displayName = "<html><b><font color='red' style='text-shadow: 1px 1px 0px black, -1px -1px 0px black, 1px -1px 0px black, -1px 1px 0px black;'>Tauros, the Minotaur</font></b></html>";
                    break;
                default:
                    displayName = "OPPONENT";
            }
            battleScreen.getOpponentLabel().setText(displayName);
        }
    }

/**
    This method updates the environment information display
    including name and effect descriptions.
*/
    private void updateEnvironmentInfo() {
        if (environment != null) {
            battleScreen.getEnvironmentLabel().setText("Environment: " + environment.getEnvironmentName());
            
            String effectsText = "";
            switch (environment.getEnvironmentName()) {
                case "Arena":
                    effectsText = "<html><center><i>No Environmental Effects</i></center></html>";
                    break;
                case "Swamp":
                    effectsText = "<html><center>Player: -1 HP/turn<br>Opponent: +1 ATK/turn</center></html>";
                    break;
                case "Colosseum":
                    effectsText = "<html><center>Player: +1 ATK/turn<br>Opponent: -1 DEF/turn</center></html>";
                    break;
            }
            battleScreen.getEnvironmentEffectsLabel().setText(effectsText);
        } else {
            battleScreen.getEnvironmentLabel().setText("Environment: ???");
            battleScreen.getEnvironmentEffectsLabel().setText("<html><center><i>No effects</i></center></html>");
        }
    }

/**
    This method updates the opponent portrait image based on
    the currently selected opponent type.
*/
    private void updateCharacterPortraits() {
        if (opponent != null && battleScreen.getOpponentPortrait() != null) {
            String imageName = "";
            switch (opponent.getName()) {
                case "Thief":
                    imageName = "thief_idle.png";
                    break;
                case "Viking":
                    imageName = "viking_idle.png";
                    break;
                case "Minotaur":
                    imageName = "minotaur_idle.png";
                    break;
                default:
                    imageName = "opponent.png"; 
            }
            
            // Load and scale the new image
            ImageIcon newIcon = new ImageIcon(imageName);
            Image newImageScaled = newIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            battleScreen.getOpponentPortrait().setIcon(new ImageIcon(newImageScaled));
            
            // Refresh the display
            battleScreen.getOpponentPortrait().repaint();
        }
    }

/**
    This method updates the opponent portrait to show different
    images based on their current action.
    
    @param opponentAction - the action the opponent is taking
*/
    private void updateOpponentPortraitByAction(String opponentAction) {
        if (opponent != null && battleScreen.getOpponentPortrait() != null) {
            String imageName = "";
            
            if (opponent.getName().equals("Minotaur")) {
                switch (opponentAction) {
                    case "attack":
                        imageName = "minotaur_attack.png";
                        break;
                    case "charge":
                        imageName = "minotaur_charge.png";
                        break;
                    default:
                        imageName = "minotaur_idle.png"; 
                        break;
                }
            }
            else if (opponent.getName().equals("Viking")) {
                switch (opponentAction) {
                    case "attack":
                        imageName = "viking_attack.png";
                        break;
                    case "defend":
                        imageName = "viking_defend.png";
                        break;
                    default:
                        imageName = "viking_idle.png"; 
                        break;
                }
            }
            else if (opponent.getName().equals("Thief")) {
                switch (opponentAction) {
                    case "attack":
                        imageName = "thief_attack.png";
                        break;
                    default:
                        imageName = "thief_idle.png"; 
                        break;
                }
            }
            else {
                // For any other opponents, don't change portrait
                return;
            }
            
            // Load and scale the new image
            ImageIcon newIcon = new ImageIcon(imageName);
            Image newImageScaled = newIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            battleScreen.getOpponentPortrait().setIcon(new ImageIcon(newImageScaled));
            battleScreen.getOpponentPortrait().repaint();
        }
    }

/**
    This method updates the opponent portrait to show victory
    or defeat images based on battle results.
    
    @param opponentWon - true if opponent won, false if player won
*/
    private void updateOpponentPortraitByResult(boolean opponentWon) {
        if (opponent != null && battleScreen.getOpponentPortrait() != null) {
            String imageName = "";
            String suffix = opponentWon ? "_win.png" : "_lose.png";
            
            switch (opponent.getName()) {
                case "Thief":
                    imageName = "thief" + suffix;
                    break;
                case "Viking":
                    imageName = "viking" + suffix;
                    break;
                case "Minotaur":
                    imageName = "minotaur" + suffix;
                    break;
                default:
                    return; 
            }
            
            // Load and scale the new image
            ImageIcon newIcon = new ImageIcon(imageName);
            Image newImageScaled = newIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            battleScreen.getOpponentPortrait().setIcon(new ImageIcon(newImageScaled));
            battleScreen.getOpponentPortrait().repaint();
        }
    }

/**
    This method updates the weapon ability display showing
    the current weapon and its special abilities.
*/
    private void updateWeaponAbilityDisplay() {
        if (player != null && battleScreen.getWeaponAbilityLabel() != null) {
            String weaponName = playerWeaponName;
            String abilityText = "";
            
            switch (weaponName) {
                case "No Weapon":
                    abilityText = "<html><b>Weapon:</b> No Weapon<br><i>No special ability</i></html>";
                    break;
                case "Dagger":
                    abilityText = "<html><b>Weapon:</b> Dagger<br><i>Guaranteed evade when defending (1 turn cooldown)</i></html>";
                    break;
                case "Sword":
                    abilityText = "<html><b>Weapon:</b> Sword<br><i>+10 attack for attacks</i></html>";
                    break;
                case "Battle Axe":
                    abilityText = "<html><b>Weapon:</b> Battle Axe<br><i>+5 Speed when charged and +5 attack for attacks next turn</i></html>";
                    break;
                default:
                    abilityText = "<html><b>Weapon:</b> " + weaponName + "<br><i>Unknown ability</i></html>";
                    break;
            }
            
            battleScreen.getWeaponAbilityLabel().setText(abilityText);
        }
    }

/**
    This method updates the background image based on the
    selected environment type.
*/
    private void updateBattleBackground() {
        if (environment != null && battleScreen.getBackgroundLabel() != null) {
            String imageName = "";
            
            switch (environment.getEnvironmentName()) {
                case "Arena":
                    imageName = "arena.png";
                    break;
                case "Swamp":
                    imageName = "swamp.png";
                    break;
                case "Colosseum":
                    imageName = "colosseum.png";
                    break;
                default:
                    imageName = "arena.png"; 
                    break;
            }
            
            // Get the actual size of the background label
            int width = battleScreen.getBackgroundLabel().getWidth();
            int height = battleScreen.getBackgroundLabel().getHeight();
            
            // If the component hasn't been rendered yet, use default sizes
            if (width <= 0 || height <= 0) {
                width = 800;  
                height = 500; 
            }
            
            ImageIcon backgroundIcon = new ImageIcon(imageName);
            Image backgroundImageScaled = backgroundIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            battleScreen.getBackgroundLabel().setIcon(new ImageIcon(backgroundImageScaled));
            battleScreen.getBackgroundLabel().repaint();
        }
    }

/**
    This is the main entry point that creates and displays
    the GUI application.
    
    @param args - command line argument
*/
    public static void main(String[] args) {
        new GUI();
    }
}
