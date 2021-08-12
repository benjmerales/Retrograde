import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EndScreen extends JPanel {
    boolean win;
    JFrame frame;

    public EndScreen(boolean isWin){
        this.win = isWin;
        frame = new JFrame();
        Utility.__initialization__(frame, this, ColorValues.CHROME_WHITE, ColorValues.SHARK);
        __setupUI__PANEL__();
        frame.setVisible(true);
    }
    @Override
    public void paintComponent(Graphics g) {
        Image bg = null;
        try{
            if(win){
                bg = ImageIO.read(new File(Data.PATH + "images/winBG.png"));
            }else{
                bg = ImageIO.read(new File(Data.PATH + "images/loseBG.png"));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        int x = (this.getWidth() - bg.getWidth(null)) / 2;
        int y = (this.getHeight() - bg.getHeight(null)) / 2;
        g.setColor(ColorValues.CHROME_WHITE);
        g.fillRect(0,0, frame.getWidth(), frame.getHeight());
        g.drawImage(bg, x,y,null);
    }
    public String computeForProgress(){
        DataReader DR = new DataReader();
        int[] questions = DR.getQuestions();
        int cnt = 0;
        for (int i: questions){
            if(i == -1 || i == -2) cnt++;
        }
        String percent = String.format("%.02f", (cnt/13f)*100);
        return percent;
    }

    public void __setupUI__PANEL__(){
        this.setLayout(null);
        if(win){
            JLabel label = new JLabel("Enter your name: ");
            JTextField field = new JTextField();
            field.setBackground(ColorValues.MAVERICK);
            String score = computeForProgress();
            field.addActionListener(e -> {
                File log = new File(Data.PATH + "data/scores.txt");
                try {
                    if (!log.exists()) {
                        System.out.println("We had to make a new file.");
                        log.createNewFile();
                    }

                    FileWriter fileWriter = new FileWriter(log, true);

                    String line = field.getText() + " " + score;

                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write("\n" + line);
                    bufferedWriter.close();
                } catch (IOException a) {
                    System.out.println("COULD NOT LOG!!");
                }
                frame.dispose();
                new HighScoreFrame();
            });
            label.setFont(Utility.getDogicaFont());
            field.setFont(Utility.getDogicaFont());

            Utility.moveComponent(this, label, 500,425, 0, 50);
            Utility.moveComponent(this, field, 450,475, 450, 50);
        }
        else{
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseEntered(e);
                    new Sounds().hover_();
                    JLabel label = new JLabel("Better luck next time!");
                    Utility.moveComponent(, label, 500, 425);
                    frame.dispose();
                }
            });
        }
    }

    public static void main(String[] args) {
        new EndScreen(false);
    }
}
