package htw.berlin.budgetapp.presistence;

import htw.berlin.budgetapp.service.AccountType;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class AccountEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long accountId;

        @Column(name = "user_fk", nullable = false)
        private String userId;

        @Column(name = "account_name", nullable = false)
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


        @Override
        public String toString() {
        return String.format(
                "Account[Account-Id=%d, Account-Name='%s', Account-Balance='%s']",
                accountId, accountName, accountBalance);
        }

        public String getUserId() { return userId;}

        public void setUserId(String userId) { this.userId = userId; }

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

        public void setAccountBalance(double accountBalance) { this.accountBalance = accountBalance; }
}