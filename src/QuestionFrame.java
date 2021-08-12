import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class QuestionFrame extends JPanel {
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
    private JButton codeButton;
    private JLabel progressBar;
    private QuestionObject QO;
    //</editor-fold>

    public JButton[] options;
    public DataReader DR;

    JFrame frame;
    public QuestionFrame(QuestionObject question){
        frame = new JFrame();
        this.QO = question;

        DR = new DataReader();
        __setupUI__PANEL__();
        Utility.__initialization__(frame, this, ColorValues.CERULEAN_BLUE, ColorValues.CYAN);
        actions();

        if(question == null) {
            question = new QuestionObject(new String[]{"0", "Zebra", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Vestibulum rhoncus luctus lectus. Vivamus maximus massa sed magna facilisis, at tempus urna tincidunt. Aenean " +
                    "lacinia orci ut dui tristique, at lobortis magna feugiat.",
                    "VS", "Short Text", "This is a Medium Text", "This is such a very long text!!!", "A", "true"});
            this.QO = question;
        }

        this.QO = shuffleChoices(question);
        if(!QO.hasCodeSnippet)
            codeButton.setVisible(false);

        initializeQuestion();
        computeForProgress();
        recheckPowerButtons();

        this.QO = question;
        this.DR = new DataReader();
        reinitialization();
        frame.setVisible(true);

    }

    public void __setupUI__PANEL__(){
        Insets insets = new Insets(0,0,0,0);

        optionAButton = new JButton("Option A");
        optionBButton = new JButton("Option B");
        optionCButton = new JButton("Option C");
        optionDButton = new JButton("Option D");

        codeButton = new JButton("See Code");

        powerAButton = new JButton("");
        powerBButton = new JButton("");
        powerCButton = new JButton("");
        int powers[] = DR.getPowers();
        if(powers[0] == 1) powerAButton.setIcon(new ImageIcon(Utility.getThisResource("images/powerups/search.png")));
        else powerAButton.setIcon(new ImageIcon(Utility.getThisResource("images/powerups/search_d.png")));

        if(powers[1] == 1) powerBButton.setIcon(new ImageIcon(Utility.getThisResource("images/powerups/eye.png")));
        else powerBButton.setIcon(new ImageIcon(Utility.getThisResource("images/powerups/eye_d.png")));

        if(powers[2] == 1) powerCButton.setIcon(new ImageIcon(Utility.getThisResource("images/powerups/lock.png")));
        else powerCButton.setIcon(new ImageIcon(Utility.getThisResource("images/powerups/lock_d.png")));

        int network_q_value = DR.getQuestions()[11];

        if(network_q_value == -1 ) {
            powerAButton.setEnabled(true);
            powerAButton.setToolTipText(null);
        }
        else{
            powerAButton.setToolTipText("Unlock Network First");
            powerAButton.setEnabled(false);
        }

        questionLabel = new JLabel("Question");
        progressBar = new JLabel("Progress: 00.00%");

        this.setLayout(null);

        int x_init = 500, y_init = 140, y_gap = 50, max_width = 300;

        Utility.moveComponent(this, progressBar, x_init - 250, y_init - 80+ (y_gap*1), max_width, 25);;
        Utility.moveComponent(this, questionLabel, 200, y_init + (y_gap*2), 1000, 100);
        Utility.moveComponent(this, codeButton, x_init, y_init + (y_gap*4), max_width, 0);;


        Utility.moveComponent(this, powerAButton, x_init+ 200, y_init + (y_gap*10), 32, 0);;
        Utility.moveComponent(this, powerBButton, x_init+ 300, y_init + (y_gap*10), 32, 0);;
        Utility.moveComponent(this, powerCButton, x_init+ 400, y_init + (y_gap*10), 32, 0);;

        x_init = 300; y_init = 140; y_gap = 50; max_width = 700;
        Utility.moveComponent(this, optionAButton, x_init, y_init + (y_gap*5), max_width, 0);;
        Utility.moveComponent(this, optionBButton, x_init, y_init + (y_gap*6), max_width, 0);;
        Utility.moveComponent(this, optionCButton, x_init, y_init + (y_gap*7), max_width, 0);;
        Utility.moveComponent(this, optionDButton, x_init, y_init + (y_gap*8), max_width, 0);;

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Image bg;
        try{
            bg = ImageIO.read(new File(Data.PATH + "images/question.png"));
        }catch (IOException e){
            bg = null;
            e.printStackTrace();
        }
        int x = (this.getWidth() - bg.getWidth(null)) / 2;
        int y = (this.getHeight() - bg.getHeight(null)) / 2;
        g.setColor(ColorValues.CHROME_WHITE);
        g.fillRect(0,0, frame.getWidth(), frame.getHeight());
        g.drawImage(bg, x,y,null);
    }

    public QuestionObject shuffleChoices(QuestionObject tQO){
        String[] choices = tQO.choices;
        char correct = tQO.correct;
        int correct_index = -1;

        System.out.print("Initial Arrangement: ");
        for(String c: choices) System.out.print(c + ", ");
        System.out.println();
        System.out.println("Initial Answer: " + correct);

        if(correct == 'A') correct_index = 0;
        else if(correct == 'B') correct_index = 1;
        else if(correct == 'C') correct_index = 2;
        else if(correct == 'D') correct_index = 3;

        Random R = new Random();
        int shuffles = 5;
        for(int i=0; i<shuffles; i++){
            int first = R.nextInt(4);
            int second = R.nextInt(4);

            if(correct_index == first){ correct_index = second; }
            else if(correct_index == second){ correct_index = first; }
            String temp = choices[first];
            choices[first] = choices[second];
            choices[second] = temp;
        }

        if(correct_index == 0) correct = 'A';
        if(correct_index == 1) correct = 'B';
        if(correct_index == 2) correct = 'C';
        if(correct_index == 3) correct = 'D';

        System.out.print("Final Arrangement: ");
        for(String c: choices) System.out.print(c + ", ");
        System.out.println();
        System.out.println("Final Answer: " + correct);

        String[] compiled = new String[]{tQO.id+"", tQO.category, tQO.question, choices[0], choices[1],choices[2], choices[3], correct+"", tQO.hasCodeSnippet+""};
        return new QuestionObject(compiled);
    }
    public void actions(){
        options = new JButton[] {optionAButton, optionBButton, optionCButton, optionDButton};
        optionAButton.addActionListener(e -> { if(QO.correct == 'A') tally(true); else tally(false); });
        optionBButton.addActionListener(e -> { if(QO.correct == 'B') tally(true); else tally(false); });
        optionCButton.addActionListener(e -> { if(QO.correct == 'C') tally(true); else tally(false); });
        optionDButton.addActionListener(e -> { if(QO.correct == 'D') tally(true); else tally(false); });

        codeButton.addActionListener(e -> {
            if(QO.hasCodeSnippet){
                new SnippetFrame(Data.PATH + "snippet/snippet_" + QO.id + ".txt");
            }
        });
        codeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                codeButton.setText(">> See Code <<");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                codeButton.setText("See Code");
            }
        });
        for(JButton option: options) {
            option.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    new Sounds().hover_();
                    option.setContentAreaFilled(true);
                    option.setBackground(ColorValues.MOUNTAIN_MEADOW);
                    option.setForeground(ColorValues.CHROME_WHITE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    option.setContentAreaFilled(true);
                    option.setBackground(null);
                    option.setForeground(ColorValues.CYAN);
                }
            });
        }

        powerAButton.addActionListener(e -> {
            new Sounds().play(new Sounds().power, false);
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
            MazePowerFrame MPF = new MazePowerFrame(QO.id);
            MPF.setVisible(true);
            frame.dispose();
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
            if(i == -1 || i == -2) cnt++;
        }
        String percent = String.format("%.02f", (cnt/13f)*100);
        progressBar.setText("Progress: " + percent + "%");
    }
    public void tally(boolean answer){
        DataReader DR = new DataReader();
        // -1 means right, -2 means wrong
        /*for(int i: DR.questions){
            System.out.print(i + ", ");
        }
        System.out.println();
         */
        if(answer){
            new Sounds().play(new Sounds().rig_sfc, false);
            JOptionPane.showMessageDialog(null, "RIGHT");
            DR.changeQuestionStatus(DR.getCurrentIndex(),-1);
        }else{
            new Sounds().play(new Sounds().wro_sfc, false);
            JOptionPane.showMessageDialog(null, "WRONG");
            DR.changeQuestionStatus(DR.getCurrentIndex(), -2);
        }
        int gameValue = checkIfDone();
        if(gameValue == -1){

            CategoryFrame CF = new CategoryFrame();
            CF.setVisible(true);
        }
        frame.dispose();
    }
    public int checkIfDone(){
        int[] questions = DR.getQuestions();
        int wrong_counters = 0;
        int right_counters = 0;
        boolean isComplete = true;
        for(int i: questions){
            if(i == -2) wrong_counters++; else if(i == -1) right_counters++;
            if(i != -1 && i != -2) isComplete = false;
        }
//        System.out.println("Total: 13\t Right: " + right_counters + "\t Wrong: " + wrong_counters + "\t Unanswered: " + (13-right_counters-wrong_counters));
        if(wrong_counters >= 7) {
            new Sounds().play(new Sounds().lose_bgm, false);
            new EndScreen(false);
            return 0;
        }
        if(isComplete){
            new Sounds().play(new Sounds().win_bgm, false);
            new EndScreen(true);
            return 1;
        }
        return -1;
    }
    public void reinitialization(){
        Component[] components = this.getComponents();
        for(Component component: components){
            Font f = Utility.getMinecraftiaFont();
            component.setFont(f.deriveFont(18f));
        }
    }
    public void initializeQuestion(){
        questionLabel.setText("<html>" + QO.question + "</html>");
        optionAButton.setText(QO.choices[0]);
        optionBButton.setText(QO.choices[1]);
        optionCButton.setText(QO.choices[2]);
        optionDButton.setText(QO.choices[3]);
    }

    public static void main(String[] args) {
        QuestionFrame QF = new QuestionFrame(null);
    }
}
