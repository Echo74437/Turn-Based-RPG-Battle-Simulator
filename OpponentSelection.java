import javax.swing.*;
import java.awt.*;
/**
   The OpponentSelection class creates the opponent selection screen
   in the game. It allows players to choose between three opponents:
   Thief, Viking, and Minotaur.

   @author Jerico G. Ocampo
*/
public class OpponentSelection {
    private JPanel panel;
    private GUI gui;
    
/**
    This constructor initializes the opponent selection screen with a reference 
    to the GUI and creates the opponent selection interface.
    
    @param gui - the main GUI object for navigation and opponent management
*/
    public OpponentSelection(GUI gui) {
        this.gui = gui;
        createOpponentSelection();
    }
    
/**
    This method returns the opponent selection panel for display in the GUI.
    
    @return the JPanel containing the opponent selection components
*/
    public JPanel getPanel() {
        return panel;
    }
    
/**
    This method creates and sets up the opponent selection panel with three
    opponent choices (Thief, Viking, Minotaur), including their images, stats,
    radio button selection, and continue functionality.
*/
    private void createOpponentSelection() {
        panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // CENTER PANEL
        JPanel centerContainer = new JPanel(new BorderLayout(10, 15));
        centerContainer.setPreferredSize(new Dimension(800, 400)); 
        centerContainer.setBorder(BorderFactory.createRaisedBevelBorder());

        // TITLE
        JLabel titleLabel = new JLabel("=== SELECT OPPONENT ===", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        centerContainer.add(titleLabel, BorderLayout.NORTH);

        // OPPONENT SELECTION
        JPanel selectionPanel = new JPanel(new GridLayout(1, 3, 20, 10)); // 1 row, 3 columns
        selectionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        ButtonGroup opponentGroup = new ButtonGroup();

        // THIEF PANEL
        JPanel thiefPanel = new JPanel(new BorderLayout(5, 10));
        thiefPanel.setBorder(BorderFactory.createEtchedBorder());
        
        // Thief image
        ImageIcon thiefIcon = new ImageIcon("thief_idle.png");
        Image thiefImageScaled = thiefIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel thiefImage = new JLabel(new ImageIcon(thiefImageScaled));
        thiefImage.setHorizontalAlignment(SwingConstants.CENTER);
        thiefPanel.add(thiefImage, BorderLayout.CENTER);
        
        // Thief stats
        JLabel thiefStats = new JLabel("<html><center><b>THIEF</b><br><br>HP: 150<br>ATK: 20<br>DEF: 20<br>SPD: 40</center></html>");
        thiefStats.setFont(new Font("Arial", Font.PLAIN, 12));
        thiefStats.setHorizontalAlignment(SwingConstants.CENTER);
        thiefPanel.add(thiefStats, BorderLayout.SOUTH);
        
        JRadioButton thief = new JRadioButton();
        thief.setHorizontalAlignment(SwingConstants.CENTER);
        thiefPanel.add(thief, BorderLayout.NORTH);

        // VIKING PANEL
        JPanel vikingPanel = new JPanel(new BorderLayout(5, 10));
        vikingPanel.setBorder(BorderFactory.createEtchedBorder());
        
        // Viking image
        ImageIcon vikingIcon = new ImageIcon("viking_idle.png");
        Image vikingImageScaled = vikingIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel vikingImage = new JLabel(new ImageIcon(vikingImageScaled));
        vikingImage.setHorizontalAlignment(SwingConstants.CENTER);
        vikingPanel.add(vikingImage, BorderLayout.CENTER);
        
        // Viking stats
        JLabel vikingStats = new JLabel("<html><center><b>VIKING</b><br><br>HP: 250<br>ATK: 30<br>DEF: 30<br>SPD: 30</center></html>");
        vikingStats.setFont(new Font("Arial", Font.PLAIN, 12));
        vikingStats.setHorizontalAlignment(SwingConstants.CENTER);
        vikingPanel.add(vikingStats, BorderLayout.SOUTH);
        
        JRadioButton viking = new JRadioButton();
        viking.setHorizontalAlignment(SwingConstants.CENTER);
        vikingPanel.add(viking, BorderLayout.NORTH);

        // MINOTAUR PANEL
        JPanel minotaurPanel = new JPanel(new BorderLayout(5, 10));
        minotaurPanel.setBorder(BorderFactory.createEtchedBorder());
        
        // Minotaur image
        ImageIcon minotaurIcon = new ImageIcon("minotaur_idle.png");
        Image minotaurImageScaled = minotaurIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel minotaurImage = new JLabel(new ImageIcon(minotaurImageScaled));
        minotaurImage.setHorizontalAlignment(SwingConstants.CENTER);
        minotaurPanel.add(minotaurImage, BorderLayout.CENTER);
        
        // Minotaur stats
        JLabel minotaurStats = new JLabel("<html><center><b>MINOTAUR</b><br><br>HP: 350<br>ATK: 40<br>DEF: 40<br>SPD: 20</center></html>");
        minotaurStats.setFont(new Font("Arial", Font.PLAIN, 12));
        minotaurStats.setHorizontalAlignment(SwingConstants.CENTER);
        minotaurPanel.add(minotaurStats, BorderLayout.SOUTH);
        
        JRadioButton minotaur = new JRadioButton();
        minotaur.setHorizontalAlignment(SwingConstants.CENTER);
        minotaurPanel.add(minotaur, BorderLayout.NORTH);

        // Add radio buttons to group
        opponentGroup.add(thief);
        opponentGroup.add(viking);
        opponentGroup.add(minotaur);

        // Add panels to selection panel
        selectionPanel.add(thiefPanel);
        selectionPanel.add(vikingPanel);
        selectionPanel.add(minotaurPanel);

        centerContainer.add(selectionPanel, BorderLayout.CENTER);

        JButton continueButton = new JButton("Continue");
        continueButton.setPreferredSize(new Dimension(120, 35));
        continueButton.addActionListener(e -> {
            // Validate that an opponent is selected
            if (!thief.isSelected() && !viking.isSelected() && !minotaur.isSelected()) {
                JOptionPane.showMessageDialog(gui, "Please select an opponent!", 
                                            "Selection Required", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (thief.isSelected()) {
                gui.setOpponent(new Opponent("Thief", 150, 20, 20, 40));
            } 
            else if (viking.isSelected()) {
                gui.setOpponent(new Opponent("Viking", 250, 30, 30, 30));
            } 
            else if (minotaur.isSelected()) {
                gui.setOpponent(new Opponent("Minotaur", 350, 40, 40, 20));
            }
            gui.showCard("environment");
        });
        continueButton.setFocusPainted(false);

        centerContainer.add(continueButton, BorderLayout.SOUTH);

        // Center the container in the main panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(centerContainer, gbc);
    }
}
