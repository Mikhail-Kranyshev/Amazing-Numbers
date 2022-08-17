package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    final static String SUPPORTED_REQUESTS = """
            
            Supported requests:
            - enter a natural number to know its properties;
            - enter two natural numbers to obtain the properties of the list:
              * the first parameter represents a starting number;
              * the second parameter shows how many consecutive numbers are to be printed;
            - two natural numbers and a property to search for;
            - separate the parameters with one space;
            - enter 0 to exit.""";

    final static ArrayList<String> AVAILABLE_PROPERTIES = new ArrayList<>(Arrays.asList("EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY"));
    final static String[] ERROR_MESSAGES = {
            "\nThe first parameter should be a natural number or zero.",
            "\nThe second parameter should be a natural number.",
            "\nThe property [%s] is wrong.\nAvailable properties: " + AVAILABLE_PROPERTIES
    };
    public static void welcome() {
        System.out.println("Welcome to Amazing Numbers!\n" + SUPPORTED_REQUESTS);
    }



    public static boolean inputNumber() {
        while (true) {
            System.out.print("\nEnter a requests: ");
            Scanner scanner = new Scanner(System.in);
            String[] strings = scanner.nextLine().split(" ");
//            if (strings[0].isEmpty()) {
//
//            } else
            if (strings[0].equals("0")) {
                return false;
            } else {
                switch (strings.length) {
                    case 1:
                        check(strings[0]);
                        break;
                    case 2:
                        check(strings[0], strings[1]);
                        break;
                    case 3:
                        check(strings);
                        break;
                    case 0:
                        System.out.println(SUPPORTED_REQUESTS);
                        break;
                }
            }
        }
    }

    public static void check(String string) {
        if (new Scanner(string).hasNextLong()) {
            long number = Long.parseLong(string);
            if (number > 0) {
                propertiesOf(number);
                return;
            }
        }
        System.out.println(ERROR_MESSAGES[0]);
        return;
    }

    public static void check(String firstString, String secondString) {
        if (new Scanner(firstString).hasNextLong()) {
            long number = Long.parseLong(firstString);
            if (number > 0) {
                if (new Scanner(secondString).hasNextLong()) {
                    long count = Long.parseLong(secondString);
                    if (count > 0) {
                        propertiesOf(number, count);
                        return;
                    }
                }
                System.out.println(ERROR_MESSAGES[1]);
                return;
            }
        }
        System.out.println(ERROR_MESSAGES[0]);
        return;
    }

    public static void check(String[] strings) {
        if (new Scanner(strings[0]).hasNextLong()) {
            long number = Long.parseLong(strings[0]);
            if (number > 0) {
                if (new Scanner(strings[1]).hasNextLong()) {
                    long count = Long.parseLong(strings[1]);
                    if (count > 0) {
                        if (AVAILABLE_PROPERTIES.contains(strings[2].toUpperCase())) {
                            propertiesOf(number, count, strings[2]);
                            return;
                        }
                        System.out.println(ERROR_MESSAGES[2].formatted(strings[2].toUpperCase()));
                        return;
                    }
                }
                System.out.println(ERROR_MESSAGES[1]);
                return;
            }
        }
        System.out.println(ERROR_MESSAGES[0]);
        return;
    }

    public static void propertiesOf(long number) {
        System.out.println("\nProperties of " + number);
        System.out.println("        buzz: " + isBuzz(number));
        System.out.println("        duck: " + isDuck(number));
        System.out.println(" palindromic: " + isPalindromic(number));
        System.out.println("      gapful: " + isGapful(number));
        System.out.println("         spy: " + isSpy(number));
        System.out.println("        even: " + isEven(number));
        System.out.println("         odd: " + isOdd(number));
    }

    public static void propertiesOf(long number, long n) {
        for (int i = 0; i < n; i++) {
            System.out.printf("%15d is ", number);
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
            if (isSpy(number)) {
                System.out.print("spy, ");
            }
            if (isEven(number)) {
                System.out.print("even\n");
            } else {
                System.out.print("odd\n");
            }
            number++;
        }
    }

    public static boolean is(long number, String property) {
        boolean isTrue = false;
        switch (property.toLowerCase()) {
            case "buzz":
                isTrue = isBuzz(number);
                break;
            case "duck":
                isTrue = isDuck(number);
                break;
            case "palindromic":
                isTrue = isPalindromic(number);
                break;
            case "gapful":
                isTrue = isGapful(number);
                break;
            case "spy":
                isTrue = isSpy(number);
                break;
            case "even":
                isTrue = isEven(number);
                break;
            case "odd":
                isTrue = isOdd(number);
                break;
        }
        return isTrue;
    }

    public static void propertiesOf(long number, long n, String property) {
        for (int i = 0; i < n; number++) {
            if (is(number, property)) {
                System.out.printf("%15d is ", number);
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
                if (isSpy(number)) {
                    System.out.print("spy, ");
                }
                if (isEven(number)) {
                    System.out.print("even\n");
                } else {
                    System.out.print("odd\n");
                }
                i++;
            }
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

    public static boolean isSpy(long number) {
        int sum = 0;
        int product = 1;
        long tmp = number;
        while (tmp > 0) {
            sum += tmp % 10;
            product *= tmp % 10;
            tmp /= 10;
        }
        if (sum == product) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        welcome();
        inputNumber();
        System.out.println("\nGoodbye!");
    }
}
