package htw.berlin.budgetapp.service;

import htw.berlin.budgetapp.presistence.AccountEntity;
import htw.berlin.budgetapp.presistence.AccountEntryEntity;
import htw.berlin.budgetapp.presistence.AccountEntryRepository;
import htw.berlin.budgetapp.presistence.AccountRepository;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

public class AccountEntryServiceImpl implements AccountEntryService{

    private final RestTemplate restTemplate;
    private final AccountEntryRepository accountEntryRepository;

    public AccountEntryServiceImpl(RestTemplate restTemplate, AccountEntryRepository accountEntryRepository) {
        this.restTemplate = restTemplate;
        this.accountEntryRepository = accountEntryRepository;
    }

    @Override
    public AccountEntryEntity createNewEntry(Long accountFk, String entryDescription, EntryType entryType, Double entryAmount, LocalDate entryDate, String userFk) {
        return accountEntryRepository.save(new AccountEntryEntity(accountFk, entryDescription, entryType, entryAmount, entryDate, userFk));
    }

    @Override
    public List<AccountEntryEntity> getAllEntries() {
        return (List<AccountEntryEntity>) accountEntryRepository.findAll();
    }
}
