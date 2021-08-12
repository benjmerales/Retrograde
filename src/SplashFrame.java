import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/***
 * This is the SplashFrame, the index frame of the project
 */
public class SplashFrame extends JFrame implements Utility{
    private JPanel mainPanel;
    SoundTest ST;
    public SplashFrame(){
        Utility.__initialization__(this, mainPanel, ColorValues.SHARK, ColorValues.MALACHITE);
        /*Sounds S = new Sounds();
        S.play(S.bgm, true);*/
        ST = new SoundTest();
        ST.loadFile("src/resources/sounds/bgm.wav");
        ST.play();
        ST.loop();
        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MenuFrame MF = new MenuFrame();
                MF.setVisible(true);

            }
        });
    }


    /*** Main Driver **/
    public static void main(String[] args) {
        SplashFrame SF = new SplashFrame();
        SF.setVisible(true);
    }
}
