package htw.berlin.budgetapp.web;

import htw.berlin.budgetapp.presistence.AccountEntryEntity;
import htw.berlin.budgetapp.service.AccountEntryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class EntriesRestController {

    private final AccountEntryServiceImpl accountEntryServiceImpl;

    public EntriesRestController(AccountEntryServiceImpl accountEntryServiceImpl) {
        this.accountEntryServiceImpl = accountEntryServiceImpl;
    }

    @GetMapping(path = "/api/v1/getEntries")
    public ResponseEntity<List<AccountEntryEntity>> getAllEntries() {
        var accountEntry = accountEntryServiceImpl.findAllEntries();
        return ResponseEntity.ok(accountEntry);
    }

    @GetMapping(path = "/api/v1/getEntry/{id}")
    public ResponseEntity<AccountEntryEntity> getEntryById(@PathVariable Long id) {
        var accountEntry = accountEntryServiceImpl.findEntryById(id);
        return accountEntry != null? ResponseEntity.ok(accountEntry.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/newEntry")
    public ResponseEntity<Void> createEntry(@RequestBody AccountEntryEntity entry) throws URISyntaxException {
        var newEntry = accountEntryServiceImpl.createEntry(entry);
        URI uri = new URI("/api/v1/getEntry/" + newEntry.getAccountEntryId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/api/v1/updateEntry/{id}")
    public ResponseEntity<AccountEntryEntity> updateEntry(@PathVariable Long id, @RequestBody AccountEntryEntity entry) {
        var updateEntry = accountEntryServiceImpl.updateEntry(id, entry);
        return updateEntry != null? ResponseEntity.ok(updateEntry) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/deleteEntry/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        boolean successful = accountEntryServiceImpl.deleteAccountEntryById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
