package htw.berlin.budgetapp.web;

import htw.berlin.budgetapp.presistence.AccountEntity;
import htw.berlin.budgetapp.presistence.AccountEntryEntity;
import htw.berlin.budgetapp.presistence.AccountEntryRepository;
import htw.berlin.budgetapp.presistence.AccountRepository;
import htw.berlin.budgetapp.service.AccountServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BudgetRestController {

    private final AccountRepository accountRepository;
    private final AccountEntryRepository accountEntryRepository;

    public BudgetRestController(AccountRepository accountRepository, AccountEntryRepository accountEntryRepository) {
        this.accountRepository = accountRepository;
        this.accountEntryRepository = accountEntryRepository;
    }

    @GetMapping(path = "/rest/getAccounts")
    public List<AccountEntity> findAllAccounts() {
        return (List<AccountEntity>) accountRepository.findAll();
    }

    @PostMapping(path = "/rest/newAccount")
    public AccountEntity newAccountEntity(@RequestBody AccountEntity newAccountEntity) {
        return accountRepository.save(newAccountEntity);
    }

    @GetMapping(path = "/rest/entries")
    public List<AccountEntryEntity> findAllEntries() {
        return (List<AccountEntryEntity>) accountEntryRepository.findAll();
    }

    @PostMapping(path = "/rest/newEntry")
    public AccountEntryEntity newAccountEntryEntity(@RequestBody AccountEntryEntity newAccountEntryEntity) {
        return accountEntryRepository.save(newAccountEntryEntity);
    }

}
