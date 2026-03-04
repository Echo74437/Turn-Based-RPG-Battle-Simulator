import javax.swing.*;
import java.awt.*;
/**
   The EnvironmentSelection class represents the user interface for selecting
   the battle environment in the game. It displays all relevant information such as
   environment options, descriptions, and images.

   @author Jerico G. Ocampo
*/
public class EnvironmentSelection {
    private JPanel panel;
    private GUI gui;
    
/**
    This constructor initializes the environment selection screen with a reference 
    to the GUI and creates the environment selection interface.
    
    @param gui - the main GUI object for navigation and environment management
*/
    public EnvironmentSelection(GUI gui) {
        this.gui = gui;
        createEnvironmentSelection();
    }
    
/**
    This method returns the environment selection panel for display in the GUI.
    
    @return the JPanel containing the environment selection components
*/
    public JPanel getPanel() {
        return panel;
    }
    
/**
    This method creates and sets up the environment selection panel with three
    environment choices (Arena, Swamp, Colosseum), including their images, 
    effect descriptions, radio button selection, and battle start functionality.
*/
    private void createEnvironmentSelection() {
        panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // CENTER PANEL
        JPanel centerContainer = new JPanel(new BorderLayout(10, 15));
        centerContainer.setPreferredSize(new Dimension(800, 400)); 
        centerContainer.setBorder(BorderFactory.createRaisedBevelBorder());

        // TITLE
        JLabel titleLabel = new JLabel("=== SELECT ENVIRONMENT ===", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        centerContainer.add(titleLabel, BorderLayout.NORTH);

        // ENVIRONMENT SELECTION - Changed to horizontal layout
        JPanel selectionPanel = new JPanel(new GridLayout(1, 3, 20, 10)); // 1 row, 3 columns
        selectionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        ButtonGroup envGroup = new ButtonGroup();

        // ARENA PANEL
        JPanel arenaPanel = new JPanel(new BorderLayout(5, 10));
        arenaPanel.setBorder(BorderFactory.createEtchedBorder());
        
        // Arena image
        ImageIcon arenaIcon = new ImageIcon("arena.png");
        Image arenaImageScaled = arenaIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel arenaImage = new JLabel(new ImageIcon(arenaImageScaled));
        arenaImage.setHorizontalAlignment(SwingConstants.CENTER);
        arenaPanel.add(arenaImage, BorderLayout.CENTER);
        
        // Arena description
        JLabel arenaDesc = new JLabel("<html><center><b>ARENA</b><br><br>No penalties<br>Goodluck<br><br>A fair battlefield for honorable combat</center></html>");
        arenaDesc.setFont(new Font("Arial", Font.PLAIN, 12));
        arenaDesc.setHorizontalAlignment(SwingConstants.CENTER);
        arenaPanel.add(arenaDesc, BorderLayout.SOUTH);
        
        JRadioButton arena = new JRadioButton();
        arena.setHorizontalAlignment(SwingConstants.CENTER);
        arenaPanel.add(arena, BorderLayout.NORTH);

        // SWAMP PANEL
        JPanel swampPanel = new JPanel(new BorderLayout(5, 10));
        swampPanel.setBorder(BorderFactory.createEtchedBorder());
        
        // Swamp image
        ImageIcon swampIcon = new ImageIcon("swamp.png");
        Image swampImageScaled = swampIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel swampImage = new JLabel(new ImageIcon(swampImageScaled));
        swampImage.setHorizontalAlignment(SwingConstants.CENTER);
        swampPanel.add(swampImage, BorderLayout.CENTER);
        
        // Swamp description
        JLabel swampDesc = new JLabel("<html><center><b>SWAMP</b><br><br>Player takes 1 damage/turn<br>Opponent gains 1 atk/turn<br><br>Toxic marshland</center></html>");
        swampDesc.setFont(new Font("Arial", Font.PLAIN, 12));
        swampDesc.setHorizontalAlignment(SwingConstants.CENTER);
        swampPanel.add(swampDesc, BorderLayout.SOUTH);
        
        JRadioButton swamp = new JRadioButton();
        swamp.setHorizontalAlignment(SwingConstants.CENTER);
        swampPanel.add(swamp, BorderLayout.NORTH);

        // COLOSSEUM PANEL
        JPanel colosseumPanel = new JPanel(new BorderLayout(5, 10));
        colosseumPanel.setBorder(BorderFactory.createEtchedBorder());
        
        // Colosseum image
        ImageIcon colosseumIcon = new ImageIcon("colosseum.png");
        Image colosseumImageScaled = colosseumIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel colosseumImage = new JLabel(new ImageIcon(colosseumImageScaled));
        colosseumImage.setHorizontalAlignment(SwingConstants.CENTER);
        colosseumPanel.add(colosseumImage, BorderLayout.CENTER);
        
        // Colosseum description
        JLabel colosseumDesc = new JLabel("<html><center><b>COLOSSEUM</b><br><br>Player gains 1 atk/turn<br>Opponent loses 1 def/turn<br><br>Gladiator arena</center></html>");
        colosseumDesc.setFont(new Font("Arial", Font.PLAIN, 12));
        colosseumDesc.setHorizontalAlignment(SwingConstants.CENTER);
        colosseumPanel.add(colosseumDesc, BorderLayout.SOUTH);
        
        JRadioButton colosseum = new JRadioButton();
        colosseum.setHorizontalAlignment(SwingConstants.CENTER);
        colosseumPanel.add(colosseum, BorderLayout.NORTH);

        // Add radio buttons to group
        envGroup.add(arena);
        envGroup.add(swamp);
        envGroup.add(colosseum);

        // Add panels to selection panel
        selectionPanel.add(arenaPanel);
        selectionPanel.add(swampPanel);
        selectionPanel.add(colosseumPanel);

        centerContainer.add(selectionPanel, BorderLayout.CENTER);

        JButton startBattleButton = new JButton("Start Battle");
        startBattleButton.setPreferredSize(new Dimension(120, 35));
        startBattleButton.addActionListener(e -> {
            // Validate that an environment is selected
            if (!arena.isSelected() && !swamp.isSelected() && !colosseum.isSelected()) {
                JOptionPane.showMessageDialog(gui, "Please select an environment!", 
                                            "Selection Required", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (arena.isSelected()) {
                gui.setEnvironment(new Environment("Arena"));
            } 
            else if (swamp.isSelected()) {
                gui.setEnvironment(new Environment("Swamp"));
            } 
            else if (colosseum.isSelected()) {
                gui.setEnvironment(new Environment("Colosseum"));
            }
            gui.initializeBattle();
            gui.showCard("battle");
        });
        startBattleButton.setFocusPainted(false);

        centerContainer.add(startBattleButton, BorderLayout.SOUTH);

        // Center the container in the main panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(centerContainer, gbc);
    }
}
