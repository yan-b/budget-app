package htw.berlin.budgetapp.web;

import htw.berlin.budgetapp.config.Endpoints;
import htw.berlin.budgetapp.config.ViewNames;
import htw.berlin.budgetapp.presistence.AccountEntity;
import htw.berlin.budgetapp.presistence.AccountEntryRepository;
import htw.berlin.budgetapp.presistence.AccountRepository;
import htw.berlin.budgetapp.service.AccountType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.util.*;

@Controller
public class AccountController {
    private final AccountRepository accountRepository;
    private final AccountEntryRepository accountEntryRepository;

    public AccountController(AccountRepository accountRepository, AccountEntryRepository accountEntryRepository) {
        this.accountRepository = accountRepository;
        this.accountEntryRepository = accountEntryRepository;
    }

    @GetMapping(value = {Endpoints.ACCOUNTS})
    public ModelAndView getAccountList(@AuthenticationPrincipal OidcUser user) {
        Map<String, Object> mav = new HashMap<String, Object>();

        if (user != null) {
            String token = user.getSubject();
            mav.put("name", user.getGivenName() + " " + user.getFamilyName());
            List<AccountEntity> accounts = accountRepository.findByUserId(token);
            Iterator<AccountEntity> accountIterator = accounts.iterator();
            DecimalFormat df2 = new DecimalFormat("#.00");

            if (!accounts.isEmpty()) {
                Double sumBal = 0.00;
                ArrayList<Double> sumList = new ArrayList<Double>();
                ArrayList<AccountEntity> userAccounts = new ArrayList<AccountEntity>();

                while (accountIterator.hasNext()) {
                    AccountEntity acc = accountIterator.next();
                    userAccounts.add(acc);
                    Double acc_balance = acc.getAccountBalance();
                    sumList.add(acc_balance);

                }
                if (!sumList.isEmpty()) {
                    sumBal = sumList.stream().reduce(0.00, Double::sum);
                } else {
                    sumBal = 0.00;
                }
                mav.put("balance", df2.format(sumBal).toString());
                mav.put("accountOverview", userAccounts);
                return new ModelAndView(ViewNames.ACCOUNTS, mav);
            } else {
                String str = "You don't have any accounts yet. Would you like to get started?";
                mav.put("accountOverviewEmpty", str);
                return new ModelAndView(ViewNames.ACCOUNTS, mav);
            }
        } else {
            return new ModelAndView(ViewNames.ACCOUNTS);
        }
    }

    // @AuthenticationPrincipal OidcUser user, ---- LINK: /name={name}/type={type}/balance={balance}
    @PostMapping("/api/v1/newAccount2")
    public ModelAndView createNewAccount(@RequestBody AccountEntity accountEntity) {
        String userId = "00u12dvbrnzq5XiNP5d7";
//      String userId = user.getSubject();
        AccountType accountType = accountEntity.getAccountType();
        Double accountBalance = accountEntity.getAccountBalance();

        AccountEntity newAccountEntity = new AccountEntity(userId, accountEntity.getAccountName(), accountType, accountBalance);
        accountRepository.save(newAccountEntity);

        return new ModelAndView("/accounts");
    }

    @GetMapping("/accounts/deleteAccount/id={id}")
    @Transactional
    public String deleteAccount(@AuthenticationPrincipal OidcUser user, @PathVariable String id) {
        Long accountId = Long.parseLong(id);
        String uid = accountRepository.findById(accountId).get().getUserId();

        if (uid.equals(user.getSubject())) {
            accountEntryRepository.deleteAllByAccountFk(accountId);
            accountRepository.deleteById(accountId);
        }
        return "redirect:/accounts";
    }

    @PutMapping("accounts/updateBalance/id={id}/amount={amount}")
    public String updateBalance(@AuthenticationPrincipal OidcUser user, @PathVariable String id, @PathVariable String amount) {
        Long accountId = Long.parseLong(id);
        Double updateAmount = Double.parseDouble(amount);
        String uid = accountRepository.findById(accountId).get().getUserId();
        Double balance = accountRepository.findById(accountId).get().getAccountBalance();

        if (uid.equals(user.getSubject())) {

            if (updateAmount >= 0.00) {
                Double update = balance + updateAmount;
                accountRepository.findById(accountId).get().setAccountBalance(update);
            } else {
                Double update = balance - updateAmount;
                accountRepository.findById(accountId).get().setAccountBalance(update);
            }
        }
        return "redirect:/entries/id=" + accountId;
    }
}
