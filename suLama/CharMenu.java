package suLama;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.FontFormatException;

public class CharMenu extends JPanel {
    private BufferedImage menuImage;
    private BufferedImage backgroundImage;
    private BufferedImage characterImage;

    private int menuSpacing = 50; // Custom spacing around the menu image

    public CharMenu() {
        try {
            // Load menu image
            BufferedImage originalMenuImage = ImageIO.read(new File("suLama/menu.png"));

            // Calculate the desired size of the menu image
            int desiredMenuWidth = 400; // Set the desired width of the menu image
            int desiredMenuHeight = 300; // Set the desired height of the menu image

            // Resize the menu image to the desired size
            menuImage = resizeImage(originalMenuImage, desiredMenuWidth, desiredMenuHeight);

            // Load background image
            backgroundImage = ImageIO.read(new File("suLama/Mountain-Dusk.png"));

            // Load character sprite sheet
            BufferedImage spriteSheet = ImageIO.read(new File("suLama/Idle.png"));

            // Extract the desired character image from the sprite sheet
            int spriteWidth = spriteSheet.getWidth() / 3; // Assuming the sprite sheet contains 3 images horizontally
            int spriteHeight = spriteSheet.getHeight();
            int desiredSpriteIndex = 1; // Index of the desired character image (0 for the first image, 1 for the second image, etc.)
            characterImage = spriteSheet.getSubimage(spriteWidth * desiredSpriteIndex, 0, spriteWidth, spriteHeight);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // Draw the "Choose Your Poison" text on the background image
        Font customFont = loadCustomFont("suLama/Cybersomething.ttf", 36);
        g.setFont(customFont);
        g.setColor(Color.WHITE);
        String text = "Choose Your Poison";
        FontMetrics fontMetrics = g.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(text);
        int textHeight = fontMetrics.getHeight();
        int textX = (getWidth() - textWidth) / 2;
        int textY = (getHeight() - textHeight) / 2 + fontMetrics.getAscent();
        g.drawString(text, textX, textY);

        // Calculate the available space
        int availableWidth = getWidth() - 2 * menuSpacing;
        int availableHeight = getHeight() - 2 * menuSpacing;

        // Calculate the top-left coordinates of the menu image
        int menuX = menuSpacing + (availableWidth - menuImage.getWidth()) / 2;
        int menuY = menuSpacing + (availableHeight - menuImage.getHeight()) / 2;

        // Draw the menu image
        g.drawImage(menuImage, menuX, menuY, null);

        // Calculate the top-left coordinates of the character image
        int characterX = menuX + (menuImage.getWidth() - characterImage.getWidth()) / 2;
        int characterY = menuY + (menuImage.getHeight() - characterImage.getHeight()) / 2;

        // Draw the character image
        g.drawImage(characterImage, characterX, characterY, null);
    }

    // Resize the image to the specified width and height
    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(originalImage, 0, 0, width, height, null);
        g2d.dispose();
        return resizedImage;
    }

    // Load a custom TrueType font from file
    private Font loadCustomFont(String filePath, int size) {
        try {
            File fontFile = new File(filePath);
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.PLAIN, size);
            return font;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        // Return a default font if the custom font cannot be loaded
        return new Font("Arial", Font.PLAIN, size);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Character Selection Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800); // Set a custom size for the JFrame
        frame.setResizable(false); // Prevent resizing of the window

        CharMenu charMenu = new CharMenu();
        frame.add(charMenu);

        frame.setVisible(true);
    }
}
