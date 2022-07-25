package numbers;

import java.util.Scanner;

public class Main {

    public static void welcome() {
        System.out.println("""
                Welcome to Amazing Numbers!
                                
                Supported requests:
                - enter a natural number to know its properties;
                - enter 0 to exit.""");
    }

    public static long inputNumber() {
        while (true) {
            System.out.print("\nEnter a requests: ");
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextLong()) {
                long number = scanner.nextLong();
                if (number >= 0) return number;
            }
            System.out.println("\nThe first parameter should be a natural number or zero.");
        }
    }

    public static void propertiesOf(long number) {
        System.out.println("\nProperties of " + number);
        System.out.println("        even: " + isEven(number));
        System.out.println("         odd: " + isOdd(number));
        System.out.println("        buzz: " + isBuzz(number));
        System.out.println("        duck: " + isDuck(number));
        System.out.println(" palindromic: " + isPalindromic(number));
    }
    public static boolean isEven(long number) {
        return number % 2 == 0;
    }

    public static boolean isOdd(long number) {
        return number % 2 == 1;
    }

    public static boolean isBuzz(long number) {
        return number % 7 == 0 || number % 10 == 7;
    }

    public static boolean isDuck(long number) {
        while (number > 0) {
            if (number % 10 == 0) {
                return true;
            }
            number /= 10;
        }
        return false;
    }

    public static boolean isPalindromic(long number) {
        long tmp = number;
        long newNumber = 0;
        while (tmp > 0) {
            newNumber = newNumber * 10 + tmp % 10;
            tmp /= 10;
        }
        return newNumber == number;
    }

    public static void main(String[] args) {
        welcome();
        long number;
        while (true) {
            number = inputNumber();
            if (number == 0) break;
            propertiesOf(number);
        }
        System.out.println("\nGoodbye!");
    }
}
