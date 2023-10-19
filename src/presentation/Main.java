package presentation;

import business.config.IOFile;
import business.entity.account.Account;

import business.entity.account.Role;
import business.service.authen.Authentication;
import business.utils.ScannerUtils;
import presentation.shop.StoreManagement;
import presentation.web.Home;

import java.util.Optional;

public class Main {
    public static void main(String[] args) throws  Exception {
        while (true) {
            System.out.println( "+-----------------------------------------+\n" +
                                "|                 LUXE SHOP               |\n" +
                                "+---+-------------------------------------+\n" +
                                "| 1 | Login                               |\n" +
                                "+---+-------------------------------------+\n" +
                                "| 2 | Register                            |\n" +
                                "+---+-------------------------------------+\n"+
                                "| 3 | Exit                                |\n" +
                                "+---+-------------------------------------");
            System.out.println("Choice function:");
            int choice = ScannerUtils.inputInteger();

            switch (choice) {
                case 1:
                    Optional<Account> optionalAccount =  Optional.ofNullable(Authentication.login());

                    if(optionalAccount.isPresent()) {
                        IOFile.getInstance().writeToCookie(IOFile.getInstance().getCOOKIE_PATH(), optionalAccount.get());
                        if(optionalAccount.get().getRole() == Role.ADMIN) {
                            StoreManagement.menu();
                        }else {
                            Home.shopLuxe();
                        }
                    }
                    break;
                case 2:
                    Authentication.register();
                    break;
                case 3:
                    ScannerUtils.closeScanner();
                    System.exit(0);
                default:
                    System.out.printf("The choice \"%d\" is not function %n", choice);
            }
        }
    }
}
