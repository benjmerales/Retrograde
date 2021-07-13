import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SplashFrame extends JFrame implements Utility{
    private JPanel mainPanel;
    public SplashFrame(){
        Utility.__initialization__(this, mainPanel, ThemeValues.SHARK, ThemeValues.MALACHITE);
        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
                MenuFrame MF = new MenuFrame();
                MF.setVisible(true);
            }
        });

    }

    public static void main(String[] args) {
        SplashFrame SF = new SplashFrame();
        SF.setVisible(true);
    }
}
