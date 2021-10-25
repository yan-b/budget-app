package htw.berlin.budgetapp.web;

import htw.berlin.budgetapp.config.Endpoints;
import htw.berlin.budgetapp.config.ViewNames;
import htw.berlin.budgetapp.presistence.AccountEntity;
import htw.berlin.budgetapp.presistence.AccountRepository;
import htw.berlin.budgetapp.service.AccountType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.util.*;

@Controller
public class IndexController {
    private final AccountRepository accountRepository;

    public IndexController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping(value = {Endpoints.SLASH_INDEX, Endpoints.INDEX})
    public ModelAndView getCurrentUserOverview(@AuthenticationPrincipal OidcUser user) {

        if (user != null) {
            Map<String, Object> mav = new HashMap<String, Object>();
            mav.put("name", user.getGivenName() + " " + user.getFamilyName());

            String token = user.getSubject();
            List<AccountEntity> accounts = accountRepository.findByUserId(token);
            Iterator<AccountEntity> accountIterator = accounts.iterator();
            DecimalFormat df2 = new DecimalFormat("#.00");

            if (!accounts.isEmpty()) {
                Double sumBal = null;
                ArrayList<Double> sumList = new ArrayList<Double>();
                int accountCount = 0;

                // Sum of all Account-Balances and Sum of all Accounts.
                while (accountIterator.hasNext()) {
                    AccountEntity acc = accountIterator.next();
                    Double acc_balance = acc.getAccountBalance();
                    sumList.add(acc_balance);
                    accountCount++;
                }
                if (!sumList.isEmpty()) {
                    sumBal = sumList.stream().reduce(0.00, Double::sum);
                } else {
                    sumBal = 0.00;
                }
                mav.put("wealth", df2.format(sumBal).toString());
                mav.put("account_count", accountCount);
            }
            return new ModelAndView(ViewNames.INDEX, mav);
        }
        return new ModelAndView(ViewNames.INDEX);
    }
}
