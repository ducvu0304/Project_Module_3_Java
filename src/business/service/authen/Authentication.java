package business.service.authen;

import business.config.IOFile;
import business.entity.account.Account;
import business.entity.account.Role;
import business.utils.ScannerUtils;

import java.io.IOException;
import java.util.regex.Pattern;

import static business.utils.IdGenerator.generateUniqueId;
import static business.utils.ScannerUtils.inputString;
import static presentation.shop.StoreManagement.accountService;

public class Authentication  {

    /**
     * Login
     * */
    public static Account login() {

        System.out.println("===== LOGIN ===== ");
        System.out.println("Email:");
        String email = ScannerUtils.inputString().trim();

        System.out.println("Password:");
        String password = ScannerUtils.inputString().trim();

        Account account = accountService.findAccountByEmail(email);

        if (account != null && account.getPassword().equals(password)) {
            if(account.isStatus()) {
                System.out.println("Login successfully!");
                return account;
            }else {
                System.err.printf("The account \"%s\" is blocked ! %n", account.getEmail());
                return null;
            }
        }

        System.err.println("Login failed! (Email or password are wrong)");
        return null;
    }

    /**
     * Register
     * */
    public static void register() throws IOException {
        String emailRegex = "^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$";
        String phoneRegex = "^0{1}\\d{9,10}$";

        Account account = new Account();

        do {
            System.out.println("===== REGISTER =====");
            /**Input Email*/
            do {
                System.out.println("Email:");
                String email = ScannerUtils.inputString().trim();

                if (!Pattern.matches(emailRegex, email)) {
                    System.err.printf("The email \"%s\" is invalid! (Example: nguyen@gmail.com)%n", email);
                }else if(accountService.isExistByEmail(email)) {
                    System.err.printf("The email \"%s\" is exists! %n", email);
                }else {
                    account.setEmail(email);
                }

            } while (account.getEmail() == null );


            /**Input Password*/
            do {
                System.out.println("Password:");
                String password = ScannerUtils.inputString().trim();

                if (password.length() < 6) {
                    System.err.println("The Password at least 6 character!");
                }else {
                    /**Confirm password*/
                    do {
                        System.out.println("Confirm password:");
                        String confirmPassword = ScannerUtils.inputString();

                        if(password.equals(confirmPassword)) {
                            account.setPassword(password);
                        }else {
                            System.err.println("Confirm password is wrong");
                        }
                    }while (account.getPassword() == null);
                }
            } while (account.getPassword() == null);

            /**Input Firstname*/
            do {
                System.out.println("First name:");
                account.setFirstName(inputString().trim());

                if (account.getFirstName().isEmpty()) {
                    System.err.println("The Firstname must be not empty!");
                }
            } while (account.getFirstName().isEmpty());

            /**Input Lastname*/
            do {
                System.out.println("Last name:");
                account.setLastName(inputString().trim());

                if (account.getLastName().isEmpty()) {
                    System.err.println("The Lastname must be not empty!");
                }
            } while (account.getLastName().isEmpty());

            /**Input Phone number*/
            do {
                System.out.println("Phone number:");
                account.setPhoneNumber(ScannerUtils.inputString().trim());

                if (!Pattern.matches(phoneRegex, account.getPhoneNumber())) {
                    System.err.printf("The Phone number \"%s\" is invalid, must be 10 or 11 character!  (Example: 0988888888)%n", account.getPhoneNumber());
                }
            } while (!Pattern.matches(phoneRegex, account.getPhoneNumber()));

            /**Input Address*/
            do {
                System.out.println("Address:");
                account.setAddress(ScannerUtils.inputString().trim());

                if (account.getAddress().isEmpty()) {
                    System.err.println("The address must be not empty!");
                }
            } while (account.getAddress().isEmpty());

            account.setId(generateUniqueId());
            account.setRole(Role.USER);
            account.setStatus(true);

            accountService.findAll().add(account);
            accountService.save();
            System.out.println("Create account successfully!");
            break;
        }while (true);
    }

    /**
     * Logout
     * */
    public static void logout() {
        Account account = null;
        IOFile.getInstance().writeToCookie(IOFile.getInstance().getCOOKIE_PATH(), null);
    }
}
