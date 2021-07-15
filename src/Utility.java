import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

interface ColorValues {
    Color ROYAL_BLUE = new Color(29, 87, 235);
    Color CERULEAN_BLUE = new Color(52,102, 192);
    Color BLUE_CHILL = new Color(14, 167, 172);
    Color MOUNTAIN_MEADOW = new Color(34, 181, 146);
    Color MALACHITE = new Color(25, 235, 130);
    Color SHARK = new Color(38, 39, 48);
    Color MAVERICK = new Color(215, 192, 208);
    Color CHROME_WHITE = new Color(239, 240, 209);
    Color CYAN = Color.CYAN;
}
public interface Utility {
    static void __initialization__(JFrame frame, JPanel panel, Color background, Color foreground){
        frame.setIconImage(new ImageIcon(Utility.getThisResource("images/icon.png")).getImage());
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Retrograde");
        panel.setBackground(background);
        styleComponents(panel.getComponents(),background, foreground);
        recheckScreen(frame);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
    }
    static void recheckScreen(JFrame frame){
        boolean isFullScreen = Objects.requireNonNull(Data.get(Data.FULLSCREEN_dl)).contains("ON");
        if(!isFullScreen){
            frame.pack();
        }else {
            frame.setUndecorated(true);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
    }
    static Font getDogicaFont(){
        InputStream IS = Utility.class.getResourceAsStream("resources/fonts/dogica.ttf");
        Font font = null;
        try{
            font = Font.createFont(Font.TRUETYPE_FONT, IS);
        }catch (IOException | FontFormatException e){
            e.printStackTrace();
        }
        assert font != null;
        font = font.deriveFont(36f);
        return font;
    }
    static void styleComponents(Component[] C, Color background, Color foreground){
        for(Component c: C){
            String name = c.getClass().getName();
            c.setFont(Utility.getDogicaFont());
            c.setBackground(background);
            c.setForeground(foreground);
            if(name.contains("JButton")){
                JButton b = (JButton) c;
                b.setFocusPainted(false);
                b.setFocusable(false);
                b.setBorderPainted(false);
                b.addActionListener(e -> new Sounds().click_());
            }
        }
    }
    static void resizeFont(JPanel panel, float size){
        for(Component c: panel.getComponents()){
            c.setFont(Utility.getDogicaFont().deriveFont(size));
        }
    }
    static URL getThisResource(String str){
        return Utility.class.getResource("resources/" + str);
    }
}
