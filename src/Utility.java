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
    // Initial code for most frames. Includes coloring and setting frame properties
    static void __initialization__(JFrame frame, JPanel panel, Color background, Color foreground){
        frame.setContentPane(panel);
        frameProperties(frame);
        frame.setBackground(background);
        panel.setBackground(background);
        styleComponents(panel.getComponents(),background, foreground);
    }

    // In __initialization__. all frame code
    private static void frameProperties(JFrame frame){
        frame.setIconImage(new ImageIcon(Utility.getThisResource("images/icon.png")).getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Retrograde");
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    // Get custom font
    static Font getMinecraftiaFont(){
        InputStream IS = Utility.class.getResourceAsStream( "resources/fonts/minecraftia.ttf");
        Font font = null;
        try{
            font = Font.createFont(Font.TRUETYPE_FONT, IS);
        }catch (IOException | FontFormatException e){
            e.printStackTrace();
        }
        assert font != null;
        font = font.deriveFont(21f);
        return font;
    }
    static Font getMinecraftiaFont(int size){
        InputStream IS = Utility.class.getResourceAsStream( "resources/fonts/minecraftia.ttf");
        Font font = null;
        try{
            font = Font.createFont(Font.TRUETYPE_FONT, IS);
        }catch (IOException | FontFormatException e){
            e.printStackTrace();
        }
        assert font != null;
        font = font.deriveFont(size);
        return font;
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
            font = font.deriveFont(18f);
            return font;
        }
        static Font getDogicaFont(float fontSize){
            InputStream IS = Utility.class.getResourceAsStream( "resources/fonts/dogica.ttf");
            Font font = null;
            try{
                font = Font.createFont(Font.TRUETYPE_FONT, IS);
            }catch (IOException | FontFormatException e){
                e.printStackTrace();
            }
            assert font != null;
            font = font.deriveFont(fontSize);
            return font;
        }
    private static void styleComponents(Component[] C, Color background, Color foreground){
        for(Component c: C){
            String name = c.getClass().getName();
            c.setFont(Utility.getMinecraftiaFont());
            c.setForeground(foreground);
            if(name.contains("JButton")){
                JButton b = (JButton) c;
                b.setContentAreaFilled(false);
                b.setFocusPainted(false);
                b.setFocusable(false);
                b.setBorderPainted(false);
                b.addActionListener(e -> new Sounds().click_());
            }
        }
    }
    static void moveComponent(JPanel thisPanel, Component component, int x, int y){
        Insets insets = new Insets(0, 0, 0, 0);
        Dimension size = component.getPreferredSize();
        component.setBounds(x+insets.left, y+insets.top, size.width, size.height);
        thisPanel.add(component);
    }
    static void moveComponent(JPanel thisPanel, Component component, int x, int y, int width, int height) {
        Insets insets = new Insets(0, 0, 0, 0);
        Dimension size = component.getPreferredSize();
        int w,h;
        if(width == 0) w = size.width; else w = width;
        if(height == 0) h = size.height; else h = height;
        component.setBounds(x+insets.left, y+insets.top, w, h);
        thisPanel.add(component);
    }

    static URL getThisResource(String str){
        return Utility.class.getResource("resources/" + str);
    }
}
