package QandA;

public class Question {
    String question;
    String answer;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {return question;}
    public String getAnswer() {return answer;}

    public void setQuestion(String question) {this.question = question;}
    public void setAnswer(String answer) {this.answer = answer;}

    public boolean checkAnswer(String answer) {
        return this.answer.equals(answer);
    }
}
