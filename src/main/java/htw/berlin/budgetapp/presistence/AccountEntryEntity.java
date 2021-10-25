package htw.berlin.budgetapp.presistence;

import htw.berlin.budgetapp.service.AccountType;
import htw.berlin.budgetapp.service.EntryType;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Column(name = "entry_date")
    private LocalDate entryDate;

    @Column(name = "user_fk")
    private String userFk;


    public AccountEntryEntity(Long accountFk, String entryDescription, EntryType entryType, Double entryAmount, LocalDate entryDate, String userFk) {
        this.accountFk = accountFk;
        this.entryDescription = entryDescription;
        this.entryType = entryType;
        this.entryAmount = entryAmount;
        this.entryDate = entryDate;
        this.userFk = userFk;
    }

    protected AccountEntryEntity() {
    }

    public String getUserFk() { return userFk; }

    public void setUserFk(String userFk) { this.userFk = userFk; }

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

    public Long getAccountEntryId() {
        return accountEntryId;
    }

    public void setAccountEntryId(Long accountEntryId) {
        this.accountEntryId = accountEntryId;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    @Override
    public String toString() {
        return String.format(
                "Account[Entry-Id=%d, Account-FK= '%s', Entry-Description='%s', Entry-Type='%s', Entry-Amount='%s']",
                accountEntryId, accountFk, entryDescription, entryType, entryAmount);
    }
}