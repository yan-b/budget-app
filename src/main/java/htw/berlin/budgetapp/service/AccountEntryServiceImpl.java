package htw.berlin.budgetapp.service;

import htw.berlin.budgetapp.presistence.*;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AccountEntryServiceImpl implements AccountEntryService{

    private final AccountEntryRepository accountEntryRepository;

    public AccountEntryServiceImpl(AccountEntryRepository accountEntryRepository) {
        this.accountEntryRepository = accountEntryRepository;
    }

    public List<AccountEntryEntity> findAllEntries() {

        List<AccountEntryEntity> accountEntry = accountEntryRepository.findAll();
        return accountEntry;
    }

    public Optional<AccountEntryEntity> findEntryById(Long id) {

        var entry = accountEntryRepository.findById(id);
        return entry;
    }

    public List<AccountEntryEntity> findEntriesByAccountFk(Long accountFk) {
        var entries = accountEntryRepository.findByAccountFk(accountFk);
        return entries;
    }

    public AccountEntryEntity createEntry(AccountEntryEntity entry) {

        var accountFk = entry.getAccountFk();
        var description = entry.getEntryDescription();
        var type = entry.getEntryType();
        var amount = entry.getEntryAmount();
        var date = entry.getEntryDate();
        var accountEntryEntity = new AccountEntryEntity(accountFk, description, type, amount, date, "111");
        accountEntryEntity = accountEntryRepository.save(accountEntryEntity);

        return accountEntryEntity;
    }

    public AccountEntryEntity updateEntry(Long id, AccountEntryEntity entry) {

        var accountEntryEntityOptional = accountEntryRepository.findById(id);
        if (accountEntryEntityOptional.isEmpty()) {
            return null;
        }

        var accountEntryEntity = accountEntryEntityOptional.get();
        accountEntryEntity.setAccountFk(entry.getAccountFk());
        accountEntryEntity.setEntryDescription(entry.getEntryDescription());
        accountEntryEntity.setEntryType(entry.getEntryType());
        accountEntryEntity.setEntryAmount(entry.getEntryAmount());
        accountEntryEntity.setEntryDate(entry.getEntryDate());
        accountEntryEntity = accountEntryRepository.save(accountEntryEntity);

        return accountEntryEntity;
    }

    public boolean deleteAccountEntryById(Long id) {

        if (!accountEntryRepository.existsById(id)) {
            return false;
        }
        accountEntryRepository.deleteById(id);
        return true;
    }
}
