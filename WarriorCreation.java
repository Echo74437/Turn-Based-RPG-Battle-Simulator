import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
   The WarriorCreation class creates the character creation screen
   in the game. It allows players to select their armor and weapon,
   preview their stats, and confirm their choices.

   @author Jerico G. Ocampo
*/
public class WarriorCreation {
    private JPanel panel;
    private GUI gui;
    
/**
    This constructor initializes the character creation screen with a reference 
    to the GUI and creates all equipment selection components.
    
    @param gui - the main GUI object for navigation and player management
*/
    public WarriorCreation(GUI gui) {
        this.gui = gui;
        createWarriorCreation();
    }
    
/**
    This method returns the character creation panel for display in the GUI.
    
    @return the JPanel containing the character creation components
*/
    public JPanel getPanel() {
        return panel;
    }
    
/**
    This method creates and sets up the character creation panel with armor
    selection, weapon selection, stats preview, and continue functionality.
    Includes radio buttons for equipment choices and real-time stat calculation.
*/
    private void createWarriorCreation() {
        panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // TITLE
        JLabel titleLabel = new JLabel("=== CHARACTER CREATION ===", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);

        // EQUIPMENT SELECTION
        JPanel selectionPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        // ARMOR SELECTION
        JPanel armorPanel = new JPanel(new BorderLayout());
        armorPanel.setBorder(BorderFactory.createTitledBorder("Choose your armor"));
        ButtonGroup armorGroup = new ButtonGroup();
        JPanel armorButtons = new JPanel(new GridLayout(4, 1));
        JRadioButton noArmor = new JRadioButton("No Armor");
        JRadioButton lightArmor = new JRadioButton("Light Armor (+20 Def, -5 Spd)");
        JRadioButton mediumArmor = new JRadioButton("Medium Armor (+30 Def, -15 Spd)");
        JRadioButton heavyArmor = new JRadioButton("Heavy Armor (+40 Def, -25 Spd)");

        armorGroup.add(noArmor);
        armorGroup.add(lightArmor);
        armorGroup.add(mediumArmor);
        armorGroup.add(heavyArmor);

        armorButtons.add(noArmor);
        armorButtons.add(lightArmor);
        armorButtons.add(mediumArmor);
        armorButtons.add(heavyArmor);

        // Default selected
        noArmor.setSelected(true);
        
        armorPanel.add(armorButtons, BorderLayout.CENTER);

        // WEAPON SELECTION
        JPanel weaponPanel = new JPanel(new BorderLayout());
        weaponPanel.setBorder(BorderFactory.createTitledBorder("Choose your weapon"));
        ButtonGroup weaponGroup = new ButtonGroup();
        JPanel weaponButtons = new JPanel(new GridLayout(4, 1));

        JRadioButton noWeapon = new JRadioButton("No Weapon");
        JRadioButton dagger = new JRadioButton("Dagger (+20 Atk, 0 Spd)");
        JRadioButton sword = new JRadioButton("Sword (+30 Atk, -10 Spd)");
        JRadioButton battleAxe = new JRadioButton("Battle Axe (+40 Atk, -20 Spd)");

        weaponGroup.add(noWeapon);
        weaponGroup.add(dagger);
        weaponGroup.add(sword);
        weaponGroup.add(battleAxe);

        weaponButtons.add(noWeapon);
        weaponButtons.add(dagger);
        weaponButtons.add(sword);
        weaponButtons.add(battleAxe);

        // Default selected
        noWeapon.setSelected(true);

        weaponPanel.add(weaponButtons, BorderLayout.CENTER);

        selectionPanel.add(armorPanel);
        selectionPanel.add(weaponPanel);

        panel.add(selectionPanel, BorderLayout.CENTER);

        // STATS AND CONTINUE BUTTON
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JTextArea statsArea = new JTextArea(5, 40);
        statsArea.setEditable(false);
        statsArea.setText("Your current stats will appear here");
        bottomPanel.add(new JScrollPane(statsArea), BorderLayout.CENTER);

        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(e -> {
            // Validate that at least armor and weapon are selected 
            boolean armorSelected = noArmor.isSelected() || lightArmor.isSelected() || 
                                  mediumArmor.isSelected() || heavyArmor.isSelected();
            boolean weaponSelected = noWeapon.isSelected() || dagger.isSelected() || 
                                   sword.isSelected() || battleAxe.isSelected();
            
            if (!armorSelected || !weaponSelected) {
                JOptionPane.showMessageDialog(gui, "Please select both armor and weapon options!", 
                                            "Selection Required", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Process armor selection
            if (lightArmor.isSelected()) {
                gui.getPlayer().equipArmor(new Light());
            } 
            else if (mediumArmor.isSelected()) {
                gui.getPlayer().equipArmor(new Medium());
            } 
            else if (heavyArmor.isSelected()) {
                gui.getPlayer().equipArmor(new Heavy());
            }
            else {
                // If no armor is selected, don't equip any armor (keep base stats)
                // The warrior already has base defense and speed
            }

            // Process weapon selection
            if (dagger.isSelected()) {
                gui.getPlayer().equipWeapon(new Dagger());
                gui.setPlayerWeaponName("Dagger");
            } 
            else if (sword.isSelected()) {
                gui.getPlayer().equipWeapon(new Sword());
                gui.setPlayerWeaponName("Sword");
            } 
            else if (battleAxe.isSelected()) {
                gui.getPlayer().equipWeapon(new BattleAxe());
                gui.setPlayerWeaponName("Battle Axe");
            }
            else {
                // If no weapon is selected, don't equip any weapon
                gui.setPlayerWeaponName("No Weapon");
            }

            gui.showCard("opponent");
        });
        continueButton.setFocusPainted(false);

        bottomPanel.add(continueButton, BorderLayout.SOUTH);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        ActionListener updateStats = e -> {
            // Calculate stats manually based on base warrior stats + equipment bonuses
            int baseHP = 100;
            int baseAtk = 1;  
            int baseDef = 1;
            int baseSpd = 50;
            
            // Calculate equipment bonuses
            int armorDef = 0, armorSpd = 0;
            int weaponAtk = 0, weaponSpd = 0;
            
            // Apply armor bonuses
            if (lightArmor.isSelected()) {
                armorDef = 20; 
                armorSpd = -5;
            } 
            else if (mediumArmor.isSelected()) {
                armorDef = 30; 
                armorSpd = -15;
            } 
            else if (heavyArmor.isSelected()) {
                armorDef = 40; 
                armorSpd = -25;
            }
            // noArmor selected = no bonuses (0, 0)
            
            // Apply weapon bonuses  
            if (dagger.isSelected()) {
                weaponAtk = 20; 
                weaponSpd = 0;
            } 
            else if (sword.isSelected()) {
                weaponAtk = 30; 
                weaponSpd = -10;
            } 
            else if (battleAxe.isSelected()) {
                weaponAtk = 40; 
                weaponSpd = -20;
            }
            // noWeapon selected = no bonuses (0, 0)
            
            // Calculate final stats
            int finalHP = baseHP;
            int finalAtk = baseAtk + weaponAtk;
            int finalDef = baseDef + armorDef;
            int finalSpd = baseSpd + armorSpd + weaponSpd;
            
            StringBuilder stats = new StringBuilder("=== YOUR CURRENT STATS ===\n");
            stats.append(String.format("Health Points: %d\n", finalHP));
            stats.append(String.format("Attack Power: %d\n", finalAtk));
            stats.append(String.format("Defense Power: %d\n", finalDef));
            stats.append(String.format("Speed Points: %d\n", finalSpd));
            statsArea.setText(stats.toString());
        };

        // Listeners for radio buttons
        noArmor.addActionListener(updateStats);
        lightArmor.addActionListener(updateStats);
        mediumArmor.addActionListener(updateStats);
        heavyArmor.addActionListener(updateStats);
        noWeapon.addActionListener(updateStats);
        dagger.addActionListener(updateStats);
        sword.addActionListener(updateStats);
        battleAxe.addActionListener(updateStats);

        updateStats.actionPerformed(null);
    }
}
