package htw.berlin.budgetapp.service;

import htw.berlin.budgetapp.presistence.AccountEntity;
import htw.berlin.budgetapp.presistence.AccountRepository;

import java.util.List;

public interface AccountService {

    AccountEntity create(AccountEntity request);

    List<AccountEntity> getUsersAccount(String userId);

    List<AccountEntity> getAllAccounts();

    List<AccountEntity> getUsersAccountsByUserFk(String userFk);

}
