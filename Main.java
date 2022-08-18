package numbers;

import java.util.*;

public class Main {

    /**
     * Инструкция
     */
    final static String SUPPORTED_REQUESTS = """
                        
            Supported requests:
            - enter a natural number to know its properties;
            - enter two natural numbers to obtain the properties of the list:
              * the first parameter represents a starting number;
              * the second parameter shows how many consecutive numbers are to be printed;
            - two natural numbers and properties to search for;
            - a property preceded by minus must not be present in numbers;
            - separate the parameters with one space;
            - enter 0 to exit.""";


    /**
     * Доступные свойства
     */
    final static ArrayList<String> AVAILABLE_PROPERTIES = new ArrayList<>(Arrays.asList(
            "EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SUNNY", "SQUARE", "JUMPING", "HAPPY", "SAD",
            "-EVEN", "-ODD", "-BUZZ", "-DUCK", "-PALINDROMIC", "-GAPFUL", "-SPY", "-SUNNY", "-SQUARE", "-JUMPING", "-HAPPY", "-SAD"
    ));

    /**
     * Сообщения об ошибках
     */
    final static String[] ERROR_MESSAGES = {
            "\nThe first parameter should be a natural number or zero.",
            "\nThe second parameter should be a natural number.", "\nThe property [%s] is wrong.\nAvailable properties: " + AVAILABLE_PROPERTIES,
            "\nThe properties %s are wrong.\nAvailable properties: " + AVAILABLE_PROPERTIES,
            "\nThe request contains mutually exclusive properties: %s\nThere are no numbers with these properties.",
            "\nThe request contains mutually exclusive properties: [%s, %s]\nThere are no numbers with these properties."};

    /**
     * Недопустимые комбинации свойств
     */
    final static String[][] impossible = {{"EVEN", "ODD"}, {"SQUARE", "SUNNY"}, {"DUCK", "SPY"}, {"HAPPY", "SAD"},
            {"-EVEN", "-ODD"}, {"-DUCK", "-SPY"}, {"-HAPPY", "-SAD"}, {"EVEN", "-EVEN"},
            {"ODD", "-ODD"}, {"BUZZ", "-BUZZ"}, {"DUCK", "-DUCK"}, {"PALINDROMIC", "-PALINDROMIC"},
            {"GAPFUL", "-GAPFUL"}, {"SPY", "-SPY"}, {"SUNNY", "-SUNNY"}, {"SQUARE", "-SQUARE"}, {"JUMPING", "-JUMPING"},
            {"HAPPY", "-HAPPY"}, {"SAD", "-SAD"}};


    /**
     * Приветственное сообщение
     */
    public static void welcome() {
        System.out.println("Welcome to Amazing Numbers!\n" + SUPPORTED_REQUESTS);
    }

    /**
     * Рабочий цикл
     */
    public static void start() {
        welcome();
        while (true) {
            System.out.print("\nEnter a requests: ");
            Scanner scanner = new Scanner(System.in);
            String[] strings = scanner.nextLine().split(" ");
            if (strings[0].equals("0")) {
                System.out.println("\nGoodbye!");
                return;
            } else if (strings[0].isEmpty()) {
                System.out.println(SUPPORTED_REQUESTS);
            } else {
                switch (strings.length) {
                    case 1 -> check(strings[0]);
                    case 2 -> check(strings[0], strings[1]);
                    case 3 -> check(strings[0], strings[1], strings[2]);
                    case 4, 5, 6, 7, 8, 9, 10 -> {
                        String[] properties = new String[strings.length - 2];
                        for (int i = 2; i < strings.length; i++) {
                            properties[i - 2] = strings[i].toUpperCase();
                        }
                        check(strings[0], strings[1], properties);
                    }
                }
            }
        }
    }

    /* Проверки ввода */

    /**
     * Проверка если введено только одно число
     */
    public static void check(String string) {
        if (new Scanner(string).hasNextLong()) {
            long number = Long.parseLong(string);
            if (number > 0) {
                propertiesOf(number);
                return;
            }
        }
        System.out.println(ERROR_MESSAGES[0]);
    }

    /**
     * Проверка если введено два числа
     */
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
    }

    /**
     * Проверка если введено два числа и свойство
     */
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
    }

    /**
     * Проверка если введено два числа и свойства
     */
    public static void check(String numberString, String countString, String[] properties) {
        if (new Scanner(numberString).hasNextLong()) {
            long number = Long.parseLong(numberString);
            if (number > 0) {
                if (new Scanner(countString).hasNextLong()) {
                    long count = Long.parseLong(countString);
                    if (count > 0) {
                        if (AVAILABLE_PROPERTIES.containsAll(List.of(properties))) {
                            for (String[] strings : impossible) {
                                for (int i = 0; i < properties.length - 1; i++) {
                                    for (int j = i + 1; j < properties.length; j++) {
                                        if ((properties[i].equals(strings[0]) && properties[j].equals(strings[1])) ||
                                                (properties[j].equals(strings[0]) && properties[i].equals(strings[1]))) {
                                            System.out.println(ERROR_MESSAGES[4].formatted(Arrays.toString(strings)));
                                            return;
                                        }
                                    }

                                }
                            }
                            propertiesOf(number, count, properties);
                            return;
                        }
                        ArrayList<String> propertiesWithMistakes = new ArrayList<>();
                        for (String property : properties) {
                            if (!AVAILABLE_PROPERTIES.contains(property)) {
                                propertiesWithMistakes.add(property);
                            }
                        }
                        if (propertiesWithMistakes.size() == 1) {
                            System.out.println(ERROR_MESSAGES[2].formatted(propertiesWithMistakes.get(0)));
                        } else {
                            System.out.println(ERROR_MESSAGES[3].formatted(propertiesWithMistakes.toString()));
                        }
                        return;
                    }
                }
                System.out.println(ERROR_MESSAGES[1]);
                return;
            }
        }
        System.out.println(ERROR_MESSAGES[0]);
    }

    /* Вывод свойств */

    /**
     * Вывод свойств для одного числа
     */
    public static void propertiesOf(long number) {
        System.out.println("\nProperties of " + number);
        System.out.println("        buzz: " + isBuzz(number));
        System.out.println("        duck: " + isDuck(number));
        System.out.println(" palindromic: " + isPalindromic(number));
        System.out.println("      gapful: " + isGapful(number));
        System.out.println("         spy: " + isSpy(number));
        System.out.println("      square: " + isSquare(number));
        System.out.println("       sunny: " + isSunny(number));
        System.out.println("     jumping: " + isJumping(number));
        System.out.println("       happy: " + isHappy(number));
        System.out.println("         sad: " + isSad(number));
        System.out.println("        even: " + isEven(number));
        System.out.println("         odd: " + isOdd(number));
    }

    /**
     * Вывод свойств для чисел (number++) (n) раз
     */
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
            if (isJumping(number)) {
                System.out.print("jumping, ");
            }
            if (isHappy(number)) {
                System.out.print("happy, ");
            }
            if (isSad(number)) {
                System.out.print("sad, ");
            }
            if (isEven(number)) {
                System.out.print("even\n");
            } else {
                System.out.print("odd\n");
            }
            number++;
        }
    }

    /**
     * Проверка свойства (property)
     */
    public static boolean is(long number, String property) {
        return switch (property.toLowerCase()) {
            case "buzz" -> isBuzz(number);
            case "duck" -> isDuck(number);
            case "palindromic" -> isPalindromic(number);
            case "gapful" -> isGapful(number);
            case "spy" -> isSpy(number);
            case "square" -> isSquare(number);
            case "sunny" -> isSunny(number);
            case "jumping" -> isJumping(number);
            case "happy" -> isHappy(number);
            case "sad" -> isSad(number);
            case "even" -> isEven(number);
            case "odd" -> isOdd(number);
            case "-buzz" -> !isBuzz(number);
            case "-duck" -> !isDuck(number);
            case "-palindromic" -> !isPalindromic(number);
            case "-gapful" -> !isGapful(number);
            case "-spy" -> !isSpy(number);
            case "-square" -> !isSquare(number);
            case "-sunny" -> !isSunny(number);
            case "-jumping" -> !isJumping(number);
            case "-happy" -> !isHappy(number);
            case "-sad" -> !isSad(number);
            case "-even" -> !isEven(number);
            case "-odd" -> !isOdd(number);
            default -> false;
        };
    }

    /**
     * Вывод свойств для чисел (number++) (n) раз с определенным свойством (property)
     */
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
                if (isJumping(number)) {
                    System.out.print("jumping, ");
                }
                if (isHappy(number)) {
                    System.out.print("happy, ");
                }
                if (isSad(number)) {
                    System.out.print("sad, ");
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

    /**
     * Проверка свойств (properties)
     */
    public static boolean is(long number, String[] properties) {
//        ArrayList<Boolean> isTrue = new ArrayList<>();
        HashSet<Boolean> isTrue = new HashSet<>();
        for (String property : properties) {
            switch (property.toLowerCase()) {
                case "buzz" -> isTrue.add(isBuzz(number));
                case "duck" -> isTrue.add(isDuck(number));
                case "palindromic" -> isTrue.add(isPalindromic(number));
                case "gapful" -> isTrue.add(isGapful(number));
                case "spy" -> isTrue.add(isSpy(number));
                case "square" -> isTrue.add(isSquare(number));
                case "sunny" -> isTrue.add(isSunny(number));
                case "jumping" -> isTrue.add(isJumping(number));
                case "happy" -> isTrue.add(isHappy(number));
                case "sad" -> isTrue.add(isSad(number));
                case "even" -> isTrue.add(isEven(number));
                case "odd" -> isTrue.add(isOdd(number));
                case "-buzz" -> isTrue.add(!isBuzz(number));
                case "-duck" -> isTrue.add(!isDuck(number));
                case "-palindromic" -> isTrue.add(!isPalindromic(number));
                case "-gapful" -> isTrue.add(!isGapful(number));
                case "-spy" -> isTrue.add(!isSpy(number));
                case "-square" -> isTrue.add(!isSquare(number));
                case "-sunny" -> isTrue.add(!isSunny(number));
                case "-jumping" -> isTrue.add(!isJumping(number));
                case "-happy" -> isTrue.add(!isHappy(number));
                case "-sad" -> isTrue.add(!isSad(number));
                case "-even" -> isTrue.add(!isEven(number));
                case "-odd" -> isTrue.add(!isOdd(number));
            }
        }
        return !isTrue.contains(false);
    }

    /**
     * Вывод свойств для чисел (number++) (n) раз
     * с определенным свойствами (properties)
     */
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
                if (isJumping(number)) {
                    System.out.print("jumping, ");
                }
                if (isHappy(number)) {
                    System.out.print("happy, ");
                }
                if (isSad(number)) {
                    System.out.print("sad, ");
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

    /*
     * ПРОВЕРКИ
     */
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
        return number > 99 && number % (number % 10 + 10L * numericValue) == 0;
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
        return sum == product;
    }

    public static boolean isSquare(long number) {
        int sqrt = (int) Math.sqrt(number);
        return number == (long) sqrt * sqrt;
    }

    public static boolean isSunny(long number) {
        return isSquare(number + 1);
    }

    public static boolean isJumping(long number) {
        long tmp;
        while (number > 10) {
            tmp = number % 10;
            number /= 10;
            if (Math.abs(tmp - number % 10) != 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isHappy(long number) {
        HashSet<Long> hashSet = new HashSet<>();
        while (number != 1) {
            long tmp = 0;
            while (number > 0) {
                tmp += (number % 10) * (number % 10);
                number /= 10;
            }
            number = tmp;
            if (hashSet.contains(number)) {
                return false;
            }
            hashSet.add(tmp);

        }
        return true;
    }

    public static boolean isSad(long number) {
        return !isHappy(number);
    }

    public static void main(String[] args) {
        start();
    }
}
