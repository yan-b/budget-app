package htw.berlin.budgetapp.service;

import htw.berlin.budgetapp.presistence.AccountEntity;
import htw.berlin.budgetapp.presistence.AccountRepository;

import java.util.List;

public interface AccountService {

    AccountEntity createNewAccount(String accountName, AccountType accountType);

    List<AccountEntity> getAllAccounts();

}
