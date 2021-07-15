import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuFrame extends JFrame{
    private JButton startButton;
    private JButton continueButton;
    private JButton howToPlayButton;
    private JButton highscoreButton;
    private JButton exitButton;
    private JPanel mainPanel;
    private JButton pongButton;
    private JLabel titleLabel;

    public JButton[] buttons;
    public MenuFrame(){
        buttons = new JButton[] {startButton, continueButton, howToPlayButton, highscoreButton, pongButton, exitButton};

        Utility.__initialization__(this, mainPanel, ColorValues.MOUNTAIN_MEADOW, ColorValues.CYAN);

        titleLabel.setForeground(Color.YELLOW);

        exitButton.addActionListener(e -> {
            int process = JOptionPane.showConfirmDialog(null, "Are you sure want to quit?");
            if (process == JOptionPane.YES_OPTION) System.exit(0);
        });
        howToPlayButton.addActionListener(e -> {
            dispose();
            HowToPlayFrame HF = new HowToPlayFrame();
            HF.setVisible(true);
        });
        highscoreButton.addActionListener(e -> {
            dispose();
            HighScoreFrame HF = new HighScoreFrame();
            HF.setVisible(true);
        });
        startButton.addActionListener(e -> {
            dispose();
            new DataReader().reset();
            StoryFrame SF = new StoryFrame();
            SF.setVisible(true);
        });

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
        continueButton.addActionListener(e -> {
            CategoryFrame CF = new CategoryFrame();
            CF.setVisible(true);
        });
    }


    public static void main(String[] args) {
        MenuFrame MF = new MenuFrame();
        MF.setVisible(true);
    }
}
