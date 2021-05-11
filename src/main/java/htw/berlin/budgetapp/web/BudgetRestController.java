package htw.berlin.budgetapp.web;

import htw.berlin.budgetapp.presistence.AccountEntity;
import htw.berlin.budgetapp.presistence.AccountRepository;
import htw.berlin.budgetapp.service.AccountServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BudgetRestController {

    private final AccountRepository accountRepository;

    public BudgetRestController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @GetMapping(path = "/accounts")
    public List<AccountEntity> all() {
        return (List<AccountEntity>) accountRepository.findAll();
    }


    @PostMapping(path = "/newAccount")
    public AccountEntity newAccountEntity(@RequestBody AccountEntity newAccountEntity) {
        return accountRepository.save(newAccountEntity);
    }

}
