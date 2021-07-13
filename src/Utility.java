import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

interface ThemeValues {
    Font font = (new Font("Segoe UI", Font.PLAIN, 24));
    Color ROYAL_BLUE = new Color(29, 87, 235);
    Color CERULEAN_BLUE = new Color(52,102, 192);
    Color BLUE_CHILL = new Color(14, 167, 172);
    Color MOUNTAIN_MEADOW = new Color(34, 181, 146);
    Color MALACHITE = new Color(25, 235, 130);
    Color SHARK = new Color(38, 39, 48);
    Color MAVERICK = new Color(215, 192, 208);
    Color CHROME_WHITE = new Color(239, 240, 209);
}
public interface Utility {
    static void __initialization__(JFrame frame, JPanel panel, Color background, Color foreground){
        frame.setIconImage(new ImageIcon(Utility.getThisResource("images/icon.png")).getImage());
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Retrograde");
        panel.setBackground(background);
        styleComponents(panel.getComponents(),background, foreground);
//        recheckScreen(frame);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
    }
    /*static void recheckScreen(JFrame frame){
        boolean isFullScreen = DataEdit.getDataLineAt(DataEdit.FULLSCREEN_dl).contains("ON");
        if(!isFullScreen){
            frame.pack();
        }else if(isFullScreen){
            frame.setUndecorated(true);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }else{
            System.out.println("ERROR CANT FIND SPECIFIED FILE");
        }
    }*/
    static Font getDogicaFont(){
        InputStream IS = Utility.class.getResourceAsStream("resources/fonts/dogica.ttf");
        Font font = null;
        try{
            font = Font.createFont(Font.TRUETYPE_FONT, IS);
        }catch (IOException e){
            e.printStackTrace();
        }catch (FontFormatException e){
            e.printStackTrace();
        }
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
                b.addActionListener(e -> {
                    new Sounds().click_();
                });
            }
        }
    }

    static URL getThisResource(String str){
        return Utility.class.getResource("resources/" + str);
    }

    static boolean isNull(String str){
        if (str.equals("") || str.equals(" ") || str == null){
            return true;
        }
        else if(!str.equals("") && !str.equals(" ") || str != null){
            return false;
        }
        else{
            System.out.println("ERROR ON ISNULL RETURNING FALSE BREAKER");
            return false;
        }
    }
}
