package presentation.shop;

import business.entity.account.Account;
import business.entity.account.Role;
import business.service.authen.Authentication;
import business.utils.ScannerUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static business.utils.ScannerUtils.inputInteger;
import static presentation.shop.StoreManagement.accountService;

public class AccountManagement {
    private static List<Account> accountList;
    private static Account account;
    private static int accountId;
    private static String accountEmail;
    private static Optional<List<Account>> optionalList;

    public static void menu() throws IOException {

        while (true) {
            System.out.println ("+---------------------------------------+\n" +
                                "|            ACCOUNT MANAGEMENT         |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 1 | View list account                 |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 2 | Find account by id                |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 3 | Find account by email             |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 4 | Sort by role                      |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 5 | Add account                       |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 6 | Edit account status               |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 7 | Exit                              |\n" +
                                "+---+-----------------------------------+  " );

            System.out.println("Enter choice function:");
            int choice = inputInteger();

            switch (choice) {
                case 1:
                    accountList = accountService.findAll();

                    if (!accountList.isEmpty()) {
                        header();
                        for (int i = 0; i < accountList.size() ; i++) {
                            accountInformation(i+1, accountList.get(i));
                        }
                    }

                    break;
                case 2:
                    System.out.println("Enter id:");
                    accountId = ScannerUtils.inputInteger();
                    account = accountService.findById(accountId);

                    if(account != null) {
                        header();
                        accountInformation(1, account);
                    }else {
                        System.out.printf("Not found account with id \"%d\" %n", accountId);
                    }

                    break;
                case 3:
                    System.out.println("Enter email:");
                    accountEmail = ScannerUtils.inputString();
                    account = accountService.findAccountByEmail(accountEmail);

                    if(account != null) {
                        header();
                        accountInformation(1, account);
                    }else {
                        System.out.printf("Not found account with email \"%s\" %n", accountEmail);
                    }
                    break;
                case 4:
                    System.out.println("1. Admin ");
                    System.out.println("2. User  ");
                    int choiceRole = ScannerUtils.inputInteger();

                    switch (choiceRole) {
                        case 1:
                            optionalList = Optional.ofNullable(accountService.sortAccountByRole(Role.ADMIN));

                            if (optionalList.isPresent()) {
                                accountList = optionalList.get();
                                header();
                                for (int i = 0; i < accountList.size(); i++) {
                                    accountInformation(i+1, accountList.get(i));
                                }
                            }else {
                                System.out.println("List account is empty !");
                            }

                            break;
                        case 2:
                             optionalList = Optional.ofNullable(accountService.sortAccountByRole(Role.USER));

                            if (optionalList.isPresent()) {
                                accountList = optionalList.get();
                                header();
                                for (int i = 0; i < accountList.size(); i++) {
                                    accountInformation(i+1, accountList.get(i));
                                }
                            }else {
                                System.out.println("List account is empty !");
                            }

                            break;
                        default:
                            System.out.println("No choice");
                    }

                    break;
                case 5:
                    Authentication.register();
                    break;
                case 6:
                    System.out.println("Enter id:");
                    accountId = ScannerUtils.inputInteger();

                    if( accountService.delete(accountId)) {
                        System.out.printf("Account \"%d\" update status successfully!%n", accountId);
                    }else {
                        System.out.printf("Not found account with id \"%d\" %n", accountId);
                    }

                    accountService.save();
                    break;
                case 7:
                   return;
                default:
                    System.out.println();
            }
        }
    }

    private static void header() {
        System.out.println("+------------------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("+                                                                 ACCOUNT LIST                                                                   +");
        System.out.println("+-----+------------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-4s| %-16s| %-20s| %-12s| %-12s| %-15s| %-30s| %-8s| %-10s| %n",
                "STT", "Account ID", "Email", "Firstname", "Lastname", "Phone number", "Address", "Role", "Status");
        System.out.println("+-----+------------------------------------------------------------------------------------------------------------------------------------------+");
    }

    private static void accountInformation(int stt, Account account) {
        System.out.printf("| %-4d| %-16d| %-20s| %-12s| %-12s| %-15s| %-30s| %-8s| %-10s| %n",
                stt ,
                account.getId(),
                account.getEmail(),
                account.getFirstName(),
                account.getLastName(),
                account.getPhoneNumber(),
                account.getAddress(),
                account.getRole(),
                account.isStatus() ? "Active" : "Block");

        System.out.println("+-----+------------------------------------------------------------------------------------------------------------------------------------------+");
    }


}
