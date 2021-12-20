package htw.berlin.budgetapp.service;

import htw.berlin.budgetapp.presistence.AccountEntity;
import htw.berlin.budgetapp.presistence.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountEntity create(AccountEntity request) {
        var userId = String.valueOf(request.getUserId());
        var accountName = request.getAccountName();
        var accountType = request.getAccountType();
        var accountAmount = request.getAccountBalance();
        var accountEntity = new AccountEntity(userId, accountName, accountType, accountAmount);
        accountEntity = accountRepository.save(accountEntity);
        return accountEntity;
    }

    public List<AccountEntity> getUsersAccount(String userId) {
        return (List<AccountEntity>) accountRepository.findAll();
    }

    public List<AccountEntity> getAllAccounts() {
        List<AccountEntity> accounts = accountRepository.findAll();
        return accounts;
    }

    @Override
    public List<AccountEntity> getUsersAccountsByUserFk(String userFk) {
        return null;
    }
}
