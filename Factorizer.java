import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Factorizer {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("What number would you like to factor? ");
        int num = -1;
        do {
            try {
                num = in.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Invalid number. Try again: ");
            }
        } while(num == -1);

        checkNumber(num, in);
        in.close();
    }

    private static void checkNumber(int num, Scanner in) {
        ArrayList<Integer> factors = new ArrayList<>();
        int numFactors = 0;
        int factorSum = 0;

        for(int i = 1; i < num; i++) {
            if(num % i == 0) {
                factors.add(i);
                numFactors++;
                factorSum += i;
            }
        }

        System.out.println(String.format("The factors of %d are %s.", num, factors.toString()));
        System.out.println(String.format("%d has %d factors.", num, numFactors));
        System.out.println(num + (factorSum == num ? " is a perfect number." : " is not a perfect number."));
        System.out.println(num + (numFactors == 1 ? " is a prime number." : " is not a prime number."));
    }
}
