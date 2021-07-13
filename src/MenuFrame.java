import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuFrame extends JFrame{
    private JButton startButton;
    private JButton continueButton;
    private JButton howToPlayButton;
    private JButton highscoreButton;
    private JButton exitButton;
    private JPanel mainPanel;
    private JButton button1;
    private JLabel titleLabel;

    public JButton buttons[];
    public MenuFrame(){
        buttons = new JButton[] {startButton, continueButton, howToPlayButton, highscoreButton, exitButton};

        Utility.__initialization__(this, mainPanel, ThemeValues.MALACHITE, ThemeValues.MOUNTAIN_MEADOW);

        titleLabel.setForeground(Color.YELLOW);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int process = JOptionPane.showConfirmDialog(null, "Are you sure want to quit?");
                if (process == JOptionPane.YES_OPTION) System.exit(0);
            }
        });
        howToPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HowToPlayFrame HF = new HowToPlayFrame();
                HF.setVisible(true);
            }
        });
        highscoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HighScoreFrame HF = new HighScoreFrame();
                HF.setVisible(true);
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StoryFrame SF = new StoryFrame();
                SF.setVisible(true);
            }
        });

        for(JButton button: buttons) {
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    button.setText(">" + button.getText());

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    button.setText("" + button.getText().substring(1));
                }
            });
        }

        highscoreButton.addMouseListener(new MouseAdapter() {
        });
    }


    public static void main(String[] args) {
        MenuFrame MF = new MenuFrame();
        MF.setVisible(true);
    }
}
