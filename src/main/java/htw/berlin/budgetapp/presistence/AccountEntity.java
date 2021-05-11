package htw.berlin.budgetapp.presistence;

import htw.berlin.budgetapp.service.AccountType;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class AccountEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long accountId;

        @Column(name = "account_name", nullable = false)
        private String accountName;

        @Column(name = "account_type", nullable = false)
        @Enumerated(value = EnumType.STRING)
        private AccountType accountType;

        @Column(name = "account_balance")
        private double accountBalance;


        public AccountEntity(String accountName, AccountType accountType) {
            this.accountName = accountName;
            this.accountType = accountType;
            this.accountBalance = 0.00;
        }

        protected AccountEntity() {}


        @Override
        public String toString() {
        return String.format(
                "Account[Account-Id=%d, Account-Name='%s', Account-Balance='%s']",
                accountId, accountName, accountBalance);
    }

        public Long getId() {
            return accountId;
        }


        public String getAccountName() {
            return accountName;
        }


        public AccountType getAccountType() {
            return accountType;
        }


        public double getAccountBalance() {
            return accountBalance;
        }
 }