package suLama;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoadingScreen loadingScreen = new LoadingScreen();
            loadingScreen.setVisible(true);
        });
    }
}
