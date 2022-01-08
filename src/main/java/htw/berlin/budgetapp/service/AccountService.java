package htw.berlin.budgetapp.service;

import htw.berlin.budgetapp.presistence.AccountEntity;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<AccountEntity> findAllAccounts();

    Optional<AccountEntity> findAccountById(Long id);

    AccountEntity createAccount(AccountEntity account);

    boolean deleteAccountById(Long id);

}
