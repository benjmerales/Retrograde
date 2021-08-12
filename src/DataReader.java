import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * DATA FILE FORMAT
 *      0 - Data Game File - DO NOT EDIT*
 *      1 - Total Questions
 *      2 - Current Points of the game
 *      3 - Fullscreen settings
 *      4 -
 */
interface Data {
    //DataReader.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "resources/data/
    String PATH = "src/resources/";
    String DATA = PATH + "data/data.txt";
    int DESCRIPTION_dl = 0;
    int TOTAL_QUESTIONS_dl = 1;
    int CURRENT_INDEX_dl = 2;
    int QUESTIONS_dl = 3;
    int POWERS_dl = 4;
    int FULLSCREEN_dl = 5;

    static String get(int index){
        try{
            File dataFile = new File(DATA);
            Scanner S = new Scanner(dataFile);
            int counter = 0;
            while(S.hasNextLine()){
                String data = S.nextLine();
                if(counter == index){
                    return data;
                }
                counter++;
            }
            S.close();
        }catch (FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "ERROR v.1.1 Can't Find data Data File");
            System.out.println("An Error Occured.");
            e.printStackTrace();
        }
        return null;
    }
    static int strip(String str){

        return Integer.parseInt(str.replaceAll("[^0-9]", ""));
    }
    static int[] strip(String[] str){
        int[] array = new int[str.length];

        for (int i = 0; i < str.length; i++) {
//            array[i] = Integer.parseInt(str[i].replaceAll("[^0-9]", ""));
            array[i] = Integer.parseInt(str[i]);
        }
        return array;
    }
    static void replace(int index, String str) {
        try{
            File dataFile = new File(DATA);
            Scanner S = new Scanner(dataFile);
            ArrayList<String> allLines = new ArrayList<>();
            int counter = 0;
            while(S.hasNextLine()){
                if(counter == index){
                    allLines.add(str);
                    S.nextLine();
                }else{
                    allLines.add(S.nextLine());
                }
                counter++;
            }
            S.close();
            FileWriter dataFileNew = new FileWriter(DATA);
            for(String i: allLines){
                dataFileNew.write(i + "\n");
            }
            dataFileNew.close();
        }catch (FileNotFoundException e){
            System.out.println("We can't find the file.");
            e.printStackTrace();
        }catch(IOException e){
            System.out.println("There was an error");
            e.printStackTrace();
        }
    }

}

public class DataReader {
    String description;
    int sizeQuestions;
    int currentIndex;
    int questions[];
    int powers[];
    boolean fullScreen;

    public DataReader(){
        this.description = Data.get(0);
        this.sizeQuestions = Data.strip(Objects.requireNonNull(Data.get(1)));
        this.currentIndex = Data.strip(Data.get(Data.CURRENT_INDEX_dl));
        if(Data.get(Data.QUESTIONS_dl).equals("")){
            System.out.println("ERROR on DATAREADER: getting question line is null, initializing new questions");
            this.questions = new int[13];
        }else{
            this.questions = Data.strip(Data.get(Data.QUESTIONS_dl).split(" "));
        }
        this.powers = Data.strip(Data.get(Data.POWERS_dl).split(" "));
        this.fullScreen = Data.get(Data.FULLSCREEN_dl).contains("ON");
    }

    public boolean checkPowerIndex(int index){
        if(powers[index] == 1){
            return true;
        }else{
            return false;
        }
    }
    public boolean changeScreenStatus(){
        if(isFullScreen()){
            Data.replace(Data.FULLSCREEN_dl, "OFF");
        }else{
            Data.replace(Data.FULLSCREEN_dl, "ON");
        }
        this.fullScreen = !fullScreen;
        return fullScreen;
    }
    public boolean changePowersStatus(int index){
        if(checkPowerIndex(index))
            powers[index] = 0;
        else
            powers[index] = 1;
        String temp = "";
        for(int i: powers){
            temp += i + " ";
        }
        Data.replace(Data.POWERS_dl, temp);
        return checkPowerIndex(index);
    }
    public void changeQuestionStatus(int index, int status){
        questions[index] = status;
        String temp = "";
        for(int i: questions){
            temp += i + " ";
        }
        Data.replace(Data.QUESTIONS_dl, temp);
    }
    public int getQuestionID(int index){
        return questions[index];
    }
    public void newQuestions(){
        String question_line = "";
        int max = Data.strip(Objects.requireNonNull(Data.get(Data.TOTAL_QUESTIONS_dl)));
        Data.replace(Data.QUESTIONS_dl, " ");
        this.questions = new int[13];
        for (int i = 0; i < 13; i++) {
            question_line += getRandomExcept(question_line, max)+ " ";
        }
        System.out.println("Questions gathered for this run: " + question_line);
        this.questions = Data.strip(question_line.split(" "));
        Data.replace(Data.QUESTIONS_dl, question_line);
    }
    public int getRandomExcept(String questions, int max){
        Random R = new Random();
        if(questions.equals("")) return R.nextInt(max);
        String[] splitted = questions.split(" ");
        int len = splitted.length;
        ArrayList foo = new ArrayList();
        for (int i = 0; i < len; i++) {
            foo.add(Integer.parseInt(splitted[i]));
        }
        int x = 0;
        do{
            x = R.nextInt(max);
        }while(foo.contains(x));
        return x;
    }
    public void reset(){
        try {
            FileWriter dataFileNew = new FileWriter(Data.DATA);
            dataFileNew.write("Retrograde Data Game File\n");
            dataFileNew.write("Total Questions: 40\n");
            dataFileNew.write("0\n");
            dataFileNew.write("\n");
            dataFileNew.write("1 1 1\n");
            dataFileNew.write("OFF\n");
            dataFileNew.close();
        }catch (IOException e){
            System.out.println("ERROR in WRITING FILE.");
            e.printStackTrace();
        }
        newQuestions();
    }

    public static void main(String[] args) {
        new DataReader().changePowersStatus(0);
    }
    //<editor-fold desc="- - GETTERS - -">
    public String getDescription() {
        return description;
    }
    public int getSizeQuestions() {
        return sizeQuestions;
    }
    public boolean isFullScreen() {
        return fullScreen;
    }
    public int getCurrentIndex() {
        return currentIndex;
    }
    public int[] getQuestions() {
        return questions;
    }
    public int[] getPowers() {
        return powers;
    }
    //</editor-fold>

}
