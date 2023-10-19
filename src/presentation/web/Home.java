package presentation.web;

import business.config.IOFile;
import business.entity.Stock;
import business.entity.account.Account;
import business.service.impl.StockService;
import business.utils.InputData;
import business.utils.ScannerUtils;
import presentation.shop.StoreManagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static business.service.authen.Authentication.logout;
import static presentation.shop.StoreManagement.accountService;

public class Home {
    public static void shopLuxe() throws IOException {
        Account account = IOFile.getInstance().readFromCookie(IOFile.getInstance().getCOOKIE_PATH());

        while (true) {
            System.out.println( "+---------------------------------------+\n" +
                                "|              LUXE SHOP                |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 1 | PRODUCTS FOR MEN                  |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 2 | Edit profile                      |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 3 | Change password                   |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 4 | View profile                      |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 5 | Logout                            |\n" +
                                "+---+-----------------------------------+\n" );
            System.out.println("Enter choice:");
            int choice = ScannerUtils.inputInteger();

            switch (choice) {
                case 1:
                    Men.products();
                    break;
                case 2:
                    profile(account);
                    accountService.update(account);
                    accountService.save();
                    break;
                case 3:
                    accountService.changePassword(accountService.findById(account.getId()));
                    accountService.save();
                    System.out.println("Change password successfully!");
                    break;
                case 4:
                    profile(account);
                    break;
                case 5:
                    logout();
                    return;
                default:
                    System.err.printf("No choice \"%d\"%n", choice);
            }
        }
    }

    private static void profile(Account account){

        System.out.println("+--------------------------------------------+");
        System.out.println("|                   PROFILE                  +");
        System.out.println("+--------------------------------------------+");
        System.out.printf("| First name | %-30s|%n", account.getFirstName());
        System.out.println("+--------------------------------------------+");
        System.out.printf("| Last name  | %-30s|%n", account.getLastName());
        System.out.println("+--------------------------------------------+");
        System.out.printf("| Phone      | %-30s|%n", account.getPhoneNumber());
        System.out.println("+--------------------------------------------+");
        System.out.printf("| Address    | %-30s|%n", account.getAddress());
        System.out.println("+--------------------------------------------+");

    }
}
