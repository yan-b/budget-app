package htw.berlin.budgetapp.service;

import htw.berlin.budgetapp.presistence.AccountEntity;
import htw.berlin.budgetapp.presistence.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountEntity> findAllAccounts() {

        List<AccountEntity> accounts = accountRepository.findAll();
        return accounts;
    }

    public Optional<AccountEntity> findAccountById(Long id) {

        var account = accountRepository.findById(id);
        return account;
    }

    public AccountEntity createAccount(AccountEntity account) {

        var userId = "111";
        var accountName = account.getAccountName();
        var accountType = account.getAccountType();
        var accountAmount = account.getAccountBalance();
        var accountEntity = new AccountEntity(userId, accountName, accountType, accountAmount);
        accountEntity = accountRepository.save(accountEntity);

        return accountEntity;
    }

    public AccountEntity updateAccount(Long id, AccountEntity account) {

        var accountEntityOptional = accountRepository.findById(id);
        if (accountEntityOptional.isEmpty()) {
            return null;
        }

        var accountEntity = accountEntityOptional.get();
        accountEntity.setUserId(accountEntity.getUserId());
        accountEntity.setAccountName(account.getAccountName());
        accountEntity.setAccountType(account.getAccountType());
        accountEntity.setAccountBalance(account.getAccountBalance());
        accountEntity = accountRepository.save(accountEntity);

        return accountEntity;
    }

    public boolean deleteAccountById(Long id) {

        if (!accountRepository.existsById(id)) {
            return false;
        }
        accountRepository.deleteById(id);
        return true;
    }
}
