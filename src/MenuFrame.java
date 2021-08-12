import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MenuFrame extends JPanel {
    private JButton startButton;
    private JButton continueButton;
    private JButton howToPlayButton;
    private JButton highscoreButton;
    private JButton exitButton;
    private JButton pongButton;

    JFrame frame;
    public JButton[] buttons;

    public MenuFrame(){
        frame = new JFrame();
        __setupUI__PANEL__();
        Utility.__initialization__(frame, this, ColorValues.CHROME_WHITE, ColorValues.CYAN);
        actions();
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Image bg = null;
        try{
            bg = ImageIO.read(new File(Data.PATH + "images/title.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        assert bg != null;
        int x = (this.getWidth() - bg.getWidth(null)) / 2;
        int y = (this.getHeight() - bg.getHeight(null)) / 2;
        g.setColor(ColorValues.CHROME_WHITE);
        g.fillRect(0,0, frame.getWidth(), frame.getHeight());
        g.drawImage(bg, x,y,null);
    }
    public void actions(){
        startButton.addActionListener(e -> {
            frame.dispose();
            new DataReader().reset();
            new StoryFrame();

        });
        continueButton.addActionListener(e -> {
            frame.dispose();
            new CategoryFrame();
        });
        howToPlayButton.addActionListener(e -> {
            frame.dispose();
            new HowToPlayFrame();
        });
        highscoreButton.addActionListener(e -> {
            frame.dispose();
            new HighScoreFrame();
        });
        pongButton.addActionListener(e -> {
            if(hasPerfect()){
                frame.dispose();
            }
        });
        exitButton.addActionListener(e -> exitAction());
        for(JButton button: buttons) {
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    button.setText("> " + button.getText());
                    new Sounds().hover_();
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    button.setText("" + button.getText().substring(2));
                }
            });
        }
    }
    public void exitAction(){
        JFrame exitFrame = new JFrame();
        JPanel exitPanel = new JPanel();

        exitPanel.setLayout(null);
        JLabel bgLabel = new JLabel();
        JButton yeahButton = new JButton();
        JButton noButton = new JButton();

        exitPanel.setOpaque(false);

        exitPanel.setBackground(ColorValues.MOUNTAIN_MEADOW);
        exitFrame.setBackground(ColorValues.MOUNTAIN_MEADOW);
        exitFrame.setContentPane(exitPanel);
        exitFrame.setUndecorated(true);
        exitFrame.setSize(682,305);
        exitFrame.setLocationRelativeTo(null);

        yeahButton.setBackground(Color.BLUE); yeahButton.setContentAreaFilled(false); yeahButton.setBorder(null);
        noButton.setBackground(Color.RED); noButton.setContentAreaFilled(false); noButton.setBorder(null);
        bgLabel.setIcon(new ImageIcon(Utility.getThisResource("images/Exit.png")));

        Utility.moveComponent(exitPanel, bgLabel, -6,-60, 720 ,432);
        Utility.moveComponent(exitPanel, yeahButton, 398,260, 213,70);
        Utility.moveComponent(exitPanel, noButton, 93,260, 213,70);

        noButton.addActionListener(e1 -> exitFrame.dispose());
        yeahButton.addActionListener(e1 -> System.exit(0));

        exitFrame.setVisible(true);
    }
    public void __setupUI__PANEL__(){
        this.setLayout(null);

        startButton = new JButton("Start");
        continueButton = new JButton("Continue");
        howToPlayButton = new JButton("How to Play");
        highscoreButton =  new JButton("White Hatters");
        pongButton =  new JButton("???");
        exitButton =  new JButton("Exit");

        buttons = new JButton[] {startButton, continueButton, howToPlayButton, highscoreButton, pongButton, exitButton};

        int x_init = 620; int y_init = 330  ; int y_gap = 45;
        int more = 50;

        Utility.moveComponent(this, startButton, x_init, y_init + (y_gap), 124 + more, 0);
        Utility.moveComponent(this, continueButton, x_init - 27, y_init + (y_gap*2),180 + more, 0);
        Utility.moveComponent(this, howToPlayButton, x_init - 60, y_init + (y_gap*3),250 + more, 0);
        Utility.moveComponent(this, highscoreButton, x_init - 75, y_init + (y_gap*4),280 + more, 0);
        Utility.moveComponent(this, pongButton, x_init - 7, y_init + (y_gap*5),140 + more, 0);
        Utility.moveComponent(this, exitButton, x_init - 7, y_init + (y_gap*6),140 + more, 0);
    }


    public boolean hasPerfect(){
        String SCORE_PATH = Data.PATH + "data/scores.txt";
        try{
            File dataFile = new File(SCORE_PATH);
            Scanner S = new Scanner(dataFile);
            while(S.hasNextLine()){
                String data = S.nextLine();
                String[] stripped = data.split(" ");
                if(stripped[1].equals("100")){
                    return true;
                }
            }
            S.close();
        }catch (FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "ERROR v.1.3 Can't Find data Score File");
            System.out.println("An Error Occurred.");
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        new MenuFrame();
    }
}
