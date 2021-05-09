package htw.berlin.budgetapp.service;

public class Account {

    private String accountId;
    private String accountName;
    private String accountType;
    private double accountBalance;


    public Account(String accountId, String accountName, String accountType, double accountBalance) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
    }

    public String getId() {
        return accountId;
    }

    public void setId(String id) {
        this.accountId = id;
    }

    public String getName() {
        return accountName;
    }

    public void setName(String name) {
        this.accountName = name;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return accountBalance;
    }

    public void setBalance(double balance) {
        this.accountBalance = balance;
    }
}
