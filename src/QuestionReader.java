import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

class QuestionObject {
    public int id;
    public String category;
    public String question;
    public String[] choices;
    public char correct;
    //    public String questionType;
    public boolean hasCodeSnippet;
    // ID	Category	Question	A	B	C	D	Correct Answer      questionType   isThereACodeSnippet?
    public QuestionObject(String[] compiled){
        this.id = Integer.parseInt(compiled[0]);
        this.category = compiled[1];
        this.question = compiled[2];
        this.choices = new String[] {compiled[3], compiled[4], compiled[5], compiled[6]};
        this.correct = compiled[7].charAt(0);
        this.hasCodeSnippet = compiled[8].contains("true");
    }

    public String toString(){
        String temp = "";
        temp += "ID: " + id + "\n";
        temp += "Category: " + category + "\n";
        temp += "\tQuestion: " + question + "\n";
        temp += "\t\tA.) : " + choices[0] + "\n";
        temp += "\t\tB.) : " + choices[1] + "\n";
        temp += "\t\tC.) : " + choices[2] + "\n";
        temp += "\t\tD.) : " + choices[3] + "\n";
        temp += "\tCorrect Answer: " + correct + "\n";
//        temp += "\tThis question is a " + questionType + " type of question.";
        temp += "\tThis question has a code snippet: " + hasCodeSnippet;
        temp += "\n";
        return temp;
    }
}

public class QuestionReader {
    List<QuestionObject> QO_Lists = new ArrayList<>();      // ALL Questions
    String PATHNAME = Data.PATH + "data/questions.xlsx";
    QuestionObject currentQ = null;                         // Placeholder for QuestionObject
    DataReader DR;

    public QuestionReader(int sheetIndex) {
        DR = new DataReader();
        try {
            FileInputStream fis = new FileInputStream(new File(PATHNAME));
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(sheetIndex);
            Iterator<Row> itr = sheet.iterator();
            itr.next();
            while(itr.hasNext()){
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                ArrayList<String> line = new ArrayList<>();
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cell.getCellTypeEnum()){
                        case STRING:
                            String temp = cell.getStringCellValue();
                            line.add(temp);
                            break;
                        case NUMERIC:
                            int temp_int = (int) cell.getNumericCellValue();
                            line.add(temp_int + "");
                            break;
                    }
                }
                String[] compiled = new String[line.size()];

                for (int i = 0; i < line.size(); i++) {
                    compiled[i] = line.get(i);
                }
                if(compiled.length != 0) {
                    if(compiled.length < 8) System.out.println("WARNING... Q_ID" + compiled[0] + " LACKS CELLS" );
                    QuestionObject newQO = new QuestionObject(compiled);
                    QO_Lists.add(newQO);
                }
            }
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Error on class QuestionReader : Rename question file to " + PATHNAME);
        }
    }
    public QuestionObject pickQuestion(int index){
        QuestionObject question = QO_Lists.get(index);
        currentQ = question;
        return question;
    }

    public static void main(String[] args) {
        QuestionReader QR = new QuestionReader(0);
        for (int i = 0; i < Data.strip(Data.get(Data.TOTAL_QUESTIONS_dl)); i++) {
            System.out.println(QR.pickQuestion(i));
        }
    }
}
