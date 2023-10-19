package business.service.impl;

import business.config.IOFile;
import business.entity.account.Account;
import business.entity.account.Role;
import business.service.IAccountService;
import business.utils.InputData;
import business.utils.ScannerUtils;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AccountService implements IAccountService {
    private static final List<Account> accountList = IOFile.getInstance().readFromFile(IOFile.getInstance().getACCOUNT_PATH());

    @Override
    public List<Account> findAll() {
        return accountList;
    }

    @Override
    public Account findById(Integer id) {
        if (!accountList.isEmpty()) {
            for (int i = 0; i < accountList.size(); i++) {
                if (accountList.get(i).getId() == id) {
                    return accountList.get(i);
                }
            }
        }
        return null;
    }

    @Override
    public void create() {
    }


    @Override
    public void update(Account account) {
        String newFirstName, newLastName, newPhoneNumber, newAddress;

        /**First name*/
        System.out.println("Enter first name:");
        newFirstName = ScannerUtils.inputString();

        if (!newFirstName.isEmpty()) {
            account.setFirstName(newFirstName);
        }

        /**Last name*/
        System.out.println("Enter last name:");
        newLastName = ScannerUtils.inputString();

        if (!newLastName.isEmpty()) {
            account.setLastName(newLastName);
        }

        /**Phone number*/
        String phoneRegex = "^0{1}\\d{9,10}$";

        do {
            System.out.println("Enter phone number:");
            newPhoneNumber = ScannerUtils.inputString();

            if (newPhoneNumber.isEmpty()) {
                break;
            }

            if (!Pattern.matches(phoneRegex, newPhoneNumber)) {
                account.setPhoneNumber(newPhoneNumber);
                break;
            } else {
                System.err.printf("The Phone number \"%s\" is invalid, must be 10 or 11 character! " +
                        "(Example: 0988888888)%n", newPhoneNumber);
            }

        } while (!Pattern.matches(phoneRegex, newPhoneNumber));

        /**Address*/
        System.out.println("Enter address:");
        newAddress = ScannerUtils.inputString();

        if (!newAddress.isEmpty()) {
            account.setAddress(newAddress);
        }
        System.out.println("Update account successfully");
    }

    @Override
    public boolean delete(Integer id) {
        if (!accountList.isEmpty()) {
            for (int i = 0; i < accountList.size(); i++) {
                if (accountList.get(i).getId() == id) {
                    accountList.get(i).setStatus(false);
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public boolean isExistById(Integer id) {
        if (!accountList.isEmpty()) {
            for (int i = 0; i < accountList.size(); i++) {
                if (accountList.get(i).getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void save() {

        IOFile.getInstance().writeToFile(IOFile.getInstance().getACCOUNT_PATH(), accountList);
    }

    @Override
    public boolean isExistByEmail(String email) {
        if (!accountList.isEmpty()) {
            for (int i = 0; i < accountList.size(); i++) {
                if (accountList.get(i).getEmail().equalsIgnoreCase(email)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Account findAccountByEmail(String email) {
        if (!accountList.isEmpty()) {
            for (int i = 0; i < accountList.size(); i++) {
                if (accountList.get(i).getEmail().equalsIgnoreCase(email)) {
                    return accountList.get(i);
                }
            }
        }
        return null;
    }

    @Override
    public List<Account> sortAccountByRole(Role role) {
        if (accountList.size() > 1) {
            return accountList.stream().filter(account -> account.getRole().equals(role)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public void changePassword(Account account) {
        String password, confPassword;

        /**Current password*/
        do {
            password  = InputData.text("password");

            if(!account.getPassword().equals(password)) {
                System.err.println("The password is wrong!");
            }
        }while (!account.getPassword().equals(password));

        /**New password*/
        do {
            password  = InputData.text("new password");

            if(password.length() < 6) {
                System.err.println("The password must be more than 6 character!");
            }
        }while (password.length() < 6);

        /**Confirm password*/
        do {
            confPassword  = InputData.text("new password");

            if(!confPassword.equals(password)) {
                System.err.println("Confirm password is incorrect!");
            }
        }while (!confPassword.equals(password));

        account.setPassword(password);
    }
}
