import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
/**
   The BattleScreen class represents the user interface for the battle
   system in the game. It displays all relevant information such as
   player and opponent stats, health bars, and the battle log.

   @author Jerico G. Ocampo
*/
public class BattleScreen {
    private JPanel panel;
    private GUI gui;
    private JTextArea battleLog;
    private JLabel playerStatsLabel;
    private JLabel opponentStatsLabel;
    private JProgressBar playerHealthBar;
    private JProgressBar opponentHealthBar;
    private JLabel environmentEffectsLabel;
    private JLabel weaponAbilityLabel;
    private JLabel environmentLabel;
    private JLabel opponentLabel;
    private JLabel opponentPortrait;
    private JLabel backgroundLabel;
    
/**
    This constructor initializes the battle screen with a reference to the GUI
    and creates all battle interface components including health bars, stats,
    battle log, and action buttons.
    
    @param gui - the main GUI object for battle interaction
*/
    public BattleScreen(GUI gui) {
        this.gui = gui;
        createBattleScreen();
    }
    
/**
    This method returns the battle screen panel for display in the GUI.
    
    @return the JPanel containing all battle screen components
*/
    public JPanel getPanel() {
        return panel;
    }
    
/**
    This method returns the battle log text area for message display.
    
    @return the JTextArea containing battle messages and events
*/
    public JTextArea getBattleLog() {
        return battleLog;
    }
    
/**
    This method returns the player stats label for display updates.
    
    @return the JLabel showing player's attack, defense, and speed stats
*/
    public JLabel getPlayerStatsLabel() {
        return playerStatsLabel;
    }
    
/**
    This method returns the opponent stats label for display updates.
    
    @return the JLabel showing opponent's attack, defense, and speed stats
*/
    public JLabel getOpponentStatsLabel() {
        return opponentStatsLabel;
    }
    
/**
    This method returns the player health bar for HP display and updates.
    
    @return the JProgressBar showing player's current health status
*/
    public JProgressBar getPlayerHealthBar() {
        return playerHealthBar;
    }
    
/**
    This method returns the opponent health bar for HP display and updates.
    
    @return the JProgressBar showing opponent's current health status
*/
    public JProgressBar getOpponentHealthBar() {
        return opponentHealthBar;
    }
    
/**
    This method returns the environment effects label for displaying
    environmental battle modifiers and descriptions.
    
    @return the JLabel showing current environment effects
*/
    public JLabel getEnvironmentEffectsLabel() {
        return environmentEffectsLabel;
    }
    
/**
    This method returns the weapon ability label for displaying
    current weapon and its special abilities.
    
    @return the JLabel showing weapon information and abilities
*/
    public JLabel getWeaponAbilityLabel() {
        return weaponAbilityLabel;
    }
    
/**
    This method returns the environment label for displaying
    the current battle environment name.
    
    @return the JLabel showing environment name
*/
    public JLabel getEnvironmentLabel() {
        return environmentLabel;
    }
    
/**
    This method returns the opponent label for displaying
    the opponent's name with formatting.
    
    @return the JLabel showing opponent's display name
*/
    public JLabel getOpponentLabel() {
        return opponentLabel;
    }
    
/**
    This method returns the opponent portrait label for displaying
    and updating opponent images based on actions and results.
    
    @return the JLabel containing opponent portrait images
*/
    public JLabel getOpponentPortrait() {
        return opponentPortrait;
    }
    
/**
    This method returns the background label for displaying
    environment-specific background images.
    
    @return the JLabel containing background images
*/
    public JLabel getBackgroundLabel() {
        return backgroundLabel;
    }
    
/**
    This method creates and sets up the complete battle screen layout
    with left sidebar, main battle area, and all UI components.
*/
    private void createBattleScreen() {
        panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // LEFT PANEL
        JPanel leftSidebar = createLeftSidebarArea();
        panel.add(leftSidebar, BorderLayout.WEST);

        // MAIN PANEL
        JPanel mainBattleArea = new JPanel(new BorderLayout(10, 10));

        // TOP PANEL (Health Bar)
        JPanel topHealthPanel = createTopArea();
        mainBattleArea.add(topHealthPanel, BorderLayout.NORTH);
        
        // CENTER PANEL (Battle Area)
        JPanel centerArea = createCenterArea();
        mainBattleArea.add(centerArea, BorderLayout.CENTER);

        // BOTTOM PANEL (Actions)
        JPanel actionPanel = createActionPanel();
        mainBattleArea.add(actionPanel, BorderLayout.SOUTH);

        panel.add(mainBattleArea, BorderLayout.CENTER);
    }

/**
    This method creates the left sidebar area containing environment info,
    battle log, combat stats, and weapon ability displays.
    
    @return the JPanel containing all sidebar components
*/
    private JPanel createLeftSidebarArea() {
        JPanel sidebar = new JPanel(new BorderLayout(5, 5));
        sidebar.setPreferredSize(new Dimension(300, 0));
        sidebar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Battle Info", 
                TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION)
        ));

        // ENVIRONMENT SECTION (NORTH) - Changed from single label to panel
        JPanel environmentPanel = new JPanel(new BorderLayout(5, 5));
        environmentPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK), "Environment"
        ));
        
        environmentLabel = new JLabel("Environment: ???", SwingConstants.CENTER);
        environmentLabel.setFont(new Font("Arial", Font.BOLD, 14));
        environmentPanel.add(environmentLabel, BorderLayout.NORTH);
        
        // Add environment effects label
        environmentEffectsLabel = new JLabel("<html><center><i>No effects</i></center></html>", SwingConstants.CENTER);
        environmentEffectsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        environmentEffectsLabel.setForeground(Color.GRAY);
        environmentPanel.add(environmentEffectsLabel, BorderLayout.CENTER);
        
        sidebar.add(environmentPanel, BorderLayout.NORTH);

        // BATTLE LOG (CENTER) 
        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK), "Battle Log"
        ));
        
        battleLog = new JTextArea(10, 25); 
        battleLog.setEditable(false);
        battleLog.setFont(new Font("Monospaced", Font.PLAIN, 11));
        battleLog.setLineWrap(true);
        battleLog.setWrapStyleWord(true);

        JScrollPane logScrollPane = new JScrollPane(battleLog);
        logScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        logScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        logPanel.add(logScrollPane, BorderLayout.CENTER);
        
        sidebar.add(logPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        
        // STATS OF PLAYERS (SOUTH-WEST)
        JPanel statsPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        statsPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK), "Combat Stats"
        ));
        
        playerStatsLabel = new JLabel("<html><b>Player:</b><br>ATK: 0<br>DEF: 0<br>SPD: 0</html>"); 
        opponentStatsLabel = new JLabel("<html><b>Opponent:</b><br>ATK: 0<br>DEF: 0<br>SPD: 0</html>");
        
        playerStatsLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        opponentStatsLabel.setFont(new Font("Arial", Font.PLAIN, 11));

        playerStatsLabel.setBackground(Color.WHITE);
        opponentStatsLabel.setBackground(Color.WHITE);

        playerStatsLabel.setOpaque(true);
        opponentStatsLabel.setOpaque(true);
        
        statsPanel.add(playerStatsLabel);
        statsPanel.add(opponentStatsLabel);
        
        bottomPanel.add(statsPanel, BorderLayout.NORTH);
        
        // WEAPON ABILITIES PANEL (SOUTH-WEST)
        JPanel weaponPanel = new JPanel(new BorderLayout());
        weaponPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK), "Weapon Ability"
        ));
        
        weaponAbilityLabel = new JLabel("<html><b>Weapon:</b> No Weapon<br><i>No special ability</i></html>");
        weaponAbilityLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        weaponAbilityLabel.setVerticalAlignment(SwingConstants.TOP);
        weaponAbilityLabel.setBackground(Color.WHITE);
        weaponAbilityLabel.setOpaque(true);
        
        weaponPanel.add(weaponAbilityLabel, BorderLayout.CENTER);
        
        bottomPanel.add(weaponPanel, BorderLayout.SOUTH);
        
        sidebar.add(bottomPanel, BorderLayout.SOUTH);
        
        return sidebar;
    }

/**
    This method creates the top area of the battle screen containing
    the opponent's health bar and name display.
    
    @return the JPanel containing opponent health information
*/
    private JPanel createTopArea() {
        JPanel healthPanel = new JPanel(new BorderLayout(5, 10));
        healthPanel.setPreferredSize(new Dimension(0, 80));

        // OPPONENT HEALTH BAR (Full width)
        JPanel opponentHealthPanel = new JPanel(new BorderLayout(5, 5));
        opponentLabel = new JLabel("OPPONENT", SwingConstants.CENTER);
        opponentLabel.setFont(new Font("Arial", Font.BOLD, 16));
        opponentHealthPanel.add(opponentLabel, BorderLayout.NORTH);
        
        opponentHealthBar = new JProgressBar(0, 100);
        opponentHealthBar.setStringPainted(true);
        opponentHealthBar.setString("100/100");
        opponentHealthBar.setForeground(Color.RED);
        opponentHealthBar.setValue(100);
        opponentHealthBar.setPreferredSize(new Dimension(0, 35));
        opponentHealthBar.setFont(new Font("Arial", Font.BOLD, 14));
        opponentHealthPanel.add(opponentHealthBar, BorderLayout.CENTER);
        
        healthPanel.add(opponentHealthPanel, BorderLayout.CENTER);

        return healthPanel;
    }

/**
    This method creates the center battle area with background image
    and opponent portrait positioning.
    
    @return the JPanel containing the main battle visualization
*/
    private JPanel createCenterArea() {
        JPanel showdownPanel = new JPanel(new BorderLayout());
        showdownPanel.setBackground(Color.WHITE);
        showdownPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Background label that fills the entire panel
        backgroundLabel = new JLabel();
        backgroundLabel.setLayout(new BorderLayout()); 
        backgroundLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundLabel.setVerticalAlignment(SwingConstants.CENTER);

        // Opponent portrait panel
        JPanel opponentPortraitPanel = new JPanel(new BorderLayout());
        opponentPortraitPanel.setOpaque(false); 

        ImageIcon opponentIcon = new ImageIcon("opponent.png");
        Image opponentImageScaled = opponentIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        opponentPortrait = new JLabel(new ImageIcon(opponentImageScaled));
        opponentPortraitPanel.add(opponentPortrait, BorderLayout.CENTER);

        // Add an empty panel at the top to push portrait down
        JPanel spacerPanel = new JPanel();
        spacerPanel.setOpaque(false);
        spacerPanel.setPreferredSize(new Dimension(0, 400)); // Adjust height (more height = lower))
        backgroundLabel.add(spacerPanel, BorderLayout.NORTH);
        backgroundLabel.add(opponentPortraitPanel, BorderLayout.CENTER);
        backgroundLabel.add(opponentPortraitPanel, BorderLayout.CENTER);
        
        showdownPanel.add(backgroundLabel, BorderLayout.CENTER);

        return showdownPanel;
    }

/**
    This method creates the action panel at the bottom containing
    attack/defend/charge buttons and player health bar.
    
    @return the JPanel containing player action controls
*/
    private JPanel createActionPanel() {
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

        attackButton.addActionListener(e -> gui.executeTurn("attack"));
        defendButton.addActionListener(e -> gui.executeTurn("defend"));
        chargeButton.addActionListener(e -> gui.executeTurn("charge"));

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
        
        playerHealthBar = new JProgressBar(0, 100);
        playerHealthBar.setStringPainted(true);
        playerHealthBar.setString("100/100");
        playerHealthBar.setForeground(Color.GREEN);
        playerHealthBar.setValue(100); 
        playerHealthBar.setFont(new Font("Arial", Font.BOLD, 16));
        
        healthBarPanel.add(playerHealthBar, BorderLayout.CENTER);
        actionPanel.add(healthBarPanel);

        // Small space at bottom
        actionPanel.add(Box.createVerticalStrut(3));
        
        return actionPanel;
    }
}
