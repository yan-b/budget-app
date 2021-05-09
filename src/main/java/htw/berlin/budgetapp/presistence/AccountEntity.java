package htw.berlin.budgetapp.presistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class AccountEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long accountId;

        @Column(nullable = false)
        private String accountName;

        @Column(nullable = false)
        private String accountType;

        @Column(nullable = false)
        private double accountBalance;


        public AccountEntity(String accountName, String accountType, double accountBalance) {
            this.accountName = accountName;
            this.accountType = accountType;
            this.accountBalance = accountBalance;
        }

        protected AccountEntity() {}

        public Long getId() {
            return accountId;
        }


        public String getAccountName() {
            return accountName;
        }


        public String getAccountType() {
            return accountType;
        }


        public double getAccountBalance() {
            return accountBalance;
        }

        public void setAccountBalance(double balance) {
            this.accountBalance = balance;
        }
    }