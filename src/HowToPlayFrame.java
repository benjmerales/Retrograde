import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class HowToPlayFrame extends JPanel {
    private JButton returnButton;
    JFrame frame;

    public HowToPlayFrame(){
        frame = new JFrame();
        __setupUI__PANEL__();
        Utility.__initialization__(frame, this, ColorValues.CHROME_WHITE, ColorValues.CHROME_WHITE);
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Image bg = null;
        try{
            bg = ImageIO.read(new File(Data.PATH + "images/how.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        int y = (this.getHeight() - bg.getHeight(null)) / 2;
        int x = (this.getWidth() - bg.getWidth(null)) / 2;
        g.drawImage(bg, x,y,null);
    }
    public void __setupUI__PANEL__(){
        this.setLayout(null);

        returnButton = new JButton("Return");

        JLabel instruct1 = new JLabel("<html>Completely restore a retro computer by answering various programming questions. " +
                "Choose from the 4 options for the correct answer. " +
                "Each correct answer increases the level of completion of the restoration and the subcategory will be highlighted from the category pick. " +
                "While each wrong answer will not add any points and the subcategory will be grayed out. " +
                "If the player reaches a number of 7 wrongly answered questions, the game ends and the restoration will be a failure. " +
                "Complete all of the 7 categories and its corresponding subcategories to win the game. ");
        JLabel instruct2 = new JLabel("<html>You get to have three power ups at your disposal. Use it wisely.");
        JLabel instruct3 = new JLabel("<html>Obtain a progress level of 100% to unlock a hidden feature.");
        JLabel instruct4 = new JLabel("<html>Unlocked after completing the Network category.");
        JLabel instruct5 = new JLabel("<html>Reveal correct answer after solving a problem.");
        JLabel instruct6 = new JLabel("<html>Skip a question by completing a puzzle.");

        JButton powerAButton = new JButton(""); powerAButton.setIcon(new ImageIcon(Utility.getThisResource("images/powerups/search.png")));
        JButton powerBButton = new JButton(""); powerBButton.setIcon(new ImageIcon(Utility.getThisResource("images/powerups/eye.png")));
        JButton powerCButton = new JButton(""); powerCButton.setIcon(new ImageIcon(Utility.getThisResource("images/powerups/lock.png")));

        int x_init = 205, y_init = -10, y_gap = 50, max_width = 300;

        Utility.moveComponent(this, powerAButton, x_init, y_init + (y_gap*9), max_width, 0);;
        Utility.moveComponent(this, powerBButton, x_init, y_init + (y_gap*10), max_width, 0);;
        Utility.moveComponent(this, powerCButton, x_init, y_init + (y_gap*11), max_width, 0);;


        Utility.moveComponent(this, instruct1, 210, 20, 1000, 400);
        Utility.moveComponent(this, instruct2, 288, 370, 1000, 100);
        Utility.moveComponent(this, instruct3, 310, 590, 1000, 100);
        Utility.moveComponent(this,instruct4, 405,415,1000,100);
        Utility.moveComponent(this,instruct5, 405,470,1000,100);
        Utility.moveComponent(this,instruct6, 405,520,1000,100);

        returnButton.addActionListener(e -> {
            frame.dispose();
            new MenuFrame();
        });
        returnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                returnButton.setContentAreaFilled(true);
                returnButton.setBackground(ColorValues.MOUNTAIN_MEADOW);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                returnButton.setContentAreaFilled(false);
                returnButton.setBackground(null);
            }
        });
        Utility.moveComponent(this, returnButton, 550, 700, 300, 0);
    }

    public static void main(String[] args) {
        new HowToPlayFrame();
    }
}
