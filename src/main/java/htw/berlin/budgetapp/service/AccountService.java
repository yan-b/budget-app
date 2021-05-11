package htw.berlin.budgetapp.service;

import htw.berlin.budgetapp.api.Account;
import htw.berlin.budgetapp.presistence.AccountEntity;
import htw.berlin.budgetapp.presistence.AccountRepository;

import java.util.List;

public interface AccountService {

    public AccountEntity createNewAccount(String accountName, AccountType accountType);

    public List<AccountEntity> getAllAccounts();

}
