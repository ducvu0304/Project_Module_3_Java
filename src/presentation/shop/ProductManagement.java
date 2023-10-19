package presentation.shop;

import java.io.IOException;

import static business.utils.ScannerUtils.inputInteger;

public class ProductManagement {
    public static void menu() throws IOException {
        while (true) {
            System.out.println( "+---------------------------------------+\n" +
                                "|            PRODUCT MANAGEMENT         |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 1 | Shoes Management                  |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 2 | Clothing Management               |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 3 | Stock Management                  |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 4 | Back                              |\n" +
                                "+---+-----------------------------------+  " );

            System.out.println("Enter choice function:");
            int choice = inputInteger();

            switch (choice) {
                case 1:
                    ShoesManagement.menu();
                    break;
                case 2:
                    ClothingManagement.menu();
                    break;
                case 3:
                    StockManagement.menu();
                    break;
                case 4:
                    return;
                default:
                    System.out.printf("The choice \"%d\" is not function %n", choice);
            }
        }
    }
}
