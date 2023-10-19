package business.utils;

import java.util.Scanner;

public class ScannerUtils {
    static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    /**Input integer*/
    public static int inputInteger() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e) {
                System.err.println("Invalid number !");
            }
        }
    }

    /**Input float*/
    public static float inputFloat() {
        while (true) {
            try {
                return Float.parseFloat(scanner.nextLine());
            }catch (NumberFormatException e) {
                System.err.println("Invalid number !");
            }
        }
    }

    /**Input double*/
    public static double inputDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            }catch (NumberFormatException e) {
                System.err.println("Invalid number !");
            }
        }
    }

    /**Input double*/
//    public static char inputChar() {
//        while (true) {
//            try {
//                return Character.c(scanner.nextLine());
//            }catch (NumberFormatException e) {
//                System.err.println("Invalid number !");
//            }
//        }
//    }

    /**Input String*/
    public static String inputString() {
        return scanner.nextLine();
    }

    /** Close Scanner */
    public static void closeScanner() {
        scanner.close();
    }
}
