import java.util.*;
class Question {
    String question;
    List<String> options;
    int correctOption;

    public Question(String question, List<String> options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }
}
