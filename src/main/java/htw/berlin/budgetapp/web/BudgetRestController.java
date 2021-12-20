package htw.berlin.budgetapp.web;

import htw.berlin.budgetapp.presistence.AccountEntity;
import htw.berlin.budgetapp.presistence.AccountEntryEntity;
import htw.berlin.budgetapp.presistence.AccountEntryRepository;
import htw.berlin.budgetapp.presistence.AccountRepository;
import htw.berlin.budgetapp.service.AccountServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BudgetRestController {

    private final AccountRepository accountRepository;
    private final AccountEntryRepository accountEntryRepository;
    private final AccountServiceImpl accountServiceImpl;

    public BudgetRestController(AccountRepository accountRepository, AccountEntryRepository accountEntryRepository, AccountServiceImpl accountServiceImpl) {
        this.accountRepository = accountRepository;
        this.accountEntryRepository = accountEntryRepository;
        this.accountServiceImpl = accountServiceImpl;
    }

    @GetMapping(path = "/api/v1/getAccounts")
    public List<AccountEntity> getAllAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping(path = "/api/v1/getAccounts/{id}")
    public Optional<AccountEntity> findAccountById(@PathVariable Long id) {
        return accountRepository.findById(id);
    }

    @PostMapping(path = "/api/v1/newAccount")
    public void createNewAccount(@RequestBody AccountEntity newAccountEntity) {
        accountServiceImpl.create(newAccountEntity);
    }

    @GetMapping(path = "/api/v1/getEntries")
    public List<AccountEntryEntity> findAllEntries() {
        return (List<AccountEntryEntity>) accountEntryRepository.findAll();
    }

    @PostMapping(path = "/api/v1/newEntry")
    public AccountEntryEntity newAccountEntryEntity(@RequestBody AccountEntryEntity newAccountEntryEntity) {
        return accountEntryRepository.save(newAccountEntryEntity);
    }

}
