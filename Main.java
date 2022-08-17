package numbers;

import java.util.*;

public class Main {
    final static String SUPPORTED_REQUESTS = """
            
            Supported requests:
            - enter a natural number to know its properties;\s
            - enter two natural numbers to obtain the properties of the list:
              * the first parameter represents a starting number;
              * the second parameter shows how many consecutive numbers are to be printed;
            - two natural numbers and a property to search for;
            - two natural numbers and two properties to search for;
            - separate the parameters with one space;
            - enter 0 to exit.""";

    final static ArrayList<String> AVAILABLE_PROPERTIES = new ArrayList<>(
            Arrays.asList("EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SUNNY", "SQUARE"));

    final static String[] ERROR_MESSAGES = {
            "\nThe first parameter should be a natural number or zero.",
            "\nThe second parameter should be a natural number.",
            "\nThe property [%s] is wrong.\nAvailable properties: " + AVAILABLE_PROPERTIES,
            "\nThe properties %s are wrong.\nAvailable properties: " + AVAILABLE_PROPERTIES,
            "\nThe request contains mutually exclusive properties: %s\nThere are no numbers with these properties."

    };
    public static void welcome() {
        System.out.println("Welcome to Amazing Numbers!\n" + SUPPORTED_REQUESTS);
    }



    public static void start() {
        welcome();
        while (true) {
            System.out.print("\nEnter a requests: ");
            Scanner scanner = new Scanner(System.in);
            String[] strings = scanner.nextLine().split(" ");
            if (strings[0].equals("0")) {
                System.out.println("\nGoodbye!");
                return;
            } else {
                switch (strings.length) {
                    case 1:
                        check(strings[0]);
                        break;
                    case 2:
                        check(strings[0], strings[1]);
                        break;
                    case 3:
                        check(strings[0], strings[1], strings[2]);
                        break;
                    case 4:
                        check(strings[0], strings[1], new String[]{strings[2].toUpperCase(), strings[3].toUpperCase()});
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

    public static void check(String numberString, String countString, String property) {
        if (new Scanner(numberString).hasNextLong()) {
            long number = Long.parseLong(numberString);
            if (number > 0) {
                if (new Scanner(countString).hasNextLong()) {
                    long count = Long.parseLong(countString);
                    if (count > 0) {
                        if (AVAILABLE_PROPERTIES.contains(property.toUpperCase())) {
                            propertiesOf(number, count, property);
                            return;
                        }
                        System.out.println(ERROR_MESSAGES[2].formatted(property.toUpperCase()));
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

    public static void check(String numberString, String countString, String[] properties) {
        if (new Scanner(numberString).hasNextLong()) {
            long number = Long.parseLong(numberString);
            if (number > 0) {
                if (new Scanner(countString).hasNextLong()) {
                    long count = Long.parseLong(countString);
                    if (count > 0) {
                        if (AVAILABLE_PROPERTIES.containsAll(List.of(properties))) {
                            if ((properties[0].equals("EVEN") && properties[1].equals("ODD")) ||
                                    (properties[0].equals("ODD") && properties[1].equals("EVEN")) ||
                                    (properties[0].equals("DUCK") && properties[1].equals("SPY")) ||
                                    (properties[0].equals("SPY") && properties[1].equals("DUCK")) ||
                                    (properties[0].equals("SUNNY") && properties[1].equals("SQUARE")) ||
                                    (properties[0].equals("SQUARE") && properties[1].equals("SUNNY"))) {
                                System.out.println(ERROR_MESSAGES[4].formatted(Arrays.toString(properties)));
                                return;
                            }
                            propertiesOf(number, count, properties);
                            return;
                        } else if (AVAILABLE_PROPERTIES.contains(properties[0])) {
                            System.out.println(ERROR_MESSAGES[2].formatted(properties[1]));
                        } else if (AVAILABLE_PROPERTIES.contains(properties[1])) {
                            System.out.println(ERROR_MESSAGES[2].formatted(properties[0]));
                        } else {
                            System.out.println(ERROR_MESSAGES[3].formatted(Arrays.toString(properties)));
                        }
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
        System.out.println("      square: " + isSquare(number));
        System.out.println("       sunny: " + isSunny(number));
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
            if (isSquare(number)) {
                System.out.print("square, ");
            }
            if (isSunny(number)) {
                System.out.print("sunny, ");
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
            case "square":
                isTrue = isSquare(number);
                break;
            case "sunny":
                isTrue = isSunny(number);
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
                if (isSquare(number)) {
                    System.out.print("square, ");
                }
                if (isSunny(number)) {
                    System.out.print("sunny, ");
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

    public static boolean is(long number, String[] properties) {
        ArrayList<Boolean> isTrue = new ArrayList<>();
        for (String property: properties) {
            switch (property.toLowerCase()) {
                case "buzz":
                    isTrue.add(isBuzz(number));
                    break;
                case "duck":
                    isTrue.add(isDuck(number));
                    break;
                case "palindromic":
                    isTrue.add(isPalindromic(number));
                    break;
                case "gapful":
                    isTrue.add(isGapful(number));
                    break;
                case "spy":
                    isTrue.add(isSpy(number));
                    break;
                case "square":
                    isTrue.add(isSquare(number));
                    break;
                case "sunny":
                    isTrue.add(isSunny(number));
                    break;
                case "even":
                    isTrue.add(isEven(number));
                    break;
                case "odd":
                    isTrue.add(isOdd(number));
                    break;
            }
        }
        return isTrue.get(0) && isTrue.get(1);
    }

    public static void propertiesOf(long number, long n, String[] properties) {
        for (int i = 0; i < n; number++) {
            if (is(number, properties)) {
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
                if (isSquare(number)) {
                    System.out.print("square, ");
                }
                if (isSunny(number)) {
                    System.out.print("sunny, ");
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

    public static boolean isSquare(long number) {
        int sqrt = (int) Math.sqrt(number);
        if (number == sqrt * sqrt) {
            return true;
        }
        return false;
    }

    public static boolean isSunny(long number) {
        if (isSquare(number + 1)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        start();
    }
}
