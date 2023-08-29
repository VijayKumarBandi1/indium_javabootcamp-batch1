import java.util.Scanner;

public class CalculatorApp {

    public static void main(String[] args) {
        int choice;
        int maxAttempts = 3; // Maximum attempts for valid input
        Scanner scan = new Scanner(System.in);

        do {
        	 calculator(maxAttempts);
            System.out.println("\n1. Continue");
            System.out.println("2. Exit");
            System.out.print("\nEnter Your Choice (1-2): ");
            choice = scan.nextInt();

            // Input validation for main menu choice with limited attempts
            int attempts = 0;
            while ((choice < 1 || choice > 2) && attempts < maxAttempts) {
                attempts++;
                System.out.println("\nInvalid Choice! Attempt " + attempts + " of " + maxAttempts);
                System.out.println("1. Continue");
                System.out.println("2. Exit");
                System.out.print("\nEnter Your Choice (1-2): ");
                choice = scan.nextInt();
            }

            if (attempts >= maxAttempts) {
                System.out.println("\nMaximum attempts reached. Exiting the application.");
                break;
            }

            if (choice == 2) {
                System.out.println("\nGood Bye!! Have a nice day");
            }
        } while (choice == 1);

        scan.close();
    }

    public static void calculator(int maxAttempts) {
        float a, b, res;
        int choice;
        Scanner scan = new Scanner(System.in);

        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");
        System.out.print("Enter Your Choice (1-4): ");
        choice = scan.nextInt();

        // Input validation for calculator options
        int attempts = 0;
        while ((choice < 1 || choice > 4) && attempts < maxAttempts) {
            attempts++;
            System.out.println("\nInvalid Choice! Attempt " + attempts + " of " + maxAttempts);
            System.out.println("1. Addition (+)");
            System.out.println("2. Subtraction (-)");
            System.out.println("3. Multiplication (*)");
            System.out.println("4. Division (/)");
            System.out.print("Enter Your Choice (1-4): ");
            choice = scan.nextInt();
        }

        if (attempts >= maxAttempts) {
            System.out.println("\nMaximum attempts reached for calculator input. Exiting the application.");
            scan.close();
            System.exit(0);
        }

        System.out.print("\nEnter First Number: ");
        a = scan.nextFloat();
        System.out.print("Enter Second Number: ");
        b = scan.nextFloat();

        if (choice == 1)
            res = a + b;
        else if (choice == 2)
            res = a - b;
        else if (choice == 3)
            res = a * b;
        else
            res = a / b;

        System.out.println("\nResult = " + res);

        scan.close();
    }
}
