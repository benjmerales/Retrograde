import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CategoryFrame extends JFrame{
    private JPanel mainPanel;
    private JButton motherboardButton;
    private JButton CPUButton;
    private JButton chipsetButton;
    private JButton networkButton;
    private JButton operatingSystemButton;
    private JButton subCategory1;
    private JButton subCategory2;
    private JButton subCategory3;
    private JButton subCategory4;
    private JButton subCategory5;
    private JButton subCategory6;
    private JLabel titleLabel;

    public JButton[] allButtons;
    public JButton[] majorButtons;
    public JButton[] subButtons;
    public boolean[] activeMajorButtons;

    public int major_index;
    public int minor_index;
    int[] questions;
    DataReader DR;
    public CategoryFrame(){
        allButtons = new JButton[] { motherboardButton, CPUButton, chipsetButton, networkButton, operatingSystemButton,
                subCategory1, subCategory2, subCategory3, subCategory4, subCategory5, subCategory6 };
        majorButtons = new JButton[] { motherboardButton, CPUButton, chipsetButton, networkButton, operatingSystemButton };
        subButtons = new JButton[] { subCategory1, subCategory2, subCategory3, subCategory4, subCategory5, subCategory6 };

        activeMajorButtons = new boolean[5];
        major_index = minor_index = -1;

        DR = new DataReader();
        questions = DR.getQuestions();
        Utility.__initialization__(this, mainPanel, ColorValues.ROYAL_BLUE, Color.WHITE);
        __initialization__();
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);

        motherboardButton.addActionListener(e -> {
            resetColorMajorButtons();
            major_index = 0;
            motherboardButton.setBackground(Color.WHITE);
            motherboardButton.setForeground(ColorValues.ROYAL_BLUE);
            setSubCategories(major_index);
            checkAvailableCategories(0,6);
        });
        CPUButton.addActionListener(e -> {
            resetColorMajorButtons();
            major_index = 1;
            CPUButton.setBackground(Color.WHITE);
            CPUButton.setForeground(ColorValues.ROYAL_BLUE);
            setSubCategories(major_index);
            checkAvailableCategories(6, 3);
        });
        chipsetButton.addActionListener(e -> {
            resetColorMajorButtons();
            major_index = 2;
            chipsetButton.setBackground(Color.WHITE);
            chipsetButton.setForeground(ColorValues.ROYAL_BLUE);
            setSubCategories(major_index);
            checkAvailableCategories(9, 2);
        });
        networkButton.addActionListener(e -> {
            resetColorMajorButtons();
            major_index = 3;
            networkButton.setBackground(Color.WHITE);
            networkButton.setForeground(ColorValues.ROYAL_BLUE);
            setSubCategories(major_index);
            checkAvailableCategories(11, 1);
        });
        operatingSystemButton.addActionListener(e -> {
            resetColorMajorButtons();
            major_index = 4;
            operatingSystemButton.setBackground(Color.WHITE);
            operatingSystemButton.setForeground(ColorValues.ROYAL_BLUE);
            setSubCategories(major_index);
            checkAvailableCategories(12, 1);
        });


        subCategory1.addActionListener(e -> { minor_index = 0; prepareQuestionFrame(); });
        subCategory2.addActionListener(e -> { minor_index = 1; prepareQuestionFrame(); });
        subCategory3.addActionListener(e -> { minor_index = 2; prepareQuestionFrame(); });
        subCategory4.addActionListener(e -> { minor_index = 3; prepareQuestionFrame(); });
        subCategory5.addActionListener(e -> { minor_index = 4; prepareQuestionFrame(); });
        subCategory6.addActionListener(e -> { minor_index = 5; prepareQuestionFrame(); });


    }
    public void setSubCategories(int index){
        if(index == 0){
            subCategory1.setText("System Clock"); // 0
            subCategory2.setText("Expansion Slots");
            subCategory3.setText("Ports");
            subCategory4.setText("BIOS");
            subCategory5.setText("CMOS");
            subCategory6.setText("Bus Ports");
        }
        else if(index == 1){
            subCategory1.setText("Control Unit"); // 6
            subCategory2.setText("Arithmetic and Logic Unit");
            subCategory3.setText("Registers");
            subCategory4.setText("");
            subCategory5.setText("");
            subCategory6.setText("");
        }
        else if(index == 2){
            subCategory1.setText("Northbridge"); // 9
            subCategory2.setText("Southbridge");
            subCategory3.setText("");
            subCategory4.setText("");
            subCategory5.setText("");
            subCategory6.setText("");
        }
        else if(index == 3){
            subCategory1.setText("Hypertext Transfer Protocol"); // 11
            subCategory2.setText("");
            subCategory3.setText("");
            subCategory4.setText("");
            subCategory5.setText("");
            subCategory6.setText("");
        }
        else if(index == 4){
            subCategory1.setText("Ubuntu"); // 12
            subCategory2.setText("");
            subCategory3.setText("");
            subCategory4.setText("");
            subCategory5.setText("");
            subCategory6.setText("");
        }
    }
    public void checkAvailableCategories(int startingIndex, int size){
        for (int i = 0; i < size; i++) {
            if(questions[startingIndex + i] == -2) {
                subButtons[i].setEnabled(false);
                subButtons[i].setForeground(Color.RED);
            }
            if(questions[startingIndex + i] == -1) {
                subButtons[i].setForeground(Color.GREEN);
            }
        }
    }
    public void prepareQuestionFrame(){
        QuestionReader QR = new QuestionReader(0);

        int index = DR.getQuestionID(major_index + minor_index);
        Data.replace(Data.PROGRESS_dl, index + "");
        QuestionObject QO = QR.pickQuestion(index);
        dispose();
        QuestionFrame QF = new QuestionFrame(QO);
        QF.setVisible(true);
    }
    public void __initialization__(){

        for(JButton button: allButtons){
            Font thisFont = Utility.getDogicaFont();
            thisFont = thisFont.deriveFont(18f);
            titleLabel.setFont(thisFont);
            button.setFont(thisFont);
            button.setBackground(null);
            button.setFocusPainted(false);
            button.setBorder(null);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    new Sounds().hover_();
                    button.setBackground(Color.WHITE);
                    button.setForeground(ColorValues.CERULEAN_BLUE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    button.setBackground(null);
                    button.setForeground(Color.WHITE);
                }
            });
        }


    }

    public void resetColorMajorButtons(){
        for(JButton b: majorButtons){
            b.setBackground(null);
            b.setForeground(Color.WHITE);
        }
    }

    public static void main(String[] args) {
        CategoryFrame CF = new CategoryFrame();
        CF.setVisible(true);
    }
}
