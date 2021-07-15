import javax.swing.*;
import java.awt.*;

public class MenuFrameII {

    public MenuFrameII(){
        __setupUI__();
    }

    public void __setupUI__(){
        JFrame frame = new JFrame();
        JPanel mainPanel = new JPanel();

        frame.setContentPane(mainPanel);
        frame.setDefaultLookAndFeelDecorated(false);
        mainPanel.setLayout(null);

        JLabel backgroundLabel = new JLabel();;
        JButton startButton = new JButton("Start");
        JButton continueButton = new JButton("Continue");
        JButton howButton = new JButton("How to Play");
        JButton highButton =  new JButton("White Hatters");
        JButton pongButton =  new JButton("???");
        JButton exitButton =  new JButton("Exit");

        backgroundLabel.setIcon(new ImageIcon(Utility.getThisResource("images/title.png")));

        mainPanel.add(backgroundLabel);
        mainPanel.add(startButton);
        mainPanel.add(continueButton);
        mainPanel.add(howButton);
        mainPanel.add(highButton);
        mainPanel.add(pongButton);
        mainPanel.add(exitButton);

        Insets insets = mainPanel.getInsets();

        Dimension size;

        size = backgroundLabel.getPreferredSize();
        backgroundLabel.setBounds(250 + insets.left, 125 + insets.top, 100, 100);

        size = startButton.getPreferredSize();
        startButton.setBounds(675 + insets.left, 354 + insets.top, size.width,size.height);

        size = continueButton.getPreferredSize();
        continueButton.setBounds(675 + insets.left, 432 + insets.top, size.width,size.height);

        size = howButton.getPreferredSize();
        howButton.setBounds(675 + insets.left, 150 + insets.top, size.width,size.height);

        size = highButton.getPreferredSize();
        highButton.setBounds(675 + insets.left, 175 + insets.top, size.width,size.height);

        size = pongButton.getPreferredSize();
        pongButton.setBounds(675 + insets.left, 200 + insets.top, size.width,size.height);

        size = exitButton.getPreferredSize();
        exitButton.setBounds(675 + insets.left, 225 + insets.top, size.width,size.height);



       /* mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                try{
                    Thread.sleep(500);

                    int x_2 = MouseInfo.getPointerInfo().getLocation().x;
                    int y_2 = MouseInfo.getPointerInfo().getLocation().y;

                    mainPanel.setToolTipText("x: " + x_2 + "y: " + y_2);
                    Dimension size = startButton.getPreferredSize();

//                        startButton.setBounds(x_2, 100, size.width,size.height); // TODO: CHANGE/DELETE This code to final
                }catch(InterruptedException f){
                    f.printStackTrace();
                }
            }
        });*/
        Utility.__initialization__(frame, mainPanel, ColorValues.CHROME_WHITE, ColorValues.MOUNTAIN_MEADOW);
        startButton.setBackground(null);
        startButton.setForeground(Color.BLACK);
        frame.setMinimumSize(new Dimension(500,500));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        MenuFrameII MF = new MenuFrameII();
    }
}
