package business.service;

import business.entity.account.Account;
import business.entity.account.Role;
import business.service.IGeneric;

import java.util.List;

public interface IAccountService extends IGeneric<Account, Integer> {
    boolean delete(Integer id);
    boolean isExistByEmail(String email);
    Account findAccountByEmail(String email);
    List<Account> sortAccountByRole(Role role);
    void changePassword(Account account);
}
