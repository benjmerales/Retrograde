import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SnippetFrame extends JPanel {
    private JLabel headerLabel1;
    private JLabel headerLabel2;
    private JLabel headerLabel3;
    private JTextPane snippetPane;
//    private JScrollPane scrollPane;
    private String SNIPPET_PATH;
    JFrame frame;
    public SnippetFrame(String path){
        this.SNIPPET_PATH = path;
        frame = new JFrame();
        frame.setIconImage(new ImageIcon(Utility.getThisResource("images/cmd.png")).getImage());
        frame.setTitle("C:\\WINDOWS\\system32\\cmd.exe");
        frame.setSize(975,475);
        frame.setContentPane(this);
        frame.setLocationRelativeTo(null);
        __setupUI__PANEL__();
        frame.setVisible(true);
    }
    public void getSnippet(){
        try{
            File dataFile = new File(SNIPPET_PATH);
            Scanner S = new Scanner(dataFile);
            while(S.hasNextLine()){
                String data = S.nextLine();
                snippetPane.setText(snippetPane.getText() + data + "\n");
            }
            S.close();
        }catch (FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "ERROR v.1.7 Can't Find data Snippet File named " + SNIPPET_PATH);
            System.out.println("An Error Occured.");
            e.printStackTrace();
        }
    }
    public void __setupUI__PANEL__(){
        headerLabel1 = new JLabel("Microsoft Windows [Version 10.0.19041.1110]" );
        headerLabel2 = new JLabel("(c) Microsoft Corporation. All rights reserved.");
        headerLabel3 = new JLabel("D:\\Documents\\SS 2020-2021 Teaching\\CMSC 13 Assignment\\Assignment_4\\Merales_nth\\Retrograde\\resources\\snippet\\snippet.exe");

        snippetPane = new JTextPane();
//        scrollPane = new JScrollPane(snippetPane);

        this.setLayout(null);
        this.setBackground(Color.BLACK);

        getSnippet();
        headerLabel1.setForeground(Color.WHITE);
        headerLabel2.setForeground(Color.WHITE);
        headerLabel3.setForeground(Color.WHITE);
        headerLabel1.setFont(new Font("Typeface", Font.PLAIN, 14));
        headerLabel2.setFont(new Font("Typeface", Font.PLAIN, 14));
        headerLabel3.setFont(new Font("Typeface", Font.PLAIN, 14));
        snippetPane.setFont(new Font("Typeface", Font.PLAIN, 14));
        snippetPane.setBackground(Color.BLACK);
        snippetPane.setForeground(Color.WHITE);
        snippetPane.setCaretPosition(snippetPane.getText().length()-1);
        Utility.moveComponent(this, headerLabel1, 0,0);
        Utility.moveComponent(this, headerLabel2, 0,14);
        Utility.moveComponent(this, headerLabel3, 0,52);
        Utility.moveComponent(this, snippetPane, 100,75, 800, 500);
//        Utility.moveComponent(this, scrollPane, 0, 0, 20, 800);
    }
    public void __initialization__() {
        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
        snippetPane.setParagraphAttributes(attribs, true);
        snippetPane.setFocusable(false);
        snippetPane.setForeground(Color.WHITE);
        snippetPane.setCharacterAttributes(attribs, true);
    }
    public static void main(String[] args) {
        new SnippetFrame(Data.PATH + "snippet/snippet_0.txt");
    }
}
