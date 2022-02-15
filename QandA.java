package QandA;

import java.util.Scanner;

public class QandA {

    static Question[] QUESTIONS = new Question[5];

    public static void main(String[] args) {
        initialise();
        Scanner in = new Scanner(System.in);
        System.out.println(String.format("Welcome to the quiz! You will be asked %d questions.", QUESTIONS.length));
        System.out.println("Type R to begin, or Q to quit.");

        boolean validInput = false;
        do {
            String input = in.next();
            if(input.toUpperCase().equals("R")) {
                runQuiz(in);
                validInput = true;
            } else if (input.toUpperCase().equals("Q")) {
                validInput = true;
            } else {
                System.out.println("Invalid input. Type R to begin or Q to quit.");
            }
        } while(!validInput);

        in.close();
    }

    private static void runQuiz(Scanner in) {
        int score = 0;
        in.nextLine();

        for(Question q : QUESTIONS) {
            System.out.println(q.question);
            String answer = in.nextLine();
            if(answer.toUpperCase().equals(q.answer.toUpperCase())) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println(String.format("Incorrect. The answer was %s.", q.answer));
            }
            System.out.println("___________________________________________________");
    }

        float percentage = (float)score / QUESTIONS.length * 100;
        String reaction = "";
        if(percentage < 50) {
            reaction = "Too bad! Better luck next time!";
        } else if(percentage < 70) {
            reaction = "Not bad! Keep trying!";
        } else if(percentage < 90) {
            reaction = "Great effort!";
        } else if(percentage == 100) {
            reaction = "Amazing! You aced it!";
        }

        System.out.println(String.format("You've reached the end of the quiz! Your final score is %d/%d (%.1f%%).", score, QUESTIONS.length, percentage));

        System.out.println(reaction);
    }

    private static void initialise() {
        QUESTIONS[0] = new Question("What colour is found on 75% of the world’s flags?", "Red");
        QUESTIONS[1] = new Question("What is the hottest chilli pepper in the world?", "Carolina Reaper");
        QUESTIONS[2] = new Question("Which is the only venomous snake native to the UK?", "Adder");
        QUESTIONS[3] = new Question("How many bones in the adult human body?", "206");
        QUESTIONS[4] = new Question("What is the most spoken language in the world?", "Mandarin");
    }
}
