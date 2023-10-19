package presentation.shop;

import business.config.IOFile;
import business.entity.account.Account;
import business.service.impl.AccountService;
import business.service.IAccountService;

import java.io.IOException;

import static business.utils.ScannerUtils.inputInteger;

public class StoreManagement {
    public static final IAccountService accountService = new AccountService();
    public static void menu() throws IOException {
        Account account = IOFile.getInstance().readFromCookie(IOFile.getInstance().getCOOKIE_PATH());

        while (true) {
            System.out.println( "+---------------------------------------+\n" +
                                "|              SHOP MANAGEMENT          |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 1 | Account Management                |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 2 | Product Management                |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 3 | Logout                            |\n" +
                                "+---+-----------------------------------+  " );

            System.out.println("Enter choice function:");
            int choice = inputInteger();

            switch (choice) {
                case 1:
                    AccountManagement.menu();
                    break;
                case 2:
                    ProductManagement.menu();
                    break;
                case 3:
                    return;
                default:
                    System.out.printf("The choice \"%d\" is not function %n", choice);
            }
        }
    }
}
