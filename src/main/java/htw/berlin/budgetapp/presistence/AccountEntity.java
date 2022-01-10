package htw.berlin.budgetapp.presistence;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "accounts")
public class AccountEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long accountId;

        @Column(name = "user_fk")
        private String userId;

        @Column(name = "account_name", nullable = false)
        @NotBlank(message = "The account name must not be empty.")
        private String accountName;

        @Column(name = "account_type", nullable = false)
        @Enumerated(value = EnumType.STRING)
        private AccountType accountType;

        @Column(name = "account_balance")
        private double accountBalance;


        public AccountEntity(String userId, String accountName, AccountType accountType, Double accountBalance) {
            this.userId = userId;
            this.accountName = accountName;
            this.accountType = accountType;
            this.accountBalance = accountBalance;
        }

        protected AccountEntity() {}

        public Long getAccountId() { return accountId; }

        public void setAccountId(Long accountId) { this.accountId = accountId; }

        public String getUserId() { return userId; }

        public void setUserId(String userId) { this.userId = userId; }

        public String getAccountName() { return accountName; }

        public void setAccountName(String accountName) { this.accountName = accountName; }

        public AccountType getAccountType() { return accountType; }

        public void setAccountType(AccountType accountType) { this.accountType = accountType; }

        public double getAccountBalance() { return accountBalance; }

        public void setAccountBalance(double accountBalance) { this.accountBalance = accountBalance; }

        @Override
        public String toString() {
                return String.format(
                        "Account[Account-Id=%d, User-FK= '%s', Account-Name='%s', Account-Type='%s', Account-Balance='%s']",
                        accountId, userId, accountName, accountType, accountBalance);
        }

}
