import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class CategoryFrame extends JPanel {
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

    public JButton[] allButtons;
    public JButton[] majorButtons;
    public JButton[] subButtons;
    public boolean[] activeMajorButtons;

    public int major_index;
    public int minor_index;
    int[] questions;
    DataReader DR;

    JFrame frame;
    Insets insets;
    public CategoryFrame(){
        activeMajorButtons = new boolean[5];
        major_index = minor_index = -1;

        frame = new JFrame();
        DR = new DataReader();
        questions = DR.getQuestions();

        __setupUI__PANEL__();
        Utility.__initialization__(frame, this, ColorValues.ROYAL_BLUE, Color.WHITE);
        __initialization__();
        actions();
        checkSubCategoryIfAvailable();
        frame.setVisible(true);

    }
    public void checkSubCategoryIfAvailable(){
        for(JButton i: subButtons){
            String text = i.getText();
            if(text.isEmpty()) i.setVisible(false);
            else i.setVisible(true);
        }
    }
    public void actions(){
        motherboardButton.addActionListener(e -> {
            resetColorMajorButtons();
            major_index = 0;
            motherboardButton.setBackground(Color.WHITE);
            motherboardButton.setForeground(ColorValues.ROYAL_BLUE);
            setSubCategories(major_index);
            checkAvailableCategories(0,6);
            checkSubCategoryIfAvailable();
        });
        CPUButton.addActionListener(e -> {
            resetColorMajorButtons();
            major_index = 1;
            CPUButton.setBackground(Color.WHITE);
            CPUButton.setForeground(ColorValues.ROYAL_BLUE);
            setSubCategories(major_index);
            checkAvailableCategories(6, 3);
            checkSubCategoryIfAvailable();
        });
        chipsetButton.addActionListener(e -> {
            resetColorMajorButtons();
            major_index = 2;
            chipsetButton.setBackground(Color.WHITE);
            chipsetButton.setForeground(ColorValues.ROYAL_BLUE);
            setSubCategories(major_index);
            checkAvailableCategories(9, 2);
            checkSubCategoryIfAvailable();
        });
        networkButton.addActionListener(e -> {
            resetColorMajorButtons();
            major_index = 3;
            networkButton.setBackground(Color.WHITE);
            networkButton.setForeground(ColorValues.ROYAL_BLUE);
            setSubCategories(major_index);
            checkAvailableCategories(11, 1);
            checkSubCategoryIfAvailable();
        });
        operatingSystemButton.addActionListener(e -> {
            resetColorMajorButtons();
            major_index = 4;
            operatingSystemButton.setBackground(Color.WHITE);
            operatingSystemButton.setForeground(ColorValues.ROYAL_BLUE);
            setSubCategories(major_index);
            checkAvailableCategories(12, 1);
            checkSubCategoryIfAvailable();
        });

        subCategory1.addActionListener(e -> {
            minor_index = 0;
            if(isButtonUncleared(subButtons[minor_index]))
                prepareQuestionFrame();
        });
        subCategory2.addActionListener(e -> {
            minor_index = 1;
            if(isButtonUncleared(subButtons[minor_index]))
                prepareQuestionFrame();
        });
        subCategory3.addActionListener(e -> {
            minor_index = 2;
            if(isButtonUncleared(subButtons[minor_index]))
                prepareQuestionFrame();
        });
        subCategory4.addActionListener(e -> {
            minor_index = 3;
            if(isButtonUncleared(subButtons[minor_index]))
                prepareQuestionFrame();
        });
        subCategory5.addActionListener(e -> {
            minor_index = 4;
            if(isButtonUncleared(subButtons[minor_index]))
                prepareQuestionFrame();
        });
        subCategory6.addActionListener(e -> {
            minor_index = 5;
            if(isButtonUncleared(subButtons[minor_index]))
                prepareQuestionFrame();
        });
    }


    public boolean isButtonUncleared(JButton button){
        if(button.getForeground() != Color.LIGHT_GRAY && button.getForeground() != ColorValues.MALACHITE)
            return true;
        else return false;
    }
    public void setSubCategories(int index){
        if(index == 0){
            subCategory1.setText("   System Clock"); // 0
            subCategory2.setText("   Expansion Slots");
            subCategory3.setText("   Ports");
            subCategory4.setText("   BIOS");
            subCategory5.setText("   CMOS");
            subCategory6.setText("   Bus Ports");
        }
        else if(index == 1){
            subCategory1.setText("   Control Unit"); // 6
            subCategory2.setText("   Arithmetic and Logic Unit");
            subCategory3.setText("   Registers");
            subCategory4.setText("");
            subCategory5.setText("");
            subCategory6.setText("");
        }
        else if(index == 2){
            subCategory1.setText("   Northbridge"); // 9
            subCategory2.setText("   Southbridge");
            subCategory3.setText("");
            subCategory4.setText("");
            subCategory5.setText("");
            subCategory6.setText("");
        }
        else if(index == 3){
            subCategory1.setText("   Connect to a network"); // 11
            subCategory2.setText("");
            subCategory3.setText("");
            subCategory4.setText("");
            subCategory5.setText("");
            subCategory6.setText("");
        }
        else if(index == 4){
            subCategory1.setText("   Ubuntu"); // 12
            subCategory2.setText("");
            subCategory3.setText("");
            subCategory4.setText("");
            subCategory5.setText("");
            subCategory6.setText("");
        }
    }
    public void checkAvailableCategories(int startingIndex, int size){
        for (int i = 0; i < size; i++) {
            int current_data_val = questions[startingIndex + i];
            if(current_data_val == -2) {
                subButtons[i].setForeground(Color.LIGHT_GRAY);
            }
            else if(current_data_val == -1) {
                subButtons[i].setForeground(ColorValues.MALACHITE);
            }
            else{
                subButtons[i].setForeground(Color.WHITE);
            }
        }
    }
    public void prepareQuestionFrame(){
        QuestionReader QR = new QuestionReader(0);
        if(major_index == 1) major_index = 6;
        else if(major_index == 2) major_index = 9;
        else if(major_index == 3) major_index = 11;
        else if(major_index == 4) major_index = 12;

        int index = major_index + minor_index;
        Data.replace(Data.CURRENT_INDEX_dl, index + "");
        QuestionObject QO = QR.pickQuestion(DR.getQuestionID(index));
        frame.dispose();
        new QuestionFrame(QO);
    }
    public void __initialization__(){
        for(JButton button: allButtons){
            Font thisFont = Utility.getDogicaFont(12);
            thisFont = thisFont.deriveFont(18f);
            button.setContentAreaFilled(false);
            button.setFont(thisFont);
            button.setBackground(null);
            button.setFocusPainted(false);
            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.setBorder(null);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    new Sounds().hover_();
                    button.setContentAreaFilled(true);
                    button.setBackground(Color.WHITE);

                    if(button.getForeground() != Color.LIGHT_GRAY && button.getForeground() != ColorValues.MALACHITE){
                        button.setForeground(ColorValues.CERULEAN_BLUE);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    button.setContentAreaFilled(false);
                    if(button.getForeground() != Color.LIGHT_GRAY && button.getForeground() != ColorValues.MALACHITE){
                        button.setForeground(Color.WHITE);
                    }
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

    public void __setupUI__PANEL__(){
        insets = frame.getInsets();

        motherboardButton = new JButton("   MOTHERBOARD :");
        CPUButton = new JButton("   CPU :");
        chipsetButton = new JButton("   CHIPSET :");
        networkButton = new JButton("   NETWORK :");
        operatingSystemButton = new JButton("   OPERATING SYSTEM :");

        subCategory1 = new JButton("");
        subCategory2 = new JButton("");
        subCategory3 = new JButton("");
        subCategory4 = new JButton("");
        subCategory5 = new JButton("");
        subCategory6 = new JButton("");

        allButtons = new JButton[] { motherboardButton, CPUButton, chipsetButton, networkButton, operatingSystemButton,
                subCategory1, subCategory2, subCategory3, subCategory4, subCategory5, subCategory6 };
        majorButtons = new JButton[] { motherboardButton, CPUButton, chipsetButton, networkButton, operatingSystemButton };
        subButtons = new JButton[] { subCategory1, subCategory2, subCategory3, subCategory4, subCategory5, subCategory6 };

        this.setLayout(null);


        int x_init = 250; int y_init = 140; int y_gap = 50; int max_width = 400;

        Utility.moveComponent(this, motherboardButton, x_init, y_init + (y_gap*1), max_width, 0   );
        Utility.moveComponent(this, CPUButton, x_init, y_init + (y_gap*2), max_width, 0);
        Utility.moveComponent(this, chipsetButton, x_init, y_init + (y_gap*3), max_width, 0);
        Utility.moveComponent(this, networkButton, x_init, y_init + (y_gap*4), max_width, 0);
        Utility.moveComponent(this, operatingSystemButton, x_init, y_init + (y_gap*5), max_width, 0);

        x_init = 770; y_init = 141; y_gap = 50;

        Utility.moveComponent(this, subCategory1, x_init, y_init + (y_gap*1), max_width, 25);
        Utility.moveComponent(this, subCategory2, x_init, y_init + (y_gap*2), max_width, 25);
        Utility.moveComponent(this, subCategory3, x_init, y_init + (y_gap*3), max_width, 25);
        Utility.moveComponent(this, subCategory4, x_init, y_init + (y_gap*4), max_width, 25);
        Utility.moveComponent(this, subCategory5, x_init, y_init + (y_gap*5), max_width, 25);
        Utility.moveComponent(this, subCategory6, x_init, y_init + (y_gap*6), max_width, 25);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Image bg = null;
        try{
            bg = ImageIO.read(new File(Data.PATH + "images/categ.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        g.drawImage(bg, 0,0,null);
    }

    public static void main(String[] args) {
        new CategoryFrame();
    }
}
