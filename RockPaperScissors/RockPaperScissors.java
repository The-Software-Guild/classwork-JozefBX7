package RockPaperScissors;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    private final String[] CHOICES = {"ROCK", "PAPER", "SCISSORS"};
    private final String[] OUTCOME_MESSAGES = {"crushed", "nullified", "cut up"};

    public static void main(String[] args) {
        RockPaperScissors rps = new RockPaperScissors();
        rps.play();
    }

    private void play() {
        // scanner to read player's inputs
        Scanner in = new Scanner(System.in);
        print("Welcome to Rock Paper Scissors!");

        // whether to play again at the end of the round
        boolean playAgain = false;

        // loop until the player decides to quit
        do {
            System.out.print("How many rounds do you wish to play? 1 - 10: ");
            int numRounds = 0; // the number of rounds to play
            int playerScore = 0;
            int computerScore = 0;
            int ties = 0;

            // ensure the player enters a valid number of rounds from 1 to 10
            boolean validRounds = false;
            do {
                numRounds = safeIntInput(in);
                validRounds = numRounds >= 1 && numRounds <= 10;
                if(!validRounds) {
                    //System.out.print("Invalid input. You can only choose between 1 and 10 rounds. Try again: ");

                    /// edited this block to quit as per the requirement, but kept the code for asking again in place
                    ///
                    System.out.print("Invalid choice. You can only choose between 1 and 10 rounds. You can't follow instructions so I'm leaving!");
                    // close the scanner as cleanup and return
                    in.close();
                    return;
                    ///
                }
            }  while(!validRounds);

            // start the game
            print("Okay, lets go!");
            System.out.println("___________________________________________________"); //  line before game starts

            // loop for each round
            for(int i = 1; i <= numRounds; i++) {
                print(String.format("Round %d!", i));
                System.out.print("Rock [1] / Paper [2] / Scissors [3]? ");
                int playerChoice = 0;

                // ensure the player enters a valid choice between 1 - 3 for rock, paper or scissors respectively
                boolean validChoice = false;
                do {
                    playerChoice = safeIntInput(in);
                    validChoice = playerChoice > 0 && playerChoice < 4;
                    if(!validChoice) {
                        System.out.print("Invalid choice. You must pick 1 to 3. Try again: ");
                    }
                } while(!validChoice);

                // Randomly pick for the computer and display their choice to the player
                int computerChoice = computerChoice();
                print(String.format("This computer chose %s!", CHOICES[computerChoice-1]));
                // 1 = rock, 2 = paper, 3 = scissors

                // print the result of the round
                switch (computeOutcome(playerChoice, computerChoice)) {
                    case 0:
                        ties++;
                        print(String.format("It was a tie! You both chose %s!", CHOICES[playerChoice-1]));
                        break;
                    case 1:
                        playerScore++;
                        print(String.format("You WON this round! Your %s %s the computer's %s!", CHOICES[playerChoice-1], OUTCOME_MESSAGES[playerChoice-1], CHOICES[computerChoice-1]));
                        break;
                    case 2:
                        computerScore++;
                        print(String.format("You LOST this round! The computer's %s %s your %s!", CHOICES[computerChoice-1], OUTCOME_MESSAGES[computerChoice-1], CHOICES[playerChoice-1]));
                        break;
                }
                System.out.println("___________________________________________________"); //  line between rounds
            }

            // print an appropriate message after all the rounds have completed
            String result = playerScore > computerScore ? "WON" : "LOST";
            if(playerScore != computerScore) {
                print(String.format("GAME OVER! You %s with a score of %d wins to %d and %d ties!", result, playerScore, computerScore, ties));
            } else {
                print(String.format("GAME OVER! UNBELIEVABLE! You DREW with equal wins of %d each and %d ties!", playerScore, ties));
            }
            System.out.println("___________________________________________________"); //  draw a line

            // offer the user to play again. If so continue the outer do while loop, otherwise thank them for playing and exit
            System.out.print("Do you want to play again? [Y / N]: ");
            in.nextLine();
            playAgain = safeBoolInput(in);
            if(!playAgain) {
                print("Thanks for playing!");
            }
        } while(playAgain);

        // close the scanner as cleanup when the player is done
        in.close();
    }

    private int computerChoice() {
        final Random rnd = new Random();
        return 1 + rnd.nextInt(3);
    }

    private int computeOutcome(int player1, int player2) {
        // 0 = tie, 1 = player1, 2 = player2

        if(player1 == player2) return 0; // same choice = tie

        //check if player 1 won, otherwise player 2 won
        if((player1 == 1 && player2 == 3) || // rock beats scissors
                (player1 == 2 && player2 == 1) || // paper beats rock
                (player1 == 3 && player2 == 2)) { // scissors beats paper
            return 1;
        } else { // not a tie and player 1 didn't win, therefore player 2 won
            return 2;
        }
    }

    private void print(String message) {
        System.out.println(message);
    }
    private int safeIntInput(Scanner in) {
        Integer num = null;
        do {
            try {
                num = in.nextInt();
            } catch(InputMismatchException ex) {
                in.nextLine();
                System.out.print("Invalid number. Try again: ");
            }
        } while(num == null);

        return num;
    }
    private boolean safeBoolInput(Scanner in) {
        do {
                String input = in.nextLine();
                input = input.toUpperCase();
                if(input.equals("Y")) {
                    return true;
                } else if(input.equals("N")) {
                    return false;
                } else {
                    System.out.print("Invalid choice. Try again: ");
                }
        } while(true);
    }
}
