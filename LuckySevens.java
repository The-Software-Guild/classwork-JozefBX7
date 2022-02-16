import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class LuckySevens {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("How many dollars do you want to bet? ");
        float dollars = safeFloatInput(in);
        in.close();
        play(dollars);
    }

    private static void play(float dollars) {
        int rolls = 0;
        float maxDollars = dollars;
        int rollsAtMax = 0;

        while(dollars >= 1) {
            rolls++;
            int dice1 = diceRoll();
            int total = dice1 + diceRoll();
            if(total == 7) {
                dollars += 4;
                if(dollars > maxDollars) {
                    maxDollars = dollars;
                    rollsAtMax = rolls;
                }
            } else {
                dollars--;
            }
        }
        print(String.format("You are broke after %d rolls.", rolls));
        print(String.format("You should have quit after %d rolls when you had $%.2f.", rollsAtMax, maxDollars));
    }

    private static int diceRoll() {
        final Random rnd = new Random();
        return 1 + rnd.nextInt(6);
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
                in.nextLine();
                System.out.print("Invalid number. Try again: ");
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
                in.nextLine();
                System.out.print("Invalid number. Try again: ");
            }
        } while(num == null);

        return num;
    }
}
