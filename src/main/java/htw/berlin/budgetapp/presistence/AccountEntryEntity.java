package htw.berlin.budgetapp.presistence;

import htw.berlin.budgetapp.service.AccountType;
import htw.berlin.budgetapp.service.EntryType;

import javax.persistence.*;

@Entity
@Table(name = "accountEntries")
public class AccountEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountEntryId;

    @Column(name = "account_FK", nullable = false)
    private Long accountFk;

    @Column(name = "entry_description", nullable = false)
    private String entryDescription;

    @Column(name = "entry_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private EntryType entryType;

    @Column(name = "entry_amount")
    private double entryAmount;


    public AccountEntryEntity(Long accountFk, String entryDescription, EntryType entryType, Double entryAmount) {
        this.accountFk = accountFk;
        this.entryDescription = entryDescription;
        this.entryType = entryType;
        this.entryAmount = entryAmount;
    }

    protected AccountEntryEntity() {
    }

    public Long getAccountFk() {
        return accountFk;
    }

    public void setAccountFk(Long accountFk) {
        this.accountFk = accountFk;
    }

    public String getEntryDescription() {
        return entryDescription;
    }

    public void setEntryDescription(String entryDescription) {
        this.entryDescription = entryDescription;
    }

    public EntryType getEntryType() {
        return entryType;
    }

    public void setEntryType(EntryType entryType) {
        this.entryType = entryType;
    }

    public double getEntryAmount() {
        return entryAmount;
    }

    public void setEntryAmount(double entryAmount) {
        this.entryAmount = entryAmount;
    }

    @Override
    public String toString() {
        return String.format(
                "Account[Entry-Id=%d, Account-FK= '%s', Entry-Description='%s', Entry-Type='%s', Entry-Amount='%s']",
                accountEntryId, accountFk, entryDescription, entryType, entryAmount);
    }
}