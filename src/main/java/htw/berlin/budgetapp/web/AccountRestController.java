package htw.berlin.budgetapp.web;

import htw.berlin.budgetapp.presistence.AccountEntity;
import htw.berlin.budgetapp.service.AccountServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class AccountRestController {

    private final AccountServiceImpl accountServiceImpl;

    public AccountRestController(AccountServiceImpl accountServiceImpl) {
        this.accountServiceImpl = accountServiceImpl;
    }

    @GetMapping(path = "/api/v1/getAccounts")
    public ResponseEntity<List<AccountEntity>> getAllAccounts() {
        var accounts = accountServiceImpl.findAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping(path = "/api/v1/getAccount/{id}")
    public ResponseEntity<AccountEntity> getAccountById(@PathVariable Long id) {
        var account = accountServiceImpl.findAccountById(id);
        return account != null? ResponseEntity.ok(account.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/newAccount")
    public ResponseEntity<Void> createAccount(@Valid @RequestBody AccountEntity account) throws URISyntaxException {
        var newAccount = accountServiceImpl.createAccount(account);
        URI uri = new URI("/api/v1/getAccount/" + newAccount.getAccountId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/api/v1/updateAccount/{id}")
    public ResponseEntity<AccountEntity> updateAccount(@PathVariable Long id, @RequestBody AccountEntity account) {
        var updateAccount = accountServiceImpl.updateAccount(id, account);
        return updateAccount != null? ResponseEntity.ok(updateAccount) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/deleteAccount/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        boolean successful = accountServiceImpl.deleteAccountById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
