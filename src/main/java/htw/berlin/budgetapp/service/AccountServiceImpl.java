package htw.berlin.budgetapp.service;

import htw.berlin.budgetapp.presistence.AccountEntity;
import htw.berlin.budgetapp.presistence.AccountRepository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class AccountServiceImpl implements AccountService {

    private final RestTemplate restTemplate;
    private final AccountRepository accountRepository;

    public AccountServiceImpl(RestTemplate restTemplate, AccountRepository accountRepository) {
        this.restTemplate = restTemplate;
        this.accountRepository = accountRepository;
    }

    public AccountEntity createNewAccount(String userId, String accountName, AccountType accountType, Double accountAmount) {
        return accountRepository.save(new AccountEntity(userId, accountName, accountType, accountAmount));
    }

    public List<AccountEntity> getUsersAccount(String userId) {
        return (List<AccountEntity>) accountRepository.findAll();
    }

    public List<AccountEntity> getAllAccounts() {
        return (List<AccountEntity>) accountRepository.findAll();
    }

    @Override
    public List<AccountEntity> getUsersAccountsByUserFk(String userFk) {
        return null;
    }
}
