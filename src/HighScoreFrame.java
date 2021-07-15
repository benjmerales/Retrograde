import javax.swing.*;

public class HighScoreFrame extends JFrame{
    private JButton backButton;
    private JPanel mainPanel;
    public HighScoreFrame(){
        Utility.__initialization__(this, mainPanel, ColorValues.BLUE_CHILL, ColorValues.SHARK);
        backButton.addActionListener(e -> {
            dispose();
            MenuFrame MF = new MenuFrame();
            MF.setVisible(true);
        });
    }
}
