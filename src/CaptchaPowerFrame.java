import javax.swing.*;

/***
 * The 2nd power up of the game. Like an ordinary CAPTCHA, enter the characters as specified
 */
public class CaptchaPowerFrame extends JFrame{
    private JButton skipButton;
    private JButton enterButton;
    private JTextField enterField;
    private JLabel captchaLabel;
    private JPanel mainPanel;
    private JLabel notifLabel;

    public String[] captcha_names = new String[] {"captcha0.png", "captcha1.png", "captcha2.png",
                                                  "captcha3.png", "captcha4.png", "captcha5.png"};
    public char answer;
    public int counter;
    public CaptchaPowerFrame(char correct_answer){
        Utility.__initialization__(this, mainPanel, ColorValues.SHARK, ColorValues.CHROME_WHITE);
        this.setSize(800,200);
        this.setExtendedState(0);
        this.setLocationRelativeTo(null);
        enterField.grabFocus();
        counter = 0;
        this.answer = correct_answer;
        notifLabel.setVisible(false);
        enterField.setForeground(ColorValues.MOUNTAIN_MEADOW);
        captchaLabel.setIcon(new ImageIcon(Utility.getThisResource("images/powerups/captchas/" + captcha_names[counter])));
        enterButton.addActionListener(e -> verify());
        skipButton.addActionListener(e -> {
            if (counter != 5) {
                counter++;
                notifLabel.setVisible(true);
                notifLabel.setText("You have " + (6-counter) + " tries left!");
                captchaLabel.setIcon(new ImageIcon(Utility.getThisResource("images/powerups/captchas/" + captcha_names[counter])));
            }else{
                notifLabel.setText("This is your last try!");
            }
        });
        enterField.addActionListener(e -> verify());
    }

    public void verify(){
        if( (counter == 0 && enterField.getText().equals("HWVC5U")) ||
                (counter == 1 && enterField.getText().equals("rntbY")) ||
                (counter == 2 && enterField.getText().equals("6T9JBCDS")) ||
                (counter == 3 && enterField.getText().equals("4")) ||
                (counter == 4 && enterField.getText().equals("ARP3CT")) ||
                (counter == 5 && enterField.getText().equals("WNHK6")) ){
            JOptionPane.showMessageDialog(null, "The Correct Answer is " + answer);
            dispose();
        }else{
            counter++;
            if(6-counter == 0){
                JOptionPane.showMessageDialog(null, "You are a robot");
                dispose();
            }
            else{
                notifLabel.setVisible(true);
                notifLabel.setText("Incorrect! You have " + (6-counter) + " tries left!");
                enterField.setText("");
                captchaLabel.setIcon(new ImageIcon(Utility.getThisResource("images/powerups/captchas/" + captcha_names[counter])));
            }
        }
    }
    public static void main(String[] args) {
        CaptchaPowerFrame CPF = new CaptchaPowerFrame('A');
        CPF.setVisible(true);
    }
}
