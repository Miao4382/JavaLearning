package lms.corejava.assignment1;

import java.util.Scanner;

public class Q13 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            if (input.equals("q")) break;

            int x = 0;
            try {
                x = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.err.println("Please enter a positive integer! Enter q to quit.");
                continue;
            }

            if (x > 0)
                System.out.println("Sum of 1 to " + x + " is: " + doSomething(x));
            else
                System.err.println("Please enter a positive integer!");
        }

        System.out.println("Bye");
    }

    private static int doSomething(int x) {
        return x * (x + 1) / 2;
    }
}
