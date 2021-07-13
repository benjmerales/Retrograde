import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class QuestionReader {
    List<QuestionObject> QO_Lists = new ArrayList<>();      // ALL Questions
    String PATHNAME = "src/resources/data/questions.xlsx";  // TODO: change src folder
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
        System.out.println("Picked Question ID0" + currentQ.id);
        return question;
    }
}
