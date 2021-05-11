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

    @Override
    public AccountEntity createNewAccount(String accountName, AccountType accountType) {
        return accountRepository.save(new AccountEntity(accountName, accountType));
//        return new AccountEntity(accountName, accountType);
    }

    public List<AccountEntity> getAllAccounts() {
        return (List<AccountEntity>) accountRepository.findAll();
    }


}
