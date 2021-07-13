public class QuestionObject {
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
//        this.questionType = compiled[8];
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
        return temp;
    }
}
