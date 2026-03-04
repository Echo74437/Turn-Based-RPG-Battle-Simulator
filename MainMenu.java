import javax.swing.*;
import java.awt.*;
/**
   The MainMenu class represents the main menu of the game.
   It initializes the main menu panel with a title and a start button.

   @author Jerico G. Ocampo
*/
public class MainMenu {
    private JPanel panel;
    private GUI gui;
    
/**
    This constructor initializes the main menu with a reference to the GUI
    and creates the main menu panel with all its components.
    
    @param gui - the main GUI object for navigation between screens
*/
    public MainMenu(GUI gui) {
        this.gui = gui;
        createMainMenu();
    }
    
/**
    This method returns the main menu panel for display in the GUI.
    
    @return the JPanel containing the main menu components
*/
    public JPanel getPanel() {
        return panel;
    }
    
/**
    This method creates and sets up the main menu panel with title,
    start button, and proper layout using GridBagLayout for centering.
*/
    private void createMainMenu() {
        panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create a container for centered content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("WARRIOR GAME");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = new JButton("Start Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(e -> {
            gui.setPlayer(new Warrior());
            gui.showCard("character");
        });
        startButton.setFocusPainted(false);

        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(30)); 
        contentPanel.add(startButton);

        // Center the content panel in the main menu
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(contentPanel, gbc);
    }
}
