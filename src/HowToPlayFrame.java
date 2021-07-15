import javax.swing.*;

public class HowToPlayFrame extends JFrame{
    private JButton backButton;
    private JPanel mainPanel;
    private JTextArea HowToPlay;

    public HowToPlayFrame(){
        Utility.__initialization__(this, mainPanel, ColorValues.SHARK, ColorValues.CHROME_WHITE);
        Utility.resizeFont(mainPanel, 14f);
        HowToPlay.setText("You are the hacker in this game, who needs to restore a retro computer. \n" +
                "The computer will be divided into 5 main parts; The Motherboard, Central Processing Unit (CPU), Chipset, Network, and Operating System. " +
                "Each part will correspond to the following categories respectively; Theoretical, and Programming Questions, with the following subtopics: Java, C, Python, and HTML." +
                " Upon starting the game, the category pick shall be displayed, consisting of The Motherboard, Central Processing Unit (CPU), Chipset, Network, and Operating System. The player shall be given the chance whichever category he/she wants to answer first. The questions are ready to be chosen from the question bin, which are displayed through the question’s category. The question bin would have all the compiled questions that includes the topic, choices, answer and the question itself. It would then act as our database for our questions. When the player then chooses a category, the question would be displayed along with its choices. Unlike “Are you Smarter Than A 5th Grader?” wherein the player is given a question with either true/false, a three-answered multiple-choice or a short-answered, in our game, all questions will consist of four-answered multiple choice. If the player chooses correctly, the level of restoration will increase based on their corresponding percentage (Refer back to scoring system), then he/she would go back to the category frame and will have to choose among the remaining category/subcategories. If the player chooses incorrectly however, he/she would redirect back again to the category frame but won’t be able to answer the same question again in that category and the level of restoration will not go up. If the player accumulates a certain number of incorrect answers, the system will be corrupted and the computer will crash, thus the game will be over. If “Are you Smarter Than A 5th Grader?” has a million-dollar question, our game also has something similar. If the level of completion reaches 100%, a bonus question appears, wherein the player shall now guess the password of the computer, with only 3 attempts. A hint will also appear on the screen.   If the player guesses the correct password, a bonus minigame will be available at the menu: Pong. We chose to include this in our game because Pong is a codable game that is versatile to almost all programming languages, from C to HTML. The retro computer shall also ask for the player's name and shall be recorded in the list of white hatters. ");
        backButton.addActionListener(e -> {
            dispose();
            MenuFrame MF = new MenuFrame();
            MF.setVisible(true);
        });
    }

    public static void main(String[] args) {
        HowToPlayFrame hf = new HowToPlayFrame();
        hf.setVisible(true);
    }
}
