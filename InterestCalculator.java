import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InterestCalculator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        print("How much do you want to invest? ");
        float amount = safeFloatInput(in);
        print("How many years are you investing? ");
        int years = safeIntInput(in);
        print("What is the annual interest rate % growth? ");
        float interestRate = safeFloatInput(in) / 100;
        print("What compound period? Quarterly [1], monthly [2] or daily [3]? ");
        int compoundPeriod = safeIntInput(in);
        System.out.println();

        if(compoundPeriod <= 1) {
            interestRate /= 4;
            printInterest(amount, years, interestRate, 4);
        } else if(compoundPeriod == 2) {
            interestRate /= 12;
            printInterest(amount, years, interestRate, 12);
        } else {
            interestRate /= 365;
            printInterest(amount, years, interestRate, 365);
        }

        in.close();
    }

    private static void printInterest(float amount, int years, float interestRate, int numDivisions) {
        final DecimalFormat df = new DecimalFormat("0.00");

        for(int year = 1; year <= years; year++) {
            float currAmount = amount;
            for(int i = 0; i < numDivisions; i++) {
                currAmount *= (1 + (interestRate));
            }
            float interest = currAmount - amount;

            print(String.format("Year %d:", year));
            print("Began with $" + df.format(amount));
            print("Earned $" + df.format(interest));
            print("Ended with $" + df.format(currAmount));
            System.out.println();
            amount = currAmount;
        }
    }


    private static void print(String message) {
        System.out.println(message);
    }
    private static int safeIntInput(Scanner in) {
        Integer num = null;
        do {
            try {
                num = in.nextInt();
            } catch(InputMismatchException ex) {
                System.out.println("Invalid number. Try again: ");
                in.nextLine();
            }
        } while(num == null);

        return num;
    }

    private static float safeFloatInput(Scanner in) {
        Float num = null;
        do {
            try {
                num = in.nextFloat();
            } catch(InputMismatchException ex) {
                System.out.println("Invalid number. Try again: ");
                in.nextLine();
            }
        } while(num == null);

        return num;
    }
}
