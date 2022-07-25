package numbers;

import java.util.Scanner;

public class Main {
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static boolean isOdd(int number) {
        return number % 2 == 1;
    }

    public static boolean isBuzz(int number) {
        return number % 7 == 0 || number % 10 == 7;
    }

    public static boolean isDuck(int number) {
        while (number > 0) {
            if (number % 10 == 0) {
                return true;
            }
            number /= 10;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a natural number:");
        int number = scanner.nextInt();
        if (number < 1) {
            System.out.println("This number is not natural!");
        } else {
            System.out.println("Properties of " + number);
            System.out.println("        even: " + isEven(number));
            System.out.println("         odd: " + isOdd(number));
            System.out.println("        buzz: " + isBuzz(number));
            System.out.println("        duck: " + isDuck(number));
        }
    }
}
