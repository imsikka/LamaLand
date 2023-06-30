package suLama;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.Font;

public class LoadingScreen extends JFrame {
    private ImageIcon backgroundImage;

    public LoadingScreen() {
        // Set the size of the JFrame
        setSize(1920, 1080);

        // Load the background image
        backgroundImage = new ImageIcon("suLama/Mountain-Dusk.png");

        // Set up a custom JPanel for drawing
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);

                // Display text using the TTF font
                drawText(g, "LAMALAND");
            }
        };

        // Set the layout manager for the panel
        panel.setLayout(new BorderLayout());

        // Add the panel to the JFrame
        add(panel);

        // Set the JFrame properties
        setUndecorated(true); // Remove window decorations
        setLocationRelativeTo(null); // Center the JFrame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the window is closed
    }

    // Draw text using the TTF font
    private void drawText(Graphics g, String text) {
        try {
            // Load the TTF font file
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("suLama/Cybersomething.ttf")).deriveFont(150f);

            // Set the font for drawing the text
            g.setFont(customFont);

            // Set the color for drawing the text
            g.setColor(Color.YELLOW);

            // Get the dimensions of the text
            FontMetrics fontMetrics = g.getFontMetrics();
            int textWidth = fontMetrics.stringWidth(text);
            int textHeight = fontMetrics.getHeight();

            // Calculate the center position
            int centerX = (getWidth() - textWidth) / 2;
            int centerY = (getHeight() - textHeight) / 2;

            // Draw the text
            g.drawString(text, centerX, centerY);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoadingScreen loadingScreen = new LoadingScreen();
            loadingScreen.setVisible(true);

            // Start the game when the "Play" button is clicked
            JButton playButton = new JButton("Play");
            playButton.setBackground(new Color(0, 0, 0, 0));
            playButton.setBorder(null);
            Font playButtonFont;
            try {
                playButtonFont = Font.createFont(Font.TRUETYPE_FONT, new File("suLama/Cybersomething.ttf")).deriveFont(Font.BOLD, 30);
                playButton.setFont(playButtonFont);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
            playButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Gameplay.startGame();
                }
            });

            // End the game when the "Exit" button is clicked
            JButton exitButton = new JButton("Exit");
            exitButton.setBackground(new Color(0, 0, 0, 0));
            exitButton.setBorder(null);
            Font exitButtonFont;
            try {
                exitButtonFont = Font.createFont(Font.TRUETYPE_FONT, new File("suLama/Cybersomething.ttf")).deriveFont(Font.BOLD, 30);
                exitButton.setFont(exitButtonFont);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
            exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Gameplay.endGame();
                    System.exit(0);
                }
            });

            // Create a panel to hold the buttons
            JPanel buttonPanel = new JPanel();
            buttonPanel.setOpaque(false); // Set the panel to be transparent
            buttonPanel.add(playButton);
            buttonPanel.add(exitButton);

            // Create a panel for button alignment
            JPanel alignPanel = new JPanel(new GridBagLayout());
            alignPanel.setOpaque(false); // Set the panel to be transparent
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            alignPanel.add(buttonPanel, gbc);

            // Add the button panel to the loading screen panel
            loadingScreen.getContentPane().add(alignPanel, BorderLayout.SOUTH);
        });
    }
}
