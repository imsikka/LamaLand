package suLama;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LoadingScreen extends JFrame {
    private ImageIcon backgroundImage;

    public LoadingScreen() throws FontFormatException, IOException {
	    	Font font = Font.createFont(Font.TRUETYPE_FONT, new File("suLama/Cybersomething.ttf")).deriveFont(Font.BOLD, 30);
	    	setSize(800, 800);
	        backgroundImage = new ImageIcon("suLama/Mountain-Dusk.png");
	        JPanel panel = new JPanel() {
	            @Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
	                // Display text using the TTF font
	                drawText(g, "LAMALAND");
	            }
	        };
	
	        
	        panel.setLayout(new GridBagLayout());
	        GridBagConstraints gridBagConstraints = new GridBagConstraints();
	        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
	        
	        JButton playButton = new JButton("Play");
	        playButton.setFont(font);
	        playButton.setBorder(null);
	        playButton.setOpaque(false);
	        playButton.setContentAreaFilled(false);
	        playButton.setBorderPainted(false);
	        playButton.setForeground(Color.white);
	        
	        playButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
//              	Gameplay.startGame();
	        	}
	        });
	
	      JButton exitButton = new JButton("Exit");
	      exitButton.setFont(font);
	      exitButton.setBorder(null);
	      exitButton.setOpaque(false);
	      exitButton.setContentAreaFilled(false);
	      exitButton.setBorderPainted(false);
	      exitButton.setForeground(Color.white);
	     
	      exitButton.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
//              	Gameplay.endGame();
	              	System.exit(0);
	          }
	      });

	      panel.add(playButton,gridBagConstraints);
	      panel.add(exitButton,gridBagConstraints);
	      add(panel);
	      
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

    public static void main(String[] args) throws FontFormatException, IOException {
            LoadingScreen loadingScreen = new LoadingScreen();
            loadingScreen.setVisible(true);
           
    }
}
