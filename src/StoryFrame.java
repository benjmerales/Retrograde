import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StoryFrame extends JPanel{
    private JLabel imageButton;
    private JButton skipButton;

    JFrame frame;
    int counter = 1;
    public StoryFrame(){
        frame = new JFrame();
        __setupUI__PANEL__();
        Utility.__initialization__(frame, this, ColorValues.CHROME_WHITE, ColorValues.SHARK);
        skipButton.setForeground(ColorValues.BLUE_CHILL);
        actions();
        imageButton.setIcon(new ImageIcon(Utility.getThisResource("images/s" + counter +".jpg")));
        counter++;
        frame.setVisible(true);
    }
    public void actions(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(counter == 4){
                    skipButton.setText("Click Anywhere to Continue");
                    imageButton.setIcon(new ImageIcon(Utility.getThisResource("images/s" + counter +".jpg")));
                }
                else if(counter == 5){
                    new Sounds().play(new Sounds().after_story, false);
                    frame.dispose();
                    new CategoryFrame();
                }
                else{
                    imageButton.setIcon(new ImageIcon(Utility.getThisResource("images/s" + counter +".jpg")));

                }
                counter++;

            }
        });
        skipButton.addActionListener(e -> {
            frame.dispose();
            new CategoryFrame();
        });
        skipButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                skipButton.setText("Skip >>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                skipButton.setText("Skip");
            }
        });
    }
    public void __setupUI__PANEL__(){
        this.setLayout(null);
        skipButton = new JButton("Skip");
        imageButton = new JLabel();
        Utility.moveComponent(this, skipButton, 1200,0, 160, 40);
        Utility.moveComponent(this, imageButton, -18,-116, 1500,1000);

    }

    public static void main(String[] args) {
        new StoryFrame();
    }
}
