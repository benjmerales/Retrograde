import javax.swing.*;

/***
 * The third power up for the game. Uses images instead of using the graphics method for painting the maze
 */
public class MazePowerFrame extends JFrame{
    private JButton upButton;
    private JButton leftButton;
    private JButton downButton;
    private JButton rightButton;
    private JPanel mainPanel;
    private JLabel mazeLabel;

    private int id;
    private int index;
    int image_state;
    int incorrect;
    JButton[] controlButtons;
    int[] solution={ 38, 39, 39, 37, 40, 37, 39, 39, 39, 37, 37, 37, 37, 39, 38,
            39, 38, 40, 38, 39, 39, 39, 38, 38, 39, 37, 39};
    public MazePowerFrame(int id){
        Utility.__initialization__(this, mainPanel, ColorValues.CHROME_WHITE, ColorValues.SHARK);

        int[] questions = new DataReader().getQuestions();
        this.id = id;
        incorrect = 0;
        for (int i = 0; i < questions.length; i++) {
            if(id == questions[i]){
                index = i;
                break;
            }
        }
        controlButtons = new JButton[]{upButton, downButton, leftButton, rightButton};
        image_state = 25;
        mazeLabel.setIcon(new ImageIcon(Utility.getThisResource("images/powerups/maze_imgs/" + image_state + ".png")));
        for(JButton button: controlButtons){
            button.addActionListener(e -> {
                if(button.getText().equals("Up")){ check(39); }
                if(button.getText().equals("Down")){ check(40); }
                if(button.getText().equals("Left")){ check(37); }
                if(button.getText().equals("Right")){ check(38); }
            });
        }
    }

    public int check(int value){
        if(value == solution[image_state-1]){
            image_state++;
            if(image_state == 28){
                JOptionPane.showMessageDialog(null, "You passed! You can now proceed to the next level.");
                dispose();
                new CategoryFrame();
                new DataReader().changeQuestionStatus(this.index, -1);
            }else{
                mazeLabel.setIcon(new ImageIcon(Utility.getThisResource("images/powerups/maze_imgs/" + (image_state) + ".png")));
            }
        }
        else{

            JOptionPane.showMessageDialog(null, "Wrong!");
            incorrect++;
            if(incorrect == 2) {
                new DataReader().changeQuestionStatus(this.index, -1);
                JOptionPane.showMessageDialog(null, "No more tries left!");
                dispose();
                new CategoryFrame();
            }
            mazeLabel.setIcon(new ImageIcon(Utility.getThisResource("images/powerups/maze_imgs/" + 1 + ".png")));
            image_state = 1;
        }
        return image_state;
    }

    public static void main(String[] args) {
        MazePowerFrame MPF = new MazePowerFrame('B');
        MPF.setVisible(true);
    }
}
