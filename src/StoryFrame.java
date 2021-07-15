import javax.swing.*;

import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StoryFrame extends JFrame {

    private JPanel mainPanel;
    private JLabel test;

    public StoryFrame(){

        test.setText("Lorem ipsum sit dolor iscet ");
        Utility.__initialization__(this, mainPanel, ColorValues.SHARK, ColorValues.CERULEAN_BLUE);
        mainPanel.addComponentListener(new ComponentAdapter() { } );
        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
                CategoryFrame CF = new CategoryFrame();
                CF.setVisible(true);
            }
        });

    }

    public static void main(String[] args) {
        StoryFrame SF = new StoryFrame();

        SF.setVisible(true);
    }
}
