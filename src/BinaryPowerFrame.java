import javax.swing.*;
import java.util.Random;

/***
 * The 1st power up of the game. The usual decimal to binary puzzle game.
 */
public class BinaryPowerFrame extends JFrame{
    private JPanel mainPanel;
    private JButton submitButton;
    private JButton _64Button;
    private JButton _32Button;
    private JButton _16Button;
    private JButton _8Button;
    private JButton _4Button;
    private JButton _2Button;
    private JButton _1Button;
    private JLabel decimalLabel;

    Random R;
    int decimal;
    int total;
    JButton[] binaryNumbers;
    public BinaryPowerFrame(char correct_answer){
        Utility.__initialization__(this, mainPanel, ColorValues.SHARK, ColorValues.CHROME_WHITE);
        this.setSize(800,200);
        this.setExtendedState(0);
        this.setLocationRelativeTo(null);
        R = new Random();
        binaryNumbers = new JButton[] {_64Button, _32Button, _16Button,_8Button, _4Button,_2Button,_1Button};
        decimal = R.nextInt(128);
        decimalLabel.setText(decimal + "");

        for (JButton binary : binaryNumbers){
            binary.addActionListener(e -> {
                if(binary.getText().contains("0")) binary.setText("1");
                else binary.setText("0");
            });
        }

        submitButton.addActionListener(e -> {
            calculate();
            if(total == decimal){
                JOptionPane.showMessageDialog(null, "The Answer is " + correct_answer);
            }else{
                JOptionPane.showMessageDialog(null, "Incorrect!!");
            }
            new DataReader().changePowersStatus(1);
            dispose();
        });
    }

    public void calculate(){
        int _1 = (_1Button.getText().contains("1")) ? 1 : 0;
        int _2 = (_2Button.getText().contains("1")) ? 2 : 0;
        int _4 = (_4Button.getText().contains("1")) ? 4 : 0;
        int _8 = (_8Button.getText().contains("1")) ? 8 : 0;
        int _16 = (_16Button.getText().contains("1")) ? 16 : 0;
        int _32 = (_32Button.getText().contains("1")) ? 32 : 0;
        int _64 = (_64Button.getText().contains("1")) ? 64 : 0;

        total = _1 + _2 + _4 + _8 + _16 + _32 + _64;
    }

    public static void main(String[] args) {
        BinaryPowerFrame BPF = new BinaryPowerFrame('A');
        BPF.setVisible(true);
    }
}
