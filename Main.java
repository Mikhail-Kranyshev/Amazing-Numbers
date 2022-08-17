package numbers;

import java.util.Scanner;

public class Main {
    final static String SUPPORTED_REQUESTS = """
            
            Supported requests:
            - enter a natural number to know its properties;
            - enter two natural numbers to obtain the properties of the list:
              * the first parameter represents a starting number;
              * the second parameter shows how many consecutive numbers are to be processed;
            - separate the parameters with one space;
            - enter 0 to exit.""";

    public static void welcome() {
        System.out.println("Welcome to Amazing Numbers!\n" + SUPPORTED_REQUESTS);
    }



    public static long[] inputNumber() {
        while (true) {
            System.out.print("\nEnter a requests: ");
            Scanner scanner = new Scanner(System.in);
            String[] strings = scanner.nextLine().split(" ");
            if (strings[0].isEmpty()) {
                System.out.println(SUPPORTED_REQUESTS);
            } else {
                String start = "\nThe first parameter ";
                String end = " or zero.";
                long[] number = {0, 0};
                if (strings.length >= 2) {
                    if (new Scanner(strings[0]).hasNextLong()) {
                        number[0] = Long.parseLong(strings[0]);
                        if (number[0] >= 0) {
                            if (new Scanner(strings[1]).hasNextLong()) {
                                number[1] = Long.parseLong(strings[1]);
                                if (number[1] > 0) {
                                    return number;
                                }
                            }
                            start = "\nThe second parameter ";
                            end = ".";
                        }
                    }
                } else {
                    if (new Scanner(strings[0]).hasNextLong()) {
                        number[0] = Long.parseLong(strings[0]);
                        if (number[0] >= 0) {
                            return number;
                        }
                    }
                }
                System.out.println(start + "should be a natural number" + end);
            }
        }
    }

    public static void propertiesOf(long number) {
        System.out.println("\nProperties of " + number);
        System.out.println("        buzz: " + isBuzz(number));
        System.out.println("        duck: " + isDuck(number));
        System.out.println(" palindromic: " + isPalindromic(number));
        System.out.println("      gapful: " + isGapful(number));
        System.out.println("        even: " + isEven(number));
        System.out.println("         odd: " + isOdd(number));
    }

    public static void propertiesOf(long number, long n) {
        for (int i = 0; i < n; i++) {
            System.out.print("             " + number + " is ");
            if (isBuzz(number)) {
                System.out.print("buzz, ");
            }
            if (isDuck(number)) {
                System.out.print("duck, ");
            }
            if (isPalindromic(number)) {
                System.out.print("palindromic, ");
            }
            if (isGapful(number)) {
                System.out.print("gapful, ");
            }
            if (isEven(number)) {
                System.out.print("even\n");
            } else {
                System.out.print("odd\n");
            }
            number++;
        }
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

    public static boolean isGapful(long number) {
        int numericValue = Character.getNumericValue(String.valueOf(number).charAt(0));
        if (number > 99 && number % (number % 10 + 10L * numericValue) == 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        welcome();
        long[] number;
        while (true) {
            number = inputNumber();
            if (number[0] == 0) break;
            if (number[1] == 0) {
                propertiesOf(number[0]);
            } else {
                propertiesOf(number[0], number[1]);
            }
        }
        System.out.println("\nGoodbye!");
    }
}
