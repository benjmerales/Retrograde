import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class HighScoreFrame extends JPanel{
    private JButton backButton;
    private JTextPane namePane;
    private JTextPane scorePane;

    JFrame frame;

    public HighScoreFrame(){
        frame = new JFrame();
        __setupUI__PANEL__();
        Utility.__initialization__(frame, this, ColorValues.CHROME_WHITE, ColorValues.SHARK);
//        __initialization__();
        actions();
        getScores();
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Image bg = null;
        try{
            bg = ImageIO.read(new File(Data.PATH + "images/high.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        g.setColor(ColorValues.CHROME_WHITE);
        g.fillRect(0,0, frame.getWidth(), frame.getHeight());
        int y = (this.getHeight() - bg.getHeight(null)) / 2;
        int x = (this.getWidth() - bg.getWidth(null)) / 2;
        g.drawImage(bg, x,y,null);
    }
    public void actions(){
        backButton.addActionListener(e -> {
            frame.dispose();
            new MenuFrame();
        });
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                backButton.setContentAreaFilled(true);
                backButton.setBackground(ColorValues.CERULEAN_BLUE);
                backButton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                backButton.setForeground(ColorValues.SHARK);
                backButton.setContentAreaFilled(false);
                backButton.setBackground(null);
            }
        });
    }
    public void __setupUI__PANEL__(){
        this.setLayout(null);

        backButton = new JButton("Back");

        namePane = new JTextPane();
        scorePane = new JTextPane();

        namePane.setFont(Utility.getMinecraftiaFont());
        namePane.setBackground(ColorValues.MAVERICK);
        scorePane.setBackground(ColorValues.MAVERICK);
        scorePane.setFont(Utility.getMinecraftiaFont());

        namePane.setFocusable(false);
        scorePane.setFocusable(false);
        Utility.moveComponent(this, backButton, 620, 650, 124, 0);
        Utility.moveComponent(this, namePane, 480,185, 200,400);
        Utility.moveComponent(this, scorePane, 800,185, 200,400);

    }

    public void getScores(){
        String SCORE_PATH = Data.PATH + "data/scores.txt";
        try{
            File dataFile = new File(SCORE_PATH);
            Scanner S = new Scanner(dataFile);
            while(S.hasNextLine()){
                String data = S.nextLine();
                String[] stripped = data.split(" ");
                namePane.setText(namePane.getText() + stripped[0] + "\n");
                scorePane.setText(scorePane.getText() + stripped[1] + "\n");
            }
            S.close();
        }catch (FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "ERROR v.1.3 Can't Find data Score File");
            System.out.println("An Error Occured.");
            e.printStackTrace();
        }
    }
    public void __initialization__(){
        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
        namePane.setParagraphAttributes(attribs, true);
        scorePane.setParagraphAttributes(attribs, true);
        namePane.setFocusable(false);
        scorePane.setFocusable(false);
        namePane.setForeground(Color.WHITE);
        scorePane.setForeground(Color.WHITE);
        namePane.setCharacterAttributes(attribs, true);
        scorePane.setCharacterAttributes(attribs, true);
    }

    public static void main(String[] args) {
        new HighScoreFrame();
    }
}
