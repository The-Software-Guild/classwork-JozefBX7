import java.util.InputMismatchException;
import java.util.Scanner;

public class WindowMaster {
    public static void main(String[] args) {
        final float GLASS_SQUARE_FT = 3.5f;
        final float TRIM_LINEAR_FT = 2.25f;

        Scanner in = new Scanner(System.in);
        float width = 0, height = 0;
        System.out.println("Enter the width of the window in feet: ");
        width = getFloatInput(in);
        System.out.println("Enter the height of the window in feet: ");
        height = getFloatInput(in);

        float area = height * width;
        float perimeter = 2 * height + 2 * width;
        float glassCost = width * height * GLASS_SQUARE_FT;
        float trimCost = perimeter * TRIM_LINEAR_FT;

        System.out.println("Window height = " + height + "ft");
        System.out.println("Window width = " + width + "ft");
        System.out.println("Window area = " + area + " sq ft");
        System.out.println("Window perimeter = " + perimeter + "ft");
        System.out.println("Total cost = $" + (glassCost + trimCost));
    }

    private static float getFloatInput(Scanner in) {
        boolean valid = false;
        float num = -1f;
        while(!valid) {
            try {
                num = in.nextFloat();
                valid = true;
            } catch(InputMismatchException ex) {
                System.out.println("You did not enter a valid number. Try again:");
                in.next();
            }
        }

        return num;
    }
}
