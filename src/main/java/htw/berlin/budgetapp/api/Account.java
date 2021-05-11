package htw.berlin.budgetapp.api;

import htw.berlin.budgetapp.service.AccountType;

public class Account {

    private Long accountId;
    private String accountName;
    private AccountType accountType;
    private double accountBalance;


    public Account(Long accountId, String accountName, AccountType accountType, double accountBalance) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
    }

    public Long getId() {
        return accountId;
    }

    public String getName() {
        return accountName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return accountBalance;
    }
}
