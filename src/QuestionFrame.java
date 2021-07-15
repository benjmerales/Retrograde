import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuestionFrame extends JFrame{
    //<editor-fold desc="- - Swing Components - -">
    private JPanel mainPanel;
    private JButton optionAButton;
    private JButton optionBButton;
    private JButton optionCButton;
    private JButton optionDButton;
    private JButton powerCButton;
    private JButton powerBButton;
    private JButton powerAButton;
    private JLabel questionLabel;
    private JLabel progressBar;
    private QuestionObject QO;
    //</editor-fold>

    public JButton[] options;
    public DataReader DR;

    public QuestionFrame(QuestionObject question){
        this.QO = question;
        this.DR = new DataReader();
        Utility.__initialization__(this, mainPanel, ColorValues.ROYAL_BLUE, ColorValues.BLUE_CHILL);
        reinitialization();
        if(question == null) {
            question = new QuestionObject(new String[]{"0", "Zebra", "Null Null A Null Null?", "Error", "000xc:", "Exception", "Throw", "A", "false"});
            this.QO = question;
        }

        initializeQuestion();
        computeForProgress();
        recheckPowerButtons();

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
                    option.setBackground(ColorValues.MOUNTAIN_MEADOW);
                    option.setForeground(ColorValues.CHROME_WHITE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    option.setBackground(null);
                    option.setForeground(ColorValues.BLUE_CHILL);
                }
            });
        }

        powerAButton.addActionListener(e -> {
            CaptchaPowerFrame CPF = new CaptchaPowerFrame(QO.correct);
            CPF.setVisible(true);
            DR.changePowersStatus(0);
            recheckPowerButtons();
        });
        powerBButton.addActionListener(e -> {
            BinaryPowerFrame BPF = new BinaryPowerFrame(QO.correct);
            BPF.setVisible(true);
            DR.changePowersStatus(1);
            recheckPowerButtons();
        });
        powerCButton.addActionListener(e -> {
            DR.changePowersStatus(2);
            recheckPowerButtons();
        });
    }
    public void recheckPowerButtons(){
        if(!DR.checkPowerIndex(0)) powerAButton.setEnabled(false);
        if(!DR.checkPowerIndex(1)) powerBButton.setEnabled(false);
        if(!DR.checkPowerIndex(2)) powerCButton.setEnabled(false);
    }
    public void computeForProgress(){
        int[] questions = DR.getQuestions();
        int cnt = 0;
        for (int i: questions){
            if(i == -1) cnt++;
        }
        String percent = String.format("%.02f", (cnt/13f)*100);
        progressBar.setText("Progress: " + percent + "%");
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
