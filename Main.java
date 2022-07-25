package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a natural number:");
        int number = scanner.nextInt();
        if (number < 1) {
            System.out.println("This number is not natural!");
        } else {
            if (number % 2 == 1) {
                System.out.println("This number is Odd.");
            } else {
                System.out.println("This number is Even.");
            }
            if (number % 7 == 0 || number % 10 == 7) {
                System.out.println("""
                        It is a Buzz number.
                        Explanation:
                        """);
                if (number % 7 == 0 && number % 10 == 7) {
                    System.out.println(number + " is divisible by 7 and ends with 7");
                } else if (number % 7 == 0) {
                    System.out.println(number + " is divisible by 7");
                } else {
                    System.out.println(number + " ends with 7");
                }
            } else {
                System.out.printf("It is not a Buzz number.\n" +
                        "Explanation:\n" +
                        "%d is neither divisible by 7 nor does it end with 7.", number);
            }
        }
    }
}
