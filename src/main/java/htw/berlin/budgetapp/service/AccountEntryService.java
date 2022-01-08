package htw.berlin.budgetapp.service;

import htw.berlin.budgetapp.presistence.AccountEntryEntity;
import htw.berlin.budgetapp.presistence.EntryType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AccountEntryService {

    List<AccountEntryEntity> findAllEntries();

    Optional<AccountEntryEntity> findEntryById(Long id);

    AccountEntryEntity createEntry(AccountEntryEntity entry);

    AccountEntryEntity updateEntry(Long id, AccountEntryEntity entry);

    boolean deleteAccountEntryById(Long id);
}
