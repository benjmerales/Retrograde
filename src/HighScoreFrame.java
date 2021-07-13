import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighScoreFrame extends JFrame{
    private JButton backButton;
    private JPanel mainPanel;
    public HighScoreFrame(){
        Utility.__initialization__(this, mainPanel, ThemeValues.BLUE_CHILL, ThemeValues.SHARK);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuFrame MF = new MenuFrame();
                MF.setVisible(true);
            }
        });
    }
}
