package htw.berlin.budgetapp.service;

import htw.berlin.budgetapp.presistence.AccountEntryEntity;

import java.util.List;

public interface AccountEntryService {

    AccountEntryEntity createNewEntry(Long accountFk, String entryDescription, EntryType entryType, Double entryAmount);

    List<AccountEntryEntity> getAllEntries();
}
