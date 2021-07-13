import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuestionFrame extends JFrame{
    private JPanel mainPanel;
    private JButton optionAButton;
    private JButton optionBButton;
    private JButton optionCButton;
    private JButton optionDButton;
    private JButton browseButton;
    private JButton jailbreakButton;
    private JButton showButton;
    private JLabel questionLabel;
    private QuestionObject QO;

    public JButton[] options;
    public QuestionFrame(QuestionObject question){
        this.QO = question;

        Utility.__initialization__(this, mainPanel, ThemeValues.ROYAL_BLUE, ThemeValues.BLUE_CHILL);
        reinitialization();

        if(question == null) {
            question = new QuestionObject(new String[]{"0", "Zebra", "Null Null A Null Null?", "Error", "000xc:", "Exception", "Throw", "A", "false"});
            this.QO = question;
        }
        initializeQuestion();

        options = new JButton[] {optionAButton, optionBButton, optionCButton, optionDButton};
        optionAButton.addActionListener(e -> { if(QO.correct == 'A') tally(true); else tally(false); });
        optionBButton.addActionListener(e -> { if(QO.correct == 'B') tally(true); else tally(false); });
        optionCButton.addActionListener(e -> { if(QO.correct == 'C') tally(true); else tally(false); });
        optionDButton.addActionListener(e -> { if(QO.correct == 'D') tally(true); else tally(false); });

        for(JButton option: options) {
            option.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    new Sounds().hover_();
                    option.setBackground(ThemeValues.MOUNTAIN_MEADOW);
                    option.setForeground(ThemeValues.CHROME_WHITE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    option.setBackground(null);
                    option.setForeground(ThemeValues.BLUE_CHILL);
                }
            });
        }
    }
    public void tally(boolean answer){
        DataReader DR = new DataReader();
        // -1 means right, -2 means wrong
        if(answer){
            JOptionPane.showMessageDialog(null, "RIGHT");
            DR.changeQuestionStatus(DR.getProgress(),-1);
        }else{
            JOptionPane.showMessageDialog(null, "WRONG");
            DR.changeQuestionStatus(DR.getProgress(), -2);
        }
        dispose();
        CategoryFrame CF = new CategoryFrame();
        CF.setVisible(true);
    }
    public void reinitialization(){
        Component[] components = mainPanel.getComponents();
        for(Component component: components){
            Font f = Utility.getDogicaFont();
            component.setFont(f.deriveFont(18f));
        }
    }
    public void initializeQuestion(){
        questionLabel.setText(QO.question);
        optionAButton.setText(QO.choices[0]);
        optionBButton.setText(QO.choices[1]);
        optionCButton.setText(QO.choices[2]);
        optionDButton.setText(QO.choices[3]);
    }

    public static void main(String[] args) {
        QuestionFrame QF = new QuestionFrame(null);
        QF.setVisible(true);
    }
}
